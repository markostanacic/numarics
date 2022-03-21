package com.numarics.playerservice.endpoint;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numarics.playerservice.PlayerModelResponseTest;
import com.numarics.playerservice.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PlayerServiceEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;


    @Test
    void registerPlayer() throws Exception {
        mockMvc.perform(post("/api/v1/player/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(PlayerModelResponseTest.playerModelResponse())))
               .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getPlayer() throws Exception {
        when(playerService.getPlayer(anyLong())).thenReturn(PlayerModelResponseTest.playerModelResponse());

        mockMvc.perform(get("/api/v1/player/2"))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().is2xxSuccessful())
               .andExpect(jsonPath("$.id", equalTo(1)))
               .andExpect(jsonPath("$.name", equalTo("marko")))
               .andExpect(jsonPath("$.gameId", equalTo(10)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
