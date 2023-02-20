package uo.ri.cws.application.persistence.contract.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;



public class ContractAssembler {

	public static List<ContractDALDto> toContractDALDtoList(ResultSet rs) throws SQLException {
		
		
		List<ContractDALDto> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToContractDALDto(rs));
		}

		return res;
	}
	
	
	
	public static Optional<ContractDALDto> toContractDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToContractDALDto(m));
		}
		else 	
			return Optional.ofNullable(null);
	}
	
	
	public static ContractDALDto toContractDALDto2(ResultSet m) throws SQLException {
		if (m.next()) {
			return resultSetToContractDALDto(m);
		}
		else 	
			return null;
	}
	
	
	
	
	private static ContractDALDto resultSetToContractDALDto(ResultSet rs) throws SQLException {
		ContractDALDto value = new ContractDALDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");
		
		value.dni = rs.getString("MECHANIC_ID");
		value.annualBaseWage = rs.getDouble("ANNUALBASEWAGE");
		value.contractTypeName = rs.getString("CONTRACTTYPE_ID");
		value.professionalGroupName=rs.getString("PROFESSIONALGROUP_ID");
		value.startDate=rs.getDate("STARTDATE");
		value.endDate= rs.getDate("ENDDATE");
		value.settlement=rs.getDouble("SETTLEMENT");
		value.state=rs.getString("STATE");
		
		
		return value;
	}

}
