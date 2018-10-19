let screen;
let renderer;

/**
 * Gets elements that have been loaded
 */
window.onload = function() {
    screen = document.getElementById("screen");
    renderer = screen.getContext("2d");
};

/**
 * The function that periodically gets called in the game loop
 * Takes in how many frames have elapsed
 * Returns whether the next frame should be rendered or not
 */
function draw(frameNumber) {

}

/**
 * Periodically calls the gameLoop function
 */
let frameCount = 0;
let gameLoopHandler = setInterval(function () {
    try {
        renderer.clearRect(0, 0, screen.width, screen.height);
        if (draw(frameCount)) {
            clearInterval(gameLoopHandler);
        }
        frameCount += 1;
    } catch (e) {}
}, 17);
