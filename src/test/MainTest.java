package test;

import main.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{

    /** PREPARE SONG OBJECTS TO PARSE THE DATA. **/
    static String[] TEST_SONGS = {
            "brr, fiu, cric-cric, brrah",
            "pep, birip, trri-trri, croac",
            "bri-bri, plop, cric-cric, brrah",
            "fiu, brr, brr, croac"
    };

    static List<Song> allSongs;
    static SongStorage songStorage;

    @org.junit.jupiter.api.BeforeAll
    static void setUp()
    {
        allSongs = Song.buildSongsFromStringList(Arrays.asList(TEST_SONGS));
        songStorage = new SongStorage(allSongs);
        songStorage.defineSoundContinuationsForSongList();
    }

    /**
     * Test that a sound in a song is followed by the right group of sounds.
     */
    @org.junit.jupiter.api.Test
    public void testSimpleContinuation(){

        Set<Song> continuations = songStorage.getContinuationsForSound("trri-trri");
        assertEquals("[(croac)]", continuations.toString());
    }

    /**
     * Test that a sound that is last in a song has no valid continuations.
     */
    @org.junit.jupiter.api.Test
    public void testNoContinuation(){

        Set<Song> continuations = songStorage.getContinuationsForSound("brrah");
        assertEquals("[]", continuations.toString());
    }

    /**
     * Test that a non-available sound returns a null list of continuations.
     */
    @org.junit.jupiter.api.Test
    public void testNonAvailableSound(){

        Set<Song> continuations = songStorage.getContinuationsForSound("HELLO");
        assertNull(continuations);
    }

    /**
     * Test that a sound that appears in more than one song, with the exact same
     * continuation, only returns a single instance of such continuation.
     */
    @org.junit.jupiter.api.Test
    public void testRepeatedContinuation(){

        Set<Song> continuations = songStorage.getContinuationsForSound("cric-cric");
        assertEquals(continuations.size(), 1);
    }

    /**
     * Check that a sound followed by the same sound, returns a valid continuation.
     */
    @org.junit.jupiter.api.Test
    public void testItselfAsContinuation(){

        Set<Song> continuations = songStorage.getContinuationsForSound("brr");
        assertTrue(continuations.toString().contains("brr"));
    }

    /**
     * Check that sounds return the same values independently of the letter case.
     */
    @org.junit.jupiter.api.Test
    public void testMixedCaseContinuation(){

        Set<Song> continuations1 = songStorage.getContinuationsForSound("brr");
        Set<Song> continuations2 = songStorage.getContinuationsForSound("bRR");
        assertTrue(continuations1.toString().equals(continuations2.toString()));
    }

    /**
     * Check that all the continuations for a sound that appears in more than 2 songs are returned
     * correctly.
     */
    @org.junit.jupiter.api.Test
    public void testDistinctContinuations(){

        Set<Song> continuations = songStorage.getContinuationsForSound("fiu");
        assertEquals(continuations.size(), 2);
    }

    /* Ensure empty arguments do not break the service and return null. */
    @org.junit.jupiter.api.Test
    public void testEmptySound(){

        Set<Song> continuations = songStorage.getContinuationsForSound("");
        assertNull(continuations);
    }

}