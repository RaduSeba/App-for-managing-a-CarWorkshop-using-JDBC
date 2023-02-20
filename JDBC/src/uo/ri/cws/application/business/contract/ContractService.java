package uo.ri.cws.application.business.contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

public interface ContractService {

	/**
	 * This method creates a new contract for a given mechanic. 
	 * If there already exists an earlier contract for the same mechanic and this is in force,
	 * it will be marked as terminated and the settlement will be computed.
	 * Contract start date will be the first of the next month.
	 *  
	 * @param c, contract business DTO. Consider only fields mechanic dni, type name,
	 * professionalGroup name, startDate, 
	 * endDate (if not null) and annuaBaseWage. 
	 * Other will be ignored. All them, except end date are mandatory.
	 * 
	 * @throws BusinessException when:
	 * 		- Contract type does not exist, or 
	 * 		- mechanic does not exist, or 
	 * 		- professional group does not exist, or
	 * 		- end date, when no null, is not later than start date.
	 * @throws IllegalArgumentException when:
	 * 		- argument is null, or
	 * 		- one of the mandatory values is null or empty String, or
	 * 		- professional group is FIXED_TERM and end date is null, or
	 * 		- annualBaseWage is not greater than 0
	 */
	ContractBLDto add(ContractBLDto c) throws BusinessException;
	
	/**
	 * It updates a contract that must be in force.
	 * Only three of the fields in the argument will be considered: id to identify the contract to update and
	 * endDate (if not null in the argument) and annualBaseWage, to update values stored. 
	 * 
	 * If endDate provided is not null, it must be a valid future date and will be updated. If it is null, 
	 * then contract endDate will be set to null.
	 * 
	 * @param dto, just id, endDate and annualBaseWage are mandatory. Other fields in the argument 
	 * will be ignored.
	 * 
	 * @throws BusinessException when:
	 * 		- The contract does not exist, or
	 * 		- the contract is no longer in force, or
	 * 		- contract type is FIXED_TERM and end date is earlier than startDate, or
	 * 		- the contract is no longer in force.
	 * @throws IllegalArgumentException when:
	 * 		- arg is null, or
 	 * 		- id is null or empty, or
	 * 		- the annualBaseWage is a negative value.
	 */
	void updateContract(ContractBLDto dto) throws BusinessException;
	
	/**
	 * It deletes the contract, if possible.
	 * 
	 * @param id Contract identifier
	 * @throws BusinessException when:
	 * 		- The contract does not exist, or
	 * 		- mechanic has workorders, or
	 * 		- there are payrolls for this contract.
	 * @throws IllegalArgumentException when
 	 * 		- id is null or empty.
	 */
	void deleteContract(String id) throws BusinessException;
	
	/**
	 * It changes contract to TERMINATED and calculates settlement according the rules described in the 
	 * problem statement document.
	 * 
	 * @param contractId, 
	 * @throws BusinessException when, 
	 * 		- The contract does not exist, or
	 * 		- the contract is not in force, or
	 * @throws IllegalArgumentException when
 	 * 		- id is null or empty, or
	 */
	void terminateContract(String contractId) throws BusinessException;

	/**
	 * @param contractId, 
	 * @return el dto para el contrato con todos los campos rellenados, 
	 * o nulo si el contrato no existe
	 * @throws BusinessException DOES NOT
	 * @throws IllegalArgumentException when id is null or empty.
	 */
	Optional<ContractBLDto> findContractById(String id) throws BusinessException;
	
	/**
	 * @param mechanic dni 
	 * @return a list with every ContractsByMechanic_BLDto registered for this mechanic, probably empty
	 * @throws BusinessException DOES NOT
	 * @throws IllegalArgumentException when id is null or empty. 
	 */
	List<ContractSummaryBLDto> findContractsByMechanic(String mechanicDni) throws BusinessException;

	/**
	 * @return a list with every Contract_BLDto registered, probably empty
	 * @throws BusinessException DOES NOT
	 * @throws IllegalArgumentException when id is null or empty. 
	 */
	List<ContractSummaryBLDto> findAllContracts( ) throws BusinessException;
	
	/*
	 * To communicate BL - UI
	 */

	public enum ContractState  {TERMINATED, IN_FORCE}

	public class ContractBLDto {

		public String id;
		public long version;
		
		public String dni;
		public String contractTypeName;
		public String professionalGroupName;
		public LocalDate startDate;
		public LocalDate endDate;
		public double annualBaseWage;

		// Filled in reading operations
		public double settlement;
		public ContractState state;
	}
	
	public class ContractSummaryBLDto {

		public String id;
		public long version;
		
		public String dni;

		// Filled in reading operations
		public double settlement;
		public ContractState state;

		// For summary only
		public int numPayrolls;
	}
}
