package uo.ri.cws.application.ui.cashier.action;

import menu.Action;
import uo.ri.cws.application.business.BusinessException;

public class SettleInvoiceAction implements Action {

	/**
	 * Algorithm:
	 * 
	 *  - Ask user invoice number 
	 *  - Retrieve invoice info
	 *  - Display invoice info
	 *  - Check is unpaid (state <> 'PAID')
	 *  - List payment methods accepted for the customer
	 *  - Ask user to type amount to charge in each payment method
	 *  	Check that sum of amounts equals invoice amount
	 *  - Record payments in the DDBB
	 *  - Increase total for each payment method
	 *  - Decrease money available in coupon if any has been redeemed
	 *  - Finally, mark invoice as paid 
	 *  
	 */
	@Override
	public void execute() throws BusinessException {
		// TODO ...
	}

}
