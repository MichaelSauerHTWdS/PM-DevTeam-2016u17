public class FileHasExtensionException extends RuntimeException
{
    public FileHasExtensionException() {
        super();
    }

    public FileHasExtensionException( String msg ) {
        super( msg );
    }

    public FileHasExtensionException( Throwable cause ) {
        super( cause );
    }

    public FileHasExtensionException( String msg, Throwable cause ) {
        super( msg, cause );
    }
}