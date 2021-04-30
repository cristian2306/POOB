package domain;

import java.awt.Color;
import java.util.ArrayList;

/**Informacion sobre una célula<br>
<b>(automata,fila,columna,edad, estado, estadoSigiente, color)</b><br>
Las celulas conocenel automata en la que viven, la posición en la que están en ese autómata,su edad, su estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
 */
public class Celula extends Ser implements Elemento{
    protected char estadoSiguiente;
    protected Color color;
    protected AutomataCelular automata;
    protected int fila,columna;
    protected ArrayList<Elemento> vecinos;
    protected ArrayList<int[]> posicionVecinos;
    

    /**Crea una célula en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
    @param ac automata celular en el que se va a ubicar la nueva célula
    @param fila fila en el automata celular
    @param columna columna en el automata celula
     */
    public Celula(AutomataCelular ac,int fila, int columna){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        estadoSiguiente=Ser.VIVO;
        automata.setElemento(fila,columna,(Elemento)this);  
        ArrayList<Elemento> vecinos = new ArrayList<Elemento>();
        ArrayList<int[]> posicionVecinos = new ArrayList<int[]>();
        color=Color.blue;
    }

    /**Retorna la fila del automata en que se encuentra 
    @return 
     */
    public final int getFila(){
        return fila;
    }

    /**Retorna la columna del automata en que se encuentra
    @return 
     */
    public final int getColumma(){
        return columna;
    }

    public final AutomataCelular getAutomata(){
        return this.automata;
    }
    
    /**Retorna el color de  la célula
    @return 
     */
    public final Color getColor(){
        return color;
    }
    
    public final void setColor(Color color){this.color = color;}

    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(){
        if (getEdad()>=3){
            estadoSiguiente=Ser.MUERTO;
        }   
    }

    protected void Norte_Sur(ArrayList<Elemento> vecinos, ArrayList<int[]> posicionVecinos){
        if(fila > 0){
            Elemento norte = automata.getElemento(fila-1,columna);
            posicionVecinos = posicionVecinos(vecinos,norte,posicionVecinos,fila-1,columna);
        }
        if(fila < automata.getLongitud()-1){
            Elemento sur = automata.getElemento(fila+1,columna);
            posicionVecinos = posicionVecinos(vecinos,sur,posicionVecinos,fila+1,columna);
        }
        
    }

    protected void Oriente_Occidente(ArrayList<Elemento> vecinos, ArrayList<int[]> posicionVecinos){
        if(columna  < automata.getLongitud()-1){
            Elemento oriente = automata.getElemento(fila,columna+1);
            posicionVecinos = posicionVecinos(vecinos,oriente,posicionVecinos,fila,columna+1);
        }
        if(columna > 0){
            Elemento occidente = automata.getElemento(fila,columna-1);
            posicionVecinos = posicionVecinos(vecinos,occidente,posicionVecinos,fila,columna-1);
        }
    }
    
    /**
     * Mira cuales son los vecinos de la celula y los asigna a la variable vecinos
     */
    protected void vecinos(){
        ArrayList<Elemento> vecinos = new ArrayList<Elemento>();
        ArrayList<int[]> posicionVecinos = new ArrayList<int[]>();
        Norte_Sur(vecinos, posicionVecinos);
        Oriente_Occidente(vecinos, posicionVecinos);
        this.vecinos = vecinos;
        this.posicionVecinos = posicionVecinos;
    }
    
    protected ArrayList<int[]> posicionVecinos(ArrayList<Elemento> vecinos,Elemento vecino, ArrayList<int[]> posiciones,int fila,int columna){
        vecinos.add(vecino);
        posiciones.add(new int[]{fila,columna});
        return posiciones;
    }
    
    public ArrayList<Elemento> getVecinos(){
        vecinos();
        return this.vecinos;
    }
    
    public ArrayList<int[]> getPosicionVecinos(){
        return this.posicionVecinos;
    }

    /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        cumple();
        estado = estadoSiguiente;
        vecinos();
    }
}
