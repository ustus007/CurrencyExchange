package test.kapital.merit.managers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.kapital.merit.DAO.interfaces.CurrencyDAO;
import test.kapital.merit.entity.Currency;
import test.kapital.merit.managers.interfaces.CurrencyManager;

@Service
public class CurrencyManagerImpl implements CurrencyManager {

	@Autowired
	private CurrencyDAO currencyDAO;

	@Override
	public Currency getCurrencyByName(String name) {
		List<Currency> result = currencyDAO.findByName(name);
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<Currency> getAllCurrencies() {
		return currencyDAO.getAllElements();
	}

	@Override
	@Transactional 
	public Throwable createCurrency(String currencyName) {
		Currency cur0 = getCurrencyByName(currencyName);
		if (cur0 != null){
			return new IllegalArgumentException("Currency with name "+currencyName+" already exists.");
		}
		Currency cur = new Currency();
		cur.setName(currencyName);
		try {
			currencyDAO.persistElements(cur);
			return null;
		} catch (Throwable e) {
			return e;
		}
	}

	private BigDecimal getRateForPair(Currency from, Currency to) {
		if (from.equals(to)) {
			return new BigDecimal(1);
		} else {
			Random randomGenerator = new Random();
			Double rate = (randomGenerator.nextDouble()) > (0.5) ? randomGenerator.nextDouble() * 100
					: 1 / (randomGenerator.nextDouble() * 100);
			return BigDecimal.valueOf(rate).setScale(7, RoundingMode.HALF_EVEN);
		}
	}

	@Override
	@Transactional(rollbackFor = IllegalArgumentException.class, noRollbackFor = IllegalArgumentException.class)
	public List<BigDecimal> getCurrenciesRate(Currency from, Currency to, LocalDate start, LocalDate end) {
		if (end.isBefore(start)) {
			throw new IllegalArgumentException("Start date should be before final one");
		}
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
			result.add(getRateForPair(from, to));
		}
		return result;

	}

}
