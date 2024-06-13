package management.entity;
// Generated Dec 14, 2022, 9:49:56 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Nhacungcap generated by hbm2java
 */
@Entity
@Table(name = "nhacungcap", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL"),
		@UniqueConstraint(columnNames = "SDT") })
public class Nhacungcap implements java.io.Serializable {

	private int mancc;
	private String tenncc;
	private String diachi;
	private String email;
	private String sdt;
	private Set<Phieunhap> phieunhaps = new HashSet<Phieunhap>(0);

	public Nhacungcap() {
	}

	public Nhacungcap(int mancc, String tenncc, String diachi, String email, String sdt) {
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.email = email;
		this.sdt = sdt;
	}

	public Nhacungcap(int mancc, String tenncc, String diachi, String email, String sdt,
			Set<Phieunhap> phieunhaps) {
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.diachi = diachi;
		this.email = email;
		this.sdt = sdt;
		this.phieunhaps = phieunhaps;
	}

	@Id

	@Column(name = "MANCC", unique = true, nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMancc() {
		return this.mancc;
	}

	public void setMancc(int mancc) {
		this.mancc = mancc;
	}

	@Column(name = "TENNCC", nullable = false, columnDefinition = "nvarchar(100)")
	public String getTenncc() {
		return this.tenncc;
	}

	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}

	@Column(name = "DIACHI", nullable = false, columnDefinition = "nvarchar(100)")
	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Column(name = "EMAIL", unique = true, nullable = false, length = 500)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SDT", unique = true, nullable = false, length = 15)
	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhacungcap")
	public Set<Phieunhap> getphieunhaps() {
		return this.phieunhaps;
	}

	public void setphieunhaps(Set<Phieunhap> phieunhaps) {
		this.phieunhaps = phieunhaps;
	}

}