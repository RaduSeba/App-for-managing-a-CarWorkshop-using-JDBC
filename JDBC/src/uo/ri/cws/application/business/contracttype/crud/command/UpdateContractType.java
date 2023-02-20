package uo.ri.cws.application.business.contracttype.crud.command;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class UpdateContractType implements Command<ContractTypeBLDto> {

	private ContractTypeBLDto c ;
	
	public UpdateContractType(ContractTypeBLDto dto) {
		Argument.isNotNull(dto, "please not null");
		Argument.isNotEmpty(dto.name, "name cannot be empty");
		Argument.isNotNull(dto.name, "name cannot be null");
		
		if(dto.compensationDays<0)
		{
			throw new IllegalArgumentException( "Days cannot be negative" );
		}
		
		
		this.c=dto;
		
		
		
	}

	@Override
	public ContractTypeBLDto execute() throws BusinessException {
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		BuisnessCheck.isNotNull(ctg.findByName(c.name),
				"The contract type doesn t exists");
		
		ctg.update(ContractTypeAssembler.toDALDto(c));
		
		
		return null;
		
	}

}
