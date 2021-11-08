package com.zkdlu.outbox.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkdlu.outboxapp.order.Order;
import com.zkdlu.outboxapp.order.OrderApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderApiTest {

    private MockMvc mockMvc;
    private SpyOrderService spyOrderService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        spyOrderService = new SpyOrderService();
        OrderApi orderApi = new OrderApi(spyOrderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderApi).build();
    }

    @Test
    void createOrder_isOk() throws Exception {
        long orderId = 1L;
        mockMvc.perform(post("/order/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_callsNewOrderInOrderService() throws Exception {
        long orderId = 1L;
        mockMvc.perform(post("/order/" + orderId))
                .andExpect(status().isOk());

        assertThat(spyOrderService.newOrder_argumentOrderId).isEqualTo(orderId);
    }

    @Test
    void completeOrder_isOk() throws Exception {
        long orderId = 1L;

        mockMvc.perform(patch("/order/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    void completeOrder_callsCompleteOrderInOrderService() throws Exception {
        long orderId = 1L;

        mockMvc.perform(patch("/order/" + orderId))
                .andExpect(status().isOk());

        assertThat(spyOrderService.completeOrder_argumentOrderId).isEqualTo(orderId);
    }

    @Test
    void getOrder_isOk() throws Exception {
        long orderId = 1L;

        mockMvc.perform(get("/order/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    void getOrder_callsCompleteOrderInOrderService() throws Exception {
        long orderId = 1L;

        mockMvc.perform(get("/order/" + orderId))
                .andExpect(status().isOk());

        assertThat(spyOrderService.getOrder_argumentOrderId).isEqualTo(orderId);
    }

    @Test
    void getOrder_returnsOrder() throws Exception {
        long orderId = 1L;

        Order givenOrder = new Order(orderId);
        spyOrderService.getOrder_returnValue = givenOrder;

        mockMvc.perform(get("/order/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(givenOrder)));
    }
}