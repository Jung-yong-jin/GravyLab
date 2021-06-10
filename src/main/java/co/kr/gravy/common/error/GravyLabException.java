package co.kr.gravy.common.error;

public class GravyLabException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception exception;
	private String message;
	private String serviceId;

	public GravyLabException(Exception e, String serviceId) {
        this.exception = e;
        this.serviceId = serviceId;
    }
    
    @Override
    public String getMessage() {
        return message;
    }

	public String getServiceId() {
		return serviceId;
	}
	
	public Exception getException () {
		return exception;
	}
}
