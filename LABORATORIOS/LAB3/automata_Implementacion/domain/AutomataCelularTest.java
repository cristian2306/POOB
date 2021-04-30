package domain;
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The test class AutomataCelularTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AutomataCelularTest
{
    /**
     * Default constructor for test class AutomataCelularTest
     */
    public AutomataCelularTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    @Test
    public void deberiaCelulaNormal(){
        AutomataCelular automata = new AutomataCelular();
        Celula celula_1 = (Celula)automata.getElemento(1,1);
        Celula celula_2 = (Celula)automata.getElemento(2,2);
        automata.ticTac();
        assertEquals(1,celula_1.getEdad());
        assertEquals(1,celula_2.getEdad());
        automata.ticTac();
        assertEquals(2,celula_1.getEdad());
        assertEquals(2,celula_2.getEdad());
        automata.ticTac();
        assertEquals(3,celula_1.getEdad());
        assertEquals(3,celula_2.getEdad());
    }
    
    @Test
    public void deberiaCelulaEspecial(){
        AutomataCelular automata = new AutomataCelular();
        CelulaEspecial agamenon = (CelulaEspecial)automata.getElemento(5,5);
        CelulaEspecial venus = (CelulaEspecial)automata.getElemento(10,10);
        automata.ticTac();
        //Genera una nueva celula
        assertEquals(true,automata.getElemento(6,5).isVivo());
        assertEquals(1,agamenon.getEdad());
        assertEquals(1,venus.getEdad());
        automata.ticTac();
        assertEquals(2,agamenon.getEdad());
        assertEquals(2,venus.getEdad());
        automata.ticTac();
        assertEquals(3,agamenon.getEdad());
        assertEquals(3,venus.getEdad());
        automata.ticTac();
        automata.ticTac();
        //Muere cuando esta rodeada de celulas muertas.
        assertEquals(false,automata.getElemento(5,5).isVivo());
    }
    
    @Test
    public void noDeberiaCelulaEspecial(){
        AutomataCelular automata = new AutomataCelular();
        CelulaEspecial agamenon = (CelulaEspecial)automata.getElemento(5,5);
        CelulaEspecial venus = (CelulaEspecial)automata.getElemento(10,10);
        automata.ticTac();
        assertEquals(false,automata.getElemento(6,5).isVivo());
    }
    
    @Test
    public void deberiaCalefactor(){
        AutomataCelular automata = new AutomataCelular();
        Calefactor sureste = (Calefactor)automata.getElemento(29,29);
        Calefactor noreste = (Calefactor)automata.getElemento(0,29);
        automata.ticTac();
        assertEquals(sureste.getColor(),Color.yellow);
        assertEquals(noreste.getColor(),Color.yellow);
        automata.ticTac();
        assertEquals(sureste.getColor(),Color.red);
        assertEquals(noreste.getColor(),Color.red);
        automata.ticTac();
        assertEquals(sureste.getColor(),Color.yellow);
        assertEquals(noreste.getColor(),Color.yellow);
    }
    
    @Test
    public void noDeberiaCalefactor(){
        AutomataCelular automata = new AutomataCelular();
        Calefactor sureste = (Calefactor)automata.getElemento(29,29);
        Calefactor noreste = (Calefactor)automata.getElemento(0,29);
        automata.ticTac();
        assertEquals(sureste.getColor(),Color.blue);
        assertTrue(sureste.isVivo()==false);
    }
    
    @Test
    public void deberiaCelulaGenesis(){
        AutomataCelular automata = new AutomataCelular();
        CelulaGenesis genesis_1 = (CelulaGenesis)automata.getElemento(20,20);
        CelulaGenesis genesis_2 = (CelulaGenesis)automata.getElemento(6,23);
        for(int i = 0; i<=5;i++){
            automata.ticTac();
        }
        assertTrue(genesis_1.DemeVecinos());
        assertTrue(genesis_2.DemeVecinos());
    }

    @Test
    public void nodeberiaCelulaGenesis(){
        AutomataCelular automata = new AutomataCelular();
        CelulaGenesis genesis_1 = (CelulaGenesis)automata.getElemento(20,20);
        CelulaGenesis genesis_2 = (CelulaGenesis)automata.getElemento(6,23);
        for(int i = 0; i<=5;i++){
            automata.ticTac();
        }
        assertEquals(genesis_1.getColor(), Color.BLUE);
        assertEquals(genesis_2.getColor(), Color.BLUE);
    }
     
    @Test
    public void deberiaSensorVida(){
        AutomataCelular automata = new AutomataCelular();
        for(int i = 0; i<=18;i++){
            automata.ticTac();
        }
        for(int i = 15; i< 17; i++){
            for(int j = 28; j< 30; j++){
                SensorVida sensor = (SensorVida)automata.getElemento(i,j);
                assertEquals(sensor.getColor(), Color.RED);
            }
        }
    }


    @Test
    public void nodeberiaSensorVida(){
        AutomataCelular automata = new AutomataCelular();
        for(int i = 0; i<=18;i++){
            automata.ticTac();
        }
        for(int i = 15; i< 17; i++){
            for(int j = 28; j< 30; j++){
                SensorVida sensor = (SensorVida)automata.getElemento(i,j);
                assertEquals(sensor.getColor(), Color.GREEN);
            }
        }
    }
    
    @Test
    public void dosCelulasConFila(){
        AutomataCelular automata = new AutomataCelular();
        CelulaConway con1 = (CelulaConway)automata.getElemento(14,15);
        CelulaConway con2 = (CelulaConway)automata.getElemento(14,16);
        automata.ticTac();
        assertEquals(true,con1.isVivo());
        assertEquals(true,con2.isVivo());
        automata.ticTac();
        assertEquals(false,con1.isVivo());
        assertEquals(false,con2.isVivo());
        automata.ticTac();
        assertEquals(false,con1.isVivo());
        assertEquals(false,con2.isVivo());
    }
    
    @Test
    public void bloque(){
        AutomataCelular automata = new AutomataCelular();
        CelulaConway con1 = (CelulaConway)automata.getElemento(28,0);
        CelulaConway con2 = (CelulaConway)automata.getElemento(28,1);
        CelulaConway con3 = (CelulaConway)automata.getElemento(29,0);
        CelulaConway con4 = (CelulaConway)automata.getElemento(29,1);
        automata.ticTac();
        assertEquals(true,con1.isVivo());
        assertEquals(true,con2.isVivo());
        assertEquals(true,con3.isVivo());
        assertEquals(true,con4.isVivo());
        automata.ticTac();
        assertEquals(true,con1.isVivo());
        assertEquals(true,con2.isVivo());
        assertEquals(true,con3.isVivo());
        assertEquals(true,con4.isVivo());
        automata.ticTac();
        assertEquals(true,con1.isVivo());
        assertEquals(true,con2.isVivo());
        assertEquals(true,con3.isVivo());
        assertEquals(true,con4.isVivo());
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
