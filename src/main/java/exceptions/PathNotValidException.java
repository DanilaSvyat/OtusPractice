package exceptions;

public class PathNotValidException extends RuntimeException{

    public PathNotValidException() {
        super("Path not set to class");
    }

}
