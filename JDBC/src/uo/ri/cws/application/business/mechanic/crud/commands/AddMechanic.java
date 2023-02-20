package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class AddMechanic implements Command<MechanicBLDto> {

    private MechanicBLDto mechanic = null;

    public AddMechanic(MechanicBLDto mechanic) {

	Argument.isNotNull(mechanic, "Mechanic cannot be null");
	Argument.isNotEmpty(mechanic.dni, "The dni cannot be empty");
	this.mechanic = mechanic;
	this.mechanic.id = UUID.randomUUID().toString();
	this.mechanic.version = 1L;
    }

    public MechanicBLDto execute() throws BusinessException {

	MechanicGateway mg = PersistenceFactory.forMechanic();

	BuisnessCheck.isTrue(mg.findByDni(mechanic.dni).isEmpty(),
		"There is a Mechanic with the same dni");
	
	mg.add(MechanicAssembler.toDALDto(mechanic));

	return this.mechanic;

    }

}
