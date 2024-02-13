package org.generation.italy.JDBC_ente_sportivo.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.generation.italy.JDBC_ente_sportivo.model.entity.Gara;
import org.generation.italy.JDBC_ente_sportivo.model1.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model1.TestJdbcEnteSportivo;

@WebServlet(urlPatterns = {"/lista-gare"})
public class StaffGaraServlet2EE extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() { // metodo che viene richiamato dal container al momento della installazione
							// della webapp in esso con mappatura della servlet (l'altro è 'destroy' (al
							// momento della rimozione della servlet dal container), non gestito in questa
							// servlet).
		// utenteDAO = new UtenteDAO();
		
		System.out.println("init eseguito");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal container
																					// (GlassFish), a seguito di
																					// ricezione da parte sua del
																					// messaggio HTTP-Request, con
																					// metodo POST inviato dal client
																					// (browser)
			throws ServletException, IOException {
		executeAction(request, response); // re-inoltra al metodo doGet la gestione della action | request e response
											// sono istanze di tipo HttpServletRequest ed HttpServletResponse, create
											// dal container per fornire a e ricevere dalla servlet i dettagli circa i
											// messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al
											// client.
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal container
																					// (GlassFish), a seguito di
																					// ricezione da parte sua del
																					// messaggio HTTP-Request, con
																					// metodo GET inviato dal client
																					// (browser)
			throws ServletException, IOException {
		System.out.println("DO GET ENTRATO");
		executeAction(request, response); // re-inoltra al metodo doGet la gestione della action | request e response
											// sono istanze di tipo HttpServletRequest ed HttpServletResponse, create
											// dal container per fornire a e ricevere dalla servlet i dettagli circa i
											// messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al
											// client.

	}

	protected void executeAction(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal
																							// container (GlassFish), a
																							// seguito di ricezione da
																							// parte sua del messaggio
																							// HTTP-Request, con metodo
																							// GET inviato dal client
																							// (browser)
			throws ServletException, IOException {
  
		String actionName = request.getServletPath(); // parte action della URI: gestione della azione applicativa, la
														// parte della URL dopo il nome della webapp...

		switch (actionName.toLowerCase().trim()) {

		//http://localhost:8080/JDBCente_sportivo/lista-gare
		case "/lista-gare":
			   actionVisualizzaGare(request, response);
			  // System.out.println("Azione intereccettata");
			break;

		default:
			;
		}

	}

	private void actionVisualizzaGare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		List<Gara> elencoGare = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoGare=testJdbcEnteSportivo.getGaraDao().loadGara();
			request.setAttribute("listaGare", elencoGare);
			// HttpSession httpSession = request.getSession(); 

		} catch (EnteSportivoModelException e) {
			messageToShow = UserMessages.msgErroreVisualizzazioneLista;

		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-gara.jsp");
        //ottiene il riferimento alla apgina JSP
        dispatcher.forward(request, response);

		
	}
}


