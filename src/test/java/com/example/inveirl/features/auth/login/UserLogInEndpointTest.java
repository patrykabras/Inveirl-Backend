package com.example.inveirl.features.auth.login;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class UserLogInEndpointTest {

    private static final String USER_LOGIN_URL = "/auth/login";
    private static final String ADMIN_LOGIN_URL = "/auth/admin/login";

    private static final UserLogInService service = mock(UserLogInService.class);
    private static final Validator validator = mock(Validator.class);

    private static final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new UserLogInEndpoint(service))
                                                          .setMessageConverters(new MappingJackson2HttpMessageConverter(
                                                                  Jackson2ObjectMapperBuilder.json()
                                                                                             .build()))
                                                          .setValidator(validator)
                                                          .build();

    @Test
    void shouldCallApiUserLogin() throws Exception {
        // given
        final String body = """
                {
                    "email": "User",
                    "password": "U$ser"
                }
                """;

        // when
        final ResultActions actions = mockMvc.perform(post(USER_LOGIN_URL).content(body)
                                                                          .accept(MediaType.APPLICATION_JSON_VALUE)
                                                                          .contentType(MediaType.APPLICATION_JSON_VALUE));
        // then

        final MvcResult mvcResult = actions.andReturn();
        assertEquals(HttpStatus.OK.value(),
                     mvcResult.getResponse()
                              .getStatus());
    }

    @Test
    void shouldCallApiAdminLogin() throws Exception {
        // given
        final String body = """
                {
                    "email": "User",
                    "password": "U$ser"
                }
                """;

        // when
        final ResultActions actions = mockMvc.perform(post(ADMIN_LOGIN_URL).content(body)
                                                                           .accept(MediaType.APPLICATION_JSON_VALUE)
                                                                           .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        final MvcResult mvcResult = actions.andReturn();
        assertEquals(HttpStatus.OK.value(),
                     mvcResult.getResponse()
                              .getStatus());
    }

}