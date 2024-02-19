package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Partecipazione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Velocista;
import org.generation.italy.JDBC_ente_sportivo.model.entity.VelocistaPartecipanteGara;

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
				
			Partecipazione partecipazione = new Partecipazione (codiceFiscale,idGara,tempo);
			elencoPartecipazione.add(partecipazione);
			}

		} catch (SQLException sqlException) {
             System.out.println("errore " + sqlException.getMessage());
			throw new EnteSportivoModelException("PartecipazioneDao ===> loadPartecipazione" + sqlException.getMessage());
			
			// normalizzazione dell'eccezione SQLException

		}

		return elencoPartecipazione;

	}
	

	 public List<VelocistaPartecipanteGara> loadVelocistiPartecipantiGara(Long idGara) throws EnteSportivoModelException {
        Velocista velocista = null;

		List<VelocistaPartecipanteGara> elencoVelocistiPartecipantiGara = new ArrayList<VelocistaPartecipanteGara>();

		try {
          
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromPartecipazioneInnerJoinVelocista);
			
			
			
			preparedStatement.setLong(1, idGara);
			
			System.out.println(QueryCatalog.selectFromPartecipazioneInnerJoinVelocista);
			
			ResultSet rsSelect = preparedStatement.executeQuery();

			while (rsSelect.next()) {  // usare per due metodi 

				
				Integer eta = rsSelect.getInt("eta");
				if (rsSelect.wasNull()) {
					eta = 0;
				}

				
				Float tempo = rsSelect.getFloat("tempo");
				if (rsSelect.wasNull()) {
					tempo = 0f;
				}

				String nominativo = rsSelect.getString("nominativo");
				if (rsSelect.wasNull()) {
					nominativo = "";

				}
										
				VelocistaPartecipanteGara velocistaPartecipanteGara = new VelocistaPartecipanteGara( nominativo, eta, tempo);
			    elencoVelocistiPartecipantiGara.add(velocistaPartecipanteGara);
		//	elencoVelocistiPartecipantiGara = loadVelocistaByQuery(preparedStatement);

			

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("VelocistaDao -> loadVelocistaInnerJoin -> " + sqlException.getMessage());
		}

		return elencoVelocistiPartecipantiGara;
	}
	
}

