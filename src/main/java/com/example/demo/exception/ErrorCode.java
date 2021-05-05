package com.example.demo.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.PAYMENT_REQUIRED;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * All business exception codes should come here.
 * At least common codes should be under this
 */
@ToString
public class ErrorCode {
  public static final ErrorCode SERVICE_NOT_FOUND = new ErrorCode("SERVICE_NOT_FOUND",
      NOT_FOUND, "Service is not yet registered");
  public static final ErrorCode CONTEXT_INITIALIZATION_FAILED = new ErrorCode("CONTEXT_INITIALIZATION_FAILED",
      HttpStatus.INTERNAL_SERVER_ERROR, "Failed while loading PlatformContext");
  public static final ErrorCode EXTERNAL_CONNECTION_ERROR = new ErrorCode("EXTERNAL_CONNECTION_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Failed while connecting to external service");
  public static final ErrorCode BAD_REQUEST_ERROR = new ErrorCode("BAD_REQUEST",
      BAD_REQUEST, "Bad Request");
  public static final ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode("INTERNAL_SERVER_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
  public static final ErrorCode NOT_FOUND_ERROR = new ErrorCode("NOT_FOUND",
      NOT_FOUND, "Not Found");
  public static final ErrorCode TREEIFICATION_ERROR = new ErrorCode("INTERNAL_SERVER_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Failed to convert Treeify with provided List");
  public static final ErrorCode EXCEL_PROCESSING_ERROR = new ErrorCode("EXCEL_PROCESSING_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process Excel File");
  public static final ErrorCode CSV_PROCESSING_ERROR = new ErrorCode("CSV_PROCESSING_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process CSV File");

  public static final ErrorCode INVALID_PARAM = new ErrorCode("INVALID_PARAM",
      BAD_REQUEST, "Invalid Param");

  public static final ErrorCode UNAUTHORIZED_ERROR = new ErrorCode("UNAUTHORIZED_ERROR",
      UNAUTHORIZED, "Unauthorized request, needed Authentication to access the requested resource.");

  public static final ErrorCode PROCESSING_ERROR = new ErrorCode("PROCESSING_ERROR",
      HttpStatus.INTERNAL_SERVER_ERROR, "Something ");

  // user setup not done.
  public static final ErrorCode USER_SETUP_INCOMPLETE = new ErrorCode("USER_SETUP_INCOMPLETE",
      PRECONDITION_FAILED, "User setup is incomplete");

  public static final ErrorCode USER_BLOCKED = new ErrorCode("USER_BLOCKED",
      PAYMENT_REQUIRED, "User is blocked, Please contact administrator");

  public static final ErrorCode PRE_CONDITION_FAILED = new ErrorCode("PRE_CONDITION_FAILED",
      PRECONDITION_FAILED, "Given details not enough to process");

  public static final ErrorCode METHOD_NOT_IMPLEMENTED = new ErrorCode("METHOD_NOT_IMPLEMENTED",
      NOT_IMPLEMENTED, "Accessed method is not implemented.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String description;

  public ErrorCode(final String code, final HttpStatus httpStatus, final String description) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getDescription() {
    return description;
  }
}
