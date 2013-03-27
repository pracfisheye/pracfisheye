/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

/**
 *
 * @author jnp2
 */
public class CNumber extends Cell{
    
    public CNumber(String contents) {
        super.contents = contents;        
    }
    

    @Override
    public String evaluate(){
        return super.contents.trim();
    }
    
}
