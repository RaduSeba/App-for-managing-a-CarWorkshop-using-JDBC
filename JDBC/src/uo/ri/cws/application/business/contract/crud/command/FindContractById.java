package uo.ri.cws.application.business.contract.crud.command;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;

public class FindContractById implements Command<Optional<ContractBLDto>> {

	private String i;
	

	public FindContractById(String id) {
		Argument.isNotNull(id, "id cannot be null");
		Argument.isNotEmpty(id, "The id cannot be empty");
		this.i=id;
	}

	@Override
	public Optional<ContractBLDto> execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		return ContractAssembler.toBLDto(cg.findById(i));
	}

}
