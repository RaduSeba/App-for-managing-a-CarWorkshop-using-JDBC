package uo.ri.cws.application.business.vehicle;

import uo.ri.cws.application.business.BusinessException;

import java.util.List;
import java.util.Optional;

/**
 * This service is intended to be used by the Foreman
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleService {
    public List<VehicleBLDto> findByClientDni(String dni) throws BusinessException;

    public List<VehicleBLDto> findByMake(String make) throws BusinessException;

    /**
     * @param plate number
     * @return an Optional with the vehicle dto specified be the plate number
     * @throws BusinessException, DOES NOT
     */
    Optional<VehicleBLDto> findVehicleByPlate(String plate) throws BusinessException;

    public static class VehicleBLDto {
        public String id;
        public Long version;

        public String plateNumber;
        public String make;
        public String model;
        public String clientId;
        public String vehicleTypeId;

    }

}
