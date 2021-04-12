package mission;
import crates.*;
import shapes.*;

import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Representa la bodega
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 27/03/2021
 */
public class View
{
    //Posicion de la zona
    int x;
    int y;
    int numStacks;//Numero de pilas
    int max;//Altura maxima de la vista
    ArrayList<StackCrates> stacks;
    boolean isVisible;
    int min;
    /**
     * Crea una nueva zona en la posicion y medidas especificadas
     * @param x- Posicion en el eje x de la vista
     * @param y- Posicion en el eje y de la vista
     * @param pilas- Numero de pilas a agregar
     * @param crates - Arreglo de enteros que contiene el numero de cajas por columna
     */
    public View(int x,int y,int pilas){  
        this.x = x;
        this.y = y;
        max = 0;
        min = y;
        isVisible = false;
        stacks = new ArrayList<StackCrates>();
        int grew = new Rectangle().getSize();
        for(int i=0;i<pilas;i++){
            int move = i*grew;
            addStack(new StackCrates(x+(move),y));
            numStacks++;
        }
        refresh();
    }
    
    /**
     * Crea una nueva zona en la posicion y cajas especificadas
     * @param x- Posicion en el eje x de la vista
     * @param y- Posicion en el eje y de la vista
     * @param crates - Arreglo de enteros que contiene el numero de cajas por columna
     */
    public View(int x,int y,int[] crates){  
        this(x,y,crates.length);
        for(int i=0;i<crates.length;i++){
            for(int j=0;j<crates[i];j++){
                //Según ciclo 4, todas las creaciones automatizadas se harán con cajas normales
                //Así que la única manera de que hayan cajas diferentes es que las coloquen una por una
                store(i,"Normal");
            }
        }
        refresh();
    }

    /**
     * Función encargada de retornar el arreglo de pilas de la vista
     * @return
     */
    protected ArrayList getStacks(){
        return this.stacks;
    }

    /**
     * Refresca la vista, teniendo en cuenta la altura de la vista baja la imagen
     */
    private void refresh(){
        int grew = new Rectangle().getSize();
        max = getMaxStack().getMax();
        if(max > 1){
            for(StackCrates stack:stacks){
                int move = grew*(max-stack.getMax());
                stack.moveVertical(move);
                stack.setMax(max);
            }
        }
    }
    
    /**
     * Agrega la pila dada a la vista
     * @stack - Pila a agregar a la vista
     */
    private void addStack(StackCrates stack){
        stacks.add(stack);
    }
    
    /**
     * Vacia la pila de la posicion dada de la vista, dejando una caja
     * @column - Numero de la pila a vaciar
     */
    public void removeStack(int column){
        StackCrates stack = stacks.get(column-1);
        while(stack.numCrates()>1){
            stack.removeCrate();
        }
    }
    
    /**
     * Función generada para hacer visible la vista
     */
    public void makeVisible(){
        isVisible = true;
        for(StackCrates stack:stacks){
            stack.makeVisible();
        }
    }
    
    /**
     * Función generada para hacer invisible la vista
     */
    public void makeInvisible(){
        for(StackCrates stack:stacks){
            stack.makeInvisible();
        }
        isVisible = false;
    }
    
    /**
     * Función generada para mover verticalmente la vista tanto quiera el usuario
     * @param d
     */
    public void moveVertical(int d){
        for(StackCrates stack:stacks){
            stack.moveVertical(d);
        }
        y += d;
    }
   
    /**
     * Función generada para mover horizontalmente la vista tanto quiera el usuario
     * @param d
     */
    public void moveHorizontal(int d){
        for(StackCrates stack:stacks){
            stack.moveHorizontal(d);
        }
        x += d;
    }
    
    /**
     * Agrega a la pila indicada una nueva caja
     * @param columna- Numero de la pila a agregar la caja
     */
    public void store(int column, String type){
        StackCrates stack = stacks.get(column);
        stack.addCrate(type);
        refresh();
    }   
    
    /**
     * Roba de la pila indicada la ultima caja agregada
     * @param columna- Numero de la pila a robar
     */
    public void steal(int column){
        StackCrates stack = stacks.get(column);
        stack.removeCrate();
    }

    /**
     * Función que retorna la caja que esta siendo robada
     * @param column
     * @return
     */
    private Crate steal_return(int column){
        StackCrates stack = stacks.get(column);
        Crate crate = stack.removeCrate();
        return crate;
    }
    
    /**
     * Calcula la altura maxima de la vista teniendo en cuenta las pilas de la vista
     * @return - Pila mas alta
     */
    public StackCrates getMaxStack(){
        int max = 0;
        int j = 0;
        for(int i=0;i<stacks.size();i++){
            StackCrates stack = stacks.get(i); 
            if(stack.getMax()>max){
                max = stack.getMax();
                j = i;
            }
        }
        return stacks.get(j);
    }
    
    public int getMax(){return getMaxStack().getMax();}
    public int getMin(){return getMaxStack().getMin();}
    public void setMax(int max){this.max = max;}
    
}
