package test.kapital.merit.json;

import java.time.LocalDate;

public class RatesRequest {

	public RatesRequest(){}
	
	private String from;
	private String to;
	private LocalDate start;
	private LocalDate end;
	
	public RatesRequest(String from, String to, LocalDate start, LocalDate end) {
		super();
		this.from = from;
		this.to = to;
		this.start = start;
		this.end = end;
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
