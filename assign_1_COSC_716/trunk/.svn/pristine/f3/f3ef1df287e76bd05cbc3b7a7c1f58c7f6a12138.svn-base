package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;

public class Sschatz extends Animal implements Talker {
    
    private final String sound;
    /**
     * Constructor for Sschatz, takes in String
     * 
     *  @param animalName the name of the animal
     * 
     */

     //animal class implements named, therefore it must have a name. For that reason the super constructor takes in animalName.

    public Sschatz(String animalName) {

        // Call the super constructor, which requires a string identifying the animal name.
        super(animalName);

        // Set the sound of the animal, as this class implements Talker, thus is requires the sound of the animal in the constructor.
        this.sound = "Tut tut it looks like rain";
    }

    /**
     * getSound method gets the sound the animal makes
     *  
     * @return the sound as a string
     * 
     */

     //Since class Sschatz implements Talker, it must include getSound() method. 

    @Override 
    public String getSound() {
        // Return the sound of the animal
        return this.sound;
    }
}
