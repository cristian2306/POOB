package mission;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase para revisar el funcionamiento correcto de los métodos del MissionContest a partir
 * de pruebas unitarias
 *
 * @author  (Diego González - Cristian Castellanos)
 * @version (Ciclo Final)
 */
public class MissionContestTest
{
    private MissionContest missionContest;
    private int[][] proof_matrix = {{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        missionContest = new MissionContest();
    }

    @Test 
    /**
     * Test generado para revisar el método solve(), revisar si con la matriz
     * dada por el ICPC se roban las cajas correspondientes
     */
    public void SegunCGdeberiaSolucionar(){
        int possible_answer = missionContest.solve(proof_matrix);
        //Revisar de que está robando todas las posibles cajas
        assertTrue(9 == possible_answer);
        int[][] matrix = (int[][])missionContest.getRobbery();
        //Revisar que esté robando en las posiciones deseadas
        assertEquals((int[][])proof_matrix,new int[][]{{1,4,0,5,1},{1,1,2,0,1},{0,1,3,1,4},{0,1,0,3,1},{1,1,2,1,1}});
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
