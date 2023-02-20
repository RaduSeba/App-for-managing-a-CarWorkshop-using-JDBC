package uo.ri.cws.application.business.contract.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.crud.command.AddContract;
import uo.ri.cws.application.business.contract.crud.command.DeleteContract;
import uo.ri.cws.application.business.contract.crud.command.FindAllContracts;
import uo.ri.cws.application.business.contract.crud.command.FindContractById;
import uo.ri.cws.application.business.contract.crud.command.FindContractByMechanic;
import uo.ri.cws.application.business.contract.crud.command.TerminateContract;
import uo.ri.cws.application.business.contract.crud.command.UpdateContract;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class ContractServiceImpl implements ContractService {
	
	CommandExecutor executor = new CommandExecutor();

	@Override
	public ContractBLDto add(ContractBLDto c) throws BusinessException {
		
		return executor.execute(new AddContract(c));
	}

	@Override
	public void updateContract(ContractBLDto dto) throws BusinessException {
		 
		executor.execute(new UpdateContract(dto));
		
	}

	@Override
	public void deleteContract(String id) throws BusinessException {
		
		 executor.execute(new DeleteContract(id));
		
	}

	@Override
	public void terminateContract(String contractId) throws BusinessException {
		
		executor.execute(new TerminateContract(contractId));
	}

	@Override
	public Optional<ContractBLDto> findContractById(String id) throws BusinessException {
		
		
		return executor.execute(new FindContractById(id));
	}

	@Override
	public List<ContractSummaryBLDto> findContractsByMechanic(String mechanicDni) throws BusinessException {
		
		return executor.execute(new FindContractByMechanic(mechanicDni));
	}

	@Override
	public List<ContractSummaryBLDto> findAllContracts() throws BusinessException {
		
		return executor.execute(new FindAllContracts());
	}

}
