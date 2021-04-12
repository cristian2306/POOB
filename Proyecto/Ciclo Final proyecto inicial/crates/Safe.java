package crates;
/**
 * Clase encargada de la creación de la caja segura
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Safe extends Crate
{
    public static final String COLOR="green";
    /**
     * Constructor de la caja segura
     */
    public Safe()
    {
        //Se inicia la super clase
        super();
        this.type = "Safe";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }

    /**
     * Sobreescritura del método steal de la superclase Crate. se retorna
     * FALSE debido a que esta caja no puede ser robada
     */
    public boolean steal(){
        return false;
    }
}
