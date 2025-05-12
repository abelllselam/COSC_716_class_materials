package SeeAndSay;

/**
 * The Animal class is the base class for all animals in the SeeAndSay project.
 * It implements the Named interface.
 */
public class Animal implements Named {
    private final String animalName;

    /**
     * Constructor for the Animal class.
     *
     * @param animalName The name of the animal.
     */
    public Animal(String animalName) {
        this.animalName = animalName;
    }

    /**
     * Gets the name of the animal.
     *
     * @return The name of the animal.
     */
    @Override
    public String getName() {
        return this.animalName;
    }
}

