package uo.ri.cws.application.business.invoice.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.WorkOrderForInvoicingDALDto;
//import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class InvoicingAssembler {

	public static InvoiceBLDto toDto(InvoiceDALDto arg) {
		InvoiceBLDto result = new InvoiceBLDto();
		result.id = arg.id;
		result.number = arg.number;
		result.state = InvoiceState.valueOf(arg.state);
		result.date = arg.date;
		result.total = arg.amount;
		result.vat = arg.vat;
		return result;
	}
	
	
	public static InvoiceDALDto toDALDto(InvoiceBLDto arg) {
		
		InvoiceDALDto result = new InvoiceDALDto();
		
		result.id = arg.id;
		result.number = arg.number;
		result.state = arg.state.toString();
		result.date = arg.date;
		result.amount = arg.total;
		result.vat = arg.vat;
		
		return result;
	}


	

	
	  public static List<WorkOrderForInvoicingBLDto> toInvoicingWorkOrderList (List<WorkOrderForInvoicingDALDto> arg)
	  { 
		  List<WorkOrderForInvoicingBLDto> result = new	ArrayList<WorkOrderForInvoicingBLDto>(); 
		  for (WorkOrderForInvoicingDALDto record : arg)
			  result.add(toDto(record)); return result; 
	  }
	  
	  private static WorkOrderForInvoicingBLDto toDto(WorkOrderForInvoicingDALDto record) {
	  WorkOrderForInvoicingBLDto dto = new WorkOrderForInvoicingBLDto(); 
	 
	  dto.id =record.id; 
	  dto.date = record.date.toLocalDate().atStartOfDay(); 
	  dto.description = record.description;
	  dto.state = record.state;
	  dto.total = record.amount;
	  
	  return dto; }
	 
}
