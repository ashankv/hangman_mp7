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
		
		
		
		int randNum1 = (int)(Math.random() * (words.length));
		int randNum2 = (int)(Math.random() * (words.length));
		String correctWord1 = words[randNum1];
		String correctWord2 = words[randNum2];
		
		ArrayList<String> alphabet = getAlphabet();
		Scanner input = new Scanner(System.in);
		
		System.out.println("WELCOME TO HANGMEN!");
		//System.out.println(correctWord1);
		//System.out.println(correctWord2);
		
		boolean finishedGame1 = false;
		boolean finishedGame2 = false;
		boolean hasWon1 = false;
		boolean hasWon2 = false;
		int numStrikes1 = 0;
		int numStrikes2 = 0;
		
		char[] userGuesses1 = new char[correctWord1.length()];  
		char[] correctCharArray1 = correctWord1.toCharArray();   
		
		char[] userGuesses2 = new char[correctWord2.length()];   
		char[] correctCharArray2 = correctWord2.toCharArray();  
		
		for (int i = 0; i < userGuesses1.length; i++) {
			userGuesses1[i] = '*';		
		}
		
		for (int i = 0; i < userGuesses2.length; i++) {
			userGuesses2[i] = '*';
		}
		
		while(!finishedGame1 || !finishedGame2) {
			
			if(numStrikes1 == 6) {
				break;
			} else if(numStrikes2 == 6) {
				break;
			} else if(numStrikes1 == 6 && numStrikes2 == 6) {
				break;
			}
			
			System.out.println();
			System.out.println();
			System.out.print(strikes.get(numStrikes1));
			System.out.print(strikes.get(numStrikes2));
			System.out.println();
			System.out.println();

			System.out.print("Word 1: ");
			for (int i = 0; i < userGuesses1.length; i++) {
				System.out.print(userGuesses1[i]);
			}
			
			System.out.print("    Word 2: ");
			for (int i = 0; i < userGuesses2.length; i++) {
				System.out.print(userGuesses2[i]);
			}
			
			System.out.println();
			System.out.println("Enter a letter (lowercase) from the alphabet: ");
			
			String guess = input.next();
			guess = guess.toLowerCase();
			
			if (!alphabet.contains(guess)) {
				System.out.println("YOU'VE ALREADY ENTERED THIS LETTER. TRY AGAIN.");
				continue;
			}
			
			alphabet.remove(guess);
			boolean isFirstCorrect = false;
			boolean isSecondCorrect = false;
			
			for (int i = 0; i < correctCharArray1.length; i++) {
				if (guess.equals(Character.toString(correctCharArray1[i]))) {
					userGuesses1[i] = correctCharArray1[i];
					isFirstCorrect = true;
				}
			}
			
			for (int i = 0; i < correctCharArray2.length; i++) {
				if (guess.equals(Character.toString(correctCharArray2[i]))) {
					userGuesses2[i] = correctCharArray2[i];
					isSecondCorrect = true;
				}
			}
			
			if (!isFirstCorrect && !isSecondCorrect) {
				
				if (!finishedGame1 && !finishedGame2) {
					numStrikes1++;
					numStrikes2++;
				} else if (!finishedGame1 && finishedGame2) {
					numStrikes1++;
				} else if (finishedGame1 && !finishedGame2) {
					numStrikes2++;
				}
				
				if (!hasWon1 && !hasWon2) {
					System.out.println("Incorrect guess for both words.");
				} else if (!hasWon1 && hasWon2) {
					System.out.println("Incorrect guess for first word.");
				} else if (hasWon1 && !hasWon2) {
					System.out.println("Incorrect guess for second word.");
				}
				
			} else if (isFirstCorrect && !isSecondCorrect) {
				if (!finishedGame2) {
					numStrikes2++;
				}
				
				if (!hasWon2) {
					System.out.println("Correct guess for first word, but incorrect guess for second word");
				} else {
					System.out.println("Correct guess for first word.");
				}
				
			} else if (!isFirstCorrect && isSecondCorrect) {
				if (!finishedGame1) {
					numStrikes1++;
				}

				if (!hasWon1) {
					System.out.println("Correct guess for second word, but incorrect guess for first word.");
				} else {
					System.out.println("Correct guess for second word.");
				}
			} 
			
			boolean areAllSame1 = true;
			for (int i = 0; i < userGuesses1.length; i++) {
				if (userGuesses1[i] != correctCharArray1[i]) {
					areAllSame1 = false;
				}
			}
			
			boolean areAllSame2 = true;
			for (int i = 0; i < userGuesses2.length; i++) {
				if (userGuesses2[i] != correctCharArray2[i]) {
					areAllSame2 = false;
				}
			}
			
			if (areAllSame1 && areAllSame2) {
				hasWon1 = true;
				hasWon2 = true;
				finishedGame1 = true;
				finishedGame2 = true;
			} 
			
			if (areAllSame1 && !areAllSame2) {
				hasWon1 = true;
				finishedGame1 = true;
			} 
			
			if (!areAllSame1 && areAllSame2) {
				hasWon2 = true;
				finishedGame2 = true;
			}
		}
		
		System.out.println();
		System.out.println();
		if (hasWon1 && hasWon2) {
			System.out.println("Congratulations, you won! The first word was: " + correctWord1 + " and your second word was: " + correctWord2);
		} else if (hasWon1 && !hasWon2) {
			System.out.println("You ran out of lives and only got the first word! Your first word was: " + correctWord1 + " and your second word was: " + correctWord2);
		} else if (!hasWon1 && hasWon2) {
			System.out.println("You ran out of lives and only got the second word! Your first word was: " + correctWord1 + " and your second word was: " + correctWord2);
		} else {
			System.out.println("Sorry, you ran out of lives and didn't get any words. Your first word was: " + correctWord1 + " and your second word was: " + correctWord2);
		}
	}
		
	public static ArrayList<String> getAlphabet() {
		ArrayList<String> alphabet = new ArrayList<String>();
		
		for (int i = 97; i < 123; i++) {
			alphabet.add(Character.toString((char) i));
		}

		return alphabet;
	}
}
