package main;

import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.Scanner;


/**
 * Musical Lake guesser.
 * This helper class offers a simple way of determining the continuations
 * of the sounds recorded by the explorer.
 *
 * It uses a main.SongStorage object to operate on the list of songs available.
 * It also uses the main.Song class to build main.Song objects.
 *
 * These two classes may be used however the user wants; this little script
 * simply tries to demonstrate the way they operate.
 *
 */

public class Main {

    public static void main(String[] args) {

        // This array can be acquired any way you want. Let's hardcode it.

        String[] SAMPLE_SONGS = {
                "brr, fiu, cric-cric, brrah",
                "pep, birip, trri-trri, croac",
                "bri-bri, plop, cric-cric, brrah"
        };


        List<Song> allSongs = Song.buildSongsFromStringList(Arrays.asList(SAMPLE_SONGS));
        SongStorage songStorage = new SongStorage(allSongs);
        songStorage.defineSoundContinuationsForSongList();

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Get continuations for sound: ");
            String argument = scanner.nextLine();

            Set<Song> continuations = songStorage.getContinuationsForSound(argument);
            if(continuations != null){
                System.out.println("Continued by -> "+continuations);
            }
            else{
                System.out.println("The given sound is not in the database.");
            }
        }

    }

}
