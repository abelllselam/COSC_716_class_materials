package objectAdventure;
// $Id: RoomInitializer.java 1357 2025-04-18 02:20:13Z awolde1 $

import objectAdventure.core.map.RoomList;

/**
 * Note that this REALLY doesn't belong in this package in terms of "good design" (it
 * should in the Room package or perhaps with the Core package). However, it's placed
 * here to make it easier to edit as everyone adds their Room Implementations
 * and makes them available to the shell.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class RoomInitializer {

    private RoomInitializer() {
        // Utility classes cannot (should not) be instantiated.
    }

    /**
     * Initialization method for all rooms in the game.
     * <p>
     * NOTE: As some want to constantly rearrange imports, use the "Fully Qualified
     * Name" (FQN) in this class to avoid merge conflicts.
     * <p>
     * Using a "newInstance" method is often a better approach than using a
     * constructor directly, for reasons that will be discussed in class later in
     * the semester.
     *
     * @param rooms The list of rooms in the game.
     */
    public static void initRooms(RoomList rooms) {
        // !!! DO NOT MODIFY THE EXAMPLE BELOW, OTHERS ARE REPLYING UPON IT !!!
        // !!! All rooms must be constructed with the room ID and Room Name
        // (short name to be displayed at prompts) !!!

        // NOTE: The FQN (Fully Qualified Name) is used to prevent merge conflicts due to
        //       "import wars" within a source file that is touched by many developers.
        //       Many IDE's will automatically reformat imports, causing merge conflicts.
        //       Using the FQN prevents this problem.

        // aconover
        // NOTE: The room ID (the one assigned), and the room's display name.
        rooms.addRoom(objectAdventure.world.aconover.StartRoom.newInstance(0, "The Lobby"));

        /*
         * **************************************************************************
         * EACH STUDENT SHOULD ADD THEIR ROOMS BELOW.
         * USE THE SAME FORMAT AS ABOVE. (The "// aconover" example.)
         *
         * Use the "Fully Qualified Class Name" for the room as shown. This will
         * prevent most merge conflicts caused by frequently changing import
         * statements. The room ID should be the same as the one assigned.
         *
         * Be sure to use *Your Own* ROOM ID, ROOM NAME, and USERNAME.
         * **************************************************************************
         */


        // amcnei8

        // aroger28

        // asillah2

        // awolde1
            objectAdventure.world.awolde1.MyRoom myRoom = objectAdventure.world.awolde1.MyRoom.newInstance();
            objectAdventure.world.awolde1.AncientKey key = new objectAdventure.world.awolde1.AncientKey();

            myRoom.initWithItem(key);       
            myRoom.addItem(key);            
            rooms.addRoom(myRoom);
        // babumer1

        // bnaylon1

        // careval1

        // chogga1

        // dbinkam1

        // dgraff1

        // eadeniy1

        // easomuy1

        // gofoche1

        // iashio1

        // jbhavsa2

        // jsykes9

        // kdingm1

        // kgiles3

        // kmattox2

        // kpatel43

        // manjori1

        // masubo2

        // mopoku3

        // mstern13

        // nbhatt5

        // nketter1

        // promer2

        // screedon

        // sjnbapt1

        // sschatz1

    }
}
