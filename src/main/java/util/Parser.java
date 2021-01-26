package util;

import java.util.HashMap;
import java.util.InputMismatchException;

public interface Parser {
    /**
     * Returns the first command word in the input. E.g "todo iP" will return "iP"
     * @param input The full input ine from the user.
     * @return The first command word.
     */
    static String getCommand(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Filters out the first command word in the input and converts the rest of the
     * String into HashMap of arguments to be processed by other classes.
     * @param input The full string inputted by the user.
     * @return A HashMap containing the arguments that were supplied by the user.
     * @throws InputMismatchException Occurs when the input was malformed.
     */
    static HashMap<String, String> getArgMap(String input) throws InputMismatchException {
        HashMap<String, String> argMap = new HashMap<>();

        if (input.split(" ").length < 2) {
            return argMap;
        }

        String[] argArr = input.split("/");
        for (int i = 0; i < argArr.length; i++) {
            String flag;
            String param;

            if (i == 0) {
                flag = "desc";
            } else {
                flag = argArr[i].split(" ", 2)[0];
            }

            try {
                param = argArr[i].split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InputMismatchException("Parameter for \"" + flag + "\" cannot be empty");
            }
            argMap.put(flag, param);
        }
        return argMap;
    }

}
