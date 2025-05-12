package objectAdventure.core.command;
// $Id: UserInput.java 1028 2025-01-02 20:26:50Z aconover $

import objectAdventure.core.Direction;
import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * The UserInput class handles the main user input loop.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class UserInput {
    private UserInput() {
    }

    /**
     * The main user input loop. This can eventually be refactored into a more universal "command
     * processor". But for now, we are only dealing with movement, so the input is only compass
     * directions.
     *
     * @param commandProcessor The command processor used for the input loop.
     * @param scanner          The scanner used in the input loop.
     */
    public static void userInputLoop(CommandProcessor commandProcessor, Scanner scanner) {
        int currentRoomId = java.lang.Integer.MIN_VALUE;
        GameController controller = commandProcessor.getController();

        do {
            // AJC_TODO: Keep a list of rooms visited to avoid long descriptions once seen.
            // Show the room description only on room changes.
            if (controller.getPlayer().getCurrentRoomId() != currentRoomId) {
                try {
                    out.println(controller.getCurrentRoom().getRoomDescription());
                } catch (NoSuchRoomException ex) {
                    out.println(ex.getMessage());
                }
                currentRoomId = controller.getPlayer().getCurrentRoomId();
            }

            // Prompt the user.
            String inputLine = displayUserPrompt(controller, scanner);

            // Break out of the loop if the user is a quitter.
            if ("q".equals(inputLine)) {
                out.println("Use an uppercase Q (or type \"quit\") to quit.");
                continue;
            }

            // Break out of the loop if the user is a quitter.
            if ("Q".equals(inputLine) || "QUIT".equalsIgnoreCase(inputLine)) {
                // Quit the loop without any further processing!
                break;
            }

            // Process the command.
            try {
                var result = commandProcessor.processCommand(inputLine.toUpperCase());
                result.ifPresent(UserInput::displayCommandResult);
            } catch (UnknownCommandException ex) {
                out.printf("Command Error: %s%n", ex.getMessage());
            } catch (NoSuchRoomException ex) {
                err.printf("Invalid Room: %s%n", ex.getMessage());
            }

            Logger.getGlobal().fine(getGameDebugInfo(controller));
        } while (true);  // A debatable practice, but eliminates a bunch of nested (or cascading) "if" statements!
    }

    /**
     * Display the result of the command, only if there is a result.
     *
     * @param result The text result of the command.
     */
    private static void displayCommandResult(String result) {
        if (result != null && !result.isBlank()) {
            out.println(result);
        }
    }

    /**
     * Display the user Prompt.
     *
     * @param controller The game controller.
     * @param input      The Scanner source for the input.
     */
    private static String displayUserPrompt(GameController controller, Scanner input) {
        // Build a string of the available exit directions.
        List<Direction> exitDirections = controller.getExitDirections();

        out.println();

        // Display/Prompt
        try {
            Room room = controller.getCurrentRoom();
            out.printf("=====> In Room %d: %s <=====%n", room.getRoomId(), room.getRoomName());
        } catch (NoSuchRoomException ex) {
            out.printf("=====> In Room %d <=====%n", controller.getPlayer().getCurrentRoomId());
        }

        out.printf("Exits: %s%n".formatted(
                exitDirections
                        .stream()
                        .map(Direction::getLongName)
                        .map(String::valueOf)
                        .collect(joining(", "))));

        out.print("Enter Command: ");

        return input.nextLine().trim();
    }

    /**
     * Display the current info for the room and player.
     *
     * @param controller The game controller containing the game state information,
     */
    private static String getGameDebugInfo(GameController controller) {
        return """
                Room Information:
                %s
                Player Information:
                %s
                """.formatted(controller.getCurrentRoomInfo(), controller.getPlayer());
    }
}
