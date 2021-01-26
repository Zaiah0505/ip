package util;

import java.util.List;

public interface Formatter {

    /**
     * Encloses the feedback of any line given by the program in lines.
     * @param output String to be showed to the user
     * @return String wrapped and formatted for display to the user.
     */
    static String formatOut(String output) {
        String opening = "\"----------------------------------------\n";
        String closing = "----------------------------------------\"\n";
        String combined = opening + output + "\n" + closing;

        String[] strArray = combined.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: strArray) {
            stringBuilder.append("\t").append(s).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Takes in an array of Strings and applies indices to format the output.
     * @param strArray The array of Strings to be formatted
     * @return String with indices for element in the array.
     */
    static String formatList(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = (i + 1) + ". " + strArray[i];
        }
        return String.join("\n", strArray);
    }

    /**
     * Overloaded method to take in a List of Strings and applies indices to format
     * the output.
     * @param strList The List of Strings to be formatted.
     * @return String with indices for each element in the List.
     */
    static String formatList(List<String> strList) {
        return formatList(strList.toArray(new String[] { }));
    }
}
