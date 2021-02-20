

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RelationalCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RelationalCalculatorTest
{
    private RelationalCalculator calculator;
    private String[] attributes;
    private String [][] data;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        calculator = new RelationalCalculator();
        attributes= new String[]{"SIGLA","NOMBRE"};
        data= new String[][]{{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        calculator.add(attributes,data);
    }
    
    @Test
    public void shouldCreate(){
        RelationalCalculator calculator1 = new RelationalCalculator();
        assertTrue(calculator1.getTables().empty());
    }
    
    @Test
    public void shouldNotCreate(){
        assertTrue(calculator.getTables().empty());       
    }
    
    @Test
    public void shouldAdd(){
        calculator.add(attributes);
        assertFalse(calculator.getTables().empty());
    }
    
    @Test
    public void shouldNotAdd(){
        String [] attributes2={"SIGLA","NOMBRE"};
        String [][] data2={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        calculator.add(attributes2,data2);
        assertFalse(calculator.getTables().get(0).toString().equals(calculator.getTables().get(1).toString()));
    } 
    
    @Test
    public void shouldConsult(){
        Table table = new Table(attributes);
        table.insert(data);
        assertEquals(table.toString(),calculator.consult());
    }
    
    
    @Test
    public void shouldNotConsult(){
        Table table = new Table(attributes);
        assertEquals(table.toString(),calculator.consult());
    }
    
    @Test
    public void shouldInsert(){
        String[][] data = {{"AYED","Algoritmos y estructuras de datos"}} ;
        calculator.insert(data);
        assertEquals(calculator.consult(),"[SIGLA,NOMBRE)\n(POOB,Programacion orientada a objetos)\n(MBDA, Modelos y bases de datos)\n(AYED,Algoritmos y estructuras de datos)");
    }
    
    @Test
    public void shouldSelect(){
        String[][] data  = {{"AYED","Algoritmos y estructuras de datos"}};
        calculator.insert(data);
        assertEquals("[SIGLA)\n(AYED,Algoritmos y estructuras de datos)\n",calculator.select("SIGLA","AYED"));
    }
    
    @Test
    public void shouldProyect(){
        String[] attributes = {"SIGLA"};
        assertEquals("[SIGLA)\n(POOB)\n(MBDA)\n",calculator.proyect(attributes));
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
