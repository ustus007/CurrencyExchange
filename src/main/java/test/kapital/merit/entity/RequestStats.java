package test.kapital.merit.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "request_stats")
public class RequestStats implements BasicEntity {

	@Id
	@Column(name = "idrequest_stats", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRequestStats;

	@Column(name = "request_timestamp", nullable = false)
	private LocalDateTime timestamp;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "from_currency", referencedColumnName = "idcurrency", nullable = false)
	private Currency from;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "to_currency", referencedColumnName = "idcurrency", nullable = false)
	private Currency to;

	@Column(name = "ip", nullable = false)
	private String ip;

	@Lob
	@Column(name = "coefs", nullable = false)
	private String coefs;

	public Long getIdRequestStats() {
		return idRequestStats;
	}

	public void setIdRequestStats(Long idRequestStats) {
		this.idRequestStats = idRequestStats;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCoefs() {
		return coefs;
	}

	public void setCoefs(String coefs) {
		this.coefs = coefs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coefs == null) ? 0 : coefs.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((idRequestStats == null) ? 0 : idRequestStats.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		RequestStats other = (RequestStats) obj;
		if (coefs == null) {
			if (other.coefs != null)
				return false;
		} else if (!coefs.equals(other.coefs))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (idRequestStats == null) {
			if (other.idRequestStats != null)
				return false;
		} else if (!idRequestStats.equals(other.idRequestStats))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

}
