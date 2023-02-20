package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

public class ListContractTypeByNameAction implements Action  {

	@Override
	public void execute() throws BusinessException {
		
		String name = Console.readString("Contract type name ");
		
		//Optional<ContractTypeBLDto> ct = null;
		
		 BusinessFactory.forContractTypeService().findContractTypeByName(name);

//		Printer.printContractType(ct);
	}

}
