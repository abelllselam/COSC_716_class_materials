package objectAdventure.world.aconover;
// $Id: StartRoom.java 1032 2025-01-03 19:12:38Z aconover $

import objectAdventure.common.Utils;
import objectAdventure.core.room.Room;

/**
 * The StartRoom is the first room the player will see when they start the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class StartRoom extends Room {
    /**
     * Constructor for the room.
     * <p>
     * This is private because we are using a simple static initializer to create the new room.
     *
     * @param roomId The ID of the room.
     */
    private StartRoom(final int roomId, final String roomName) {
        super(roomId, roomName);
        setRoomAuthor("Adam J. Conover");

        String description = """
                This is not a very interesting room, but this description
                is here mainly to illustrate how to use multi-line strings
                in Java in an easily readable manner. (If you are seeing this
                text "in game", then look over the source code for this room.)
                
                You can type "help" (or "?") to see a list of commands that
                you can use in the game or "quit" (or "Q") to quit.""";


        // Set the room author and description.
        final String roomDisplayHeader = Utils.boxifyText(roomName + '\n' + getRoomAuthor());
        setRoomDescription(roomDisplayHeader + '\n' + description);
    }

    /**
     * Factory methods are used to create new objects using static methods, and this is sometimes a
     * better approach than using a constructor directly. It allows the constructor to complete the
     * essential initialization of the object, and then the factory method can perform additional
     * initialization on a fully instantiated object.
     *
     * @param roomId The ID of the room.
     * @return A new room.
     */
    public static Room newInstance(final int roomId, String roomName) {
        // Create a new room
        var theRoom = new StartRoom(roomId, roomName);

        // Create a new item
        var aDemoItem = new DemoItem();

        // Add the Item to the room
        theRoom.addItem(aDemoItem);

        // Return the room.
        return theRoom;
    }
}
