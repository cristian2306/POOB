import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * Write a description of class Stock here.
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (2.0 23/02/2021)
 */
public class Stock
{
    // matrix to store the values in the rows and columns in the stock 
    private int[][] stockValues; 

    //matrix to store the boxes in the stock
    private ArrayList<Stack<Rectangle>> stockSide;
    private ArrayList<Stack<Rectangle>>  stockFront;
    private Rectangle[][] stockUp;
    private int[] maxSide;
    private int[] maxFront;
    private int[][] maxUp;

    //Dimensions
    private int row;
    private int column;
    private int Max;

    /**
     * Constructor object for class Stock.
     * @param row
     * @param column
     */

    public Stock(int row, int column){
        //define the dimensions of the stock
        this.row = row;
        this.column = column;
     

        //creation of arrays[int] of stock
       stockValues = new int[row][column];
       maxUp = new int[row][column]; 
       //creation of arrays of stock
       stockSide = new ArrayList<Stack<Rectangle>>() ;
       stockFront = new ArrayList<Stack<Rectangle>>();
       stockUp = new Rectangle[row][column];

       //fill view arrays with rectangles
       this.stockUp = fillArrays();
       fillViews(stockSide,this.row);
       fillViews(stockFront,this.column);
    }

    /**
     * function created to fill the matrices with rectangles, 
     * this to now make each of the rectangles visible
     */
    private Rectangle[][] fillArrays(){
        Rectangle[][] stockUp = new Rectangle[this.row][this.column];
        int[] maxValueMax = maxColumns(stockValues);
        for(int i = 0; i<this.row;i++){
            for(int j = 0; j< this.column;j++){
                //Fill the stock arrays
                stockUp[i][j] = new Rectangle();
                int grew = stockUp[i][j].getSize();
                stockUp[i][j].changeColor("blue"); 
                stockUp[i][j].moveVertical(grew*(Max+i-this.row+2));
                stockUp[i][j].moveHorizontal(grew*(j+this.row+this.column+2));
            }
        }     
        return stockUp;
    }

    /**
     * Function created to fill the view 
     * (which is created with arraylists) with empty stacks 
     * @param stock
     * @param length
     */
    private void fillViews(ArrayList<Stack<Rectangle>> stock, int length){
        for(int i = 0; i<length;i++){
           stock.add(i, new Stack<Rectangle>());
        }
    }

    /**
     * Function created to return the array of values
     * @return
     */
    public int[][] getValues(){
        return this.stockValues;
    }

    /**
     * Function created to return the side view
     * @return
     */
    public ArrayList getSide(){
        return this.stockSide;
    }

    /**
     * Function created to return the front view
     * @return
     */
    public ArrayList getFront(){
        return this.stockFront;
    }
    
    /**
     * Function created to return the up view
     * @return
     */
    public Rectangle[][] getUp(){
        return this.stockUp;
    }

    /**
     * Function created to return row and column in array mode
     * @param row
     * @param column
     * @return int[] position
     */
    private int[] getPositionArray(int row,int column){
        int[] position = new int[2];
        position[0] = row+1;
        position[1] = column+1;
        return position;
    }

    /**
     * Function created to insert the boxes in 
     * the indicated position as many times as the user says
     * @param cant
     * @param position
     */
    public void takeCrates(int cant, int[] position){
        for(int i = 1; i<=cant; i++){
            store(position);
        }
    }

    /**
     * Function created to generate 
     * the complete stock matrix from values ​​given by the user
     * @param crates
     */
    public void createMatrix(int[][] crates){
        stockValues = crates;
        refresh(stockValues);
    }

    /**
     * function created to place a box in 
     * the position that the user says 
     * (the coordinates of where to leave the box are in the arrangement)
     * 
     * @param numbers[]  --> array of numbers, contains the row and the column
     */
    public void store(int numbers[]){
        int newRow = numbers[0];
        int newColumn = numbers[1];
        store(newRow,newColumn);
        
    }

    /**
     * function created to add a box
     * in a given row and column
     * 
     * @param row  --> the number of the row
     * @param column --> the number of the column
     */
    public void store(int row, int column){
        if(row < 1 && column < 1 || row >=1 && column < 1 || row <1 && column >= 1){
            JOptionPane.showMessageDialog(null, "No puedes meter cajas en esta posición", "Cuidado", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int newRow = row - 1;
            int newColumn = column - 1;
            stockValues[newRow][newColumn] += 1;
            //add one to the height variable, and for each stock array, add a new rectangle
            setStockValues(stockValues);
        }
    } 
        
    

    /**
     * Function created to calculate the maximum 
     * value of an array of numbers
     *
     * @param integer[] vector --> the array contains numbers
     *
     */
    private int maxRow(int[] vector){
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
    private int[] maxColumns(int[][] matriz){
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

    /**
     * Function created to refresh the values ​​of the arraylists 
     * with respect to the values ​​of the stockvalues, 
     * it serves for cases in which we add boxes
     */
    public void refresh(int[][] stockValues){
        //Create new Arraylists in blank
        ArrayList<Stack<Rectangle>> newStockSide = new ArrayList<Stack<Rectangle>>() ;
        ArrayList<Stack<Rectangle>> newStockFront = new ArrayList<Stack<Rectangle>>();
        fillViews(newStockSide,this.row);
        fillViews(newStockFront,this.column);
        //returns the maximum value of the columns of stockvalues
        int[] maxValueColumns = maxColumns(stockValues);
        int[] maxValueMax = new int[this.row];

        //With each maximum value obtained, we put that amount of boxes in each position,
        for(int i=0;i<this.row;i++){
            maxValueMax[i] = maxRow(stockValues[i]);
        }      
        this.maxSide = maxValueMax;
        this.maxFront = maxValueColumns;
        this.Max = maxRow(maxValueMax);
        makeInvisible();
        this.stockUp = fillArrays();
        this.stockSide = createStock(this.row,maxValueMax,this.column+1);
        this.stockFront = createStock(this.column,maxValueColumns,0);
    }

    public void setStockValues(int stockValues[][]){
        this.stockValues = stockValues;
        refresh(this.stockValues);
    }
    
    /**
     * The method is responsible for creating a view according to the parameters
     * @param length long view
     * @param stockValues array of integers, represents the vector of maximums per column for the view
     * @param horizontal horizontal movement that the view should make.
     * @return  Stack's newStock ArrayList de Stack representing the view
     */
    private ArrayList createStock(int length,int[] stockValues,int horizontal){
        ArrayList<Stack<Rectangle>> newStock = new ArrayList<Stack<Rectangle>>() ;
        fillViews(newStock,length);
        int grew = stockUp[0][0].getSize();
        int Max = maxRow(stockValues);
        //Enter the Stack n number of rectangles
        for(int i =0;i<length;i++){
            for(int j=0;j<stockValues[i];j++){
                Rectangle aux = createCare(grew*(Max-j+1),grew*(i+horizontal));
                newStock.get(i).push(aux);
            }
        }
        return newStock;
    }
    
    /**
    *The function returns a rectangle with the indicated movements.
    *@param moveV vertical movement to be performed by the box.
    *@param moveH horizontal movement that the box must perform.
    *@return Returns the box with the movements made
    **/
    public Rectangle createCare(int moveV, int moveH){
        Rectangle aux = new Rectangle();
        aux.changeColor("blue");
        aux.moveHorizontal(moveH);
        aux.moveVertical(moveV);
        return aux;
    }
    
    /**
     * Function created to return the array called stockvalues
     * @return  -->  matrix[][]
     */
    private int[][] setValues(){
        return stockValues;
    }

    /**
     * function created to make the top view visible from the values ​​obtained in stockvalues 
     */
    private void topviewVisible(boolean visible){
        for(int i = 0; i< stockUp.length; i++){
            for(int j=0;j<stockUp[0].length;j++){
                if(visible){
                    if(stockValues[i][j]>0){
                        stockUp[i][j].makeVisible();
                        maxUp[i][j] = 1;
                    } 
                }
                else{
                    stockUp[i][j].makeInvisible();
                }
            }
        } 
    }
    
    /**
     * function created to make the top view visible from the values ​​obtained in stockvalues 
     */
    private void stockVisible(ArrayList<Stack<Rectangle>> stock,boolean visible){
        for(int i=0;i<stock.size();i++){
            for(int j=0;j<stock.get(i).size();j++){
                if(visible){
                    stock.get(i).get(j).makeVisible();
                }
                else{
                    stock.get(i).get(j).makeInvisible();  
                }
            }
        }
    }
    
    /**
     * Function created to make all the warehouse simulator invisible
     */
    public void makeInvisible(){
        topviewVisible(false);
        stockVisible(this.stockSide,false);
        stockVisible(this.stockFront,false);
    }

    /**
     * function created to call all the methods that make the views visible
     */
    public void makeVisible(){
        makeInvisible();
        topviewVisible(true);
        stockVisible(this.stockSide,true);
        stockVisible(this.stockFront,true);
    }
    
    /**
     * Retun the max value
     * @return
     */
    public int getMax(){
        return Max;
    }
    
    /**
     * Function generated as support for Visible topview, because if in the matrix of values, 
     * the position is greater than 0, paint that box
     * @return
     */
    public int[][] getMaxUp(){
        return maxUp;
    }
    
    /**
    * Function created to return the array of maximum values ​​of each column
     * @return
     */
    public int[] getMaxFront(){
        return maxFront;
    }

    /**
     * Function created to return the array of maximum values ​​of each row 
     * @return
     */
    public int[] getMaxSide(){
        return maxSide;
    }
}
