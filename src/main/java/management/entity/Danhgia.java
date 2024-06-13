package management.entity;
// Generated Dec 14, 2022, 9:49:56 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Danhgia generated by hbm2java
 */
@Entity
@Table(name = "danhgia")
public class Danhgia implements java.io.Serializable {

	private DanhgiaId id;
	private Mathang mathang;
	private Taikhoan taikhoan;
	private int danhgia;

	public Danhgia() {
	}

	public Danhgia(DanhgiaId id, Mathang mathang, Taikhoan taikhoan, int danhgia) {
		this.id = id;
		this.mathang = mathang;
		this.taikhoan = taikhoan;
		this.danhgia = danhgia;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "tentk", column = @Column(name = "TENTK", nullable = false, length = 255)),
			@AttributeOverride(name = "mamh", column = @Column(name = "MAMH", nullable = false)) })
	public DanhgiaId getId() {
		return this.id;
	}

	public void setId(DanhgiaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAMH", nullable = false, insertable = false, updatable = false)
	public Mathang getMathang() {
		return this.mathang;
	}

	public void setMathang(Mathang mathang) {
		this.mathang = mathang;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TENTK", nullable = false, insertable = false, updatable = false)
	public Taikhoan getTaikhoan() {
		return this.taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}

	@Column(name = "DANHGIA", nullable = false)
	public int getDanhgia() {
		return this.danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}

}
