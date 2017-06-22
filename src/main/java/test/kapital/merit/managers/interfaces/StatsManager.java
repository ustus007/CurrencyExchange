package test.kapital.merit.managers.interfaces;

import java.math.BigDecimal;
import java.util.List;

import test.kapital.merit.entity.Currency;

public interface StatsManager {

	void saveStats(String from, String to, String ip, List<BigDecimal> coefs);
	
	

}
