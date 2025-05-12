package objectAdventure.core.player;
// $Id: Player.java 69 2024-05-15 04:26:38Z aconover $

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
