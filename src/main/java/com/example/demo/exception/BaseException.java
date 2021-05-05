package com.example.demo.exception;

/**
 * All business exceptions should extend this exception.
 * to maintain common practices in exception handling
 */
public abstract class BaseException extends RuntimeException {
  private ErrorCode errorCode;

  public BaseException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BaseException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public BaseException(ErrorCode errorCode, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}

