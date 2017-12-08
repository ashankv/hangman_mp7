import java.util.*;
public class Hangman {
	
	public static HashMap<Integer, String> strikes = new HashMap<Integer, String>();
	public static String[] words = {"patrick", "pizza", "food", "sadness", "computer", "fun", "update",
			"gradle", "cipher", "console", "eclipse", "execution", "project", "illinois",
			"snow", "christmas", "california", "javadoc", "crossword", "tests"};
	
	
	public static void main(String[] args) {
		
		strikes.put(0, "| ————-|\n" + 
				"|                		\n" + 
				"|   		 \n" + 
				"|    		\n" + 
				"|\n" + 
				"|\n" + 
				"|");
		
		strikes.put(1, "| ————-|\n" + 
				"|      O\n" + 
				"|   		\n" + 
				"|   		 	\n" + 
				"|    		\n" + 
				"|\n" + 
				"|");
		
		strikes.put(2, "| ————-|\n" + 
				"|      O\n" + 
				"|      |\n" + 
				"|      |	\n" + 
				"|    		  \n" + 
				"|\n" + 
				"|");
		
		strikes.put(3, "| ————-|\n" + 
				"|      O\n" + 
				"|     /|\n" + 
				"|      |	\n" + 
				"|    		\n" + 
				"|\n" + 
				"|");
		
		strikes.put(4, "| ————-|\n" + 
				"|      O\n" + 
				"|     /|\\\n" + 
				"|      |	\n" + 
				"|    		 \n" + 
				"|\n" + 
				"|");
		
		strikes.put(5, "| ————-|\n" + 
				"|      O\n" + 
				"|     /|\\\n" + 
				"|      |	\n" + 
				"|     / \n" + 
				"|\n" + 
				"|\n" + 
				"");
		
		strikes.put(6, "| ————-|\n" + 
				"|      O\n" + 
				"|     /|\\\n" + 
				"|      |	\n" + 
				"|     / \\\n" + 
				"|\n" + 
				"|");
		
		
		
		int randNum = (int)(Math.random() * (words.length));
		String correctWord = words[randNum];
		ArrayList<String> alphabet = getAlphabet();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Hangman!");
		
		
		
		
		System.out.println(correctWord);
		
		boolean finishedGame = false;
		boolean hasWon = false;
		int numStrikes = 0;
		
		char[] userGuesses = new char[correctWord.length()];   // *****
		char[] correctCharArray = correctWord.toCharArray();   // tests
		
		for (int i = 0; i < userGuesses.length; i++) {
			userGuesses[i] = '*';
		}
		
		while(!finishedGame) {
			
			if(numStrikes == 6) {
				hasWon = false;
				break;
			}
			
			System.out.println();
			System.out.println();
			System.out.println(strikes.get(numStrikes));
			System.out.println();
			
			for (int i = 0; i < userGuesses.length; i++) {
				System.out.print(userGuesses[i]);
			}
			
			System.out.println();
			System.out.println("Enter a letter (lowercase) from the alphabet");
			
			String guess = input.next();
			alphabet.remove(guess);
			boolean isCorrectGuess = false;
			
			for (int i = 0; i < correctCharArray.length; i++) {
				if (guess.equals(Character.toString(correctCharArray[i]))) {
					userGuesses[i] = correctCharArray[i];
					isCorrectGuess = true;
				}
			}
			
			if (!isCorrectGuess) {
				
				numStrikes++;
				System.out.println("Incorrect guess.");
				
			} else {
				boolean areAllSame = true;
				for (int i = 0; i < userGuesses.length; i++) {
					if (userGuesses[i] != correctCharArray[i]) {
						areAllSame = false;
					}
				}
				
				if (areAllSame) {
					hasWon = true;
					finishedGame = true;
				}
			}	
		}
		
		if (hasWon) {
			System.out.println("Congratulations, you won! Your word was: " + correctWord);
		} else {
			System.out.println("Sorry, you lost. The correct word was: " + correctWord);
		}
		
		
		
	}
	
	
	public static ArrayList<String> getAlphabet() {
		ArrayList<String> alphabet = new ArrayList<String>();
		
		alphabet.add("a");
		alphabet.add("b");
		alphabet.add("c");
		alphabet.add("d");
		alphabet.add("e");
		alphabet.add("f");
		alphabet.add("g");
		alphabet.add("h");
		alphabet.add("i");
		alphabet.add("j");
		alphabet.add("k");
		alphabet.add("l");
		alphabet.add("m");
		alphabet.add("n");
		alphabet.add("o");
		alphabet.add("p");
		alphabet.add("q");
		alphabet.add("r");
		alphabet.add("s");
		alphabet.add("t");
		alphabet.add("u");
		alphabet.add("v");
		alphabet.add("w");
		alphabet.add("x");
		alphabet.add("y");
		alphabet.add("z");
		
		return alphabet;
	}
}
