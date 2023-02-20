package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.assembler.InvoiceAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class InvoiceGatewayImpl implements InvoiceGateway {


	@Override
	public void add(InvoiceDALDto t) {
		
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		

		try {
			c = Jdbc.getCurrentConnection();
			  pst = c.prepareStatement(
					    Conf.getInstance().getProperty("INSERT_INVOICE"));
			  
			pst.setString(1, t.id);
			pst.setLong(2, t.number);
			pst.setDate(3, java.sql.Date.valueOf(t.date));
			pst.setDouble(4, t.vat);
			pst.setDouble(5, t.amount);
			pst.setString(6, "NOT_YET_PAID");
			pst.setLong(7, 1L);

			pst.executeUpdate();

		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
		
	
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InvoiceDALDto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<InvoiceDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<InvoiceDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InvoiceDALDto> findByNumber(Long number) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	
	
	
	
	
	@Override
	public void updateVersion(List<String> workOrderIds)  {

		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		
		
		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("UPDATEVERSION_WORKORDERS"));

			for (String workOrderID : workOrderIds) {
				pst.setString(1, workOrderID);
				pst.executeUpdate();
				}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
	}


	


	@Override
	public boolean checkWorkOrdersExist(List<String> workOrderIDS)  {
		
		
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		
		
		
		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("FIND_WORKORDERS"));

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					return false;
				}

			}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
		return true;
	}

	/*
	 * checks whether every work order id is FINISHED	 
	 */

	
	
	
	@Override
	public boolean checkClientExists(String dni)
	{

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("findClient"));
    	    
    	    pst.setString(1, dni);

    	    rs = pst.executeQuery();
    	    
    	    if(rs.next())
    	    {
    	    	return true;
    	    }
    	   
    	
    		   
    		   
    		   
    	} catch (SQLException e) {
    	    throw new PersistenceException(e);
    	} finally {
    	    if (rs != null)
    		try {
    		    rs.close();
    		} catch (SQLException e) {
    		    /* ignore */ }
    	    if (pst != null)
    		try {
    		    pst.close();
    		} catch (SQLException e) {
    		    /* ignore */ }
    	}
    	return false;
	}
		
	
	
	
	@Override
	public boolean checkWorkOrdersFinished(List<String> workOrderIDS)  {
	
		
		
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("CHECK_WORKORDER_STATE"));

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				rs.next();
				String state = rs.getString(1); 
				if (! "FINISHED".equalsIgnoreCase(state) ) {
					return false;
				}

			}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
		return true;
	}

	/*
	 * Generates next invoice number (not to be confused with the inner id)
	 */
	
	
	
	
	@Override
	public Long generateInvoiceNumber()  {
		
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("LAST_INVOICE_NUMBER"));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
	}

	/*
	 * Compute total amount of the invoice  (as the total of individual work orders' amount 
	 */

	@Override
	public Double calculateTotalInvoice(List<String> workOrderIDS)  {

		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/*
	 * checks whether every work order id is FINISHED	 
	 */
	@Override
	public double getWorkOrderTotal(String workOrderID) {
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		Double money = 0.0;

		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("FIND_WORKORDER_AMOUNT"));
			pst.setString(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new SQLException("sasa");
			}

			money = rs.getDouble(1); 

		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
		return money;

	}

	/*
	

	/*
	 * Set the invoice number field in work order table to the invoice number generated
	 */
	@Override
	public void linkWorkordersToInvoice (String invoiceId, List<String> workOrderIDS)  {
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("LINK_WORKORDER_TO_INVOICE"));

			for (String workOrderId : workOrderIDS) {
				pst.setString(1, invoiceId);
				pst.setString(2, workOrderId);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
	}




	/*
	 * Sets state to INVOICED for every workorder
	 */
	@Override
	public void markWorkOrderAsInvoiced(List<String> ids)  {

		
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("MARK_WORKORDER_AS_INVOICED"));

			for (String id: ids) {
				pst.setString(1, id);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
		    // Change at the end to database error
		    throw new PersistenceException(e);
		} finally {
		    if (rs != null)
			try {
			    rs.close();
			} catch (SQLException e) {
			    /* ignore */ }
		    if (pst != null)
			try {
			    pst.close();
			} catch (SQLException e) {
			    /* ignore */ }
		}
	}

	@Override
	public Long getNextInvoiceNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<WorkOrderForInvoicingDALDto> findNotInvoicedWorkOrders(String dni)
	{
		
		List<WorkOrderForInvoicingDALDto> work=null;
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

	try {
		c = Jdbc.getCurrentConnection();
		 pst = c.prepareStatement(
				    Conf.getInstance().getProperty("NOT_INVOICED"));

		pst.setString(1, dni);
		
		rs = pst.executeQuery();
		
		 work= InvoiceAssembler.toInvoiceDALDtoList(rs);
		
	} catch (SQLException e) {
	    // Change at the end to database error
	    throw new PersistenceException(e);
	} finally {
	    if (rs != null)
		try {
		    rs.close();
		} catch (SQLException e) {
		    /* ignore */ }
	    if (pst != null)
		try {
		    pst.close();
		} catch (SQLException e) {
		    /* ignore */ }
	}
	
	return work;
	
	
}
	

}
