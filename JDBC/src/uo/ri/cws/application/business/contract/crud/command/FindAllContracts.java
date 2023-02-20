package uo.ri.cws.application.business.contract.crud.command;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;

public class FindAllContracts implements Command<List<ContractSummaryBLDto>> {


	@Override
	public List<ContractSummaryBLDto> execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		return ContractAssembler.toDtoSummaryList(cg.findAll());
		
	}

}
