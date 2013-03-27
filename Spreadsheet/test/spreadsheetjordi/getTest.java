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
public class getTest {
    
    String cellInput;
    String value; 
    String theCell;
    String expected;
    Spreadsheet sheet;
    
    public getTest(String cellInput, String value, String theCell, String expected) {
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
            {null,null,"A1",""},
            {null,null,"ZX347",""},        
            {"A1","A string","A1","A string"},
            {"A1","A different string","A1","A different string"}, 
            {"A1","","A1",""},            
            {"A1","First","A1","First"},          
            {"X27","Second","X27","Second"},            
            {"ZX901","Third","ZX901","Third"},
//testThatNumericCellsAreIdentifiedAndStored
            {"A21","X99","A21","X99"},
            {"A21","14","A21","14"},
            {"A21"," 99 X","A21"," 99 X"},
            {"A21"," 1234 ","A21","1234"},
            {"A21"," ","A21"," "},    
 //PART II                
            {"B1"," =7","B1"," =7"}                 
                
        });
    }
  
   
    @Test
    public void multipleOperations(){
        sheet.put(cellInput, value);
        assertEquals(expected, sheet.get(theCell));    

    }    
    
    @Test
    public void reintroduceInputInCell(){
        sheet.put("A1", "Fourth");
        assertEquals("A1 after", "Fourth", sheet.get("A1"));  
    }   

}