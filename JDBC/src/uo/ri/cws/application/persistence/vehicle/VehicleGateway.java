package uo.ri.cws.application.persistence.vehicle;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public interface VehicleGateway extends Gateway<VehicleDALDto> {

	/**
	 * @param arg owner's id 
	 * @return List<VehicleRecord> if there is any vehicle for this client; could be empty
	 * @ 
	 */
	public List<VehicleDALDto> findByClient(String arg) ;
	
	public class VehicleDALDto {

		public String id;
		public Long version;
			
		public String platenumber;
		public String make;
		public String model;
		public String client_id;
		public String vehicletype_id;
	}

}
