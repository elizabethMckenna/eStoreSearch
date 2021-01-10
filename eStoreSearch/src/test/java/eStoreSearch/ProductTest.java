package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testGetProductID(){
        Product testProduct = new Product();
        testProduct.setProductID("123456");
        assertEquals(testProduct.getProductID(), "123456");
    }

    @Test
    public void testGetDescription(){
        Product testProduct = new Product();
        testProduct.setDescription("Description of Product ! **");
        assertEquals(testProduct.getDescription(), "Description of Product ! **");
    }

    @Test
    public void testGetPrice(){
        Product testProduct = new Product();
        assertEquals(0,testProduct.getPrice(),0); //initializes to expected value
        testProduct.setPrice(200.75);
        assertEquals(testProduct.getPrice(), 200.75,0);
    }

    @Test
    public void testGetYear(){
        Product testProduct = new Product();
        testProduct.setYear(2001);
        assertEquals(testProduct.getYear(), 2001,0);
    }


    @Test
    public void testSetProductID(){
        Product testProduct = new Product();
        testProduct.setProductID("123456");
        assertEquals("123456", testProduct.getProductID()); //correct... variabel is changed
        assertEquals(testProduct.setProductID("1234567"), false); //too long
        assertEquals(testProduct.setProductID("12345"), false); //too short
        assertEquals(testProduct.setProductID(null), false); //null
        assertEquals(testProduct.setProductID(""), false); //""
        assertEquals(testProduct.setProductID("000001"), true); //leading zeros
        assertEquals(testProduct.setProductID("abcdef"), false); //non-digits
        assertEquals(testProduct.setProductID("   "), false); //non-digits
    }

    @Test
    public void testSetDescription(){
        Product testProduct = new Product();
        testProduct.setDescription("meep is the description");
        assertEquals("meep is the description", testProduct.getDescription()); //variable set
        assertEquals(testProduct.setDescription(null), false); // entering if
        assertEquals(testProduct.setDescription(" "), false); // entering if
    }

    @Test
    public void testSetPrice(){
        Product testProduct = new Product();
        //double/numerical params
        assertEquals(testProduct.setPrice(0), true); //test 0
        assertEquals(testProduct.setPrice(10.75), true); //test positive (w decimal)
        assertEquals(testProduct.setPrice(-10.2), false); //test negative
        //string params
        assertEquals(testProduct.setPrice("0"), true); //test 0
        assertEquals(testProduct.setPrice("10.75"), true); //test positive (w decimal)
        assertEquals(testProduct.setPrice("-10.2"), false); //test negative
        assertEquals(testProduct.setPrice(" "), false); 
        assertEquals(testProduct.setPrice(null), false);
    }


    @Test
    public void testSetYear(){
        Product testProduct = new Product();
        //int params
        assertEquals(testProduct.setYear(-2000), false); //negative (but abs val in range)
        assertEquals(testProduct.setYear(2000), true); //valid year, middle of range
        assertEquals(testProduct.setYear(1000), true); //valid year - edge case
        assertEquals(testProduct.setYear(999), false); //invalid year - edge case
        assertEquals(testProduct.setYear(9999), true); //valid year - edge case
        assertEquals(testProduct.setYear(10000), false); //invalid year - edge case
        //string params
        assertEquals(testProduct.setYear("-2000"), false); //negative (but abs val in range)
        assertEquals(testProduct.setYear("2000"), true); //valid year, middle of range
        assertEquals(testProduct.setYear("1000"), true); //valid year - edge case
        assertEquals(testProduct.setYear("999"), false); //invalid year - edge case
        assertEquals(testProduct.setYear("9999"), true); //valid year - edge case
        assertEquals(testProduct.setYear("10000"), false); //invalid year - edge case
        assertEquals(testProduct.setYear(" "), false);
        assertEquals(testProduct.setYear(null), false);
    }


    @Test
    public void testToString(){
        try {
            Product testProduct = new Product("123456", "Decription of product", 25.50, 2002);
            assertEquals("ProductID:  " + testProduct.getProductID() + "\nDesciption:  " + testProduct.getDescription() + "\nPrice:  $" + testProduct.getPrice() + "\nYear:  " + testProduct.getYear() + "\n", testProduct.toString());
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        } 
        
    }

    @Test
    public void testEquals(){
        try {
        Product testProduct = new Product("123456", "Decription of product", 25.50, 2002);
        Product testProduct2 = new Product("123456", "Decription of product", 25.50, 2002);
        Product testProduct3 = new Product("123457", "Decription of product", 25.50, 2002);
        Product testProduct4 = new Product("123456", "Decription of books", 25.50, 2002);
        Product testProduct5 = new Product("123456", "Decription of product", 35.50, 2002);
        Product testProduct6 = new Product("123456", "Decription of product", 25.50, 2000);

        assertEquals(testProduct.equals(testProduct2),true); //books equal
        assertEquals(testProduct.equals(testProduct3),false); //ID different
        assertEquals(testProduct.equals(testProduct4),false); //Descriptions different
        assertEquals(testProduct.equals(testProduct5),false); //price different
        assertEquals(testProduct.equals(testProduct6),false); //year different
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        } 
    }

}

