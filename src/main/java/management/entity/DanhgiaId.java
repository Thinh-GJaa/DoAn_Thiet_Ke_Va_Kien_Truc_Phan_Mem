package management.entity;
// Generated Dec 14, 2022, 9:49:56 PM by Hibernate Tools 4.3.5.Final

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DanhgiaId generated by hbm2java
 */
@Embeddable
public class DanhgiaId implements java.io.Serializable {

	private String tentk;
	private int mamh;

	public DanhgiaId() {
	}

	
	public DanhgiaId(String tentk, int mamh) {
		super();
		this.tentk = tentk;
		this.mamh = mamh;
	}


	@Column(name = "TENTK", nullable = false, length =500)
	public String getTentk() {
		return this.tentk;
	}

	public void setTentk(String tentk) {
		this.tentk = tentk;
	}

	@Column(name = "MAMH", nullable = false)
	public int getMamh() {
		return this.mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}


	@Override
	public int hashCode() {
		return Objects.hash(mamh, tentk);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanhgiaId other = (DanhgiaId) obj;
		return mamh == other.mamh && Objects.equals(tentk, other.tentk);
	}

	

}