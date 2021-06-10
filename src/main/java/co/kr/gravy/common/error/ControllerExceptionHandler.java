package co.kr.gravy.common.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.kr.gravy.common.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
  	@ResponseBody
    protected ResultDTO handleException(MethodArgumentNotValidException exception) {
	
		log.error("handleException : {}", "MethodArgumentNotValidException" );
		log.error("exception msg", exception);
	    BindingResult bindingResult = exception.getBindingResult();
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSystemId("InterFace Server");
		resultDTO.setResultCode("E");
		resultDTO.setResultMsg(getResultMessage(bindingResult));
		return resultDTO;
    }
	
	@ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    @ResponseBody
    protected ResultDTO handleException(ConstraintViolationException exception) {
		
		log.error("handleException : {}", "ConstraintViolationException" );
		log.error("exception msg", exception);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSystemId("InterFace Server");
		resultDTO.setResultCode("E");
		resultDTO.setResultMsg(getResultMessage(exception.getConstraintViolations().iterator()));
		return resultDTO;
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(GravyLabException.class) // DpsException 예외를 처리
  	@ResponseBody
    protected ResultDTO handleException(GravyLabException exception) {
		
		StringWriter errors = new StringWriter();
		exception.getException().printStackTrace(new PrintWriter(errors));
	        
		log.error("handleException : {}", "DspException" );
		log.error("exception msg", exception);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSystemId("InterFace Server");
		resultDTO.setResultCode("E");
		resultDTO.setResultMsg("처리 중 에러가 발생 했습니다. [" +errors.toString()+ "] \n" + exception.getServiceId());
		return resultDTO;
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class) // NullPointerException 예외를 처리
  	@ResponseBody
    protected ResultDTO handleException(NullPointerException exception) {
	
		log.error("handleException : {}", "NullPointerException" );
		log.error("exception msg", exception);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSystemId("InterFace Server");
		resultDTO.setResultCode("E");
		resultDTO.setResultMsg("처리 중 에러가 발생 했습니다. [NullPointerException] ");
		return resultDTO;
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class) // Exception 예외를 처리
  	@ResponseBody
    protected ResultDTO handleException(Exception exception) {
	
		log.error("handleException : {}", "Exception" );
		log.error("exception msg", exception);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSystemId("InterFace Server");
		resultDTO.setResultCode("E");
		resultDTO.setResultMsg("처리 중 에러가 발생 했습니다. [" +exception.getMessage()+ "] ");
		return resultDTO;
    }
	
	protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext() == true) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                .append("[")
                .append(getPopertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                .append("](은)는 ")
                .append(constraintViolation.getMessage()) // 유효하지 않은 값
                .append(" 입력된 값: [")
                .append(constraintViolation.getInvalidValue()) // 유효성 검사 실패 시 메시지
                .append("]");

            if (violationIterator.hasNext() == true) {
                resultMessageBuilder.append(", ");
            }
        }
        return resultMessageBuilder.toString();
	}
	
	protected String getResultMessage(BindingResult  bindingResult) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
        	resultMessageBuilder.append("[")
        		.append(getPopertyName(fieldError.getField()))
        		.append("](은)는 ")
        		.append(fieldError.getDefaultMessage())
        		.append(" 입력된 값: [")
        		.append(fieldError.getRejectedValue())
        		.append("]");
        }

        return resultMessageBuilder.toString();
    }
	
	protected String getPopertyName(String propertyPath) {
	    return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }
}
