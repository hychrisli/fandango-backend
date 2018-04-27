package cmpe273.fandango.service;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import cmpe273.fandango.dto.ParamFilterOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

  OrderDto createOrder(ParamCreateOrder dto);

  OrderDto getOrder(Long orderId);

  OrderDto cancelOrder(Long orderId);

  OrderDto payOrder(Long orderId);

  Page<OrderDto> getOrders(ParamFilterOrder dto, Pageable pageable);

}
