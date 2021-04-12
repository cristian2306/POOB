package crates;
import mission.*;
import javax.swing.JOptionPane;
import shapes.*;

/**
 * Representacion de la caja que esta en el almacen
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version Ciclo FINAL
 */
public abstract class Crate extends Rectangle
{
    int[] locate;
    StackCrates stack;


    //Nueva variable requerida, pues ya no solo existe la caja normal si no 5 tipos de cajas más
    protected String type;

    /**
     * Constructor de la caja
     * @param x - Posicion inicial de la caja en el eje x
     * @param y - Posicion inicial de la caja en el eje y
     */
    public Crate()  
    {
        super();
        //Ahí se cambia el tipo por el que pidió el usuario solo si está disponible
    }

    /**
     * Función que retorna la posición X de la caja
     * @return posición X
     */
    public int getX()
    {
        return xPosition;
    }

    /**
     * Función que retorna la posición Y de la caja
     * @return posición Y
     */
    public int getY(){
        return yPosition;
    }
    
    /**
     * Función que retorna el tipo de la caja
     * @return tipo de caja
     */
    public String getType(){
        return this.type;
    }

    /**
     * función que permite meter una caja en el stack dado. Se actualiza tanto en la matriz 
     * numérica como en el simulador
     * @param locate
     * @param position
     * @param stack
     * @return
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        boolean safe = false;
        this.stack = stack;
        this.locate = locate;
        setPosition(position[0],position[1]);
        if(stack.getLength() > 0){
            Crate checker_crate = stack.getCrates().lastElement();
            //Cuando la caja de debajo si es delicada
            if(checker_crate.getType().equals("Delicate")){
                if(isVisible){JOptionPane.showMessageDialog(null, "No puede haber cajas encima de una caja delicada", "Cuidado", JOptionPane.WARNING_MESSAGE);}
            }
            //Cuando la caja de debajo no es delicada
            else{
                safe = true;
            }
        }
        else{
            safe = true;
        }
        return safe;
    }

    public boolean steal(){
        return true;

    }

    /**
     * Función creada para cambiarle el color a la caja respecto a su tipo
     * normal --> blue
     * delicate --> lightGray
     * rebel --> orange
     * frost --> white
     * safe --> verde
     * heavy --> darkGray
     * @param type
     */
    protected void change_Color_type(String type){
        if(this.type.equals("normal")){
            changeColor("blue");
        }
        else if(this.type.equals("delicate")){
            changeColor("magenta");
        }
        else if(this.type.equals("rebel")){
            changeColor("red");
        }
        else if(this.type.equals("frost")){
            changeColor("white");
        }
        else if(this.type.equals("safe")){
            changeColor("green");
        }
        else{
            changeColor("darkGray");
        }
    }
    /**
     * Función generada para revisar que el tipo de caja que quiera el usuario
     * esté entre las que se pueden crear
     * @param type_Check
     * @return --> boolean answer
     */
    private boolean checkerTypes(String type_Check){
        boolean answer = false;
        String[] Types = new String[]{"normal","delicate","rebel","frost","safe","heavy"};
        for(String type: Types){
            if(type_Check.equals(type)){
                return true;
            }
        }

        return answer;
    }

    /**
     * Función que cambia el tipo de caja actual por el que desee el usuario o
     * se tenga como parámetro
     * @param new_Type
     */
    protected void setType(String new_Type){
        //Revisa si el nuevo tipo está disponible
        if(checkerTypes(new_Type)){
            this.type = new_Type;
        }
        //Si no, se manda un mensaje de error, aunque es mejor hacerlo con throws
        //Revisar teoría de throws y errores para hacerlo bien
        else{
            JOptionPane.showMessageDialog(null, "Tipo de caja no disponible para crear en el simulador", "CUIDADO", JOptionPane.WARNING_MESSAGE);
        }

    }
    /**
     * Funcion que retorna la posicion (x,y) de la caja
     */
    public int[] getPosition(){
        return new int[]{getX(),getY()};
    }
    public void setPosition(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /**
     * Asigna a la caja una localizacion dentro de la zona en donde esta
     * @param locate - Pareja que representa la localizacion en donde esta la caja
     */
    public void setLocate(int[] locate){this.locate = locate;}

    /**
     * Función que retorna la locación de la caja
     * @return donde se encuentra la caja
     */
    public int[] getLocate(){return this.locate;}

    /**
     * Función que retorna el color de la caja
     * @return color de la caja
     */ 
    public String getColor(){return this.color;}
    
    /**
     * Funcion que asigna a la caja una pila, en donde se encuentra
     * @param stack- Pila en donde se encuentra la caja
     */
    public void setStack(StackCrates stack){this.stack=stack;}
}

