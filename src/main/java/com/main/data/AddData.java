package com.main.data;

import com.github.javafaker.Faker;
import com.main.dto.OrdersDto;
import com.main.dto.ProductCartDto;
import com.main.dto.ResponseObject;
import com.main.dto.UserPaymentDto;
import com.main.entity.Orders;
import com.main.entity.Products;
import com.main.service.OrderService;
import com.main.service.ProductService;
import com.main.utils.EntityDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("add-data")
public class AddData {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping("product")
    @ResponseBody
    public ResponseObject generateProducts() {
        int numberOfProducts = 300;

        List<Products> productsList = new ArrayList<>();
        Faker faker = new Faker();


        for (int i = 0; i < numberOfProducts; i++) {
            Products product = new Products();

            product.setId("SP000" + i);
            product.setProductTypeId(faker.number().numberBetween(1, 40));
            product.setProductBrandId("BR000" + faker.number().numberBetween(1, 10));
            product.setProductName(faker.commerce().productName());
            product.setPrice(BigDecimal.valueOf(faker.number().numberBetween(1000000, 1000000000)));
            product.setQuantity(faker.number().numberBetween(10, 10000));
            product.setDescriptions(faker.lorem().sentence());
            product.setTemplateDescription(faker.lorem().paragraph());
            product.setPowers(faker.number().numberBetween(1, 100) + faker.options().option("W", "KW"));
            product.setWarranty(faker.number().numberBetween(1, 15) + faker.options().option(" Tháng", " Năm"));
            product.setIsStatusDelete(faker.options().option("Đang kinh doanh", "Ngừng kinh doanh"));

            Timestamp randomTimestamp = new Timestamp(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).getTime());

            product.setDateCreated(randomTimestamp);

            productService.save(product);
            productsList.add(product);
        }

        return new ResponseObject("200", "Đã thêm dữ liệu Product thành công", productsList);
    }

    @GetMapping("order")
    @ResponseBody
    public ResponseObject generateOrders() {
        int numberOfOrders = 500;

        List<Orders> ordersList = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < numberOfOrders; i++) {
            Orders order = new Orders();

            order.setId("ORD000" + i);
            order.setUserId(faker.number().numberBetween(1, 40));
            order.setPaymentType(faker.bool().bool());
            order.setPaymentStatus(faker.number().numberBetween(0, 2)); // Assuming payment status can be 0, 1, or 2
            order.setOrderStatus(faker.lorem().word());
            order.setOrderShipCost(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000)));
            order.setToName(faker.name().fullName());
            order.setToPhone(faker.phoneNumber().cellPhone());
            order.setToProvince(faker.address().state());
            order.setToDistrict(faker.address().city());
            order.setToWard(faker.address().secondaryAddress());
            order.setToAddress(faker.address().fullAddress());
            order.setOrderNote(faker.lorem().sentence());
            order.setDateCreated(new Timestamp(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).getTime()));

            orderService.save(order);
            ordersList.add(order);
        }
        return new ResponseObject("200", "Đã thêm dữ liệu Product thành công", ordersList);
    }


}
