package util;

import java.util.HashMap;
import java.util.NoSuchElementException;

public interface Ui {
    static String greeting() {
        String logo = " _____  _    _ _____ _   _ \n" +
                "/  ___|| |  | |  ___| | | | \n" +
                "\\ `--. | |  | | |__ | |_| | \n" +
                " `--. \\| |/\\| |  __||  _  | \n" +
                "/\\__/ /\\  /\\  / |___| | | | \n" +
                "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        return "Hello, I am\n"
                + logo
                + "\nYour Simple Word-Executed Helper!"
                + "\nWhat shall we do today?\n";
    }

    static String help() {
        StringBuilder outString = new StringBuilder();
        outString.append("Type help <command> to see detailed information").append('\n')
                .append("\n")
                .append("Basic commands: ").append("\n")
                .append("list: show all existing tasks").append("\n")
                .append("done <n>: mark the nth task from the list as done").append("\n")
                .append("delete <n>: remove the nth task from the list").append("\n")
                .append("find <key>: list all tasks with description that contains the key").append("\n")
                .append("todo: schedules a new todo task").append("\n")
                .append("deadline: schedule a a new deadline").append("\n")
                .append("event: schedule a new event").append("\n")
                .append("bye: terminate the program");

        return outString.toString();
    }

    static String detailedHelp(HashMap<String, String> argMap) {
        if (!argMap.containsKey("desc")) {
            return help();
        }

        String desc = argMap.get("desc");

        switch (desc) {
            case "list":
                return "list: how all existing tasks";
            case "done":
                return "done <n>: mark the nth task from the list as done";
            case "delete":
                return "done <n>: remove the nth task from the list";
            case "find":
                return "find <key>: list all tasks with descriptions that contains the key";
            case "todo":
                return "todo <desc>: schedules a new todo task";
            case "deadline":
                return "deadline <desc> /by <YYYY-MM-DD>: schedules a new deadline with the specified date";
            case "event":
                return "event <desc> /at <YYYY-MM-DD>: schedules a new event at the specified date";
            case "bye":
                return "bye: closes the program";
            default:
                return "Please specify a command to get help on.";
        }
    }
}
