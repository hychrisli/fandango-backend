package cmpe273.fandango.controller;

import cmpe273.fandango.entity.City;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_CITIES;
import static cmpe273.fandango.constant.JsonConstant.KEY_CITY;
import static cmpe273.fandango.constant.JsonConstant.KEY_MESSAGE;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"City"})
@Transactional(rollbackFor = Exception.class)
public class CityController extends AbstractController{

  @Autowired
  CityService cityService;

  @ApiOperation(value = "Get Theater Cities [Topic: theaters]", response = JsonResponse.class)
  @GetMapping(CITIES)
  public ResponseEntity<JsonResponse> getCities() {
    List<City> cities = cityService.getAvailCities();
    if ( cities != null )
      return success(KEY_CITIES, cities);
    return notFound();
  }

  @ApiOperation(value = "Add a new city for theaters [Topic: theaters]", response = JsonResponse.class,
      notes = "cityId field is ignored")
  @PostMapping(CITY)
  public ResponseEntity<JsonResponse> createCity(@RequestBody City city) {
    if ( city.getCityName() == null || city.getState() == null)
      return badRequest("City Name and State Cannot be Empty");
    city = cityService.addCity(city);
    if ( city != null )
      return success(KEY_CITY, city);
    return conflict();
  }

  @ApiOperation(value = "Delete a city [Topic: theaters]", response = JsonResponse.class)
  @DeleteMapping(CITY_CITYID)
  public ResponseEntity<JsonResponse> deleteCity(@PathVariable Integer cityId) {
    if ( cityService.removeCity(cityId) )
      return success(KEY_MESSAGE, "deleted");
    return badRequest("Delete unsuccessful");
  }


}
