package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public interface Storage {
    Path SAVE_PATH = Paths.get("data", "sweh.txt");

    /**
     * Returns the save file if found. Generates a new file and directory if an
     * existing file is not found.
     * @return The save file.
     * @throws IOException
     */
    static File getFile() throws IOException{
        File file = new File(SAVE_PATH.toString());
        (new File(SAVE_PATH.getParent().toString())).mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Overwrites the current save file with the lines that are supplied.
     * @param lines The lines to overwrite the current save file with.
     * @throws IOException
     */
    static void writeSave(String lines) throws IOException {
        FileWriter fw = new FileWriter(getFile());
        fw.write(lines);
        fw.close();
    }

    /**
     * Returns all the text in the saved file if found. Returns an empty string
     * if an existing file is not found.
     * @return All text found in the current save file.
     * @throws IOException
     */
    static String readSave() throws IOException {
        File file = getFile();
        Scanner sc = new Scanner(file);
        StringBuilder output = new StringBuilder();
        while(sc.hasNext()) {
            output.append(sc.nextLine());
        }
        sc.close();
        return output.toString();
    }
}
