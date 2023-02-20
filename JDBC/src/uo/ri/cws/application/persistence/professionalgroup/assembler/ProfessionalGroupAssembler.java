package uo.ri.cws.application.persistence.professionalgroup.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;


public class ProfessionalGroupAssembler {


	public static ProfessionalGroupDALDto ProfessionalGroupDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return resultSetToProfessionalGroupDALDto(m);
		}
		else 	
			return null;
	}
	
	
	private static ProfessionalGroupDALDto resultSetToProfessionalGroupDALDto(ResultSet rs) throws SQLException {
		ProfessionalGroupDALDto value = new ProfessionalGroupDALDto();
		
		value.id = rs.getString("id");
		value.version = rs.getLong("version");
		
		value.name=rs.getString("name");
		value.productivity=rs.getDouble("productivitybonuspercentage");
		value.payment=rs.getDouble("trienniumpayment");
		
		return value;
	}

}
