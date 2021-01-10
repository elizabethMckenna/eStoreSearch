package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElectronicTest {

    @Test
    public void testGetProductID(){
        Electronic testElectronic = new Electronic();
        testElectronic.setProductID("123456");
        assertEquals(testElectronic.getProductID(), "123456");
    }

    @Test
    public void testGetDescription(){
        Electronic testElectronic = new Electronic();
        testElectronic.setDescription("Description of Electronic ! **");
        assertEquals(testElectronic.getDescription(), "Description of Electronic ! **");
    }

    @Test
    public void testGetPrice(){
        Electronic testElectronic = new Electronic();
        assertEquals(0,testElectronic.getPrice(),0); //initializes to expected value
        testElectronic.setPrice(200.75);
        assertEquals(testElectronic.getPrice(), 200.75,0);
    }

    @Test
    public void testGetYear(){
        Electronic testElectronic = new Electronic();
        testElectronic.setYear(2001);
        assertEquals(testElectronic.getYear(), 2001,0);
    }

    @Test
    public void testGetMaker(){
        Electronic testElectronic = new Electronic();
        testElectronic.setMaker("Name of Maker ! **");
        assertEquals(testElectronic.getMaker(), "Name of Maker ! **");
    }
    
    @Test
    public void testSetProductID(){
        Electronic testElectronic = new Electronic();
        testElectronic.setProductID("123456");
        assertEquals("123456", testElectronic.getProductID()); //correct... variabel is changed
        assertEquals(testElectronic.setProductID("1234567"), false); //too long
        assertEquals(testElectronic.setProductID("12345"), false); //too short
        assertEquals(testElectronic.setProductID(null), false); //null
        assertEquals(testElectronic.setProductID(""), false); //""
        assertEquals(testElectronic.setProductID("000001"), true); //leading zeros
        assertEquals(testElectronic.setProductID("abcdef"), false); //non-digits
    }

    @Test
    public void testSetDescription(){
        Electronic testElectronic = new Electronic();
        testElectronic.setDescription("meep is the description");
        assertEquals("meep is the description", testElectronic.getDescription()); //variable set
        assertEquals(testElectronic.setDescription(null), false); // entering if
    }

    @Test
    public void testSetPrice(){
        Electronic testElectronic = new Electronic();
        //numerical params
        assertEquals(testElectronic.setPrice(0), true); //test 0
        assertEquals(testElectronic.setPrice(10.75), true); //test positive (w decimal)
        assertEquals(testElectronic.setPrice(-10.2), false); //test negative
        //string params
        assertEquals(testElectronic.setPrice("0"), true); //test 0
        assertEquals(testElectronic.setPrice("10.75"), true); //test positive (w decimal)
        assertEquals(testElectronic.setPrice("-10.2"), false); //test negative
    }


    @Test
    public void testSetYear(){
        Electronic testElectronic = new Electronic();
        //int params
        assertEquals(testElectronic.setYear(-2000), false); //negative (but abs val in range)
        assertEquals(testElectronic.setYear(2000), true); //valid year, middle of range
        assertEquals(testElectronic.setYear(1000), true); //valid year - edge case
        assertEquals(testElectronic.setYear(999), false); //invalid year - edge case
        assertEquals(testElectronic.setYear(9999), true); //valid year - edge case
        assertEquals(testElectronic.setYear(10000), false); //invalid year - edge case
        //string params
        assertEquals(testElectronic.setYear("-2000"), false); //negative (but abs val in range)
        assertEquals(testElectronic.setYear("2000"), true); //valid year, middle of range
        assertEquals(testElectronic.setYear("1000"), true); //valid year - edge case
        assertEquals(testElectronic.setYear("999"), false); //invalid year - edge case
        assertEquals(testElectronic.setYear("9999"), true); //valid year - edge case
        assertEquals(testElectronic.setYear("10000"), false); //invalid year - edge case
    }

    @Test
    public void testSetMaker(){
        Electronic testElectronic = new Electronic();
        assertEquals(testElectronic.setMaker(null), true); //null string
        assertEquals(testElectronic.setMaker(""), true); //emptry string, but still exists
        assertEquals(testElectronic.setMaker("Sample string with multiple words - symbols 2!!"), true);
    }



    @Test
    public void testToString(){
        try {
            Electronic testElectronic = new Electronic("123456", "Decription of book", 25.50, 2002, "Names of Makers");
            assertEquals("ProductID:  " + testElectronic.getProductID() + "\nDesciption:  " + testElectronic.getDescription() + "\nPrice:  $" + testElectronic.getPrice() + "\nYear:  " + testElectronic.getYear()+ "\nMaker:  " + testElectronic.getMaker() + "\n" , testElectronic.toString());
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        }
        
    }
   
}

