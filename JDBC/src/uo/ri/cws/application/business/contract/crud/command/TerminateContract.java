package uo.ri.cws.application.business.contract.crud.command;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class TerminateContract implements Command<ContractBLDto> {

	private String ID;
	
	public TerminateContract(String id) {
		Argument.isNotEmpty(id, "Id cannot be empty");
		Argument.isNotNull(id, "Id cannot be null");
		this.ID=id;
	}

	
	public static Date getEndOfNextMonth() { return
			Date.valueOf(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())); }
	
	@Override
	public ContractBLDto execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		
		
		
		BuisnessCheck.isNotNull(cg.findById2(ID), "THe contract doesn t exist");
		
		BuisnessCheck.isFalse(cg.findById2(ID).state.equalsIgnoreCase("TERMINATED"), "THe contract is already terminated");
		
		String mechanicid=cg.findById2(ID).dni;
		
		ContractDALDto c=cg.findById2(ID);
		
		c.endDate=getEndOfNextMonth();
		double amount = cg.calculateTotalSettlement(c); 
		c.settlement=amount;
		
		
		BuisnessCheck.isFalse(mg.checkWorkorder(mechanicid),"The mechanic who has the contract has carried activity");
		
			cg.terminate(c);
			
			return null;
		
	}

}
