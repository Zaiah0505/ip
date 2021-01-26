import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Event extends Task {
    private LocalDate eventTime;
    public static final String COMMAND_STRING = "event";

    /**
     * Public constructor for Event.
     * @param desc The description of the event.
     * @param eventTime The time which the event occurs.
     */
    public Event(String desc, LocalDate eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    /**
     * Overloaded constructor to initialise the Event as done.
     * @param desc The description of the event.
     * @param eventTime The time which the event occurs.
     * @param isDone True if the event has been completed.
     */
    public Event(String desc, LocalDate eventTime, boolean isDone) {
        super(desc, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Factory method to create a new Event based on the supplied arguments.
     * @param argMap HashMap of the arguments to be supplied.
     * @return A new instance of Event.
     * @throws NoSuchElementException
     * @throws DateTimeParseException
     */
    public static Event newInstance(HashMap<String, String> argMap) throws NoSuchElementException, DateTimeParseException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDate eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("at")) {
            eventTime = LocalDate.parse(argMap.get("at"), ISO_LOCAL_DATE);
        }

        return new Event(desc, eventTime, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                (eventTime != null
                        ? "(at: " + eventTime.format(DateTimeFormatter.ofPattern("E, d MMM yy")) + ")"
                        : "");
    }

    /**
     * Used for saving the arguments of the deadline.
     * @return
     */
    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        if (eventTime != null) {
            argMap.put("at", eventTime.toString());
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
