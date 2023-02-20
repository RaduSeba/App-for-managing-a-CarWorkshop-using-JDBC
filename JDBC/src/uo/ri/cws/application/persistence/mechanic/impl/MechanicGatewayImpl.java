package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class MechanicGatewayImpl implements MechanicGateway {

    // the responsible for closing the connection is the business layer
    @Override
    public void add(MechanicDALDto mechanic) {
	// Process
    	
    	
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(
		    Conf.getInstance().getProperty("TMECHANICS_ADD"));
	    pst.setString(1, mechanic.id);
	    pst.setString(2, mechanic.dni);
	    pst.setString(3, mechanic.name);
	    pst.setString(4, mechanic.surname);
	    pst.setLong(5, mechanic.version);

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
    		    Conf.getInstance().getProperty("TMECHANICS_DELETE"));
    	   
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
    public void update(MechanicDALDto t) {
	// TODO Auto-generated method stub

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
		    pst.setString(1, t.name);
		    pst.setString(2, t.surname);
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
    public Optional<MechanicDALDto> findByDni(String dni) {

	Optional<MechanicDALDto> mechanic = null;

	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(
		    Conf.getInstance().getProperty("TMECHANICS_findByDni"));
	    
	    pst.setString(1, dni);

	    rs = pst.executeQuery();

	    mechanic = MechanicAssembler.toMechanicDALDto(rs);

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
	return mechanic;
    }

    @Override
    public List<MechanicDALDto> findAll() {

	List<MechanicDALDto> mechanics = null;

	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	
	    pst = c.prepareStatement(
			    Conf.getInstance().getProperty("TMECHANICS_findAll"));

	    rs = pst.executeQuery();
	    
	    mechanics = MechanicAssembler.toMechanicDALDtoList(rs);
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
	return mechanics;

    }

    @Override
    public Optional<MechanicDALDto> findById(String id) {
    	
    	Optional<MechanicDALDto> mechanic = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TMECHANICS_findByID"));
    	    
    	    pst.setString(1, id);


    	    rs = pst.executeQuery();

    	    mechanic = MechanicAssembler.toMechanicDALDto(rs);

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
    	return mechanic;
    }
    
    
    
    
    
    @Override
	public MechanicDALDto findByDni2(String dni) {

    	MechanicDALDto mechanic = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TMECHANICS_findByDni"));
    	    
    	    pst.setString(1, dni);

    	    rs = pst.executeQuery();

    	    mechanic = MechanicAssembler.toMechanicDALDto2(rs);

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
    	return mechanic;
        }
    
    @Override
    public MechanicDALDto findById2(String id) {
    	
    	MechanicDALDto mechanic = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TMECHANICS_findByID"));
    	    
    	    pst.setString(1, id);

    	    rs = pst.executeQuery();

    	    mechanic = MechanicAssembler.toMechanicDALDto2(rs);

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
    	return mechanic;
    }
    
    @Override
	public boolean checkWorkorder(String id) {

    
    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TMECHANIC_findByIDWorkorder"));
    	    
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

}
