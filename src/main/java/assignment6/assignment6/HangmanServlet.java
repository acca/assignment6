package assignment6;


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

    Hangman hangman = null;
    String message = "";
    
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
		if (this.hangman == null ) this.hangman = new Hangman();
		String op = request.getParameter("op");
		
		if (!this.hangman.isFinish()) message = "INFO: Please insert a letter and press <b>Check</b> button or press <b>Reload</b> to change word";		
		
		if ( op != null ) {
			switch (op) {
    		case "Check":
    			if (!this.hangman.isFinish()) {
    				String param = request.getParameter("word");
        			// TODO Check for numbers
        			if ( (param != null) && (!param.equals("") && (param.length() == 1) ) ) {
        				this.hangman.checkLetter(param.charAt(0));
        				if (this.hangman.isFinish()) message = "<b>INFO: Congratulation you win !!!</b>";
        			}
        			else if (param != null) {			
        				message = "ERROR: This is not a letter that I can understand";
        			}	
    			}
    			else {
    				message = "INFO: Please press <b>reload</b> to play again";
    			}
    			break;
    		case "Reload":
    			this.hangman = new Hangman();
    			message = "INFO: Please insert a letter and press <b>Check</b> button or press <b>Reload</b> to change word";
    			break;
    		default:
    			// Operation non congrua
			}
		}
		else {
			//Nessuna richiesta, ricarica la pagina
		}
		
		
		//String reload = request.getParameter("reload");
		
		PrintWriter out = response.getWriter();
		out.println(htmlDynamicPage(message));
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
				"<input type=\"text\" name=\"word\" size=\"10\" maxlength=\"1\" autofocus/><br />" +
				"<input type=\"submit\" name=\"op\" value=\"Check\" checked/>" +
				"<input type=\"submit\" name=\"op\" value=\"Reload\" />" +				
				"<form>";
		return form;
	}
	
	private String pageHeader() {
		String header = "<HTML><HEAD><TITLE>Hangman game</TITLE></HEAD><HTML><HEAD><TITLE>Hangman game</TITLE></HEAD>";
		return header;
	}
	private String pageBody(String msg) {
		String body = "<body>";
		if ( msg != null ) body += "<span style=\"color:green;\">"+msg+"</span><br />";
		body += "<br />MISTERY WORD: " + this.hangman.getHiddenWord();
		body +=
				"<ul>"	+
				"<li>Last guessed: "+this.hangman.getGuess()+"</li>" +
				"<li>Already missed: "+this.hangman.getMiss()+"</li>" +
				"<li>Remaining attempts: "+this.hangman.getAttempts()+"</li>" +
				"</ul>";
						
		if (!this.hangman.isFinish()) {
			body += submitForm();
		}
		else {
			if (!hangman.isWinner()) body += "<br /><span style=\"color:red;\">YOU LOOSE: Mistery word was: <b>" + this.hangman.getMisteryWord() + "</span></b><br />";
			body += submitForm();
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
}
