package uo.ri.cws.application.persistence.mechanic;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

public interface MechanicGateway extends Gateway<MechanicDALDto> {

	/*
	 * Finds a row in the table
	 * @param record's field
	 * @return dto from that record, probably null
	 */
	Optional<MechanicDALDto> findByDni(String dni);
	
	
	MechanicDALDto findByDni2(String dni);
	
	public class MechanicDALDto {

		public String id;
		public Long version;
		
		public String dni;
		public String name;
		public String surname;

	}

	MechanicDALDto findById2(String id);
	boolean checkWorkorder(String id);

}
