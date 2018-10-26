# Fairview-Code-Kata-Snek
A backend for a multiplayer game of snake for Fairview Code Club's Code Kata on October 22, 2018 and October 29, 2018.

## Game Description
Code-Kata-Snek is a multiplayer version of the popular game Snake. In this, the rules are simple: have the highest score by the time all Sneks die in order to win. However, sneks can die when they run into another snek or out of the bounds. Each team controls a single snek and must try and end up with the highest score. A snek's score starts at 0, but every turn that it is alive, its length gets added to its score. A snek can become longer by eating the apple. Apples are initially placed in the middle, but whenever they are subsequently consumed, the next apple is placed to be farthest away from all alive sneks. A snek eats an apple when it runs over the apple. In order for each side to control their snek, they must call the API methods described below. For the API, each team will make a team password or a key.

## Explanation of Inputs
Inputs and outputs are taken and given through REST API requests. Controlling the snek requires a turn direction. A turn direction is an integer from -1 to 1. 1 and -1 mean turn right and left respectively, and 0 means continue the current trajectory. Snek controls are relative to the direction that the snek is facing. Snek controls can be tested in the test environment.

## Graphics
To see a graphical representation of the main competition, go to ```URL``` in your browser. To see a live version of a test environment, go to ```URL/test?key=TEAM_KEY``` to see the test environment of the team of the given key.

## Test Environment Controls
Each team will be provided a test environment to test their algorithm in a board with a single snek. All responses are in valid JSON format.

### ```GET URL/test/api - params(key: String)``` 
This method will get the state of the test environment board. Format is a 2d-array of tile states, where tile states are either `"EMPTY"`, `"APPLE"`, `"RED_SNEK"`, `"BLUE_SNEK"`, `"GREEN_SNEK"`, or `"YELLOW_SNEK"`. Each board is 35x35 in dimensions.
### ```POST URL/test/api - params(key: String)```
This method will reset the test environment to its initial state. Useful if you kill your test environment snek.
### ```GET URL/test/api/progress - params(key: String)```
This method will get the meta game information such as scores, snek alive status, turn number, and whether a snek hasn't submitted for their turn. Test environment sneks will always have not submitted for their turn because as soon as they do, their next turn starts.
### ```POST URL/test/api - params(key: String, turnDirection: Int)```
This method will turn the snek of the team with the given key. 1 means turn right, 0 means don't turn, and -1 means turn left. Turns are relative to the direction that the snek is facing. The test environment world will move forward one step immediately after this reqeust is received.

## Competition Controls
The actual competition has similar controls to the test environments but with less flexibility. The world will go forward one step every time each team with a living snek has submitted an input. All responses are in valid JSON.

### ```GET URL/api```
This method gets the state of the competition board. Format is the same as the format of getting the board state of a test environment.
### ```GET URL/api/progress```
This method gets the meta game information like scores, snek alive status, turn number, and what sneks haven't submitted for the current turn.
### ```POST URL/api - params(key: String, turnDirection: Int)```
This method will turn the snek of the team with the given key. 1 means turn right, 0 means don't turn, and -1 means turn left. Turns are relative to the direction that the snek is facing. The competition environment won't update or move forward until all alive sneks submit a turn direction.

## URL
In all the controls mentioned above, the URL is usually <https://fairviewcodekatasnek.herokuapp.com/>. However, please be sure to not flood the Heroku server with too many unnecessary requests. However, if you are running this locally, the URL will usually be <localhost:8080>.

## Running Locally
There are many advantages to running this server locally. One major advantage is the ability to test with multiple sneks. Another advantage is that you won't need to worry about server downtime because you can control whenever the local environment is running. To run locally, clone this repository with ```git clone https://github.com/FHSCodeClub/Fairview-Code-Kata-Snek```, and then in the cloned repository, run ```gradle bootRun```. To control multiple sneks, set team passwords in [KeyAssociater](./src/main/kotlin/com/fairviewcodeclub/snek/KeyAssociater.kt), and use those new keys to control each team's snek.
