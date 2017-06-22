function gotCurrencies(data, textStatus) {
	if (Array.isArray(data.currencies) && data.currencies.length > 0) {
		$("#OuterContainer").append("<div id='dropListContainer' class='form-group'> </div>");
		$("#dropListContainer").append("<div><div id='Caption' class='col-md-12 text-center'><h2> Exchange </h2></div></div>");
		$("#dropListContainer").append(
				"<div id='fromCurrency' class='col-md-6 text-center bg-info'> </div>");
		$("#dropListContainer").append(
				"<div id='toCurrency' class='col-md-6 text-center bg-info'> </div>");
		$("#fromCurrency").append("From: <select id='fromCurrencyList' class='form-control'></select>");
		$("#toCurrency").append("To: <select id='toCurrencyList' class='form-control'></select>");
		for (i=0; i< data.currencies.length; i++) {
			$("#fromCurrencyList").append(
					"<option value='" + data.currencies[i] + "'>" + data.currencies[i] + "</option>");
			$("#toCurrencyList").append(
					"<option value='" + data.currencies[i] + "'>" + data.currencies[i] + "</option>");
		}
		$("#fromCurrencyList").val(data.currencies[0]);
		$("#toCurrencyList").val(data.currencies[0]);
		$("#OuterContainer").append("<div id='datesTextContainer'> </div>");
		$("#datesTextContainer").append("<div class='col-md-6 text-center bg-info p-5' style='text-align: center;'><b>Start date: </b></div>");
		$("#datesTextContainer").append("<div class='col-md-6 text-center bg-info p-5' style='text-align: center;'><b>Final date: </b></div>");
		$("#OuterContainer").append("<div id='datesContainer'> </div>");
		$("#datesContainer").append("<div class='col-md-6 text-center bg-info p-5' style='text-align: center;'>&nbsp;<div id='startDatepicker'> </div>&nbsp;</div>")
		$("#datesContainer").append("<div class='col-md-6 text-center bg-info p-5' style='text-align: center;'>&nbsp;<div id='endDatepicker'> </div>&nbsp;</div>")
		$("#startDatepicker").css("display", "inline-block");
		$("#endDatepicker").css("display", "inline-block");
		$("#startDatepicker").datepicker();
		$("#endDatepicker").datepicker();
		$("#startDatepicker").datepicker('option', 'dateFormat', 'yy-mm-dd');
		$("#endDatepicker").datepicker('option', 'dateFormat', 'yy-mm-dd');
		$("#OuterContainer").append("<div id='buttonContainer'> </div>");
		$("#buttonContainer").append("<button id='getRates' type='button' class='btn btn-primary btn-block'>Get exchange rates</button>");
		$("#OuterContainer").append("<div id='textContainer'> </div>");
		$("#getRates").on("click", getRates);
	} else {
		$("#OuterContainer").append("<div class='text-danger'>No currencies defined</div>");
	}

}

function failedToGetCurrencies() {
	$("#OuterContainer").append("<div class='text-danger'>No currencies defined</div>");
}

function getRates(){
	result = {};
	result.from = $("#fromCurrencyList").val();
	result.to = $("#toCurrencyList").val();
	result.start = $("#startDatepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();
	result.end = $("#endDatepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();
	json = JSON.stringify(result)
	$.ajax({
		type: "POST",
		url : './rates',
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		data: json,
		success : gotRates,
		//failure: ratesFailure,
		error: ratesFailure
	});
}

function gotRates(data){
	$("#textContainer").empty();
	$("#textContainer").append("<div id='resText' class='text-info'></div>")
	tb = data.start!=data.end?"For dates starting from "+data.start+" and ending at "+data.end:"For date "+data.start;
	tr = ""
	for (i=0; i< data.rates.length; i++) {
		tr = tr + data.rates[i];
		if ( i< data.rates.length - 1){
			tr = tr + ", ";
		}
	}
	$("#resText").append(tb+", exchange rate(s) for currency "+data.from+" for "+data.to+" will be "+tr);
}

function ratesFailure(jqXHR, textStatus, errorThrown){
	json = jqXHR.responseJSON;
	$("#textContainer").empty();
	$("#textContainer").append("<div id='resText' class='text-danger'></div>");
	$("#resText").append(json.message);
}

$(function() {

	$.ajax({
		url : './currency/getall',
		dataType : "json",
		success : gotCurrencies,
		failure: failedToGetCurrencies,
		error: failedToGetCurrencies
	});

})
