package test.kapital.merit.entity;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@NamedQueries({ @NamedQuery(name = Currency.FIND_BY_NAME, query = Currency.FIND_BY_NAME_QUERY) })
public class Currency implements BasicEntity {
	
	public static final String FIND_BY_NAME = "Currency.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Currency u WHERE u.name = ?1";
	
	@Id
	@Column(name = "idcurrency", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurrency;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public Long getIdCurrency() {
		return idCurrency;
	}

	public void setIdCurrency(Long idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurrency == null) ? 0 : idCurrency.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Currency other = (Currency) obj;
		if (idCurrency == null) {
			if (other.idCurrency != null)
				return false;
		} else if (!idCurrency.equals(other.idCurrency))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
