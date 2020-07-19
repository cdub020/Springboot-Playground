package com.galvanize.demo;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PlaygroundApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkpi() throws Exception {
		this.mockMvc.perform(get("/math/pi")).andDo(print()).andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void checkoperation() throws Exception {
		this.mockMvc.perform(get("/math/calculate?x=5&y=3")).andExpect(content().string("5 + 3 = 8"));
		this.mockMvc.perform(get("/math/calculate?operation=multiply&x=5&y=3")).andExpect(content().string("5 * 3 = 15"));
		this.mockMvc.perform(get("/math/calculate?operation=add&x=5&y=3")).andExpect(content().string("5 + 3 = 8"));
		this.mockMvc.perform(get("/math/calculate?operation=subtract&x=5&y=3")).andExpect(content().string("5 - 3 = 2"));
		this.mockMvc.perform(get("/math/calculate?operation=divide&x=30&y=5")).andExpect(content().string("30 / 5 = 6"));
	}

	@Test
	public void checkn() throws Exception {
		this.mockMvc.perform(post("/math/sum?n=5&n=4&n=3")).andExpect(content().string("5 + 4 + 3 = 12"));
	}
}
