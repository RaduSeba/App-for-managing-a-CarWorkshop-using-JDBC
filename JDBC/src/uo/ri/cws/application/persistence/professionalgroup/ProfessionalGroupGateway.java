package uo.ri.cws.application.persistence.professionalgroup;

import uo.ri.cws.application.persistence.Gateway;

import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;
public interface ProfessionalGroupGateway extends Gateway<ProfessionalGroupDALDto>
{
	
	
	ProfessionalGroupDALDto findId(String name);
	
	public class ProfessionalGroupDALDto{
		
		public String id;
		public Long version;
		
		public String name;
		public double productivity;
		public double payment;
		
		
		
	}
	
	
	
}
