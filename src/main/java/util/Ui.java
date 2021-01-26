package util;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static util.Parser.getArgMap;
import static util.Parser.getCommand;

public interface Ui {

    /**
     * Returns the intro message whenever Sweh is started up.
     * @return Intro message for Sweh.
     */
    static String greeting() {
        String logo = " _____  _    _ _____ _   _ \n" +
                "/  ___|| |  | |  ___| | | | \n" +
                "\\ `--. | |  | | |__ | |_| | \n" +
                " `--. \\| |/\\| |  __||  _  | \n" +
                "/\\__/ /\\  /\\  / |___| | | | \n" +
                "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        String greeting = "Hello, I am\n"
                + logo
                + "\nYour Simple Word-Executed Helper!"
                + "\nWhat shall we do today?\n";

        return greeting;
    }

    /**
     * Returns the input message back. Used in the Level-1 of the program.
     * @param input The string to be returned.
     * @return The string that was inputted.
     */
    public static String echo(String input) {
        return input;
    }
}
