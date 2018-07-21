package main;

import java.util.ArrayList;
import java.util.List;

/**
 * main.Song Class
 * A song is formed by several sounds, and can be dynamically modified
 * to allow an easier construction. It also offers string representations
 * of such song in order to make comparisons easier.
 */
public class Song
{
    private List<Sound> _sounds;
    private static String _split_delimiter = ",";

    public Song(){

        _sounds = new ArrayList<>();
    }

    public Song(List<Sound> sounds){

        this();
        if (sounds == null) return;
        _sounds.addAll(sounds);
    }

    public void appendSound(Sound sound){

        _sounds.add(sound);
    }

    public List<Sound> getSounds(){

        return _sounds;
    }

    public int getNumberOfSounds(){

        return _sounds.size();
    }

    @Override
    public String toString(){

        String songString = "(";

        for (int i = 0; i < _sounds.size(); i++){
            songString += _sounds.get(i).toString();
            if( i == _sounds.size() - 1 ) break;
            songString += ", ";
        }

        return songString + ")";
    }

    /*
    The following pair of functions allow you to build songs from external contexts.
    * */
    public static List<Song> buildSongsFromStringList(List<String> songStrings){

        List<Song> songs = new ArrayList<>();
        if(songStrings == null || songStrings.size() == 0) return songs;

        for (String song : songStrings){
            songs.add(buildSongFromString(song));
        }

        return songs;
    }

    public static Song buildSongFromString(String songString){

        if ("".equals(songString) || songString == null) return null;

        Song song = new Song();
        for(String token : songString.split(_split_delimiter)){
            song.appendSound(new Sound(token));
        }

        return song;
    }

    /*
    This function allows the user to define a delimiter to use in order to split
    the string representation of a main.Song whenever we build main.Song objects. Default: ,
    * */
    public static void setSplitDelimiter(String delimiter){
        if (delimiter == null) return;
        _split_delimiter = delimiter;
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object song2){

        return this.toString().equals(song2.toString());
    }

}
