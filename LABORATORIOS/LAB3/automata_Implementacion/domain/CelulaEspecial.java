package domain;

import java.util.ArrayList;


/**
 * Write a description of class CelulaEspecial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaEspecial extends Celula
{
    //isSola es una variable que dice si la celula especial esta acompañada  por celulas vivas o no, true si esta acompañada false de lo contrario 
    private boolean isSola = false;
    /**
     * Constructor for objects of class CelulaEspecial
     */
    public CelulaEspecial(AutomataCelular ac, int fila, int columna)
    {
        super(ac,fila,columna);
        setColor(color.green);
    }
    
    /**
     * Retorna los posibles lugares en donde la celula especial puede crear una celula normal y define si la celula eta acompañada o no
     * @return - posible - Arreglo que contiene las posible posiciones 
     */
    public ArrayList<int[]> posibles(){
        boolean safe = false;
        ArrayList<int[]> posible = new ArrayList<int[]>();
        AutomataCelular automata = getAutomata();
        for(int i=0;i<4;i++){
            Elemento vecino = getVecinos().get(i);
            int[] posicion = getPosicionVecinos().get(i);
            if(vecino instanceof Celula){
                if(vecino.isVivo() == false){
                    safe |= false;
                }
                else{
                    safe |= true;
                }
            }
            else if(vecino == null){
                posible.add(posicion);
            }
        }
        this.isSola = safe;
        return posible;
    }
    
    @Override
    /**
     * Mira si esta sola, dependiendo de esto genera una accion
     * Si esta sola genera una nueva celula normal que la acompañe solo si no esta rodeada de celulas muertas de lo contrario muere
     */
    public void decida(){
        ArrayList<int[]> posible = posibles();
        AutomataCelular automata = getAutomata();
        if(posible.size() == 0){
            if(isSola == false){
                estadoSiguiente = Ser.MUERTO;
            }
        }
        else{if(isSola == false){
                int[] posicion = posible.get(0);
                int fila = posicion[0];
                int columna = posicion[1];
                Celula nueva = new Celula(automata,fila,columna);
                automata.setElemento(fila,columna,nueva);
            }
        }
    }

}
