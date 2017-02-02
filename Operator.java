public class Operator
{
    // Operators
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char POWER = '^';
    public static final char LEFT_BRACKET = '(';
    public static final char RIGHT_BRACKET = ')';
    public static final char LEFT_BRACKET_SQUARE = '[';
    public static final char RIGHT_BRACKET_SQUARE = ']';
    public static final char LEFT_BRACKET_CURLY = '{';
    public static final char RIGHT_BRACKET_CURLY = '}';
    
    // Priorities ( from lowest to highest )
    private static final int PRIORITY_NONE = -1;
    private static final int PRIORITY_BRACKETS = 1;
    private static final int PRIORITY_LINEOP = 2;
    private static final int PRIORITY_POINTOP = 3;
    private static final int PRIORITY_POWER = 4;

    // --Operators
    // -Plus & Minus
    // Plus
    public static char getPlus()
    {
        return PLUS;
    }
    
    public static boolean isPlus( char c )
    {
        return ( c == getPlus() );
    }
    
    public static double computePlus( double leftOperand, double rightOperand )
    {
        return leftOperand + rightOperand;
    }
    
    // Minus
    public static char getMinus()
    {
        return MINUS;
    }
    
    public static boolean isMinus( char c )
    {
        return ( c == getMinus() );
    }
    
    public static double computeMinus( double leftOperand, double rightOperand )
    {
        return leftOperand - rightOperand;
    }
    
    // Line Operator
    public static boolean isLineOperator( char c )
    {
        return isPlus( c ) || isMinus( c );
    }
    
    // -Mul & Div
    // Mul
    public static char getMul()
    {
        return MUL;
    }
    
    public static boolean isMul( char c )
    {
        return ( c == getMul() );
    }
    
    public static double computeMul( double leftOperand, double rightOperand )
    {
        return leftOperand * rightOperand;
    }
    
    // Div
    public static char getDiv()
    {
        return DIV;
    }
    
    public static boolean isDiv( char c )
    {
        return ( c == getDiv() );
    }
    
    public static double computeDiv( double leftOperand, double rightOperand )
    {
        return leftOperand / rightOperand;
    }
    
    // Point Operator
    public static boolean isPointOperator( char c )
    {
        return isMul( c ) || isDiv( c );
    }
    
    // -Power
    public static char getPower()
    {
        return POWER;
    }
    
    public static boolean isPower( char c )
    {
        return ( c == getPower() );
    }
    
    public static double computePower( double leftOperand, double rightOperand )
    {
        return Math.pow( leftOperand, rightOperand );
    }
    
    // -Is Operator
    public static boolean isOperator( char c )
    {
        return (  isPlus( c ) || isMinus( c )
                || isMul( c ) || isDiv( c )
                || isPower( c ) );
    }
    
    // --Brackets
    // -Brackes
    public static char getLeftBracket()
    {
        return LEFT_BRACKET;
    }
    
    public static boolean isLeftBracket( char c )
    {
        return ( c == getLeftBracket() );
    }
    
    public static char getRightBracket()
    {
        return RIGHT_BRACKET;
    }
    
    public static boolean isRightBracket( char c )
    {
        return ( c == getRightBracket() );
    }
    
    // -Square Brackets
    public static char getLeftSquareBracket()
    {
        return LEFT_BRACKET_SQUARE;
    }
    
    public static boolean isLeftSquareBracket( char c )
    {
        return ( c == getLeftSquareBracket() );
    }
    
    public static char getRightSquareBracket()
    {
        return RIGHT_BRACKET_SQUARE;
    }
    
    public static boolean isRightSquareBracket( char c )
    {
        return ( c == getRightSquareBracket() );
    }
    
    // -Curly Brackets
    public static char getLeftCurlyBracket()
    {
        return LEFT_BRACKET_CURLY;
    }
    
    public static boolean isLeftCurlyBracket( char c )
    {
        return ( c == getLeftCurlyBracket() );
    }
    
    public static char getRightCurlyBracket()
    {
        return RIGHT_BRACKET_CURLY;
    }
    
    public static boolean isRightCurlyBracket( char c )
    {
        return ( c == getRightCurlyBracket() );
    }

    // -Is Bracket
    public static boolean isBracket( char c )
    {
        return (   isLeftBracket( c ) || isRightBracket( c )
                || isLeftSquareBracket( c ) || isRightSquareBracket( c )
                || isLeftCurlyBracket( c ) || isRightCurlyBracket( c ) );
    }

    // --Priorities
    public static int getNonePriority()
    {
        return PRIORITY_NONE;
    }
    
    public static int getBracketPriority()
    {
        return PRIORITY_BRACKETS;
    }
    
    public static int getLineOperatorPriority()
    {
        return PRIORITY_LINEOP;
    }
    
    public static int getPointOperatorPriority()
    {
        return PRIORITY_POINTOP;
    }
    
    public static int getPowerPriority()
    {
        return PRIORITY_POWER;
    }
        
    // Computes the corresponding result for an operator and it's two operands
    public static double compute( double leftOperand, char op, double rightOperand )
    {
        // Exceptions
        if ( !isOperator( op ) )
        {
            throw new IllegalArgumentException( "Operator '" + op + "' not declared." );
        }
        
        // Main code
        double result = 0;
        
        if ( isPlus( op ) )
        {
            result = computePlus( leftOperand, rightOperand );
        }
        else if ( isMinus( op ) )
        {
            result = computeMinus( leftOperand, rightOperand );
        }               
        else if ( isMul( op ) )
        {
            result = computeMul( leftOperand, rightOperand );
        }
        else if ( isDiv( op ) )
        {
            result = computeDiv( leftOperand, rightOperand );  
        }
        else if ( isPower( op ) )
        {
            result = computePower( leftOperand, rightOperand );
        }
            
        return result;
    }

    // Gets the Priority of an operator
    public static int getPriority( char op )
    {
        // Exceptions
        if ( !( isOperator( op ) || isBracket( op ) ) )
        {
            throw new IllegalArgumentException( "Character passed is no valid operator." );
        }
        
        // Main code
        int priority = getNonePriority();
        
        if ( isBracket( op ) )
        {
            priority = getBracketPriority();
        }
        else if ( isPlus( op ) || isMinus( op ) )
        {
            priority = getLineOperatorPriority();
        }
        else if ( isMul( op ) || isDiv( op ) )
        {
            priority = getPointOperatorPriority();
        }
        else if ( isPower( op ) )
        {
            priority = getPowerPriority();
        }
        
        return priority;
    }
}