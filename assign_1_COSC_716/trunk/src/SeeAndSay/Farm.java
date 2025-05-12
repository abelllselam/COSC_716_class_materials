package SeeAndSay;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The Farm class serves as a container for managing a collection
 * of animals or entities that implement the Named interface.
 * It provides functionality to add new entities and retrieve existing ones.
 *
 * @param <T> The type of entities this farm will manage.
 *            Must extend the Named interface to ensure every entity has a name.
 */
public class Farm<T extends Named> {
    // A collection of entities stored in a map, using their name as the key.
    private final Map<String, T> animalMap;

    /**
     * Constructs a new instance of the Farm class.
     * Initializes an empty map to store entities.
     */
    public Farm() {
        // Initialize the animal map using a HashMap implementation.
        this.animalMap = new HashMap<>();
    }

    /**
     * Adds an animal or entity to the farm.
     * The entity's name is used as a key to ensure uniqueness.
     *
     * @param animal The entity to add to the farm. The entity must
     *               implement the Named interface to provide a name.
     */
    public void addAnimal(T animal) {
        // Add the specified entity to the map, using its name as the key.
        this.animalMap.put(animal.getName(), animal);
    }

    /**
     * Retrieves an animal or entity from the farm by its name.
     * Provides an Optional that will either contain the entity or be empty
     * if no entity with the specified name exists.
     *
     * @param name The name of the entity to retrieve.
     * @return An Optional containing the entity if found, or empty otherwise.
     */
    public Optional<T> getAnimalByName(String name) {
        // Look up the entity by its name in the map and return as an Optional.
        return Optional.ofNullable(this.animalMap.get(name));
    }
}
