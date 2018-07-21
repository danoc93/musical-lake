package main;

/**
 * main.Sound Class
 * Simple wrapper class to define a sound by its string incantation.
 * Allows the user to operate on sounds independently of the word cases.
 */

public class Sound
{
    private String _soundText;

    public Sound(String soundText){
        if(soundText == null) throw new NullPointerException();
        _soundText = soundText.trim().toLowerCase();
    }

    @Override
    public String toString(){

        return _soundText;
    }

    @Override
    public int hashCode(){

        return _soundText.hashCode();
    }

    @Override
    public boolean equals(Object sound2){

        return _soundText.equals(sound2.toString());
    }
}
