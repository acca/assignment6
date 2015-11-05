

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangmanServlet
 */
public class HangmanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String misteryWord = "ciao";
    int attempts = 9;
    boolean end = false;
    HashSet<Character> usedLetters = new HashSet<Character>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HangmanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String reload = request.getParameter("reload");
		String param = request.getParameter("word");
		String errorMsg = null;
		// TODO Check for numbers
		if ( (param != null) && (!param.equals("") && (param.length() == 1) ) ) {
			usedLetters.add(new Character(param.charAt(0)));
			attempts--;
			if (attempts == 0) end = true;
		}
		else if (param != null) {			
			errorMsg = "ERROR: This is not a letter that I can understand";
		}
		else if (reload != null) {
			usedLetters = new HashSet<Character>();
			attempts = 9;
		}
		PrintWriter out = response.getWriter();
		out.println(htmlDynamicPage(errorMsg));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String submitForm(){
		String form = 
				"<form method=\"get\" action=\"HangmanServlet\">" +
				"<input type=\"text\" name=\"word\" />" +
				"<input type=\"submit\" value=\"Check word\" />" +
				"<form>";
		return form;
	}
	
	private String reloadForm(){
		String form = 
				"<form method=\"get\" action=\"HangmanServlet\">" +
				"<input type=\"submit\" name=\"reload\" value=\"Reload\" />" +
				"<form>";
		return form;
	}
	
	private String pageHeader() {
		String header = "<HTML><HEAD><TITLE>Hangman game</TITLE></HEAD><HTML><HEAD><TITLE>Hangman game</TITLE></HEAD>";
		return header;
	}
	private String pageBody(String errorMsg) {
		String body = "<body>";
		if ( errorMsg != null ) body += "<span style=\"color:red;\">"+errorMsg+"</span><br />";
		body += printWord();
		//results
		if (!end) {
			body += submitForm();
		}
		else {
			body += "Mistery word was: " + misteryWord;
			body += reloadForm();
		}
		
		body += "</body>";
		return body;
	}
	private String pageFooter() {
		return "</html>";
	}
	
	private String htmlDynamicPage(String errorMsg) {		
		String page = pageHeader();
		page += pageBody(errorMsg);
		page += pageFooter();
		return page;
	}
	
	private String printWord(){
		String hiddenString = "";
		for (int i = 0; i < misteryWord.length(); i++) {
	         if ( usedLetters.contains(misteryWord.charAt(i)) ) {
	             hiddenString += misteryWord.charAt(i);
	         }
	         else {
	        	 hiddenString += "_";
	         }
	    }
				
		return hiddenString;
	}
}
