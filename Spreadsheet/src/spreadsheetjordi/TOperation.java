/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

/**
 *
 * @author jnp2
 */
public class TOperation extends Token{ 
    
    public TOperation(String expresion){
        super.contents = expresion;
    }
    
    public int priority(){
        if(super.getContents().equals("+") || super.getContents().equals("-")){
            return 0;
        }   
        return 1;
    }
    
    public String getOperation(){
        return super.getContents();
    }
}
