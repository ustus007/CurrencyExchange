package test.kapital.merit.json;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RatesResponse extends JsonResponse {

	public RatesResponse(){}
	
	private List<BigDecimal> rates;
	private String from;
	private String to;
	private LocalDate start;
	private LocalDate end;
	
	public RatesResponse(List<BigDecimal> rates, String from, String to, LocalDate start, LocalDate end) {
		super();
		this.rates = rates;
		this.from = from;
		this.to = to;
		this.start = start;
		this.end = end;
	}

	public List<BigDecimal> getRates() {
		return rates;
	}

	public void setRates(List<BigDecimal> rates) {
		this.rates = rates;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}
	

	
	
}
