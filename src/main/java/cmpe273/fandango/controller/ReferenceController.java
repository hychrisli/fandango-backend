package cmpe273.fandango.controller;


import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.ReferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static cmpe273.fandango.constant.JsonConstant.KEY_CONTENT;
import static cmpe273.fandango.constant.UrlConstant.FORMAT;
import static cmpe273.fandango.constant.UrlConstant.GENRE;

@RestController
@Api(tags = {"Reference"})
@SwaggerDefinition(tags = {@Tag(name = "Reference Controller", description = "Reference Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class ReferenceController extends AbstractController {

  @Autowired
  ReferenceService referenceService;


  @ApiOperation(value = "Get All Formats", response = JsonResponse.class)
  @GetMapping(FORMAT)
  public ResponseEntity<JsonResponse> getFormats() {
    return success(KEY_CONTENT, referenceService.getAllFormats());
  }

  @ApiOperation(value = "Get All Genres", response = JsonResponse.class)
  @GetMapping(GENRE)
  public ResponseEntity<JsonResponse> getGenres() {
    return success(KEY_CONTENT, referenceService.getAllGenres());
  }

}
