
// author : connor chang
// purpose : a class that will hold a word list to draw from

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class BoogleDict {

  // member variables
  private String [] words;

  /**
  BoogleDict "from array" constructor, assigns words a copy of the given array
  @param words the source array
  **/
  public BoogleDict(String [] words) {
    this.words = Arrays.copyOf(words, words.length);
  }

  /**
  BoogleDict "from file" constructor, populates words with those from fileName
  @param fileName the location of the dictionary file
  @param length the number of words in the file
  **/
  public BoogleDict(String fileName, int length) {

    // open dictionary file
    FileInputStream file = null;
    try {
      file = new FileInputStream(fileName);
    } catch(Exception e) {
      e.printStackTrace();
      return;
    }
    Scanner dict = new Scanner(file);

    this.words = new String[length];

    // read
    int wordIndex = 0;
    String word;
    while(wordIndex < length && dict.hasNextLine()) {
      word = dict.nextLine();
      words[wordIndex] = word;
      wordIndex++;
    }

    // close the FileReader
    dict.close();
  }

  public BoogleDict wordsBeginningWith(String letter) {

    // find indexes of start/end of words starting with letter
    int start = 0, end = this.words.length - 1;
    boolean stillGoing = true;

    for(int i = 0; i < this.words.length && stillGoing; i++) {
      if(this.words[i].indexOf(letter) == 0) {
        start = i;
        stillGoing = false;
      }
    }

    stillGoing = true;
    for(int i = this.words.length - 1; i >= start && stillGoing; i--) {
      if(this.words[i].indexOf(letter) == 0) {
        end = i + 1;
        stillGoing = false;
      }
    }
    if(stillGoing) {
      end = start;
    }

    // return BoogleDict from subarray of this.words
    return new BoogleDict(Arrays.copyOfRange(this.words, start, end));
  }

  /**
  checks for an exact match in the dictionary, using binary search
  @param word the word being checked for
  **/
  public boolean contains(String word) {
    int upper = this.words.length,
        lower = 0,
        middle;

    while(upper >= lower) {
      middle = (int) ((upper + lower) / 2);
      // if less than
      if(word.compareTo(this.words[middle]) < 0) {
        upper = middle - 1;
      // if greater than
      } else if(word.compareTo(this.words[middle]) > 0) {
        lower = middle + 1;
      // if equal to
      } else {
        return true;
      }
    }
    return false;
  }

  /**
  accessor for individual words in the dictionary
  @param i index of the word
  **/
  public String getWord(int i) {
    return this.words[i];
  }

  /**
  returns whether or not the BoogleDict is empty
  **/
  public int getLength() {
    if(this.words == null) {
      return 0;
    }
    return this.words.length;
  }

  /**
  toString override. takes really long for big dictionaries, use at own risk
  **/
  @Override
  public String toString() {
    String result = "";

    for(String word : this.words) {
      result += word + "\n";
    }
    return result;
  }

}
