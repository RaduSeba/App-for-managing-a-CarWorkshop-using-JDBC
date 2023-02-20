package uo.ri.cws.application.business.contracttype.crud.command;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class FindContractTypeByName  implements Command<Optional<ContractTypeBLDto>> {

	private String  n;
	
	public FindContractTypeByName(String name) {
		
		Argument.isNotEmpty(name,"The name cannot be empty");
		Argument.isNotNull(name, "The name cannot be null");
		
		this.n=name;
		
		
	}

	@Override
	public Optional<ContractTypeBLDto> execute() throws BusinessException {
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		return ContractTypeAssembler.toBLDto(ctg.findById(n));
		
		
	}

}
