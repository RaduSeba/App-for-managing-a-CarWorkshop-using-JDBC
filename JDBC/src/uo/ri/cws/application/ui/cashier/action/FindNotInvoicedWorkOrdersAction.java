package uo.ri.cws.application.ui.cashier.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindNotInvoicedWorkOrdersAction implements Action {
	

	/**
	 * Process:
	 * 
	 *   - Ask customer dni
	 *    
	 *   - Display all uncharged workorder  
	 *   		(state <> 'INVOICED'). For each workorder, display 
	 *   		id, vehicle id, date, state, amount and description
	 */


	@Override
	public void execute() throws BusinessException {
		
		String dni = Console.readString("Client DNI ");
		
		Console.println("\nClient's not invoiced work orders\n");  
		
		List<WorkOrderForInvoicingBLDto> a=	BusinessFactory.forInvoicingService().findNotInvoicedWorkOrdersByClientDni(dni);
		
		
		
		Printer.printInvoicingWorkOrders(a);

	}

}