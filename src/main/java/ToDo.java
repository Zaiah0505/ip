import java.util.HashMap;
import java.util.NoSuchElementException;

public class ToDo extends Task {
    public static final String COMMAND_STRING = "todo";

    /**
     * Public constructor for ToDo
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constrcutor for ToDo to initialise the ToDo as done
     * @param description The description of the ToDo.
     * @param isDone True if the ToDo has already been completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory method to create a new ToDo based on the supplied arguments.
     * @param argMap HashMap of the arguments to be supplied.
     * @return A new instance of ToDo.
     * @throws NoSuchElementException
     */
    public static ToDo newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        return new ToDo(desc, argMap.containsKey("done"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Used for saving the arguments of the deadline.
     * @return
     */
    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        return argMap;
    }

    /**
     * Used to indicate the String used to trigger the deadline in the UI.
     * @return
     */
    @Override
    public String commandString() {
        return COMMAND_STRING;
    }
}
