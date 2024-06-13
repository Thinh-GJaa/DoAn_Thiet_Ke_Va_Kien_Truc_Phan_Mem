package management.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.http.HttpStatus;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import management.DTO.SPNhapDto;
import management.DTO.SanPham_PD_DTO;
import management.bean.ProductWithDiscount;
import management.controller.ReadPython;
import management.dao.IDonHangDao;
import management.dao.IGioHangDAO;
import management.dao.IMatHangDao;
import management.dao.INhapHangDao;

import management.entity.Chatlieu;
import management.entity.Ctpd;
import management.dao.IThanhToanDAO;

import management.entity.Ctpn;
import management.entity.CtpnId;
import management.entity.Hinhanhmh;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Nhacungcap;
import management.entity.Nhanhieu;
import management.entity.Phieudat;
import management.entity.Phieunhap;
import management.entity.Size;

@Controller
@Transactional
@RestController
@RequestMapping("/admin")
public class DonHangController {

	@Autowired
	private IDonHangDao donHangDao;
	
	@Autowired
	private IGioHangDAO gioHangDao;
	
	@GetMapping("/don-hang")
	public ModelAndView showhome(ModelMap model) {
		List<Phieudat> dspd = new ArrayList<>();
		//Lấy ds Phiếu đặt chưa duyệt 
		dspd = donHangDao.layDSPDTheoTrangThai(1);

		model.addAttribute("dspd_chuaDuyet", dspd);
		ModelAndView mav = new ModelAndView("admin/donHang");
		return mav;
	}

	
	@GetMapping("/don-hang/{status}")
	public ModelAndView layDonHangTheoTrangThai(@PathVariable int status) {
		List<Phieudat> dspd = new ArrayList<>();
		//Lấy ds Phiếu đặt chưa duyệt 
		dspd = donHangDao.layDSPDTheoTrangThai(status);
		ModelAndView mav = new ModelAndView("admin/donHang");
		mav.addObject("dspd", dspd);
		mav.addObject("statusValue",status);
		return mav;
	}

	@GetMapping("/don-hang/chi-tiet/{mapd}")
	public ResponseEntity<List<SanPham_PD_DTO>> layChiTietPD(@PathVariable int mapd) {
	    List<Ctpd> dspd = donHangDao.layDSSPCuaPD(mapd);
	    List<SanPham_PD_DTO> dssp = new ArrayList<>();

	    for (Ctpd ctpd : dspd) {
	        SanPham_PD_DTO sp = new SanPham_PD_DTO();
	        
	        sp.setMaSP(ctpd.getCtsize().getMathang().getMamh());
	        sp.setTenSP(ctpd.getCtsize().getMathang().getTenmh());
	        sp.setSoLuong(ctpd.getSoluong());
	        sp.setSize(ctpd.getCtsize().getSize().getTensize());
	        sp.setHinhAnh(donHangDao.layAnhSP(sp.getMaSP()));
	        sp.setGia(donHangDao.LayGiaSP(sp.getMaSP()));
	        System.out.println("Link ảnh: " + sp.getHinhAnh());
	        sp.setGia(donHangDao.LayGiaSP(sp.getMaSP()));
	        sp.setMucKM(gioHangDao.getDiscount_Product(sp.getMaSP()));
	        
	        dssp.add(sp);
	    }

	    return ResponseEntity.ok().body(dssp);
	}

	
    
	// Phương thức để xử lý hủy đơn hàng
	@PostMapping("/don-hang/huy/{mapd}")
	public ResponseEntity<String> huyPD(@PathVariable int mapd) {
	    Phieudat pd = donHangDao.layPDTheoID(mapd);
	    pd.setTrangthai(4);
	    boolean result = donHangDao.thayDoiTrangThaiPD(pd);
	    
	    if (result) {
	        return ResponseEntity.ok().build();
	    } else {
	    	return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Lỗi khi hủy đơn hàng");
	    }
	}


 // Phương thức để xử lý duyệt đơn hàng
    @PostMapping("/don-hang/duyet/{mapd}")
    public ResponseEntity<String> duyetPD(@PathVariable int mapd) {
	    Phieudat pd = donHangDao.layPDTheoID(mapd);
	    pd.setTrangthai(2);
	    boolean result = donHangDao.thayDoiTrangThaiPD(pd);
	    
	    if (result) {
	        return ResponseEntity.ok().build();
	    } else {
	    	return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Lỗi khi hủy đơn hàng");
	    }
	
    }
    
    @PostMapping("/don-hang/giao-thanh-cong/{mapd}")
    public ResponseEntity<String> giaoThanhCongPD(@PathVariable int mapd) {
	    Phieudat pd = donHangDao.layPDTheoID(mapd);
	    pd.setTrangthai(3);
	    boolean result = donHangDao.thayDoiTrangThaiPD(pd);
	    
	    if (result) {
	        return ResponseEntity.ok().build();
	    } else {
	    	return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Lỗi khi hủy đơn hàng");
	    }
	
    }
    @RequestMapping("logout")
	public ModelAndView logOut(HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		Boolean userEmail = (Boolean) session.getAttribute("login");
		
		if (userEmail != null) {
			 session.setAttribute("loggedInUserEmail", null);
	        // Nếu userEmail là null, bạn có thể xóa nó khỏi session như sau:
	        session.setAttribute("login", false);
	    }
		ModelAndView mav = new ModelAndView("redirect:/user/index");
		return mav;
	}
}
