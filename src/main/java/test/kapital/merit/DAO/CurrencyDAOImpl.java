package test.kapital.merit.DAO;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import test.kapital.merit.DAO.interfaces.CurrencyDAO;
import test.kapital.merit.entity.Currency;

@Component
public class CurrencyDAOImpl extends AbstractDAOImpl<Currency> implements CurrencyDAO {

	@Override
	public Class<Currency> getEntityClass() {
		return Currency.class;
	}
	
	@Override
	public List<Currency> findByName(String currencyName) {
		
		Query query = entityManager.createNamedQuery(Currency.FIND_BY_NAME)
				.setParameter( 1, currencyName);
	
		return (List<Currency>)query.getResultList();
	}

}
