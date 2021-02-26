import java.util.ArrayList;
import java.util.Stack;

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

    //Dimensions
    private int row;
    private int column;

    //Robbery variables and historial
    private int boxstole = 0;
    Stack<int[]> stack_1 = new Stack<int[]>();

    /**
     * Constructor object for class robbery.
     * @param row
     * @param column
     */
    public Robbery(int row, int column){
        //define the dimensions of the robbery
        this.row = row;
        this.column = column;

        //creation of arrays[int] of stock
        robberyValues = new int[row][column];

       //creation of arrays of stock
       robberySide = new ArrayList<Stack<Rectangle>>() ;
       robberyFront = new ArrayList<Stack<Rectangle>>();
       robberyUp = new Rectangle[row][column];

       //fill view arrays with rectangles
       this.robberyUp = fillArrays();
       fillViews(robberySide,this.row);
       fillViews(robberyFront,this.column);
    }


    /**
     * function created to fill the matrices with rectangles, 
     * this to now make each of the rectangles visible
     */
    private Rectangle[][] fillArrays(){
        Rectangle[][] stockUp = new Rectangle[this.row][this.column];
        int[] maxValueMax = maxColumns(robberyValues);
        int Max = maxRow(maxValueMax);
        for(int i = 0; i<this.row;i++){
            for(int j = 0; j< this.column;j++){
                // //Fill the stock arrays
                stockUp[i][j] = new Rectangle();
                stockUp[i][j].changeColor("green"); 
                stockUp[i][j].moveVertical(10*(Max+i-this.row+2));
                stockUp[i][j].moveHorizontal(10*(j+this.row+this.column+2));
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
    private void refresh(){
        //Create new Arraylists in blank
        ArrayList<Stack<Rectangle>> newRobberySide = new ArrayList<Stack<Rectangle>>() ;
        ArrayList<Stack<Rectangle>> newRobberyFront = new ArrayList<Stack<Rectangle>>();

        //returns the maximum value of the columns of stockvalues
        int[] maxvalueColumns = maxColumns(robberyValues);

        //With each maximum value obtained, we put that amount of boxes in each position,
        for(int i= 0; i<this.row;i++){
            int maxvalueRow = maxRow(robberyValues[i]);
            for(int j = 0; j<= maxvalueRow; j++){
                newRobberySide.get(i).push(new Rectangle());
            }
        }

        //traverses the array of maximum values ​​of the columns of stockvalues,
        // and to each position it creates the number of boxes assigned
        for(int i = 0; i< this.column;i++){
            for(int j = 0; j<= maxvalueColumns[i];j++){
                newRobberyFront.get(i).push(new Rectangle());
            }
        }

        //The array that we had created in the constructor is now the one we created with this function
        this.robberySide = newRobberySide;
        this.robberyFront = newRobberyFront;
    }

    /**
     * Function created to return the array called stockvalues
     * @return  -->  matrix[][]
     */
    private int[][] setValues(){
        return robberyValues;
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
        position[1] = row;
        return position;
    }

    /**
     * Function created to steal a box in the position 
     * (row, column) that the user gives us
     * @param row
     * @param column
     */
    public void steal(int row, int column){
        int[] position = getPositionArray(row, column);
        row -=1;
        column -=1;
        robberyValues[row][column] -=1;
        boxstole += 1;
        stack_1.push(position);
        refresh();
        //En esta linea se debe refrescar la imagen, pues se sabe que la de las camaras
        //de la bodega no se modifican, pero las del robbery si.
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
    public void Return(){
        int[] lastPosition = stack_1.pop();
        boxstole -= 1;
        robberyValues[lastPosition[0]-1][lastPosition[1]-1] += 1;
        refresh();
        //En esta linea se debe refrescar la imagen, pues se sabe que la de las camaras
        //de la bodega no se modifican, pero las del robbery si.
    }

    /**
     * Function created to reorganize a box from a certain position, 
     * to the position that the user wants
     * @param from
     * @param to
     */
    public void arrange(int[] from, int[] to){
        robberyValues[from[0]-1][from[0]-1] -= 1;
        robberyValues[to[0]-1][to[0]-1] += 1;
        refresh();
        //En esta linea se debe refrescar la imagen, pues se sabe que la de las camaras
        //de la bodega no se modifican, pero las del robbery si.
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

}
