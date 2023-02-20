package uo.ri.cws.application.business.contract.crud.command;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;

public class AddContract  implements Command<ContractBLDto> {
	
	private ContractBLDto c=null;

	
	public AddContract(ContractBLDto contract) {
		Argument.isNotNull(contract, "Mechanic cannot be null");
		Argument.isNotEmpty(contract.dni, "The dni cannot be empty");
		Argument.isNotEmpty(contract.contractTypeName, "The CONTYRACTTYOPE cannot be empty");
		this.c = contract;
		this.c.id = UUID.randomUUID().toString();
		this.c.version = 1L;
		this.c.settlement=0;
		this.c.state=ContractState.IN_FORCE;
		this.c.startDate= LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
		
		if(contract.endDate!=null)
		{
			this.c.endDate=contract.endDate;
		}
		
		
		
		
		
		//if(contract.contractTypeName.toUpperCase().compareTo("PERMANENT") != 0) 
		 // { 
			//this.c.endDate = getEndOfNextMonth(contract.endDate);
		// }
		//this.c.endDate =getEndOfNextMonth();
			
		
	}
	
	  
	public static LocalDate getFirstOfNextMonth(LocalDate a) { 
		
		return a
                .with(TemporalAdjusters.firstDayOfMonth());
	}
	@Override
	public ContractBLDto execute() throws BusinessException {
		
		ContractGateway cg = PersistenceFactory.forContract();
		
		MechanicGateway mg = PersistenceFactory.forMechanic();
		
		ContractTypeGateway ctg= PersistenceFactory.forContractType();
		
		ProfessionalGroupGateway pg= PersistenceFactory.forProfessionalGroup();
		
		
		
		BuisnessCheck.isFalse(mg.findByDni(c.dni).isEmpty(),
				"There is not a mechanic with this dni");
		
		BuisnessCheck.isNotNull(ctg.findByName(c.contractTypeName),
				"The contract type is invalid");
		
		
		BuisnessCheck.isNotNull(pg.findId(c.professionalGroupName), "professional group name is not correct");
		
		if(c.endDate!=null)
		{
			BuisnessCheck.isFalse(c.endDate.isBefore(c.startDate),"enddate is not correct");
			
			BuisnessCheck.isFalse(c.endDate.isEqual(c.startDate),"enddate is not correct");
		}
		
		
		
		this.c.dni=mg.findByDni2(c.dni).id;
		this.c.contractTypeName=ctg.findByName(c.contractTypeName).id;
		this.c.professionalGroupName=pg.findId(c.professionalGroupName).id;
		
		
		if(cg.findByMechanicID(c.dni)!=null)
		{
			ContractDALDto a =cg.findByMechanicID(c.dni);
		
			cg.updateEndDate(a);
			//this.c.startDate= getFirstOfNextMonth(this.c.startDate);
			this.c.startDate= LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
		}
		
		
		
		
		cg.add(ContractAssembler.toDALDto(c));
		
		return this.c;
	}

}
