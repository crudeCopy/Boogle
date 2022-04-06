# Boogle
A "Boggle" style game written in Java. Allows for play on a square board of a specified size (rather than just 4 or 5 like some others). It may be noted that the program doesn't love finding the words in a board larger than ten letters wide, but it's not very fun for the player either, with 1000+ words to be guessed.

The game draws its words from yawl.txt (included), which is a text file containing Yet Another Word List, a popular word game dictionary. When playing, the number of uncommonly used and/or nonsensical words formed on the board will be fairly large, so I may add another dictionary text file that is more fun (and possible) to play with.

# Game Rules
The program will prompt you for the board size first. Given input N, a board of NxN pseudorandomly generated letters will be built, and the program will hereupon start finding all valid words on this board. When this is done, the board will be shown on the prompt window.

From this point, you will be shown the number of unguessed words as well as the words you have already correctly guessed. If a word you guess is valid, the words remaining counter will go down and the word will be added to your list. Try to find all of the words on the board! This will take an hour on a board larger than 3, thanks to YAWL.
