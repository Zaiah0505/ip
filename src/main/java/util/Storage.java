package util;

import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static util.Parser.*;

public class Storage {
    public static String SAVE_PATH = "data/sweh.txt";
    private final File saveFile;

    public Storage(String first, String... more) throws IOException {
        Path savePath = Path.of(first, more);
        saveFile = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        saveFile.createNewFile();
    }

    public static void writeToFile(String saveString) throws IOException {
        Path savePath = Path.of("data", "sweh.txt");
        File file = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(saveString);
        fw.close();
    }

    public static File getFile() throws IOException {
        Path savePath = Path.of("data", "sweh.txt");
        File file = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        file.createNewFile();
        return file;
    }

    public static TaskManager readTaskManager() throws IOException {
        File file = getFile();
        Scanner sc = new Scanner(file);
        TaskManager taskManager = new TaskManager();

        while (sc.hasNextLine()) {
            String saveLine = sc.nextLine();
            Task newTask = readTask(saveLine);
            taskManager.addTask(newTask);
        }

        return taskManager;
    }

    /**
     * Supporting method for readTaskManager. Converts a saveString into a Task
     * @param saveString SaveString representing the Task saved in disk.
     * @return Task that was represented by the saveString
     */
    private static Task readTask(String saveString) {
        HashMap<String, List<String>> commandMap = parseCommandMap(saveString);
        String command = extractCommandString(commandMap);
        switch (command) {
            case Todo.COMMAND_STRING:
                return Todo.fromSaveString(saveString);
            case Deadline.COMMAND_STRING:
                return Deadline.fromSaveString(saveString);
            case Event.COMMAND_STRING:
                return Event.fromSaveString(saveString);
            default:
                assert false; // Internal save and parse error in Storage
                return null;
        }
    }

    public static void writeTaskManager(TaskManager taskManager) throws IOException {
        String saveString = taskManager.toSaveString();
        writeToFile(saveString);
    }
}
