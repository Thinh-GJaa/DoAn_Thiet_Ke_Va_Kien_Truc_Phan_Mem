package management.dao;

import java.util.List;

import management.entity.Nhacungcap;

public interface INhaCungCapDao {
	
	public List<Nhacungcap> get_ListSupplier();
	public boolean addSupplier(Nhacungcap supplier);
}
