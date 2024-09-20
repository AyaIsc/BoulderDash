package g58414.atlg.boulder.model.util;

import g58414.atlg.boulder.model.util.Command;

import java.io.FileNotFoundException;
import java.util.Stack;

/**
 * class with two pills for undos/redos.
 */
public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    /**
     * Adds command to the stack.
     *
     * @param command
     * @throws FileNotFoundException
     */
    public void addCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * cancels the last command introduced in the stack
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop(); //enlève dernier element
            command.unexecute();
            redoStack.push(command);
        }
    }

    /**
     * brings back the last command in the stack
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command); //<- reverse code de undo
        }
    }

    /**
     * clears the stack when the player dies
     */
    public void clearStack() {
        redoStack.clear();
        undoStack.clear();
    }
}
