package crates;
import mission.*;

/**
 * Clase que permite crear la caja delicada
 * 
 * @author (Diego González -  Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Delicate extends Crate
{
    public static final String COLOR="magenta";
    /**
     * Constructor for objects of class SafeCrate
     */
    public Delicate()
    {
        //Se inicia la super clase
        super();
        this.type = "Delicate";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }

    /**
     * Sobreescritura del método store de la superclase, retorna
     * true si puede meter la caja en esa pila.  
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        return super.store(locate,position,stack);
        
    }
}
