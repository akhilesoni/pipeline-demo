package com.test.demo.controllerTest;

import com.test.demo.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAboutPage() throws Exception {

        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(
                        "<html><body><h1>About!</h1><p>Served from RestController.</p></body></html>"
                ));
    }

    @Test
    void shouldReturnHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(
                        "<html><body><h1>Hello, World!</h1><p>Served from RestController.</p></body></html>"
                ));
    }

    @Test
    void shouldReturnContactPage() throws Exception {

        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(
                        "<html><body><h1>Contact!</h1><p>Served from RestController.</p></body></html>"
                ));
    }
}
