package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicByDni implements Command<Optional<MechanicBLDto>> {
	
	private String d;

	public FindMechanicByDni(String dniMechanic) {
		
		Argument.isNotNull(dniMechanic, "dni cannot be null");
		Argument.isNotEmpty(dniMechanic, "The dni cannot be empty");
		d=dniMechanic;
	}

	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		
		
		return MechanicAssembler.toBLDto( mg.findByDni(d));
	}

}
