/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

/**
 *
 * @author jnp2
 */
public class Token {
    String contents;
    
    public boolean isEmpty(){
        return contents.isEmpty();
    }
      
    public boolean isANumber(){
        return false;
    }
    
    public String getContents(){
        return contents;
    }
    
}
