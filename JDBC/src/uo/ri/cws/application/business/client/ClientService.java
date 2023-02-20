package uo.ri.cws.application.business.client;

import uo.ri.cws.application.business.BusinessException;

import java.util.List;
import java.util.Optional;


public interface ClientService {


    /**
     * Add a new client to the system with the data specified in the dto.
     * The id value will be ignored as it is generated here.
     *
     * @param client A dto containing info to be added
     * @param
     * @return dto with the id value set to the UUID generated
     * @throws IllegalArgumentException when argument is null or dni is null or empty string
     * @throws BusinessException        if there already exists another client with the same dni
     */
    ClientBLDto addClient(ClientBLDto client, String recommenderId) throws BusinessException;

    /**
     * @param idClient the id of the client to be deleted
     * @throws BusinessException if the client does not exist or if there are vehicles
     *                           registered for this client
     * @throws IllegalArgumentException when argument is null or empty string
     */
    void deleteClient(String idClient) throws BusinessException;

    /**
     * Updates values for the client specified by the id field,
     * except id and dni
     *
     * @param client A dto identifying the client to update by the field id,
     *               and new data in the other fields
     * @throws BusinessException        if the client does not exist
     * @throws IllegalArgumentException when the argument is null or id is null or empty
     */
    void updateClient(ClientBLDto client) throws BusinessException;

    /**
     * @return the list of all clients registered in the system. It might be an empty list if there is no client
     * <p>
     * DOES NOT @throws BusinessException
     */
    List<ClientBLDto> findAllClients() throws BusinessException;

    /**
     * @param idClient The id of the client to find
     * @return the dto for the client or null if there is none with the id
     * @throws IllegalArgumentException when argument is null or empty string
     *                                  DOES NOT throw BusinessException
     */
    Optional<ClientBLDto> findClientById(String idClient) throws BusinessException;

    /**
     * @param sponsorID The id of the client who recommended the workshop to this client
     * @return the dto for the clients or empty if there is none recommended by the argument
     * @throws IllegalArgumentException when argument is null or empty string
     *                                  DOES NOT throw BusinessException
     */
    List<ClientBLDto> findClientsRecommendedBy(String sponsorID) throws BusinessException;


    public class ClientBLDto {
        public String id;
        public long version;

        public String dni;
        public String name;
        public String surname;
        public String phone;
        public String email;

        public String addressStreet;
        public String addressCity;
        public String addressZipcode;
    }

}
