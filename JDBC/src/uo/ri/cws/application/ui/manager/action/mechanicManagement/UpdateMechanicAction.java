package uo.ri.cws.application.ui.manager.action.mechanicManagement;


import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

public class UpdateMechanicAction implements Action {

	
	//private Connection c = null;

	@Override
	public void execute() throws BusinessException {
		
		// Get info
				String id = Console.readString("Type mechahic id to update");
				String name = Console.readString("Name"); 
				String surname = Console.readString("Surname");
				
				MechanicBLDto argument = new MechanicBLDto();
				argument.id=id;
				argument.name = name;
				argument.surname = surname;
				
				// Process
				BusinessFactory.forMechanicService().updateMechanic(argument);
				// Print result
				Console.println("Mechanic updated");
	}

}
