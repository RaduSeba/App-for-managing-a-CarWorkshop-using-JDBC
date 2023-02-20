package uo.ri.cws.application.persistence.invoice.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.WorkOrderForInvoicingDALDto;

public class InvoiceAssembler {

	private static InvoiceDALDto resultSetToInvoiceDALDto(ResultSet rs) throws SQLException {
		InvoiceDALDto record = new InvoiceDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.date = rs.getDate("date").toLocalDate();
		record.amount = rs.getDouble("amount");
		record.number = rs.getLong("number");
		record.vat = rs.getDouble("vat");
		record.state = rs.getString("state");
		return record;
	}
	
	
	public static List<InvoiceDALDto> toInvoiceList(ResultSet rs) throws SQLException {
		List<InvoiceDALDto> result = new ArrayList<InvoiceDALDto>();
		while (rs.next()) {
			result.add(resultSetToInvoiceDALDto(rs));
		}
		return result;
	}


	public static Optional<InvoiceDALDto> toInvoiceDALDto(ResultSet rs) throws SQLException {
		Optional<InvoiceDALDto> oir = Optional.empty();
		if (rs.next())
			oir = Optional.of(resultSetToInvoiceDALDto(rs));
		return oir;
	}
	
	
	public static List<WorkOrderForInvoicingDALDto> toInvoiceDALDtoList(ResultSet rs) throws SQLException {
		List<WorkOrderForInvoicingDALDto> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToWorkOrderForInvoicingDALDto(rs));
		}

		return res;
	}
	
	private static WorkOrderForInvoicingDALDto resultSetToWorkOrderForInvoicingDALDto(ResultSet rs) throws SQLException {
		WorkOrderForInvoicingDALDto value = new WorkOrderForInvoicingDALDto();
		
		value.id = rs.getString("id");
		
		
		value.date=rs.getDate("date");
		value.description=rs.getString("description");
		value.state=rs.getString("date");
		value.amount=rs.getDouble("amount");
		
		return value;
	}
	


}
