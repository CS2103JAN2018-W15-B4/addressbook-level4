package seedu.club.logic;

import java.util.Stack;

import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.LogOutCommand;
import seedu.club.logic.commands.RedoCommand;
import seedu.club.logic.commands.UndoCommand;
import seedu.club.logic.commands.UndoableCommand;

/**
 * Maintains the undo-stack (the stack of commands that can be undone) and the redo-stack (the stack of
 * commands that can be undone).
 */
public class UndoRedoStack {
    private Stack<UndoableCommand> undoStack;
    private Stack<UndoableCommand> redoStack;

    public UndoRedoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public void push(Command command) {
        if (!(command instanceof UndoCommand) && !(command instanceof RedoCommand)) {
            redoStack.clear();
        }

        if (!(command instanceof UndoableCommand)) {
            if (command instanceof LogOutCommand) {
                clear();
            }
            return;
        }

        undoStack.add((UndoableCommand) command);
    }

    //@@author th14thmusician
    /**
     * Clear the stacks if it logouts
     */
    private void clear() {
        redoStack.clear();
        undoStack.clear();
    }
    //@@author

    /**
     * Pops and returns the next {@code UndoableCommand} to be undone in the stack.
     */
    public UndoableCommand popUndo() {
        UndoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be redone in the stack.
     */
    public UndoableCommand popRedo() {
        UndoableCommand toRedo = redoStack.pop();
        undoStack.push(toRedo);
        return toRedo;
    }

    /**
     * Returns true if there are more commands that can be undone.
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    /**
     * Returns true if there are more commands that can be redone.
     */
    public boolean canRedo() {
        return !redoStack.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoRedoStack)) {
            return false;
        }

        UndoRedoStack stack = (UndoRedoStack) other;

        // state check
        return undoStack.equals(stack.undoStack)
                && redoStack.equals(stack.redoStack);
    }
}
