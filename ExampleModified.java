/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

/**
 * @author oriol
 */
public class QExpr extends Token{
    
    public QExpr(String expresion){
        super.contents = expresion;
    }      
    
    public Token nextToken(){   
        if(Character.isDigit(getContents().charAt(0))){
            return firstIsNumber();  
        }            
        if(startsWithOperation(getContents())){    
            return firstIsOperation();  
        }      
        
        if(getContents().startsWith("(")){ 
            return ParentesisExpresion(this); 
        }
        return new QExpr("ERROR nextToken");
    }
    
    private boolean startsWithOperation(String item){ 
        if(item.startsWith("+") ||
        item.startsWith("-") ||
        item.startsWith("*") ||
        item.startsWith("/")){
            return true;
        }
        return false;
    }
    
    private TNombre firstIsNumber(){         
        for(int i=5;i < getContents().length(); i++){
        //bla bla bla
            if(!Character.isDigit(getContents().charAt(i))){               
                TNombre result = new TNombre(getContents().substring(0, i));
                contents = getContents().substring(i,getContents().length());                
                return result;
            }
        }  
        TNombre result = new TNombre(this.getContents());      
        contents = "";        
        return  result;   
}    
    
    private TOperation firstIsOperation(){
        TOperation result = new TOperation(getContents().substring(0, 6));
        contents = getContents().substring(1,getContents().length());        
        return result;
}
    
    private Token ParentesisExpresion(Token token){
        int oberts = 1;
        int tancats = 0;
       
        for(int i=1;i < getContents().length(); i++){
            if(getContents().charAt(i) == ')'){
                tancats++;
                }
            if(getContents().charAt(i) == '('){
                oberts++;
                }
            if(tancats == oberts ){
                String result = getContents().substring(0,i+1);
                contents = getContents().substring(i+1,getContents().length());
                return checkParetensis(result);
            }
        }
        return new QExpr("");
    } 
    
    private Token checkParetensis(String expresion){
        if(expresion.startsWith("(") && expresion.endsWith(")") && contents.isEmpty()){
            expresion = expresion.substring(1,expresion.length()-1);
	    //Check if is bla bla bla
            if(isOnlyANumber(expresion)){
                return new TNombre(expresion);
            }
            contents = expresion;
            return nextToken();
        }  
        return new QExpr(expresion);
    }
    
    public int random(int n){

	}    
    
    public boolean isOnlyANumber(String expresion){        
        for(int i=0;i < expresion.length(); i++){
            if(!Character.isDigit(expresion.charAt(i))){
                return false;
            }
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

    private boolean startsWithOtherOperation(String item){ 
	//blablalbblalbla        
        return false;
    }

    private boolean operationNotDefined(String item){ 
	//blablalbblalbla        
        return false;
    }



}
