import java.util.ArrayList;
import java.util.Stack;
 /**
 * Initial Project POOB 2021-1
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (1.0  12/2/2021)
 */
public class Mission
{
   
   //Dimensions
   private int row;
   private int column;

   //Starts the Stock and Robbery classes
   private Stock warehouse;
   private Robbery robbery;
   
   //Extra
   private int heights[][];
   
   //Actions
   private Stack<ArrayList<String>> before = new Stack<ArrayList<String>>();
   private Stack<ArrayList<String>> after = new Stack<ArrayList<String>>();
   

   public Mission(int row, int column){
       //define the dimensions of the Mission
       this.row = row;
       this.column = column;

       //Create the warehouse class and robbery class
       warehouse = new Stock(row,column);
       int maxStock = warehouse.getMax();
       robbery = new Robbery(row,column,maxStock);
   }
   
   public Mission(int row, int column, int[][] heights){
       //define the dimensions of the Mission
       this.row = row;
       this.column = column;

       //Create the warehouse class and robbery class
       warehouse = new Stock(row,column);
       int maxStock = warehouse.getMax();
       robbery = new Robbery(row,column,maxStock);

       //Define the height
       this.heights = heights;
       create(heights);
   }

   /**
     * Function created to create the matrix of values ​​and the views of the stock
     * according to the parameter (Matrix of numbers)
     * @param crates
     */
    public void create(int[][] crates){
        warehouse.createMatrix(crates);
        refresh(error());
    }

    /**
     * function created to add a box
     * in a given row and column
     * @param row --> the number of the row
     * @param column --> the number of the column
     */
    public void store(int row, int column){
        warehouse.store(row,column);
        heights = warehouse.getValues();
        refresh(error());
        beforeAction("Store",toString(row,column),null);
       
    }

     /**
     * function created to place a box in 
     * the position that the user says 
     * (the coordinates of where to leave the box are in the array)
     * 
     * @param crate --> The coordinates in the array
     */
    public void store(int[] crate){
        store(crate[0],crate[1]);
     
    }

    /**
     * Function created to copy the views and 
     * the matrix of values ​​of the warehouse to the robbery plan
     */
    public void copy(){
        robbery.copy(warehouse.getValues(),warehouse.getMax());
        refresh(error());
        beforeAction("Copy",null,null);
        
    }

    /**
     * Function created to steal a box in the position 
     * (row, column) that the user gives us
     * @param row
     * @param column
     */
    public void steal(int row, int column){
        robbery.steal(row, column);
        refresh(error());
        beforeAction("Steal",toString(row,column),null);
 
    }

    /**
     * Function created to steal a box in the position of the user. 
     * The positions are in an array [2] of integers
     * @param crate
     */
    public void steal(int[] crate){
        robbery.steal(crate[0],crate[1]);
      
    }

    /**
     * Function created to return the last stolen box
     */
    public void Return(){
        int[] crate = robbery.Return();
        refresh(error());
        beforeAction("Return",toString(crate[0],crate[1]),null);
      
    }

    /**
     * Function created to reorganize a box from a certain position, 
     * to the position that the user wants 
     * @param from
     * @param to
     */
    public void arrange(int[] from, int[] to){
        robbery.arrange(from, to);
        refresh(error());
        beforeAction("Arrange",toString(from[0],from[1]),toString(to[0],to[1]));
        
    }

    /**
     * Function created to return the number of boxes in the robbery plan
     * @return int answer
     */
    public int stolen(){
        return robbery.stolen();
    }

    /**
     * Function created to return the matrix of values ​​of the warehouse
     * @return int[][]
     */
    public int[][] warehouse(){
        return warehouse.getValues();
    }

    /**
     * Function created to return the matrix of values ​​of the robbery
     * @return int[][]
     */
    public int[][] layout(){
        return robbery.getValues();
    }
    
    /**
     * Function created to refresh the values ​​of the arraylists 
     * with respect to the values ​​of the values matrix, 
     * it serves for cases in which we add boxes
     */
    private void refresh(String color){
        makeInvisible();
        warehouse.refresh(heights);
        robbery.refresh(color);
        makeVisible();
    }
    
    /**
     * Function created to make the simulator visible
     */
    public void makeVisible(){
        warehouse.makeVisible();
        robbery.makeVisible();
    }
    
    /**
     * Function created to make the simulator invisible
     */
    public void makeInvisible(){
        robbery.makeInvisible();
        warehouse.makeInvisible();
    }
    
    /**
     * Add the specified strings to the action history
     * @action Action performed by the user
     * @param ubicacion - Coordinates of the stolen, returned or exchanged box
     * @param destino - Coordinates to which the box was sent;
     */
    private void beforeAction(String action, String[] ubicacion, String[] destino){
        ArrayList<String> move = new ArrayList<String>();
        move.add(action);
        if(ubicacion!=null){
        move.add(ubicacion[0]); 
        move.add(ubicacion[1]);
        }
        if(destino!=null){
        move.add(destino[0]);
        move.add(destino[1]);
        }
        before.push(move);
    }
    
    /**
     * It takes two numbers and converts them into a string, to return an array with these
     * @param i - First number
     * @param j - Second number
     * @return array - Arrays with both numbers as Strings
     */
    private String[] toString(int i, int j){
        String[] array = new String[2];
        array[0] = String.valueOf(i);
        array[1] = String.valueOf(j);
        return array;
    }
    
     /**
     * Enlarge or shrink the image 10% of the current size
     * @param z - '+' if you want to enlarge and '-' if you want to shrink. 
     */
    public void zoom(char z){
        Rectangle crate  = new Rectangle();
        int size = crate.getSize();
        if(z=='+'){
            crate.changeSize((int)(size*1.1),(int)(size*1.1));
        }
        else if(z=='-'){
            crate.changeSize((int)(size*0.9),(int)(size*0.9));
        }
        refresh(error());
    }
    
    /**
     * Undo the action made by the player
     */
    public void undo(){
        ArrayList<String> move = before.pop();
        String action = move.get(0);
        switch(action){
            case "Store":
                    heights[Integer.parseInt(move.get(1))-1][Integer.parseInt(move.get(2))-1] -= 1;
                    create(heights);
                break;
            case "Steal":
                    Return();
                    before.pop();
                break;
            case "Return":
                    steal(Integer.parseInt(move.get(1)),Integer.parseInt(move.get(2)));
                    before.pop();
                break;
            case "Copy":
                    int[][] array = new int[row][column];
                    robbery.copy(array,0);
                    robbery.refresh("green");
                break;
            case "Arrange":
                    int [] from = new int[2];
                    int[] to = new int[2];
                    from[0] = Integer.parseInt(move.get(3));
                    from[1] = Integer.parseInt(move.get(4));
                    to[0] = Integer.parseInt(move.get(1));
                    to[1] = Integer.parseInt(move.get(2));
                    arrange(from,to);
                    before.pop();
        }
        after.push(move);
    }
    
    /**
    * Redo the action undone by the player
    */
    public void redo(){
        ArrayList<String> move = after.pop();
        String action = move.get(0);
        switch(action){
            case "Store":
                    store(Integer.parseInt(move.get(1)),Integer.parseInt(move.get(2)));
                break;
            case "Steal":
                    steal(Integer.parseInt(move.get(1)),Integer.parseInt(move.get(2)));
                break;
            case "Return":
                    Return();   
                break;
            case "Copy":
                    copy();
                break;
            case "Arrange":
                    int [] from = new int[2];
                    int[] to = new int[2];
                    from[0] = Integer.parseInt(move.get(1));
                    from[1] = Integer.parseInt(move.get(2));
                    to[0] = Integer.parseInt(move.get(3));
                    to[1] = Integer.parseInt(move.get(4));
                    arrange(from,to);
                break;
        }
    }
    
    /**
     * The function is in charge of changing the color of the robbery zone if it differs with a camera in the warehouse.
     * @return - green if cameras match, red otherwise.
     */
    private String error(){
        boolean Up = warehouse.getMaxUp().equals(robbery.getMaxUp());
        boolean Side = warehouse.getMaxSide().equals(robbery.getMaxSide());
        boolean Front = warehouse.getMaxFront().equals(robbery.getMaxFront());
        if(Up && Side && Front){
            return "green";
        }
        else{
            return "red";    
        }
        
    }
 }      
     