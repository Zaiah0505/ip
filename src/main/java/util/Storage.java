package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public interface Storage {
    Path SAVE_PATH = Paths.get("data", "sweh.txt");

    static File getFile() throws IOException{
        File file = new File(SAVE_PATH.toString());
        (new File(SAVE_PATH.getParent().toString())).mkdir();
        file.createNewFile();
        return file;
    }

    static void writeSave(String lines) throws IOException {
        FileWriter fw = new FileWriter(getFile());
        fw.write(lines);
        fw.close();
    }

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
