package uo.ri.cws.application.persistence.contracttype;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;
public interface ContractTypeGateway extends Gateway<ContractTypeDALDto> {

	
	
	
	ContractTypeDALDto findByName(String name);
	
	
	public class ContractTypeDALDto {

		public String id;
		public long version;
		
		public double compensation;
		public String name;
		
		
		
	}


	boolean checkContract(String i);
	
}
