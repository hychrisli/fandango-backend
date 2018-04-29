package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.OrderDao;
import cmpe273.fandango.dao.SalesDao;
import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dao.filter.OrderFilter;
import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import cmpe273.fandango.dto.ParamFilterOrder;
import cmpe273.fandango.entity.*;
import cmpe273.fandango.lib.Calc;
import cmpe273.fandango.lib.DateTime;
import cmpe273.fandango.mapper.OrderMapper;
import cmpe273.fandango.mapper.ScheduleMapper;
import cmpe273.fandango.service.OrderService;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
  OrderDao orderDao;

  @Autowired
  ScheduleDao scheduleDao;

  @Autowired
  UserDao userDao;

  @Autowired
  SalesDao salesDao;

  @Autowired
  OrderMapper orderMapper;

  private static final Float taxRate = 0.07f;

  private static final Integer PENDING = 1;
  private static final Integer PAID = 2;
  private static final Integer CANCEL = 3;


  @Override
  public OrderDto createOrder(ParamCreateOrder dto) {
    Schedule schedule = scheduleDao.findOne(dto.getScheduleId());
    User user = userDao.findOne(dto.getUserId());
    if ( schedule == null || user == null || dto.getTicketNum() < 1 || dto.getTicketNum() > schedule.getAvailSeats()) return null;
    Float orderTotal = Calc.round(schedule.getPrice() * dto.getTicketNum(), 2);
    Float tax = Calc.round(orderTotal * taxRate, 2);
    Date orderTs = new Date();

    TicketOrder order = new TicketOrder();
    order.setSchedule(schedule);
    order.setUser(user);
    order.setTicketNum(dto.getTicketNum());
    order.setOrderTotal(orderTotal);
    order.setTax(tax);
    order.setGrandTotal(orderTotal + tax);
    order.setOrderTs(orderTs);
    order.setOrderDate(DateTime.getDate(orderTs));
    order.setOrderMonth(DateTime.getMonth(orderTs));

    schedule.setAvailSeats(schedule.getAvailSeats() - dto.getTicketNum());
    return orderMapper.toDto(orderDao.save(order));
  }

  @Override
  public OrderDto getOrder(Long orderId) {
    TicketOrder order = orderDao.findOne(orderId);
    if ( order == null ) return null;
    return orderMapper.toDto(order);
  }

  @Override
  public OrderDto cancelOrder(Integer userId, Long orderId) {
    return changeStatus(userId, orderId, PENDING, CANCEL);
  }

  @Override
  public Boolean deleteOrder(Long orderId) {
    TicketOrder order = orderDao.findOne(orderId);
    if ( order == null ) return false;
    Schedule schedule = order.getSchedule();
    schedule.setAvailSeats(schedule.getAvailSeats() + order.getTicketNum());
    scheduleDao.save(schedule);
    if ( order.getStatus().equals(PAID))
      updateSales(order, false);
    orderDao.delete(order);
    return true;
  }

  @Override
  public OrderDto payOrder(Integer userId,  Long orderId) {
    return changeStatus(userId, orderId, PENDING, PAID);
  }

  @Override
  public Page<OrderDto> getOrders(ParamFilterOrder dto, Pageable pageable) {
    Page<TicketOrder> orders = orderDao.findAll( new OrderFilter(dto), pageable);
    return new PageImpl<>(
        StreamSupport.stream(orders.spliterator(), false)
            .map( orderMapper::toDto).collect(Collectors.toList()), pageable, orders.getTotalElements());
  }

  private OrderDto changeStatus(Integer userId, Long orderId, Integer initStatus, Integer targetStatus){
    TicketOrder order = orderDao.findOne(orderId);
    User user = userDao.findOne(userId);
    if (order == null || order.getUser() != user || !order.getStatus().equals(initStatus)) return null;
    order.setStatus(targetStatus);
    if (targetStatus.equals(PAID)) updateSales(order, true);
    return orderMapper.toDto(orderDao.save(order));
  }

  private void updateSales(TicketOrder order, Boolean isBuy){
    Schedule schedule = order.getSchedule();
    Movie movie = schedule.getMovie();
    Theater theater = schedule.getTheater();
    Sales sales = salesDao.findByMovieIdAndTheaterId(movie.getMovieId(), theater.getTheaterId());

    if ( sales == null ) {
      sales = new Sales();
      sales.setMovie(movie);
      sales.setTheater(theater);
    }

    Integer ticketChange = isBuy ? order.getTicketNum() : - order.getTicketNum();
    Float dollarAmountChange = isBuy ? order.getOrderTotal() : - order.getOrderTotal();

    sales.setTicketNum(sales.getTicketNum() + ticketChange);
    sales.setDollarAmount(sales.getDollarAmount() + dollarAmountChange);
    salesDao.save(sales);
  }
}
