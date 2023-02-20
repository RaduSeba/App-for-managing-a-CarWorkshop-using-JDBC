package uo.ri.cws.application.persistence.professionalgroup.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ProfessionalGroupGatewayImpl implements ProfessionalGroupGateway {

	@Override
	public void add(ProfessionalGroupDALDto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProfessionalGroupDALDto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<ProfessionalGroupDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ProfessionalGroupDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessionalGroupDALDto findId(String name) {
		
		ProfessionalGroupDALDto group = null;

    	Connection c = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;

    	try {
    	    c = Jdbc.getCurrentConnection();

    	    pst = c.prepareStatement(
    		    Conf.getInstance().getProperty("TPROFESSIONALGROUP_findId"));
    	    
    	    pst.setString(1, name);

    	    rs = pst.executeQuery();

    	    group = ProfessionalGroupAssembler.ProfessionalGroupDALDto(rs);

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
    	return group;
        }
	}

