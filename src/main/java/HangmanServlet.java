

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangmanServlet
 */
public class HangmanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		PrintWriter out = response.getWriter();
		out.println(htmlDynamicPage());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String htmlForm(){
		String form = "<form method=\"get\" action=\"HangmanServlet\">"
				+ "<input type=\"text\" name=\"word\" />"
				+ "<input type=\"submit\" value=\"Check word\" />"
				+ "<form>";        
		return form;
	}
	private String pageHeader() {
		String header = "<HTML><HEAD><TITLE>Hangman game</TITLE></HEAD><HTML><HEAD><TITLE>Hangman game</TITLE></HEAD>";
		return header;
	}
	private String pageBody() {
		String body = "<body>";
		//word
		//results
		body += htmlForm();
		body += "</body>";
		return body;
	}
	private String pageFooter() {
		return "</html>";
	}
	
	private String htmlDynamicPage() {		
		String page = pageHeader();
		page += pageBody();
		page += pageFooter();
		return page;
	}
}
