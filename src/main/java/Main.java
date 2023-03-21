import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int remainingChances = 10;
    public static void main(String [] args) throws Exception {
        File file = new File("C:\\Users\\Kyalo\\Desktop\\Java\\GuessMovie\\src\\main\\java\\movies.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> movieList = new ArrayList<>();
        while(sc.hasNextLine()) {
            movieList.add(sc.nextLine());
        }
        Game game = new Game();
        String movieChoice = game.getMovieTitle(movieList);
        String placeHolder = movieChoice.replaceAll("[^\\S]","");
        String maskedTitle = placeHolder.replaceAll("[a-zA-Z0-9]","_");
        StringBuilder sb = new StringBuilder(maskedTitle);
        System.out.println("The movie title has " + maskedTitle.length() + " letters.");
        System.out.println("You are guessing: " + maskedTitle);
        while(remainingChances > 0 && (!(movieChoice.equals(maskedTitle)))) {
            System.out.println("Guess a letter: ");
            Scanner read = new Scanner(System.in);
            char userInput = read.next().charAt(0);
            if(( (userInput >= '0' && userInput <='9'))) {
                System.out.println("Invalid input. Please Guess a letter:");
                continue;
            }
            int unmaskIndex = placeHolder.indexOf(userInput);

            //setting matched user input letters to masked movie title
            if( unmaskIndex != -1) {
                sb.setCharAt(unmaskIndex, userInput);
            }
            while( unmaskIndex >= 0) {
                unmaskIndex = placeHolder.indexOf(userInput,unmaskIndex+1 );//search for next matching index
                //if more than one occurrence of the letter exist in the title, unmask them all
                if( unmaskIndex != -1) {
                    sb.setCharAt(unmaskIndex, userInput);
                }
            }
            maskedTitle = sb.toString();
            remainingChances = game.checkGuess(userInput,placeHolder,maskedTitle,sb);
            if(maskedTitle.equals(placeHolder)) {
                System.out.println("\nYou win! You have guessed '"+movieChoice+"' correctly.");
                break;
            }
        }
        if(!(maskedTitle.equals(placeHolder))) {
            System.out.println("You failed. Better luck next time. The title we were looking for is "+ movieChoice);
        }
    }
}
