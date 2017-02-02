import java.util.Stack;

public class ETree
{
    private Node root = null;
    private String[] vars = null;
    private String postfixFormular = null;
    private String infixFormular = null;
    private double[] vals = null;
    private char delimiter = 0;
    private int blocksComputed = 0;
    private double result = 0;
    private boolean build = false;
   
    public ETree( String[] vars, double[] vals, String postfixFormular, String infixFormular )
    {
        setVariables( vars );
        setValues( vals );
        setPostfixFormular( postfixFormular );
        setInfixFormular( infixFormular );
        delimiter = InfixConverter.getDelimiter();
        
        System.out.println( "--------------------------------" );
        System.out.println( "-> Expression = " + getInfixFormular() );
        System.out.println( "--------------------------------" );
        System.out.println( "" );
        
        buildTree();
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
    
    public int numBlocksComputed()
    {
        return blocksComputed;
    }    
    
    public void calcResult()
    {
        setResult( root.evaluate() );
    }
    
    private void setResult( double result )
    {
        this.result = result;
    }
    
    public double getResult()
    {
        return result;
    }
    
    private void setBuild()
    {
        build = numBlocksComputed() > 0;
    }
    
    public boolean isBuild()
    {
        return build;
    }
    
    public void buildTree( double[] vals )
    {
        setValues( vals );
        buildTree();
    }
    
    public void buildTree()
    {
        Stack<Node> nodes = new Stack<Node>();
        String formular = getPostfixFormular();
        
        for ( int i = 0; i < formular.length(); i++ )
        {
            char c = formular.charAt( i );
            //DEBUG System.out.println( "____char: " + c );
            
            if ( Operator.isOperator( c ) )
            {
               Node rightNode = nodes.pop();
               Node leftNode = nodes.pop();
               //DEBUG System.out.println( "____push OP-Node: " + Character.toString( c ) );
               nodes.push( new Node( leftNode, rightNode, Character.toString( c ) ) );
            }
            else
            {
                int delimiterIndex = formular.indexOf( delimiter, i );
                String identifier = formular.substring( i, delimiterIndex );
                //DEBUG System.out.println( "____identifier: " + identifier );
                int pos = -1;
                
                for ( int j = 0; j < getNumVariables(); j++ )
                {
                    //DEBUG System.out.println( "____array search[" + j + "]: " + vars[j] );
                    if ( vars[j].equals( identifier ) )
                    {
                        pos = j;
                        //DEBUG System.out.println( "____found identifier in array: " + j );
                        break;
                    }
                }
                
                if ( pos != -1 )
                {
                    nodes.add( new Node( identifier, vals[pos] ) );
                }
                else
                {
                    throw new MalformedFormularException( "Formular given does not match declaration: " + identifier );
                }
                
                i += ( delimiterIndex - i );              
            }
        }
        
        root = nodes.pop();
        calcResult();
        
        display();
        
        setBuild();
        blocksComputed++;
    }
    
    public String visualize()
    {
        StringBuffer sb = new StringBuffer( "" );
            sb.append( "-------------------------------- [Block " + numBlocksComputed() + "] \n" );
            sb.append( root.visualize() );
            sb.append( "| \n" );
            sb.append( "└> Result = " + getResult() + "\n" );
            sb.append( "-------------------------------- [Block " + numBlocksComputed() + "] \n" );
       
        return sb.toString();
    }
    
    public void display()
    {
        System.out.println( visualize() );
    }
    
    private String inOrder( Node curNode )
    {
        StringBuffer sb = new StringBuffer( "" );
        
        if ( curNode != null )
        {
            sb.append( inOrder( curNode.getLeft() ) );
            sb.append( curNode.toString() + " " );
            sb.append( inOrder( curNode.getRight() ) );
        }
        
        return sb.toString();
    }
    
    private String inOrder()
    {
        return inOrder( root );
    }
   
    public String toString()
    {       
        return inOrder();
    }
}