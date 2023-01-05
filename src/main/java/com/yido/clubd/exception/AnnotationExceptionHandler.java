package com.yido.clubd.exception;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RestController
@Slf4j
public class AnnotationExceptionHandler {

    public ResponseEntity<ErrorResponse> wrappingResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(message), status);
    }
    
    public ModelAndView ModelAndViewResponse(String message) {
    	ModelAndView mav = new ModelAndView("exception");
    	mav.addObject("message", message);
    	return mav;
    }
    
    public boolean isAjaxRequest() {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	log.debug("x-requested-with - {}", request.getHeader("x-requested-with"));
    	return request.getHeader("x-requested-with") != null ? true : false;
    }
    
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleServerError(HttpServletRequest req, Exception e) {
    	log.error("Exception - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse("잘못된 요청입니다.") ;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFound(NotFoundException e) {
    	log.error("NotFoundException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object NoHandlerFoundException(NotFoundException e) {
    	log.error("NotFoundException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMissingParam(MissingServletRequestParameterException e) {
    	log.error("MissingServletRequestParameterException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleNullPoint(NullPointerException e) {
    	log.error("NullPointerException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handleSQL(DuplicateKeyException e) {
	log.error("DuplicateKeyException - {}", e.getMessage());
	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleDuplicated(DuplicateException e) {
    	log.error("DuplicateException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleSQL(SQLException e) {
		
		if(e.getErrorCode() == 1062) {
			return handleDuplicated(new DuplicateException("중복되었습니다."));
		}
    	log.error("SQLException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleIllegalState(IllegalStateException e) {
    	log.error("IllegalStateException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleAuth(AuthException e) {
    	log.error("AuthException - {}", e.getMessage());
    	return isAjaxRequest()? wrappingResponse(e.getMessage(),HttpStatus.BAD_REQUEST) : ModelAndViewResponse(e.getMessage()) ;
    }
    
    protected ResponseEntity<ErrorResponse> errorResponse(Throwable throwable,
            HttpStatus status) {
        if (null != throwable) {
            return response(new ErrorResponse(), status);
        } else {
            return response(null, status);
        }
    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<T>(body, new HttpHeaders(), status);
    }



}
