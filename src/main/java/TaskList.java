import util.Formatter;
import util.Storage;
import static util.Parser.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Public constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns a message for the number of tasks in the list.
     * @return Count of the tasks in the list.
     */
    private String taskCountMsg() {
        return "\nNow you have " + taskList.size() + " task(s) in your list";
    }

    /**
     * Return the list of Tasks.
     * @return ArrayList of the tasks in the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds the specified task to the ArrayList of tasks.
     * @param task The task to be added.
     * @return String feedback to indicate whether the action was completed.
     */
    public String addTask(Task task) {
        taskList.add(task);

        saveToDisk();

        return "Gotcha. I've added the task: \n    " 
                + task 
                + taskCountMsg();
    }


    /**
     * Marks the indicated task as completed
     * @param position the index of the task to be completed (0-based indexing).
     * @return String feedback to indicate whether the action was completed.
     * @throws NoSuchElementException
     * @throws IndexOutOfBoundsException
     */
    public String markTaskDone(int position) throws NoSuchElementException, IndexOutOfBoundsException {
        try {
            taskList.get(position).markDone();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }
        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    /**
     * Marks the indicated task as completed.
     * @param argMap Hashmap of the arguments to be supplied.
     * @return String feedback to indicate whether the action was completed.
     * @throws NoSuchElementException
     * @throws IndexOutOfBoundsException
     */
    public String markTaskDone(HashMap<String, String> argMap) throws NoSuchElementException,
            IndexOutOfBoundsException {
        int position;
        try {
            position = Integer.parseInt(argMap.get("desc")) - 1;
            taskList.get(position).markDone();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }

        saveToDisk();

        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    /**
     * Deletes the specified task.
     * @param argMap Hashmap of the arguments to be supplied.
     * @return String feedback to indicate whether the action was completed.
     * @throws NoSuchElementException
     * @throws IndexOutOfBoundsException
     */
    public String deleteTask(HashMap<String, String> argMap) throws NoSuchElementException,
            IndexOutOfBoundsException {
        int position;
        Task taskToRemove;
        try {
            position = Integer.parseInt(argMap.get("desc")) - 1;
            taskToRemove = taskList.get(position);
            taskList.remove(position);
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new NoSuchElementException("Error: Please include the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }

        saveToDisk();

        return "I've removed the task:\n" 
            + taskToRemove.toString()
            + taskCountMsg();
    }

    /**
     * Returns a list of all the tasks that are in the list.
     * @return String representation of the tasks that are in the list.
     */
    public String listTasks() {
        return "Here is your list of tasks: \n" + Formatter.formatList(taskList
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList())
        );
    }

    /**
     * Saves all current tasks to the default file.
     * @return Returns true if the action was successful.
     */
    public boolean saveToDisk() {
        StringBuilder saveLines = new StringBuilder();
        for (Task t: taskList) {
            saveLines.append(t.toSaveFormat()).append('\n');
        }

        try {
            Storage.writeSave(saveLines.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Loads up the data from the default file.
     * @return True if the action was successful.
     */
    public boolean readFromDisk() {
        File file;
        Scanner sc;
        try {
            file = Storage.getFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            return false;
        }

        while(sc.hasNextLine()) {
            String saveLine = sc.nextLine();
            String command = getCommand(saveLine);
            HashMap<String, String> argMap = getArgMap(saveLine);
            Task newTask;

            switch (command) {
                case ToDo.COMMAND_STRING:
                    newTask = ToDo.newInstance(argMap);
                    break;
                case Deadline.COMMAND_STRING:
                    newTask = Deadline.newInstance(argMap);
                    break;
                default:
                    newTask = Event.newInstance(argMap);
                    break;
            }

            taskList.add(newTask);
        }

        return true;
    }
}
