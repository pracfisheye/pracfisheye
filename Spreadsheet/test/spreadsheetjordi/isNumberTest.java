/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author jnp2
 */
@RunWith (value=Parameterized.class)
public class isNumberTest {
    
    String st;
    Spreadsheet sheet;
    
    public isNumberTest(String st) {
        this.st = st;
    }
    
    @Before
    public void setUp(){  
        this.sheet = new Spreadsheet(); 
    }    
    
    @Parameterized.Parameters
    public static Collection <Object[]> getTestParameters(){
        
        return Arrays.asList(new  Object[][]{      
            {"9"},
            {"-9"},
            {"99"},
            {"-9999760"},
            {"0"},
            {" -95555 "},
            {"-9"}               
        });
    }
  
   
    @Test
    public void multipleOperations(){
      assertTrue(sheet.isNumber(st));        
    }    

}