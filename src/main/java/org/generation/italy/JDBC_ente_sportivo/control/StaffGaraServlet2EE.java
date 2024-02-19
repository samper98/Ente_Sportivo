package org.generation.italy.JDBC_ente_sportivo.control;

import java.io.IOException;
import java.time.LocalDateTime;
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
import org.generation.italy.JDBC_ente_sportivo.model.entity.VelocistaIscrizioneGara;
import org.generation.italy.JDBC_ente_sportivo.model.entity.VelocistaPartecipanteGara;

@WebServlet(urlPatterns = {"/homepage-staff-gara","/visualizza-dettaglio-staff","/visualizza-iscritti-staff",
		                   "/form-gara","/gara","/form-modifica-gara","/modifica-gara","/elimina-gara",
		                   "/elimina-iscritto"})
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
		case "/homepage-staff-gara":
			actionHomePageStaffGara(request, response);

			// System.out.println("Azione intereccettata");
			break;
		case "/visualizza-dettaglio-staff":
			System.out.println("Azione intercettata");
		
			actionVisualizzaDettaglioVelocistiPartecipantiGara(request, response);
			break;
		case "/visualizza-iscritti-staff":
			actionVisualizzaDettaglioVelocistiIscrittiGara(request, response);
			break;
		case "/form-gara":
			actionFormNuovaGara(request, response);
			break;
		case "/gara":
		  actionNuovaGara(request, response);
		break;
		case "/form-modifica-gara":
			actionFormModificaDatiGara(request, response);
			break;
		case "/modifica-gara":
			actionModificaDatiGara(request, response);
			break;
		case "/elimina-gara":
			actionEliminazioneGara(request, response);
	
		case  "/elimina-iscritto":
			actionEliminaIscritto(request, response);
		break;
		default:
			break;
			
		}

	}

	private void actionHomePageStaffGara(HttpServletRequest request, HttpServletResponse response)
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
			} else if ("data".equals(ordina)){
				elencoGare = testJdbcEnteSportivo.getGaraDao().loadGaraByDataAndOra();
			}
			request.setAttribute("listaGare", elencoGare);
			// HttpSession httpSession = request.getSession();
		} catch (EnteSportivoModelException e) {
			messageToShow = UserMessages.msgErroreVisualizzazioneLista;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("homepage-staff-gara.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);
	}

	

	

	private void actionVisualizzaDettaglioVelocistiPartecipantiGara(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<VelocistaPartecipanteGara> elencoVelocistaPartecipantiGara = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocistaPartecipantiGara = testJdbcEnteSportivo.getPartecipazioneDao()
					.loadVelocistiPartecipantiGara(id);

			System.out.println("Numero partecipanti" + elencoVelocistaPartecipantiGara.size());

		} catch (Exception e) {
			System.out.println("errore loadGarePartecipate:" + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaPartecipanti", elencoVelocistaPartecipantiGara); // ASSOCIARE E LAVORARE CON JSTL SU
																					// L'APPOSITA
		// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-dettaglio-staff.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}


	
	
	private void actionVisualizzaDettaglioVelocistiIscrittiGara(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<VelocistaIscrizioneGara> elencoVelocistiIscrittiGara = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocistiIscrittiGara = testJdbcEnteSportivo.getIscrizioneDao().loadVelocistiIscrittiGara(id);
			System.out.println("Numero partecipanti" + elencoVelocistiIscrittiGara.size());

		} catch (Exception e) {
			System.out.println("errore loadIscrittiGara: " + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaIscritti", elencoVelocistiIscrittiGara); // ASSOCIARE E LAVORARE CON JSTL SU
																					// L'APPOSITA
		// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-iscritti-staff.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}
	
	
	
	
	
	public void actionNuovaGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;

		 String dataOraGaraString = request.getParameter("data-gara") !=
		 null ? request.getParameter("data-gara") : "";
		String luogo = request.getParameter("luogo") != null
				? request.getParameter("luogo")
				: "";

		// LocalDateTime dataOraiscrizione =
		// LocalDateTime.parse(dataOraIscrizioneString);
		LocalDateTime dataGara = LocalDateTime.parse(dataOraGaraString);
		Gara gara = new Gara (dataGara,luogo);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getGaraDao().addGara(gara);

			messageToShow = UserMessages.msgEsitoOkGara;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreGara;
			// htmlContentPage = e.getMessage().getBytes();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
//		// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("message-staff.jsp");
		// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}

	private static void actionFormNuovaGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("form-gara.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}
	
	
	
	
	private static void actionFormModificaDatiGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
		Long id = Long.parseLong(request.getParameter("id-gara"));
		request.setAttribute("id-gara", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("form-modifica-gara.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}
	
	
	public void actionModificaDatiGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;

		Long id = Long.parseLong(request.getParameter("id-gara")); // get0 parameter ti salva la vita , getParameter
		// lavora
		// sui tipi di stringhe
		System.out.println("Id gara :" + id);
  String dataOraGaraString = request.getParameter("data-gara") !=  null ? request.getParameter("data-gara") : "";
String luogo = request.getParameter("luogo") != null ? request.getParameter("luogo") : "";

 LocalDateTime dataOraiscrizione = LocalDateTime.parse(dataOraGaraString);
 
		Gara gara = new Gara(id,dataOraiscrizione, luogo);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getGaraDao().updateGara(gara);

			messageToShow = UserMessages.msgEsitoOkModificaDatiGara;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreModificaGara;
// htmlContentPage = e.getMessage().getBytes();

// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("message-staff-modifica.jsp");
// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}		
	 
	
	
	public void actionEliminazioneGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException	{
		String messageToShow = UserMessages.msgOkEliminazione ;
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
		// sui tipi di stringhe
           request.setAttribute("id-gara", id);
           System.out.println("Id-gara" + id);
		   Gara gara =null;
           try {
   			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
   			 testJdbcEnteSportivo.getGaraDao().deleteGara(id);


	} catch (Exception e) {
		System.out.println("errore loadGarePartecipate:" + e.getMessage());
		messageToShow = UserMessages.msgErroreEliminazioneGara;
	}
           request.setAttribute("message-to-show", messageToShow);
        // imposta il parametro nominativoUtenteLoggato

        		RequestDispatcher dispatcher = request.getRequestDispatcher("message-staff-elimina.jsp");
        // ottiene il riferimento alla apgina JSP
        		dispatcher.forward(request, response);
	
	}
	
	
	public void actionEliminaIscritto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		List<VelocistaIscrizioneGara> elencoVelocistiIscrittiGara = new ArrayList<>();
		String messageToShow = UserMessages.msgOkEliminazione ;
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
		 String nominativo = request.getParameter("nominativo");// sui tipi di stringhe
           request.setAttribute("id-gara", id);
           request.setAttribute("nominativo", nominativo);
           System.out.println("Id-gara " + id);
           System.out.println("nominativo" + nominativo);
		   Iscrizione iscrizione = new Iscrizione(nominativo, id);
           try {
   			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
   			 testJdbcEnteSportivo.getIscrizioneDao().removeIscrizioneVelocista(nominativo, id);


	} catch (Exception e) {
		System.out.println("errore loadGarePartecipate:" + e.getMessage());
		messageToShow = UserMessages.msgErroreEliminazioneGara;
	}
           request.setAttribute("listaIscritti", elencoVelocistiIscrittiGara);
           
           request.setAttribute("message-to-show", messageToShow);
        // imposta il parametro nominativoUtenteLoggato

        		RequestDispatcher dispatcher = request.getRequestDispatcher("message-staff-elimina-iscritto.jsp");
        // ottiene il riferimento alla apgina JSP
        		dispatcher.forward(request, response);
	
	}
	
	
	
}
