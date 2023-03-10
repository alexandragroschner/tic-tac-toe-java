package com.example.tictacto;

import com.example.tictacto.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A fxml controller class connected to game-view.fxml
 * It gets the created game with its players for the PlayerSetupController.
 * It reacts to turns made on the GUI and maps them to actions on the Game class.
 */
public class GameController {

    private Stage thisStage;
    private final PlayerSetupController playerSetupController;
    private Game game;

    @FXML
    private Label currentplayername;
    @FXML
    private Label currentplayerlabel;
    @FXML
    private Label infoLabel;
    @FXML
    private GridPane gameField;
    @FXML
    private Button lt;
    @FXML
    private Button lm;
    @FXML
    private Button lb;
    @FXML
    private Button mt;
    @FXML
    private Button mm;
    @FXML
    private Button mb;
    @FXML
    private Button rm;
    @FXML
    private Button rt;
    @FXML
    private Button rb;
    private ArrayList<Button> buttons = new ArrayList<>();
    @FXML
    private ImageView player1pic;
    @FXML
    private ImageView player2pic;
    @FXML
    private Label player1name;
    @FXML
    private Label player2name;
    @FXML
    private Button newGame;
    @FXML
    private Label player1gamesWon;
    @FXML
    private Label player2gamesWon;

    public GameController(PlayerSetupController playerSetupController) {
        // gets the PlayerSetupController instance so it has access to the previously created game
        this.playerSetupController = playerSetupController;

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buttons.add(lt);
        buttons.add(lm);
        buttons.add(lb);
        buttons.add(mt);
        buttons.add(mm);
        buttons.add(mb);
        buttons.add(rt);
        buttons.add(rm);
        buttons.add(rb);
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    private void initialize() throws Exception {
        this.game = playerSetupController.getGame();

        lt.setOnAction(event -> {
            try {
                clickLT();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        lm.setOnAction(event -> {
            try {
                clickLM();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        lb.setOnAction(event -> {
            try {
                clickLB();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        mt.setOnAction(event -> {
            try {
                clickMT();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        mm.setOnAction(event -> {
            try {
                clickMM();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        mb.setOnAction(event -> {
            try {
                clickMB();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        rt.setOnAction(event -> {
            try {
                clickRT();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        rm.setOnAction(event -> {
            try {
                clickRM();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        rb.setOnAction(event -> {
            try {
                clickRB();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        newGame.setOnAction(event -> {
            try {
                newGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // display initial game view (sets profile pics and names)
        player1pic.setImage(new Image(game.getPlayers().get(0).getProfilePicUrl()));
        player1name.setText(game.getPlayers().get(0).getName() + " with sign: " + game.getPlayers().get(0).getSign());
        player2pic.setImage(new Image(game.getPlayers().get(1).getProfilePicUrl()));
        player2name.setText(game.getPlayers().get(1).getName() + " with sign: " + game.getPlayers().get(1).getSign());
        updateScores();

        // make initial computerplayer move
        if (game.getCurrentPlayer() instanceof ComputerPlayer) {
            clickAction(game);
        }

        currentplayername.setText(game.getCurrentPlayer().getName());
    }

    /**
     * Resets GUI and created new game with same players and mode as last game
     * @throws Exception
     */
    private void newGame() throws Exception {
        // reset grid
        for (Button b: buttons) {
            b.setText("");
            b.setDisable(false);
        }
        currentplayerlabel.setText("Current Player:");
        // create new games with same players and mode
        game = new Game(game.getPlayers(), game.getMode());

        if (!game.isReady()) {
            throw new Exception("Something wrong with the new game");
        }

        currentplayername.setText(game.getCurrentPlayer().getName());
        infoLabel.setText("");

        // trigger initial computer player turn if necessary
        if (game.getMode() == GameMode.HVC && game.getCurrentPlayer() instanceof ComputerPlayer) {
            clickAction(game);
        }
    }

    private void updateScores() {
        player1gamesWon.setText("Games won:\n" + game.getPlayers().get(0).getGamesWon());
        player2gamesWon.setText("Games won:\n" + game.getPlayers().get(1).getGamesWon());
    }

    /**
     * Updates GUI after a game results in a win
     * Informs a Human Player about his win (for statistics)
     * @throws Exception
     */
    private void endGame() throws Exception {
        infoLabel.setText("Winner is: " + game.getPlayerWithSign(game.getWinnerSign()).getName());
        currentplayername.setText("");
        currentplayerlabel.setText("");

        if (game.getPlayerWithSign(game.getWinnerSign()) instanceof HumanPlayer) {
            // notify player that he won this game so that GameStats are created
            ((HumanPlayer)game.getPlayerWithSign(game.getWinnerSign())).wonGame(game);
        }

        updateScores();
    }

    private void endTieGame() {
        infoLabel.setText("It's a tie!");
        currentplayername.setText("");
        currentplayerlabel.setText("");
    }

    /**
     * Calls approriate end function of game (tie or not) if the game ended
     * Disables all buttons.
     * @param game
     * @throws Exception
     */
    private void endGameIfOver(Game game) throws Exception {
        if (game.getWinnerSign() != null) {
            for (Button b: buttons) {
                b.setDisable(true);
            }
            endGame();
        } else if (game.isTie()) {
            for (Button b: buttons) {
                b.setDisable(true);
            }
            endTieGame();
        }
    }

    /**
     * Collects all actions after a button was clicked.
     * If game mode is "Human vs Computer" triggers a computer player turn and sets the resulting button to its sign.
     * Otherwise just switches displaying the current player name.
     * Calls check for ending the game.
     * @param game
     * @throws Exception
     */
    private void clickAction(Game game) throws Exception {
        if (game.getMode() == GameMode.HVC) {
            ArrayList<Integer> position = ((ComputerPlayer) game.getCurrentPlayer()).makeTurn(game);
            if (position == null) {
                endTieGame();
                return;
            }
            getNodeByRowColumnIndex(position, gameField).setText(game.getNextPlayer().getSign().toString());
            getNodeByRowColumnIndex(position, gameField).setDisable(true);
        }
        currentplayername.setText(game.getCurrentPlayer().getName());
        endGameIfOver(game);
    }

    private void clickLT() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(0,0, game) == 1) {
            // change button label to sign of current player
            lt.setText(game.getNextPlayer().getSign().toString());
            lt.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }

    private void clickLM() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(1,0, game) == 1) {
            // change button label to sign of current player
            lm.setText(game.getNextPlayer().getSign().toString());
            lm.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickLB() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(2,0, game) == 1) {
            // change button label to sign of current player
            lb.setText(game.getNextPlayer().getSign().toString());
            lb.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMT() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(0,1, game) == 1) {
            // change button label to sign of current player
            mt.setText(game.getNextPlayer().getSign().toString());
            mt.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMM() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(1,1, game) == 1) {
            // change button label to sign of current player
            mm.setText(game.getNextPlayer().getSign().toString());
            mm.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMB() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(2,1, game) == 1) {
            // change button label to sign of current player
            mb.setText(game.getNextPlayer().getSign().toString());
            mb.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRT() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(0,2, game) == 1) {
            // change button label to sign of player i.e. "nextPlayer" because "makeTurn" already switched the players
            rt.setText(game.getNextPlayer().getSign().toString());
            rt.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRM() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(1,2, game) == 1) {
            // change button label to sign of current player
            rm.setText(game.getNextPlayer().getSign().toString());
            rm.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRB() throws Exception {
        if (((HumanPlayer)game.getCurrentPlayer()).makeTurn(2,2, game) == 1) {
            // change button label to sign of current player
            rb.setText(game.getNextPlayer().getSign().toString());
            rb.setDisable(true);
            clickAction(game);
        } else {
            infoLabel.setText("Field already set. Try another one");
        }
    }

    /**
     * Takes a position and the gridPane of the gamefield as parameters and returns the button on the provided position
     * @param position
     * @param gridPane
     * @return the instance of the button on the given position
     */
    // gets a specific button by providing a position array of the gameField
    public Button getNodeByRowColumnIndex (ArrayList<Integer> position, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(Objects.equals(GridPane.getRowIndex(node), position.get(0)) && Objects.equals(GridPane.getColumnIndex(node), position.get(1))) {
                result = node;
                break;
            }
        }
        return (Button) result;
    }
}
