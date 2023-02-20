package uo.ri.cws.application.business.contract.assembler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractSummaryDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;


public class ContractAssembler {
	
	public static ContractDALDto toDALDto(ContractBLDto arg) {
		
		
		ContractDALDto result = new ContractDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.dni = arg.dni;
		result.annualBaseWage = arg.annualBaseWage;
		result.contractTypeName = arg.contractTypeName;
		result.professionalGroupName=arg.professionalGroupName;
		result.startDate=Date.valueOf(arg.startDate);
		
		//if(arg.contractTypeName.toUpperCase().compareTo("PERMANENT") != 0) 
		 // { 
			//result.endDate = Date.valueOf(arg.endDate);
		// }
		
		
		result.settlement=arg.settlement;
		result.state=arg.state.toString();
		
		
		return result;
	}
	
	
	
public static ContractDALDto toDALDto2(ContractBLDto arg) {
		
		
		ContractDALDto result = new ContractDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.annualBaseWage = arg.annualBaseWage;
		result.endDate= Date.valueOf(arg.endDate);
		
		
		
		return result;
	}
	
	
	
	
	
	
	public static List<ContractSummaryBLDto> toDtoList(List<ContractSummaryDALDto> arg) {
		List<ContractSummaryBLDto> result = new ArrayList<ContractSummaryBLDto>();
		for (ContractSummaryDALDto mr : arg)
			result.add(toContractSummaryDto(mr));
		return result;
	}
	
	public static List<ContractSummaryBLDto> toDtoSummaryList(List<ContractDALDto> arg) {
		List<ContractSummaryBLDto> result = new ArrayList<ContractSummaryBLDto>();
		for (ContractDALDto mr : arg)
			result.add(toSummaryContractDto(mr));
		return result;
	}
	
	
	private static ContractSummaryBLDto toContractSummaryDto(ContractSummaryDALDto arg) {

		ContractSummaryBLDto result = new ContractSummaryBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.numPayrolls=arg.numPayrolls;
		result.settlement=arg.settlement;
		result.state=ContractState.valueOf(arg.state);
		result.dni = arg.dni;
		return result;
	}
	
	private static ContractSummaryBLDto toSummaryContractDto(ContractDALDto arg) {

		ContractSummaryBLDto result = new ContractSummaryBLDto();
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		result.id = arg.id;
		result.version = arg.version;
		result.numPayrolls=1;
		result.settlement=arg.settlement;
		result.state=ContractState.valueOf(arg.state);
		result.dni = mg.findById2(arg.dni).dni;
		return result;
	}



	public static Optional<ContractBLDto> toBLDto(
			Optional<ContractDALDto> arg) {
		Optional<ContractBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toContractDto(arg.get()));
		return result;
	}

	public static ContractBLDto toContractDto(ContractDALDto arg) {
		
		
		ContractBLDto result = new ContractBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.dni = arg.dni;
		result.annualBaseWage = arg.annualBaseWage;
		result.contractTypeName = arg.contractTypeName;
		result.professionalGroupName=arg.professionalGroupName;
		result.startDate=arg.startDate.toLocalDate();
		//result.endDate= arg.endDate.toLocalDate();
		result.settlement=arg.settlement;
		result.state= ContractState.valueOf(arg.state);
		
		
		return result;
	}

	

}
