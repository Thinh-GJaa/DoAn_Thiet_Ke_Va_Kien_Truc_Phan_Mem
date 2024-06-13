<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="javax.swing.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<style>

.form-control.form-group{
	font-size: 23px;
}

</style>
</head>
<body class="hold-transition sidebar-mini">
	<div class="container">
		<div class="wrapper">
			<div class="content-wrapper">
				<section class="content-header">
					<div class="container-fluid"></div>
				</section>
				<section class="content">
					<div class="container-fluid">
						<div class="col-md-12">
							<div class="card w-auto">
								<div class="card-body register-card-body">
									<h1 class="text-center text-danger mb-4">
										<i class="fas fa-user"></i> THÔNG TIN CÁ NHÂN <i
											class="fas fa-user"></i>
									</h1>
									<div class="row mb-2">
										<div class="col-sm-6">
											<c:if test="${not empty successMessage}">
												<div class="alert alert-success" role="alert">
													<p>${successMessage}</p>
												</div>
											</c:if>
											<c:if test="${not empty errorMessage}">
												<div class="alert alert-danger" role="alert">
													<ul>
														<c:forEach items="${errorMessage}" var="error">
															<li>${error}</li>
														</c:forEach>
													</ul>
												</div>
											</c:if>
										</div>
									</div>
									<form method="POST" action="/user/profile/Edit"
										enctype="multipart/form-data">
										<div class="form-row">
											<div class="form-group col-md-6">
												<label for="manv"><i class="fas fa-id-card"></i> Mã</label>
												<input type="text" class="form-control"
													value="${khachhang.getMakh()}" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="hoten"><i class="fas fa-user"></i> Họ
													Tên</label> <input type="text" class="form-control"
													value="${khachhang.getHotenkh()}" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="ngaysinh"><i
													class="fas fa-birthday-cake"></i> Ngày sinh</label> <input
													type="text" class="form-control"
													value="<fmt:formatDate value='${khachhang.getNgaysinh()}' pattern='dd-MM-yyyy' />"
													disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="sdt"><i class="fas fa-phone"></i> Số
													điện thoại</label> <input type="text" class="form-control"
													value="${khachhang.getSdt()}" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="gioitinh"><i class="fas fa-venus-mars"></i>
													Giới Tính</label> <input type="text" class="form-control"
													value="${khachhang.getGioitinh() == true ? 'Nam' : 'Nữ'}"
													disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="diachi"><i class="fas fa-map-marker-alt"></i>
													Địa chỉ</label> <input type="text" class="form-control"
													value="${khachhang.getDiachi()}" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="email"><i class="fas fa-envelope"></i>
													Địa chỉ Email</label> <input type="text" class="form-control"
													value="${khachhang.taikhoan.getEmail()}" disabled>
											</div>
										</div>
										<button type="button" class="btn btn-info float-right ml-2"
											data-toggle="modal"
											data-target="#modal-edit-${khachhang.getMakh()}">
											<i class="fas fa-edit"></i> Chỉnh sửa
										</button>

										<div class="modal fade" id="modal-edit-${khachhang.getMakh()}"
											tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered modal-lg">
												<div class="modal-content">
													<div class="modal-header bg-warning text-white">
														<h4 class="modal-title">
															<i class="fas fa-user-edit"></i> Cập Nhật Thông Tin
														</h4>
														<button type="button" class="close" data-dismiss="modal">&times;</button>
													</div>
													<div class="modal-body">
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label for="makh"><i class="fas fa-id-card"></i>
																		Mã</label> <input type="text" name="makh" class="form-control"
																		value="${khachhang.getMakh()}" disabled>
																</div>
																<div class="form-group">
																	<label for="email"><i class="fas fa-envelope"></i>
																		Email</label> <input type="email" name="email"
																		value="${khachhang.getTaikhoan().getEmail()}"
																		class="form-control" disabled>
																</div>
																<div class="form-group">
																	<label for="hoten"><i class="fas fa-user"></i>
																		Họ Tên</label> <input type="text" name="hoten"
																		value="${khachhang.getHotenkh()}" class="form-control"
																		required>
																</div>
																<div class="form-group">
																	<label for="sdt"><i class="fas fa-phone"></i>
																		SĐT</label> <input type="text" name="sdt"
																		value="${khachhang.getSdt()}" class="form-control"
																		required>
																</div>
																<div class="form-group">
																	<label for="ngaysinh"><i
																		class="fas fa-birthday-cake"></i> Ngày Sinh</label> <input
																		type="date" name="ngaysinh"
																		value="<fmt:formatDate value='${khachhang.getNgaysinh()}' pattern='yyyy-MM-dd' />"
																		class="form-control" required>
																</div>
																<div class="form-group">
																	<label for="gioitinh"><i
																		class="fas fa-venus-mars"></i> Giới Tính</label> <select
																		name="gioitinh" class="form-control">
																		<option value="true"
																			${khachhang.getGioitinh() == true ? "selected" : ""}>Nam</option>
																		<option value="false"
																			${khachhang.getGioitinh() == false ? "selected" : ""}>Nữ</option>
																	</select>
																</div>
																<div class="form-group">
																	<label for="diachi"><i
																		class="fas fa-map-marker-alt"></i> Địa chỉ</label> <input
																		type="text" name="diachi"
																		value="${khachhang.getDiachi()}" class="form-control"
																		required>
																</div>
																<button type="submit" class="btn btn-primary"
																	name="update"
																	onclick="return confirm('Bạn có chắc muốn cập nhật ?')">
																	<i class="fas fa-check"></i> Cập nhật
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>

			<footer class="main-footer">
				<div class="float-right d-none d-sm-block">
					<b>TN-TT</b>
				</div>
			</footer>
		</div>
	</div>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$("#myInput")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			bsCustomFileInput.init();
		});
	</script>
</body>
</html>


