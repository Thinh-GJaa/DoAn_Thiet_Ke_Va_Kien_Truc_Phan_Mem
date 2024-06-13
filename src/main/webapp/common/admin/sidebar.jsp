<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href='<c:url value="/admin/"/>' class="brand-link">
        <img src='<c:url value="/templates/admin/dist/img/logo.jpg"/>' alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">Fashion Shop</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <a href='<c:url value="/admin/"/>' class="nav-link">
                        <i class="fas fa-chart-pie"></i>
                        <p>Thống Kê<i class="right fas fa-angle-left"></i></p>
                    </a>
                    <ul class="nav nav-treeview" style="display: none;">
                        <li class="nav-item">
                            <a href='<c:url value="/admin/"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Doanh thu</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href='<c:url value="/admin/don-hang/1"/>' class="nav-link">
                        <i class="fas fa-shopping-cart"></i>
                        <p>Quản lý Đơn đặt hàng<i class="right fas fa-angle-left"></i></p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-box"></i>
                        <p>Quản Lý Nhập Hàng<i class="right fas fa-angle-left"></i></p>
                    </a>
                    <ul class="nav nav-treeview" style="display: none;">
                        <li class="nav-item">
                            <a href='<c:url value="/admin/nhap-hang"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Lập phiếu nhập hàng</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href='<c:url value="/admin/listReceipt"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Danh sách phiếu nhập</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-cubes"></i>
                        <p>Quản Lý Sản Phẩm<i class="right fas fa-angle-left"></i></p>
                    </a>
                    <ul class="nav nav-treeview" style="display: none;">
                        <li class="nav-item">
                            <a href='<c:url value="/admin/update/price"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Cập nhật giá sản phẩm</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href='<c:url value="/admin/products"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Danh sách sản phẩm</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-truck"></i>
                        <p>Quản Lý Nhà cung cấp<i class="right fas fa-angle-left"></i></p>
                    </a>
                    <ul class="nav nav-treeview" style="display: none;">
                        <li class="nav-item">
                            <a href='<c:url value="/admin/add-supplier"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Thêm nhà cung cấp</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href='<c:url value="/admin/listsupplier"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Danh sách nhà cung cấp</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="/admin/promotion" class="nav-link">
                        <i class="fas fa-tags"></i>
                        <p>Quản Lý Khuyến Mãi<i class="right fas fa-angle-left"></i></p>
                    </a>
                    <ul class="nav nav-treeview" style="display: none;">
                        <li class="nav-item">
                            <a href='<c:url value="/admin/promotion/home"/>' class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Danh sách khuyến mãi</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link" onclick="confirmLogout(event)">
                        <i class="fas fa-sign-out-alt"></i>
                        <p>Đăng xuất<i class="right fas fa-angle-left"></i></p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

<aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
</aside>

<!-- Thêm thư viện SweetAlert2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<!-- Thêm hàm JavaScript để xác nhận đăng xuất -->
<script>
    function confirmLogout(event) {
        event.preventDefault();
        Swal.fire({
            title: 'Bạn có chắc chắn muốn đăng xuất?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đăng xuất',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '/admin/logout';
            }
        })
    }
</script>
