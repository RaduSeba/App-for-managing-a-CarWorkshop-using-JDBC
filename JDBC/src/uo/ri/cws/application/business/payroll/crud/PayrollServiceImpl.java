package uo.ri.cws.application.business.payroll.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService;

public class PayrollServiceImpl implements PayrollService {

	public PayrollServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generatePayrolls() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generatePayrolls(LocalDate present) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLastPayrollFor(String mechanicId)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLastPayrolls() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PayrollBLDto> getPayrollDetails(String id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(
			String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
