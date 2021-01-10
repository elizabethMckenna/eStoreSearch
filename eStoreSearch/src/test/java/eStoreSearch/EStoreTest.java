package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextArea;

public class EStoreTest {
    Scanner keyboard = new Scanner(System.in);
   
    HashMap< String, ArrayList<Integer> > keyWordHash = new HashMap < String, ArrayList<Integer> > ();
    

    @Test
    public void testUniqueID(){
        EStore store = new EStore();
        try {           
        Product testProduct = new Product("000001", "Decription of electronic", 25.50, 2002);
        Product testProduct1 = new Product("000002", "Decription of electronic", 25.50, 2002);
        Product testProduct2 = new Product("000004", "Decription of electronic", 25.50, 2002);
        store.addNewProduct(testProduct, keyWordHash );
        store.addNewProduct(testProduct1, keyWordHash );
        store.addNewProduct(testProduct2, keyWordHash );

        assertEquals(store.uniqueID("000003"), true); //unique id -- between two already existing ids
        assertEquals(store.uniqueID("000001"), false); //repeat id 
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        }

    }

    @Test
    public void testPrintProductListSearchMatches(){
        EStore store = new EStore();
        ArrayList<Integer> expectedOut = new ArrayList<Integer>();
        JTextArea textArea = new JTextArea();
        try {
            Product testProduct = new Product("000001", "Decription meep moo", 25.50, 2000);
            Product testProduct1 = new Product("000002", "Decription MOO meow", 25.50, 2001);
            Product testProduct2 = new Product("000003", "Decription Meow woof", 25.50, 2002);
            Product testProduct3 = new Product("000004", "Decription Meowing woof", 25.50, 2003);
            store.addNewProduct(testProduct, keyWordHash);
            store.addNewProduct(testProduct1, keyWordHash);
            store.addNewProduct(testProduct2, keyWordHash);
            store.addNewProduct(testProduct3, keyWordHash);
            //when no search parameters
            expectedOut.add(0);
            expectedOut.add(1);
            expectedOut.add(2);
            expectedOut.add(3);
            assertEquals(store.printProductListSearchMatches(0, keyWordHash,textArea), expectedOut);
            expectedOut.clear();
            //----JUST ID SEARCH---//
            //search at start
            store.setSearchID("000001");
            expectedOut.add(0);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
            //search at end
            store.setSearchID("000004");
            expectedOut.clear();
            expectedOut.add(3);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
            //search in middle
            store.setSearchID("000003");
            expectedOut.clear();
            expectedOut.add(2);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
         
            //-----JUST YEAR SEARCH ---//
            store.resetSearchValues();
            assertEquals(store.getSearchID(), null);
    
            //search single year mode
            store.searchYear(2002,2002);
            expectedOut.clear();
            expectedOut.add(2);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
            //search pre and incl year mode
            store.searchYear(0,2002);
            expectedOut.clear();
            expectedOut.add(0);
            expectedOut.add(1);
            expectedOut.add(2);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
            //search post and incl year mode
            store.searchYear(2002,0);
            expectedOut.clear();
            expectedOut.add(2);
            expectedOut.add(3);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
            //search year range mode
            store.searchYear(2001,2002);
            expectedOut.clear();
            expectedOut.add(1);
            expectedOut.add(2);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
    
            //-----JUST KEYWORD SEARCH ---//
            store.resetSearchValues();
            assertEquals(store.getSearchYear1(), 0);
            assertEquals(store.getSearchYear2(), 0);
    
            //search single keyword (first elements of list)
            store.setSearchKeywords("Moo");
            expectedOut.clear();
            expectedOut.add(0);
            expectedOut.add(1);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
    
            //search multiple keywords in any order
            store.resetSearchValues();
            store.setSearchKeywords("WOOF meow");
            expectedOut.clear();
            expectedOut.add(2);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
    
            //search only finds exact word match
            //search single keyword (end of list)
            store.resetSearchValues();
            store.setSearchKeywords("MeowIng");
            expectedOut.clear();
            expectedOut.add(3);
            assertEquals(store.printProductListSearchMatches(1, keyWordHash,textArea),expectedOut);
    
            //------ 3 PARAMETER SEARCH -----//
            store.resetSearchValues();
            //correct
            store.setSearchID("000002");
            store.searchYear(2001, 2001);
            store.setSearchKeywords("meow");
            expectedOut.clear();
            expectedOut.add(1);
            assertEquals(store.printProductListSearchMatches(3, keyWordHash,textArea),expectedOut);
            //ID not match
            store.setSearchID("000001");
            store.searchYear(2001,2001);
            store.setSearchKeywords("meow");
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(3, keyWordHash,textArea),expectedOut);
            //Keyword not match
            store.setSearchID("000002");
            store.searchYear(2000,2000);
            store.setSearchKeywords("meow");
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(3, keyWordHash,textArea),expectedOut);
            //year not match
            store.setSearchID("000002");
            store.searchYear(2001,2001);
            store.setSearchKeywords("woof");
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(3, keyWordHash,textArea),expectedOut);
    
            // 2 PARAMETER SEARCH
            //ID and YEAR - match found
            store.resetSearchValues();
            store.setSearchID("000002");
            store.searchYear(2001, 2001);
            expectedOut.clear();
            expectedOut.add(1);
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);
            //ID and YEAR - no match
            store.resetSearchValues();
            store.setSearchID("000002");
            store.searchYear(2002,2002);
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);
            //ID and KEYWORD - match found
            store.resetSearchValues();
            store.setSearchID("000002");
            store.setSearchKeywords("meow");
            expectedOut.clear();
            expectedOut.add(1);
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);
            //ID and KEYWORD - no match
            store.resetSearchValues();
            store.setSearchID("000001");
            store.setSearchKeywords("meow");
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);
            //YEAR and KEYWORD - match found
            store.resetSearchValues();
            store.searchYear(2001,2001);
            store.setSearchKeywords("meow");
            expectedOut.clear();
            expectedOut.add(1);
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);
            //YEAR and KEYWORD - no match
            store.resetSearchValues();
            store.searchYear(2000,2000);
            store.setSearchKeywords("meow");
            expectedOut.clear();
            assertEquals(store.printProductListSearchMatches(2, keyWordHash,textArea),expectedOut);    
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        }
    }   

    @Test
    public void testReadProductListFromValidFile(){
        // this isnt working, and im not sure why, but I tried.
        EStore store1 = new EStore();
        EStore store2 = new EStore();
try {
    Book prod1 = new Book ("000025", "Absolute Java", 199.95, 2015, "Walter Savith, Kenrich Mock", "Pearson");
    Electronic prod2 = new Electronic ("000107", "MacBook Air 11\"", 1099.99, 2013, "Apple Inc." );
    Book prod3 = new Book ("000026", "Absolute Java", 199.95, 2015, "Walter Savith, Kenrich Mock", "Pearson");
    Book prod4 = new Book ("123456", "the art of espresso: the java arts", 200.0, 2019, "meep", "meow");
    Book prod5 = new Book ("000000", "Fairy Tale book", 2.0, 1412, "the brothers grimm", "");
    store2.addNewProduct(prod1, keyWordHash);
    store2.addNewProduct(prod2, keyWordHash);
    store2.addNewProduct(prod3, keyWordHash);
    store2.addNewProduct(prod4, keyWordHash);
    store2.addNewProduct(prod5, keyWordHash);
    
    keyWordHash.clear();
    store1.readProductListFromFile(store1, "testValidInput", keyWordHash);
    assertEquals(store1.productList, store2.productList); 
}
catch (Exception m) {
    System.out.println("Constructor exception has occured. New Book was not created");
} 
         
    }


    @Test
    public void testReadProductListFromInvalidFile(){
        // this isnt working, and im not sure why, but I tried.
        EStore store1 = new EStore();
        EStore store2 = new EStore();

        keyWordHash.clear();
        store1.readProductListFromFile( store1,  "testInvalidInput", keyWordHash);
        assertEquals(store1.productList, store2.productList);
    }
}

