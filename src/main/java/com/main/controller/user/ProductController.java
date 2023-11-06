package com.main.controller.user;

import com.main.dto.CartsDto;
import com.main.entity.Carts;
import com.main.entity.Products;
import com.main.entity.Users;
import com.main.service.*;
import com.main.utils.EntityDtoUtils;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("san-pham")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductBrandService productBrandService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpSession session;

    @GetMapping
    public String showProductPage(Model model) {
        model.addAttribute("categories", productCategoryService.findAll());
        return "views/user/page/product";
    }

    @GetMapping("loai-danh-muc")
    public String showCategoryPage() {
        return "views/user/page/product-type";
    }

    @GetMapping("san-pham-chi-tiet")
    public String showProductDetails(@RequestParam("ma-san-pham") String productId, Model model) {
        if (!productId.isEmpty()) {
            Optional<Products> product = productService.findByProductId(productId);

            model.addAttribute("product", product);
            model.addAttribute("categories", productCategoryService.findAll());
            model.addAttribute("products", productService.findByProductTypeId(1));
        }
        return "views/user/page/product_details";
    }

    @PostMapping("them-vao-gio-hang/{productId}&{quantity}")
    public String addProductIntoCart(@PathVariable String productId, @PathVariable int quantity) {
        CartsDto cartsDto = new CartsDto();

        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
        Carts exitsCart = cartService.findProductExits(users.getId(), productId);

        if (exitsCart != null) {
            exitsCart.setQuantity(exitsCart.getQuantity() + quantity);
            cartService.save(exitsCart);

        } else {
            cartsDto.setUserId(users.getId());
            cartsDto.setProductId(productId);
            cartsDto.setQuantity(quantity);
            Carts carts = EntityDtoUtils.convertToEntity(cartsDto, Carts.class);
            cartService.save(carts);
        }
        return "redirect:/gio-hang";
    }
}
