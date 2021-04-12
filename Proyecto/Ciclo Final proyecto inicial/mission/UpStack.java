package mission;
import crates.*;

/**
 * Pila de cajas desde una vista aerea
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 29/03/2021
 */
public class UpStack extends StackCrates
{
    int[] locate;
    /**
     * Crea una pila de cajas visto desde el aire en la posicion indicada
     * @param x- Posicion x de la pila
     * @param y- Posicion y de la pila
     */
    public UpStack(int x, int y){super(x,y);}
    
    /**
     * Crea una pila de cajas con un numero de cajas en la posicion indicada
     * @param x- Posicion x de la pila
     * @param y- Posicion y de la pila
     */
    public UpStack(int x, int y, int crates){super(x,y,crates);}
    
    @Override
    /**
     * Agrega una caja a la pila
     */
    public void addCrate(String type){
        String typeClass = "crates."+type;
        try{
            Crate crate = (Crate) Class.forName(typeClass).newInstance();
            if(crate.store(new int[]{0,0},new int[]{x,y},this)){
                if(isVisible){crate.makeVisible();}//Hace visible la nueva caja si el simulador esta visible
                crates.push(crate);
                numCrates++;
                max = (max<numCrates)? numCrates : max;}
        }
        //Si el numero de cajas supera la altura, esta se actualiza;}
        catch(Exception e){e.printStackTrace();}
    }

    /**
     * Función creada para retornar TRUE si la caja se puede
     * meter en la posición deseada
     */
    public boolean canStore(String type){
        boolean safe = false;
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
     * Asigna a la pila una localizacion dentro de la vista
     */
    public void setLocate(int[] locate){this.locate = locate;}
}
