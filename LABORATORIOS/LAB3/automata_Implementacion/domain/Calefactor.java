package domain;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class Calefactor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Calefactor extends Ser implements Elemento
{
    protected Color color;
    private AutomataCelular automata;
    private int fila,columna;
    /**
     * Constructor for objects of class Calefactor
     */
    public Calefactor(AutomataCelular ac,int fila,int columna)
    {
        automata = ac;
        setColor(color.red);
        setEstado(Ser.VIVO);
        automata.setElemento(fila,columna,this);
    }
    
    public void setColor(Color  color){
        this.color = color;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    public int forma(){
        return Elemento.CUADRADA;
    }

    public void cambie(){
        cumple();
        if(getEdad()%2==0.0){
            setColor(color.red);
        }
        else{
            setColor(color.yellow);
        }
    }


}
