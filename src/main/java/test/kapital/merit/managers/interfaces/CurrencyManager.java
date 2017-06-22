package test.kapital.merit.managers.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import test.kapital.merit.entity.Currency;

public interface CurrencyManager {

	Currency getCurrencyByName(String name);

	List<Currency> getAllCurrencies();

	Throwable createCurrency(String currencyName);

	List<BigDecimal> getCurrenciesRate(Currency from, Currency to, LocalDate start, LocalDate end);

}
