import util.Formatter;
import util.Ui;

import static util.Parser.*;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class for the Sweh program. Handles initialisation and program state.
 */
public class Sweh {
    public static TaskList taskList = new TaskList();

    /**
     * Main entry point of the program.
     * @param args Usually left blank.
     */
    public static void main(String[] args) {
        System.out.println(Ui.greeting());

        taskList.readFromDisk();
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            System.out.println(Formatter.formatOut(respond(cmd)));
            if (cmd.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Takes in a command and performs the relevant actions, returning a string
     * output to the user.
     * @param commandString The line that was entered by the user.
     * @return
     */
    public static String respond(String commandString) {
        String command = getCommand(commandString);
        HashMap<String, String> argMap = getArgMap(commandString);

        try {
            switch (command) {
                case "list":
                    return taskList.listTasks();
                case "done":
                    return taskList.markTaskDone(argMap);
                case "delete":
                    return taskList.deleteTask(argMap);
                case "todo":
                    return taskList.addTask(ToDo.newInstance(argMap));
                case "deadline":
                    return taskList.addTask(Deadline.newInstance(argMap));
                case "event":
                    return taskList.addTask(Event.newInstance(argMap));
                case "bye":
                    return "Bye. See ya again soon!";
                default:
                    return "Error: Sorry, I didn't understand that";
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please input dates in the form YYYY-MM-DD";
        }
    }

    /**
     * Returns the same input string back. Used for level 1 of the Sweh program.
     * @param input String to be returned.
     * @return
     */
    public static String echo(String input) {
        return input;
    }
}
