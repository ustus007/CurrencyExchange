package test.kapital.merit.DAO;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import test.kapital.merit.DAO.interfaces.IpStatsDAO;
import test.kapital.merit.entity.Currency;
import test.kapital.merit.entity.IpStats;

@Component
public class IpStatsDAOImpl extends AbstractDAOImpl<IpStats> implements IpStatsDAO {

	@Override
	public Class<IpStats> getEntityClass() {
		return IpStats.class;
	}

	
	@Override
	public List<IpStats> findByIp(String ip) {
		
		Query query = entityManager.createNamedQuery(IpStats.FIND_BY_IP)
				.setParameter( 1, ip);
	
		return (List<IpStats>)query.getResultList();
	}
	
}
