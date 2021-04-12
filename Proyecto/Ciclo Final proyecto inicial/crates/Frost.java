package crates;
import mission.*;
/**
 * Clase encargada de crear la caja frost (se cuenta en la matriz numérica pero no se debe mostrar en el simulador)
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Frost extends Crate
{
    public static final String COLOR="white";
    /**
     * Constructor de la caja Frost
     */
    public Frost()
    {
        //Se inicia la super clase
        super();
        this.type = "Frost";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
        makeVisible();
       
    }

    /**
     * Sobreescritura del método store de la superclase Crate, se encarga de retornar
     * TRUE en caso que se pueda meter a la pila deseada
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        return super.store(locate,position,stack);
    }

    /**
     * Sobreescritura del método makeVisible() de la clase Rectangle
     * que se encuentra en el paquete shapes.
     */
    public void makeVisible(){
        isVisible = false;
    }
}
