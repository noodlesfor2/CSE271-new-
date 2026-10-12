import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.swing.internal.plaf.metal.resources.metal;

public class SongTest2 {
    
    /*
     * Common JUnit Test methods:
     * assertEquals(expected, actual)
     * assertTrue(boolVal)
     * assertFalse(boolVal
     * fail()
     */
    /**
     * Verifying the workhorse constructor works as expected.
     */
    @Test
    public void testWorkhorseConstructor() {
        // Used to check double values with expected rounding issues
        //double delta = 0.000001;
        
        Song s = new Song("someTitle", "someArtist", "someAlbumName", "4:44", 5, true);
        
        // Checking that title is "someTitle", sets it to "Sometitle"
        assertEquals("Sometitle", s.getTitle());
        
        assertEquals(5, s.getStreams());
        
        assertTrue(s.isFavorite());
        
        
    }
    
    /**
     * Verifying that the equals method can accurately compare Song objects
     */
    @Test
    public void testEquals() {
        Song s = new Song("someTitle", "someArtist", "someAlbumName", "4:44", 5, true);
        Song s2 = new Song("someTitle", "someArtist", "someAlbumName", "4:44", 5, true);
        
        assertTrue(s.equals(s2));
    }
    
    @Test
    public void testPlay() {
        Song s = new Song("someTitle", "someArtist", "someAlbumName", "4:44", 5, true);
        
        // You can test a method using similar things, may have to use try/catch depending on what's expected.
    }

}
