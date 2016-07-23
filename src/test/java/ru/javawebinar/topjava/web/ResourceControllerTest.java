package ru.javawebinar.topjava.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by buhalo on 21.07.16.
 */
public class ResourceControllerTest extends AbstractControllerTest {

    @Test
   public void StyleResourceTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/resources/css/style.css"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/css"));
    }
}
