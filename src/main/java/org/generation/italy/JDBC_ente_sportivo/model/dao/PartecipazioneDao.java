package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Partecipazione;
import org.generation.italy.JDBC_ente_sportivo.model1.EnteSportivoModelException;

public class PartecipazioneDao extends ADao {

	public PartecipazioneDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}
	private List<Partecipazione> loadPartecipazioneByQuery(PreparedStatement preparedstatement) throws EnteSportivoModelException {

		List<Partecipazione> elencoPartecipazione = new ArrayList<Partecipazione>();

		try {

			ResultSet rsSelect = preparedstatement.executeQuery();

			while (rsSelect.next()) {

				Float tempo = rsSelect.getFloat("tempo");
				if (rsSelect.wasNull()) {
					tempo = 0f;
				}

				String codiceFiscale = rsSelect.getString("codice_fiscale");
				if (rsSelect.wasNull()) {
					codiceFiscale = "";

				}
				Long idGara = rsSelect.getLong("id_gara");
				if (rsSelect.wasNull()) {
					idGara = (long) 0;		
				}
								
			}

		} catch (SQLException sqlException) {
             System.out.println("errore " + sqlException.getMessage());
			throw new EnteSportivoModelException("PartecipazioneDao ===> loadPartecipazione" + sqlException.getMessage());
			
			// normalizzazione dell'eccezione SQLException

		}

		return elencoPartecipazione;

	}
	
	 public List<Partecipazione> loadGarePartecipate(Long idGara) throws EnteSportivoModelException {
         Partecipazione partecipazione = null;

		List<Partecipazione> elencoPartecipazione = new ArrayList<Partecipazione>();

		try {
           
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromPartecipazioneInnerJoinVelocista);
			
			System.out.println(QueryCatalog.selectFromPartecipazioneInnerJoinVelocista);
			
			preparedStatement.setLong(1, idGara);

			
			elencoPartecipazione = loadPartecipazioneByQuery(preparedStatement);

			if (elencoPartecipazione.size() == 1) {
				partecipazione = elencoPartecipazione.get(0);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("PartecipazioneDao -> loadPartecipazioneInnerJoinVelocista -> " + sqlException.getMessage());
		}

		return elencoPartecipazione;
	}
	
}

