package guru.springframework.msscbeerservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
	
	private  final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	
	@Override
	public BeerDto getById(Integer beerId) {
		
		Optional<Beer> opt = beerRepository.findById(beerId);
		Beer beer = opt.get();
		return beerMapper.beerToBeerDto(beer);
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {		
		Beer beer = beerMapper.beerDtoToBeer(beerDto);
		beer.setId(null);
		return beerMapper.beerToBeerDto(beerRepository.save(beer));		
	}

	@Override
	public BeerDto updateBeer(Integer beerId, BeerDto beerDto) {
		//Beer beer = beerRepository.findById(beerId).orElseThrow(RuntimeException::new);
		Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new RuntimeException("beer with id not found"));
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public List<BeerDto> listBeers() {
		ArrayList<BeerDto> beers = new ArrayList<BeerDto>();		
		beerRepository.findAll().forEach(b -> beers.add(beerMapper.beerToBeerDtoWithInventory(b)));
		return beers;					
	}

	@Override
	public BeerDto getByUpc(String upc) {
		return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
	}

}
