package domain;
import java.awt.Color;

/**
 * Write a description of class SensorVida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SensorVida extends Ser implements Elemento
{
    protected Color color;
    private AutomataCelular automata;
    private int fila,columna;

    public SensorVida(AutomataCelular automata,int fila, int columna){
        this.automata = automata;
        automata.setElemento(fila,columna,this);
        setColor(color.yellow);
        setEstado(Ser.VIVO);
    }

    public Color getColor(){return this.color;}
    public void setColor(Color color){
        this.color = color;
    }
    
    public int forma(){
        return Elemento.CUADRADA;
    }

    private void checkCount(AutomataCelular automata){
        int count_celulas = 0;
        int count_vivos = 0;
        int longitud = automata.getLongitud();
        for(int i = 0; i< longitud; i++){
            for(int j = 0; j< longitud; j++){
                //Revise que todos los elementos que se pidieron sean células y no otras cosas
                if(automata.getElemento(i, j) instanceof Celula){
                    count_celulas +=1;
                    //Revisar en todos los elementos de la matriz, si hay alguno vivo
                    if(automata.getElemento(i, j).isVivo()){
                        count_vivos +=1;
                    }
                }
            }
        }
        if(count_celulas == count_vivos){
            //Caso donde todas las células están vivas
            setColor(Color.green);
        }
        else if(count_vivos == 0){
            setColor(Color.red);
        }
        else{
            setColor(Color.yellow);
        }
    }
    
    public void decida(){
        checkCount(automata);
    }
}
