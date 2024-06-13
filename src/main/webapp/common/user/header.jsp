<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!-- Thêm thư viện SweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
/* Thêm độ cao và đường viền cho ô tìm kiếm */
#searchForm input[type="search"] {
    height: 28px;
    border: 1px solid #ccc;
}

/* Thêm padding cho các thành phần header */
header .container {
    padding-top: 10px;
    padding-bottom: 10px;
}

/* Căn giữa nội dung trong cùng một dòng */
header .align-items-center {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>

<header class="bg-dark text-white fixed-top">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-4">
                <a href="/user/index">
                    <div id="shop-name" class="display-4">
                        <i class="fas fa-dragon"></i> Urban Vogue
                    </div>
                </a>
                <p>
                    <span class="lead ml-4"><em>Thời trang - phong cách</em></span>
                </p>
            </div>
            <div class="col-md-5">
                <form class="form-inline mt-4" id="searchForm" action="/product/search/">
                    <input class="form-control mr-2" style="width: 60%;" type="search" placeholder="Tìm kiếm" name="search">
                    <button class="btn btn-outline-light" type="submit">Tìm kiếm</button>
                </form>
            </div>
            <div class="col-md-3">
                <div class="d-flex align-items-center justify-content-end">
                    <%-- Kiểm tra đăng nhập và hiển thị phần tương ứng --%>
                    <%
                    Boolean loginValue = (Boolean) session.getAttribute("login");
                    boolean isLoggedIn = loginValue != null && loginValue.booleanValue();
                    if (isLoggedIn) {
                    %>
                    <a id="cartButton" href="/user/giohang" class="btn btn-outline-secondary">
                        <i class="fas fa-shopping-cart"></i> Giỏ hàng
                    </a>
                    <div class="dropdown mr-3">
                        <button onclick="toggleDropdown()" class="btn btn-outline-secondary dropbtn">
                            <i class="fas fa-user"></i> Nguyễn Xuân Thịnh
                        </button>
                        <div id="myDropdown" class="dropdown-content">
                            <a class="dropdown-item bg-light" href="/user/profile">Thông tin cá nhân</a>
                            <a class="dropdown-item bg-light" href="/user/history">Xem lịch sử đơn đặt hàng</a>
                            <a id="logoutBtn" class="dropdown-item bg-light" href="#">Đăng xuất</a>
                        </div>
                    </div>
                    <%
                    } else {
                    %>
                    <a id="loginButton" href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&hl=vi&redirect_uri=http://localhost:8080/login_google/LoginGoogleHandler&response_type=code&client_id=242828357380-bmi9embekgk0pqkcl14jqt35g80letup.apps.googleusercontent.com&approval_prompt=force" class="btn btn-outline-secondary">
                        <i class="fas fa-user"></i> Đăng Nhập
                    </a>
                    <%
                    }
                    %>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Lấy button "Nguyễn Xuân Thịnh" và dropdown menu
            var userNameButton = document.querySelector(".dropbtn");
            var userDropdownMenu = document.querySelector(".dropdown-content");

            // Thêm sự kiện click vào button "Nguyễn Xuân Thịnh"
            userNameButton.addEventListener('click', function(event) {
                event.preventDefault(); // Ngăn chặn mặc định chuyển hướng liên kết
                userDropdownMenu.classList.toggle('show'); // Hiển thị/ẩn dropdown menu
            });

            // Thêm sự kiện click vào document để ẩn dropdown menu khi nhấp ra ngoài
            document.addEventListener('click', function(event) {
                // Kiểm tra xem người dùng có nhấp vào button "Nguyễn Xuân Thịnh" hay không
                var isClickInsideButton = userNameButton.contains(event.target);
                // Kiểm tra xem dropdown menu hiện đang được hiển thị hay không
                var isDropdownVisible = userDropdownMenu.classList.contains('show');
                // Nếu người dùng không nhấp vào button "Nguyễn Xuân Thịnh" và dropdown menu đang hiển thị, ẩn nó đi
                if (!isClickInsideButton && isDropdownVisible) {
                    userDropdownMenu.classList.remove('show');
                }
            });

            // Thêm xác nhận trước khi đăng xuất
            var logoutBtn = document.getElementById('logoutBtn');
            logoutBtn.addEventListener('click', function(event) {
                event.preventDefault();
                Swal.fire({
                    title: 'Xác nhận đăng xuất',
                    text: 'Bạn có chắc chắn muốn đăng xuất?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Đồng ý',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Xử lý khi người dùng đồng ý đăng xuất
                        window.location.href= "/user/log-out"; // Chuyển hướng đến trang đăng xuất
                    }
                });
            });
        });
    </script>
</header>

