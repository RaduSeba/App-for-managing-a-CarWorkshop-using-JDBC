package uo.ri.cws.application.persistence.contracttype.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

public class ContractTypeAssembler {

	public static List<ContractTypeDALDto> ContractTypeDALDtoList(ResultSet rs) throws SQLException {
		
		List<ContractTypeDALDto> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToContractTypeDALDto(rs));
		}

		return res;
	}
	
	
	private static ContractTypeDALDto resultSetToContractTypeDALDto(ResultSet rs) throws SQLException {
		ContractTypeDALDto value = new ContractTypeDALDto();
		
		value.id = rs.getString("id");
		value.version = rs.getLong("version");
		
		value.compensation=rs.getDouble("compensationdays");
		value.name = rs.getString("name");
		
		return value;
	}


	public static ContractTypeDALDto ContractTypeDALDto(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return resultSetToContractTypeDALDto(rs);
		}
		else 	
			return null;
	}
	
	public static Optional<ContractTypeDALDto> toContractTypeOptionalDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToContractTypeDALDto(m));
		}
		else 	
			return Optional.ofNullable(null);
	}

}
