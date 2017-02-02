//Redhead's gonna steal your soul

import java.util.Scanner;
import java.io.*;

public class ETreeDialog
{
    //variables
    private Scanner input = null;
    private ETree tree = null;
    
    public ETreeDialog()
    {
        input = new Scanner( System.in );
    }
    
    public void start()
    {
        System.out.println( "Please enter a filename: " );
        String filename = input.next();
        
        start( filename );
    }
    
    public void start( String filename )
    {
        readFromFile( filename );   
    }
    
    private void readFromFile( String filename )
    {      
        File tempFile = null;
        
        try
        {
            //exceptions
            tempFile = new File( filename );
            
            if ( !tempFile.exists() )
            {
                throw new FileDoesNotExistException( "File '" + filename + "' doesn't exist." );
            }
                  
            if ( tempFile.getName().lastIndexOf( "." ) != -1 && tempFile.getName().lastIndexOf( "." ) != 0 )
            {
                throw new FileHasExtensionException( "File '" + filename + "' has an extension, which is not allowed." );
            }
            
            if ( !tempFile.canRead() )
            {
                throw new FileNotReadableException( "You have no permission to read file '" + filename + "'." );
            }
            
            //main code
            ETreeParser parser = new ETreeParser( filename );
            tree = parser.getTree();
            
            System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
            
            if ( tree != null && tree.isBuild() )
            {
                System.out.println( "Formular = " + tree.getInfixFormular() );
                System.out.println();
                System.out.println( "Key/Value pairs:" );
                
                for ( int i = 0; i < tree.getNumVariables(); i++ )
                {
                    System.out.println( "\t" + tree.getVariables()[i] + " = " + tree.getValues()[i] );
                }
                System.out.println();
                System.out.println( "Result = " + tree.getResult() );
                System.out.println();
                System.out.println( "InOrder of last Tree: " + tree.toString() );
            }
            else
            {
                System.out.println( "Tree wasn't build completely." );
            }
            
            System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        }
        catch( FileDoesNotExistException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( FileHasExtensionException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( FileNotReadableException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( RuntimeException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( Exception e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args )
    {
        if ( args.length == 0 )
        {
            new ETreeDialog().start();
        }
        else
        {
            new ETreeDialog().start( args[0] );
        }
    }
}