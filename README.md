# tic-tac-toe

## Features

* 2 game modes
  * Human vs human: 2 players are created one of the other and can play against each other
  * Human vs computer: only 1 player is manually created. An additional computer player is automatically added and will make moves according to the implemented algorithm.

* Player statistics
  * statistics are only calculated and saved for human players
  * for each player a statistic file (naming: `<playername>.json`) is saved
  * for each won game the gameID, the date and the opponent are saved in the json
  * while playing the amount of won games of the *current* session are displayed under the player name

* Player profile pictures
  * all players get randomly chosen profile pictures from the [Cat as a Service API](https://cataas.com)
  * the profile pictures are loaded in a separate thread during player creation and displayed during the game

## GUI

The GUI is implemented with JavaFX.

There are separate fxml files that each have a dedicated controller for the different views:
* `init.fxml` is used to choose the game mode
* `setup-player.fxml` is used to enter a name for each human player and displays the profile picture
* `game-view.fxml` is used for the actual game

*Note: I couldn't make switching views correctly, so for every switch it opens a new window*

## Structure

Notable characteristics about the classes:
* all classes can be found under the `model` folder
* `Player` is an abstract class and serves as a Superclass for `HumanPlayer` and `ComputerPlayer`
* `SignCounter` is a helper class and contains only static methods
