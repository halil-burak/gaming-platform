package com.turkcell.playcell.gamingplatform.api.util;

import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.turkcell.playcell.gamingplatform.api.exception.BaseRuntimeException;
import com.turkcell.playcell.gamingplatform.api.exception.error.ErrorCode;
import com.turkcell.playcell.gamingplatform.api.exception.error.imp.ApiErrorCode;
import com.turkcell.playcell.gamingplatform.api.exception.impl.MobileConnectValidationException;
import com.turkcell.playcell.gamingplatform.api.response.ApiBaseErrorResponse;
import com.turkcell.playcell.gamingplatform.api.response.MetaData;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler {
	
	private static final String NO_MESSAGE_AVAILABLE = "No message available";
	private final MessageSource apiErrorMessageSource;

	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiBaseErrorResponse> handleException(Exception exception, HttpServletRequest httpServletRequest) {
		log.error("Exception occured: request-uri:[{}], class: [{}], msg: [{}] \n{}", httpServletRequest.getRequestURI(), exception.getClass().getName(), exception.getMessage(), StackTraceUtils.extractTurkcellStackTrace(exception));
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getApiBaseErrorResponse(exception));
	}

	@ExceptionHandler(AccessDeniedException.class)
	ResponseEntity<ApiBaseErrorResponse> handleAccessDeniedException(Exception exception, Locale locale, HttpServletRequest httpServletRequest) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getApiBaseErrorResponse(exception));
	}

	@ExceptionHandler(BaseRuntimeException.class)
	ResponseEntity<ApiBaseErrorResponse> handleServiceExceptions(BaseRuntimeException exception, Locale locale, HttpServletRequest httpServletRequest) {
		ErrorCode errorCode = exception.getErrorCode();

		return ResponseEntity.status(errorCode.httpStatus()).body(getApiBaseErrorResponse(exception));
	}

	private ApiBaseErrorResponse getApiBaseErrorResponse(Exception ex) {
		
		Optional<ErrorCode> maybeErrorCode = Optional.empty();
		Optional<Object[]> maybeParams = Optional.empty();
		Optional<String> maybeMessage = Optional.empty();

		if (ex instanceof MobileConnectValidationException) {
			maybeErrorCode = Optional.of(ApiErrorCode.MOBILE_CONNECT_VALIDATION_ERROR);
		} else if (ex instanceof BaseRuntimeException) {
			maybeErrorCode = Optional.ofNullable(((BaseRuntimeException) ex).getErrorCode());
			maybeParams = Optional.ofNullable(((BaseRuntimeException) ex).getParams());
		} else {
			maybeErrorCode = Optional.of(ApiErrorCode.INTERNAL_ERROR);
			log.error("Api Error occurred. {}", ExceptionUtils.getRootCauseMessage(ex));
		}

		ApiBaseErrorResponse.ApiBaseErrorResponseBuilder baseBuilder = ApiBaseErrorResponse.builder();

		MetaData.MetaDataBuilder errorResponseBuilder = MetaData.builder();
		errorResponseBuilder.message(NO_MESSAGE_AVAILABLE);

		if (maybeErrorCode.isPresent()) {
			maybeMessage = Optional.ofNullable(apiErrorMessageSource.getMessage(maybeErrorCode.get().code().toString(), maybeParams.isPresent() ? maybeParams.get() : null, LocaleContextHolder.getLocale()));
			errorResponseBuilder.code(maybeErrorCode.get().code());
		}
		
		if (maybeMessage.isPresent())
			errorResponseBuilder.message(maybeMessage.get());

		baseBuilder.meta(errorResponseBuilder.build());

		return baseBuilder.build();
	}
}
