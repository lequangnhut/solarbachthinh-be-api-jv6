package com.main.controller.admin;

import com.main.dto.ProductsDto;
import com.main.entity.Products;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quan-tri/san-pham")
public class ProductControllerAD {

    @Autowired
    ProductService productService;

    List<Products> productsList = new ArrayList<>();

    @GetMapping
    public String product(Model model) {
        model.addAttribute("products", productService.findAll());
        return "views/admin/page/views/products-list";
    }

    @GetMapping("them-san-pham")
    public String showAddProduct(Model model) {
        model.addAttribute("productsDTO", new ProductsDto());
        return "views/admin/page/crud/product/products-add";
    }

    @GetMapping("sua-san-pham/{productId}")
    public String showEditProduct(@PathVariable String productId, Model model) {
        Products product = productService.findByProductId(productId);

        model.addAttribute("product", product);
        return "views/admin/page/crud/product/products-edit";
    }

    @ModelAttribute("productIdValue")
    public String productIdValue() {
        productsList = productService.findAll();
        String productId;
        if (productsList.isEmpty()) {
            productId = "SP0001";
        } else {
            String input = productsList.get(productsList.size() - 1).getId();
            String prefix = input.substring(0, 2);
            int number = Integer.parseInt(input.substring(2));
            String newNumber = String.format("%04d", number + 1);

            productId = prefix + newNumber;
        }
        return productId;
    }
}
