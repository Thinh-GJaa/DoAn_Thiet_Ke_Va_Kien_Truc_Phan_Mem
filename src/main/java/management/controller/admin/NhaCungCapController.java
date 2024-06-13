package management.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import management.dao.INhaCungCapDao;
import management.entity.Nhacungcap;

@Controller
@RequestMapping("/admin/")
public class NhaCungCapController {

	@Autowired
	private INhaCungCapDao iNhaCungCapDao;

	// Trả về view: DS NCC
	@RequestMapping("listsupplier")
	public String getList_Customer(ModelMap map) {
		map.addAttribute("suppliers", iNhaCungCapDao.get_ListSupplier());

		return "admin/list_Supplier";
	}
	
	
	@RequestMapping("add-supplier")
	public String addSupplier(ModelMap modelMap) {
		modelMap.addAttribute("success", "");
		return "admin/addSupplier";
	}

	// Xử lí khi thêm nhà cung cấp
	@RequestMapping(value = "add-supplier", method = RequestMethod.POST)
	public String submitForm(ModelMap modelMap, @RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String address, @RequestParam("email") String email) {
		try {

			Nhacungcap supplier = new Nhacungcap();
			
			supplier.setTenncc(name);
			supplier.setSdt(phoneNumber);
			supplier.setDiachi(address);
			supplier.setEmail(email);
			if (iNhaCungCapDao.addSupplier(supplier)) {
				modelMap.addAttribute("success", "Thêm Nhà Cung Cấp Thành Công");
			} else {
				modelMap.addAttribute("success", "Lỗi cơ sở dữ liệu!");
			}
		} catch (Exception e) {
			modelMap.addAttribute("success", "Lỗi cơ sở dữ liệu!");
		}
		return "redirect:/admin/listsupplier";
	}
}
