package uo.ri.cws.application.persistence.contract;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

public interface ContractGateway extends Gateway<ContractDALDto> {
	
	
	
	
	Optional<ContractDALDto> findByDni(String dni);
	
	public class ContractDALDto {

		public String id;
		public long version;
		
		public String dni;
		public String contractTypeName;
		public String professionalGroupName;
		public Date startDate;
		public Date endDate;
		public double annualBaseWage;

		// Filled in reading operations
		public double settlement;
		public String state;
	}
	
	public class ContractSummaryDALDto{
		
		public String id;
		public long version;
		
		public String dni;

		// Filled in reading operations
		public double settlement;
		public String state;

		// For summary only
		public int numPayrolls;
	}

	boolean checkPayrool(String id);

	ContractDALDto findById2(String id);

	String ContractState(String id);

	List<ContractDALDto> findByMechanic(String id);

	void terminate(ContractDALDto t);

	ContractDALDto findByMechanicID(String id);

	void updateEndDate(ContractDALDto t);

	double calculateTotalSettlement(ContractDALDto c);

	double getTotalWage(String id);

	

}
