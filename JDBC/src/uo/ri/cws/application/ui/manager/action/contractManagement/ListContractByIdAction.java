
  package uo.ri.cws.application.ui.manager.action.contractManagement;
  
  import java.util.List;
import java.util.Optional;

import console.Console;
import menu.Action;
import
  uo.ri.cws.application.business.BusinessException;
import
  uo.ri.cws.application.business.BusinessFactory;
import
  uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import
  uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;
  
 /**
	 * Clase que encuentra un contrato dado un id
	 * 
	 * @author Carlos
	 *
	 */

  
  public class ListContractByIdAction implements Action {
  

 /**
	 * Encuentra un contrato dado un id de contrato pasado por consola
	 */
		  @Override public void execute() throws BusinessException {
			  
			  Console.println("\nPrinting contracts summary\n");
			  
			  
			  List<ContractSummaryBLDto> lc =  BusinessFactory.forContractService().findAllContracts();
			  
			  
			  
			  
			 
			  if (lc.isEmpty())
				  Console.print("There is no Contract ");
			  else { 
				  for(ContractSummaryBLDto c : lc) {
					  
				  
					  Printer.displayThisContractDetailsWithPayrolls(c);
			  
			  } }
			  
		  
		  String id = Console.readString("Type id");
		  
		  Optional<ContractBLDto> opContract = null;
		  
		  opContract=BusinessFactory.forContractService().findContractById(id);		  
		  
		  
		  if (opContract.isPresent())
			  Printer.displayThisContractDetails(opContract.get());
		  else
		  Console.println("There is no contract with this id");
		  
		  }
		  
		  
		  }
		 