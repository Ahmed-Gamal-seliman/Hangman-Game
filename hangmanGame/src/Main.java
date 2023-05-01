import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.*;
public class Main {

 /*
 @brief Filling Data of words that will be random
 */
static void fillDataOfWords(List<String> words)
    {
        words.add("apple");
        words.add("banana");
        words.add("rice");
        words.add("meat");
        words.add("bread");
        words.add("potato");
    }

/*
@brief Generate Random words index
*/
static int generateRandomWords()
    {
        Random rand = new Random();
        return rand.nextInt(6);
    }

/*
@brief Generate And print this shape [-,-,-,-]
@param wordsLength to draw this dashes [-] according to the length of word
*/
static String generateAndPrintShape(int wordLength)
    {
        String printedString=new String("");
        for (int i = 0; i < wordLength; i++) {
            if (i == wordLength - 1)
                printedString = printedString + "-";
            else
                printedString = printedString + "-,";
        }
        return printedString;
    }

/*
@brief check if the player Loses the game or not accroding to the Lives he have
@param Lives is the number of lives the player have
*/
static boolean isLose(int Lives)
    {
        if(Lives == 0)
            return true;
        return false;
    }

/*
@brief check if the player win the game or not if the number of trials is equal to wordLength then it means that all fields he guess is right
@param trueTrials is Number of trials that the Letter he write is correct
@param wordLength is the length of the word
*/
static boolean isWin(int trueTrials,int wordLength)
    {
        if(trueTrials == wordLength)
            return true;
        return false;
    }

/*
@brief check if the player guess the right letter or not
@param Letter is the letter that the player enter
@param wordLetter is the letter that is in the word that is randomly generated
*/
static boolean isRightGuess(char Letter , char wordLetter){
        if(Letter == wordLetter)
            return true;
        return false;
}


 public static void main(String[] args) {

        System.out.println("Let's play Hangman!!\nYou have only 6 lives so try to guess the word within 6 attempts! Good luck !!");

        Scanner input = new Scanner(System.in);

        List<String> words = new ArrayList<String>();
        fillDataOfWords(words);

        int wordIndex=generateRandomWords();

        int remainingCharcterIndex = 0;
        boolean flag= true;
        int Lives=6;
        int trueTrials=0;
        String printedString = "";
        String word = words.get(wordIndex);
        int wordLength = word.length();

        char Letter = 0;
        System.out.println(word);


        while (true)
        {

            System.out.print("[");
            System.out.print(printedString);
            if(flag)
            {
               printedString=generateAndPrintShape(wordLength);
                System.out.print(printedString);
                flag=false;
            }


            System.out.println("]");
            System.out.println("Guess a letter: ");
            Letter = input.next().charAt(0);

            /*
            @brief Check it is right guess
            */

            if(isRightGuess(Letter , word.charAt(remainingCharcterIndex)))
            {
                int index=printedString.indexOf('-');
                printedString=printedString.substring(0,index)+Letter+printedString.substring(index+1);
                remainingCharcterIndex++;
                trueTrials++;
            }
            else
            {
                Lives--;
                System.out.println("You guessed u that is not present in the word. so you lose a life [you have " + Lives + " lives]");
            }

            /*
            @brief Check Player is Win
            */
            if(isWin(trueTrials,wordLength))
            {
                System.out.println("[" + printedString + "]");
                System.out.println("You Win");
                break;
            }

            /*
            @brief Check Player is Lose
            */
            else if(isLose(Lives))
            {
                System.out.println("You Lose!");
                break;
            }

        }
    }
}
