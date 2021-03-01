import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;  

import jdk.jfr.Timestamp;

/**
 * The test class MissionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MissionTest
{
    private int[][] heights;
    private int row = 3;
    private int column = 3;
    private Mission mission_1 = new Mission(row,column);

    @Before
    public void setUp(){
        mission_1 = new Mission(row,column);
    }

    @Test
    /**
     * Test generated to see if the boxes are correctly inserted in the warehouse
     */
    public void segunCGshouldStore(){
        int[][] proofArray = {{2,0,0},{0,2,0},{0,0,2}};
        mission_1.store(1,1);
        mission_1.store(1,1);
        mission_1.store(2,2);
        mission_1.store(2,2);
        mission_1.store(3,3);
        mission_1.store(3,3);
        assertEquals(proofArray ,mission_1.warehouse());
    }

    @Test 
    /**
     * Test generated to see if the boxes are inserted correctly in the warehouse,
     * but the store function now has an array of integers as a parameter
     */
    public void segunCGshouldStorewithArrays(){
        int[][] proofArray = {{1,0,0},{0,1,0},{0,0,1}};
        for(int index = 1; index<=3; index++){
            int[] proof = {index,index};
            mission_1.store(proof);
        }
        assertEquals(proofArray, mission_1.warehouse());
    }

    @Test 
    /**
     * Test generated to see if the boxes are not inserted, because  
     * not comply with the fact that the user starts counting from 1, not from 0
     */
    public void segunCGshouldNotStore(){
        mission_1.store(0,0);
        assertTrue(mission_1.warehouse()[0][0] == 3);
        fail();
    }

    @Test 
    /**
     * Test generated to see if the boxes are not inserted, because 
     * they do not comply with the fact that the user starts counting from 1, not from 0. 
     * The store function receives an array of integers as parameters
     */
    public void segunCGshouldNotStorewithARRAYS(){
        for(int index = 0; index<=3; index++){
            int[] proof = {0,index};
            mission_1.store(proof);
        }
        assertTrue(mission_1.warehouse()[0][0] == 1);
    }

    @Test 
    /**
     * Test generated to see if the robbery plan correctly copies the array of values ​​from warehouse
     */
    public void segunCGshouldCopy(){
        mission_1.store(1,1);
        mission_1.store(2,2);
        mission_1.store(3,3);
        mission_1.copy();
        assertEquals(mission_1.warehouse(),mission_1.layout());
    }

    @Test 
    /**
     * Test generated to see if the Robbery class is correctly stealing the boxes in the robbery plan
     */
    public void segunCGshouldSteal(){
        for(int index = 1; index <= 2; index++){
            mission_1.store(1,1);
        }
        mission_1.copy();
        mission_1.steal(1,1);
        assertTrue(mission_1.layout()[0][0] == 1);
    }

    @Test 
    /**
     * Test generated to see if the Robbery class is correctly stealing the boxes in the robbery plan. 
     * The steal function receives an array of integers as parameters
     */
    public void segunCGshouldStealwithARRAYS(){
        int[] positions = {1,1};
        mission_1.store(1,1);
        mission_1.copy();
        mission_1.steal(positions);
        assertTrue(mission_1.layout()[0][0] == 0);

    }

    @Test 
    /**
     * Test generated to check if it is stolen in an invalid position, it does not steal
     */
    public void segunCGshouldnotSteal(){
        mission_1.store(1,1);
        mission_1.store(1,1);
        mission_1.steal(0,0);
        assertTrue(mission_1.layout()[1][1] == 1);
    }

    @Test 
    /**
     * Test generated to check if you steal in an invalid position, it does not steal. 
     * the steal function receives as a parameter an array of integers
     */
    public void segunCGshouldnotStealwithARRAYS(){
        int[] positions = {0,0};
        mission_1.store(1,1);
        mission_1.copy();
        mission_1.steal(positions);
        assertTrue(mission_1.layout()[0][0] == 0);

    }

    @Test
    /**
     * Test generated to check if the Return function returns the last stolen box
     */
    public void segunCGshouldReturn(){
        mission_1.store(1,1);
        mission_1.copy();
        mission_1.steal(1,1);
        mission_1.Return();
        assertEquals(mission_1.layout(),mission_1.warehouse());
    }
}