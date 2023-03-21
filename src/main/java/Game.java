import java.util.ArrayList;

public class Game {
    static int wrongLetters = 0;
    static int numberOfChances = 10;
    static ArrayList<Character> wrongChoices = new ArrayList<Character>();
    public String getMovieTitle(ArrayList<String> movieList) {
        int size = movieList.size();
        int index = (int) (Math.random() * size);
        String movieChoice = movieList.get(index);
        return movieChoice;
    }

    public int checkGuess(char userInput, String placeHolder, String maskedTitle, StringBuilder sb) {
        if(placeHolder.indexOf(userInput) != -1) {
            System.out.println("You are guessing: "+ maskedTitle);
            System.out.println("You have guessed (" + wrongChoices.size() + ") wrong letters:" + wrongChoices);
        } else {
            if( wrongLetters == 0 || (wrongLetters >= 1 && placeHolder.indexOf(userInput) == -1)) {
                wrongLetters++;
                numberOfChances--;
                wrongChoices.add(userInput);
            }
            System.out.println("You are guessing: "+ maskedTitle);
            System.out.println("You have guessed (" + wrongChoices.size() + ") wrong letters:" + wrongChoices);
        }
        return numberOfChances;
    }
}
