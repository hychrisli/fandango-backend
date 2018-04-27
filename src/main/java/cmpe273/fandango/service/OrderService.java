package cmpe273.fandango.service;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import org.springframework.data.domain.Page;

public interface OrderService {

  OrderDto createOrder(ParamCreateOrder dto);

  OrderDto getOrder(Long orderId);

  OrderDto cancelOrder(Long orderId);

  OrderDto payOrder(Long orderId);

  Page<OrderDto> getOrderByUserId(Integer userId);

  Page<OrderDto> getOrderByMovieId(Integer movieId);


}
