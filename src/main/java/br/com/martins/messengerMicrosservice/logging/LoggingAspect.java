package br.com.martins.messengerMicrosservice.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            LOGGER.warn("HttpServletRequest is not available for logging the request.");
            return;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        Object[] args = joinPoint.getArgs();

        LOGGER.debug(
                "Incoming Request: Method: {}, Path: {}, QueryString: {}, Controller Method: {}, Arguments: {}",
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
        HttpServletRequest request = getCurrentRequest();
        HttpServletResponse response = getCurrentResponse();
        if (request == null || response == null) {
            LOGGER.warn("HttpServletRequest or HttpServletResponse is not available for logging the response.");
            return;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        LOGGER.debug(
                "Outgoing Response: Status: {}, Controller Method: {}, Path: {}, Response: {}",
                response.getStatus(),
                methodName,
                request.getRequestURI(),
                result);
    }

    @AfterThrowing(
            pointcut = "within(@org.springframework.web.bind.annotation.RestController *)",
            throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        HttpServletRequest request = getCurrentRequest();
        HttpServletResponse response = getCurrentResponse();
        if (request == null) {
            LOGGER.warn("HttpServletRequest is not available for logging the exception.");
            return;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        LOGGER.error(
                "Exception in Request: Status: {}, Controller Method: {}, Path: {}, Exception: {}",
                response != null ? response.getStatus() : "Unknown",
                methodName,
                request.getRequestURI(),
                ex.getMessage(),
                ex);
    }

    private HttpServletRequest getCurrentRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) attributes).getRequest();
        }
        return null;
    }

    private HttpServletResponse getCurrentResponse() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) attributes).getResponse();
        }
        return null;
    }
}

