package domain;
import java.util.ArrayList;

import java.lang.reflect.Array;

/**
 * Write a description of class CelulaGenesis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaGenesis extends CelulaEspecial
{
    /**
     * Constructor de la nueva célula (Célula Génesis)
     * @param automata
     * @param fila
     * @param columna
     */
    public CelulaGenesis(AutomataCelular automata, int fila, int columna){
        super(automata, fila, columna);
        setColor(color.magenta);
    }

    public boolean DemeVecinos(){
        boolean safe = true;
        ArrayList<int[]> posicion_vecinos = getPosicionVecinos();
        ArrayList<Elemento> vecinos = getVecinos();
        AutomataCelular automata = getAutomata();
        for(int i = 0; i< posicion_vecinos.size();i++){
            Elemento vecino = vecinos.get(i) ;
            if(vecino instanceof CelulaEspecial){
                safe = safe && true;
            }
            else{
                safe = safe && false;
            }
        }
        return safe;
    }


    private void CrearHijos(){
        ArrayList<int[]> posicion_vecinos = getPosicionVecinos();
        ArrayList<Elemento> vecinos = getVecinos();
        AutomataCelular automata = getAutomata();
        for(int i = 0; i< posicion_vecinos.size();i++){
            Elemento vecino = vecinos.get(i);
            int[] pos_vecinos = posicion_vecinos.get(i);
            if(vecino == null){
                CelulaEspecial nueva = new CelulaEspecial(automata, pos_vecinos[0], pos_vecinos[1]);
                automata.setElemento(pos_vecinos[0], pos_vecinos[1], nueva);
            }
        }
        }
    

    public void decida(){
        if(getEdad() >= 3){
            CrearHijos();
            estadoSiguiente = Ser.MUERTO;
        }  
    }
}
