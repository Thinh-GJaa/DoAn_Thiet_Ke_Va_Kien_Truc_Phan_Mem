<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý đơn đặt hàng</title>

<!-- Thêm thư viện jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Thêm thư viện Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Thêm thư viện Bootstrap JS -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- <!-- Thêm thư viện DataTables CSS -->
<link
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css"
	rel="stylesheet">
-->
<!-- Thêm thư viện DataTables JS -->
<script
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<!-- Thêm thư viện SweetAlert2 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
	<div class="content-wrapper">
		<section class="container-fluid">
			<h1 class="text-center">Quản lý đơn đặt hàng</h1>
			<div style="font-size: 30px;">
				<label for="status">Trạng thái đơn đặt hàng: </label> <select
					class="form-select" id="status">
					<option value="1" ${statusValue == 1 ? 'selected' : ''}>Chưa
						duyệt</option>
					<option value="2" ${statusValue == 2 ? 'selected' : ''}>Đã
						duyệt</option>
					<option value="3" ${statusValue == 3 ? 'selected' : ''}>Giao
						thành công</option>
					<option value="4" ${statusValue == 4 ? 'selected' : ''}>Bị
						hủy</option>
				</select>
			</div>
			<section class="card mt-4">
				<div class="card-body">
					<table id="invoiceTable"
						class="table table-striped table-bordered table-hover">
						<thead style="background-color: lightgray;">
							<tr>
								<th>STT</th>
								<th>Mã đơn</th>
								<th>ID Khách hàng</th>
								<th>Ngày đặt</th>
								<th>Địa chỉ</th>
								<th>SĐT</th>
								<th>Tổng tiền</th>
								<th>Hoạt động</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty dspd}">
								<c:forEach items="${dspd}" var="pd" varStatus="status">
									<tr data-order-id="${pd.getMapd()}">
										<td>${status.index + 1}</td>
										<td>${pd.getMapd()}</td>
										<td>${pd.getKhachhang().getMakh()}</td>
										<td>${pd.getNgaydat()}</td>
										<td>${pd.getDiachi()}</td>
										<td>${pd.getSdt()}</td>
										<td><c:choose>
												<c:when test="${pd.getTongtienkm() > 0}">
													<del>
														<span class="gia-tien" data-gia="${pd.getTongtien()}">${pd.getTongtien()}</span>
													</del>
													<span class="gia-tien"
														data-gia="${pd.getTongtienkm()}">${pd.getTongtienkm()}</span>
												</c:when>
												<c:otherwise>
													<span class="gia-tien" data-gia="${pd.getTongtien()}">${pd.getTongtien()}</span>
												</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when test="${pd.getTrangthai() == 1}">
													<button onclick="viewDetail(${pd.getMapd()})"
														class="btn btn-info" title="Chi tiết phiếu đặt">
														<i class="fas fa-info-circle"></i>
													</button>
													<button onclick="confirmApproveOrder(${pd.getMapd()})"
														class="btn btn-success" title="Duyệt phiếu đặt">
														<i class="fas fa-check-circle"></i>
													</button>
													<button onclick="confirmCancelOrder(${pd.getMapd()})"
														class="btn btn-danger" title="Hủy phiếu đặt">
														<i class="fas fa-times-circle"></i>
													</button>
												</c:when>
												<c:when test="${pd.getTrangthai() == 2}">
													<button onclick="viewDetail(${pd.getMapd()})"
														class="btn btn-info" title="Chi tiết phiếu đặt">
														<i class="fas fa-info-circle"></i>
													</button>
													<button onclick="confirmDeliverOrder(${pd.getMapd()})"
														class="btn btn-success" title="Giao thành công">
														<i class="fas fa-truck"></i>
													</button>
												</c:when>
												<c:when test="${pd.getTrangthai() == 3}">
													<button onclick="viewDetail(${pd.getMapd()})"
														class="btn btn-info" title="Xem chi tiết">
														<i class="fas fa-info-circle"></i>
													</button>
												</c:when>
												<c:when test="${pd.getTrangthai() == 4}">
													<button onclick="viewDetail(${pd.getMapd()})"
														class="btn btn-info" title="Xem chi tiết">
														<i class="fas fa-info-circle"></i>
													</button>
												</c:when>
											</c:choose></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</section>
		</section>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Chi tiết Phiếu
						đặt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table id="chiTietPDTable"
						class="table table-striped table-bordered table-hover">
						<thead style="background-color: lightgray;">
							<tr>
								<th>STT</th>
								<th>Mã Sản phẩm</th>
								<th>Tên Sản phẩm</th>
								<th>Size</th>
								<th>Hình ảnh</th>
								<th>Giá tiền</th>
							</tr>
						</thead>
						<tbody>
							<!-- Dữ liệu sẽ được thêm vào đây bằng JavaScript -->
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
    $(document).ready(function() {
        $("#status").change(function() {
            var selectedOption = $(this).children("option:selected").val();
            window.location.href = "/admin/don-hang/" + selectedOption; // Chuyển hướng đến trang tương ứng
        });

        $('#invoiceTable').DataTable();

        // Định dạng tất cả các phần tử có lớp "gia-tien"
        $('.gia-tien').each(function() {
            var gia = $(this).data('gia');
            var formatter = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND',
            });
            $(this).text(formatter.format(gia));
        });
		
        $('.giaKM').each(function() {
            // Lấy giá trị từ thuộc tính "data-giaKM"
            var giaKM = $(this).data('giaKM');
			console.log(giaKM);
            // Định dạng giá trị với Intl.NumberFormat
            var formatterKM = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND',
            });

            // Gán lại giá trị đã định dạng vào phần tử
            $(this).text(formatterKM.format(giaKM));
        });
        
        
        // Hiển thị thông báo khi danh sách rỗng
        if ($('#invoiceTable tbody tr').length === 0) {
            Swal.fire({
                icon: 'info',
                title: 'Không có dữ liệu',
                text: 'Không có đơn hàng phù hợp được tìm thấy.'
            });
        }
        

    });

    function viewDetail(orderId) {
        $.ajax({
            url: "/admin/don-hang/chi-tiet/" + orderId,
            type: "GET",
            success: function(data) {
                $("#chiTietPDTable tbody").empty();
                $.each(data, function(index, item) {
                    $("#chiTietPDTable tbody").append(
                        "<tr>" +
                        "<td>" + (index + 1) + "</td>" +
                        "<td>" + item.maSP + "</td>" +
                        "<td>" + item.tenSP + "</td>" +
                        "<td>" + item.size + "</td>" +
                        "<td><img src='" + item.hinhAnh + "' style='max-width: 50px; max-height: 50px;'></td>" +
                        "<td><span class='gia-tien' data-gia='" + item.giaSauKM + "'>" + item.giaSauKM + " VND</span></td>" +
                        "</tr>"
                    );
                });
                // Định dạng tất cả các phần tử có lớp "gia-tien"
                $('.gia-tien').each(function() {
                    var gia = $(this).data('gia');
                    var formatter = new Intl.NumberFormat('vi-VN', {
                        style: 'currency',
                        currency: 'VND',
                    });
                    $(this).text(formatter.format(gia));
                });
                
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log("Error: " + errorThrown);
            }
        });
        $("#detailModal").modal("show");
    }

    function approveOrder(orderId) {
        // Gửi yêu cầu duyệt đơn hàng đến máy chủ
        $.ajax({
            url: "/admin/don-hang/duyet/" + orderId,
            type: "POST",
            success: function(response) {
                // Xóa đơn hàng khỏi bảng
                $("#invoiceTable tbody tr[data-order-id='" + orderId + "']").remove();
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Đã duyệt đơn hàng',
                    showConfirmButton: false,
                    timer: 1500
                });
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log("Error: " + errorThrown);
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi khi duyệt đơn hàng',
                    text: response.message
                });
            }
        });
    }

    function cancelOrder(orderId) {
        // Gửi yêu cầu hủy đơn hàng đến máy chủ
        $.ajax({
            url: "/admin/don-hang/huy/" + orderId,
            type: "POST",
            success: function(response) {
                // Xóa đơn hàng khỏi bảng
                $("#invoiceTable tbody tr[data-order-id='" + orderId + "']").remove();
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Đã hủy đơn hàng',
                    showConfirmButton: false,
                    timer: 1500
                });
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log("Error: " + errorThrown);
                // Hiển thị thông báo lỗi
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi khi hủy đơn hàng',
                    text: xhr.responseText
                });
            }
        });
    }

    function deliverOrder(orderId) {
        // Gửi yêu cầu giao hàng thành công đến máy chủ
        $.ajax({
            url: "/admin/don-hang/giao-thanh-cong/" + orderId,
            type: "POST",
            success: function(response) {
                // Xóa đơn hàng khỏi bảng
                $("#invoiceTable tbody tr[data-order-id='" + orderId + "']").remove();
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận giao hàng thành công',
                    showConfirmButton: false,
                    timer: 1500
                });
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log("Error: " + errorThrown);
                // Hiển thị thông báo lỗi
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi khi xác nhận giao hàng thành công',
                    text: xhr.responseText
                });
            }
        });
    }

    function confirmApproveOrder(orderId) {
        Swal.fire({
            title: 'Xác nhận duyệt đơn hàng?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Duyệt',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng đồng ý, thực hiện duyệt đơn hàng
                approveOrder(orderId);
            }
        });
    }

    function confirmCancelOrder(orderId) {
        Swal.fire({
            title: 'Xác nhận hủy đơn hàng?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Không'
        }).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng đồng ý, thực hiện hủy đơn hàng
                cancelOrder(orderId);
            }
        });
    }

    function confirmDeliverOrder(orderId) {
        Swal.fire({
            title: 'Xác nhận giao hàng thành công?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng đồng ý, thực hiện giao hàng thành công
                deliverOrder(orderId);
            }
        });
    }
</script>
	<script type="text/javascript"></script>


</body>
</html>
