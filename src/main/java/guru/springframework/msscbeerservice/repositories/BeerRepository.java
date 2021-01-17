package guru.springframework.msscbeerservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import guru.springframework.msscbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, Integer>{
	
	Beer findByUpc(String upc);
	

}
