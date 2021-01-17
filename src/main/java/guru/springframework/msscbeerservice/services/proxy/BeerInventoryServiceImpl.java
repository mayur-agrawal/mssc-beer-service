package guru.springframework.msscbeerservice.services.proxy;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;

import guru.springframework.msscbeerservice.services.proxy.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeerInventoryServiceImpl implements BeerInventoryService {

	public static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Override
	public Integer getOnhandInventory(Integer beerId) {
		 log.info("Calling Inventory Service");
		 ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
	                .exchange("http://localhost:8084/inventory" + INVENTORY_PATH, HttpMethod.GET, null,
	                        new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);
		 
		  //sum from inventory list
		 
		 Integer onHand = Objects.requireNonNull(responseEntity.getBody())
				 				.stream()
				 				.mapToInt(BeerInventoryDto::getQuantityOnHand)
				 				.sum();		 
		 return onHand;
		
	}

}
