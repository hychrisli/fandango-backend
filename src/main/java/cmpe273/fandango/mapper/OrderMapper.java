package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import cmpe273.fandango.entity.TicketOrder;

import javax.persistence.criteria.Order;

public interface OrderMapper {
  OrderDto toDto (TicketOrder pojo);
}
