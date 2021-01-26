import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * A Deadline is a task that has a date for which it ends.
 */
public class Deadline extends Task {
    private LocalDate endDate;
    public static final String COMMAND_STRING = "deadline";

    /**
     * Public constructor for Deadline.
     * @param desc Description of the deadline.
     * @param endDate The date the deadline happens.
     */
    public Deadline(String desc, LocalDate endDate) {
        super(desc);
        this.endDate = endDate;
    }

    /**
     * Overloaded constructor to initialise the task as done, if necessary.
     * @param desc Description of the deadline.
     * @param endDate The date the deadline happens.
     * @param isDone True if the task has already been completed.
     */
    public Deadline(String desc, LocalDate endDate, boolean isDone) {
        super(desc, isDone);
        this.endDate = endDate;
    }

    /**
     * Factory method to generate a new Deadline from a HashMap,
     * @param argMap HashMap of the arguments to be supplied.
     * @return A new instance of the Deadline.
     * @throws NoSuchElementException
     * @throws DateTimeParseException
     */
    public static Deadline newInstance(HashMap<String, String> argMap)
            throws NoSuchElementException, DateTimeParseException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDate eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("by")) {
            eventTime = LocalDate.parse(argMap.get("by"), ISO_LOCAL_DATE);
        }

        return new Deadline(desc, eventTime, isDone);
    }

    /**
     * Returns a string representation for the user. The "by" field is empty if
     * not specified by the user
     * @return String representation for the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                (endDate != null
                        ? "(by: " + endDate.format(DateTimeFormatter.ofPattern("E, d MMM yy")) + ")"
                        : "");
    }

    /**
     * Used for saving the arguments of the deadline.
     * @return
     */
    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        if (endDate != null) {
            argMap.put("by", endDate.toString());
        }
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
