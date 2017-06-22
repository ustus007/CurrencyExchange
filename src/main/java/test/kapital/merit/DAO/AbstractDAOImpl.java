package test.kapital.merit.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import test.kapital.merit.DAO.interfaces.AbstractDAO;
import test.kapital.merit.entity.BasicEntity;

public abstract class AbstractDAOImpl<T extends BasicEntity> implements AbstractDAO<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public abstract Class<T> getEntityClass();
	

	@Override
	public T getElementById(Long id) {
		return entityManager.find(getEntityClass(), id);
	}
	

	@Override
	public List<T> getAllElements() {
		return entityManager.createQuery(
				"From " + getEntityClass().getCanonicalName()).getResultList();
	}
	

	@Override
	public void persistElements(T... elements) {
		for (T entity : elements) {
			entityManager.persist(entity);
		}

	}
	

	@Override
	public List<T> updateElements(T... elements) {
		List<T> elementsResult = new ArrayList<T>();
		for (T element : elements) {
			elementsResult.add(entityManager.merge(element));
		}
		return elementsResult;

	}
	

	@Override
	public void deleteElements(T... elements) {
		for (T element : elements) {
			entityManager.remove(element);
		}
	}
}
