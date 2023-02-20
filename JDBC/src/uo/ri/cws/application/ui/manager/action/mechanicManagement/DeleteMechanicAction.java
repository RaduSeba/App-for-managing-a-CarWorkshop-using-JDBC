package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

public class DeleteMechanicAction implements Action {

	
	@Override
	public void execute() throws BusinessException {
		
		String idMechanic = Console.readString("Type mechanic id "); 
		
		BusinessFactory.forMechanicService().deleteMechanic(idMechanic);

		Console.println("Mechanic deleted");
	}

}
