
  package uo.ri.cws.application.ui.manager.action.contractManagement;
  
  import java.time.LocalDate;
import java.util.List;

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
	 * Clase que actualiza un contrato dado su id
	 * 
	 * @author Carlos
	 *
	 */

  
  public class UpdateContractAction implements Action {
  
  
 /**
	 * Actualiza un contrato cuyo id es pasado por consola, el contrato es
	 * actualizado con una fecha de finalizacion y un salario pasado por consola
	 */
		  @Override public void execute() throws BusinessException {
		  
		  

			  List<ContractSummaryBLDto> lc =  BusinessFactory.forContractService().findAllContracts();
			  
			  
			  
			 
			  if (lc.isEmpty())
				  Console.print("There is no Contract ");
			  else { 
				  for(ContractSummaryBLDto c : lc) {
				  
					 Printer.displayThisContractDetailsWithPayrolls(c);
			  
			  } }
			  
			  
		  String id = Console.readString("Type contract id ");
		  String e= Console.readString("Type new end date format yyyy-mm-dd");
		  LocalDate endD = LocalDate.parse(e) ; 
		  double salary = Console.readDouble("Type annual base wage ");
		  
		  ContractBLDto cdto = new ContractBLDto();
		  cdto.id = id; cdto.endDate = endD;
		  cdto.annualBaseWage = salary;
		  
		  
		  BusinessFactory.forContractService().updateContract(cdto);
		  
		  Console.println("Contract updated");
		  
		  }
  }
		 