package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.entity.TicketOrder;

public interface OrderMapper {
  public OrderDto toDto (TicketOrder pojo);
}
