package g58414.atlg.boulder.model.util;


/**
 * Observable that register and observes
 */
public interface Observable {
    /**
     * registers what needs to be observed
     *
     * @param observer
     */
    void register(Observer observer);

    /**
     * unregisters by removing the observer
     * @param observer observer removed
     */
    void unregister(Observer observer);

}
