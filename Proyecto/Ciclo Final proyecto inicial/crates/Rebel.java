package crates;
import mission.*;

/**
 * Clase encargada de la creación de la caja rebelde
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Rebel extends Crate
{
    public static final String COLOR="orange";
    /**
     * Constructor de caja rebelde
     */
    public Rebel()
    {
        //Se inicia la super clase
        super();
        this.type = "Rebel";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }

    /**
     * sobreescritura del método store de la superclase Crate. Retorna TRUE
     * en caso de poder insertar la caja en la pila dada
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        return super.store(new int[] {locate[1],locate[0]}, new int[] {position[1], position[0]},stack);
    }
}
