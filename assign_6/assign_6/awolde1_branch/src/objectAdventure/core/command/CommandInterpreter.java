package objectAdventure.core.command;
// $Id: CommandInterpreter.java 1306 2025-04-17 19:12:49Z awolde1 $

import objectAdventure.core.item.ItemInteractionEventType;
import objectAdventure.core.map.Direction;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static objectAdventure.core.DescriptionType.LONG;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!! NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>This class is intended to be improved upon by using a more sophisticated design pattern such
 * as the 'Interpreter' pattern, perhaps in combination with other collaborating patterns such as
 * 'Command', 'Chain of Responsibility', and/or 'Visitor' patterns!!!</p>
 *
 * <p>Aside from Directions and a few helpful exceptions (noted in "showCommands()"), all commands
 * are two words (verb noun).</p>
 *
 * @param controller The game controller that manages the game state and logic.
 * @author Adam J. Conover, COSC436/COSC716
 */
public record CommandInterpreter(GameController controller) {

    /**
     * Parse player input.
     *
     * @param inputLine The line typed by the user
     * @throws UnknownCommandException Thrown if the command cannot be processed
     */
    Optional<String> processCommand(final String inputLine) throws UnknownCommandException {
        String preProcessedLine = controller.preProcessInput(inputLine);
        var normalizedLine = preProcessedLine.trim().toUpperCase();

        // Bail out if blank
        if (normalizedLine.isBlank()) {
            return Optional.empty();
        }

        // Return help if requested
        if (normalizedLine.startsWith("?") || normalizedLine.startsWith("HELP")) {
            return Optional.of(CommandHelp.help());
        }

        // Tokenize the string
        var tokenizedInput = PlayerCommand.tokenizeInputString(normalizedLine);

        // Process command
        if (tokenizedInput.isPresent()) {
            PlayerCommand playerCommand = tokenizedInput.get();
            return processCommand(playerCommand);
        } else {
            return Optional.of(CommandHelp.help());
        }
    }

    /**
     * Processes a command issued by the player and performs the corresponding action.
     * The method interprets the command based on the verb and noun provided in the
     * {@link PlayerCommand}, and executes the appropriate game logic. It also handles
     * any invalid or unknown commands by throwing an {@link UnknownCommandException}.
     *
     * @param playerCommand The command issued by the player. Contains the verb and noun extracted
     *                      from the player's input.
     * @return An {@link Optional} containing the response string resulting from the command execution.
     *         If the command does not produce a text response (e.g., movement commands), the Optional
     *         will be empty.
     * @throws UnknownCommandException If the command is not recognized or cannot be processed.
     */
    private Optional<String> processCommand(final PlayerCommand playerCommand) throws UnknownCommandException {

        // Extract the verb and noun from the command because they are used multiple times.
        final var noun = playerCommand.noun();
        final var verb = playerCommand.verb();

        // NOTE: This switch is intended for an eventual refactoring.
        var response = switch (verb) {
            // Movement
            case "N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST", "U", "UP", "D", "DOWN" -> {
                Direction.directionFromLexeme(verb).ifPresent(controller::movePlayer);

                // Nulls are usually bad, but this one never leaves the method and is only used for part of the "optional" response.
                yield null;
            }

            // Inventory
            case "I", "INVENTORY" -> "Inventory:%n%s".formatted(controller.getFormattedInventoryItemString());

            // Commands for getting items
            case "GET", "TAKE" -> controller.handleTakeItem(playerCommand);

            // Commands for dropping items
            case "DROP" -> controller.handleDropItem(playerCommand);

            // Looking at objects. (note this a special command as looking could be for rooms or items).
            case "L", "LOOK" -> controller.handleLook(playerCommand);

            // Various "DISPLAY" commands
            case "DISPLAY", "SHOW" -> {
                if ("ROOM".equalsIgnoreCase(noun)) {
                    controller.getCurrentRoom().displayRoomImage();
                    yield null;
                } else {
                    yield "I don't know how to DISPLAY %s.".formatted(noun);
                }
            }

            // Teleportation from room to room
            case "T", "TELEPORT" -> {
                try {
                    var roomId = parseInt(noun);
                    if (controller.handleTeleport(roomId)) {
                        yield ("Teleported to room: " + noun);
                    } else {
                        yield "Teleportation to non-existent locations is not yet supported.";
                    }
                } catch (NumberFormatException nfe) {
                    Logger.getGlobal().warning("Invalid room ID: " + noun);
                    yield "You can only teleport to a room by its ID.";
                }
            }

            // Various "DISPLAY" commands
            case "DEBUG" -> switch (noun) {
                case "ROOM" -> DEBUG_getFormattedRoomInfo();
                case "MAP" -> controller.DEBUG_DescribeAllRoomContents();
                default -> "I don't know how to DISPLAY %s.".formatted(noun);
            };

            // Set the log level dynamically.
            case "LOG" -> {
                try {
                    Logger.getGlobal().setLevel(Level.parse(noun));
                    yield format("Log level set to: %s", noun);
                } catch (IllegalArgumentException __) {
                    yield format("Invalid log level: %s", noun);
                }
            }

            // The default is to assume the command was the result of an interaction with an item.
            // Loop though all item interaction commands and fire off the one that matches the verb.
            default -> {
                ItemInteractionEventType action =
                        ItemInteractionEventType
                                .actionFromLexeme(verb)
                                .orElseThrow(() -> new UnknownCommandException(
                                        "I don't understand: %s".formatted(playerCommand.originalInput())));

                yield controller.interactWithItem(playerCommand, action);
            }
        };

        // Return the response
        return Optional.ofNullable(response);
    }

    /**
     * Helper for processCommand() for greater legibility.
     *
     * @return Formatted room description, room contents, and Inventory
     */
    private String DEBUG_getFormattedRoomInfo() {
        return """
                Room Author: %s
                
                Room Description:
                %s
                
                Items in Room:
                %s
                
                Items in Inventory:
                %s""".formatted(
                controller.getCurrentRoom().getRoomAuthor(),
                controller.getCurrentRoom().getRoomDescription(),
                controller.getRoomItemDisplayNames(LONG),
                controller.getFormattedInventoryItemString());
    }
}
