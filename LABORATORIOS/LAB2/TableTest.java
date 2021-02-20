import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Diego Gonzalez - Cristian Castellanos   ECI
 * @version 2021-1
 */
public class TableTest
{
 
    private Table table;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        Table table = new Table(new String[]{"",""});
    }
    
    
    /**
     * test generated to check if 
     * the table with the given attributes is created correctly
     */
    @Test    
    public void shouldPass(){
        //Create an array of attributes and convert them to a table
        String [] attributes = {"ATTRIBUTE1","ATTRIBUTE2"};
        Table courses=new Table(attributes);
        
        //Check that the two attributes are not the same
        assertFalse(attributes[0].equals(attributes[1]));
        
        //Check that the two elements are written in uppercase
        assertTrue(attributes[0].equals("ATTRIBUTE1"));
        assertTrue(attributes[1].equals("ATTRIBUTE2"));
    }
    
    
    /**
     * test generated to check if the table creation generates a fail
     * because it does not comply with the parameters given in the laboratory
     */
    @Test
    public void shouldFail(){
        //Create an array of attributes and convert them to a table
        String [] attributes = {"attribute1","attribute1"};
        Table courses=new Table(attributes);
        
        //Check that the two attributes are not the same
        assertFalse(attributes[0].equals(attributes[1]));
       
        
        //Check that the two elements are written in uppercase
        assertTrue(attributes[0].equals("ATTRIBUTE1"));
        assertTrue(attributes[1].equals("ATTRIBUTE1"));
    }
    
    
    /**
     * test made to generate an error on test compilation
     */
    @Test    
    public void shouldErr(){
        //Create an array of attributes and convert them to a table
        assertTrue(table.equals(table));
        
        
        }
    
    
        
    /**
     * test created to see if empty tables are created correctly
     */
    @Test
    public void shouldCreateAnEmptyTables(){
        String [] attributes={"SIGLA","NOMBRE"};
        Table courses=new Table(attributes);
        assertEquals(0,courses.size());
        assertArrayEquals(attributes,courses.attributes());
    }
    
    
    /**
     * test created to see if the tables are created correctly
     */
    @Test
    public void shouldCreateTables(){
        String [] attributes={"SIGLA","NOMBRE"};
        String [][] data={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        Table courses=new Table(attributes);
        courses.insert(data);
        assertEquals(2,courses.size());
        assertTrue(courses.in(data[0]));
        assertTrue(courses.in(data[1]));
    }
    
    
    /**
     * test created to see if when inserting things that are not tuples or
     * tuples that do not meet the conditions, they are not inserted
     */
    @Test
    public void shouldNotInsertBadTuples(){
        String [] attributes={"SIGLA","NOMBRE"};
        String [][] data={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        Table courses=new Table(attributes);
        courses.insert(data);
        
    }    
    
    
    /**
     * test created to see if the toString function of the table class 
     * creates the expected string of attributes and tuples
     */
    @Test
    public void shouldRepresentTableAsString(){
        String [] attributes={"first","second","third"};
        String [][] data={{"x","x","x"},{"b","c",""},{"b","f","g"},{"d","d","d"},{"a","b","x"},{"a","b","c"}};
        Table example=new Table(attributes);
        example.insert(data);
        String result="[FIRST,SECOND,THIRD)\n(x,x,x)\n(b,c,)\n(b,f,g)\n(d,d,d)\n(a,b,x)\n(a,b,c)\n";
        assertEquals(result,example.toString()); 
    }
    
    
    /**
     *test created to see if the desired element is selected. T
     *his is seen by putting the result in String, 
     *to see if the result is the same
     */
    @Test
    public void shouldSelection(){
        String[]attributes= {"SIGLA","NOMBRE"};
        String[][]data = {{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"},{"AYED","Algoritmos y estructuras de datos"}};
        table = new Table(attributes);
        table.insert(data);
        assertEquals("[SIGLA)\n(AYED,Algoritmos y estructuras de datos)\n",table.selection("SIGLA","AYED").toString());
    }
    
    
    /**
     * test created to see if it projects table attributes
     */
    @Test
    public void shouldProyection(){
        String[] attribute = {"SIGLA"};
        String[] attributes= {"SIGLA","NOMBRE"};
        String[][]data = {{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        table = new Table(attributes);
        table.insert(data);
        assertEquals("[SIGLA)\n(POOB)\n(MBDA)\n",table.proyection(attribute).toString());
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
