
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
	 * Clase que lista por pantalla los contratos dado un id
	 * 
	 * @author Carlos
	 *
	 */

  
  public class ListAllContractsAction implements Action {
  
 /**
	 * Lista los contratos por consola
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
		  
		  }
		  
		  }
		 