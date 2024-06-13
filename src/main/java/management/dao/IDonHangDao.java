
package management.dao;


import java.util.List;

import management.entity.Ctpd;
import management.entity.Mathang;
import management.entity.Phieudat;
import management.entity.Size;

public interface IDonHangDao {
	
	public Mathang layMatHangTheoID(int id);
	
	public int LayGiaSP(int id);
	
	public Size laySize(int maSize);
	
	public List<Ctpd> layDSSPCuaPD(int mapd);
	
	public List<Phieudat> layDSPDTheoTrangThai(int status);
	
	public boolean thayDoiTrangThaiPD(Phieudat pd);
	
	public Phieudat layPDTheoID(int mapd);
	
	public String layAnhSP(int masp);
}

	