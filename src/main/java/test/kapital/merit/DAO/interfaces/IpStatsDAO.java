package test.kapital.merit.DAO.interfaces;

import java.util.List;

import test.kapital.merit.entity.IpStats;

public interface IpStatsDAO extends AbstractDAO<IpStats> {

	List<IpStats> findByIp(String ip);

}
