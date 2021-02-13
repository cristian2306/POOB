import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JOptionPane;
 /**
 * Initial Project POOB 2021-1
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (1.0  12/2/2021)
 */
 public class Mission
 {
    // matrix to store the values in the rows and columns in the stock 
    private int[][] stockValues;
    
    //matrix to store the values in the rows and columns in the robbery
    private int[][] robberyValues;
    
    //Dimensions
    private int length;
    private int width;
    
    //Extras
    private int boxStole = 0;
    ArrayList<int[]> Historial = new ArrayList<int[]>();
   
    //matrix to store the boxes in the stock
    private ArrayList<Rectangle[]> stockSide;
    private ArrayList<Rectangle[]>  stockFront;
    private Rectangle[][] stockUp;
    
    //matrix to store the boxes in the robbery
    private ArrayList<Rectangle[]> robberySide;
    private ArrayList<Rectangle[]> robberyFront;
    private Rectangle[][] robberyUp;
    
    //Auxiliar
    private int height;
   
    
     /**
     * Constructor for objects of class Store
     *
     * @param length --> the length of the array
     * @param width  --> the width of the array
     *
     */
    public Mission(int length, int width){
       this.length = length;
       this.width = width;
       height = 0;
       
       //creation of arrays[int] of stock
       stockValues = new int[length][width];
       
       //creation of arrays[int] of robbery
       robberyValues = new int[length][width];
       
       //creation of arrays of stock
       stockSide = new ArrayList<Rectangle[]>() ;
       stockFront = new ArrayList<Rectangle[]>();
       stockUp = new Rectangle[length][width];
       
       //creation of arrays of robbery
       robberyUp = new Rectangle[length][width];
       robberySide = new ArrayList<Rectangle[]>() ;
       robberyFront = new ArrayList<Rectangle[]>() ;
       
       fillArrays();
     }
    
    
    /***
     * function created to fill the matrices with rectangles, 
     * this to now make each of the rectangles visible
     */
    private void fillArrays(){
        for(int i = 0; i< length;i++){
            for(int j = 0; j< width; j++){
             
                // //Fill the stock arrays
                stockUp[i][j] = new Rectangle();
                stockUp[i][j].changeColor("green");
                stockUp[i][j].moveHorizontal((width+1)*20+10*j);
                stockUp[i][j].moveVertical(10*i);
                
                //Fill the robbery arrays
                robberyUp[i][j] = new Rectangle();
                robberyUp[i][j].changeColor("blue");
                robberyUp[i][j].moveHorizontal((width+1)*20+10*j);
                robberyUp[i][j].moveVertical((length+5)*10+10*i);
            }
        }
    }
    
    
    /***
     * function created to fill the matrix
     * of boxes from the user values
     *
     * @param numbers[]  --> array of numbers, contains the row and the column
     *
     */
    public void store(int numbers[]){
        int newLength = numbers[0];
        int newWidth = numbers[1];
        store(length,width);       
    }
        
   
    /***
     * function created to add a box
     * in a given row and column
     *
     * @param row  --> the number of the row
     * @param column --> the number of the column
     *
     */
    public void store(int row, int column){
        //read the new row and column values, since it is taken into account that the user does not start at 0 but at 1
        int newRow = row -1;
        int newColumn = column - 1;
     
        //Warning Messages --> Third Cicle(Extend the code).
        if(row <0 || row > length || column < 0 || column > width){
            JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para meter una caja", JOptionPane.WARNING_MESSAGE);
        }
        else{
            stockValues[newRow][newColumn] += 1;
            if (stockValues[newRow][newColumn] > height){
             
                //add one to the height variable, and for each stock and robbery array, add a new rectangle
                height += 1;
                stockSide.add(new Rectangle[length]);
                stockFront.add(new Rectangle[width]);
                robberySide.add(new Rectangle[length]);
                robberyFront.add(new Rectangle[length]);
                
                for(int i=0;i<length;i++){
                    //Create a crate per column for the side view(Stock and robbery)
                    stockSide.get(height-1)[i] = new Rectangle();
                    stockSide.get(height-1)[i].changeColor("red");
                    stockSide.get(height-1)[i].moveHorizontal(10*i);
                    robberySide.get(height-1)[i] = new Rectangle();
                    robberySide.get(height-1)[i].changeColor("red");
                    robberySide.get(height-1)[i].moveHorizontal(10*i);
                    robberySide.get(height-1)[i].moveVertical((10*i)+(height+1)*10);
                }
                for(int i=0;i<width;i++){
                    //Create a crate per column for the frontal view(Stock and robbery)
                    stockFront.get(height-1)[i] = new Rectangle();
                    stockFront.get(height-1)[i].changeColor("red");
                    stockFront.get(height-1)[i].moveHorizontal(10*i+((width+1)*10));
                    robberyFront.get(height-1)[i] = new Rectangle();
                    robberyFront.get(height-1)[i].changeColor("red");
                    robberyFront.get(height-1)[i].moveHorizontal(10*i+((width+1)*10));
                    robberyFront.get(height-1)[i].moveVertical((10)+(height+1)*10);
                }
                for(int i=0;i<height-1;i++){
                    for(j=0;j<length;j++){
                     
                     //vertical movement for stock and robbery side views
                    stockSide.get(i)[j].moveVertical(10);
                    robberySide.get(i)[j].moveVertical(10);
                    }
                    for(int j=0;j<width;j++){
                     
                     //vertical movement for stock and robbery front views
                    stockFront.get(i)[j].moveVertical(10);
                    robberyFront.get(i)[j].moveVertical(10);
                    }
                }
            }
        }
        
    }
    
  
    /***
     * function created to copy into the robbery array,
     * the array of stock values
     */
    public void copy(){
        for(int i= 0; i < length;i++){
            for(int j = 0; j < width;j++){
                robberyValues[i][j] = stockValues[i][j];
            }
        }
    }
    
  
    /***
     * Function created to steal a box 
     * from the position given by the user. 
     * Check if there are boxes to steal in that position
     *
     * @param row  --> the number of the row
     * @param column --> the number of the column
     *
     */
    public void steal(int row, int column){
        //Modify the variable row and column. The user counts as one the initial position of the array.
        row -= 1;
        column -= 1;
     
        //Warning Messages --> Third Cicle(Extend the code).
        if(row <0 || row > length || column < 0 || column > width){
            JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para robar una caja", JOptionPane.WARNING_MESSAGE);
        }
        else{
        ///takes a box from the position determined in the robbery plan and adds one to the counter of stolen boxes.
        robberyValues[row][column] -= 1;
        boxStole += 1;
        }  
    }
    
    /***
     * Function created to steal a box with respect to the row and column indicated by the user,
     * which are contained in the arrangement
     *
     * @param integer[] crate --> the array containing the row and column
     *
     */
    public void steal(int[] crate){
        int newLength = crate[0] - 1;
        int newWidth = crate[1] - 1;
         steal(newLength,newWidth);
    }
    
    
    /***
     * Function created to make visible all the rectangles placed both
     * in the stock arrays and in the robbery arrays
     */
    public void makeVisible(){
        stockVisible();
        robberyVisible();
    }
    
    
    /***
     * Function created to make invisible all the rectangles placed both
     * in the stock arrays and in the robbery arrays
     */
    public void makeInvisible(){
        //Make invisible the arrays of stock and robbery
        for(int i = 0; i < length;i++){
            for(int j = 0;j < width;j++){
                stockUp[i][j].makeInvisible();
                robberyUp[i][j].makeInvisible();
            }
        }
        for(int i=0;i<height;i++){
            for(int j=0;j<length;j++){
             
                //the side view of the stock and robbery arrays, they are going to be made invisible
                stockSide.get(i)[j].makeInvisible();
                robberySide.get(i)[j].makeInvisible();
            }
            for(int j=0;j<width;j++){
                //the front view of the stock and robbery arrays, they are going to be made invisible
                stockFront.get(i)[j].makeInvisible();
                robberyFront.get(i)[j].makeInvisible();
            }
        }
        }
  
  
    /***
    * Function created to make visible all the views 
    * of the security cameras of the stock
    */
    public void stockVisible(){
        int[] maxs = new int[stockValues.length];
        for(int i=0;i<stockValues.length;i++){
            maxs[i] = max(stockValues[i]);
            //Top View
            for(j=0;j<stockValues[0].length;j++){
                if(stockValues[i][j]>0){
                    stockUp[i][j].makeVisible();
                } 
            }
            //Side View
            for(int j=0;j<maxs[i];j++){
                stockSide.get(j)[i].makeVisible();
            }
        }   
        maxs = max(stockValues);
        for(int i=0;i<stockValues[0].length;i++){
            //Front View
            for(int j=0;j<maxs[i];j++){
                stockFront.get(j)[i].makeVisible();
            }
        }
    }
     
    public void robberyVisible(){
    int i;
    int j;
    int[] maxs = new int[robberyValues.length];
    for(int i=0;i<robberyValues.length;i++){
        maxs[i] = max(robberyValues[i]);
        //Top View
        for(int j=0;j<robberyValues[0].length;j++){
            if(robberyValues[i][j]>0){
                robberyUp[i][j].makeVisible();
            } 
        }
        //Side View
        for(int j=0;j<maxs[i];j++){
            robberySide.get(j)[i].makeVisible();
        }
    }   
    maxs = max(robberyValues);
    for(int i=0;i<robberyValues[0].length;i++){
        //Front View
        for(int j=0;j<maxs[i];j++){
            robberyFront.get(j)[i].makeVisible();
        }
    }
    }
    
  
    /**
     * Function created to calculate the maximum 
     * value of an array of numbers
     *
     * @param integer[] vector --> the array contains numbers
     *
     */
    private int max(int[] vector){
        int max = vector[0];
        for(int i=1;i<vector.length;i++){
            if (vector[i]>max){
                max = vector[i];
            }
        }
        return max;
    }
    
  
    /**
     * Function created to calculate the maximum value of a column, 
     * this is for when you have a matrix
     *
     * @param integer[][] matriz --> the matrix contains numbers 
     *
    */
    private int[] max(int[][] matriz){
        int max;
        int[] maxs = new int[matriz[0].length];
        
        for(int i=0;i<matriz[0].length;i++){
            max = matriz[0][i];
            for(int j=0;j<matriz.length;j++){
                if(matriz[j][i]>max){
                    max = matriz[j][i];
                }
            }
            maxs[i] = max;
        }
        return maxs;
    }
  
  
  /***
     * Function created to leave a box in the required position, 
     * and in that part, add a box to the matrix of numbers
     *
     * @param length --> the integer value of length
     * @param width --> the integer value of width
     *
     */
    private void fillSpace(int length, int width){
        if(robberyValues[length][width] == 0){
            robberyUp[length][width].makeVisible();
        }
        robberyValues[length][width] += 1;
    }
        
        
    /***
     * Function created to return the 
     * last box stolen in the robbery plan.
     */
    public void Return(){
      int[] tuples;
      
      //an array is created that has as elements the positions of the boxes that have been stolen
      tuples = Historial.get(Historial.size()-1);
      Historial.remove(Historial.size()-1);
      
      //A box is removed from the stolen box count and returned to where it was
      boxStole -= 1;
      fillSpace(tuples[0],tuples[1]);
    }
     
     
    /***
     * function created to organize 
     * a certain box in another position. 
     * The position of going and coming are given by the user
     *
     * @param integer[] from --> array with initial positions
     * @param integer[] to --> array with end positions
     *
     */
    public void arrange(int[] from, int[] to){
        //add or subtract to position respectly
        robberyValues[from[0]][from[1]] -=1;
        robberyValues[to[0]][to[1]] +=1;
    }
    
}
