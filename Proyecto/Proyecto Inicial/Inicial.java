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
        
        //Variable extra
        private int boxStole = 0;
        ArrayList<int[]> Historial = new ArrayList<int[]>();
       
        //matrix to store the boxes in the stock
        private Rectangle[][] stockSide;
        private Rectangle[][] stockFront;
        private Rectangle[][] stockUp;
        
        //matrix to store the boxes in the robbery
        private Rectangle[][] robberySide;
        private Rectangle[][] robberyFront;
        private Rectangle[][] robberyUp;
       
        
        /**
         * Constructor for objects of class Store
         */
        public Mission(int length, int width)
         {
           this.length = length;
           this.width = width;
           
           //creation of arrays[int] of stock
           stockValues = new int[length][width];
           
           //creation of arrays[int] of robbery
           robberyValues = new int[length][width];
           
           //creation of arrays of stock
           stockSide = new Rectangle[length][width];
           stockFront = new Rectangle[length][width];
           stockUp = new Rectangle[length][width];
           
           //creation of arrays of robbery
           robberySide = new Rectangle[length][width];
           robberyFront = new Rectangle[length][width];
           robberyUp = new Rectangle[length][width];
           
           fillArrays();
           
           
    
    
        }
        
        
        /***
         * function created to fill the matrices with rectangles, 
         * this to now make each of the rectangles visible
         */
        private void fillArrays(){
            for(int i = 0; i< length;i++){
                for(int j = 0; j< width; j++){
                    //Fill the stock arrays
                    stockSide[i][j] = new Rectangle();
                    stockFront[i][j] = new Rectangle();
                    stockUp[i][j] = new Rectangle();
                    
                    //Fill the robbery arrays
                    robberySide[i][j] = new Rectangle();
                    robberyFront[i][j] = new Rectangle();
                    robberyUp[i][j] = new Rectangle();
                }
            }
        }
      
        
        /***
         * function created to fill the matrix
         * of boxes from the user values
         */
        public void store(int numbers[]){
            int newLength = numbers[0];
            int newWidth = numbers[1];
            store(length,width);       
        }
            
       
        /***
         * function created to add a box
         * in a given row and column
         */
        public void store(int row, int column){
            int newRow = row -1;
            int newColumn = column - 1;
            if(newRow <0 || newRow > length || newColumn < 0 || newColumn > width){
                JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para meter una caja", JOptionPane.WARNING_MESSAGE);
            }
            else{
                stockValues[newRow][newColumn] += 1;
            }
        }
        
        
        private void refreshRobbery(int[][] crates, Rectangle[][] array){
            for(int i = 0; i< length;i++){
                for(int j = 0; j< width; j++){
                    if(crates[i][j] > 0){
                        array[i][j].makeVisible();
                    }
                    else{
                        array[i][j].makeInvisible();
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
                    robberyValues[i][j]= stockValues[i][j];
                }
            }
        }
        
        
        /***
         * Function created to make a tuple with
         * the box positions.
         */
        private void makeTuple(int row,  int column){
            int[] crate;
            crate = new int[2];
            crate[0] = row+1;
            crate[1] = column + 1;
            Historial.add(crate);
        }
        
        
        /***
         * Function created to steal a box 
         * from the position given by the user. 
         * Check if there are boxes to steal in that position
         */
        public void steal(int row, int column){
            int newRow = row -1;
            int newColumn = column - 1;
            if(row <0 || row > length || column < 0 || column > width){
                JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para robar una caja", JOptionPane.WARNING_MESSAGE);
            }
            else{
                robberyValues[newRow][newColumn] -= 1;
                boxStole += 1;   
                makeTuple(row,column);
            }  
        }
        
        
        /***
         *Function created to steal a box 
         *from the array of positions given by the user. 
         *Check if there are boxes to steal in that position
         */
        public void steal(int[] crate){
            int newLength = crate[0];
            int newWidth = crate[1];
            steal(newLength,newWidth);
            Historial.add(crate);                   
        }
        
        
        /***
         * Function created to make visible all the rectangles placed both
         * in the stock arrays and in the robbery arrays
         */
        public void makeVisible(){
            for(int i = 0; i < length;i++){
                for(int j = 0;j < width;j++){
                    //Make visible the arrays of stock
                    stockSide[i][j].makeVisible();
                    stockFront[i][j].makeVisible();
                    stockUp[i][j].makeVisible();
                    
                    //Make visible the arrays of robbery
                    robberySide[i][j].makeVisible();
                    robberyFront[i][j].makeVisible();
                    robberyUp[i][j].makeVisible();
                }
            }
        }
        
        
        /***
         * Function created to make invisible all the rectangles placed both
         * in the stock arrays and in the robbery arrays
         */
        public void makeInvisible(){
            for(int i = 0; i < length;i++){
                for(int j = 0;j < width;j++){
                    //Make invisible the arrays of stock
                    stockSide[i][j].makeInvisible();
                    stockFront[i][j].makeInvisible();   
                    stockUp[i][j].makeInvisible();
                    
                    //Make invisible the arrays of robbery
                    robberySide[i][j].makeInvisible();
                    robberyFront[i][j].makeInvisible();
                    robberyUp[i][j].makeInvisible();
                }
            }
        }
        
        
        /***
         * Function created to leave a box in the required position, 
         * and in that part, add a box to the matrix of numbers
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
          tuples = Historial.get(Historial.size()-1);
          Historial.remove(Historial.size()-1);
          boxStole -= 1;
          fillSpace(tuples[0],tuples[1]);
        }
         
         
         /***
         * function created to organize 
         * a certain box in another position. 
         * The position of going and coming are given by the user
         */
        public void arrange(int[] from, int[] to){
            //add or subtract to position respectly
            robberyValues[from[0]][from[1]] -=1;
            robberyValues[to[0]][to[1]] +=1;
        }
    } 


/*
//This is me :)





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
    
    //Variable extra
    private int boxStole = 0;
   
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
       robberySide = new ArrayList<Rectangle[]>() ;
       robberyFront = new ArrayList<Rectangle[]>() ;
       robberyUp = new Rectangle[length][width];
       
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
                stockUp[i][j].moveHorizontal((width+1)*20+10*j);
                stockUp[i][j].moveVertical(10*i);
                
                //Fill the robbery arrays
                robberyUp[i][j] = new Rectangle();
                robberyUp[i][j].changeColor("green");
                robberyUp[i][j].moveHorizontal((width+1)*20+10*j);
                robberyUp[i][j].moveVertical((length+1)*10+10*i);
            }
        }
    }
    
    
    /***
     * function created to fill the matrix
     * of boxes from the user values
     */
    public void store(int numbers[]){
        int newLength = numbers[0];
        int newWidth = numbers[1];
        store(length,width);       
    }
        
   
    /***
     * function created to add a box
     * in a given row and column
     */
    public void store(int row, int column){
        int newRow = row -1;
        int newColumn = column - 1;
        int i;
        int j;
        if(newRow <0 || newRow > length || newColumn < 0 || newColumn > width){
            JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para meter una caja", JOptionPane.WARNING_MESSAGE);
        }
        else{
            stockValues[newRow][newColumn] += 1;
            if (stockValues[newRow][newColumn] > height){
                height += 1;
                stockSide.add(new Rectangle[length]);
                stockFront.add(new Rectangle[width]);
                for(i=0;i<length;i++){
                    stockSide.get(height-1)[i] = new Rectangle();
                    stockSide.get(height-1)[i].changeColor("red");
                    stockSide.get(height-1)[i].moveHorizontal(10*i);
                }
                for(i=0;i<width;i++){
                    stockFront.get(height-1)[i] = new Rectangle();
                    stockFront.get(height-1)[i].changeColor("red");
                    stockFront.get(height-1)[i].moveHorizontal(10*i+(width*10));
                }
                for(i=0;i<height-1;i++){
                    for(j=0;j<length;j++){
                    stockSide.get(i)[j].moveVertical(10);
                    }
                    for(j=0;j<width;j++){
                    stockFront.get(i)[j].moveVertical(10);
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
                robberyValues[i][j]= stockValues[i][j];
            }
        }
    }
    
    
    /***
     * Function created to steal a box 
     * from the position given by the user. 
     * Check if there are boxes to steal in that position
     */
    public void steal(int row, int column){
        row -= 1;
        column -= 1;
        if(row <0 || row > length || column < 0 || column > width){
            JOptionPane.showMessageDialog(null, "Cuidado", "Posicion no válida para robar una caja", JOptionPane.WARNING_MESSAGE);
        }
        else{
        robberyValues[row][column] -= 1;
        boxStole += 1;
        }  
    }
    
    
    public void steal(int[] crate){
        int newLength = crate[0] - 1;
        int newWidth = crate[1] - 1;
        
        
    }
    
    
    /***
     * Function created to make visible all the rectangles placed both
     * in the stock arrays and in the robbery arrays
     */
    // public void makeVisible(){
        // for(int i = 0; i < length;i++){
            // for(int j = 0;j < width;j++){
                // //Make visible the arrays of stock
                // stockSide[i][j].makeVisible();
                // stockFront[i][j].makeVisible();
                // stockUp[i][j].makeVisible();
                
                // //Make visible the arrays of robbery
                // robberySide[i][j].makeVisible();
                // robberyFront[i][j].makeVisible();
                // robberyUp[i][j].makeVisible();
                
            // }
        // }
    // }
    
    
    /***
     * Function created to make invisible all the rectangles placed both
     * in the stock arrays and in the robbery arrays
     */
    public void makeInvisible(){
        //Make invisible the arrays of stock
        for(int i = 0; i < length;i++){
            for(int j = 0;j < width;j++){
                stockUp[i][j].makeInvisible();
            }
        }
        for(int i=0;i<height;i++){
            for(int j=0;j<length;j++){
                stockSide.get(i)[j].makeInvisible();
            }
            for(int j=0;j<width;j++){
                stockFront.get(i)[j].makeInvisible();
            }
        }

        // //Make invisible the arrays of robbery
                // robberySide.get(i)[j].makeInvisible();
                // robberyFront.get(i)[j].makeInvisible();
                // robberyUp[i][j].makeInvisible();
        }
    
    public void stockVisible(){
        int i;
        int j;
        int[] maxs = new int[stockValues.length];
        makeInvisible();
        //Top View
        for(i=0;i<stockValues.length;i++){
            maxs[i] = max(stockValues[i]);
            for(j=0;j<stockValues[0].length;j++){
                if(stockValues[i][j]>0){
                    stockUp[i][j].makeVisible();
                } 
            }
            //Side View
            for(j=0;j<maxs[i];j++){
                stockSide.get(j)[i].makeVisible();
            }
        }
        //{{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}}
        //Front View   
        maxs = max(stockValues);
        for(i=0;i<stockValues.length;i++){
            for(j=0;j<maxs[i];j++){
                stockFront.get(j)[i].makeVisible();
            }
        }
    }
    
    private int max(int[] vector){
        int i;
        int max = vector[0];
        
        for(i=1;i<vector.length;i++){
            if (vector[i]>max){
                max = vector[i];
            }
        }
        return max;
    }
    
    private int[] max(int[][] matriz){
        int i;
        int j;
        int max;
        int[] maxs = new int[matriz.length];
        
        for(i=0;i<matriz[0].length;i++){
            max = matriz[0][i];
            for(j=0;j<matriz.length;j++){
                if(matriz[j][i]>max){
                    max = matriz[j][i];
                }
            }
            maxs[i] = max;
        }
        return maxs;
    }
}
 


*/
