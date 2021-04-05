package org.qme.game;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing the instance of the game.
 * @author adamhutchings
 * @since 0.4
 */
public class Game {

    List<Player> playerList = new ArrayList<>();

    private int currentPlayerIndex = 0, turnCount = 0;

    public static Game game = new Game();

    public Game() {

        // For now, one player.
        playerList.add(new Player());

    }

    public Player currentPlayer() {
        return playerList.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        if (++currentPlayerIndex == playerList.size()) {
            currentPlayerIndex = 0;
            ++turnCount;
        }
    }

}
