package com.ustc.qingpark.webmanage.global;

import com.ustc.qingpark.webmanage.exception.ManageException;
import com.ustc.qingpark.webmanage.util.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        log.warn(e.getMessage(), e);
        //return super.handleExceptionInternal(e, body, headers, status, request);
        return new ResponseEntity<>(Results.fail(ErrorCode.STATUS_50X,null ), HttpStatus.OK);
    }

    /**
     * 处理自定义ServerException
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleManageException(ManageException e){
        log.warn(e.getSnapshot(),e);
        return new ResponseEntity<>(Results.fail(e.getCode(), e.getMessage(),null), HttpStatus.OK);
    }

    /**
     * 处理RunTimeException
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        //  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        //         HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(Results.fail(ErrorCode.STATUS_50X,null ), HttpStatus.OK);
    }

    /**
     * 处理异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        //        HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(Results.fail(ErrorCode.STATUS_50X,null) , HttpStatus.OK);
    }

}
