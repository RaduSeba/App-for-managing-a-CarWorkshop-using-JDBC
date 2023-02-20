package uo.ri.cws.application.business.contract.crud.command;

import java.util.ArrayList;
import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindContractByMechanic implements Command<List<ContractSummaryBLDto>>{

	private String dni;
	
	private List<ContractSummaryBLDto> result = new ArrayList<>();
	

	public FindContractByMechanic(String mechanicDni) {
		Argument.isNotNull(mechanicDni,"THE id cannot be empty");
		Argument.isNotEmpty(mechanicDni, "The id cannot be empty");
		
		this.dni=mechanicDni;
	}

	@Override
	public List<ContractSummaryBLDto> execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		
		if(mg.findByDni2(dni)==null)
		{
			return result;
		}
		
	//	BuisnessCheck.isNotNull(mg.findByDni2(dni), "The mechanic doesn t exist");
		
		String id= mg.findByDni2(dni).id;
		
		if(cg.findByMechanic(id)==null)
		{
			return result;
		}
		
		//BuisnessCheck.isNotNull(cg.findByMechanic(id),"the mechanic doesn t have contracts");
		
		return ContractAssembler.toDtoSummaryList(cg.findByMechanic(id));
		
	}

}
