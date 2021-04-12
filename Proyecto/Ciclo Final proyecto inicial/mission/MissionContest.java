package mission;
import java.util.ArrayList;

/**
 * Clase creada para resolver el problema y para simularlo
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (3.0 08/03/2021)
 */
public class MissionContest 
{
    //Crea la variable mission, es el que inserta y roba cajas con los simuladores
    private Mission mission;

    //Variables para guardar la cantidad de cajas robadas
    private int totalBoxes;
    private int[][] matrix_warehouse;
    private int[][] matrix_robbery;


    /**
     * Funcion creada para resolver la matriz de robo, y decirnos cuantas cajas se pudieron
     * robar. Se extiende el parámetro en el sentido de que se imprime cada movimiento que se haga
     * @param heights
     */
    public int solve(int[][] heights){
        //matrix_warehouse y matrix_robbery se deja igual, pues se da a entender que ya se hizo un copy para que la matriz
        //de robo tenga valores
        this.matrix_warehouse = heights;
        refresh();
        this.matrix_robbery = heights;
        canPosition(false);
        return totalBoxes;  
    }

    /**
     * Función creada para simular la solución de la automatización del robo.
     * @param heights
     * @param slow
     */
    public void simulate(int[][] heights, boolean slow){
        //Pedimos las dimensiones de la bodega para crear el simulador
        int row = heights.length;
        int column = heights[0].length;
        //Llamado a la clase Mission para crear las vistas y el warehouse
        mission = new Mission(row,column,heights);
        this.matrix_warehouse = mission.warehouse();
        //se hace copy para empezar el robo
        mission.copy();
        //Se define que la matriz de robo que da Mission va a ser la misma que la que definimos en esta clase
        this.matrix_robbery = mission.layout();
        mission.makeVisible();
        canPosition(true);

    }

    /**
     * Función creada para retornar la matriz numérica perteneciente a la bodega
     * @return - matriz numérica de bodega
     */
    public int[][] getWarehouse(){
        return this.matrix_warehouse;
    }

     /**
     * Función creada para retornar la matriz numérica perteneciente al robo
     * @return - matriz numérica de robo
     */
    public int[][] getRobbery(){
        return this.matrix_robbery;
    }

    /**
     * Funcion creada para insertar cajas en la matriz numérica de acuerdo a la posición
     * requerida por el usuario
     * @param row
     * @param column
     */
    private void store(int row, int column){
        this.matrix_warehouse[row][column] +=1;
        refresh();
    }

    /**
     * Función creada para copiar en la matriz numérica de robo, los valores
     * que tenga la matriz de bodega, esta función se usa en caso que el usuario 
     * tenga que meter más cajas (Actualizando la matriz)
     */
    private void refresh(){
        this.matrix_robbery = this.matrix_warehouse.clone();
    }

    /**
     * Función creada para robar en la posición deseada por el usuario en la matriz de robo
     * @param row
     * @param column
     */
    private void steal(int row, int column){
        this.matrix_robbery[row-1][column-1] -=1;
    }

    /**
     *  Funcion creada para robar cajas de la posicion que está como parámetro
     *  Estas cajas se meten como rectángulos en una pila
     *  Se crea contador para mirar cuantas cajas se han robado
     * @param row
     * @param column
     */
    private int stealPosition(int row, int column){
        //Revise que quede al menos una caja en la posición
         for(int i = this.matrix_robbery[row][column]; i>1;i--){
            //Roba la caja en la posición y suma 1 a las cajas robadas(totalBoxes)
            steal(row+1, column+1);
            totalBoxes +=1;
        }
        //Retornar las cajas robadas en esa posición          
        return totalBoxes;
    }

    /**
     * Función creada para simular el robo de cajas. Como ahora podemos usar el método steal
     * de Mission, se roba con ese método en vez de robar con el que creamos en esta clase
     * @param row
     * @param column
     * @return
     */
    private int stealPositionSimulate(int row, int column){
        for(int i = this.matrix_robbery[row][column]; i > 1; i--){
            mission.steal(row+1, column+1);
            totalBoxes += 1;
        }
        return totalBoxes;
    }


    /**
     * Funcion creada para sacar el maximo de la columna que desee el usuario
     * @param matriz --> int[][]
     * @param column --> int
     * @return --> Máximo de la columna
     */
    private int maxColumn(int column){        
        //Se toma como valor inicial el primer elemento de la matriz numérica pero en esa columna
        int max_column = this.matrix_robbery[0][column];
        for(int i = 0; i< this.matrix_robbery.length;i++){
            if (this.matrix_robbery[i][column] > max_column){
                max_column = this.matrix_robbery[i][column];
            }
        }
        return max_column;
    }

    /**
     * Funcion creada para retornar el número máximo de una fila
     * @param vector
     * @return --> Máximo de la fila
     */
    private int maxRow(int[] vector, int position){
        //Se toma como valor inicial el primer elemento del vector
        int max = vector[0];
        for(int i=1;i<vector.length;i++){
            if (vector[i]>max){
                max = vector[i];
            }
        }
        return max;
    }

    /**
     * Funcion generada para guardar los índices de la fila donde está repetido el número máximo
     * @param vector
     * @param max
     * @return
     */
    private ArrayList getRepeatsRow(int[] vector, int max){
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i< vector.length;i++){
            if(vector[i] == max){
                //Guarda las posiciones de la columna donde el máximo está repetido
                index.add(i);
            }
        }
        return index;
    }

    /**
     * Funcion generada para guardar los índices de la columna donde está repetido el número máximo
     * @param column
     * @param max
     * @return
     */
    private ArrayList getRepeatsColumn(int column, int max){
        int[][] matriz = this.matrix_robbery;
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i< matriz.length;i++){
            if(matriz[i][column] == max){
                //Guarda las posiciones de las filas donde el máximo está repetido
                index.add(i);
            }
        }
        return index;
    }

    /**
     * Función generada para revisar si los máximos repetidos son máximos de su fila
     * @param column
     * @param max_number
     * @return
     */
    private boolean Checker_2_repeatcolumn(int column, int max_number){
        int count = 0;
        boolean safe = true;
        ArrayList repeats = getRepeatsColumn(column, max_number);
        for(int i = 0; i< repeats.size();i++){
            int position = (int)repeats.get(i);
            if(max_number != maxRow(this.matrix_robbery[position], position)){
                count +=1;
            }
        }
        if(count == repeats.size()){
            safe = false;
        }
        return safe;
    }

    /**
     * Funcion creada para revisar en la columna donde está más de una vez el número máximo
     * Si en sus filas correspondientes son también el máximo de fila, en caso que no lo sean
     * se les roba, en caso que si, se revisa si aparece más de una vez en esa fila
     * @param column
     * @param max_number
     */
    private void checkerRepeatColumn(int column, int max_number, boolean simulate){
        ArrayList repeats = getRepeatsColumn(column,max_number);
        for(int i = 0; i< repeats.size();i++){int position = (int)repeats.get(i);
            if(Checker_2_repeatcolumn(column, max_number)){
                //Revisa si el número es máximo en esa posición de fila
                if(max_number != maxRow(this.matrix_robbery[position], position)){
                    //En caso que esté simulando
                    if(simulate){stealPositionSimulate(position,column);}
                    //En caso que no esté simulando
                    else{stealPosition(position,column);}
                }
                else{
                    //Si el número máximo está repetido en la fila que se está revisando
                    if(getRepeatsRow(this.matrix_robbery[position], max_number).size() > 1){
                        //En caso que esté simulando
                        if(simulate){checkerRepeatRow(this.matrix_robbery[position],max_number,position,true);}
                         //En caso que no esté simulando
                        else{checkerRepeatRow(this.matrix_robbery[position],max_number,position,false);}
                    }
                }
            }
            else{
                //En caso que esté simulando
                if(simulate){stealPositionSimulate((int)repeats.get(0), column);}
                //En caso que no esté simulando
                else{stealPosition((int)repeats.get(0), column);}
            }
        }
    }

    /**
     * Funcion creada para revisar si en los índices donde están repetidos, estos son máximos de su columna 
     * correspondiente, si eso no pasa, se le suma 1 al contador
     * @param vector
     * @param max_number
     * @return
     */
    private boolean Checker_2_repeatRow(int[] vector,int max_number){
        int count = 0;
        boolean safe = true;
        ArrayList repeats = getRepeatsRow(vector,max_number);
        for(int i = 0; i< repeats.size();i++){
            int position = (int)repeats.get(i);
            if(max_number != maxColumn(position)){
                count +=1;
            }
        }
        if(count == repeats.size()){
            safe = false;
        }
        return safe;

    }

    /**
     * Funcion creada para revisar en la fila si el número máximo está repetido
     * En caso tal, se revisa cada índice su columna correspondiente para mirar si también es el
     * máximo de la columna, en caso que no lo sea, se le roba
     * @param vector
     * @param max_number
     * @param position_row
     */
    private void checkerRepeatRow(int[] vector,int max_number, int position_row, boolean simulate){
        //Arreglo de índices donde está repetido el num_max
        ArrayList repeats = getRepeatsRow(vector,max_number);
        for(int i = 0; i< repeats.size();i++){int position = (int)repeats.get(i);
            //Si no es el máximo de su columna
            if (Checker_2_repeatRow(vector, max_number) && simulate){
                if(max_number != maxColumn(position)){stealPositionSimulate(position_row, position);}
                //Si es el máximo de su columna
                else{
                    //Si se encuentra más de una vez el número máximo en la columna
                    if(getRepeatsColumn(position,max_number).size() > 1){
                        //Si está simulando
                        if(simulate){checkerRepeatColumn(position, max_number,true);}
                        //Si no está simulando
                        else{checkerRepeatColumn(position, max_number,false);}
                    }
                }
            }
            else{
                //En caso que no esté simulando
                if(!simulate){stealPosition(position_row, (int)repeats.get(0));}
                //En caso que esté simulando
                else{stealPositionSimulate(position_row, (int)repeats.get(0));}
            }
        }
    }

    /**
     * Función creda para revisar si el número definido es el máximo de su fila numérica
     * pero no es máximo de su columna numérica. Revisa también que el máximo esté repetido
     * en su fila
     * @param number
     * @param vector
     * @param column
     * @param position
     * @return
     */
    private boolean checkerMaxRowNOMaxColumn(int number, int[] vector, int column, int position){
        return number == maxRow(vector, position) && 
        number != maxColumn(column) &&
         getRepeatsRow(vector, maxRow(vector, position)).size() > 1;
    }

    /**
     * Función creada para revisar si el número definido es el máximo de su columna numérica
     * pero no es máximo de su fila numérica. Revisa que el máximo esté repetido en su columna
     * @param number
     * @param vector
     * @param column
     * @param position
     * @return
     */
    private boolean checkerMaxColumnNOMaxRow(int number, int[] vector, int column, int position){
        return number == maxColumn(column) && 
        number != maxRow(vector, position) && 
        getRepeatsColumn(column,maxColumn(column)).size() > 1;
    }

    /**
     * Funcion creada para revisar si en esta posición se puede robar
     * @param number
     * @param vector
     * @param column
     * @param position
     */
    private void checkerNumber(int number, int[] vector, int column, int position, boolean simulate){
        //En caso que no sea máximo ni de fila ni de columna 
        if(number != maxRow(vector, position) && number != maxColumn(column)){
            //En caso que no se esté simulando
            if(!simulate){stealPosition(position, column);}
            //En caso que si se esté simulando
            else{stealPositionSimulate(position, column);}
        }
        //En caso que sea el máximo de su fila pero no de su columna 
        else if(checkerMaxRowNOMaxColumn(number, vector, column, position)){
                     //En caso que si se esté simulando
                     if(simulate){checkerRepeatRow(vector, maxRow(vector, position), position,true);}
                     //En caso que no se esté simulando
                     else{checkerRepeatRow(vector, maxRow(vector, position), position,false);}
        }
        //En caso que sea máximo de columna pero no de fila
        else if(checkerMaxColumnNOMaxRow(number, vector, column, position)){
            //En caso que si se esté simulando
            if(simulate){checkerRepeatColumn(column, maxColumn(column), true);}
            //En caso que no se esté simulando
            else{checkerRepeatColumn(column, maxColumn(column),false);}
        }
    }

    /**
     * Funcion creada para revisar si en la posicion de la matriz se puede robar, y si es así robar
     * @param heights
     */
    private void canPosition(boolean simulate){
        //Recorrido por las filas de la matriz de robo
        for(int i = 0; i< this.matrix_robbery.length; i++){
            //Recorrido por las columnas de la matriz de robo
            for(int j = 0; j< this.matrix_robbery[0].length;j++){
                //Si la pila en la que queremos robar no está vacia
                if(this.matrix_robbery[i][j] > 1){
                    //Si estamos simulando la solución
                    if(simulate){
                        checkerNumber(this.matrix_robbery[i][j], this.matrix_robbery[i],j,i, true);
                    }
                    //Si no estamos simulando la solución
                    else{
                        checkerNumber(this.matrix_robbery[i][j], this.matrix_robbery[i],j,i, false);
                    }
                    
                }
            }
        }
    }
}