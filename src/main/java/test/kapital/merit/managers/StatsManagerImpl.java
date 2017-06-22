package test.kapital.merit.managers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.kapital.merit.DAO.interfaces.IpStatsDAO;
import test.kapital.merit.DAO.interfaces.RequestStatsDAO;
import test.kapital.merit.entity.Currency;
import test.kapital.merit.entity.IpStats;
import test.kapital.merit.entity.RequestStats;
import test.kapital.merit.json.ErrorResponse;
import test.kapital.merit.managers.interfaces.CurrencyManager;
import test.kapital.merit.managers.interfaces.StatsManager;

@Service
public class StatsManagerImpl implements StatsManager{
	
	@Autowired
	private RequestStatsDAO reqsDAO;
	
	@Autowired
	private IpStatsDAO ipsDAO;
	
	@Autowired
	private CurrencyManager currencyManager;
	
	@Override
	@Transactional (rollbackFor=IllegalArgumentException.class, noRollbackFor=IllegalArgumentException.class)
	public void saveStats(String from, String to, String ip, List<BigDecimal> coefs){
		StringJoiner join = new StringJoiner(", ");
		for (BigDecimal e: coefs){
			join.add(e.setScale(7, RoundingMode.HALF_EVEN).toString());
		}
		
		Currency from_ = currencyManager.getCurrencyByName(from);
		if (from_ == null) {
			throw new IllegalArgumentException("Currency " + from + " not found");
		}

		Currency to_ = currencyManager.getCurrencyByName(to);
		if (to_ == null) {
			throw new IllegalArgumentException("Currency " + to + " not found");
		}
		
		RequestStats s1 = new RequestStats();
		s1.setCoefs(join.toString());
		s1.setFrom(from_);
		s1.setTo(to_);
		s1.setIp(ip);
		s1.setTimestamp(LocalDateTime.now());
		reqsDAO.persistElements(s1);
		List<IpStats> s2 = ipsDAO.findByIp(ip);
		if (s2.size() > 0){
			s2.get(0).setRequestCount(s2.get(0).getRequestCount() + 1);
			ipsDAO.updateElements(s2.get(0));
		} else {
			IpStats s3 = new IpStats();
			s3.setIp(ip);
			s3.setRequestCount(1L);
			ipsDAO.persistElements(s3);
		}
		
	}


}
