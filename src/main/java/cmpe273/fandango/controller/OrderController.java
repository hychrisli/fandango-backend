package cmpe273.fandango.controller;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamFilterOrder;
import cmpe273.fandango.exception.AppException;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cmpe273.fandango.constant.UrlConstant.ORDERS;
import static cmpe273.fandango.exception.ErrorCode.ERR_BAD_REQUEST;

@RestController
@Api(tags = {"Order"})
@Transactional(rollbackFor = Exception.class)
public class OrderController extends AbstractController {

  @Autowired
  OrderService orderService;

  @ApiOperation(value = "Get Orders with Filter Conditions [Topic: orders]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", dataType = "integer", paramType = "query",
          value = "Filter Orders per User ID"),
      @ApiImplicitParam(name = "movieId", dataType = "integer", paramType = "query",
          value = "Filter Orders per Movie ID"),
      @ApiImplicitParam(name = "orderDate", dataType = "date", paramType = "query",
          value = "Filter Order by date. yyyy-MM-dd"),
      @ApiImplicitParam(name = "orderMonth", dataType = "date", paramType = "query",
          value = "Filter Order by month. yyyy-MM"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(ORDERS)
  public Page<OrderDto> getMovies(ParamFilterOrder dto, Pageable pageable) {
    return orderService.getOrders(dto, pageable);
  }


}
