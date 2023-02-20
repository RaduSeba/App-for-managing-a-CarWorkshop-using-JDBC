package uo.ri.cws.application.business.contracttype.crud.command;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class DeleteContractType implements Command<ContractTypeBLDto> {
	
	private String n;

	public DeleteContractType(String name) {
		Argument.isNotEmpty(name,"The name cannot be empty");
		Argument.isNotNull(name, "The name cannot be null");
		this.n=name;
	}

	@Override
	public ContractTypeBLDto execute() throws BusinessException {
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		BuisnessCheck.isNotNull(ctg.findByName(n),
				"The contract type already exists");
		
		String i=ctg.findByName(n).id;
		
		BuisnessCheck.isFalse(ctg.checkContract(i),
				"The contract type already exists");
		
		ctg.remove(i);
		
		
		
		return null;
	}

}
