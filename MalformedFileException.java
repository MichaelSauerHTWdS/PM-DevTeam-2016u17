public class MalformedFileException extends RuntimeException
{
    public MalformedFileException() {
        super();
    }

    public MalformedFileException( String msg ) {
        super( msg );
    }

    public MalformedFileException( Throwable cause ) {
        super( cause );
    }

    public MalformedFileException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}