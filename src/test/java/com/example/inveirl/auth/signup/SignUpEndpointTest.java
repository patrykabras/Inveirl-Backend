package com.example.inveirl.auth.signup;

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

class SignUpEndpointTest {
    private static final String URL = "/auth/signup";

    private static final SignUpService service = mock(SignUpService.class);
    private static final Validator validator = mock(Validator.class);

    private static final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new SignUpEndpoint(service))
                                                          .setMessageConverters(new MappingJackson2HttpMessageConverter(
                                                                  Jackson2ObjectMapperBuilder.json()
                                                                                             .build()))
                                                          .setValidator(validator)
                                                          .build();

    @Test
    void shouldCallApi() throws Exception {
        // given
        final String body = """
                {
                     "email": "%s",
                     "password": "%s",
                     "username": "%s"
                }
                """.formatted("user@user.com", "U$er", "User");

        // when
        final ResultActions actions = mockMvc.perform(post(URL).content(body)
                                                               .accept(MediaType.APPLICATION_JSON_VALUE)
                                                               .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        final MvcResult mvcResult = actions.andReturn();
        assertEquals(HttpStatus.OK.value(),
                     mvcResult.getResponse()
                              .getStatus());
    }
}