package uo.ri.cws.application.business.mechanic;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface MechanicService {

	/**
	 * Add a new mechanic to the system with the data specified in the dto.
	 * 		The id value will be ignored as it is generated here.
	 * @param mecanich A dto containing info to be added
	 * @return dto with the id value set to the UUID generated 
	 * @throws IllegalArgumentException when argument is null or dni is null or empty string
		BusinessException if there already exists another mechanic with the same dni
	 */
	MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException;

	/**
	 * @param idMechanic the id of the mechanid to be deleted
	 * @throws BusinessException if the mechanic does not exist or if there are work orders
	 * registered for this mechanic or if there are contracts for this mechanic (regarding the state)
	 * @throws IllegalArgumentException when argument is null or empty string
	 */
	void deleteMechanic(String idMechanic) throws BusinessException;

	/**
	 * Updates values for the mechanic specified by the id field,
	 * 		just name and surname will be updated
	 * @param mechanic A dto identifying the mechanic to update by the field id,
	 * 					and the data to update in name and surname
	 * @throws BusinessException if the mechanic does not exist 
	 * 	IllegalArgumentException when the argument is null or any of the fields (id, dni, name, surname) are null or empty 
	 */
	void updateMechanic(MechanicBLDto mechanic) throws BusinessException;

	/**
	 * @param idMechanic The id of the mechanic to find
	 * @return the dto for the mechanic or null if there is none with the id
	 * @throws IllegalArgumentException when argument is null or empty string
	 *        DOES NOT throw BusinessException
	 */
	Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException;

	/**
	 * @param dniMechanic The dni of the mechanic to find
	 * @return the dto for the mechanic or null if there is none with this dni
	 * @throws IllegalArgumentException when argument is null or empty string
	 *        DOES NOT throw BusinessException
	 */
	Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException;
	
	
	/**
	 * @return the list of all mechanics registered in the system
	 * 	It might be an empty list if there is no mechanic
	 *
	 * DOES NOT @throws BusinessException
	 */
	List<MechanicBLDto> findAllMechanics() throws BusinessException;

	/**
	 * 
	 * 						ONLY FOR EXTENSION
	 * 
	 * @return the list of all mechanics registered in the system
	 * with a contract in force. It might be an empty list if there is no mechanic
	 *
	 * DOES NOT @throws BusinessException
	 */
	List<MechanicBLDto> findMechanicsInForce() throws BusinessException;

	/**
	 * @return the list of mechanics with contracts in force in a contract type, or 
	 * 		an empty list if there is none
	 * @throws IllegalArgumentException if
	 * 		- id is null or empty
	 * @throws BusinessException DOES NOT
	 */
	List<MechanicBLDto> findMechanicsWithContractInForceInContractType(String name) throws BusinessException;

	/**
	 * @return the list of mechanis in a professional group, or 
	 * 		an empty list if there is none
	 * @throws BusinessException DOES NOT
	 */
	List<MechanicBLDto> findMechanicsInProfessionalGroups(String name) throws BusinessException;

	public class MechanicBLDto {

		public String id;
		public long version;
		
		public String dni;
		public String name;
		public String surname;

	}
}