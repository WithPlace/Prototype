package com.example.withplaceprototype.controller;

import com.example.withplaceprototype.definition.RequestStatus;
import com.example.withplaceprototype.entity.MatchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
@SpringBootTest
public class MatchRequestAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(){

    }

    @BeforeAll
    public void boforeEach(){
        objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
    }

    @Test
    public void callMatchRequest() throws Exception {
        String url = "/match/request";
        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setUserId("test_" + System.currentTimeMillis());
        matchRequest.setSiteX("0");
        matchRequest.setSiteY("0");
        matchRequest.setStatus(RequestStatus.REQUESTED);
        matchRequest.setRequestDate("2021-11-01 00:00:00");

        String content = objectMapper.writeValueAsString(matchRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    System.out.println(response);
                    //log.info(">>>>> response : {} ", response.toString());
                });
    }
}