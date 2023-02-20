package uo.ri.cws.application.business.invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoicingService {

    /**
     * Create a new invoice billing the workorders in the argument The new
     * invoice will be in NOT_YET_PAID state, the workorders will be marked as
     * INVOICED
     * 
     * @param the list of workorder ids to bill
     * @throws BusinessException        if - the state of any of the workorders
     *                                  is not FINISHED - any of the workorders
     *                                  does not exist
     * @throws IllegalArgumentException if list is null, empty or any of the
     *                                  strings in the list is empty or null
     */
    InvoiceBLDto createInvoiceFor(List<String> workOrderIds)
	    throws BusinessException;

    /**
     * Returns a list with info of all the work orders of all the client's
     * vehicles
     * 
     * @param dni of the client
     * @return a list of InvoicingWorkOrderDto or empty list if there is none
     * @throws BusinessException DOES NOT
     */
    List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(String dni)
	    throws BusinessException;

    /**
     * Find FINISHED BUT NOT INVOICED workorders due to a client with certain
     * dni.
     * 
     * @param the dni
     * @throws BusinessException if - client with this dni does not exist
     *                           IllegalArgumentException if dni is empty
     */
    List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(
	    String dni) throws BusinessException;

    /**
     * Returns a list with info of all the work orders of a vehicle
     * 
     * @param plate, the plate number of the vehicle
     * @return a list of InvoicingWorkOrderDto or empty list if there is none
     * @throws BusinessException DOES NOT
     */
    List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(String plate)
	    throws BusinessException;

    /**
     * @param number, of the invoice
     * @return the InvoiceDto with the data of the invoice
     * @throws BusinessException DOES NOT
     */
    Optional<InvoiceBLDto> findInvoiceByNumber(Long number)
	    throws BusinessException;

    /**
     * @param dni of the client
     * @return the list of the PaymentMeanDto of a client represented by the dni
     *         or an empty list if none
     * @throws BusinessException DOES NOT
     */
    List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(String dni)
	    throws BusinessException;

    /**
     * Creates the charges against the indicated payment means (with the amount
     * indicated) and then pass the invoice to the PAID state.
     *
     * @param invoiceId, the id of the invoice to be paid
     * @param charges,   a List of ChargeDto whose: - Key (String) represents
     *                   the payment mean id, and - Value (Double) represents
     *                   the amount to be charged to the payment mean
     * @throws IllegalArgumentException if - invoiceId is null or empty -
     *                                  charges is null
     * @throws BusinessException        if - there is no invoice for the
     *                                  invoiceId provided - the indicated
     *                                  invoice is already in PAID state - does
     *                                  not exist any of the payment means
     *                                  indicated by the id - the addition of
     *                                  all amounts charged to each payment mean
     *                                  does not equals the amount of the
     *                                  invoice with a precision of two cents (
     *                                  Math.abs( total - amount) <= 0.01 ) -
     *                                  any of the payment means cannot be used
     *                                  to pay the specified amount: - For a
     *                                  CreditCard, if the current date is after
     *                                  the validThough date - For a Voucher, if
     *                                  it has not enough available for the
     *                                  amount - For Cash there is no
     *                                  constraint, cash can be used always
     *
     *                                  Note (JUST FOR JPA IMPLEMENTATION): the
     *                                  domain model does not have the proper
     *                                  design to do it polymorphically,
     *
     *                                  THUS
     *
     *                                  add a public abstract boolean canPay(
     *                                  amount ); method to PaymentMean class
     *                                  and the proper specialization on the
     *                                  child classes
     */
    void settleInvoice(String invoiceId, List<Charge_BLDto> charges)
	    throws BusinessException;

    public class InvoiceBLDto {

	public String id; // the surrogate id (UUID)
	public long version;

	public double total; // total amount (money) vat included
	public double vat; // amount of vat (money)
	public long number; // the invoice identity, a sequential number
	public LocalDate date; // of the invoice
	public InvoiceState state = InvoiceState.NOT_YET_PAID; // the state
							       // PAID,
							       // NOT_YET_PAID

	public enum InvoiceState {
	    NOT_YET_PAID, PAID
	}
    }

    public class WorkOrderForInvoicingBLDto {

	public String id;

	public String description;
	public LocalDateTime date;
	public String state;
	public double total;
    }

    public abstract class PaymentMeanForInvoicingBLDto {
	public String id;
	public long version;

	public String clientId;
	public double accumulated;

    }

    public class Charge_BLDto {

	public String id;
	public long version;
	public String invoice_id;
	public String paymentMean_id;
	public double amount;

    }

}
