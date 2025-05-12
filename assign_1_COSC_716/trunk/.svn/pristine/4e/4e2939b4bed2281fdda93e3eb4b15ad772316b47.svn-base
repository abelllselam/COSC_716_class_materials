package SeeAndSay; // Define the package this interface belongs to.

/**
 * Named is an interface that represents an entity with a name.
 * <p>
 * This interface enforces that any implementing class must provide
 * a way to retrieve the name of the entity it represents.
 * It is especially useful in scenarios where entities need to be
 * uniquely identified or categorized by their name (e.g., managing
 * animals on a farm).
 */
public interface Named {
    /**
     * Retrieves the name of the entity.
     *
     * @return A string representing the name of the entity.
     * Implementing classes will provide the logic to return
     * an appropriate name specific to the entity.
     */
    String getName();


    /**
     * Provides a default implementation for setting the name of an entity that implements the Named interface.
     * The method demonstrates the intent to rename an entity.
     *
     * @param newName The new name to assign to the entity. Implementing classes should provide the logic
     *             to update the entity's name according to the specified value.
     */
    default void setName(String newName) {
        String currentName = this.getName();
        System.out.println("You must implement setName() to rename: " + currentName + " to " + newName);
    }
}
