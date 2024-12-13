package vn.hoidanit.laptopshop.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class OrderDTO {

    @NotBlank(message = "Tên người nhận không thể để trống.")
    private String receiverName;

    @NotBlank(message = "Địa chỉ người nhận không thể để trống.")
    private String receiverAddress;

    @NotBlank(message = "Số điện thoại người nhận không thể để trống.")
    private String receiverPhone;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

}
