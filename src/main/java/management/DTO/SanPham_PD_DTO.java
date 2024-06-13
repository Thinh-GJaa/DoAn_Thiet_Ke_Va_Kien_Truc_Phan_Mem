package management.DTO;

public class SanPham_PD_DTO {
    private int maSP;
    private String tenSP;
    private String size;
    private int soLuong;
    private float gia;
    private double mucKM;
    private String hinhAnh;

    public SanPham_PD_DTO() {
        super();
    }

    public SanPham_PD_DTO(int maSP, String tenSP, String size, int soLuong, float gia, double mucKM, String hinhAnh) {
        super();
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.size = size;
        this.soLuong = soLuong;
        this.gia = gia;
        this.mucKM = mucKM;
        this.hinhAnh = hinhAnh;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public double getMucKM() {
        return mucKM;
    }

    public void setMucKM(double mucKM) {
        this.mucKM = mucKM;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public float getGiaSauKM() {
        return gia * (1 - (float) mucKM/100);
    }
}
