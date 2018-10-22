let screen;
let renderer;

/**
 * Gets elements that have been loaded
 */
window.onload = () => {
    screen = document.getElementById("screen");
    renderer = screen.getContext("2d");

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
            renderer.fillRect(cellSize * row, cellSize * col, cellSize, cellSize);
        }
    }
}

/**
 * Periodically calls the gameLoop function
 */
let counter = 0;
let gameState = null;
let isDone = false;
let gameLoopHandler = setInterval(async () => {
    try {
        if (counter % 30 === 0) {
            gameState = JSON.parse(await (await fetch(`${window.location.href}api`)).text());
            isDone = JSON.parse(await (await fetch(`${window.location.href}api/progress`)).text()).done;
        }
        draw(gameState);
        if (isDone) {
            clearInterval(gameLoopHandler);
        }
        counter++;
    } catch (e) {}
}, 17);
