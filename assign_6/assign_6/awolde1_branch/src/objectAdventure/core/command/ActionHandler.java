package objectAdventure.core.command;
// $Id: ActionHandler.java 1306 2025-04-17 19:12:49Z awolde1 $

/**
 * Represents a handler for processing player commands in the game. The classes implementing this interface
 * are responsible for executing actions based on the PlayerCommand provided.
 *
 * <p>The handleAction method serves as the entry point for handling the specific command logic depending
 * on the implementation.</p>
 */
public interface ActionHandler {

    /**
     * Handles the action associated with the given PlayerCommand.
     *
     * @param command The PlayerCommand to be handled.
     * @return A string message indicating the result of the action.
     */
    String handleAction(PlayerCommand command);
}
