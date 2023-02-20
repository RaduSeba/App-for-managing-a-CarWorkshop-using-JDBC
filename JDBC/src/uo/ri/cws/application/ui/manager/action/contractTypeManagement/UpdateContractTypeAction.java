package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;

public class UpdateContractTypeAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Get info
		String name = Console.readString("Name "); 
		Double compensationDays = Console.readDouble("Compensation days ");
		
		// Process
		
		ContractTypeBLDto ct = new ContractTypeBLDto();
		ct.compensationDays = compensationDays;
		ct.name = name;
		
		
		BusinessFactory.forContractTypeService().updateContractType(ct);

		
		// Print result
		Console.println("Contract type succesfully updated");
		
	}

}
