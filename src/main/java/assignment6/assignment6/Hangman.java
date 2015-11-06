package assignment6;


import java.util.HashSet;

public class Hangman {

	private int attempts;
	private String misteryWord;
	private boolean end;
	private boolean win;
	private HashSet<Character> missedChars;
	private HashSet<Character> guessedChars;
	private Character guess;
	
	public Hangman() {
		attempts = 9;
		misteryWord = "ciao";
		missedChars = new HashSet<Character>();
		guessedChars = new HashSet<Character>();
		end = false;
		win = false;
		guess = null;
	}
	
	public void checkLetter(Character c) {
		if (misteryWord.indexOf(c) == -1) {
			missedChars.add(new Character(Character.toLowerCase(c)));
			this.attempts--;
		}
		else {
			guessedChars.add(new Character(Character.toLowerCase(c)));
			if (guessedChars.size() == misteryWord.length()) {
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
