package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import java.util.Optional;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindMechanicAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		// Get info
		String dni = Console.readString("Mechanic DNI "); 

		// Process
		Optional<MechanicBLDto> m;

		m = BusinessFactory.forMechanicService().findMechanicByDni(dni);		
			
		if (m.isEmpty())
			Console.println("\nMechanic does not exist\n");  
		else
			Printer.printMechanic( m.get() );
	}
}
