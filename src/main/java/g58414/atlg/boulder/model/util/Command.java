package g58414.atlg.boulder.model.util;


/**
 * class that contains the command execute and unexecute.
 */
public interface Command {
    /**
     * executes the command introduced
     */
    void execute();

    /**
     * cancels the command introduced
     */
    void unexecute();
}
