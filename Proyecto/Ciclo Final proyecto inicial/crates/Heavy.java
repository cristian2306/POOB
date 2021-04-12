package crates;
import javax.swing.JOptionPane;
import mission.*;

/**
 * Clase encargada de crear la caja pesada
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo Final)
 */
public class Heavy extends Crate
{
    public static final String COLOR="darkGray";
    /**
     * Constructor de la caja pesada
     */
    public Heavy()
    {
        //Se inicia la super clase
        super();
        this.type = "Heavy";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }

    /**
     * Sobreescritura del método store de la superclase Crate. Revisa que 
     * la pila donde se quiere meter esté vacía, esto debido a que la caja
     * pesada, si se quiere meter, debe ser la primera de la pila
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        boolean safe = false;
        //Decimos que la caja si puede ser insertada
        if(stack.getLength()==0){
            safe = true;
        }
        else{
            //Le decimos que la caja no puede ser insertada
            safe = false;
            if(isVisible){
                JOptionPane.showMessageDialog(null, "La caja pesada no es la primera de la pila", "Cuidado", JOptionPane.WARNING_MESSAGE);
            }
        }
        return safe;
    }   
}
