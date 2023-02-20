package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;

public class AddContractTypeAction implements Action {
	
	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String name = Console.readString("Name "); 
		Double compensationDays = Console.readDouble("Compensation days ");
		
		// Process
		
		ContractTypeBLDto ct = new ContractTypeBLDto();
		ct.compensationDays = compensationDays;
		ct.name = name;
		
		BusinessFactory.forContractTypeService().addContractType(ct);

				
		// Print result
		Console.println("New contract type succesfully added");
		//Printer.printContractType(result);
	}
}
