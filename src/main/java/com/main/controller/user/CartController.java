package com.main.controller.user;

import com.main.entity.Carts;
import com.main.entity.Users;
import com.main.service.CartService;
import com.main.service.ProductService;
import com.main.service.UserService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("gio-hang")
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpSession session;

    @GetMapping
    public String showCart(Model model) {
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
        List<Carts> carts = cartService.findListCartByUserId(users.getId());
        int totalPricePay = cartService.findPriceByUserId(users.getId());

        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", totalPricePay);
        session.setAttribute("cartQuantity", cartService.countCartByUserId(users.getId()));
        return "views/user/page/cart";
    }

    @GetMapping("delete/{cartId}")
    public String deleteCart(@PathVariable int cartId) {
        cartService.delete(cartId);
        session.setAttribute("toastSuccess", "Xoá sản phẩm thành công !");
        return "redirect:/gio-hang";
    }
}