import java.util.Stack;

/** Calculator.java
 * @author ESCUELA 2021-1
 */
    
public class RelationalCalculator{

    private Stack<Table> tables;
    // Consultar en el API Java la clase Stack
    
    public RelationalCalculator(){
        tables = new Stack<Table>();
    }
    
    /**
     * Add to the stack of tables a table with this attributes
     * @param the attributes of table that we want to add
     */
    public void add(String [] attributes){
        tables.push(new Table(attributes));
    }
    
    /**
     * Add to the stack of tables a table with this attributes and this data(tuples)
     * @param attrributes the attributes of table that we want to add 
     * @param tuples the data of the table that we want to insert in the table
     */
    public void add(String [] attributes, String[][] tuples){
        Table table = new Table(attributes);
        table.insert(tuples);
        tables.push(table);
    }
    
    /**
     * Consult the top of the stack
     * @return the string of the table at the top of the stack
     */
    public String consult(){
        return tables.peek().toString();
    }
    
    public void delete(){
    }
    
 
    /**
     * Insert the tuples in the table at the top of the stack
     * @param tuples the data that we want to insert
     **/
    public void insert(String[][] tuples){ 
        tables.peek().insert(tuples);
    }  
    
    /**
     * Selecciona las filas de los datos que tienen value en el attribute seleccionado
     * @param attribute nombre de la columna en donde queremos buscar los datos
     * @param value valor que se debe encontar en las tuples de el attribute
     * @return un arreglo con las tuplas que cumplen la condicion 
     **/
    public String select(String attribute, String value){
        Table table;
        table = tables.peek().selection(attribute,value);
        return table.toString();
    }
    
    /**
     * Given a attributes, return the data of this
     * @param attributes give the attributes that we want to show
     * @return the table with the attributes and its data 
     */
    public String proyect(String attributes[]){
        Table table;
        table = tables.peek().proyection(attributes);
        return table.toString();
    }
    
    /**
    * set operation: 'u' (union), 'i' (intersection), 'd' (difference)
    * relational operation:  'p' (projection), 's' (selection), 'j' (natural join) , 'r' (rename)
    * To project and rename, the attributes are at the top of the stack.
    + To select, the attributes and values are at the top of the row.  
    * If the operation cannot be done, the stack is not modified.
    */
    public void calculate(char operator){
    }
    
    /*Indicates if the last action was successful*/
    public boolean ok(){
        return false;
    }
    
    public Stack<Table> getTables(){
        return tables;
    }
    
}
    



