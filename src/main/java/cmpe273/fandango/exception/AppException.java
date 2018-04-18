package cmpe273.fandango.exception;

public class AppException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6421943159463138912L;
    private ErrorCode errorCode;
    private String msg;

    public AppException(ErrorCode errorCode, Exception e) {
	this.errorCode = errorCode;
	this.msg = e.getMessage();
	this.setStackTrace(e.getStackTrace());
    }

    public AppException(ErrorCode errorCode, String msg) {
	this.errorCode = errorCode;
	this.msg = msg;
    }

    @Override
    public String getMessage() {
	return this.msg;
    }

    public ErrorCode getErrorCode() {
	return this.errorCode;
    }

}
