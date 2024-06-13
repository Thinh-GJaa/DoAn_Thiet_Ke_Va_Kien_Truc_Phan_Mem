
package management.controller.admin;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import management.dao.IMatHangDao;
import management.dao.INhapHangDao;
import management.entity.Loaimh;
import management.entity.Mathang;

@Controller

@RequestMapping("/admin/")
public class SanPhamController {
	@Autowired
	private IMatHangDao iMatHangDao;

	@RequestMapping("products")
	private String product(ModelMap model)
	{
		List<Mathang> productlist = iMatHangDao.getAllSP();
		model.addAttribute("productlist", productlist);
		return "admin/quanlysp";
	}
	
	@RequestMapping(value = "product/Edit")
	public String editSP(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//		
//		if (userObj == null) {
//	        return "login";
//	    }
		Integer masp = Integer.parseInt(request.getParameter("masp"));
		String tensp = request.getParameter("ten");
		String motasp = request.getParameter("mota");

		/*
		 * try { if (!file.isEmpty()) { BasePath bPath = new BasePath(); String path =
		 * bPath.getPathImgProduct() + masp + ".jpg"; System.out.println("Upload ảnh: "
		 * + file.getOriginalFilename() + " thành công"); file.transferTo(new
		 * File(path)); anhGoc = masp + ".jpg"; Thread.sleep(5000);
		 * System.out.println(path); }
		 * 
		 * Mathang product =iMatHangDao.getSP(masp); product.setTenmh(tensp);
		 * 
		 * product.setMota(motasp);
		 * 
		 * Integer temp = iMatHangDao.updateProduct(product); if (temp != 0) {
		 * model.addAttribute("successMessage", "Cập nhật thành công!"); } else {
		 * model.addAttribute("errorMessage", "Cập nhật thất bại!"); }
		 * 
		 * List<Mathang> productlist = iMatHangDao.getAllSP(); List<Loaimh> categorylist
		 * = iMatHangDao.getAllLoai(); model.addAttribute("productlist", productlist);
		 * model.addAttribute("categorylist", categorylist); } catch (Exception e) {
		 * List<Mathang> productlist = iMatHangDao.getAllSP(); List<Loaimh> categorylist
		 * = iMatHangDao.getAllLoai(); model.addAttribute("productlist", productlist);
		 * model.addAttribute("categorylist", categorylist);
		 * model.addAttribute("errorMessage", "Lỗi: Cập nhật thất bại!"); return
		 * "admin/quanlysp"; }
		 */
		Mathang product =iMatHangDao.getSP(masp);
		product.setTenmh(tensp);
		
		product.setMota(motasp);
		
		Integer temp = iMatHangDao.updateProduct(product);
		if (temp != 0) {
			model.addAttribute("successMessage", "Cập nhật thành công!");
		} else {
			model.addAttribute("errorMessage", "Cập nhật thất bại!");
		}

		List<Mathang> productlist = iMatHangDao.getAllSP();
		List<Loaimh> categorylist = iMatHangDao.getAllLoai();
		model.addAttribute("productlist", productlist);
		model.addAttribute("categorylist", categorylist);
		return "admin/quanlysp";

	}

	@RequestMapping("product/Delete")
	public String deleteSP(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//		
//		if (userObj == null) {
//	        return "login";
//	    }
		Integer masp = Integer.parseInt(request.getParameter("masp"));
		Mathang sp = iMatHangDao.getSP(masp);
		sp.setTrangthai(0);

		Integer temp = iMatHangDao.updateProduct(sp);
		// Integer temp1 = productAdminDao.updateStatusSeri(masp);
		if (temp != 0) {
			model.addAttribute("successMessage", "Xóa thành công!");
		} else {
			model.addAttribute("errorMessage", "Xóa thất bại!");
		}
		List<Mathang> productlist = iMatHangDao.getAllSP();
		List<Loaimh> categorylist = iMatHangDao.getAllLoai();
		model.addAttribute("productlist", productlist);
		model.addAttribute("categorylist", categorylist);
		return "admin/quanlysp";
	}

}
