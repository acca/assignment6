

import java.util.HashSet;

public class Hangman {

	int attempts;
	String misteryWord;
    boolean end = false;
    HashSet<Character> usedLetters;
	
	public Hangman() {
		attempts = 9;
		misteryWord = "ciao";
		usedLetters = new HashSet<Character>();
		
	}
	
	public void checkLetter(Character c) {
		usedLetters.add(new Character(Character.toLowerCase(c)));
	}
	
	public String getWord(){
		String hiddenString = "";
		for (int i = 0; i < misteryWord.length(); i++) {
	         if ( usedLetters.contains(misteryWord.toLowerCase().charAt(i)) ) {
	             hiddenString += misteryWord.charAt(i);
	         }
	         else {
	        	 hiddenString += "_";
	         }
	    }
		return hiddenString;
	}
}
