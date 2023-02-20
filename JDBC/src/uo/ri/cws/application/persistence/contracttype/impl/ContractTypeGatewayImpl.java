package uo.ri.cws.application.persistence.contracttype.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ContractTypeGatewayImpl implements ContractTypeGateway {

	

	@Override
	public void add(ContractTypeDALDto t) {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("ADD_CONTRACTTYPE"));
		    
		    pst.setString(1, t.id);
		    pst.setDouble(2, t.compensation);
		    pst.setString(3, t.name);
		    pst.setLong(4, t.version);
		    
		    

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
    		    Conf.getInstance().getProperty("TCONTRACTTYPE_DELETE"));
    	   
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
	public void update(ContractTypeDALDto t) {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TCONTRACTTYPES_UPDATE"));
		    
		    pst.setDouble(1, t.compensation);
		    pst.setString(2, t.name);

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
	public Optional<ContractTypeDALDto> findById(String id) {
		
		Optional<ContractTypeDALDto> contract = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTTYPE_findByID"));
    	    
    	    pst.setString(1, id);


    	    rs = pst.executeQuery();

    	    contract = ContractTypeAssembler.toContractTypeOptionalDALDto(rs);

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
	public List<ContractTypeDALDto> findAll() {
		
		List<ContractTypeDALDto> contracts = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		
		    pst = c.prepareStatement(
				    Conf.getInstance().getProperty("TCONTRACTTYPE_findAll"));

		    rs = pst.executeQuery();
		    
		    contracts = ContractTypeAssembler.ContractTypeDALDtoList(rs);
		    
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
	public ContractTypeDALDto findByName(String name) {
		
		
		ContractTypeDALDto contract = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTTYPE_findId"));
    	    
    	    pst.setString(1, name);

    	    rs = pst.executeQuery();

    	    contract = ContractTypeAssembler.ContractTypeDALDto(rs);

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
	public boolean checkContract(String i)
	{
		Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TCONTRACTTYPE_findByIDCONTRACT"));
    	    
    	    pst.setString(1, i);
    	    
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
	
	
}
