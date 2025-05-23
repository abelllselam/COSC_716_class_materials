package objectAdventure.common;
// $Id: RoomEventListener.java 1070 2025-02-10 23:54:58Z aconover $

import objectAdventure.core.player.Player;
import objectAdventure.core.player.PlayerImpl;

import java.util.logging.Logger;

/**
 * Rooms that implement RoomEventListener will be notified when a player enters or leaves the room.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public interface RoomEventListener {

    /**
     * Notification method for a player entering a room. (Invoked when a player enters a room.)
     *
     * @param player The player entering the room.
     */
    default void playerEnteringRoom(Player player) {
        Logger.getGlobal().info("Player %s entering room %d from %d"
                .formatted(player, player.getCurrentRoomId(), player.getPreviousRoomID()));
    }

    /**
     * Notification method for a player leaving a room. (Invoked when a player leaves a room.)
     *
     * @param player The player leaving the room.
     */
    default void playerLeavingRoom(Player player) {
        Logger.getGlobal().info("Player %s leaving room %d for %d"
                .formatted(
                        player,
                        player.getPreviousRoomID(),
                        player.getCurrentRoomId()));
    }

    /**
     * Notification method for a player looking at a room. (Invoked when a player looks at a room.)
     * 
     * @param player
     */
    default void playerLookingAtRoom(PlayerImpl player) {
        Logger.getGlobal().info("Player %s looking at room %d"
                .formatted(player, player.getCurrentRoomId()));
    }
        
}
