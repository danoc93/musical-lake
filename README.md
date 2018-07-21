# musical-lake


The following definitions may help clarify some things:


Sound: A simple incantation e.g. brr, fiu, etc.

Song: A set of sounds.


The code was created by adding a few modules and the corresponding helper functions.

The SongStorage class allows the user to store a set of songs, and to operate on such songs by getting for example all the possible continuations for a given sound.

The Main class simply instanciates the mentioned class and plays with it by allowing user input!

There is a Test Suite created using JUnit5 which covers the basic scenarios.

The main parts of the code are explained in comments, however here is a simplification of the process used to obtain the combinations.

1. Take the songs, tokenize them, store them.
2. Create a map Sound -> Set of Song
3. Add each sound in a song as key in the map, and add every sound to its right to a new Song. 
This new song is a continuation for such sound and thus you can add it to the set corresponding to that key.
4. Since we are using a Set, by definition this data structure does not allow duplicates, which makes it easy for us not to include two or more identical continations for a same sound. In order for this to work with custom objects, we override the hashing and equals methods of the Song class to compare the string representation of the code.

Building the database is O(n) in terms of the full dictionary of tokens and O(1) when it comes to looking up continuations for a sound.

The tool ensures only valid sounds return items, else null is returned and can be handled accordingly (e.g. show a message like I do in Main.java).
