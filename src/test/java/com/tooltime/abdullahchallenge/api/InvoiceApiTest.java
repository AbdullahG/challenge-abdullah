package com.tooltime.abdullahchallenge.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tooltime.abdullahchallenge.AbdullahChallengeApplication;
import com.tooltime.abdullahchallenge.dto.CreateInvoiceDto;
import com.tooltime.abdullahchallenge.dto.InvoicePositionDto;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AbdullahChallengeApplication.class})
@WebAppConfiguration
public class InvoiceApiTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void givenWac_whenServletContext_thenItProvidesGreetController() {
    ServletContext servletContext = webApplicationContext.getServletContext();

    Assertions.assertThat(servletContext).isNotNull();
    Assertions.assertThat(servletContext instanceof MockServletContext).isTrue();
    Assertions.assertThat(webApplicationContext.getBean("invoiceApi")).isNotNull();
  }

  @Test
  public void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
    List<InvoicePositionDto> positionDtos = new ArrayList<>();
    positionDtos.add(new InvoicePositionDto("position-description-1", 55.5f));
    positionDtos.add(new InvoicePositionDto("position-description-2", 77.5f));
    ObjectMapper objectMapper = new ObjectMapper();
    CreateInvoiceDto createInvoiceDto = new CreateInvoiceDto("invoice-title", "invoice-description",
        1L, positionDtos);
    MvcResult mvcResult = this.mockMvc.perform(post("/api/invoice").content(
            objectMapper.writeValueAsString(createInvoiceDto)
        ).contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.code").isNotEmpty())
        .andExpect(jsonPath("$.title").value(createInvoiceDto.getTitle()))
        .andExpect(jsonPath("$.description").value(createInvoiceDto.getDescription()))
        .andExpect(jsonPath("$.issuedAt").isString())
        .andExpect(jsonPath("$.customer.id").isNotEmpty())
        .andExpect(jsonPath("$.positions").isArray())
        .andExpect(jsonPath("$.totalAmount").isNotEmpty())
        .andReturn();

    Assertions.assertThat("application/json")
        .isEqualTo(mvcResult.getResponse().getContentType());
  }
}
