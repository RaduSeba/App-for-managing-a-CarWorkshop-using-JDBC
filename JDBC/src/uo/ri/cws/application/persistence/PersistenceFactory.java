package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.impl.ContractGatewayImpl;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.impl.ContractTypeGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.impl.ProfessionalGroupGatewayImpl;

public class PersistenceFactory {

	
	
    public static MechanicGateway forMechanic() {
	return new MechanicGatewayImpl();
    }
    



    
      //public static WorkOrderGateway forWorkOrder() { return new
    // WorkOrderGatewayImpl(); }
      
      public static InvoiceGateway forInvoice() { return new
      InvoiceGatewayImpl(); }


	public static ContractGateway forContract() {
		// TODO Auto-generated method stub
		return new ContractGatewayImpl();
	}
      
	public static ContractTypeGateway forContractType() {
		// TODO Auto-generated method stub
		return new ContractTypeGatewayImpl();
	}
	
	public static ProfessionalGroupGateway forProfessionalGroup() {
		
		return new ProfessionalGroupGatewayImpl();
	}
	
    /* 
     * public static ClientGateway forClient() { return new ClientGatewayImpl();
     * }
     * 
     * public static VehicleGateway forVehicle() { return new
     * VehicleGatewayImpl(); }
     */

}
