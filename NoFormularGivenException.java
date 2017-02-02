public class NoFormularGivenException extends RuntimeException
{
    public NoFormularGivenException() {
        super();
    }

    public NoFormularGivenException( String msg ) {
        super( msg );
    }

    public NoFormularGivenException( Throwable cause ) {
        super( cause );
    }

    public NoFormularGivenException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}