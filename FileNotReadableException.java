public class FileNotReadableException extends RuntimeException
{
    public FileNotReadableException() {
        super();
    }

    public FileNotReadableException( String msg ) {
        super( msg );
    }

    public FileNotReadableException( Throwable cause ) {
        super( cause );
    }

    public FileNotReadableException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}