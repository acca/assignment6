package assignment6;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Hangman {

	private int attempts;
	private String misteryWord;
	private boolean end;
	private boolean win;
	private HashSet<Character> missedChars;
	private HashSet<Character> guessedChars;
	private Character guess;
	int counter;
	
	public Hangman() {
		attempts = 9;
		misteryWord = getRandomWord();
		missedChars = new HashSet<Character>();
		guessedChars = new HashSet<Character>();
		end = false;
		win = false;
		guess = null;
		counter = 0;
	}
	
	private String getRandomWord() {
		ArrayList<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("webarchitecture");
		words.add("sun");
		words.add("unreachable");
		words.add("tree");
		words.add("animal");
		words.add("telephone");
		words.add("water");
		words.add("science");
		words.add("intermolecular");
		words.add("nobody");
		words.add("elements");		
		Random randomGenerator;
		randomGenerator = new Random();
		int index = randomGenerator.nextInt(words.size());
		return words.get(index);
	}

	public void checkLetter(Character c) {
		if (misteryWord.indexOf(c) == -1) {
			missedChars.add(new Character(Character.toLowerCase(c)));
			this.attempts--;
		}
		else {			
			guessedChars.add(new Character(Character.toLowerCase(c)));
			int count = misteryWord.length() - misteryWord.replace(c.toString(), "").length();
			counter += count;
			if (counter == misteryWord.length()) {
				this.end = true;
				this.win = true;
			}
		}
		this.guess = c;
		if (attempts == 0) this.end = true;
	}
	
	public String getHiddenWord(){
		String hiddenString = "";
		if (this.end) {
			StringBuilder sb = new StringBuilder();
			for (char c: misteryWord.toCharArray()) {
				   sb.append(c).append(" ");
				}			
			hiddenString = sb.toString();
		}
		else {
			for (int i = 0; i < misteryWord.length(); i++) {
		         if ( guessedChars.contains(misteryWord.toLowerCase().charAt(i)) ) {
		             hiddenString += misteryWord.charAt(i)+" ";
		         }
		         else {
		        	 hiddenString += "_ ";
		         }
		    }	
		}		
		return hiddenString;
	}

	public String getMisteryWord() {
		return this.misteryWord;
	}

	public boolean isFinish() {
		return this.end;
	}
	
	public boolean isWinner() {
		return this.win;
	}

	public String getGuess() {
		if (guess != null) {
			return this.guess.toString();	
		}
		else return new Character('_').toString();
		
	}

	public String getMiss() {
		return this.missedChars.toString();
	}

	public String getAttempts() {
		return ""+this.attempts;
	}
}
