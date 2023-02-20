package uo.ri.cws.application.business.mechanic.crud.commands;


import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteMechanic implements Command<MechanicBLDto> {

	private String i ;
	
	
	public DeleteMechanic(String id) {
		
		i=id;
		
		Argument.isNotNull(id, "ID cannot be null");
		Argument.isNotEmpty(id, "The id cannot be empty");
	}
	
	
	@Override
	public MechanicBLDto execute() throws BusinessException {
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		BuisnessCheck.isFalse(mg.findById(i).isEmpty(),
				"The Mechanic doesn t exist");
		
		BuisnessCheck.isFalse(mg.checkWorkorder(i), "The mechanic has existing workorders");
		
		mg.remove(i);
		
		return null;
	}

    

}
