package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.OrderDTO;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;
    private final CartDetailRepository cartDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            CartService cartService, CartDetailRepository cartDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartService = cartService;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Order orderDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        if (orderDTO != null) {
            order.setReceiverName(orderDTO.getReceiverName());
            order.setReceiverAddress(orderDTO.getReceiverAddress());
            order.setReceiverPhone(orderDTO.getReceiverPhone());
        }
        return order;
    }

    public void handleSaveOrder(OrderDTO orderDTO, User user, Cart cart, HttpSession session) {
        Order order = orderDTOToOrder(orderDTO);
        if (user != null) {
            if (cart != null) {
                List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
                order.setUser(user);
                order.setStatus("PENDING");
                order.setTotalPrice(getTotalPrice(cartDetails));
                this.orderRepository.save(order);
                addProductToOrderDetail(cartDetails, order);
                this.cartService.handleRemoveCart(cart);
                session.setAttribute("sum", 0);
            }
        }

    }

    private double getTotalPrice(List<CartDetail> cartDetails) {
        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }
        return totalPrice;
    }

    public void addProductToOrderDetail(List<CartDetail> cartDetails, Order order) {
        if (!cartDetails.isEmpty()) {
            for (CartDetail cd : cartDetails) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail = new OrderDetail();
                orderDetail.setQuantity(cd.getQuantity());
                orderDetail.setProduct(cd.getProduct());
                orderDetail.setPrice(cd.getPrice());
                orderDetail.setOrder(order);
                this.orderDetailRepository.save(orderDetail);
                this.cartDetailRepository.delete(cd);
            }

        }
    }

}
