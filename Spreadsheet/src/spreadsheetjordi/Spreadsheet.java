/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jnp2
 */
public class Spreadsheet {
  
    private Map<String, Cell> cells;
    
    public Spreadsheet(){
        this.cells = new HashMap<String,Cell>();
    }
    
    public void put(String cell, String contents){
        if(contents==null){    
            Cell item = new CString("");
            this.cells.put(cell, item);
            
        }
        else if(isNumber(contents)){ 
            Cell item = new CNumber(contents);
            this.cells.put(cell, item);
        }
        else if(isFormula(contents)){
            Cell item = new CFormula(contents);
            this.cells.put(cell, item);
        }
        else{
            Cell item = new CString(contents);
            this.cells.put(cell, item);
        } 
        
    }   
    
    public String get(String cell){        
        Cell contents = cells.get(cell);
        
        if(contents == null) return "";
        
        return contents.evaluate();        
    } 
    
    public String getLiteral(String cell){        
        Cell contents = cells.get(cell);
        
        if(contents == null) return "";
        
        return contents.getLiteral(); 
    } 
            
    public boolean isFormula(String string){      
        return string.startsWith("=");
    }
    
    public boolean isNumber(String number){
        try{  
            double d = Double.parseDouble(number);        
            if(checkSpecialStart(number)){
                return false;
            }
        }  
        catch(NumberFormatException nfe){ 
            return false;  
        }  
        return true;  
}
    
    public static boolean checkSpecialStart(String number){ 
        number = number.trim();         
        if((number.startsWith("-0") || number.startsWith("0")) && number.length()>1){
            return true;
        }        
        return false;         
    }
 
    
    public static void main(String[] args){ 
    }

    
}
