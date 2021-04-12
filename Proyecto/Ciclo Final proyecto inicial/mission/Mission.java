package mission;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import crates.*;
import java.util.Stack;
/**
 * Un juego que simula el robo de una bodega
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 30/03/2021
 */
public class Mission
{
    Robbery robbery;
    Zone wareHouse;
    int length;
    int width;
    boolean isVisible;

    Stack<String>[][] matrix_types;

    //Actions
    private Stack<ArrayList<String>> before = new Stack<ArrayList<String>>();
   private Stack<ArrayList<String>> after = new Stack<ArrayList<String>>();
    /**
     * Crea una nueva mision con las medidas dadas
     * @param length - Longitud de la bodega a simular
     * @param width - Ancho de la bodega a simular
     */
    public Mission(int length, int width){
        this.length = length;
        this.width = width;
        wareHouse = new Zone(10,60,length,width);
        isVisible = false;
    }

    /**
     * Segundo constructor de la clase Mission, este tiene como parámetros los mismos que el primero
     * solo que se le agrega una matriz numérica para empezar de una vez el simulador y sus vistas en
     * vez de estar metiendo caja por caja
     * @param length
     * @param width
     * @param heights
     */
    public Mission(int length, int width, int[][] heights){
        this(length,width);
        create(heights);
    }
    
    
    /**
     * Función creada para crear las pilas de cajas y vistas
     * respecto a la matriz numérica dada por el usuario
     * @param heights
     */
    public void create(int[][] heights){
        for(int i = 0; i<heights.length;i++){
            for(int j = 0; j< heights[0].length;j++){
                //Según ciclo 4, si se necesita crear vistas y demás
                //entonces las cajas a crear serán de tipo normal
                Store_All_box(i+1, j+1, heights[i][j]);
            }
        }
    }

    /**
     * Función creada para retornar la zona a la que pertenece la bodega
     * @return - zona de bodega
     */
    public Zone getZone(){
        return this.wareHouse;
    }
    
    /**
     * Agrega una caja en la bodega en la posicion indicada
     * @param row - Fila de la bodega en donde se ubica la caja
     * @param column - Columna de la bodega en donde se ubica la caja
     */
    public void store(int row, int column){
        if(row < 1 && column < 1 || row >=1 && column < 1 || row <1 && column >= 1){
            if(isVisible){
                JOptionPane.showMessageDialog(null, "No puedes meter cajas en esta posición", "Cuidado", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            wareHouse.store(row-1,column-1,"Normal");
            //Se le da la instrucción de guardar en el arraylist la acción 
            //que fue insertar en la posición una caja normal
            //beforeAction("Store",toString(row,column),"Normal");
            if(robbery!=null){
                if(robbery.getMaxZone() < wareHouse.getMaxZone()){robbery.moveVertical(10);}
                    robbery.store(row-1,column-1,"Normal");
            }
        }
        beforeAction("Store","Normal",toString(row-1,column-1),null);
    }
    
    /**
     * Función generada para insertar la cantidad de cajas dadas en la matriz numérica
     * en la posición deseada 
     * @param row
     * @param column
     * @param cant
     */
    private void Store_All_box(int row, int column, int cant){
        for(int i = 1; i<= cant; i++){
            store(row, column);
        }
    }
    
    /**
     * Agrega una caja en la bodega en la posicion indicada
     * @param crate- Pareja que da la posicion en donde poner la caja
     */
    public void store(int[] crate){
        store(crate[0],crate[1]);
    }

    /**
     * Función generada para insertar una caja en la posición deseada por el usuario
     * esta función tiene la diferencia de que la caja puede ser de otro tipo además de la normal
     * (normal, delicate, rebel, frost, safe, heavy) 
     * @param type
     * @param row
     * @param column
     */
    public void store(String type, int row, int column){
        int Row = row;
        int Column = column;
        if(type.equals("Rebel")){
            Row = column;
            Column = row;
        }
        wareHouse.store(Row-1,Column-1,type);
        //Se le da la instrucción de guardar en el arraylist la acción 
        //que fue insertar en la posición una caja normal
        //beforeAction("Store",toString(row,column),type);
        if(robbery!=null){
            if(robbery.getMaxZone() < wareHouse.getMaxZone()){robbery.moveVertical(10);}
            robbery.store(Row-1,Column-1,type);
        }
        beforeAction("Store",type,toString(Row-1,Column-1),null);    }

    /**
     * Crea una copia de la bodega en una zona de robo
    */
    public void copy(){
        //Si es simulador de robo ya está, se pone invisible para crear el otro actualizado
        if(robbery!=null){
            robbery.makeInvisible();
        }
        UpView newViews = wareHouse.getUpView();
        robbery = new Robbery(10,130,newViews);
        //Copia todas las vistas de la bodega al plan de robo
        robbery.moveVertical(35);
        if(isVisible){robbery.makeVisible();}

    }   
    
    /**
     * Roba la caja de la posicion asignada
     * @param row - Fila de la bodega en donde se roba la caja
     * @param column - Columna de la bodega en donde se roba la caja
     */
    public void steal(int row, int column){
        Crate crate = robbery.steal(row-1,column-1);
        //beforeAction("Steal",crate.getType(),toString(row-1,column-1),null);
        refresh();
    }

    /**
     * Función creada para llamar a la función steal(row,column), para que robe
     * en la posición deseada de acuerdo a las coordenadas que se encuentra 
     * como parámetro (crate)
     * @param crate
     */
    public void steal(int[] crate){
        robbery.steal(crate[0], crate[1]);
    }
    
    /**
     * Retorna la ultima robada a su posicion original en la zona de robo
     */
    public void Return(){
        int[] crate = robbery.Return();
        beforeAction("Return",null,toString(crate[0],crate[1]),null);
    }
    
    /**
     * Envia la caja desde una posicion en la zona de robo a otra
     * @param from - Ubicacion de la caja a cambiar
     * @param to - Destino de la caja a cambiar
     */
    public void arrange(int[] from, int[] to){
        robbery.arrange(from,to);
        beforeAction("Arrange",null,toString(from[0]-1,from[1]-1),toString(to[0]-1,to[1]-1));
    }
    
    /**
     * Numero de cajas robadas en la ultima accion del plan de robo
     * @return - Numero de cajas
     */
    public int stolen(){return robbery.stolen();}
    
    /**
     * Retorna los valores de la zona de bodega
     * @return - Valores de la zona de bodega
     */
    public int[][] warehouse(){return wareHouse.getHeigths();}
    
    /**
     * Retorna los valores de la zona de robo
     * @return valores de la zona de robo
     */
    public int[][] layout(){return robbery.getHeigths();}
    
    /**
     * Hace visible el simulador
     */
    public void makeVisible(){
        isVisible = true;
        wareHouse.makeVisible();
        if(robbery != null){robbery.makeVisible();}
    }
    
    /**
     * Hace invisible el simulador
     */
    public void makeInvisible(){
        wareHouse.makeVisible();
        if(robbery!=null){robbery.makeInvisible();}   
        isVisible = false;
    }
    
    /**
     * Finaliza el simulador
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Verifica si la accion recien hecha se puede hacer
     * @return - true si la accion se puede hacer de lo contrario else
     */
    public boolean ok(){
        boolean safe = true;
        safe &= equals(robbery.maxRow(layout()),wareHouse.maxRow(warehouse()));
        safe &= equals(robbery.maxColumns(layout()),wareHouse.maxColumns(warehouse()));
        safe &= equals(robbery.makeMaxUp(),wareHouse.makeMaxUp());
        return safe;
    }

    private void refresh(){
        if(ok()==false && isVisible){
            JOptionPane.showMessageDialog(null, "La camara te detecto", "CUIDADO", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Función generada para revisar si el arreglo de la bodega
     * es igual al arreglo de robo
     * @param stock
     * @param robbery
     * @return
     */
    private boolean equals(int[] stock,int[] robbery){
        boolean safe = true;
        for(int i =0;i<stock.length;i++){
            if(stock[i] != robbery[i]){
                safe = false;
                break;
            }
        }
        return safe;
    }

    /**
     * Funcion generada para revisar si la vista superior de la bodega es iguala a la de robo
     * @param stock valores de la bodega
     * @param robbery valores de la zona de robo
     * @return true si los valores son iguales, false de lo contrario
     */
    private boolean equals(int[][] stock,int[][] robbery){
        boolean safe = true;
        for(int i =0;i<stock.length;i++){
            for(int j =0;j<stock[0].length;j++){
                if(stock[i][j] != robbery[i][j]){
                    safe = false;
                    break;
                }
            }
        }
        return safe;
    }

    /**
     * Agrega la accion descrita al historial
     * @action Action ha agregar
     * @param ubicacion - Coordenadas de la caja robada, retornada o cambiada.
     * @param destino - Coordenadas a donde fue enviada la caja
     */
    private void beforeAction(String action,String Type, String[] ubicacion, String[] destino){
        ArrayList<String> move = new ArrayList<String>();
        move.add(action);
        move.add(Type);
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
     * Encargado de meter al arreglo de before lo que se hizo
     * @param action
     * @param Char
     */
    private void beforeAction(String action,char Char){
        ArrayList<String> move = new ArrayList<String>();
        move.add(action);
        move.add(String.valueOf(Char));
        before.push(move);
    }
    
    /**
     * Toma dos numeros y los retorna como un arreglo de String
     * @param i - Primer numero
     * @param j - Segundo numeros
     * @return array - Arreglo con dos numeros en forma String 
     */
    private String[] toString(int i, int j){
        String[] array = new String[2];
        array[0] = String.valueOf(i);
        array[1] = String.valueOf(j);
        return array;
    }
    
     // // /**
     // // * Ecoge o agranda la imagen del simulador una unidad
     // // * @param z - '+' si quiere agrandarla and '-' si quiere encogerla 
     // // */
    // // public void zoom(char z){
        // // wareHouse.zoom(z);
        // // beforeAction("Zoom",z);
        // // refresh(error());
    // // }
    
    /**
     * Deshace la accion que hizo el jugador
     */
    public void undo(){
        ArrayList<String> move = before.pop();
        String action = move.get(0);
        System.out.println("Undo");
        System.out.println(move.get(2));
        System.out.println(move.get(3));
        switch(action){
            case "Store":
                    wareHouse.stealZ(Integer.parseInt(move.get(2)),Integer.parseInt(move.get(3)));
                break;
            case "Steal":
                    Return();
                    before.pop();
                break;
            case "Return":
                    steal(Integer.parseInt(move.get(2)),Integer.parseInt(move.get(3)));
                    before.pop();
                break;
            case "Arrange":
                    int [] from = new int[2];
                    int[] to = new int[2];
                    from[0] = Integer.parseInt(move.get(4));
                    from[1] = Integer.parseInt(move.get(5));
                    to[0] = Integer.parseInt(move.get(2));
                    to[1] = Integer.parseInt(move.get(3));
                    arrange(from,to);
                    before.pop();
                break;
            // // case "Zoom":
                    // // if(move.get(1).charAt(0) == '+'){
                        // // zoom('-');
                    // // }
                    // // else{
                        // // zoom('+');
                    // // }
                    // // before.pop();
        }
        after.push(move);
    }
    
    /**
    * Rehace la accion que hizo el jugador
    */
    public void redo(){
        ArrayList<String> move = after.pop();
        String action = move.get(0);
        System.out.println(move.get(2));
        System.out.println(move.get(3));
        switch(action){
            case "Store":
                    store(Integer.parseInt(move.get(2)+1),Integer.parseInt(move.get(3))+1);
                break;
            case "Steal":
                    steal(Integer.parseInt(move.get(2)),Integer.parseInt(move.get(3)));
                break;
            case "Return":
                    Return();   
                break;
            case "Arrange":
                    int [] from = new int[2];
                    int[] to = new int[2];
                    from[0] = Integer.parseInt(move.get(2));
                    from[1] = Integer.parseInt(move.get(3));
                    to[0] = Integer.parseInt(move.get(4));
                    to[1] = Integer.parseInt(move.get(5));
                    arrange(from,to);
                break;
            // // case "Zoom":
                    // // zoom(move.get(1).charAt(0));
        }
    }
}
