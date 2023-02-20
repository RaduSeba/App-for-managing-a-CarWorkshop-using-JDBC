package uo.ri.cws.application.business.contracttype.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService;
import uo.ri.cws.application.business.contracttype.crud.command.AddContractType;
import uo.ri.cws.application.business.contracttype.crud.command.DeleteContractType;
import uo.ri.cws.application.business.contracttype.crud.command.FindAllContractTypes;
import uo.ri.cws.application.business.contracttype.crud.command.FindContractTypeByName;
import uo.ri.cws.application.business.contracttype.crud.command.UpdateContractType;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class ContractTypeServiceImpl implements ContractTypeService {

	CommandExecutor executor = new CommandExecutor();

	@Override
	public ContractTypeBLDto addContractType(ContractTypeBLDto dto)
			throws BusinessException {
		
		return executor.execute(new AddContractType(dto));
	}

	@Override
	public void deleteContractType(String name) throws BusinessException {
		
		executor.execute(new DeleteContractType(name));
		
	}

	@Override
	public void updateContractType(ContractTypeBLDto dto)
			throws BusinessException {
		
		executor.execute(new UpdateContractType(dto));
		
	}

	@Override
	public Optional<ContractTypeBLDto> findContractTypeByName(String name)
			throws BusinessException {
		
		return executor.execute(new FindContractTypeByName(name));
	}

	@Override
	public List<ContractTypeBLDto> findAllContractTypes()
			throws BusinessException {
		
		return executor.execute(new FindAllContractTypes());
	}

}
