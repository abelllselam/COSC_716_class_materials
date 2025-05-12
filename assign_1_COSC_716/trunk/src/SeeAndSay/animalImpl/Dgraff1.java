package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;

/*
 * The Dgraff1 class is meant to add an animal instance that makes the sound "ROAR"
 */
public class Dgraff1 extends Animal implements Talker {

    private final String sound;

    /**
     * Simple Constructor accepting only a string.
     *
     * @param animalName The sound the animal makes
     */
    public Dgraff1(String animalName) {
        // Call the super constructor, which requires a string.
        super(animalName);

        // Set the sound of the animal, the TIGER ROARS
        this.sound = "ROAR";
    }


    /**
     * Get the sound of the animal.
     *
     * @return The sound of the animal
     */
    @Override
    public String getSound() {
        // Return the sound of the animal
        return this.sound;
    }

}
