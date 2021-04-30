package domain;
import java.util.ArrayList;
import java.awt.Color;
/**
 * Write a description of class CelulaConway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaConway extends Celula
{

    public CelulaConway(AutomataCelular automata, int fila, int columna)
    {
        super(automata, fila, columna);
        vecinos();
    }

    public int forma(){
        return CUADRADA;
    }
    
    private void diagonales_Oriente(ArrayList<Elemento> vecinos, ArrayList<int[]> posicionVecinos){
        if(columna < automata.getLongitud()-1 && fila > 0){
            Elemento NorOriente = automata.getElemento(fila-1, columna+1);
            posicionVecinos = posicionVecinos(vecinos, NorOriente, posicionVecinos, fila-1, columna+1);
        }
        if(columna < automata.getLongitud()-1 && fila < automata.getLongitud()-1){
            Elemento SurOriente = automata.getElemento(fila+1,columna+1);
            posicionVecinos = posicionVecinos(vecinos, SurOriente, posicionVecinos, fila+1, columna+1);
        }
    }

    private void diagonales_Occidente(ArrayList<Elemento> vecinos, ArrayList<int[]> posicionVecinos){
        if(columna > 0 && fila >0){
            Elemento NorOccidente = automata.getElemento(fila-1,columna-1);
            posicionVecinos = posicionVecinos(vecinos, NorOccidente, posicionVecinos, fila-1, columna-1);
        }
        if(fila < automata.getLongitud()-1 && columna > 0){
            Elemento SurOccidente = automata.getElemento(fila+1,columna-1);
            posicionVecinos = posicionVecinos(vecinos, SurOccidente, posicionVecinos, fila+1, columna-1);
        }
    }

    protected void vecinos(){
        super.vecinos();
        diagonales_Occidente(vecinos,posicionVecinos);
        diagonales_Oriente(vecinos,posicionVecinos);
    }


    private boolean checker_vivos_vecinos(){
        boolean safe = false;
        if(getVivos().size() == 3){
                safe = true;
            }
        return safe;
    }

    private void vivir(){
        estadoSiguiente = Ser.VIVO;
    }

    private void morir(){
        estadoSiguiente = Ser.MUERTO;
    }

    private boolean checker_2_vivos(){
        boolean safe = false;
        if(getVivos().size() == 2 || getVivos().size() == 3){
            safe = true;
        }
        return safe;
    }

    private boolean checker_3_vivos(){
        boolean safe = false;
        if(getVivos().size() < 2 || getVivos().size() > 3){
            safe = true;
        }
        return safe;
    }

    public ArrayList getVivos(){
        ArrayList<Elemento> vivos = new ArrayList<Elemento>();
        for(Elemento vecino: getVecinos()){
            if(vecino instanceof Celula && vecino.isVivo()){
                vivos.add(vecino);
            }
        }
        return vivos;
    }

    public  ArrayList checker_4_vivos(){
        ArrayList<int[]>posiciones_nulas = new ArrayList<int[]>();
        if(getVecinos().size() > 0){
            for(int i = 0; i< getVecinos().size();i++){
                int[] posicion = getPosicionVecinos().get(i);
                if(getVecinos().get(i) == null){
                    posiciones_nulas.add(posicion);
                }
            }
        }
        return posiciones_nulas;
    }

    private int[] revisionVecindario(ArrayList<int[]> posiciones_nulas){
        boolean safe = false;
        ArrayList<int[]> posibles = new ArrayList<int[]>();
        for(int[] posicion: posiciones_nulas){
            CelulaConway auxiliar = new CelulaConway(automata,posicion[0],posicion[1]);
            ArrayList<int[]> vivos = auxiliar.getVivos();
            if(vivos.size() >= 3){
                safe |= true;
                posibles.add(posicion);
            }
            else{
                safe |= false;
            }
            automata.setElemento(posicion[0],posicion[1],null);
        }
        if(posibles.size()>0){
            return posibles.get(0);
        }
        else{return null;}
    }

    @Override
    public void decida(){
        if(checker_vivos_vecinos() && !isVivo()){
            vivir();
        }
        if(checker_2_vivos() && isVivo()){
            vivir();
        }
        if(checker_3_vivos() && isVivo()){
            morir();
        }
        int[] posible = revisionVecindario(checker_4_vivos());
        if(posible != null){
            CelulaConway nueva = new CelulaConway(automata,posible[0],posible[1]);
        }
    }
   
}
