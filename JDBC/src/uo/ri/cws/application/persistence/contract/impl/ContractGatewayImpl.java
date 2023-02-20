package uo.ri.cws.application.persistence.contract.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.assembler.ContractAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ContractGatewayImpl implements ContractGateway {

	

	@Override
	public void add(ContractDALDto t) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("ADD_CONTRACT"));
		    pst.setString(1, t.id);
		    pst.setDouble(2,  t.annualBaseWage);
		    pst.setDate(3,  t.endDate);
		    pst.setDouble(4, t.settlement );
		    pst.setDate(5,  t.startDate);
		    pst.setString(6, t.state);
		    pst.setLong(7,t.version);
		    pst.setString(8, t.contractTypeName);
		    pst.setString(9, t.dni);
		    pst.setString(10,t.professionalGroupName);
		    

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
		
		Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;

    	try {
    		
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTS_DELETE"));
    	   
    	    pst.setString(1, id);
    	    
    	   

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
	public void updateEndDate(ContractDALDto t)
	{
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TCONTRACTS_UPDATEDATE"));
		    
		    pst.setDate(1,	Date.valueOf(  LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())));
		    pst.setDouble(2, calculateTotalSettlement(t));
		    pst.setString(3, t.id);

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
	public void update(ContractDALDto t) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TCONTRACTS_UPDATE"));
		    
		    pst.setDouble(1, t.annualBaseWage);
		    pst.setDate(2,	t.endDate);
		    pst.setString(3, t.id);

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
	public boolean checkPayrool(String id) {

    
    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTS_findByIDPAYROLL"));
    	    
    	    pst.setString(1, id);
    	    
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
	public List<ContractDALDto> findAll() {
		
		List<ContractDALDto> contracts = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		
		    pst = c.prepareStatement(
				    Conf.getInstance().getProperty("TCONTRACTS_findAll"));

		    rs = pst.executeQuery();
		    
		    contracts = ContractAssembler.toContractDALDtoList(rs);
		    
		} catch (SQLException e) {
		    throw new RuntimeException(e);
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
		return contracts;

	}

	@Override
	public Optional<ContractDALDto> findByDni(String dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public ContractDALDto findById2(String id) {
		
		ContractDALDto contract = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTS_findByID"));
    	    
    	    pst.setString(1, id);

    	    rs = pst.executeQuery();

    	    contract=ContractAssembler.toContractDALDto2(rs);

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
    	return contract;
    }
	
	@Override
	public String ContractState(String id)
	{
		String state = null;
		
    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTS_findState"));
    	    
    	    pst.setString(1, id);

    	    rs = pst.executeQuery();
    	    if(rs.next())
    	    {
    	    	 state=rs.getString("NAME");
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
    	return state;
		
	}

	@Override
	public Optional<ContractDALDto> findById(String id) {
		
		Optional<ContractDALDto> contract = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTS_findByID"));
    	    
    	    pst.setString(1, id);


    	    rs = pst.executeQuery();

    	    contract = ContractAssembler.toContractDALDto(rs);

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
    	return contract;
	}
		
	@Override
	public List<ContractDALDto> findByMechanic(String id){
		
		List<ContractDALDto> contracts = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		
		    pst = c.prepareStatement(
				    Conf.getInstance().getProperty("TCONTRACTS_findByMechanic"));
		    
		    pst.setString(1, id);

		    rs = pst.executeQuery();
		    
		    contracts = ContractAssembler.toContractDALDtoList(rs);
		    
		} catch (SQLException e) {
		    throw new RuntimeException(e);
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
		return contracts;

		
		
	}
	
@Override	
public ContractDALDto findByMechanicID(String id){
		
		ContractDALDto contracts = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		
		    pst = c.prepareStatement(
				    Conf.getInstance().getProperty("TCONTRACTS_findByMechanicID"));
		    
		    pst.setString(1, id);

		    rs = pst.executeQuery();
		    
		    contracts = ContractAssembler.toContractDALDto2(rs);
		    
		} catch (SQLException e) {
		    throw new RuntimeException(e);
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
		return contracts;

		
		
	}
	
	
	
	
	
	
	@Override
	public void terminate(ContractDALDto t) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TCONTRACTS_TERMINATE"));
		    
		    pst.setDouble(1, t.settlement);
		    pst.setDate(2,	t.endDate);
		    pst.setString(3, "TERMINATED");
		    pst.setString(4, t.id);

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
	public double calculateTotalSettlement(ContractDALDto c)
	{
		double totalsettlement = 0.0;
		long daysBetween = Math.abs(Date.valueOf(  LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())).getTime() - c.startDate.getTime());
		double daysDiff=daysBetween / (1000 * 60 * 60 * 24);
		double yearsDiff=daysBetween/(1000 * 60 * 60 * 24 * 365);
		int Year=(int)daysBetween/(1000 * 60 * 60 * 24 * 365);
		
		double d =daysDiff/yearsDiff  ;
		totalsettlement=getTotalWage(c.id)/365 * Year * d;
		
		return totalsettlement;
	}
	
	
	@Override
	public double getTotalWage(String id)
	{
		PreparedStatement pst = null;
		Connection c = null;
		ResultSet rs = null;
		Double money = 0.0;

		try {
			c = Jdbc.getCurrentConnection();
			 pst = c.prepareStatement(
					    Conf.getInstance().getProperty("FIND_WAGE_AMOUNT"));
			pst.setString(1, id);

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
	
		
	}

