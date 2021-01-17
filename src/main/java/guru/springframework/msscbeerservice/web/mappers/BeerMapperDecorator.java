package guru.springframework.msscbeerservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.proxy.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {
	
	
	private BeerInventoryService beerInventoryService;
	private BeerMapper beerMapper;
	
	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}
	@Autowired
	public void setBeerMapper(BeerMapper beerMapper) {
		this.beerMapper = beerMapper;
	}
	

	@Override
	public	BeerDto beerToBeerDtoWithInventory(Beer beer) {
		BeerDto dto = beerMapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
		return dto;
	}
	@Override
	public Beer beerDtoToBeer(BeerDto dto) {
		return beerMapper.beerDtoToBeer(dto);		
	}
	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		return beerMapper.beerToBeerDto(beer);
	}

}
