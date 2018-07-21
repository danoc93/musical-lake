package main;

import java.util.*;

/**
 * main.SongStorage Class
 * Operations to store and manipulate different songs. Allows the user
 * to tokenize a songs and acquire their continuations.
 *
 * A - continuation - for a sound is the set of sounds that follow it within a song.
 * A continuation is simply treated as a smaller main.Song in the context of this service.
 *
 * This class acts as a database, and can keep track of repeated continuations
 * by using a HashSet for in the mapping, which is constructed using a HashMap to allow
 * constant time lookups.
 */

public class SongStorage
{
    private List<Song> _allSongs;
    private HashMap<Sound, Set<Song>> _soundToContinuation;

    public SongStorage(List<Song> songs){

        _allSongs = new ArrayList<>();
        _soundToContinuation = new HashMap<>();

        if(songs != null) _allSongs.addAll(songs);
    }

    /*
    By calling this function you can create a database of main.Sound -> List of Continuations.
    List of Continuations has been implemented as a HashMap in order to eliminate duplicate
    continuations in constant time.
    * */
    public void defineSoundContinuationsForSongList(){

        if(_allSongs == null || _allSongs.size() == 0) return;

        for(Song song : _allSongs){
            defineSoundContinuationsForSong(song);
        }
    }

    /*
    The following pair of functions allow you to get the set of continuations for a sound.
    * */
    public Set<Song> getContinuationsForSound(Sound sound){

        if(sound == null) return null;
        return _soundToContinuation.get(sound);
    }

    public Set<Song> getContinuationsForSound(String sound){

        if(sound == null) return null;
        return getContinuationsForSound(new Sound(sound));
    }


    /*
    The Continuation Construction logic goes as follows:
    In order to optimize the run time, for each sound in a song,
    assign all the sounds to the right of such sound as a continuation.
    If the sound is ever found in another song, simply append the new continuation to its
    list. Traverse the sounds back-wards in order to only use one run of the for loop.
    * */
    private void defineSoundContinuationsForSong(Song song){

        if(song == null) return;

        defineEmptyContinuations(song);
        if(song.getNumberOfSounds() <= 1) return;

        Song currentContinuation = new Song();
        currentContinuation.appendSound(
                song.getSounds().get(song.getNumberOfSounds() - 1));

        for(int i = song.getNumberOfSounds() - 2; i >= 0; i--){
            Sound referenceSound = song.getSounds().get(i);
            Song continuation = new Song(currentContinuation.getSounds());
            _soundToContinuation.get(referenceSound).add(continuation);
            currentContinuation.getSounds().add(0, referenceSound);
        }
    }

    private void defineEmptyContinuations(Song song){

        if(song == null || song.getNumberOfSounds() == 0) return;

        for(Sound sound : song.getSounds()){
            if(_soundToContinuation.containsKey(sound)) continue;
            _soundToContinuation.put(sound, new HashSet<>());
        }
    }

}
