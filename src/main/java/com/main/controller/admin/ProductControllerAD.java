package com.main.controller.admin;

import com.github.javafaker.Faker;
import com.main.dto.ProductImagesDto;
import com.main.dto.ProductsDto;
import com.main.entity.ProductImages;
import com.main.entity.Products;

import com.main.entity.Users;
import com.main.repository.ProductsRepository;
import com.main.service.*;
import com.main.utils.EntityDtoUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("quan-tri/san-pham")
public class ProductControllerAD {

    @Autowired
    ProductService productService;

    @Autowired
    ProductBrandService productBrandService;

    @Autowired
    ProductTypeService productTypesService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    HistoryService historyService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ProductsRepository productRepository;

    @Autowired
    HttpSession session;

    List<Products> productsList = new ArrayList<>();

    Users users = new Users();
    Map<String, String> response = new HashMap<>();

    @GetMapping()
    public String product(@ModelAttribute ProductsDto productsDto, Model model) {
        model.addAttribute("productDto", productsDto);
        model.addAttribute("listProduct", productService.findAll());
        return "views/admin/page/views/products-list";
    }

    @GetMapping("them-san-pham")
    public String showAddProduct(@ModelAttribute ProductsDto productsDto,
                                 RedirectAttributes redirectAttributes,
                                 Model model
    ) {
//        for (Products product : generateProducts(100)) {
//            productRepository.save(product);
//        }
        model.addAttribute("productDto", productsDto);
        model.addAttribute("listBrandValue", productBrandService.findAll());
        model.addAttribute("listProductTypeValue", productTypesService.findAll());
        return "views/admin/page/crud/product/products-add";
    }

    @PostMapping("them-san-pham")
    @ResponseBody
    public String saveProduct(@Validated @ModelAttribute ProductsDto productsDto,
                              BindingResult bindingResult,
                              @RequestParam(value = "file1", required = false) MultipartFile file01,
                              @RequestParam(value = "file2", required = false) MultipartFile file02,
                              @RequestParam(value = "file3", required = false) MultipartFile file03,
                              @RequestParam(value = "file4", required = false) MultipartFile file04,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (bindingResult.hasErrors()) {

            try {
                ProductImagesDto productImagesDto = new ProductImagesDto();
                model.addAttribute("listBrandValue", productBrandService.findAll());
                model.addAttribute("listProductTypeValue", productTypesService.findAll());

                productsDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
                productsDto.setId(productIdValue());
                productsDto.setIsStatusDelete("Đang kinh doanh");

                Products save = EntityDtoUtils.convertToEntity(productsDto, Products.class);
                System.out.println(productsDto.toString());

                productService.save(save);

                if (file01 != null && !file01.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }

                    try {
                        InputStream inputStream = file01.getInputStream();
                        Files.copy(inputStream, path.resolve(file01.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file01.getOriginalFilename();

                    productImagesDto.setId("IMG01_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);
                    productImageService.save(productImages);

                }

                if (file02 != null && !file02.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }
                    try {
                        InputStream inputStream = file02.getInputStream();
                        Files.copy(inputStream, path.resolve(file02.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file02.getOriginalFilename();

                    productImagesDto.setId("IMG02_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    productImageService.save(productImages);
                }

                if (file03 != null && !file03.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();
                    }
                    try {
                        InputStream inputStream = file03.getInputStream();
                        Files.copy(inputStream, path.resolve(file03.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file03.getOriginalFilename();

                    productImagesDto.setId("IMG03_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    productImageService.save(productImages);
                }

                if (file04 != null && !file04.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }
                    try {
                        InputStream inputStream = file04.getInputStream();
                        Files.copy(inputStream, path.resolve(file04.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String fileName = file04.getOriginalFilename();

                    productImagesDto.setId("IMG04_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    productImageService.save(productImages);

                }

                return response.put("result", "success");
            } catch (Exception io) {
                return response.put("result", "error");
            }
        } else {
            return response.put("result", "error");
        }
    }

    @GetMapping("sua-san-pham/{id}")
    public String showEditProduct(@Validated @ModelAttribute ProductsDto productsDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = "file1", required = false) MultipartFile file01,
                                  @RequestParam(value = "file2", required = false) MultipartFile file02,
                                  @RequestParam(value = "file3", required = false) MultipartFile file03,
                                  @RequestParam(value = "file4", required = false) MultipartFile file04,
                                  @PathVariable("id") String productIdPath,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        Optional<Products> product = productService.findByProductId(productIdPath);
        ProductImagesDto productImagesDto = new ProductImagesDto();

        ProductsDto productsDto1 = EntityDtoUtils.convertToDto(product.get(), ProductsDto.class);
        model.addAttribute("listBrandValue", productBrandService.findAll());
        model.addAttribute("listProductTypeValue", productTypesService.findAll());


        if (product.isPresent()) {
            model.addAttribute("productDto", productsDto1);
            return "views/admin/page/crud/product/products-edit";
        } else {
            return "views/admin/page/crud/product/products-edit";
        }
    }

    @PostMapping("sua-san-pham/{id}")
    @ResponseBody
    public String saveEditProduct(@Validated @ModelAttribute ProductsDto productsDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = "file1", required = false) MultipartFile file01,
                                  @RequestParam(value = "file2", required = false) MultipartFile file02,
                                  @RequestParam(value = "file3", required = false) MultipartFile file03,
                                  @RequestParam(value = "file4", required = false) MultipartFile file04,
                                  @PathVariable("id") String productIdPath,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        Optional<Products> product = productService.findByProductId(productIdPath);

        ProductsDto productsDto1 = EntityDtoUtils.convertToDto(product.get(), ProductsDto.class);
        model.addAttribute("listBrandValue", productBrandService.findAll());
        model.addAttribute("listProductTypeValue", productTypesService.findAll());

        ProductImagesDto productImagesDto = new ProductImagesDto();

        if (bindingResult.hasErrors()) {
            try {

                productsDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
                productsDto.setId(productIdPath);
                productsDto.setIsStatusDelete("Đang kinh doanh");

                Products save = EntityDtoUtils.convertToEntity(productsDto, Products.class);

                productService.save(save);
                System.out.println("Lưu thành công");
                System.out.println(productsDto.toString());

                if (file01 != null && !file01.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }

                    try {
                        InputStream inputStream = file01.getInputStream();
                        Files.copy(inputStream, path.resolve(file01.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file01.getOriginalFilename();

                    productImagesDto.setId("IMG01_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);
                    productImageService.save(productImages);

                }

                if (file02 != null && !file02.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }
                    try {
                        InputStream inputStream = file02.getInputStream();
                        Files.copy(inputStream, path.resolve(file02.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file02.getOriginalFilename();

                    productImagesDto.setId("IMG02_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    productImageService.save(productImages);
                }

                if (file03 != null && !file03.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();
                    }
                    try {
                        InputStream inputStream = file03.getInputStream();
                        Files.copy(inputStream, path.resolve(file03.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String fileName = file03.getOriginalFilename();

                    productImagesDto.setId("IMG03_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    productImageService.save(productImages);
                }

                if (file04 != null && !file04.isEmpty()) {
                    Path path = Paths.get("src/main/resources/static/upload/");

                    if (!path.toFile().exists()) {
                        path.toFile().mkdirs();

                    }
                    try {
                        InputStream inputStream = file04.getInputStream();
                        Files.copy(inputStream, path.resolve(file04.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String fileName = file04.getOriginalFilename();

                    productImagesDto.setId("IMG04_" + productsDto.getId());
                    productImagesDto.setProductId(productsDto.getId());
                    productImagesDto.setImagePath(fileName);

                    ProductImages productImages = EntityDtoUtils.convertToEntity(productImagesDto, ProductImages.class);

                    redirectAttributes.addFlashAttribute("updateProductSuccess", "Thay đổi thông tin sản phẩm thành công");
                    productImageService.save(productImages);

                }

                return response.put("result", "success");
            } catch (Exception io) {
                return response.put("result", "error");
            }
        } else {
            return response.put("result", "error");
        }
    }

    @ModelAttribute("productAddIdValue")
    public String productIdValue() {
        productsList = productService.findAll();
        String productId;
        if (productsList.isEmpty()) {
            productId = "SP0001";
        } else {
            String input = productsList.get(productsList.size() - 1).getId();
            String prefix = input.substring(0, 2);
            int number = Integer.parseInt(input.substring(2));
            String newNumber = String.format("%07d", number + 1);

            productId = prefix + newNumber;
        }

        if (productService.doesProductExist(productId)) {
            String prefix = productId.substring(0, 2);
            int number = Integer.parseInt(productId.substring(2));
            String newNumber = String.format("%07d", number + 1);
            productId = prefix + newNumber;
            return productId;
        } else {
            return productId;
        }
    }

    @GetMapping("productIdValueAPI")
    @ResponseBody
    public String productIdValueAPI() {
        productsList = productService.findAll();
        String productId;
        if (productsList.isEmpty()) {
            productId = "SP0000001";
        } else {
            String input = productsList.get(productsList.size() - 1).getId();
            String prefix = input.substring(0, 2);
            int number = Integer.parseInt(input.substring(2));
            String newNumber = String.format("%07d", number + 1);

            productId = prefix + newNumber;
        }

        if (productService.doesProductExist(productId)) {
            String prefix = productId.substring(0, 2);
            int number = Integer.parseInt(productId.substring(2));
            String newNumber = String.format("%07d", number + 1);
            productId = prefix + newNumber;
            return productId;
        } else {
            return productId;
        }
    }

    @ModelAttribute("isStatusDeleteValue")
    public List<String> isStatusDeleteValue() {
        List<String> listStatus = new ArrayList<>();
        List<String> additionalList = Arrays.asList("Đang kinh doanh", "Ngừng kinh doanh");
        listStatus.addAll(additionalList);
        return listStatus;
    }

    public List<Products> generateProducts(int numberOfProducts) {
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
//            product.setSaleOff(BigDecimal.valueOf(faker.number().numberBetween(10000, 100000)));
            product.setIsStatusDelete("Đang kinh doanh");

            Timestamp randomTimestamp = new Timestamp(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).getTime());

            product.setDateCreated(randomTimestamp);

            productsList.add(product);
        }
        return productsList;
    }
}
