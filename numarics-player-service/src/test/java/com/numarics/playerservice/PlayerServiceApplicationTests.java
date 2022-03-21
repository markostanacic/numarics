package com.numarics.playerservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.numarics.playerservice.endpoint.PlayerServiceEndpoint;
import com.numarics.playerservice.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PlayerServiceApplicationTests {

    @Autowired
    private PlayerServiceEndpoint playerServiceEndpoint;

    @Autowired
    private PlayerService playerService;

    @Test
    void contextLoads() {
        assertThat(playerServiceEndpoint).isNotNull();
        assertThat(playerService).isNotNull();
    }

}
