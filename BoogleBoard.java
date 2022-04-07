
// author : connor chang
// purpose : a class that represents a board of letters, used in playing boogle

import java.util.*;

public class BoogleBoard {

  // class variables
  private BoogleLetter[][] board;
  private int width, height;

  /**
  helper function for the constructors, big ugly code to find and assign the adjacent letters
  **/
  private void assignAdjacents() {
    // iterate again, assigning "adjacent" BoogleLetters
    for(int x = 0; x < this.width; x++) {
      for(int y = 0; y < this.height; y++) {
        // finding adjacents for board[x][y]
        if(x > 0) {
          this.board[x][y].addAdjacent(this.board[x-1][y]);
          if(y > 0) {
            this.board[x][y].addAdjacent(this.board[x-1][y-1]);
          }
        }
        if(y > 0) {
          this.board[x][y].addAdjacent(this.board[x][y-1]);
          if(x < this.width - 1) {
            this.board[x][y].addAdjacent(this.board[x+1][y-1]);
          }
        }
        if(x < this.width - 1) {
          this.board[x][y].addAdjacent(this.board[x+1][y]);
          if(y < this.height - 1) {
            this.board[x][y].addAdjacent(this.board[x+1][y+1]);
          }
        }
        if(y < this.height - 1) {
          this.board[x][y].addAdjacent(this.board[x][y+1]);
          if(x > 0) {
            this.board[x][y].addAdjacent(this.board[x-1][y+1]);
          }
        }
      }
    }
  }
  /**
  by-size constructor
  @param width board width
  @param height board height
  **/
  public BoogleBoard(int width, int height) {
    this.width = width;
    this.height = height;
    this.board = new BoogleLetter[this.width][this.height];

    // iterate over all board elements, assign each a random letter
    int[] adj = new int[8];
    for(int x = 0; x < this.width; x++) {
      for(int y = 0; y < this.height; y++) {

        this.board[x][y] = new BoogleLetter();
      }
    }
    this.assignAdjacents();
  }
  /**
  by-contents constructor
  @param board the String 2d array that represents the board
  **/
  public BoogleBoard(String[][] stringBoard) {
    this.width = stringBoard.length;
    this.height = stringBoard[0].length;
    this.board = new BoogleLetter[this.width][this.height];

    // assign letters to the board
    for(int x = 0; x < this.width; x++) {
      for(int y = 0; y < this.height; y++) {
        this.board[x][y] = new BoogleLetter(stringBoard[x][y]);
      }
    }
    this.assignAdjacents();
  }

  /**
  returns a single BoogleLetter from the board at the given index
  @param x x-index of the letter
  @param y y-index of the letter
  **/
  public BoogleLetter getLetter(int x, int y) {
    return this.board[x][y];
  }



  /**
  helper for validWords that checks for words formed from one given letter
  @param currentLetter the BoogleLetter to begin with
  @param used the BoogleLetters that have already been used
  @param nug the previously collected letter sequence
  @param dict the BoogleDict to search through
  @param validSet the set to add the valid words to
  **/
  private void validWordsFromLetter(BoogleLetter currentLetter, HashSet<BoogleLetter> used, String nug, BoogleDict dict, HashSet<String> validSet) {
    // make a copy of the used letter set so that used letters in this branch don't show as used in another
    HashSet<BoogleLetter> newUsed = new HashSet<BoogleLetter>(used);

    // add new letter to nug, if it isn't already used
    if(newUsed.contains(currentLetter)) return;
    nug += currentLetter.toString();
    newUsed.add(currentLetter);

    // get words beginning with current word nugget, return if there are none
    BoogleDict newDict = dict.wordsBeginningWith(nug);
    if(newDict.getLength() == 0) return;

    // if the nug occurs exactly in the dict, add it to the set of valid words. either way, continue on to the adjacents
    if(newDict.contains(nug)) {
      validSet.add(nug);
    }
    for(int i = 0; i < currentLetter.getDegree(); i++) {
      this.validWordsFromLetter(currentLetter.getAdjacent(i), newUsed, nug, newDict, validSet);
    }
  }


  /**
  method to calculate all possible words and return them as a Set<String>
  @param dict a descending-order-sorted BoogleDict to find words in
  **/
  public HashSet<String> validWords(BoogleDict dict) {
    // make a string HashSet to hold the valid words
    HashSet<String> valid = new HashSet<String>();

    // iterate through every letter on the board
    for(int y = 0; y < this.height; y++) {
      for(int x = 0; x < this.width; x++) {
        this.validWordsFromLetter(this.board[x][y], new HashSet<BoogleLetter>(), "", dict, valid);
      }
    }
    return valid;
  }

  /**
  toString override, prints the board
  **/
  @Override
  public String toString() {
    String result = "";

    for(int y = 0; y < this.height; y++) {
      for(int x = 0; x < this.width; x++) {

        result += this.board[x][y] + " ";
        if(this.board[x][y].isOneLetter()) { result += " "; }

      }
      result += "\n";
    }
    return result;
  }



}
