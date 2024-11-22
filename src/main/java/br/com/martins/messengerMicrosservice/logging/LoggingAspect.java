package br.com.martins.messengerMicrosservice.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Autowired private HttpServletRequest request;
    @Autowired private HttpServletResponse response;

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logRequest(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        Object[] args = joinPoint.getArgs();

        LOGGER.debug(
                "Incoming Request: Method: {}, Path: {}, QueryString: {}, Method: {}, Arguments: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                methodName,
                Arrays.toString(args));
    }

    @AfterReturning(
            pointcut = "within(@org.springframework.web.bind.annotation.RestController *)",
            returning = "result")
    public void logResponse(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        LOGGER.debug(
                "Outgoing Response: Status: {}, Method: {}, Path: {}, Response: {}",
                response.getStatus(),
                methodName,
                request.getRequestURI(),
                result);
    }

    @AfterThrowing(
            pointcut = "within(@org.springframework.web.bind.annotation.RestController *)",
            throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        LOGGER.error(
                "Exception in Request: Status: {}, Method: {}, Path: {}, Exception: {}",
                response.getStatus(),
                methodName,
                request.getRequestURI(),
                ex.getMessage(),
                ex);
    }
}
