package uo.ri.cws.application.business.contract.crud.command;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteContract implements Command<ContractBLDto> {

	private String ID;
	
	public DeleteContract(String id ) {
		
		Argument.isNotNull(id, "ID cannot be null");
		Argument.isNotEmpty(id, "The id cannot be empty");
		
		ID=id;
		
		
	}

	@Override
	public ContractBLDto execute() throws BusinessException {
		
	ContractGateway cg = PersistenceFactory.forContract();
	
	MechanicGateway mg = PersistenceFactory.forMechanic();
	
	
	
	
	BuisnessCheck.isNotNull(cg.findById2(ID), "THe contract doesn t exist");
	
	String mechanicid=cg.findById2(ID).dni;
	
	
	BuisnessCheck.isFalse(mg.checkWorkorder(mechanicid),"The mechanic who has the contract has carried activity");
	
	
	
	BuisnessCheck.isFalse(cg.checkPayrool(ID), "THE contrat has genrated payrools");
		
	cg.remove(ID);
		
		return null;
	}

}
