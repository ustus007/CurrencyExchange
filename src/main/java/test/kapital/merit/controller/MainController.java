package test.kapital.merit.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import test.kapital.merit.entity.Currency;
import test.kapital.merit.json.CreateCurrencyRequest;
import test.kapital.merit.json.CreateCurrencyResponse;
import test.kapital.merit.json.ErrorResponse;
import test.kapital.merit.json.GetAllCurrenciesResponse;
import test.kapital.merit.json.JsonResponse;
import test.kapital.merit.json.RatesRequest;
import test.kapital.merit.json.RatesResponse;
import test.kapital.merit.managers.interfaces.CurrencyManager;
import test.kapital.merit.managers.interfaces.StatsManager;

@Controller
public class MainController {

	@Autowired
	private CurrencyManager currencyManager;

	@Autowired
	private StatsManager statsManager;

	@RequestMapping(value = "/currency/create", method = RequestMethod.PUT)
	public @ResponseBody CreateCurrencyResponse createCurrency(@RequestBody CreateCurrencyRequest request, HttpServletResponse response) {
		Throwable e = currencyManager.createCurrency(request.getName());
		if (e == null) {
			return new CreateCurrencyResponse(true, "Currency " + request.getName() + " was succesfully created");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new CreateCurrencyResponse(false, "Could not create currency " + request.getName()+". Reason:"+e.getMessage());
		}
	}

	@RequestMapping(value = "/currency/getall", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getCurrencies(HttpServletResponse response) {
		try {
			List<String> result = currencyManager.getAllCurrencies().stream().map(x -> x.getName())
					.collect(Collectors.toList());
			return new GetAllCurrenciesResponse(result);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ErrorResponse("Could not get currencies due to " + e.getMessage());
		}
	}

	@RequestMapping(value = "/rates", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getRates(@RequestBody RatesRequest requestBody, HttpServletRequest request, HttpServletResponse response) {
		try {
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null || "".equals(ip)) {
				ip = request.getRemoteAddr();
			}
			Currency from = currencyManager.getCurrencyByName(requestBody.getFrom());
			if (from == null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return new ErrorResponse("Currency " + requestBody.getFrom() + " not found");
			}

			Currency to = currencyManager.getCurrencyByName(requestBody.getTo());
			if (to == null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return new ErrorResponse("Currency " + requestBody.getTo() + " not found");
			}

			List<BigDecimal> result = currencyManager.getCurrenciesRate(from, to, requestBody.getStart(),
					requestBody.getEnd());

			statsManager.saveStats(requestBody.getFrom(), requestBody.getTo(), ip, result);

			return new RatesResponse(result, from.getName(), to.getName(), requestBody.getStart(), requestBody.getEnd());
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ErrorResponse("Could not get exchange rates due to " + e.getMessage());
		}

	}

}
