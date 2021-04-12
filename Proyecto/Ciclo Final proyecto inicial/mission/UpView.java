package mission;
import crates.*;
import shapes.*;

/**
 * Vista aerea de la bodega
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 29/03/2021
 */
public class UpView
{
    int x;
    int y;
    int fila;
    int columna;
    int [][] heigths;
    boolean isVisible;
    UpStack[][] stacks;
    int max;
    
    /**
     * Crea la vista aerea de una bodega con las medidas dadas y en la posicion dad
     * @param x - Posicion en el eje x de la vista
     * @param y - Posicion en le eje y  de la vista
     * @param heigths - Medidas de la bodega
     */
    public UpView(int x, int y, int fila, int columna){
        this.x = x;
        this.y = y;
        max = fila;
        this.fila = fila;
        this.columna = columna;
        int grew = new Rectangle().getSize();
        stacks = new UpStack[fila][columna];
        for(int i=0;i<fila;i++){
            for(int j=0;j<columna;j++){
                UpStack stack = new UpStack(x+j*grew,y+i*grew);
                stack.setLocate(new int[]{i,j});
                stacks[i][j] = stack;
            }
        }
    }
    
    /**
     * Crea la vista aerea de una bodega con medidas heigths en la posicion dada
     * @param x - Posicion en el eje x de la vista
     * @param y - Posicion en le eje y  de la vista
     * @param heigths - Medidas de la bodega
     */
    public UpView(int x, int y, int[][] heigths){
        this(x,y,heigths.length,heigths[0].length);
        this.heigths = heigths;
        int lenA = heigths.length;
        int grew = new Rectangle().getSize();
        int lenB = heigths[0].length;
        for(int i=0;i<lenA;i++){
            for(int j=0;j<lenB;j++){
                int crates = heigths[i][j];
                for(int k=0;k<crates;k++){
                    stacks[i][j].addCrate("Normal");
                }
            }
        }
    }
    
    public void makeVisible(){
        for(UpStack[] array:stacks){
            for(UpStack stack:array){
                stack.makeVisible();
            }
        }
        isVisible = true;
    }
    
    public void makeInvisible(){
        isVisible = false;
        for(UpStack[] array:stacks){
            for(UpStack stack:array){
                stack.makeInvisible();
            }
        }
    }
    
    /**
     * Mueve la vista una distancia d verticalmente
     * @param d - Distancia a mover la vista
     */
    public void moveVertical(int d){
        for(UpStack[] array:stacks){
            for(UpStack stack:array){
                stack.moveVertical(d);
            }
        } 
        y += d;
    }
    
    /**
     * Mueve la vista una distancia d horizontalmente
     * @param d - Distancia a mover la vista
     */
    public void moveHorizontal(int d){
        for(UpStack[] array:stacks){
            for(UpStack stack:array){
                stack.moveHorizontal(d);
            }
        } 
        x += d;
    }
    
    /**
     * Agrega a la pila indicada una nueva caja
     * @param fila - Posicion x en la matriz de pilas en donde esta la pila
     * @param columna - Posicion y en la matriz de pilas en donde esta la pila
     */
    public void store(int fila, int columna, String type){
        if(canStore(fila, columna, type)){
            stacks[fila][columna].addCrate(type);
        }
    }

    /**
     * Función creada para retornar si se puede meter la caja en la pila deseada
     * 
     * @param fila
     * @param columna
     * @param type
     * @return
     */
    public boolean canStore(int fila, int columna, String type){
        return stacks[fila][columna].canStore(type);
    }
    
    /**
     * Quita de la pila indicada la ultima caja agregada
     * @param fila - Posicion x en la matriz de pilas en donde esta la pila
     * @param columna - Posicion y en la matriz de pilas en donde esta la pila
     */
    public Crate steal(int fila, int columna){
        if(stacks[fila][columna].getLength() >=1){
            Crate crate = stacks[fila][columna].removeCrate();
            return crate;
        }
        return null;
    }
    
    /**
     * Vacia la pila indicada dejando una caja
     * @param fila - Posicion x en la matriz de pilas en donde esta la pila a vaciar
     * @param columna - Posicion y en la matriz de pilas en donde esta la pila a vaciar
     */
    public void removeStack(int fila,int columna){
        UpStack stack = stacks[fila][columna];
        while(stack.numCrates > 1){stack.removeCrate();}
    }
    
    public void setMax(int max){this.max = max;}
    public int getMax(){return this.max;}
    public UpStack[][] getStacks(){return stacks;}
    public int getFila(){return fila;}
    public int getColumna(){return columna;}
}