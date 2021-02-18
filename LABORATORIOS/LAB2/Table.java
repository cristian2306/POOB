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

    public int size(){
        return tuples.size();
    }
    
    public String[] attributes(){
        return attributes;
    }
    
    public String[] tuple(int n){
        return null;
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
        
     

    /*
     * Relational operations: proyection, seleccion, natural join, rename
     */
    
    public Table proyection(String attibutes[]){
        return null;
    }

    public Table selection(String attribute, String value){
        return null;
    }    
 
    public Table naturalJoin(Table t){
        return null;
    }

    
    public Table rename(String [] newAttributes){
        return null;
    }
    
    /*
     * Set operators
     * The two relations involved must be union-compatible—that is, the two relations must have the same set of attributes.
     */
    public Table union(Table t){
        return null;
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
     */
    private boolean equals (Table t) {
        return false;
    }

 
    @Override
    public boolean equals (Object o) {
            return this.equals ((Table) o);
    }
 
    @Override
    public String toString () {
        //Convert to upper the attributes
        for(int i = 0; i< this.attributes.length;i++){
            attributes[i].toUpperCase();
        }
        
        //tuples.toString();
        System.out.println(tuples.toString());
        return tuples.toString();
    }

}
