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

import org.generation.italy.JDBC_ente_sportivo.model.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model.TestJdbcEnteSportivo;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Gara;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Partecipazione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Velocista;

@WebServlet(urlPatterns = { "/homepage-velocista", "/visualizza-dettaglio", "/form-iscrizione", "/iscrizione"})
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
		System.out.println(request.getServletPath());
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
		System.out.println("Action name: " + actionName); // parte della URL dopo il nome della webapp...

		switch (actionName.toLowerCase().trim()) {

		// http://localhost:8080/JDBCente_sportivo/lista-gare
		case "/homepage-velocista":
			actionHomePageVelocista(request, response);

			// System.out.println("Azione intereccettata");
			break;
		case "/visualizza-dettaglio":
			System.out.println("Azione intercettata");
			// actionVisualizzaDettaglioGarePartecipate(request, response);
			// actionVisualizzaDettaglioGarePartecipate1(request, response);
			actionVisualizzaDettaglioGarePartecipate2(request, response);
			break;
		case "/form-iscrizione":
			System.out.println("azione :" + actionName);
			actionFormIscrizione(request, response);
			break;
		case "/iscrizione":
			System.out.println("azione"+ actionName);
			actionIscrizione(request, response);
			
			break;
		default:
			;
		}

	}

	private void actionHomePageVelocista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		String ordina = request.getParameter("ordinamento");

		List<Gara> elencoGare = new ArrayList<>();

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			if (ordina == null) {
				elencoGare = testJdbcEnteSportivo.getGaraDao().loadGara();
			} else if ("asc".equals(ordina)) {
				elencoGare = testJdbcEnteSportivo.getGaraDao().loadGaraOrderByLuogo();
			} else {
				elencoGare = testJdbcEnteSportivo.getGaraDao().loadGara();
			}
			request.setAttribute("listaGare", elencoGare);
			// HttpSession httpSession = request.getSession();
		} catch (EnteSportivoModelException e) {
			messageToShow = UserMessages.msgErroreVisualizzazioneLista;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("homepage-velocista.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);
	}

	private void actionVisualizzaDettaglioGarePartecipate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		System.out.println("Id gara" + id);
		List<Partecipazione> elencoPartecipanti = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoPartecipanti = testJdbcEnteSportivo.getPartecipazioneDao().loadGarePartecipate(id);

			System.out.println("Numero partecipanti" + elencoPartecipanti.size());

		} catch (Exception e) {
			System.out.println("errore loadGarePartecipate:" + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaPartecipanti", elencoPartecipanti); // ASSOCIARE E LAVORARE CON JSTL SU L'APPOSITA
																		// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-dettaglio.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}

	/*
	 * private void actionVisualizzaIscritte(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { String
	 * messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista; String iscritti
	 * = request.getParameter("visualizza-iscritti");
	 * 
	 * List<Iscrizione> elencoIscritti = new ArrayList<>(); try {
	 * TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
	 * elencoIscritti =
	 * testJdbcEnteSportivo.getIscrizioneDao().loadListaIscrittiGara();
	 * 
	 * request.setAttribute("listaIscritti", elencoIscritti);
	 * 
	 * }catch (EnteSportivoModelException e) { messageToShow =
	 * UserMessages.msgErroreVisualizzazioneListaIscritti; } RequestDispatcher
	 * dispatcher = request.getRequestDispatcher("elencoIscritti.jsp"); // ottiene
	 * il riferimento alla pagina JSP dispatcher.forward(request, response);
	 * 
	 * }
	 */

	public void actionIscrizione(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;
		Long id = Long.parseLong(request.getParameter("id-gara")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		System.out.println("Id gara" + id);
		// String dataOraIscrizioneString = request.getParameter("data_iscrizione") !=
		// null ? request.getParameter("data_iscrizione") : "";
		String codiceFiscaleString = request.getParameter("codice_fiscale") != null
				? request.getParameter("codice_fiscale")
				: "";

		// LocalDateTime dataOraiscrizione =
		// LocalDateTime.parse(dataOraIscrizioneString);
		Iscrizione iscrizione = new Iscrizione(codiceFiscaleString, id);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getIscrizioneDao().addIscrizione(iscrizione);

			messageToShow = UserMessages.msgEsitoOkIscrizione;
         
		} catch (EnteSportivoModelException e) {
			messageToShow = UserMessages.msgErroreIscrizione;
			// htmlContentPage = e.getMessage().getBytes();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
		// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
		// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}

	private static void actionFormIscrizione(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id-gara"));
		// throws BancaControlException, BancaModelException {
		
		request.setAttribute("id-gara", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("form-iscrizione.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}

	private void actionVisualizzaDettaglioGarePartecipate1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<Velocista> elencoVelocisti = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocisti = testJdbcEnteSportivo.getVelocistaDao().loadGarePartecipate(id);

			System.out.println("Numero partecipanti" + elencoVelocisti.size());

		} catch (Exception e) {
			System.out.println("errore loadGarePartecipate:" + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaPartecipanti", elencoVelocisti); // ASSOCIARE E LAVORARE CON JSTL SU L'APPOSITA
																	// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-dettaglio.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}

	private void actionVisualizzaDettaglioGarePartecipate2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<Gara> elencoVelocisti = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocisti = testJdbcEnteSportivo.getGaraDao().loadGarePartecipate(id);

			System.out.println("Numero partecipanti" + elencoVelocisti.size());

		} catch (Exception e) {
			System.out.println("errore loadGarePartecipate:" + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaPartecipanti", elencoVelocisti); // ASSOCIARE E LAVORARE CON JSTL SU L'APPOSITA
																	// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-dettaglio.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}

}
