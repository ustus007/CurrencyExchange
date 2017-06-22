package test.kapital.merit.DAO;

import org.springframework.stereotype.Component;

import test.kapital.merit.DAO.interfaces.RequestStatsDAO;
import test.kapital.merit.entity.RequestStats;

@Component
public class RequestStatsDAOImpl extends AbstractDAOImpl<RequestStats> implements RequestStatsDAO{

	@Override
	public Class<RequestStats> getEntityClass() {
		return RequestStats.class;
	}

}
