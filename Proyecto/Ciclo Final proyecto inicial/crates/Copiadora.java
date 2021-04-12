
package crates;
import mission.*;

/**
 * Caja que revisa la anterior, y en caso que la anterior sea segura, entonces se coloca una segura 
 * y después se coloca la caja copiadora. En caso que la caja no sea segura, entonces se coloca una
 * normal y despúes se pne la copiadora.
 * 
 * @author (Diego González - Cristian Castellanos) 
 * @version (Ciclo FINAL)
 */
public class Copiadora extends Crate
{
    public static final String COLOR="lightGray";
    /**
     * Constructor de la clase caja Copiadora
     */
    public Copiadora(){
        //Se inicia la super clase
        super();
        this.type = "Copiadora";
        //Se le cambia el color respecto al tipo de caja
        changeColor(COLOR);
    }

    /**
     * Sobreescritura de método store con respecto a la superclase Crate
     * si debajo de la caja copiadora hay una caja segura, 
     */
    public boolean store(int[] locate, int[] position, StackCrates stack){
        //Revisamos la caja de abajo
        Crate checker_crate = stack.getCrates().lastElement();
        //En caso que no se pueda robar la caja
        if(checker_crate.getType().equals("Safe")){
            //Inserte  cajas segura (Es decir que la caja copiadora está entre dos cajas seguras)
            stack.addCrate("Safe");
        }
        else{
            //En caso que no sea una caja segura la de abajo, entonces se inserta la que
            //cogimos para revisar y añadimos una caja normal 
            stack.addCrate("Normal");
        }
        return super.store(locate,position,stack);
    }
}
