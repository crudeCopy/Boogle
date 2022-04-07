# Boogle
A "Boggle" style game written in Java. Allows for play on a square board of a specified size (rather than just 4 or 5 like some others). It may be noted that the program doesn't love finding the words in a board larger than ten letters wide, but it's not very fun for the player either, with 1000+ words to be guessed.

The game draws its words from one of three dictionaries:

(EASY) powerlanglist.txt, which is a text file of 1000 of the most common English words. Curated by powerlanguage on GitHub: https://github.com/powerlanguage/word-lists/blob/master/1000-most-common-words.txt . Super easy, not many valid words are found.

(MEDIUM) ecpricelist.txt, another text file, this time from Eric Price's MIT site: https://www.mit.edu/~ecprice/wordlist.10000 . A little less esoteric than YAWL, and 20x smaller.

(HARD/IMPOSSIBLE) yawl.txt, a text file containing Yet Another Word List, a popular word game dictionary. When playing, the number of uncommonly used and/or nonsensical words formed on the board will be fairly large, so I may add another dictionary text file that is more fun (and possible) to play with.

# Game Rules
The program will prompt you for the board size first. Given input N, a board of NxN pseudorandomly generated letters will be built, and the program will hereupon start finding all valid words on this board. When this is done, the board will be shown on the prompt window.

From this point, you will be shown the number of unguessed words as well as the words you have already correctly guessed. If a word you guess is valid, the words remaining counter will go down and the word will be added to your list. Try to find all of the words on the board! If you can't, you can always guess "q" to quit and see what words you missed.
