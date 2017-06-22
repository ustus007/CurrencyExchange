package test.kapital.merit.json;

public class CreateCurrencyResponse extends JsonResponse{
		
	public CreateCurrencyResponse(){}
	
	private Boolean success;
	
	private String message;

	public CreateCurrencyResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
