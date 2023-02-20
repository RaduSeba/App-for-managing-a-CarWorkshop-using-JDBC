package uo.ri.cws.application.business.contracttype.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

public class ContractTypeAssembler {

public static ContractTypeDALDto toDALDto(ContractTypeBLDto arg) {
		
		
		ContractTypeDALDto result = new ContractTypeDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.compensation=arg.compensationDays;
		result.name=arg.name;
		
		
		
		return result;
	}

public static List<ContractTypeBLDto> toDtoList(List<ContractTypeDALDto> arg) {
	List<ContractTypeBLDto> result = new ArrayList<ContractTypeBLDto>();
	for (ContractTypeDALDto mr : arg)
		result.add(toContractTypeDto(mr));
	return result;
}

private static ContractTypeBLDto toContractTypeDto(ContractTypeDALDto arg) {
	
	ContractTypeBLDto result = new ContractTypeBLDto();
	
	result.id = arg.id;
	result.version = arg.version;
	result.compensationDays=arg.compensation;
	result.name=arg.name;
	
	
	
	return result;
	
}



public static Optional<ContractTypeBLDto> toBLDto(
		Optional<ContractTypeDALDto> arg) {
	Optional<ContractTypeBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
			: Optional.ofNullable(toContractTypeDto(arg.get()));
	return result;
}


}


