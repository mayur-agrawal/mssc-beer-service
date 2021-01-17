package guru.springframework.msscbeerservice.services;


import java.util.List;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;

public interface BeerService {
    BeerDto getById(Integer beerId);
    
    BeerDto getByUpc(String upc);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(Integer beerId, BeerDto beerDto);
    
    List<BeerDto> listBeers();
}