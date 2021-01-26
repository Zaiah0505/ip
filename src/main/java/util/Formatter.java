package util;

import java.util.List;

public interface Formatter {
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

    static String formatList(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = (i + 1) + ". " + strArray[i];
        }
        return String.join("\n", strArray);
    }

    static String formatList(List<String> strList) {
        return formatList(strList.toArray(new String[] { }));
    }
}
