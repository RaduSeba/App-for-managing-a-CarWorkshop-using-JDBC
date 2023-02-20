package uo.ri.cws.application.business.contracttype.crud.command;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class FindAllContractTypes  implements Command<List<ContractTypeBLDto>> {


	@Override
	public List<ContractTypeBLDto> execute() throws BusinessException {
		
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		return ContractTypeAssembler.toDtoList(ctg.findAll());
	}

}
