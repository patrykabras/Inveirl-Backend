package com.example.inveirl.admin.users.list;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class UsersListEndpointTest {

    private static final String URL = "/admin/users";

    private static final UsersListService service = mock(UsersListService.class);
    private static final Validator validator = mock(Validator.class);

    private static final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new UsersListEndpoint(service))
                                                          .setMessageConverters(new MappingJackson2HttpMessageConverter(
                                                                  Jackson2ObjectMapperBuilder.json()
                                                                                             .build()))
                                                          .setValidator(validator)
                                                          .build();

    @Test
    void shouldCallApi() throws Exception {
        // given

        // when
        final ResultActions actions = mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        final MvcResult mvcResult = actions.andReturn();
        assertEquals(HttpStatus.OK.value(),
                     mvcResult.getResponse()
                              .getStatus());
    }

    @Test
    void shouldCallApiWithParams() throws Exception {
        // given
        final String urlWithParams = URL + "?emailContains=user@user.com&usernameContains=User&roles=ADMIN";

        // when
        final ResultActions actions = mockMvc.perform(get(urlWithParams).contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        final MvcResult mvcResult = actions.andReturn();
        assertEquals(HttpStatus.OK.value(),
                     mvcResult.getResponse()
                              .getStatus());
    }
}