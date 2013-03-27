/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

/**
 *
 * @author jnp2
 */
public class TNombre extends Token{   
    int value;
    
    public TNombre(String expresion){
        super.contents = expresion;  
        this.value = Integer.parseInt(expresion);  
    }
        
    public int getValue(){
        return value;
    }
    
    @Override
    public boolean isANumber(){
        return true;
    }
    
}
