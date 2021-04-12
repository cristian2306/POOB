package mission;
import crates.*;
import java.util.Stack;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdk.jfr.Timestamp;

/**
 * Clase para revisar el funcionamiento correcto de los métodos de Mission a partir
 * de pruebas unitarias
 *
 * @author  (Diego González - Cristian Castellanos)
 * @version (Ciclo Final)
 */
public class MissionTest
{
    private Mission mission;
    private int length = 2;
    private int width = 2;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        mission = new Mission(length,width);
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja normal a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo
     */
    public void deberíaInsertarCajaNormal(){
        mission.store("Normal",1,1);
        int[][] warehouse = mission.warehouse();
        StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
        Stack stack_proof = StackCrates_proof.getCrates();
        Crate Crate_proof = (Crate)stack_proof.pop();
        String Final_proof = Crate_proof.getType();

        assertTrue(warehouse[0][0] == 1);
        assertTrue(Final_proof.equals("Normal"));
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja delicada a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo
     */
    public void deberiaInsertarCajaDelicada(){
        mission.store("Normal",1,1);
        mission.store("Delicate",1,1);
        int[][] warehouse = mission.warehouse();
        StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
        Stack stack_proof = StackCrates_proof.getCrates();
        Crate Crate_proof = (Crate)stack_proof.pop();
        String Final_proof = Crate_proof.getType();

        assertTrue(warehouse[0][0] == 2);
        assertTrue(Final_proof.equals("Delicate"));
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja rebelde a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo
     */
    public void deberiaInsertarCajaRebelde(){
            mission.store("Rebel",2,1);
            //Si le damos la posición 2,1 debería meter en 1,2
            int[][] warehouse = mission.warehouse();
            StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(1);
            Stack stack_proof = StackCrates_proof.getCrates();
            Crate Crate_proof = (Crate)stack_proof.pop();
            
            String Final_proof = Crate_proof.getType();
            assertTrue(Final_proof.equals("Rebel"));
            assertTrue(warehouse[0][1] == 1);
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja segura a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo. También se revisa
     * si no se está robando (Parámetro de ciclo 4)
     */
    public void deberiaInsertarCajaSegura(){
            mission.store("Safe",1,1);
            mission.copy();
            mission.steal(1,1);
            int[][] robbery = mission.warehouse();
            StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
            Stack stack_proof = StackCrates_proof.getCrates();
            Crate Crate_proof = (Crate)stack_proof.pop();
            String Final_proof = Crate_proof.getType();
            //Revisa de que la caja no se esté robando
            assertTrue(robbery[0][0] == 1);
            //Revisa que el tipo de caja sea segura
            assertTrue(Final_proof.equals("Safe"));
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja frost a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo. Esta caja no 
     * deberia ser visible en el simulador
     */
    public void deberiaInsertarCajaFrost(){
            mission.store("Frost",1,1);
            int[][] warehouse = mission.warehouse();
            StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
            Stack stack_proof = StackCrates_proof.getCrates();
            Crate Crate_proof = (Crate)stack_proof.pop();
            String Final_proof = Crate_proof.getType();
            //Revisa de que la caja no se esté robando
            assertTrue(warehouse[0][0] == 1);
            //Revisa que el tipo de caja sea segura
            assertTrue(Final_proof.equals("Frost"));
            assertTrue(Crate_proof.isVisible == false);
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja pesada a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo. Esta caja deberia
     * ser la primera en la pila que se quiera insertar
     */
    public void deberiaInsertarCajaHeavy(){
        mission.store("Heavy", 1, 1);
        int[][] warehouse = mission.warehouse();
        StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
        Stack stack_proof = StackCrates_proof.getCrates();
        Crate Crate_proof = (Crate)stack_proof.lastElement();
        String Final_proof = Crate_proof.getType();
        //Revisa que la caja sea de tipo Heavy
        assertTrue(Final_proof.equals("Heavy"));
        //Revisa que se haya metido correctamente
        assertTrue(warehouse[0][0] == 1);
        mission.store("Normal", 1, 1);
        mission.store("Heavy", 1, 1);
        //Revisar que no se haya metido la segunda caja heavy
        assertTrue(warehouse[0][0] == 2);
    }

    @Test
    /**
     * Test generado para revisar si se estaba insertando correctamente una caja normal a partir
     * del nuevo método store con parámetros de fila-columna y con parámetro tipo. Este tipo
     * de caja revisa la de abajo. Si es segura, la copiadora mete otra segura. En caso que
     * sea otra, mete una normal
     */
    public void deberiaInsertarCajaCopiadora(){
        mission.store("Normal",1,1);
        mission.store("Copiadora",1,1);
        int[][] warehouse = mission.warehouse();
        StackCrates StackCrates_proof = (StackCrates)mission.getZone().getSideViews()[0].getStacks().get(0);
        Stack stack_proof = StackCrates_proof.getCrates();
        Crate Crate_proof = (Crate)stack_proof.lastElement();
        String Final_proof = Crate_proof.getType();
        //Revisa que la caja es de tipo copiadora
        assertTrue(Final_proof.equals("Copiadora"));
        //Revisa que se esté metiendo la caja copiada (para este caso es Normal)
        //assertTrue(warehouse[0][0] ==3);
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
