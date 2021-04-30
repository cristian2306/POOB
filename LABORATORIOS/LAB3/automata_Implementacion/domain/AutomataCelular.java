package domain;
import java.io.File;
import java.util.*;


/*No olviden adicionar la documentacion*/
public class AutomataCelular{
    static private int LONGITUD=30;
    private Elemento[][] automata;
    private String sensor = "yellow";
    
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
    }

    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento[][] getAutomata(){return this.automata;}
    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }
    
    public String getSensor(){
        return this.sensor;
    }

    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public void algunosElementos(){
        SensorVida sensor_1 = new SensorVida(this,15,29);
        SensorVida sensor_2 = new SensorVida(this,16,29);
        SensorVida sensor_3 = new SensorVida(this,15,28);
        SensorVida sensor_4 = new SensorVida(this,16,28);
        Celula muerta = new Celula(this, 5,6);
        setElemento(5,4,muerta);
        setElemento(4,5,muerta);
        ticTac();
        ticTac();
        ticTac();
        Celula indiana = new Celula(this, 1,1);
        Celula celula_007 = new Celula(this,2,2);
        Celula agamenon = new CelulaEspecial(this,5,5);
        Celula venus = new CelulaEspecial(this,10,10);
        Calefactor suroeste = new Calefactor(this,0,29);
        Calefactor noreste = new Calefactor(this,29,29);
        Celula diego = new CelulaGenesis(this,20,20);
        Celula cristian = new CelulaGenesis(this,6,23);
        setElemento(0,29,suroeste);
        setElemento(29,29,noreste);
        CelulaConway con1 = new CelulaConway(this,14,15);
        CelulaConway con2 = new CelulaConway(this,14,16);
        prueba1();
        prueba2();
        prueba3();
    }
    
    private void prueba1(){
        CelulaConway john = new CelulaConway(this,5,27);
        CelulaConway horton = new CelulaConway(this,5,28);
    }
    
    private void prueba2(){
        CelulaConway con5 = new CelulaConway(this,29,0);
        CelulaConway con6 = new CelulaConway(this,29,1);
        CelulaConway con7 = new CelulaConway(this,28,1);
        CelulaConway con8 = new CelulaConway(this,28,0);
    }
    
    private void prueba3(){
        CelulaConway con9 = new CelulaConway(this,29,15);
        CelulaConway con10 = new CelulaConway(this,29,16);
        CelulaConway con11 = new CelulaConway(this,29,17);
    }
    
    public void ticTac(){
        for(Elemento[] array:automata){
            for(Elemento elemento:array){
                if(elemento != null){
                    elemento.decida();
                    elemento.cambie();
                }
            }
        }
    }

    /**
     * Toma del archivo dado su informacion (automata,sensor y longitud)
     * @param file Archivo en donde se encuentra la informacion
     */
    public static void Abrir(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);;
    }
    /**
     * Toma la informacion del automata y la guarda (automata,sensor y longitud)
     * @param file Archivo en donde se guarda la informacion
     */
    public static void Guardar(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
    }
    /**
     * Toma del archivo dado la informacion dada linea por linea
     * @param file Archivo en donde se encuentra la informacion
     */
    public static void Importar(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
    }
    /**
     * Toma la informacion del automata y la guarda (automata,sensor y longitud) en forma de texto
     * @param file Archivo en donde se guarda la informacion
     */
    public static void Exportar(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
    }
}
