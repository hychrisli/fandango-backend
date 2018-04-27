package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.OrderDao;
import cmpe273.fandango.dao.filter.OrderFilter;
import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import cmpe273.fandango.dto.ParamFilterOrder;
import cmpe273.fandango.entity.TicketOrder;
import cmpe273.fandango.mapper.OrderMapper;
import cmpe273.fandango.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
  OrderDao orderDao;

  @Autowired
  OrderMapper orderMapper;

  @Override
  public OrderDto createOrder(ParamCreateOrder dto) {
    return null;
  }

  @Override
  public OrderDto getOrder(Long orderId) {
    return null;
  }

  @Override
  public OrderDto cancelOrder(Long orderId) {
    return null;
  }

  @Override
  public OrderDto payOrder(Long orderId) {
    return null;
  }

  @Override
  public Page<OrderDto> getOrders(ParamFilterOrder dto, Pageable pageable) {
    Page<TicketOrder> orders = orderDao.findAll( new OrderFilter(dto), pageable);
    return new PageImpl<>(
        StreamSupport.stream(orders.spliterator(), false)
            .map( orderMapper::toDto).collect(Collectors.toList()), pageable, orders.getTotalElements());
  }
}
