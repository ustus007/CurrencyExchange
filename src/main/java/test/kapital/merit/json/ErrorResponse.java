package test.kapital.merit.json;

public class ErrorResponse extends JsonResponse{

	public ErrorResponse(){}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse(String message) {
		this.message = message;
	}

	private String message;
	
}
