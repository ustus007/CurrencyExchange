package test.kapital.merit.json;

public class CreateCurrencyRequest {

	public CreateCurrencyRequest(){}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateCurrencyRequest(String name) {
		this.name = name;
	}
	
}
