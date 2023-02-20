package uo.ri.cws.application.business.paymentmean.voucher;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.PaymentMeanBLDto;

public interface VoucherService {
    /**
     * Generate the vouchers following one or several policies:
     * 	- by total number of workorders in their vehicles
     * 	- by number of recommendations
     * 	- by invoices over 500 euros
     * 
     * @return a counter with the number of generated vouchers
     * DOES NOT @throws BusinessException.
     */
    int generateVouchers() throws BusinessException;

    /**
     * Returns vouchers identified by its id
     * 
     * @param id
     * @return the voucher
     * DOES NOT @throws BusinessException.
     * @throws IllegalArgumentException if the argument is null or blank
     */
    Optional<VoucherBLDto> findVouchersById(String id) throws BusinessException;
    
    /**
     * Returns all the vouchers for the client identified by its id
     * 
     * @param id
     * @return A list with vouchers or empty if there is no voucher from the client
     * DOES NOT @throws BusinessException.
     * @throws IllegalArgumentException if the argument is null or blank
     */
    List<VoucherBLDto> findVouchersByClientId(String id) throws BusinessException;

    /**
     * Returns data for the vouchers, aggregated by client id 
     * 
     * @return A list of aggregated information of the vouchers of each client or empty if there is no voucher
     * DOES NOT @throws BusinessException.
     */
    List<VoucherSummaryBLDto> getVoucherSummary() throws BusinessException;
    
    public class VoucherBLDto extends PaymentMeanBLDto {

    	public String code;
    	public String description;
    	public Double balance;

    }

    
    /**
     * An aggregated result of all vouchers of a client
     */
    public class VoucherSummaryBLDto {
    	public String dni;			// of the client 
    	public String name;			// of the client 
    	public String surname;		// of the client 
    	public int issued;			// how many vouchers has been issued
    	public double totalAmount;	// the total amount "voucherized" (money)
    	public double availableBalance;	// how much remains available for the client
    	public double consumed;		// how much has been 
    	
    }

}
