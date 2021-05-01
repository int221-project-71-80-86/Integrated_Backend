package int221.project.exceptions;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import int221.project.files.ResponseMessage;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(DataRelatedException.class)
	 public ResponseEntity<Object> exceptionsHandle(ExceptionResponse ex, HttpRequest req){
		 
		 ExceptionResponse response = new ExceptionResponse(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now());
		 ResponseEntity<Object> entity = new ResponseEntity<Object>(response , HttpStatus.NOT_FOUND);
		 
		 return entity;
		 
	 }
	 
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	  }
}
