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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mathang generated by hbm2java
 */
@Entity
@Table(name = "mathang")
public class Mathang implements java.io.Serializable {

	

	private int mamh;

	private Chatlieu chatlieu;
	
	private Loaimh loaimh;
	private Nhanhieu nhanhieu;
	private String tenmh;
	private String mota;
	private int trangthai;
	private String nhan;
	private String cachlam;
	
	
	private Set<Hinhanhmh> hinhanhmhs = new HashSet<Hinhanhmh>(0);
	
	private Set<Banggia> banggias = new HashSet<Banggia>(0);

	private Set<Ctdkm> ctdkms = new HashSet<Ctdkm>(0);
	
//	private Set<Ctpd> ctpds = new HashSet<Ctpd>(0);
	
	private Set<Ctsize> ctsizes = new HashSet<Ctsize>(0);
	
	private Set<Danhgia> danhgias = new HashSet<Danhgia>(0);
   
	public Mathang() {
	}
   

	
	public Mathang(int mamh, Chatlieu chatlieu, Loaimh loaimh, Nhanhieu nhanhieu, String tenmh, String mota,
			int trangthai, Set<Hinhanhmh> hinhanhmhs, Set<Banggia> banggias, Set<Ctdkm> ctdkms, Set<Ctsize> ctsizes,
			Set<Danhgia> danhgias) {
		super();
		this.mamh = mamh;
		this.chatlieu = chatlieu;
		this.loaimh = loaimh;
		this.nhanhieu = nhanhieu;
		this.tenmh = tenmh;
		this.mota = mota;
		this.trangthai = trangthai;
		this.hinhanhmhs = hinhanhmhs;
		this.banggias = banggias;
		this.ctdkms = ctdkms;
		this.ctsizes = ctsizes;
		this.danhgias = danhgias;
	}



	@Id
	
	@Column(name = "MAMH", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMamh() {
		return this.mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MACL")
	public Chatlieu getChatlieu() {
		return this.chatlieu;
	}

	public void setChatlieu(Chatlieu chatlieu) {
		this.chatlieu = chatlieu;
	}

	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MALOAIMH")
	public Loaimh getLoaimh() {
		return this.loaimh;
	}

	public void setLoaimh(Loaimh loaimh) {
		this.loaimh = loaimh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANH", nullable = true)
	public Nhanhieu getNhanhieu() {
		return this.nhanhieu;
	}

	public void setNhanhieu(Nhanhieu nhanhieu) {
		this.nhanhieu = nhanhieu;
	}

	@Column(name = "TENMH", nullable = false, columnDefinition = "nvarchar(255)")
	public String getTenmh() {
		return this.tenmh;
	}
	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
	}
	
	@Column(name = "NHAN", nullable = true)
	public String getNhan() {
		return nhan;
	}

	public void setNhan(String nhan) {
		this.nhan = nhan;
	}
	
	
	@Column(name = "CACHLAM", nullable = true)
	public String getCachlam() {
		return cachlam;
	}

	public void setCachlam(String cachlam) {
		this.cachlam = cachlam;
	}

	
	
	@Column(name = "MOTA", nullable = true, columnDefinition = "nvarchar(255)")
	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@Column(name = "TRANGTHAI", nullable = true)
	public int getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mathang")
	public Set<Hinhanhmh> getHinhanhmhs() {
		return this.hinhanhmhs;
	}

	public void setHinhanhmhs(Set<Hinhanhmh> hinhanhmhs) {
		this.hinhanhmhs = hinhanhmhs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mathang")
	public Set<Banggia> getBanggias() {
		return this.banggias;
	}

	public void setBanggias(Set<Banggia> banggias) {
		this.banggias = banggias;
	}


	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mathang")
	
	public Set<Ctsize> getCtsizes() {
		return ctsizes;
	}

	public void setCtsizes(Set<Ctsize> ctsizes) {
		this.ctsizes = ctsizes;
	}

  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mathang")
	public Set<Ctdkm> getCtdkms() {
		return this.ctdkms;
	}

	public void setCtdkms(Set<Ctdkm> ctdkms) {
		this.ctdkms = ctdkms;
	}

	

	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mathang")
	public Set<Danhgia> getDanhgias() {
		return this.danhgias;
	}

	public void setDanhgias(Set<Danhgia> danhgias) {
		this.danhgias = danhgias;
	}

}
