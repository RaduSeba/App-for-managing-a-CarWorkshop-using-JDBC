package uo.ri.cws.application.business.contract.crud.command;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;

public class UpdateContract implements Command<ContractBLDto> {

	private ContractBLDto contract;
	
	public UpdateContract(ContractBLDto dto) {
		Argument.isNotNull(dto, "Mechanic cannot be null");
		Argument.isNotEmpty(dto.id, "The id cannot be empty");
		
		
		if(dto.annualBaseWage<=0.0)
		{
			
			throw new IllegalArgumentException( "Wage cannot be negative" );
			
		}
		
		
		this.contract = dto;
		
		
	}

	@Override
	public ContractBLDto execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		BuisnessCheck.isNotNull(cg.findById2(contract.id),"The contract doesn t exist");
		
		BuisnessCheck.isFalse(contract.annualBaseWage<0, "wage is not correct");
		

		
		
		String type =cg.findById2(contract.id).contractTypeName;
		
		BuisnessCheck.isSTATE(cg.ContractState(type), "The contract state cannot be updated");
		
		BuisnessCheck.isFalse(contract.endDate.isBefore(contract.startDate),"enddate is not correct");
		
		cg.update(ContractAssembler.toDALDto2(contract));
		
		return this.contract;
		
	}

}
