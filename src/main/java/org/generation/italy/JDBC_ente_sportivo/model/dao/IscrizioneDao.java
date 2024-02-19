package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Velocista;
import org.generation.italy.JDBC_ente_sportivo.model.entity.VelocistaIscrizioneGara;

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

	public List <Iscrizione> loadIscrizioneByIdGara(Long idGara) throws EnteSportivoModelException {

		
		List<Iscrizione> elencoIscrizione = new ArrayList<Iscrizione>();
		try {

	

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromIscrizioneByIdGara);

			preparedStatement.setLong(1, idGara);

			elencoIscrizione = loadIscrizioneByQuery(preparedStatement);

		
		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> loadIscrizioneByIdGara -> " + sqlException.getMessage());
		}

		return elencoIscrizione;
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
	
    
    public void loadIscrizioniSvolte(String CodiceFiscale)  throws EnteSportivoModelException {
    	try {
            String codiceFiscale="";
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectNumeroGareIscritteByCodiceFiscale);
			
			preparedStatement.setString(1, codiceFiscale);
			 ResultSet resultSet = preparedStatement.executeQuery();
	        	        
    
			}

		 catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> loadIscrizioneDaoByInnerJoin -> " + sqlException.getMessage());
		}

 
    	
    	
    	
    }
    
    
    public List<VelocistaIscrizioneGara> loadVelocistiIscrittiGara(Long idGara) throws EnteSportivoModelException {
        Velocista velocista = null;

		List<VelocistaIscrizioneGara> elencoVelocistiIscrittiGara = new ArrayList<VelocistaIscrizioneGara>();

		try {
          
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromIscrizioneInnerJoinVelocista);
			
			
			
			preparedStatement.setLong(1, idGara);
			
			System.out.println(QueryCatalog.selectFromIscrizioneInnerJoinVelocista);
			
			ResultSet rsSelect = preparedStatement.executeQuery();

			while (rsSelect.next()) {  // usare per due metodi 

				
				int eta = rsSelect.getInt("eta");
				if (rsSelect.wasNull()) {
					eta = 0;
				}

			

				String nominativo = rsSelect.getString("nominativo");
				if (rsSelect.wasNull()) {
					nominativo = "";

				}
										
				VelocistaIscrizioneGara velocistaIscrittoGara = new VelocistaIscrizioneGara(nominativo, eta);
			    elencoVelocistiIscrittiGara.add(velocistaIscrittoGara);
		//	elencoVelocistiPartecipantiGara = loadVelocistaByQuery(preparedStatement);

			

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("VelocistaDao -> loadVelocistaInnerJoin -> " + sqlException.getMessage());
		}

		return elencoVelocistiIscrittiGara;
	}
	
	
	public void addIscrizione(Iscrizione iscrizione) throws EnteSportivoModelException {

		try {
            Trigger.checkLimiteIscrizioni(iscrizione);
            
			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.insertIscrizione);

		//	preparedStatement.setTimestamp(1,Timestamp.valueOf(iscrizione.getDataOraIscrizione()));
			preparedStatement.setString(1, iscrizione.getCodiceFiscale());
			preparedStatement.setLong(2, iscrizione.getIdGara());

			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("IscrizioneDao -> addIscrizione  -> " + sqlException.getMessage());

		}
	}
	
public void removeIscrizioneVelocista(String nominativo,Long idGara) throws EnteSportivoModelException{

	
	try {
		

		PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
				.prepareStatement(QueryCatalog.deleteIscrizione);

		preparedStatement.setLong(1, idGara);
		preparedStatement.setString(2, nominativo);

	     System.out.println(QueryCatalog.deleteIscrizione);

		preparedStatement.executeUpdate();

	}catch (SQLException sqlException) {

		throw new EnteSportivoModelException("GaraDao -> deleteIscrizione  -> " + sqlException.getMessage());

	}

	
}
}
