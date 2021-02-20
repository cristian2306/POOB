import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author ECI, 2021-1
 *
 */
public class Table{


    private String[] attributes;
    private ArrayList<String[]> tuples;
    /*
     * The tables must remain 
     * (i) with the attribute names in uppercase 
     * (ii) without repeating tuples*/

    /**
     * Constructs a new, empty table, with the specified attribute names.
     * @param names, 
    **/
    public Table(String attributes[]){
        this.tuples = new ArrayList<String[]>();
        this.attributes = new String[attributes.length];
        this.attributes = attributes;
    }    
    
    
    /**
     * Inserts the specified tuples to this table 
     * @param tuples, 
    **/
    public void insert(String tuples[][]) {
        for(int i = 0; i< tuples.length; i++){
            this.tuples.add(tuples[i]);
        }
    }
    
    /**
     * @return size of the table, number of rows in the table
     */
    public int size(){
        return tuples.size();
    }
    
    /**
     * @return attributes of the table
     */
    public String[] attributes(){
        return attributes;
    }
    
    /**
     * @param n index in the table that we want to look the data
     * @return tuples in the table in index n
     */
    public String[] tuple(int n){
        return  tuples.get(n);
    }
    
    public boolean in(String tuple[]){
        boolean safe = false;
        for(int i = 0; i< this.tuples.size();i++){
            if(tuples.get(i).equals(tuple)){
                safe= true;
            }
        }
        return safe;
        }
        
    /**
     * Relational operations: proyection, seleccion, natural join, rename
     **/
    
    /**
     * Given a attributes, return the data of this
     * @param attributes give the attributes that we want to show
     * @return the table with the attributes and its data 
     */
    public Table proyection(String attributes[]){
        Table table;
        String[][] tuples = new String[this.tuples.size()][attributes.length];
        int[] index = index(attributes);
        for(int i=0;i<this.tuples.size();i++){
            for(int j=0;j<attributes.length;j++){
                tuples[i][j] = this.tuples.get(i)[index[j]]; 
            }
        }
        table = new Table(attributes);
        table.insert(tuples);
        return table;
    }
    
    /**
     * Calculate the position of the attributes in the table
     * @attributes array wich we want to obtain the position in teh attributes in the table
     * @return a array of the index of the attributes
    */
    private int[] index(String[] attributes){
        int[] index = new int[attributes.length];
        for(int j=0;j<attributes.length;j++){
            for(int i=0;i<this.attributes.length;i++){
                if(attributes[j].equals(this.attributes[i])){
                    index[j] = i;
                }
            }
        }
        return index;
    }
    
    /**
     * Selecciona las filas de los datos que tienen value en el attribute seleccionado
     * @param attribute nombre de la columna en donde queremos buscar los datos
     * @param value valor que se debe encontar en las tuples de el attribute
     * @return un arreglo con las tuplas que cumplen la condicion 
     **/
    public Table selection(String attribute, String value){
        Table table;
        int[] index;
        String[] attributes = {attribute};
        index = index(attributes);
        table = new Table(attributes);
        String[][] tuple = new String[1][this.tuples.get(0).length];
        for(int i=0;i<this.tuples.size();i++){
            if(tuples.get(i)[index[0]]==value){
                tuple[0] = tuples.get(i);
                table.insert(tuple);
            }
        }
        return table;
    }    
 
    public Table naturalJoin(Table t){
        return null;
    }

    
    public Table rename(String [] newAttributes){
        return null;
    }
    
    /**
     * Set operators
     * The two relations involved must be union-compatible—that is, the two relations must have the same set of attributes.
     **/
    public Table union(Table t){
        boolean[] safe = new boolean[this.attributes.length];
        boolean flag;
        int[] index;
        String[][] tuples = new String[this.tuples.size()][attributes.length];
        String[][] tuple = new String[1][attributes.length];
        Table table = new Table(this.attributes);
        if(this.attributes.length != t.attributes.length){
           return null;
        }
        for(int i=0;i<this.attributes.length;i++){
            safe[i] = false;
            for(int j=0;j<this.attributes.length;j++){
                safe[i] = safe[i] || (this.attributes[i]==t.attributes[j]);
            }
        }
        flag = true;
        for(int i=0;i<safe.length;i++){
            flag = flag && safe[i];
        }
        if (flag==false){
            return null;
        }
        index = index(t.attributes);
        for(int i=0;i<t.tuples.size();i++){
            for(int j=0;j<t.attributes.length;j++){
                tuples[i][j] = this.tuples.get(i)[index[j]]; 
            }
        }
        table.insert(tuples);
        for(int i=0;i<this.tuples.size();i++){
            tuples[0]=this.tuples.get(i);
            table.insert(tuples);
        }
        return table;
        
    }

    public Table intersection(Table t){
       return null;
    }  
    
    public Table difference(Table t){
       return null;        
    }
    

    /**
     * Indicates whether some other table is "equal to" this one.
     * @param t the table with which to compare.
     **/
    private boolean equals (Table t) {
        return false;
    }

 
    @Override
    public boolean equals (Object o) {
            return this.equals ((Table) o);
    }
 
    @Override
    /**
     * Function created to put all attribute values ​​of tables in a string, as well as tuples
     * @return Concatenation of the attributes values and the tuples 
     **/
    public String toString () {
        String s = "";
        s = s + "[";
        for(int i=0;i<attributes.length;i++){
            if(i<attributes.length-1){
                s = s + attributes[i].toString().toUpperCase() + ",";}
            else{
                s = s + attributes[i].toString().toUpperCase();}
        }
        s = s + ")\n";
        for(int i=0;i<tuples.size();i++){
            s = s + "(";
            for(int j=0;j<tuples.get(i).length;j++){
                if(j<tuples.get(i).length-1){
                    s = s + tuples.get(i)[j].toString() + ",";}
                else{
                    s = s + tuples.get(i)[j].toString();}
            }
            s = s + ")\n";
        }
        return s;
    }

}
