package mission;
import crates.*;
import shapes.*;


import java.util.Stack;
import javax.swing.JOptionPane;
/**
 * Represetacion de una pila de cajas en el almacen
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 27/03/2021
 */
public class StackCrates{
    Stack<Crate> crates;
    int x;
    int y;
    int numCrates;
    int max;//Altura maxima de la pila
    boolean isVisible;
    String type;
    /**
     * Crea la pila de cajas en la posicion especificada
     * @param x - Posicion de la pila en el eje x
     * @param y - Posicion de la pila en el eje y
     */
    public StackCrates(int x, int y){
        crates = new Stack<Crate>();
        this.x = x;
        this.y = y;
        isVisible = false;
    }
    
    /**
     * Crea la pila de cajas con el numero especifico de cajas
     * @param crates - Numero de cajas con la que inicia la pila
     */
    public StackCrates(int x, int y, int crates){
        this(x,y);
        for(int i=0;i<crates;i++){
            //ACLARACION
            //Según ciclo 4, todas las creaciones automatizadas se harán con cajas normales
            //Así que la única manera de que hayan cajas diferentes es que las coloquen una por una
            addCrate("Normal");
        }
        this.max = crates;
    }

    /**
     * Función encargada de retornar la longitud de la pila
     * @return
     */
    public int getLength(){
        return crates.size();
    }
    
    /**
     * Metodo que agrega una caja a la pila
     */
    public void addCrate(String type){
        int grew = new Rectangle().getSize();
        String typeClass = "crates."+type;
        try{
            Crate crate = (Crate) Class.forName(typeClass).newInstance();
            if(crate.store(new int[]{0,0},new int[]{x,y},this)){
                crate.moveVertical(-grew*numCrates);
                if(isVisible){crate.makeVisible();}//Hace visible la nueva caja si el simulador esta visible
                crates.push(crate);
                if(max==numCrates){moveVertical(grew);}
                numCrates++;
                max = (max<numCrates)? numCrates : max;}
            else{
                if(isVisible && type.equals("Delicada")){JOptionPane.showMessageDialog(null, "No puede haber cajas encima de una caja delicada", "Cuidado", JOptionPane.WARNING_MESSAGE);}
            }
        }
        //Si el numero de cajas supera la altura, esta se actualiza;}
        catch(Exception e){e.printStackTrace();}
        //Aca debe ir un checker para revisar el tipo de caja y si es factible meterla ahí
    }

    /**
     * Función que me dice si la caja que deseo insertar, se puede meter en la pila
     * @param type
     * @return
     */
    public boolean canStore(String type){
        boolean safe = false;
        int grew = new Rectangle().getSize();
        String typeClass = "crates."+type;
        try{
            Crate crate = (Crate) Class.forName(typeClass).newInstance();
            if(crate.store(new int[]{0,0},new int[]{x,y},this)){
                safe = true;
            }
        }catch(Exception e){e.printStackTrace();}
        return safe;
    }

    /**
     * Metodo que remueve una caja de la pila
     */
    public Crate removeCrate(){
        Crate crate = crates.lastElement();
        if(crate.steal()){
            crate.makeInvisible();
            int grew = crate.getSize();
            numCrates -= 1;
            return crate;
        }
        else{
            JOptionPane.showMessageDialog(null, "La caja segura no se puede robar", "Cuidado", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    
    }
    
    public void makeVisible(){
        isVisible = true;
        for(Crate crate:crates){
            crate.makeVisible();
        }
    }
    
    public void makeInvisible(){
        for(Crate crate:crates){
            crate.makeInvisible();
        }
        isVisible = false;
    }
    
    /**
     * Función que retorna la pila de cajas
     * @return - pila de cajas
     */
    public Stack<Crate> getCrates(){
        return this.crates;
    }
    
    /**
     * Función que retorna el número de cajas de la pila
     * @return -  número de cajas de la pila
     */
    public int numCrates(){
        return this.numCrates;
    }
    
    /**
     * Metodo que mueve toda la pila una distancia d
     * @param d- Distancia a mover la pila verticalmente
     */
    public void moveVertical(int d){
        for(Crate crate:crates){
            crate.moveVertical(d);
        }
        y += d;
    }
    
    /**
     * Metodo que mueve toda la pila una distancia d
     * @param d- Distancia a mover la pila horizontalmente
     */
    public void moveHorizontal(int d){
        for(Crate crate:crates){
            crate.moveHorizontal(d);
        }
        x += d;
    }
    
    public void setMax(int max){this.max = max;}
    public int getMax(){return this.max;}
    public int getMin(){return this.y;}

}