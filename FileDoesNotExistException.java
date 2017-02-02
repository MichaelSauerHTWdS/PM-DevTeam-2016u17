public class FileDoesNotExistException extends RuntimeException
{
    public FileDoesNotExistException() {
        super();
    }

    public FileDoesNotExistException( String msg ) {
        super( msg );
    }

    public FileDoesNotExistException( Throwable cause ) {
        super( cause );
    }

    public FileDoesNotExistException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}