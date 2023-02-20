package uo.ri.cws.application.business.mechanic.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

public class MechanicAssembler {

	
	public static Optional<MechanicBLDto> toBLDto(Optional<MechanicDALDto> arg) {
		Optional<MechanicBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toMechanicDto(arg.get()));
		return result;
	}

	public static List<MechanicBLDto> toDtoList(List<MechanicDALDto> arg) {
		List<MechanicBLDto> result = new ArrayList<MechanicBLDto>();
		for (MechanicDALDto mr : arg)
			result.add(toMechanicDto(mr));
		return result;
	}

	public static MechanicDALDto toDALDto(MechanicBLDto arg) {
		MechanicDALDto result = new MechanicDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		return result;
	}
	
	

	private static MechanicBLDto toMechanicDto(MechanicDALDto arg) {

		MechanicBLDto result = new MechanicBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.name = arg.name;
		result.surname = arg.surname;
		result.dni = arg.dni;
		return result;
	}
	
}