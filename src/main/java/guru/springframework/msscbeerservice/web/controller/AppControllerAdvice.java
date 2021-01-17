package guru.springframework.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> handleValidationError(ConstraintViolationException cvex)
	{
		System.out.println("entered ConstraintViolationException ");
		
		List<String> errors = new ArrayList<String>(cvex.getConstraintViolations().size());
		cvex.getConstraintViolations().forEach(
							v -> errors.add(v.getPropertyPath() + " : " + v.getMessage())				
				);		
		return new ResponseEntity<List>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List> handleValidationError1(MethodArgumentNotValidException ex)
	{
		List<String> result = new ArrayList<String>(ex.getBindingResult().getErrorCount());
		System.out.println("entered MethodArgumentNotValidException ");
		
//		ex.getBindingResult()
//		.getAllErrors()
//		.stream()
//		.filter(objectError -> objectError.getClass().isInstance(FieldError.class))
//		.map(objectError -> FieldError.class.cast(objectError))
//		.forEach(fieldError -> result.add(fieldError.getField() + ":" + fieldError.getDefaultMessage()));
//		
//		
		
		//System.out.println("result is " + result);
		//.forEach( fieldError -> result.add(fieldError.getField + " : " + fieldError.getDefaultMessage()) );
				
			
		
			ex.getBindingResult().getAllErrors().forEach(
							e -> result.add(e.getObjectName() + " : " + e.getDefaultMessage())				
				);
		
		return new ResponseEntity<List>(result,HttpStatus.BAD_REQUEST);
		
	}
	
	
//	e.getBindingResult().getAllErrors()
//    .stream()
//    .filter(FieldError.class::isInstance)
//    .map(FieldError.class::cast)
//    .forEach(fieldError -> results.addResult(fieldError.getField(), fieldError.getDefaultMessage()));

	

//	@ExceptionHandler(BindException.class)
//	public ResponseEntity<List> handleValidationError2(BindException ex)
//	{
//		System.out.println("entered BindException ");
//		List<String> errors = new ArrayList<String>(ex.getBindingResult().getErrorCount());
//		ex.getBindingResult().getAllErrors().forEach(
//							e -> errors.add(e.getArguments() + " : " + e.getDefaultMessage())				
//				);
//		
//		return new ResponseEntity<List>(errors,HttpStatus.BAD_REQUEST);
//		
//	}
}
