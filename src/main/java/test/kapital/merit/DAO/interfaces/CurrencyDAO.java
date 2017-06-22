package test.kapital.merit.DAO.interfaces;

import java.util.List;

import test.kapital.merit.entity.Currency;

public interface CurrencyDAO extends AbstractDAO<Currency> {

	List<Currency> findByName(String currencyName);

}
