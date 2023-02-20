package uo.ri.cws.application.business.workorder;

import uo.ri.cws.application.business.BusinessException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This service is intended to be used by the Mechanic It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface WorkOrderService {

    List<WorkOrderBLDto> findWorkordersByVehicle(String plate) throws BusinessException;


    public class WorkOrderBLDto {

        public String id;
        public long version;

        public String vehicleId;
        public String description;
        public LocalDateTime date;
        public double total;
        public String state;

        // might be null
        public String mechanicId;
        public String invoiceId;

    }
}
