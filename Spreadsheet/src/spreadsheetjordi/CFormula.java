package spreadsheetjordi;

/**
 *
 * @author jnp2
 */
public class CFormula extends Cell{

    public CFormula(String contents) {
        super.contents = contents;
    }
    
    @Override
    public String evaluate(){
        TNombre zero = new TNombre("0");
        TOperation oper = new TOperation("+");
        TExpr expr = new TExpr(contents.substring(1, contents.length()));
        return evaluateRec(zero, oper, expr);
    }
    
    public String evaluateRec(TNombre left1, TOperation op1, TExpr expr){
        if(expr.isEmpty()){
            return "#Error";
        }        
        Token tokenExpr = expr.nextToken();
        
        if(isParenthesisExpresion(tokenExpr)){  
            return evaluateRec(left1, op1, insert(expr, evaluateRec(left1, op1, (TExpr)tokenExpr)));
        }       
        if(tokenExpr.isANumber()){  
          if(expr.isEmpty()) {
             return apply(left1, op1, new TNombre(tokenExpr.getContents()));
            }
            else{
                return evaluateBinaryExpression(left1, op1, new TNombre(tokenExpr.getContents()), new TExpr(expr.getContents()));
            }
        }       
        return "#Error"; 
    }

    private String evaluateBinaryExpression(TNombre left1, TOperation op1, TNombre nbToken ,TExpr expr){
        try{
            Token op = expr.nextToken();
            TOperation op2 = new TOperation(op.getContents());
            
            if(expr.isEmpty()){return "#Error";}
            
            if(op2.priority() > op1.priority()){
                TNombre zero = new TNombre("0");
                TOperation oper = new TOperation("+");
                String result = evaluateRec(zero, oper, expr);
                
                TExpr expr2 = new TExpr(result);
                TNombre rigth1 = new TNombre(evaluateRec(nbToken, op2, expr2));
                
                return apply(left1, op1, rigth1);
            }
            else{  
                TNombre left = new TNombre(apply(left1, op1, new TNombre(nbToken.getContents())));
                return evaluateRec(left, op2, expr);
            }
            
        }
        catch(Exception e){
            return "#Error";
          }     
    }    
    
private String apply(TNombre left, TOperation op, TNombre right){
    int num1 = left.getValue();     
    int num2 = right.getValue();  

    if( "+".equals(op.getOperation())){
       int result =  num1 + num2;    
       return String.valueOf(result);
    }    
    if( "-".equals(op.getOperation())){
       int result =  num1 - num2;    
       return String.valueOf(result);
    }   
    if( "*".equals(op.getOperation())){
       int result =  num1 * num2;    
       return String.valueOf(result);
    }    
    if( "/".equals(op.getOperation())){
       int result =  num1 / num2;    
       return String.valueOf(result);
    }
    return "#Error";    
}
 
private TExpr insert(Token expr, String evaluateRec){
    return new TExpr(evaluateRec+expr.getContents());
}   

private boolean isParenthesisExpresion(Token token){
    String expr = token.getContents();
    if(expr.startsWith("(") && expr.endsWith(")")){
        return true;
    }        
    return false;
}    
    
}
