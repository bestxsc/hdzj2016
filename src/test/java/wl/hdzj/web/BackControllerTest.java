package wl.hdzj.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wl.hdzj.dao.NewsRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BackControllerTest {
    @Autowired NewsRepository newsRepository;
    @Autowired WebApplicationContext webApplicationContext;
    MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc =  MockMvcBuilders.standaloneSetup(BackController.class).build();
    }

    @Test
    public void addnews() throws Exception {
        //登陆
        mvc.perform(post("/login")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("username", "dsf")
                .param("password", "123"))
                .andExpect(status().isOk());
    }
}