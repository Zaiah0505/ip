package command;

import task.TaskManager;
import util.DukeException;

import java.util.HashMap;
import java.util.List;

public class QuitCommand implements Command {
    public static final String COMMAND_STRING = "quit";
    public static final CommandType COMMAND_TYPE = CommandType.DONE;
    private String message = "";

    public static QuitCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        return new QuitCommand();
    }

    @Override
    public void execute(TaskManager taskManager) {

    }

    @Override
    public String getMessage() {
        return message;
    }
}