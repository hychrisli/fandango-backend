package cmpe273.fandango.controller;

import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import static cmpe273.fandango.constant.UrlConstant.USER;
import static cmpe273.fandango.constant.UrlConstant.USERS;

@RestController
@Api(tags = {"User"})
@SwaggerDefinition(tags = {@Tag(name = "User Controller", description = "User Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class UserController extends AbstractController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "Get All Users", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(USERS)
  public Page<UserSimpleDto> getUser(Pageable pageable) {
    return userService.getAllUsers(pageable);
  }


  @ApiOperation(value = "Get User", response = JsonResponse.class)
  @GetMapping(USER + "/{userId}")
  public ResponseEntity<JsonResponse> getUser(@PathVariable Integer userId) {

    User user = userService.getUser(userId);

    //UserDto userDto = userService.retrieveUserDto(username);
    if (user != null)
      return success("user", user);
    return notFound();
  }




}
