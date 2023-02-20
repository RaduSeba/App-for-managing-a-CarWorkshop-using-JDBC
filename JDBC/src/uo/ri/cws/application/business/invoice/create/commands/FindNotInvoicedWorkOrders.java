package uo.ri.cws.application.business.invoice.create.commands;

import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;

public class FindNotInvoicedWorkOrders implements Command<List<WorkOrderForInvoicingBLDto> >{

	String d;
	
	public FindNotInvoicedWorkOrders(String dni) {
	
		Argument.isNotEmpty(dni, "The dni cannot be empty");
		d=dni;
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		
		InvoiceGateway ig = PersistenceFactory.forInvoice();
		
		BuisnessCheck.isTrue(ig.checkClientExists(d), "client doesn t exist");
		
		return InvoicingAssembler.toInvoicingWorkOrderList(ig.findNotInvoicedWorkOrders(d));
	}

}
