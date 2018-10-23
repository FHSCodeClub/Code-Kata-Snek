let screen;
let renderer;

/**
 * Gets elements that have been loaded
 */
window.onload = () => {
    screen = document.getElementById("screen");
    renderer = screen.getContext("2d");
    renderer.strokeStyle = "#000";

    function resizeProcedure() {
        if (window.innerWidth / 35 < window.innerHeight / 35) {
            screen.width = window.innerWidth;
            screen.height = window.innerWidth;
        } else {
            screen.height = window.innerHeight;
            screen.width = window.innerHeight;
        }
    }

    resizeProcedure();
    window.onresize = resizeProcedure();
};

/**
 * The function that periodically gets called in the game loop
 * Takes in how many frames have elapsed
 * Returns whether the next frame should be rendered or not
 */
function draw(gameState) {
    let cellSize = screen.width / 35;
    for (let row = 0; row < 35; row++) {
        for (let col = 0; col < 35; col++) {
            let tile = gameState[row][col];
            renderer.fillStyle = "#fff";
            if (tile === "RED_SNEK") {
                renderer.fillStyle = "#f00";
            } else if (tile === "BLUE_SNEK") {
                renderer.fillStyle = "#00f";
            } else if (tile === "GREEN_SNEK") {
                renderer.fillStyle = "#0f0";
            } else if (tile === "YELLOW_SNEK") {
                renderer.fillStyle = "#ffff33";
            } else if (tile === "APPLE") {
                renderer.fillStyle = "#ff69b4";
            }
            renderer.fillRect(cellSize * col, cellSize * row, cellSize, cellSize);
            renderer.strokeRect(cellSize * col, cellSize * row, cellSize, cellSize);
        }
    }
}

/**
 * Periodically draws the game state
 */
let counter = 0;
let gameState = null;
let drawnTurn = -1;
let isDone = false;
let key = new URLSearchParams(window.location.search).get("key");
let gameLoopHandler = setInterval(async () => {
    try {
        if (counter % 3 === 0) {
            let gameInfo = JSON.parse(await (await fetch(`${window.location.href.split("?")[0]}/api/progress?key=${key}`)).text());
            isDone = gameInfo.done;
            if (drawnTurn !== gameInfo.turn) {
                gameState = JSON.parse(await (await fetch(`${window.location.href.split("?")[0]}/api?key=${key}`)).text());
                drawnTurn = gameInfo.turn;
            }
        }
        draw(gameState);
        if (isDone) {
            clearInterval(gameLoopHandler);
        }
        counter++;
    } catch (e) {}
}, 200);
