package guru.springframework.msscbeerservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbeerservice.web.controller.BeerController;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class ControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	//@Test
	void getBeerbyId() throws Exception {
		
		mockMvc.perform(get("/api/v1/beer/1")
							.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
				
	}
	//@Test
	void savebeer() throws Exception {
		
		BeerDto dto = BeerDto.builder()
							//.beerName("xyz")
							.beerStyle(BeerStyleEnum.ALE.name())				
							.build();
		String str = objectMapper.writeValueAsString(dto);
		
		mockMvc.perform(post("/api/v1/beer/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(str)
					    )
				.andExpect(status().isCreated());		
	}
	//@Test
	void updatebeer() {
		
	}
}
