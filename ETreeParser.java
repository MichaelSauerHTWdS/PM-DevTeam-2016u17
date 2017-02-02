import java.io.*;
import java.util.*;

public class ETreeParser
{
    LineNumberReader lineNumberReader = null;
    String line = null;
    
    private String[] vars = null;
    private String infixFormular = null;
    private String postfixFormular = null;
    private double[] vals = null;
    
    private List<String> varsList = null;
    private ETree tree = null;
    private InfixConverter formularConverter = null;
    
    public ETreeParser( String filename )
    {
        try
        {
            varsList = new ArrayList();            
            lineNumberReader = new LineNumberReader( new FileReader( filename ) );
            
            parseFile();
        }
        catch( MalformedFormularException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( NoFormularGivenException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( MalformedFileException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
        catch( IOException e )
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
        finally
        {
            //Close the LineNumberReader
            try
            {
                if ( lineNumberReader != null )
                {
                    lineNumberReader.close();
                }
            }
            catch ( IOException e )
            {
                System.out.println( e );
                e.printStackTrace();
            }
        }
    }
    
    public ETree getTree()
    {
        return tree;
    }
    
    private void setVariables( String[] arr )
    {
        if ( arr == null )
        {
            throw new IllegalArgumentException( "Array must not be empty." );
        }
        
        vars = arr;
    }
    
    public String[] getVariables()
    {
        return vars;
    }
    
    public int getNumVariables()
    {
        return vars.length;
    }
        
    private void setInfixFormular( String str )
    {
        if ( str == null )
        {
            throw new IllegalArgumentException( "String must not be empty." );
        }
        
        infixFormular = str;
    }
    
    public String getInfixFormular()
    {
        return infixFormular;
    }
    
    private void setPostfixFormular( String str )
    {
        if ( str == null )
        {
            throw new IllegalArgumentException( "String must not be empty." );
        }
        
        postfixFormular = str;
    }
    
    public String getPostfixFormular()
    {
        return postfixFormular;
    }
    
    private void setValues( double[] arr )
    {
        if ( arr == null )
        {
            throw new IllegalArgumentException( "Array must not be empty." );
        }
        
        vals = arr;
    }
    
    public double[] getValues()
    {
        return vals;
    }

    private boolean isNumeric( String s )
    {
        try
        { 
            Double.parseDouble( s ); 
        }
        catch ( NumberFormatException e )
        { 
            return false; 
        }
        catch( NullPointerException e )
        {
            return false;
        }

        return true;
    }
    
    // Verfies whether the declarations are valid, and writes them to varsList
    // Returns an array of strings with all declarations ( in order of appearance )
    private String[] verifyDeclarations() throws IOException
    {
        // Checking declaration block      
        while ( ( line = lineNumberReader.readLine() ) != null )
        {
            StringBuffer sb = new StringBuffer( line );
            //DEBUG System.out.println( "-- DECS -- Line input" + " (" + lineNumberReader.getLineNumber() + "): " + sb.toString() );
            
            if ( lineNumberReader.getLineNumber() == 1 && line.replaceAll( "\\s+", "" ).equals( "" ) )
            {
                throw new MalformedFileException( "File must start with a declaration." );
            }
            
            if ( !line.replaceAll( "\\s+", "" ).equals( "" ) )
            {
                for ( int i = 0; i < line.length(); i++ )
                {
                    if ( !Character.isLetterOrDigit( line.charAt( i ) ) )
                    {
                        throw new MalformedFileException( "Declaration must contain only letters or digits." );
                    }
                }
                
                varsList.add( line.replaceAll( "\\s+", "" ) );
            }
            else
            {
                break;
            }                
        }
        
        return varsList.toArray( new String[varsList.size()] );
        // Declaration Block okay
    }
    
    // Verifies whether the formular is valid
    // Returns the postfix'd formular as String
    private String verifyFormular() throws IOException
    {
        // Checking formular
        line = lineNumberReader.readLine();
        StringBuffer sb = new StringBuffer( line );
        //DEBUG System.out.println( "-- FORMULAR -- Line input" + " (" + lineNumberReader.getLineNumber() + "): " + sb.toString() );
        
        if ( line.replaceAll( "\\s+", "" ).equals( "" ) )
        {
            throw new MalformedFileException( "Declarations and Formular must be seperated with one line only." );
        }

        formularConverter = new InfixConverter( line );
        setInfixFormular( formularConverter.getInfix() );
        
        return formularConverter.getPostfix();
        // Formular okay
    }
    
    // Verfies whether the definitions are valid
    // Returns an array of doubles with all definitions ( in the same order as they belong to the declarations )
    private double[] verifyDefinitions() throws IOException
    {       
        // Checking defintion block  
        boolean blockProcessed = false;
        double[] defs = new double[getNumVariables()];
        int numDefs = 0;
        
        while ( ( line = lineNumberReader.readLine() ) != null )
        {
            StringBuffer sb = new StringBuffer( line );
            //DEBUG System.out.println( "-- DEFS -- Line input" + " (" + lineNumberReader.getLineNumber() + "): " + sb.toString() );
            
            if ( line.replaceAll( "\\s+", "" ).equals( "" ) )
            {
                if ( !blockProcessed )
                {
                    throw new MalformedFileException( "Definition-Blocks must be seperated with one line each." );
                }
                else
                {
                    break;
                }
            }
            else
            {
                blockProcessed = true;
            }
            
            line = line.replaceAll( "\\s+", "" );
            int len = line.length();
            int signIndex = line.lastIndexOf( "=" );
            String identifier;
            
            if ( signIndex > 0 )
            {
                identifier = line.substring( 0, signIndex );
                //DEBUG System.out.println( "________ " + identifier );
            }
            else
            {
                throw new MalformedFileException( "Definition is wrongly noted, '=' is missing." );
            }
            
            if ( varsList.contains( identifier.toString() ) )
            {
                String num = line.substring( signIndex + 1 );
                
                if ( isNumeric( num ) )
                {
                    //DEBUG System.out.println( "________ " + num );
                    defs[varsList.indexOf( identifier )] = Double.parseDouble( num );
                    numDefs++;
                }
                else
                {
                    throw new MalformedFileException( "Definition is wrongly noted. After the '=' sign should be a number." );
                }
            }
            else
            {
                throw new MalformedFileException( "Definition is wrongly noted. Identifier is not declared." );
            }
        }
        
        if ( numDefs < getNumVariables() )
        {
            throw new MalformedFileException( "Definition-Block is incomplete. All variables need to be defined." );
        }
        
        return defs;
        // Defintion block okay 
    }
    
    // Parses the whole file given, and verfies everything
    private void parseFile() throws IOException
    {      
        setVariables( verifyDeclarations() );
        //DEBUG System.out.println( "--DECS okay." );
        setPostfixFormular( verifyFormular() );
        //DEBUG System.out.println( "--FORMULAR okay." );
        
        if ( !lineNumberReader.readLine().replaceAll( "\\s+", "" ).equals( "" ) )
        {
            throw new MalformedFileException( "Formular and definitions must be seperated with one line." );
        }
        
        do
        {        
            if ( tree != null )
            {
                setValues( verifyDefinitions() );
                //DEBUG System.out.println( "--DEFS okay." );
                
                tree.buildTree( getValues() );
                //DEBUG System.out.println( "_________ " + tree.getResult() );
            }
            else
            {
                setValues( new double[getNumVariables()] );
                tree = new ETree( getVariables(), getValues(), getPostfixFormular(), getInfixFormular() );
            }
        } while ( line != null  );
        
        //DEBUG System.out.println( "--DECS & DEFS" );
        /*DEBUG
        for ( int i = 0; i < getNumVariables(); i++ )
        {
            System.out.println( vars[i] );
            System.out.println( vals[i] );
        }
        */
    }
}