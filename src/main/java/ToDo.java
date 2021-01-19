import java.util.NoSuchElementException;
import java.util.Optional;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo newInstance(Optional<String> argsOpt) throws NoSuchElementException{
        String args;

        try {
            args = argsOpt.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }
        
        return new ToDo(args);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}