import java.util.HashMap;

/**
 * A Task represents a single item in the user's list.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    protected static final char saveDelimiter = '|';

    /**
     * Constructs a task object
     * @param desc The description of the task.
     */
    protected Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to initialise the task as done, if needed.
     * @param desc The description of the task.
     * @param isDone True if the task has already been completed.
     */
    protected Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    /**
     * Sets the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of whether the task has been completed.
     * @return String representation of whether the task has been completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return blank or tick symbol
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task into a String representation so it can be saved and
     * later retrieved from the disk.
     * @return String representation of the task to be saved.
     */
    public String toSaveFormat() {
        StringBuilder savedString = new StringBuilder();
        savedString.append(commandString());
        savedString.append(" ");
        savedString.append(description);
        savedString.append((isDone ? "/done " : ""));

        saveArgs().forEach((k,v) -> {
            savedString.append("/").append(k).append(" ").append(v);
        });

        return savedString.toString();
    }

    /**
     * Subclasses should return a HashMap of the arguments they want to save,
     * expressed as key-value pairs
     * @return HashMap containing arguments to be saved.
     */
    protected abstract HashMap<String, String> saveArgs();

    /**
     * The command that is used to trigger the task in the UI.
     * @return String command taht is used to trigger the task in the UI.
     */
    public abstract String commandString();
}
