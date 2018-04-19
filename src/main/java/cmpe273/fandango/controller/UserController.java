package cmpe273.fandango.controller;


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

import java.security.Principal;
import java.util.List;

import static cmpe273.fandango.constant.UrlConstant.USER;


@RestController
@Api(tags = {"User"})
@SwaggerDefinition(tags = {@Tag(name = "User Controller", description = "User Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class UserController extends AbstractController {

  @Autowired
  UserService userService;
/*
    @ApiOperation(value = "Get User", response = JsonResponse.class)
    @GetMapping(USER + "/{username}")
    @PreAuthorize(PRIV_ADMIN)
    public ResponseEntity<JsonResponse> getUser(@PathVariable String username) {
        UserDto userDto = userService.retrieveUserDto(username);
        if (userDto != null)
            return success("user", userDto);
        return notFound();
    }

    @ApiOperation(value = "Get My Username")
    @GetMapping(USER + "/self")
    public ResponseEntity<JsonResponse> getMyUserName(Principal principal) {
        return success("self", principal.getName());
    }

    @ApiOperation(value = "Add A User")
    @PostMapping(USER)
    public ResponseEntity<JsonResponse> addUser(@RequestBody UserDto userDto) {
        if (userService.createUser(userDto))
            return created("created", userDto.getUsername());

        return conflict();
    }

    @ApiOperation(value = "Delete A User")
    @PreAuthorize(PRIV_ADMIN)
    public ResponseEntity<JsonResponse> deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username))
            return success("deleted", username);

        return notFound();
    }*/

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
  @GetMapping(USER + "s")
  //@PreAuthorize(PRIV_ADMIN)
  public Page<User> getUser(Pageable pageable) {
    return userService.getAllUsers(pageable);
    /*if (users != null && !users.isEmpty())
      return success("users", users);
    return notFound();*/
  }

}
