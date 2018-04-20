package cmpe273.fandango.controller;

import cmpe273.fandango.dto.LoginDto;
import cmpe273.fandango.dto.UserCreateDto;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.exception.AppException;
import cmpe273.fandango.exception.ErrorCode;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import static cmpe273.fandango.constant.UrlConstant.*;

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

    UserDto userDto = userService.getUser(userId);

    if (userDto != null)
      return success("user", userDto);
    return notFound();
  }

  @ApiOperation(value = "Put User", response = JsonResponse.class)
  @PutMapping(USER_ID)
  public ResponseEntity<JsonResponse> putUser(@PathVariable Integer userId, @RequestBody UserDto userDto){
    userDto = userService.updateUser(userId, userDto);
    if (userDto != null)
      return success("user", userDto);
    return notFound();
  }

  @ApiOperation(value = "Create User", response = JsonResponse.class)
  @PostMapping(USER)
  public ResponseEntity<JsonResponse> createUser(@RequestBody UserCreateDto userCreateDto){
    if ( userCreateDto.getEmail() == null || userCreateDto.getUsername() == null || userCreateDto.getPassword() == null)
      return badRequest("Required Fields needed: username, password, and email");
    if ( userService.getUserByUsername(userCreateDto.getUsername()) != null)
      return conflict();
    UserDto userDto = userService.createUser(userCreateDto);
    if (userDto != null)
      return created("user", userDto);
    return failure(ErrorCode.ERR_FAILED_TO_CREATE, "Failed to Create User");
  }

  @ApiOperation(value = "Delete User", response = JsonResponse.class)
  @DeleteMapping(USER_ID)
  public ResponseEntity<JsonResponse> DeleteUser(@PathVariable Integer userId){
    if (userService.deleteUser(userId))
      return success("user", "deleted");
    return notFound();
  }

  @ApiOperation(value = "Login User", response = JsonResponse.class)
  @PostMapping(LOGIN)
  public ResponseEntity<JsonResponse> loginUser(@RequestBody LoginDto loginDto) {
    if (loginDto.getPassword() == null || loginDto.getUsername() == null)
      return badRequest("Required Fields needed: username and password");

    UserDto userDto = userService.loginUser(loginDto);
    if (userDto != null)
      return success("user", userDto);
    return badRequest("Incorrect username and password combination");
  }

}
