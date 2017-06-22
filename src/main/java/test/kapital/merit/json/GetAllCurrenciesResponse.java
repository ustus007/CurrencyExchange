package test.kapital.merit.json;

import java.util.List;

public class GetAllCurrenciesResponse extends JsonResponse{

	public GetAllCurrenciesResponse(){}
	
	private List<String> currencies;

	public GetAllCurrenciesResponse(List<String> currencies) {
		super();
		this.currencies = currencies;
	}

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}
	
	
	
}
