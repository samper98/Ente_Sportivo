package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.entity.Gara;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;
import org.generation.italy.JDBC_ente_sportivo.model1.EnteSportivoModelException;

public class IscrizioneDao extends ADao {

	public IscrizioneDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}


	private List<Iscrizione> loadIscrizioneByQuery(PreparedStatement preparedstatement) throws EnteSportivoModelException {

		List<Iscrizione> elencoIscrizione = new ArrayList<Iscrizione>();

		try {

			ResultSet rsSelect = preparedstatement.executeQuery();

			while (rsSelect.next()) {

				Long idGara = rsSelect.getLong("id_gara");
				if (rsSelect.wasNull()) {
					idGara = (long) 0;
				}

				LocalDateTime dataOraIscrizione = rsSelect.getTimestamp("data_iscrizione").toLocalDateTime();
				if (rsSelect.wasNull()) {
					dataOraIscrizione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0));
				}

				String codiceFiscale = rsSelect.getString("codice_fiscale");
				if (rsSelect.wasNull()) {
					codiceFiscale = "";

				}
				Iscrizione iscrizione = new Iscrizione(codiceFiscale, idGara, dataOraIscrizione);
				elencoIscrizione.add(iscrizione);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao ===> loadIscrizione" + sqlException.getMessage());
			// normalizzazione dell'eccezione SQLException

		}

		return elencoIscrizione;

	}

	public Iscrizione loadIscrizioneByIdGara(Long idGara) throws EnteSportivoModelException {

		Iscrizione Iscrizione = null;

		try {

			List<Iscrizione> elencoIscrizione = new ArrayList<Iscrizione>();

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromIscrizioneByIdGara);

			preparedStatement.setLong(1, idGara);

			elencoIscrizione = loadIscrizioneByQuery(preparedStatement);

			if (elencoIscrizione.size() == 1) {
				Iscrizione = elencoIscrizione.get(0);
			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> loadIscrizioneByIdGara -> " + sqlException.getMessage());
		}

		return Iscrizione;
	}
    public List<Iscrizione> loadListaIscrittiGara(Long idGara) throws EnteSportivoModelException {
         Iscrizione iscrizione = null;

		List<Iscrizione> elencoIscrizioni = new ArrayList<Iscrizione>();

		try {
           
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromIscrizioneInnerJoinVelocista);
			
			preparedStatement.setLong(1, idGara);

			
			elencoIscrizioni = loadIscrizioneByQuery(preparedStatement);

			if (elencoIscrizioni.size() == 1) {
				iscrizione = elencoIscrizioni.get(0);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> loadIscrizioneDaoByInnerJoin -> " + sqlException.getMessage());
		}

		return elencoIscrizioni;
	}
	
    
    
	
	
	public void addIscrizione(Iscrizione iscrizione) throws EnteSportivoModelException {

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.insertIscrizione);

			preparedStatement.setTimestamp(1,Timestamp.valueOf(iscrizione.getDataOraIscrizione()));
			preparedStatement.setString(2, iscrizione.getCodiceFiscale());
			preparedStatement.setLong(3, iscrizione.getIdGara());

			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> addIscrizione  -> " + sqlException.getMessage());

		}
	}
}
