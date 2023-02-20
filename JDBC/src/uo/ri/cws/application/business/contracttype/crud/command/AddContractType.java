package uo.ri.cws.application.business.contracttype.crud.command;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class AddContractType implements Command<ContractTypeBLDto> {

	private ContractTypeBLDto c=null;
	
	public AddContractType(ContractTypeBLDto dto) {
		
		Argument.isNotNull(dto,"The contracttype cannot be null");
		Argument.isNotEmpty(dto.name, "Id cannot be empty");
		Argument.isNotNull(dto.name, "Id cannot be empty");
		Argument.isNotNull(dto.compensationDays, "Compensation days cannot be null");
		
		if(dto.compensationDays<0)
		{
			throw new IllegalArgumentException( "Days cannot be negative" );
		}
		
		this.c = dto;
		this.c.id = UUID.randomUUID().toString();
		this.c.version = 1L;
		
		
		
	}

	@Override
	public ContractTypeBLDto execute() throws BusinessException {
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		BuisnessCheck.isTrue(c.compensationDays>=0, "Compensation days must be higher than 0");
		
		BuisnessCheck.isNull(ctg.findByName(c.name),
				"The contract type already exists");
		
		ctg.add(ContractTypeAssembler.toDALDto(c));
		
		return this.c;
		
	}

}
