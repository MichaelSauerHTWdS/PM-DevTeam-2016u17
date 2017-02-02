public class MalformedFormularException extends RuntimeException
{
    public MalformedFormularException() {
        super();
    }

    public MalformedFormularException( String msg ) {
        super( msg );
    }

    public MalformedFormularException( Throwable cause ) {
        super( cause );
    }

    public MalformedFormularException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}