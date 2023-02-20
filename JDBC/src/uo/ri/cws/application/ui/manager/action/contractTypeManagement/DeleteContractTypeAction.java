package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

public class DeleteContractTypeAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Get info
		String name = Console.readString("Name "); 
		
		BusinessFactory.forContractTypeService().deleteContractType(name);
				
		// Print result
		Console.println("Contract type succesfully deleted");
	}

}
