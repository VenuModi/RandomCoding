package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);//Scanner for user input
        System.out.println("Single Player or 2 Players");
        String players = keyboard.nextLine();
        String word;

        if (players.equals("1")){
            Scanner sc = new Scanner(new File("/Users/venumodi/IdeaProjects/Hangman/src/Hangman.txt"));


            List<String> words = new ArrayList<>();

            while (sc.hasNext()){
                words.add(sc.nextLine());
            }
            Random random = new Random();

            word = words.get(random.nextInt(words.size()));// random list of words from my list

        }
        else {
            System.out.println("Player 1 - Please enter your word");
            word = keyboard.nextLine();
            System.out.println("""
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n+
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n +
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n +
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n +
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n +
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n     +
                    \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n
                    """);
            System.out.println("Ready for Player 2!! Enter your Guess!");

        }
        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;

        while (true){


            printHangedMan(wrongCount);
            if (wrongCount >= 6){
                System.out.println("You Lost!!");
                System.out.println("The word was: " + word);
                break;
            }


            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)){
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)){
                System.out.println("You win!!");
                break;
            }

            System.out.println("Please enter your guess for the word: ");
            if (keyboard.nextLine().equals(word)){
                System.out.println("You win!!");
                break;
            }
            else{
                System.out.println("Nope!! Try again");
            }
        }


    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" --------");
        System.out.println(" |-------|");
        if (wrongCount >= 1){
            System.out.println(" O");
        }
        if (wrongCount >= 2){
            System.out.print("\\ ");
            if (wrongCount >= 3){
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }
        if (wrongCount >=4){
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));//Guess added to the list

        return (word.contains(letterGuess));
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains((word.charAt(i)))){
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("_");
            }
        }
        System.out.println();
        return word.length() == correctCount;
    }
}