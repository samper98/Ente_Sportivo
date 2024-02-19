package org.generation.italy.JDBC_ente_sportivo.model.dao;

public class QueryCatalog {
	
    /////////////////////////// SELECT ///////////////////////////
	
	public static final String selectFromGaraByPrimaryKey =
            " SELECT id_gara, data_ora_gara, luogo	"
          + "   FROM gara                                  "
          + "  WHERE gara.id_gara = ?              	";
	
	public static final String selectFromVelocistaByPrimaryKey =
            " SELECT nominativo, eta, codice_fiscale, altezza, peso	"
          + "   FROM velocista                                  "
          + "  WHERE velocista.codice_fiscale = ?              	";
	
	public static final String selectFromIscrizioneByCodiceFiscale =
            " SELECT data_iscrizione, codice_fiscale, id_gara	"
          + "   FROM iscrizione                            "
          + "  WHERE iscrizione.codice_fiscale = ?              	";
	
	public static final String selectFromIscrizioneByIdGara =
            " SELECT data_iscrizione, codice_fiscale, id_gara	"
          + "   FROM iscrizione                            "
          + "  WHERE iscrizione.id_gara = ?              	";
	
	public static final String selectFromPartecipazioneByCodiceFiscale =
            " SELECT tempo, codice_fiscale, id_gara	"
          + "   FROM partecipazione                          "
          + "  WHERE partecipazione.codice_fiscale = ?              	";
	
	public static final String selectFromPartecipazioneByIdGara =
            " SELECT tempo, codice_fiscale, id_gara	"
          + "   FROM partecipazione                          "
          + "  WHERE partecipazione.id_gara = ?              	";
	
	public static final String selectFromGara =
			  " SELECT * " + 
			  "FROM gara   ";
	
	public static final String selectFromGaraOrderByLuogo=
			" SELECT *" +
	        "  FROM gara " +
		    " ORDER BY luogo" ;
	
	public static final String selectFromIscrizioneInnerJoinVelocista =
			"SELECT velocista.nominativo, velocista.eta " +
			 "FROM iscrizione " +
			 "INNER JOIN velocista  ON iscrizione.codice_fiscale = velocista.codice_fiscale " +
			 "WHERE iscrizione.id_gara = ? " ;
	
	
	public static final String selectFromPartecipazioneInnerJoinVelocista =
		    " SELECT velocista.nominativo, velocista.eta, partecipazione.tempo " +
		    " FROM partecipazione " +
		    " INNER JOIN velocista ON partecipazione.codice_fiscale = velocista.codice_fiscale " +
		    " INNER JOIN gara ON partecipazione.id_gara = gara.id_gara " +
		    " WHERE gara.id_gara = ? ";
	
	
	public static final String selectFromIscrioneeInnerJoinVelocista =
		    " SELECT velocista.nominativo, velocista.eta " +
		    " FROM iscrizione " +
		    " INNER JOIN velocista ON iscrizione.codice_fiscale = velocista.codice_fiscale " +
		    " INNER JOIN gara ON iscrizione.id_gara = gara.id_gara " +
		    " WHERE gara.id_gara = ? ";
	
	public static final String selectNumeroGareIscritteByCodiceFiscale =
			" SELECT   COUNT (iscrizione.id_gara) as nr_gare_iscritte" +
			" FROM iscrizione " +
			" WHERE  iscrizione.codice_fiscale = ?"  ;
	
  public static final String selectGaraOrderByDataAndOra =

            " SELECT * " +
            "FROM gara " +
            "ORDER BY gara.data_ora_gara " ; 
	 /////////////////////////// INSERT ///////////////////////////
	
	public static final String insertGara = 
			" INSERT INTO gara (data_ora_gara, luogo) VALUES (?, ?) ";
	
	public static final String insertVelocista = 
			" INSERT INTO velocista (nominativo, eta, codice_fiscale, altezza, peso) VALUES (?, ?, ?, ?, ?) ";
	
	public static final String insertIscrizione = 
			" INSERT INTO iscrizione (data_iscrizione, codice_fiscale, id_gara) VALUES (NOW(), ?, ?) ";
	
	public static final String insertPartecipazione = 
			" INSERT INTO partecipazione (tempo, codice_fiscale, id_gara) VALUES (?, ?, ?) ";
	
	
	
	////////////////////////// UPDATE ////////////////////////
	
	public static final String updateGara =
			" UPDATE gara " +
		  " SET " + 
		"	gara.luogo  = ? , " +
		" gara.data_ora_gara = ? " +
		" where gara.id_gara =  ?  " ;
	
	
	
	//////////////////////////DELETE //////////////////////
	public static final String deleteGara=
	" DELETE  " +
	" FROM gara " + 
	 " WHERE gara.id_gara = ? " ;
	
	public static final String deleteIscrizione=
			" DELETE iscrizione " +
			" FROM iscrizione " +
			" INNER JOIN velocista ON iscrizione.codice_fiscale = velocista.codice_fiscale " +
			" INNER JOIN gara ON iscrizione.id_gara = gara.id_gara " +
			" WHERE gara.id_gara = ? " +
			" AND velocista.nominativo = ? "  ;

}

