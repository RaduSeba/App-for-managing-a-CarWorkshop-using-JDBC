package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

public class ListAllContractTypeAction implements Action  {

	@Override
	public void execute() throws BusinessException {
		
		BusinessFactory.forContractTypeService().findAllContractTypes();
		
		
		Console.println("\nList of contract types \n");  




	}

}
