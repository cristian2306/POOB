package mission;
import shapes.*;
import java.util.Stack;
import crates.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Zona de robo del simulador
 * 
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 30/03/2021
 */
public class Robbery extends Zone
{
    Stack<Crate> boxStole;
    int stolenCrates;
    UpView stoleView;
    private Stack<ArrayList<String>> before = new Stack<ArrayList<String>>();
    private Stack<ArrayList<String>> after = new Stack<ArrayList<String>>();
    
    /**
     * Crea una zona de robo en la posicion y medidas dadas
     * @param x - Posicion en el eje x de la vista
     * @param y - Posicion en el eje y  de la vista
     * @param fila- Numero de filas de la zona a crear
     * @param columna- Numero de columnas de la zona a crear
     */
    public Robbery(int x, int y, int fila, int columna){
        super(x,y,fila,columna);
        boxStole = new Stack<Crate>();
        stolenCrates = 0;
        stoleView = new UpView(x,y,new int[][]{{}});
    }
    
    /**
     * Crea una zona de robo en la posicion y medidas dadas
     * @param x - Posicion en el eje x de la vista
     * @param y - Posicion en le eje y  de la vista
     * @param heigths - Medidas de la bodega
     */
    public Robbery(int x,int y, int[][] heigths){
        this(x,y,heigths.length,heigths[0].length);
        for(int i =0;i<fila;i++){
            for(int j =0;j<columna;j++){
                int crates = heigths[i][j]; 
                for(int k=0;k<crates;k++){store(i,j,"Normal");}
            }
        }
    }

    /**
     * Crea una zona de robo con la vista Superior dada
     * @param x- Posicion en el eje x de la zona
     * @param y- Posicion en ele eje y de la zona
     * @param heigths - Cajas de la vista superior dada
     */
    public Robbery(int x, int y, UpView view){
        this(x,y,view.getFila(),view.getColumna());
        System.out.println(view.getFila());
        System.out.println(view.getColumna());  
        UpStack[][] stacks = view.getStacks();
        for(int i=0;i<stacks.length;i++){
            for(int j=0;j<stacks.length;j++){
                UpStack stack = stacks[i][j];
                Stack<Crate> crates = stack.getCrates();
                for(Crate crate:crates){
                    store(i,j,crate.getType());
                }
            }
        }
    }
    
    /**
     * Función creada para actualizar las vistas a partir de la matriz numérica y de los máximos tanto de fila como de columna
     */
    public void refresh(){
        super.refresh();
        stolenView(stolenCrates);
    }
    
    /**
     * Roba de la zona la caja en la posicion dada
     * @param row- Fila de la caja a robar
     * @param column- Columna de la caja a robar
     * @return - Caja robada
     */
    public Crate steal(int row, int column){
        sideViews[column].steal(row);
        frontViews[row].steal(column);
        Crate crate = upView.steal(row,column);
        //Si la caja se puede robar
        if(crate != null){
            if(heigths[row][column] > 0){
                boxStole.push(crate);
                stolenCrates ++;
                heigths[row][column] --;
            }
            else{
                JOptionPane.showMessageDialog(null, "La pila de cajas en esta posición se encuentra vacía", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        refresh();
        return crate;
    }

    /**
     * Función creada para robar en la matriz de robo según la posición que
     * requiera el usuario
     * @param crate
     */
    public void steal(int[] crate){steal(crate[0],crate[1]);}
    
    /**
     * Intercambia las cajas designadas
     * @param from- Posicion inicial de la caja
     * @param to - Destino de la caja
     */
    public void arrange(int[] from, int[] to){
        steal(new int[]{from[0]-1,from[1]-1});
        Crate crate = boxStole.pop();
        String old_type = crate.getType();
        stolenCrates --;
        store(new int[]{to[0]-1,to[1]-1}, old_type);
        refresh();
    }
    
    /**
     * Función creada para retornar la última caja robada
     */
    public int[] Return(){
        //Quita la última caja de las cajas robadas
        Crate crate = boxStole.pop();
        //Se le pide el tipo de caja a la que tenemos
        String old_type = crate.getType();
        //Se pide la locación anterior de la caja
        int[] position = crate.getLocate();
        //Se vuelve a insertar
        store(position[0],position[1], old_type);
        stolenCrates --;
        stolenView(stolenCrates);
        return position;
    }

    /**
     * Función encargada de retornar la pila de cajas que han sido robadas
     * @return
     */
    public Stack getBoxStole(){
        return this.boxStole;
    }
    
    /**
     * Crea una vista superior de acuerdo al numero de cajas robadas
     * @param stolenCrates - Numero de cajas robadas
     */
    private void stolenView(int stolenCrates){
        int fila = this.max+1;
        int grew = new Rectangle().getSize();
        int columna = (int)(Math.ceil((double)stolenCrates / (double)this.max));
        int[][] stolenValues = new int[fila][columna];
        for(int i=0;i<stolenCrates;i++){
            stolenValues[i/columna][i%columna] = 1;
        }
        if(stoleView != null){stoleView.makeInvisible();}
        stoleView = new UpView(x+(this.fila+2*this.columna+6)*grew,y+grew,stolenValues);
        if(isVisible){stoleView.makeVisible();}
    }

    /**
     * Retorna el numero de cajas robadas
     */
    public int stolen(){return stolenCrates;}
    
    public void makeVisible(){
        super.makeVisible();
        stoleView.makeVisible();
    }
    
    public void makeInvisible(){
        super.makeInvisible();
        stoleView.makeInvisible();
    }
    
    /**
     * Reinicia los valores de la zona de robo(cajas robadas) 
     */
    public void clear(){
        boxStole = new Stack<Crate>();
        stolenCrates = 0;
    }
    
}