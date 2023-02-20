package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class UpdateMechanic implements Command<MechanicBLDto> {

	 private MechanicBLDto mechanic = null;
	
	public UpdateMechanic(MechanicBLDto mechanic) {
		
		Argument.isNotNull(mechanic, "Mechanic cannot be null");
		Argument.isNotEmpty(mechanic.id, "The id cannot be empty");
		Argument.isNotEmpty(mechanic.name,"The name cannot be empty");
		Argument.isNotEmpty(mechanic.surname, "The surname cannot be empty");
		this.mechanic = mechanic;
		//this.mechanic.id = UUID.randomUUID().toString();
		//this.mechanic.version = 1L;
	}

	@Override
	public MechanicBLDto execute() throws BusinessException {
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		
		BuisnessCheck.isFalse(mg.findById(mechanic.id).isEmpty(),
				"The mecannic does not exist");
		
		mg.update(MechanicAssembler.toDALDto(mechanic));
		
		return this.mechanic;
	}

}
