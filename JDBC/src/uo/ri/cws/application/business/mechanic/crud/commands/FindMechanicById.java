package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicById implements Command<Optional<MechanicBLDto>> {

	private String id;
	
	
	public FindMechanicById(String idmechanic) {

		Argument.isNotNull(idmechanic, "id cannot be null");
		Argument.isNotEmpty(idmechanic, "The id cannot be empty");
		id=idmechanic;
	}

	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		
		return MechanicAssembler.toBLDto( mg.findById(id));
	}

}
