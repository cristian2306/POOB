import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;


/**
 * Write a description of class Robbery here.
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (2.0 23/02/2021)
 */
public class Robbery
{
    // matrix to store the values in the rows and columns in the robbery 
    private int[][] robberyValues; 

    //matrix to store the boxes in the robbery
    private ArrayList<Stack<Rectangle>> robberySide;
    private ArrayList<Stack<Rectangle>>  robberyFront;
    private Rectangle[][] robberyUp;
    private int[] maxSide;
    private int[] maxFront;
    private int[][] maxUp;

    //Dimensions
    private int row;
    private int column;
    private int maxStock;
    private int Max;

    //Robbery variables and historial
    private int boxstole = 0;
    Stack<int[]> stack_1 = new Stack<int[]>();

    /**
     * Constructor object for class robbery.
     * @param row
     * @param column
     */
    public Robbery(int row, int column, int maxStock){
        //define the dimensions of the robbery
        this.row = row;
        this.column = column;

        //creation of arrays[int] of stock
        robberyValues = new int[row][column];
        maxUp = new int[row][column]; 
        //creation of arrays of stock
        robberySide = new ArrayList<Stack<Rectangle>>() ;
        robberyFront = new ArrayList<Stack<Rectangle>>();
        robberyUp = new Rectangle[row][column];

        //fill view arrays with rectangles
        this.robberyUp = fillArrays("green");
        fillViews(robberySide,this.row);
        fillViews(robberyFront,this.column);
    }

    /**
     * function created to fill the matrices with rectangles, 
     * this to now make each of the rectangles visible
     */
    private Rectangle[][] fillArrays(String color){
        Rectangle[][] stockUp = new Rectangle[this.row][this.column];
        int[] maxValueMax = maxColumns(robberyValues);
        int Max = maxRow(maxValueMax);
        for(int i = 0; i<this.row;i++){
            for(int j = 0; j< this.column;j++){
                // //Fill the stock arrays
                stockUp[i][j] = new Rectangle();
                int grew = stockUp[i][j].getSize();
                stockUp[i][j].changeColor(color); 
                stockUp[i][j].moveVertical(grew*(Max+maxStock+i-this.row+5));
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
     * Function created to calculate the maximum 
     * value of an array of numbers
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
    public void refresh(String color){
        //Create new Arraylists in blank
        ArrayList<Stack<Rectangle>> newRobberySide = new ArrayList<Stack<Rectangle>>() ;
        ArrayList<Stack<Rectangle>> newRobberyFront = new ArrayList<Stack<Rectangle>>();
        fillViews(newRobberySide,this.row);
        fillViews(newRobberyFront,this.column);
        

        //returns the maximum value of the columns of robbey values
        int[] maxValueColumns = maxColumns(robberyValues);
        int[] maxValueMax = new int[this.row];
        //With each maximum value obtained, we put that amount of boxes in each position,
        for(int i=0;i<this.row;i++){
            maxValueMax[i] = maxRow(robberyValues[i]);
        }       
        //The array that we had created in the constructor is now the one we created with this function
        this.maxSide = maxValueMax;
        this.maxFront = maxValueColumns;
        Max = maxRow(maxValueMax);
        makeInvisible();
        this.robberyUp = fillArrays(color);
        this.robberySide = createRobbery(this.row,maxValueMax,this.column+1,color);
        this.robberyFront = createRobbery(this.column,maxValueColumns,0,color);
    }
    
    /**
     * The method is responsible for creating a view according to the parameters
     * @param length long view
     * @param robberyValues array of integers, represents the vector of maximums per column for the view
     * @param horizontal horizontal movement that the view should make.
     * @return Stack's newRobbery ArrayList representing the view
     */
    private ArrayList createRobbery(int length,int[] robberyValues,int horizontal,String color){
        ArrayList<Stack<Rectangle>> newRobbery = new ArrayList<Stack<Rectangle>>() ;
        fillViews(newRobbery,length);
        int grew = robberyUp[0][0].getSize();
        int Max = maxRow(robberyValues);
        //Enter the Stack n number of rectangles
        for(int i =0;i<length;i++){
            for(int j=0;j<robberyValues[i];j++){
                Rectangle aux = createCare(grew*(Max+maxStock-j+4),grew*(i+horizontal),color);
                newRobbery.get(i).push(aux);
            }
        }
        return newRobbery;
    }
    
    /**
    *The function returns a rectangle with the indicated movements.
    *@param moveV vertical movement to be performed by the box.
    *@param moveH horizontal movement that the box must perform.
    *@return Returns the box with the movements made
    **/
    private Rectangle createCare(int moveV, int moveH,String color){
        Rectangle aux = new Rectangle();
        aux.changeColor(color);
        aux.moveHorizontal(moveH);
        aux.moveVertical(moveV);
        return aux;
    }

    /**
     * Function created to return the array called stockvalues
     * @return  -->  matrix[][]
     */
    public int[][] getValues(){
        return this.robberyValues;
    }

    /**
     * Function created to return the side view
     * @return side view 
     */
    public ArrayList getSide(){
        return this.robberySide;
    }

    /**
     * Function created to return the front view
     * @return front view
     */
    public ArrayList getFront(){
        return this.robberyFront;
    }
    
    /**
     * Function created to return the up view
     * @return up view
     */
    public Rectangle[][] getUp(){
        return this.robberyUp;
    }

    /**
     * Function created to return row and column in array mode
     * @param row
     * @param column
     * @return int[] position
     */
    private int[] getPositionArray(int row,int column){
        int[] position = new int[2];
        position[0] = row;
        position[1] = column;
        return position;
    }

    /**
     * Function created to copy the values ​​of the parameter array 
     * to robberyValues
     * @param Views
     */
    public void copy(int[][] Views, int maxRow){
        for(int i=0;i<Views.length;i++){
            for(int j=0;j<Views[i].length;j++){
                this.robberyValues[i][j] = Views[i][j];
            }
        }
        this.maxStock = maxRow;
    }

    /**
     * Function created to steal a box in the position 
     * (row, column) that the user gives us
     * @param row
     * @param column
     */
    public void steal(int row, int column){
        if(row < 1 && column < 1 || row >=1 && column < 1 || row <1 && column >= 1){
            JOptionPane.showMessageDialog(null, "No puedes robar cajas en esta posición", "Cuidado", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int[] position = getPositionArray(row, column);
            row -=1;
            column -=1;
            robberyValues[row][column] -=1;
            boxstole += 1;
            stack_1.push(position);
            refresh("green");
        }
    }

    /**
     * Function created to steal a box in the position of the user. 
     * The positions are in an array [2] of integers
     * @param crate
     */
    public void steal(int[] crate){
        steal(crate[0], crate[1]);
    }

    /**
     * Function created to return the last stolen box
     */
    public int[] Return(){
        int[] lastPosition = stack_1.pop();
        boxstole -= 1;
        robberyValues[lastPosition[0]-1][lastPosition[1]-1] += 1;
        refresh("green");
        return lastPosition;
    }

    /**
     * Function created to reorganize a box from a certain position, 
     * to the position that the user wants
     * @param from
     * @param to
     */
    public void arrange(int[] from, int[] to){
        robberyValues[from[0]-1][from[1]-1] -= 1;
        robberyValues[to[0]-1][to[1]-1] += 1;
        refresh("green");
    }


    /**
     * Function created to return the number of boxes in the robbery plan
     * @return int answer
     */
    public int stolen(){
        int answer = 0;
        for(int i = 0; i< this.row;i++){
            for(int j = 0; j< this.column; j++){
                answer += robberyValues[i][j];
            }
        }
        return answer;
    }
    
    /**
     * function created to make the top view visible from the values ​​obtained in stockvalues 
     */
    private void topviewVisible(boolean visible){
        for(int i = 0; i< robberyUp.length; i++){
            for(int j=0;j<robberyUp[0].length;j++){
                if(visible){
                    if(robberyValues[i][j]>0){
                        robberyUp[i][j].makeVisible();
                        maxUp[i][j] = 1;
                    } 
                }
                else{
                    robberyUp[i][j].makeInvisible();
                }
            }
        } 
    }
    
    /**
     * Function created to make visible to all the boxes that are in the arraylist 
     * that is given as a parameter (it serves for side view and front view)
     * @param robbery
     * @param visible
     */
    private void robberyVisible(ArrayList<Stack<Rectangle>> robbery,boolean visible){
        for(int i=0;i<robbery.size();i++){
            for(int j=0;j<robbery.get(i).size();j++){
                if(visible){
                    robbery.get(i).get(j).makeVisible();
                }
                else{
                    robbery.get(i).get(j).makeInvisible();  
                }
            }
        }
    }
    
    /**
     * Function created to make all the robbery plan simulator invisible
     */
    public void makeInvisible(){
        topviewVisible(false);
        robberyVisible(this.robberySide,false);
        robberyVisible(this.robberyFront,false);
    }

    /**
     * function created to call all the methods that make the views visible
     */
    public void makeVisible(){
        topviewVisible(true);
        robberyVisible(this.robberySide,true);
        robberyVisible(this.robberyFront,true);
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
