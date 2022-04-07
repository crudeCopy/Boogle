
// author : connor chang
// purpose : a class that will hold a word list to draw from

import java.io.*;
import java.util.*;

public class BoogleDict {

  // member variables
  private HashSet<String> words;

  /**
  BoogleDict "from set" constructor, assigns words a copy of the given set
  @param givenWords the source set
  **/
  public BoogleDict(HashSet<String> givenWords) {
    this.words = new HashSet<String>(givenWords);
  }

  /**
  BoogleDict "from file" constructor, populates words with those from fileName
  @param fileName the location of the dictionary file
  **/
  public BoogleDict(String fileName) {
    // open dictionary file and scanner
    FileInputStream file = null;
    try {
      file = new FileInputStream(fileName);
    } catch(Exception e) {
      e.printStackTrace();
      return;
    }
    Scanner dictScan = new Scanner(file);

    // read file for words and put into set if it is longer than 2 letters
    this.words = new HashSet<String>();
    String currentWord;
    while(dictScan.hasNextLine()) {
      currentWord = (String)dictScan.nextLine();
      if(currentWord.length() > 2) this.words.add(currentWord);
    }

    // close the FileReader
    dictScan.close();
  }

  /**
  finds any words that start with letter, returns them in a new BoogleDict
  @param letter the letter grouping to find words starting with
  **/
  public BoogleDict wordsBeginningWith(String letter) {
    HashSet<String> newWords = new HashSet<String>();

    // iterate through this.words, add the ones that start with the given letter grouping
    Iterator wordValues = this.words.iterator();
    String currentWord;
    while(wordValues.hasNext()) {
      currentWord = (String)wordValues.next();
      if(currentWord.indexOf(letter) == 0) newWords.add(currentWord);
    }

    return new BoogleDict(newWords);
  }

  /**
  finds if there is an exact match for the given string in the dict, using HashSet.contains
  @param word the word to look for
  **/
  public boolean contains(String word) {
    return this.words.contains(word);
  }

  /**
  returns the number of words in the dict
  **/
  public int getLength() {
    if(this.words == null) {
      return 0;
    }
    return this.words.size();
  }

  /**
  toString override
  **/
  @Override
  public String toString() {
    return this.words.toString();
  }

}
