
  package uo.ri.cws.application.ui.manager.action.contractManagement;
  
  import java.util.List;

import console.Console;
import menu.Action;
import
  uo.ri.cws.application.business.BusinessException;
import
  uo.ri.cws.application.business.BusinessFactory;
import
  uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;
  
 /**
	 * Clase que encuentra un contrato dado un id ade mecanico
	 * 
	 * @author Carlos
	 *
	 */

  
  public class ListContractsByMechanicDniAction implements Action {
  
 /**
	 * Encuentra un contrato dado un id de mecanico pasado por consola
	 */
		  @Override public void execute() throws BusinessException {
		  
		  String idM = Console.readString("Type mechanic dni ");
		  
		  List<ContractSummaryBLDto> result = null;
		  
		  result= BusinessFactory.forContractService().findContractsByMechanic(idM);
		  
		  
		  if (!result.isEmpty())
			  for(ContractSummaryBLDto c : result) {
				  
				  
				  Printer.displayThisContractDetailsWithPayrolls(c);
		  
		  } 
		  
		  else
		  Console.println("There are no contracts for a mechanic with this dni");
		  
		  }
		  
		  }
		 