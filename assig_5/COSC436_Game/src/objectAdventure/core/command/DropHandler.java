package objectAdventure.core.command;
// $Id: DropHandler.java 1306 2025-04-17 19:12:49Z awolde1 $

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static objectAdventure.core.item.ItemInteractionEventType.DROP;

/**
 * The DropHandler class is responsible for managing all item-dropping actions
 * within the game. It processes player commands related to dropping items
 * (both single items and "drop all" commands), validates the actions, and
 * communicates with the GameController to handle the transfer of items.
 *
 * <p>This class helps decouple the drop-related functionality from the GameController,
 * making the design more modular and allowing for easier maintenance and testing.</p>
 */
class DropHandler implements ActionHandler {

    /**
     * Reference to the GameController, used for accessing the player's inventory,
     * the current room, and performing transfers between item containers.
     */
    private final GameController gameController;

    /**
     * Constructs a new DropHandler instance with the given GameController dependency.
     *
     * @param gameController The GameController instance that manages the game state and interactions.
     */
    public DropHandler(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Handles a single-item drop action based on the player's command.
     * This method checks whether the specified item exists in the player's inventory,
     * and if found, attempts to drop it in the current room.
     *
     * @param playerCommand The player's input containing the item to drop.
     * @return A string result of the drop action, either success or failure messages.
     */
    public String handleAction(PlayerCommand playerCommand) {
        // Fetch the item to drop using its alias from the player's inventory
        Collection<Item> itemList = gameController.getPlayer().getItemList();
        String noun = playerCommand.noun();

        // Get the item from the player's inventory based on the noun provided
        final var itemFromAlias = gameController.getItemFromAlias(noun, itemList);

        // Attempt to drop the item, or return an appropriate failure message
        return itemFromAlias
                .map(item -> handleAction(item, playerCommand)) // If the item is found, proceed to drop it
                .orElse(format("You don't have a '%s' to drop.", noun)); // If not, return a failure message
    }

    /**
     * Handles the actual drop action for an individual item, ensuring any necessary
     * interactions or transfer processes are executed correctly.
     *
     * @param item          The item to drop from the player's inventory.
     * @param playerCommand The command issued by the player that initiated the drop.
     * @return A string result of the drop operation, including success or failure messages.
     */
    private String handleAction(Item item, PlayerCommand playerCommand) {
        // Get the item's display name for use in messages
        String itemDisplayName = item.getItemDisplayName();

        // Trigger the interaction event associated with "DROP"
        ItemInteractionResult result = item.itemInteractionHandler(
                new ItemInteractionEvent(DROP, playerCommand, gameController.getPlayer())
        );

        String responseMessage;

        // If the interaction was successful, proceed to transfer the item to the current room
        if (result.bSuccess()) {
            // Attempt to move the item from the player's inventory to the room
            boolean xfered = GameController.transferItem(
                    gameController.getPlayer(), // Source: Player's inventory
                    gameController.getCurrentRoom(), // Destination: Current room
                    item // The item being transferred
            );

            // Construct appropriate success or failure messages
            responseMessage = xfered
                              ? result.message().isBlank() // Use the interaction's custom message if provided
                                ? format("You dropped the %s.", itemDisplayName)
                                : result.message()
                              : "You couldn't drop the item!"; // Transfer failed
        } else {
            // Construct a failure message if the interaction itself was unsuccessful
            responseMessage = result.message().isBlank()
                              ? format("You can't drop the %s.", itemDisplayName)
                              : result.message();
        }

        return responseMessage; // Return the final message to the player
    }

    /**
     * Handles the "drop all" action, which involves dropping all items currently held
     * by the player into the current room.
     *
     * @param playerCommand The command issued by the player to drop all items.
     * @return A string result of the drop-all operation, including messages for each item dropped.
     */
    public String dropAllItems(PlayerCommand playerCommand) {
        // Get a copy of the player's inventory as a list (to avoid concurrent modification)
        List<Item> itemList = new ArrayList<>(gameController.getPlayer().getItemList());

        // If the inventory is empty, return an appropriate message
        if (itemList.isEmpty()) {
            return "You're not holding anything to drop.";
        } else {
            // Attempt to drop each item one by one and collect drop action messages
            return itemList.stream()
                           .map(item -> handleAction(item, playerCommand)) // Process each item drop
                           .collect(Collectors.joining("\n")); // Combine all messages into a single response
        }
    }
}
