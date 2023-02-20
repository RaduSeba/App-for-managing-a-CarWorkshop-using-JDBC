package uo.ri.cws.application.persistence.invoice;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;

public interface InvoiceGateway extends Gateway<InvoiceDALDto>{

	/**
	 * @param invoice's number 
	 * @return invoice dto or null if it does not exist
	 */
	Optional<InvoiceDALDto> findByNumber(Long number);
	
	/**
	 * @return next invoice number to assign; that is, one greater than the 
	 * 			greatest number already assigned to an invoice + 1 
	 * 
	 * Notice that, in a real deployment, this way to get a new invoice number 
	 * may produce incorrect values in a concurrent environment because two
	 * concurrent threads could get the same number.
	 * @ 
	 *  
	 */
	Long getNextInvoiceNumber() ;

	public class InvoiceDALDto {

		public String id;		// the surrogate id (UUID)
		public Long version;
		
		public Double amount;	// total amount (money) vat included
		public Double vat;		// amount of vat (money)
		public Long number;		// the invoice identity, a sequential number
		public LocalDate date;		// of the invoice
		public String state;	// the state PAID, NOT_YET_PAID
	}

	  public class WorkOrderForInvoicingDALDto {

			public String id;

			public String description;
			public Date date;
			public String state;
			public double amount;
			
		    }


	
	
	Double calculateTotalInvoice(List<String> workOrderIDS);

	
	double getWorkOrderTotal(String workOrderID);

	void linkWorkordersToInvoice(String invoiceId, List<String> workOrderIDS);

	void markWorkOrderAsInvoiced(List<String> ids);

	void updateVersion(List<String> workOrderIds) ;

	
	boolean checkWorkOrdersExist(List<String> workOrderIDS);

	
	boolean checkWorkOrdersFinished(List<String> workOrderIDS);

	Long generateInvoiceNumber();

	List<WorkOrderForInvoicingDALDto> findNotInvoicedWorkOrders(String dni);

	boolean checkClientExists(String dni);

}