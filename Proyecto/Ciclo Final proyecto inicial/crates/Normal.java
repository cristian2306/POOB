package crates;
/**
 * Clase encargada de crear la caja normal
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Normal extends Crate
{
    public static final String COLOR="blue";
    /**
     * Constructor de la caja normal
     */
    public Normal()
    {
        //Se inicia la super clase
        super();
        this.type = "Normal";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }
}
