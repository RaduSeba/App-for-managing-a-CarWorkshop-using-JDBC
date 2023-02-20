package uo.ri.cws.application.business.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

public interface PayrollService {

    /**
     * It generates all payrolls in current month only.
     * Notice next method and be careful don't repeat code, when possible.
     * 
     * @throws {@link BusinessException}
     */
    void generatePayrolls( ) throws BusinessException;

    /**
     * It generates all payrolls in the date passed as argument
     * @param arg Payroll will be generated considering this as present day
     *
     * @throws {@link BusinessException}
     */
    void generatePayrolls(LocalDate present) throws BusinessException;

    /**
     * It deletes the payroll generated last month for the mechanic passed as argument
     *
     * @param mechanic identifier
     * @throws {@link BusinessException}
     */
    void deleteLastPayrollFor(String mechanicId) throws BusinessException;

    /**
     * It deletes all payrolls generated this month.
     * 
     * @throws {@link BusinessException}
     */
    void deleteLastPayrolls() throws BusinessException;

    /**
     * It returns one payroll details.
     *
     * @param id payroll id
     * @return payroll dto 
     * @throws IllegalArgumentException when argument is null or empty
     */
    Optional<PayrollBLDto> getPayrollDetails(String id) throws BusinessException;

    /**
     * Returns all payrolls (a summary).
     *
     * @return List of all payrolls summary, or empty.
     * @throws {@link BusinessException}
     */
    List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException;

    /**
     * Returns all payrolls (a summary) for a certain mechanic.
     *
     * @param id mechanic id
     * @return List of payrolls summary, or empty.
     * @throws IllegalArgument Exception when argument is null or empty
     * @throws BusinessException when mechanic does not exist
     */
    List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id) throws BusinessException;

    /**
     * It returns all payrolls (a summary) for a give professional group
     * 
     * @param name the name of the professional group
     * @throws BussinessException DOES NOT
     * @throws IllegalArgumentException when argument is null or empty.
     */
    List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(String name) throws BusinessException;

    public class PayrollBLDto {

    	public String id;
    	public long version;
    	
    	public String contractId;
    	public LocalDate date;
    	
    	// Earnings
    	public double monthlyWage;
    	public double bonus;
    	public double productivityBonus;
    	public double trienniumPayment;
    	
    	// Deductions
    	public double incomeTax;
    	public double nic;
    	
    	// Net wage
    	public double netWage;
    }

    public class PayrollSummaryBLDto {

    	public String id;
    	public long version;
    	
    	public LocalDate date;
    	
    	public double netWage;

    }

}
