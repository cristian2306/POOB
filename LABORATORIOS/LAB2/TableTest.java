import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author   ECI
 * @version 2021-1
 */
public class TableTest
{
 
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }
 
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

    @Test    
    public void shouldErr(){
        //Create an array of attributes and convert them to a table
        String [] attributes = {"",""};
        Table courses=new Table(attributes);
        
        //REVISAR LA FUNCION assertThrows
        
        
        }
    

    @Test
    public void shouldCreateAnEmptyTables(){
        String [] attributes={"SIGLA","NOMBRE"};
        Table courses=new Table(attributes);
        assertEquals(0,courses.size());
        assertArrayEquals(attributes,courses.attributes());
    }
    
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

    @Test
    public void shouldNotInsertBadTuples(){
        String [] attributes={"SIGLA","NOMBRE"};
        String [][] data={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        Table courses=new Table(attributes);
        courses.insert(data);
        
    }    
        
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
