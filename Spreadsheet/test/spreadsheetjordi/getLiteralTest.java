/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class getLiteralTest {
    
    String cellInput;
    String value; 
    String theCell;
    String expected;
    Spreadsheet sheet;
    
    public getLiteralTest(String cellInput, String value, String theCell, String expected) {
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
            {"A21","Some string","A21","Some string"},
            {"A21"," 1234 ","A21"," 1234 "}, 
//PART II   
            {"A21","=7","A21","=7"},
            {"B1"," =7","B1"," =7"}         
                
        });
    }
  
   
    @Test
    public void multipleOperations(){
        sheet.put(cellInput, value);
        assertEquals(expected, sheet.getLiteral(theCell));    

    }    
  

}