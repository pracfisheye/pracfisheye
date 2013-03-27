package spreadsheetjordi;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author jnp2
 */
@RunWith (value=Parameterized.class)
public class operationsTest {
    
    String cellInput;
    String value; 
    String theCell;
    String expected;
    Spreadsheet sheet;
    
    public operationsTest(String cellInput, String value, String theCell, String expected) {
        this.cellInput = cellInput;
        this.value = value;      
        this.theCell = theCell;
        this.expected = expected;

    }
    
    @Before
    public void setUp(){  
        this.sheet = new Spreadsheet(); 
    }    
    
    @Parameterized.Parameters
    public static Collection <Object[]> getTestParameters(){
        
        return Arrays.asList(new  Object[][]{      
//PART II
            {"A1","=(7)","A1","7"}, 
            {"A1","=((((1))))","A1","1"},
            {"A1","=2*3","A1","6"},
            {"A1","=(71)","A1","71"},
            {"A1","=2*3*4","A1","24"},
            {"A1","=1+2+3","A1","6"},
            {"A1","=1+2*3","A1","7"},
            
            {"A1","=(2+3)","A1","5"},  
              
            {"A1","=7*(2+3)","A1","35"},  
              
            {"A1","=7*(2+3)*((((2+1))))","A1","105"},
            {"A1","=(2+3)","A1","5"},
            {"A1","=7*","A1","#Error"},
            {"A1","=)","A1","#Error"},            
           {"A1","=7*)","A1","#Error"},
           
            {"A1","=(((((7))","A1","#Error"},
            {"A1","=(((((744))))))))","A1","#Error"}         
                
        });
    }    
   
    @Test
    public void multipleOperations(){
        sheet.put(cellInput, value);
        assertEquals(expected, sheet.get(theCell));    
    }   
    
}