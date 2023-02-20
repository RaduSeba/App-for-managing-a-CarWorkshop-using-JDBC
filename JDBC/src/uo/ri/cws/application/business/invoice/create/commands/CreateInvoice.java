package uo.ri.cws.application.business.invoice.create.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import assertion.Argument;
import math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.BuisnessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;

public class CreateInvoice implements Command<InvoiceBLDto>{
	
	InvoiceBLDto invoice = new InvoiceBLDto(); 
	
	List<String> workOrders = new ArrayList<String>();
	

	public CreateInvoice(List<String> workOrderIds)  {
		
		Argument.isNotNull(workOrderIds, "ID s cannot be null");
		
		
		for(int i=0;i<workOrderIds.size();i++)
		{
			Argument.isNotEmpty(workOrderIds.get(i),"one id is empty" );
			Argument.isNotNull(workOrderIds.get(i), "one id is null");
		}
		
		
		if(workOrderIds.isEmpty()==true)
		{
			throw new IllegalArgumentException( "List cannot be empty" );
		}
		
		invoice.id=UUID.randomUUID().toString();
		invoice.version = 1L;
		invoice.date = LocalDate.now();
		workOrders=workOrderIds;
		
	}
	
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;

	}

	

	@Override
	public InvoiceBLDto execute() throws BusinessException {
		
		InvoiceGateway ig = PersistenceFactory.forInvoice();
		
		BuisnessCheck.isFalse(workOrders.isEmpty(),"cannot be empty");
		
		BuisnessCheck.isTrue(ig.checkWorkOrdersExist(workOrders), "Work Order doesn t exist");
		
		BuisnessCheck.isTrue(ig.checkWorkOrdersFinished(workOrders), "Workorder is not finished yet");
			
		
			long numberInvoice = ig.generateInvoiceNumber();
			double amount = ig.calculateTotalInvoice(workOrders); // vat not included
			double vat = vatPercentage(amount, invoice.date);
			invoice.vat=vat;
			double total = amount * (1 + vat/100); // vat included
			total = Round.twoCents(total);
			invoice.total=total;
			invoice.number=numberInvoice;
			
			
			ig.add(InvoicingAssembler.toDALDto(invoice));
			
			ig.linkWorkordersToInvoice(this.invoice.id, workOrders);
			ig.markWorkOrderAsInvoiced(workOrders);
			ig.updateVersion(workOrders);
		
			
			
		return this.invoice;
	}

}
