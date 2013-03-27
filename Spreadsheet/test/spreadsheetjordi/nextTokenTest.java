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
public class nextTokenTest {
    
    String startToken;
    String firstTokenExpected; 
    String secondTokenExpected;
    TExpr tokenExpr;
    
    public nextTokenTest(String startToken, String firstTokenExpected, String secondTokenExpected) {
        this.startToken = startToken;
        this.firstTokenExpected = firstTokenExpected;      
        this.secondTokenExpected = secondTokenExpected;
        this.tokenExpr = new TExpr(startToken);

    }
       
    @Parameterized.Parameters
    public static Collection <Object[]> getTestParameters(){
        
        return Arrays.asList(new  Object[][]{     
            {"(7)","7","" },
            {"((((1))))","1",""},
            {"2*3","2","*3"},
            {"(71)","71",""},
            {"2*3*4","2","*3*4"},
            {"1+2+3","1","+2+3"},
            {"1+2*3","1","+2*3"},
            {"7*(2+3)*((((2+1))))","7","*(2+3)*((((2+1))))"},
            {"*(2+3)*((((2+1))))","*","(2+3)*((((2+1))))"}, 
            
            {"(2+3)*((((2+1))))","(2+3)","*((((2+1))))"},
            
            {"*((((2+1))))","*","((((2+1))))"},            
            {"((((2+1))))","2","+1"},  
            {"((2+1))","2","+1"},
            {"(2+1)","2","+1"},            
            {"2+1","2","+1"},   
            {"+1","+","1"},         
            {"1","1",""},
            
            {"7*","7","*"},
            {"7*)","7","*)"},
//To revise            
 //           {"(((((7))","((((7)","#Error"},
 //           {"(((((744))))))))","((((744)))))))",""}         
                
        });
    }    
   
    @Test
    public void multipleOperations(){
        Token result = tokenExpr.nextToken();
        assertEquals("Fail firstTokenExpected", firstTokenExpected, result.contents);
        assertEquals("Fail secondTokenExpected", secondTokenExpected, tokenExpr.contents);        
    }   
    
}