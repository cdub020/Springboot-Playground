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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PlaygroundApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkpi() throws Exception {
		this.mockMvc.perform(get("/math/pi")).andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void checkjson() throws Exception{
		this.mockMvc.perform(get("/flights/flight"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"Departs\":\"2017-04-21 14:34\",\"Tickets\":[{\"Passenger\":{\"FirstName\":\"Some name\",\"LastName\":\"Some other name\"},\"Price\":200}]}"));
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

	@Test
	public void volume() throws Exception {
		this.mockMvc.perform(get("/math/volume/2/2/2"))
					.andExpect(content().string("The volume of a 2x2x2 rectangle is 8"))
					.andExpect(status().isOk())
					.andReturn();
	}

	@Test
	public void area() throws Exception {

		this.mockMvc.perform(post("/math/area")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"type\" : \"circle\", \"radius\" : \"4\"}"))
						.andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
		this.mockMvc.perform(post("/math/area")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"type\" : \"rectangle\", \"width\" : \"4\", \"height\" : \"7\"}"))
				.andExpect(content().string("Area of a 4x7 rectangle is 28"));
		this.mockMvc.perform(post("/math/area")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"type\" : \"rectangle\", \"height\" : \"7\"}"))
				.andExpect(content().string("Invalid"));
		this.mockMvc.perform(post("/math/area")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"type\" : \"circle\"}"))
				.andExpect(content().string("Invalid"));
	}
}
