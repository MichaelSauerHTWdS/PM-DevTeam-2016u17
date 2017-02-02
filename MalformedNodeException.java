public class MalformedNodeException extends RuntimeException
{
    public MalformedNodeException() {
        super();
    }

    public MalformedNodeException( String msg ) {
        super( msg );
    }

    public MalformedNodeException( Throwable cause ) {
        super( cause );
    }

    public MalformedNodeException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}