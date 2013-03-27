/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author jnp2
 */
@RunWith (value=Parameterized.class)
public class isNotNumberTest {
    
    String st;
    Spreadsheet sheet;
    
    public isNotNumberTest(String st) {
        this.st = st;
    }
    
    @Before
    public void setUp(){  
        this.sheet = new Spreadsheet(); 
    }    
    
    @Parameterized.Parameters
    public static Collection <Object[]> getTestParameters(){
        
        return Arrays.asList(new  Object[][]{      
            {"aaa"},
            {"09"},
            {"-09"},
            {"-0"}             
        });
    }
  
   
    @Test
    public void multipleOperations(){
      assertFalse(sheet.isNumber(st));        
    }    

}