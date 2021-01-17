package guru.springframework.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/beer")
@Slf4j
@RequiredArgsConstructor
public class BeerController {
	private final BeerService beerService;
	
	@GetMapping("")
	public ResponseEntity<List<BeerDto>> listBeers(){
		return new ResponseEntity<>(beerService.listBeers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BeerDto> getBeerById(@Min(1) @PathVariable("id") Integer id ){		
		
		return new ResponseEntity<>(beerService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("upc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@Min(1) @PathVariable("upc") String upc){		
		
		return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beer) {
		
		return new ResponseEntity<>( beerService.saveNewBeer(beer),HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") Integer beerId, @Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto),HttpStatus.OK);
    }
	
	
}
