import java.util.Stack;

public class InfixConverter
{
    //constants
    private static final char DELIMITER = '#'; // Delimiter for seperating variable names form each other, if they occur in direct succession
    
    //variables
    private String infix = null;
    private StringBuffer postfix = null;
    private Stack<Character> opStack = null;
        
    public InfixConverter( String formular )
    {
        setInfix( formular );
    }
    
    public static final char getDelimiter()
    {
        return DELIMITER;
    }
    
    public String getInfix()
    {
        return infix;
    }
    
    public void setInfix( String formular )
    {
        if ( formular.trim().equals( "" ) )
        {
            throw new NoFormularGivenException( "There is no formular to parse." );
        }
        
        infix = verifyFormular( formular );
    }
    
    // Verfifies a formular, and returns a trimmed version of it
    public static final String verifyFormular( String formular )
    {
        // Check for misplaced operators and brackets
        formular = formular.replaceAll( "\\s+", "" );
        int len = formular.length();
        int bracketBalance = 0;
        
        for ( int i = 0; i < len; i++ )
        {
            // Check if all operators and brackets are placed correctly are placed correctly
            if ( i == 0  || i == len - 1 )
            {
                // Is there an operator at the beginning or the end of the formular?
                if ( Operator.isOperator( formular.charAt( i ) ) ) 
                {
                    throw new MalformedFormularException( "Operators are not allowed to be placed at the start or the end of a formular." );
                }
                
                // Check if the brackets are placed correctly at the beginning or end of the fomrular
                if ( ( i == 0 && Operator.isRightBracket( formular.charAt( 0 ) ) ) || ( i == len - 1 && Operator.isLeftBracket( formular.charAt( len - 1 ) ) ) )
                {
                    throw new MalformedFormularException( "Brackets at the beginning or the end of the formular are wrong." );
                }
            }
                
            // Check if there are any operators directly followed by other operators, or if there are illegal operations with brackets [ "(OP", "OP)" ]
            if ( Operator.isOperator( formular.charAt( i ) ) && Operator.isOperator( formular.charAt( i + 1 ) ) )
            {
                throw new MalformedFormularException( "There mustn't be more than one operator in a row." );
            }
            else if ( Operator.isLeftBracket( formular.charAt( i ) ) && Operator.isOperator( formular.charAt( i + 1 ) ) )
            {
                throw new MalformedFormularException( "A left parenthesis can't be followed by an operator." );
            }
            else if ( Operator.isOperator( formular.charAt( i ) ) && Operator.isRightBracket( formular.charAt( i +1 ) ) )
            {
                throw new MalformedFormularException( "A right parenthesis can't be preceded by an operator." );
            }
            
            // Check if all brackets are in pairs
            if ( Operator.isLeftBracket( formular.charAt( i ) ) )
            {
                bracketBalance++;
            }
            
            if ( Operator.isRightBracket( formular.charAt( i ) ) )
            {
                bracketBalance--;
            }
            
            if ( bracketBalance < 0 )
            {
                throw new MalformedFormularException( "Not all brackets are opened or closed correctly (More closed than opened)." );
            }
        }

        if ( bracketBalance > 0 )
        {
            throw new MalformedFormularException( "Not all brackets are opened or closed correctly (More opened than closed)." );
        }
        
        return formular;
    }

    // Calulates the postfix expression of an infix formular and returns it
    public String getPostfix()
    {
        //exceptions
        if ( infix.trim().equals( "" ) )
        {
            throw new NoFormularGivenException( "There is no formular to parse." );
        }
        
        //main code
        infix = infix.replaceAll( "\\s+", "" );
        opStack = new Stack<Character>();
        postfix = new StringBuffer( "" );
        boolean lastCharWasLetterOrDigit = false;
        
        for ( int i = 0; i < infix.length(); i++ )
        {
            char c = infix.charAt( i );

            if ( Character.isLetterOrDigit( c ) )
            {
                postfix.append( c );
                lastCharWasLetterOrDigit = true;
            }
            else
            {
                //insert a delimiter, so that variable names can be seperated in postfix
                if ( lastCharWasLetterOrDigit )
                {
                    postfix.append( DELIMITER );
                    lastCharWasLetterOrDigit = false;
                }
            
                if ( Operator.isLeftBracket( c ) )
                {
                    opStack.push( c );
                }
                else if ( Operator.isRightBracket( c ) )
                {
                    while ( !opStack.empty() && opStack.peek() != Operator.LEFT_BRACKET )
                    {
                        postfix.append( opStack.pop() );
                    }
                    
                    if ( !opStack.empty() )
                    {
                        opStack.pop();
                    }
                }
                else
                {
                    while ( !opStack.empty() && Operator.getPriority( c ) <= Operator.getPriority( opStack.peek() ) )
                    {
                        postfix.append( opStack.pop() );
                    }

                    opStack.push( c );
                }
            }
        }
        
        // Add one more delimiter, if the last input was a variable name
        if ( lastCharWasLetterOrDigit )
        {
            postfix.append( DELIMITER );
        }
        
        while ( !opStack.empty() )
        {
            postfix.append( opStack.pop() );
        }
        
        return postfix.toString();
    }
}