/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadsheetjordi;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author jnp2
 */
public class SpreadsheetTest {
    
    public SpreadsheetTest() {
    }

//PART I    
    @Test
    public void testThatCellsAreEmptyByDefault() {
     Spreadsheet sheet = new Spreadsheet();
     assertEquals("", sheet.get("A1"));
     assertEquals("", sheet.get("ZX347"));
    }    
    
    @Test
    public void testThatTextCellsAreStored() {
     Spreadsheet sheet = new Spreadsheet();
     String theCell = "A21";

     sheet.put(theCell, "A string");
     assertEquals("A string", sheet.get(theCell));

     sheet.put(theCell, "A different string");
     assertEquals("A different string", sheet.get(theCell));

     sheet.put(theCell, "");
     assertEquals("", sheet.get(theCell));
   }    
    
  @Test
  public void testThatManyCellsExist() {
    Spreadsheet sheet = new Spreadsheet();
    sheet.put("A1", "First");
    sheet.put("X27", "Second");
    sheet.put("ZX901", "Third");

    assertEquals("A1", "First", sheet.get("A1"));
    assertEquals("X27", "Second", sheet.get("X27"));
    assertEquals("ZX901", "Third", sheet.get("ZX901"));

    sheet.put("A1", "Fourth");
    assertEquals("A1 after", "Fourth", sheet.get("A1"));
    assertEquals("X27 same", "Second", sheet.get("X27"));
    assertEquals("ZX901 same", "Third", sheet.get("ZX901"));
  } 
  
  @Test
  public void testThatNumericCellsAreIdentifiedAndStored() {
   Spreadsheet sheet = new Spreadsheet();
   String theCell = "A21";

   sheet.put(theCell, "X99"); // "Obvious" string
   assertEquals("X99", sheet.get(theCell));

   sheet.put(theCell, "14"); // "Obvious" number
   assertEquals("14", sheet.get(theCell));

   sheet.put(theCell, " 99 X"); // Whole string must be numeric
   assertEquals(" 99 X", sheet.get(theCell));

   sheet.put(theCell, " 1234 "); // Blanks ignored
   assertEquals("1234", sheet.get(theCell));

   sheet.put(theCell, " "); // Just a blank
   assertEquals(" ", sheet.get(theCell));
 }
  
  //TEST NUMERIC STRINGS

  @Test
  public void testNumericStrings() {

      Spreadsheet sheet = new Spreadsheet();
      String st = "";
      
      st = "9";
      assertTrue(sheet.isNumber(st));
      
      st = "-9";
      assertTrue(sheet.isNumber(st));
      
      st = "aaa";
      assertFalse(sheet.isNumber(st));
      
      st = "99";
      assertTrue(sheet.isNumber(st));
      
      st = "-9999760";
      assertTrue(sheet.isNumber(st));
      
      st = "0";
      assertTrue(sheet.isNumber(st));

       st = " -95555 ";
      assertTrue(sheet.isNumber(st));

      st = "09";
      assertFalse(sheet.isNumber(st));

        st = "-09";
      assertFalse(sheet.isNumber(st));
      
        st = "-0";  //-0 is not considered to be a number
      assertFalse(sheet.isNumber(st));
      

 }
  
@Test
 public void testThatWeHaveAccessToCellLiteralValuesForEditing() { 
   Spreadsheet sheet = new Spreadsheet();
   String theCell = "A21";

   sheet.put(theCell, "Some string"); 
   assertEquals("Some string", sheet.getLiteral(theCell));

   sheet.put(theCell, " 1234 "); 
   assertEquals(" 1234 ", sheet.getLiteral(theCell));

   sheet.put(theCell, "=7"); // Foreshadowing formulas:)
   assertEquals("=7", sheet.getLiteral(theCell));
 }  


 //PART II
 
 @Test
 public void testFormulaSpec() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("B1", " =7"); // note leading space
  assertEquals("Not a formula", " =7", sheet.get("B1"));
  assertEquals("Unchanged", " =7", sheet.getLiteral("B1"));
}
 
 @Test
 public void testConstantFormula() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=7");
  assertEquals("Formula", "=7", sheet.getLiteral("A1"));
  assertEquals("Value", "7", sheet.get("A1"));
 }
 
 @Test
 public void testParentheses() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=(7)");
  assertEquals("Parends", "7", sheet.get("A1"));
 }

 //@Test
 public void testDeepParentheses() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=((((1))))");
  assertEquals("Parends", "1", sheet.get("A1"));
}

@Test
public void testMultiplyTwo() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=2*3");
  assertEquals("Times", "6", sheet.get("A1"));
} 

@Test
 public void testParenthesesNbrsVariousDigits() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=(71)");
  assertEquals("Parends", "71", sheet.get("A1"));
 }


@Test
public void testMultiplyGeneral() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=2*3*4");
  assertEquals("Times", "24", sheet.get("A1"));
}


//@Test
 public void testAdd() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=1+2+3");
  assertEquals("Add", "6", sheet.get("A1"));
 }

 @Test
 public void testPrecedence() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=1+2*3");
  assertEquals("Precedence", "7", sheet.get("A1"));
 }

 @Test
 public void testFullExpression() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=7*(2+3)*((((2+1))))");
  assertEquals("Expr", "105", sheet.get("A1"));
 }

 @Test
 public void testParenthesisWithExpression() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=(2+3)");
  assertEquals("Expr", "5", sheet.get("A1"));
 }

 @Test 
 public void testSimpleFormulaError() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=7*");
  assertEquals("Error", "#Error", sheet.get("A1"));
}

 @Test 
 public void testFormulaError() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=7*)");
  assertEquals("Error", "#Error", sheet.get("A1"));
}

 
@Test 
public void testParenthesisError() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=(((((7))");
  assertEquals("Error", "#Error", sheet.get("A1")); 
}

@Test 
public void testParenthesisError2() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=(((((744))))))))");
  assertEquals("Error", "#Error", sheet.get("A1")); 
}

 

//PART 3

//@Test
public void testThatCellReferenceWorks () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "8");
  sheet.put("A2", "=A1");
  assertEquals("cell lookup", "8", sheet.get("A2"));
}

//@Test
public void referenceToANonInitializedCell () {
  Spreadsheet sheet = new Spreadsheet();
  
  sheet.put("A2", "=A6");
  assertEquals("non existing cell lookup", "#Error", sheet.get("A2"));
}



//@Test
public void testThatCellChangesPropagate () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "8");
  sheet.put("A2", "=A1");
  assertEquals("cell lookup", "8", sheet.get("A2"));

  sheet.put("A1", "9");
  assertEquals("cell change propagation", "9", sheet.get("A2"));
}

//@Test
public void testThatFormulasKnowCellsAndRecalculate () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "8");
  sheet.put("A2", "3");
  sheet.put("B1", "=A1*(A1+A2)+A2+3");
  assertEquals("calculation with cells", "94", sheet.get("B1"));

  sheet.put("A2", "6");
  assertEquals("re-calculation", "121", sheet.get("B1"));
}

//@Test
public void testThatDeepPropagationWorks () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "8");
  sheet.put("A2", "=A1");
  sheet.put("A3", "=A2");
  sheet.put("A4", "=A3");
  assertEquals("deep propagation", "8", sheet.get("A4"));

  sheet.put("A2", "6");
  assertEquals("deep re-calculation", "6", sheet.get("A4"));
}


// The following test is likely to pass already.
//@Test
public void testThatFormulaWorksWithManyCells () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "10");
  sheet.put("A2", "=A1+B1");
  sheet.put("A3", "=A2+B2");
  sheet.put("A4", "=A3");
  sheet.put("B1", "7");
  sheet.put("B2", "=A2");
  sheet.put("B3", "=A3+A2");
  sheet.put("B4", "=A4+B3");

  assertEquals("multiple expressions - A4", "34", sheet.get("A4"));
  assertEquals("multiple expressions - B4", "85", sheet.get("B4"));
}

// Refactor and get everything nice and clean.


// Next: (I almost made this a separate part, and when I
// originally did it, I did it in a different design session).
// So take a break if you need one.

// There's one big open issue for formulas: what about
// circular references? 

// I'll sketch some hints, but you should define your own tests
// that drive toward a solution compatible with your own 
// implementation.

//@Test
public void testThatCircularReferenceDoesntCrash() {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=A1");
  assertTrue(true);
}

// Just like errors return a special value, it might be nice
// if circular references did too. (See notes below).

//@Test
public void testThatCircularReferencesAdmitIt () {
  Spreadsheet sheet = new Spreadsheet();
  sheet.put("A1", "=A1");
  assertEquals("Detect circularity", "#Circular", sheet.get("A1"));
}

// You might come up with some other approach that suits your 
// taste. We won't be exploring this corner of the solution 
// any further; you just want a scheme that blocks silly mistakes.
// Make sure you test deep circularities involving partially
// evaluated expressions.

// A hint: if you blindly evaluate an expression you have no
// control over how deep the expression can be, since
// circular references appear to be infinitely deep.


// Where are we? I intend to spend the next two parts hooking
// up a GUI. Then there will be an optional part that pushes 
// things in an unexpected direction just to get a sense
// of our software's robustness.




}