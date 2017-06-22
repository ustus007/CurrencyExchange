package test.kapital.merit.entity;

import javax.persistence.*;

@Entity
@Table(name = "ip_stats")
@NamedQueries({ @NamedQuery(name = IpStats.FIND_BY_IP, query = IpStats.FIND_BY_IP_QUERY) })
public class IpStats  implements BasicEntity {

	
	public static final String FIND_BY_IP = "IpStats.findByIp";
	public static final String FIND_BY_IP_QUERY = "SELECT u FROM IpStats u WHERE u.ip = ?1";
	
	@Id
	@Column(name = "idip_stats", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIpStats;
	
	@Column(name = "ip", nullable = false)
	private String ip;
	
	@Column(name = "request_count", nullable = false)
	private Long requestCount;

	public Long getIdIpStats() {
		return idIpStats;
	}

	public void setIdIpStats(Long idIpStats) {
		this.idIpStats = idIpStats;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(Long requestCount) {
		this.requestCount = requestCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idIpStats == null) ? 0 : idIpStats.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((requestCount == null) ? 0 : requestCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpStats other = (IpStats) obj;
		if (idIpStats == null) {
			if (other.idIpStats != null)
				return false;
		} else if (!idIpStats.equals(other.idIpStats))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (requestCount == null) {
			if (other.requestCount != null)
				return false;
		} else if (!requestCount.equals(other.requestCount))
			return false;
		return true;
	}
	
}
