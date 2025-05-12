package objectAdventure.common;
// $Id: Observer.java 7 2023-10-19 14:05:26Z aconover $

/**
 * Observer pattern interface (Observer)
 *
 * @param <T> The Datatype for the message being passed.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
@FunctionalInterface
public interface Observer<T> {

    /**
     * Update the observer with a message passed from the Observable.
     *
     * @param object The Object being passed as part of the update.
     */
    void update(T object);
}
