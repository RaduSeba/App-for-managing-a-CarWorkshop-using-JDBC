package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class AddMechanicAction implements Action {

    @Override
    public void execute() throws BusinessException {

    	// Get info
    			String dni = Console.readString("Dni"); 
    			String name = Console.readString("Name"); 
    			String surname = Console.readString("Surname");
    			
    			// Process
    			MechanicBLDto m = new MechanicBLDto();
    			m.dni = dni;
    			m.name = name;
    			m.surname = surname;
    			
    			MechanicBLDto result = BusinessFactory.forMechanicService().addMechanic(m);
    					
    			// Print result
    			Console.println("Mechanic added");
    			Printer.printMechanic(result);
    }

}
