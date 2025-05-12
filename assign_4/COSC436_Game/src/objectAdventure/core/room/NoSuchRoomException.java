package objectAdventure.core.room;
// $Id: NoSuchRoomException.java 62 2024-02-25 16:04:03Z adam $

import static java.text.MessageFormat.format;

/**
 * A general exception for attempting to access non-existent room.
 * This should not be thrown in normal operations.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class NoSuchRoomException extends RuntimeException {
    /**
     * Constructor for the exception.
     *
     * @param roomId the ID of the offending room.
     */
    public NoSuchRoomException(Integer roomId) {
        super(format("The Room id={0} does not exist.", roomId));
    }

}
