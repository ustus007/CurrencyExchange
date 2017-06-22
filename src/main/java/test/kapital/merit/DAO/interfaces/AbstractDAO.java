package test.kapital.merit.DAO.interfaces;

import java.util.List;

import test.kapital.merit.entity.BasicEntity;

public interface AbstractDAO<T extends BasicEntity> {

	public abstract Class<T> getEntityClass();

	public abstract T getElementById(Long id);

	public abstract List<T> getAllElements();

	public abstract void persistElements(@SuppressWarnings("unchecked") T... elements);

	public abstract List<T> updateElements(@SuppressWarnings("unchecked") T... elements);

	public abstract void deleteElements(@SuppressWarnings("unchecked") T... elements);

}