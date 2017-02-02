public class Node
{
    public static final int TYPE_NONE = 0;
    public static final int TYPE_OP = 1;
    public static final int TYPE_VAL = 2;
    
    private Node left = null;
    private Node right = null;
    private String data = null;
    private double value = 0;
    private int type = TYPE_NONE;
    
    // For Value-Nodes.
    public Node( String data, double value )
    {
        this( null, null, data, value, TYPE_VAL );
    }
    
    // For Operator-Nodes.
    public Node( Node left, Node right, String data )
    {
        this( left, right, data, 0, TYPE_OP );
    }
    
    // Standard Constructor
    private Node( Node left, Node right, String data, double value, int type )
    {
        setLeft( left );
        setRight( right );
        setData( data );
        setValue( value );
        setType( type );
    }
    
    private void setLeft( Node left )
    {
        this.left = left;
    }
        
    public Node getLeft()
    {
        return left;
    }
    
    private void setRight( Node right )
    {
        this.right = right;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public void setData( String data )
    {
        if ( data.trim().equals( "" ) )
        {
            throw new MalformedNodeException( "String passed is empty." );
        }
        
        this.data = data;
    }  
    
    public String getData()
    {
        return data;
    }
    
    private char getOperator()
    {
        return getData().charAt( 0 );
    }
    
    public void setValue( double value )
    {
        this.value = value;
    }  
    
    public double getValue()
    {
        return value;
    } 
    
    private void setType( int type )
    {
        if ( !( type == TYPE_NONE || type == TYPE_OP || type == TYPE_VAL ) )
        {
            throw new MalformedNodeException( "Invalid Node Type." );
        }
        
        this.type = type;
    }  
    
    public int getType()
    {
        return type;
    }
    
    // Calculates the value of this, respectively all nodes necessary.
    public double evaluate()
    {
        if ( getType() == TYPE_OP )
        {           
            setValue( Operator.compute( getLeft().evaluate(), getOperator(), getRight().evaluate() ) );
        }
        
        //DEBUG System.out.println( this.toString() );
        
        return getValue();
    }
    
    // Visualize the Node itself and all it's child nodes, if existing.
    private String visualize( int depth )
    {
        String tab = "|  ";
        StringBuffer sb = new StringBuffer( "" );
        
        if ( this.getType() == Node.TYPE_VAL )
        {
            for ( int i = 0; i < depth; i++ )
            {
                sb.append( tab );
            }
            
            sb.append( "+--" + this + "\n" );
        }
        else
        {
            sb.append( this.getRight().visualize( depth + 1 ) );
            
            for ( int i = 0; i < depth; i++ )
            {
                sb.append( tab );
            }
            
            sb.append( "+--" + this + "\n" );
            sb.append( this.getLeft().visualize( depth + 1 ) );
        }
        
        return sb.toString();
    }
    
    public String visualize()
    {
        return visualize( 0 );
    }
       
    public void display()
    {
        System.out.print( visualize() );
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer( "" );
        
        if ( getType() == TYPE_NONE  )
        {
            sb.append( "{ }" );
        }
        else if ( getType() == TYPE_OP )
        {
            sb.append( "[" ).append( getOperator() ).append( ":" ).append( getValue() ).append( "]" );
        }
        else if ( getType() == TYPE_VAL )
        {
            sb.append( "{" ).append( getData() ).append( "::" ).append( getValue() ).append( "}" );
        }
        else
        {
            sb.append( "{-Invalid Node-}" );
        }
        
        return sb.toString();
    }
}