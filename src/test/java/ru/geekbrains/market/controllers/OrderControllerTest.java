package ru.geekbrains.market.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.market.dto.OrderDto;
import ru.geekbrains.market.dto.OrderItemDto;
import ru.geekbrains.market.model.Order;
import ru.geekbrains.market.model.OrderItem;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repositories.OrderRepository;
import ru.geekbrains.market.services.OrderService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "user", authorities = "USER")
    public void getAllOrderTest() throws Exception {
        String name = "user";

        User user = new User();
        user.setUsername(name);

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Tushenka");
        product.setPrice(1200);

        OrderItem orderItem = new OrderItem(product);
        List<OrderItem> orderItems = new ArrayList<>(Arrays.asList(orderItem));

        Order order = new Order();
        order.setUser(user);
        order.setItems(orderItems);

        OrderDto orderDto = new OrderDto(order);
        List<OrderDto> orderDtoList = new ArrayList<>(Arrays.asList(orderDto));

        given(orderService.findAllUserOrderDto(name)).willReturn(orderDtoList);

        mvc.perform(get("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is(orderDtoList.get(0).getUsername())))
                .andExpect(jsonPath("$[0].price", is(orderDtoList.get(0).getPrice())))
                .andExpect(jsonPath("$[0].address", is(orderDtoList.get(0).getAddress())))
                .andExpect(jsonPath("$[0].items").isArray())
                .andExpect(jsonPath("$[0].items", hasSize(orderDtoList.get(0).getItems().size())))
                .andExpect(jsonPath("$[0].items.[0].productTitle", is(orderDtoList.get(0).getItems().get(0).getProductTitle())))
                .andExpect(jsonPath("$[0].items.[0].pricePerProduct", is(orderDtoList.get(0).getItems().get(0).getPricePerProduct())));
    }
}
