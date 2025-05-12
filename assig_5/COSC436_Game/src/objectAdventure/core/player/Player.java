package objectAdventure.core.player;
// $Id: Player.java 1306 2025-04-17 19:12:49Z awolde1 $

import objectAdventure.core.item.ItemContainer;

/**
 * A player in the game. The player maintains an inventory and room ID.
 */
public interface Player extends ItemContainer {

    /**
     * Gets the current room ID of the player.
     *
     * @return The currentRoom ID for player
     */
    int getPreviousRoomID();

    /**
     * Gets the current room ID of the player.
     *
     * @return The currentRoom ID
     */
    int getCurrentRoomId();

    /**
     * Gets the destination room ID of the player.
     *
     * @return The destinationRoom ID
     */
    default int getDestinationRoomId() {
        return this.getCurrentRoomId();
    }

    /**
     * Get the player's name.
     *
     * @return The player's name.
     */
    String getPlayerName();
}
