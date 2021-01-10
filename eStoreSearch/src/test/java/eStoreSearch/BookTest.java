package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testGetProductID(){
        Book testBook = new Book();
        testBook.setProductID("123456");
        assertEquals(testBook.getProductID(), "123456");
    }

    @Test
    public void testGetDescription(){
        Book testBook = new Book();
        testBook.setDescription("Description of Electronic ! **");
        assertEquals(testBook.getDescription(), "Description of Electronic ! **");
    }

    @Test
    public void testGetPrice(){
        Book testBook = new Book();
        assertEquals(0,testBook.getPrice(),0); //initializes to expected value
        testBook.setPrice(200.75);
        assertEquals(testBook.getPrice(), 200.75,0);
    }

    @Test
    public void testGetYear(){
        Book testBook = new Book();
        testBook.setYear(2001);
        assertEquals(testBook.getYear(), 2001,0);
    }

    @Test
    public void testGetAuthors(){
        Book testBook = new Book();
        testBook.setAuthors("Name of Authors! **");
        assertEquals(testBook.getAuthors(), "Name of Authors! **");
    }

    @Test
    public void testGetPublisher(){
        Book testBook = new Book();
        testBook.setPublisher("Name of Publisher! **");
        assertEquals(testBook.getPublisher(), "Name of Publisher! **");
    }

    @Test
    public void testSetProductID(){
        Book testBook = new Book();
        testBook.setProductID("123456");
        assertEquals("123456", testBook.getProductID()); //correct... variabel is changed
        assertEquals(testBook.setProductID("1234567"), false); //too long
        assertEquals(testBook.setProductID("12345"), false); //too short
        assertEquals(testBook.setProductID(null), false); //null
        assertEquals(testBook.setProductID(""), false); //""
        assertEquals(testBook.setProductID("000001"), true); //leading zeros
        assertEquals(testBook.setProductID("abcdef"), false); //non-digits
    }

    @Test
    public void testSetDescription(){
        Book testBook = new Book();
        testBook.setDescription("meep is the description");
        assertEquals("meep is the description", testBook.getDescription()); //variable set
        assertEquals(testBook.setDescription(null), false); // entering if
    }

    @Test
    public void testSetPrice(){
        Book testBook = new Book();
        //numerical params
        assertEquals(testBook.setPrice(0), true); //test 0
        assertEquals(testBook.setPrice(10.75), true); //test positive (w decimal)
        assertEquals(testBook.setPrice(-10.2), false); //test negative
        //string params
        assertEquals(testBook.setPrice("0"), true); //test 0
        assertEquals(testBook.setPrice("10.75"), true); //test positive (w decimal)
        assertEquals(testBook.setPrice("-10.2"), false); //test negative
    }


    @Test
    public void testSetYear(){
        Book testBook = new Book();
        //int params
        assertEquals(testBook.setYear(-2000), false); //negative (but abs val in range)
        assertEquals(testBook.setYear(2000), true); //valid year, middle of range
        assertEquals(testBook.setYear(1000), true); //valid year - edge case
        assertEquals(testBook.setYear(999), false); //invalid year - edge case
        assertEquals(testBook.setYear(9999), true); //valid year - edge case
        assertEquals(testBook.setYear(10000), false); //vinvalid year - edge case
        //string params
        assertEquals(testBook.setYear("-2000"), false); //negative (but abs val in range)
        assertEquals(testBook.setYear("2000"), true); //valid year, middle of range
        assertEquals(testBook.setYear("1000"), true); //valid year - edge case
        assertEquals(testBook.setYear("999"), false); //invalid year - edge case
        assertEquals(testBook.setYear("9999"), true); //valid year - edge case
        assertEquals(testBook.setYear("10000"), false); //vinvalid year - edge case
    }

    @Test
    public void testSetAuthors(){
        Book testBook = new Book();
        assertEquals(testBook.setAuthors(null), true); //null string
        assertEquals(testBook.setAuthors(""), true); //emptry string, but still exists
        assertEquals(testBook.setAuthors("Sample string with multiple words - symbols 2!!"), true);
    }

    @Test
    public void testSetPublisher(){
        Book testBook = new Book();
        assertEquals(testBook.setPublisher(null), true); //null string
        assertEquals(testBook.setPublisher(""), true); //emptry string, but still exists
        assertEquals(testBook.setPublisher("Sample string with multiple words - symbols 2!!"), true);
    }

    @Test
    public void testToString(){
        try {
            Book testBook = new Book("123456", "Decription of book", 25.50, 2002, "Names of Authors", "Publisher Name");
            assertEquals("ProductID:  " + testBook.getProductID() + "\nDesciption:  " + testBook.getDescription() + "\nPrice:  $" + testBook.getPrice() + "\nYear:  " + testBook.getYear()+ "\nAuthor(s):  " + testBook.getAuthors()+ "\nPublisher:  " + testBook.getPublisher()+ "\n"  , testBook.toString());
        }
        catch (Exception m) {
            System.out.println("Constructor exception has occured. New Book was not created");
        }
        
    }


}

