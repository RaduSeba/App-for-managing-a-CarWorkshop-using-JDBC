package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanics;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByDni;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicById;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanic;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class MechanicServiceImpl implements MechanicService {

    CommandExecutor executor = new CommandExecutor();

    @Override
    public MechanicBLDto addMechanic(MechanicBLDto mechanic)
	    throws BusinessException {
	return executor.execute(new AddMechanic(mechanic));
    }

    @Override
    public void deleteMechanic(String idMechanic) throws BusinessException {
    	
    	executor.execute(new DeleteMechanic(idMechanic));

    }

    @Override
    public void updateMechanic(MechanicBLDto mechanic)
	    throws BusinessException {
	
    	 executor.execute(new UpdateMechanic(mechanic));

    }

    @Override
    public Optional<MechanicBLDto> findMechanicById(String idMechanic)
	    throws BusinessException {
    	
    	return executor.execute(new FindMechanicById(idMechanic));
    }

    @Override
    public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic)
	    throws BusinessException {
	
	return executor.execute(new FindMechanicByDni(dniMechanic) );
    }

    @Override
    public List<MechanicBLDto> findAllMechanics() throws BusinessException {
	// TODO Auto-generated method stub
	return executor.execute(new FindAllMechanics());
    }

	@Override
	public List<MechanicBLDto> findMechanicsInForce() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicBLDto> findMechanicsWithContractInForceInContractType(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicBLDto> findMechanicsInProfessionalGroups(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
