
// author : connor chang
// purpose : the "main" class for boogle

import java.util.*;
import java.io.IOException;

public class Boogle{
  // "member" variables -- here so that i don't need to pass them to updateScreen
  public static int wordsRemaining;
  public static BoogleBoard board;
  public static HashSet<String> guessed, unguessed;
  public static final String open = "$$$$$$$$$$$$$$$$$$$$$$$$$$$\n$                         $\n$ Boogle, based on Boggle $\n$     Connor Chang '22    $\n$                         $\n$$$$$$$$$$$$$$$$$$$$$$$$$$$";

  /**
  clear the screen
  **/
  public static void cls() {
    try{ /// TODO: provid for linux use??
      new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    } catch(Exception e) {
      return;
    }
  }
  /**
  refreshes the screen, i.e. when new word guessed
  **/
  public static void updateScreen() {
    // clear the screen
    cls();

    // print the info
    System.out.println(open + "\n\n");
    System.out.print("BOARD:\n" + board + "\n\nWords Remaining: " + wordsRemaining + "\n\nGuessed:\n" + guessed + "\n\n");
  }

  ////////////////////////////////// MAIN FUNCTION ////////////////////////////////////////////

  public static void main(String [] args) {
    // scanner for user input
    Scanner sc = new Scanner(System.in);

    // prompt user for board size
    System.out.print(open + "\n\n\nBOARD SIZE: ");
    int size = sc.nextInt();

    // inits
    board = new BoogleBoard(size,size);
    BoogleDict yawl = new BoogleDict("yawl.txt");

    unguessed = new HashSet<String>(board.validWords(yawl));
    guessed = new HashSet<String>();

    // play loop
    boolean allWordsFound = false;
    String wordGuess = "";
    wordsRemaining = unguessed.size();
    while(!allWordsFound && !wordGuess.equals("Q")) {
      updateScreen();

      System.out.println("Guess a word: ");
      wordGuess = sc.next().toUpperCase();

      if(unguessed.contains(wordGuess)) {
        guessed.add(wordGuess);
        unguessed.remove(wordGuess);
      }

      wordsRemaining = unguessed.size();

      if(wordsRemaining == 0) {
        allWordsFound = true;
      }
    }
    if(allWordsFound) {
      System.out.println("ALL WORDS FOUND!");
    } else {
      double percentGuessed = (double)guessed.size()*100.0/(guessed.size()+wordsRemaining);
      System.out.println("GUESSED: " + percentGuessed + "%\nUNGUESSED: " + unguessed);
    }
  }
}
