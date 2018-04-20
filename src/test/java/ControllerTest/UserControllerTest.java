package ControllerTest;

import cmpe273.fandango.FandangoApp;
import cmpe273.fandango.dao.UserDao;

import cmpe273.fandango.entity.User;
import cmpe273.fandango.response.JsonResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static cmpe273.fandango.constant.UrlConstant.USER;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FandangoApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@Transactional
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data-test.sql")
public class UserControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserDao userDao;

  @Test
  public void userNotFound() throws Exception {
    ResponseEntity<JsonResponse> response =
        restTemplate.getForEntity(USER + "/1", JsonResponse.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    //mockMvc.perform(get(USER + "/1")).andExpect(status().isNotFound());
  }

/*  @Test
  public void userFound() throws Exception {
    MvcResult result = mockMvc.perform(get(USER + "/1"))
        .andExpect(status().isOk())
        .andReturn();

    System.out.println(result.getResponse());
  }*/

}
