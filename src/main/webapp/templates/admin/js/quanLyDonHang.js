$(document).ready(function() {
    $("#status").change(function() {
        var status = $(this).val();
        $.ajax({
            type: "GET",
            url: "/don-hang/" + status,
            dataType: "json",
            success: function(data) {
                renderOrders(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error: " + textStatus, errorThrown);
            }
        });
    });
});

function renderOrders(orders) {
    var tbody = $("#invoiceTable tbody");
    tbody.empty();
    $.each(orders, function(index, order) {
        var row = "<tr>" +
            "<td>" + (index + 1) + "</td>" +
            "<td>" + order.orderCode + "</td>" +
            "<td>" + order.customerId + "</td>" +
            "<td>" + order.orderDate + "</td>" +
            "<td>" + order.address + "</td>" +
            "<td>" + order.phone + "</td>" +
            "<td>" + order.totalAmount + "</td>" +
            "<td>";
        if (order.status === "chuaDuyet") {
            row += "<button onclick='viewDetail(" + order.id + ")'>Xem chi tiết</button>" +
                "<button onclick='approveOrder(" + order.id + ")'>Duyệt đơn hàng</button>" +
                "<button onclick='cancelOrder(" + order.id + ")'>Hủy đơn hàng</button>";
        } else if (order.status === "daDuyet") {
            row += "<button onclick='deliverOrder(" + order.id + ")'>Giao thành công</button>";
        }
        row += "</td></tr>";
        tbody.append(row);
    });
}

function viewDetail(orderId) {
    // Thực hiện hành động xem chi tiết
}

function approveOrder(orderId) {
    $.ajax({
        type: "POST",
        url: "/orders/" + orderId + "/approve",
        success: function(data) {
            Swal.fire('Success', data, 'success');
            // Cập nhật lại danh sách đơn hàng sau khi duyệt thành công
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error: " + textStatus, errorThrown);
            Swal.fire('Error', textStatus, 'error');
        }
    });
}

function cancelOrder(orderId) {
    $.ajax({
        type: "POST",
        url: "/orders/" + orderId + "/cancel",
        success: function(data) {
            Swal.fire('Success', data, 'success');
            // Cập nhật lại danh sách đơn hàng sau khi hủy thành công
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error: " + textStatus, errorThrown);
            Swal.fire('Error', textStatus, 'error');
        }
    });
}

function deliverOrder(orderId) {
    // Thực hiện hành động giao thành công
}
