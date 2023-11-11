package com.main.controller.restcontroller;

import com.main.dto.ProductCategoriesDto;
import com.main.dto.ProductTypesDto;
import com.main.dto.ProductsDto;
import com.main.entity.ProductBrands;
import com.main.entity.ProductCategories;
import com.main.entity.ProductTypes;
import com.main.entity.Products;
import com.main.service.ProductBrandService;
import com.main.service.ProductCategoryService;
import com.main.service.ProductService;
import com.main.service.ProductTypeService;
import com.main.utils.EntityDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductCategoriesDto>> getCategoriesWithProducts(@RequestParam(name = "search_product", required = false) String searchProduct) {
        // Định nghĩa một phương thức xử lý yêu cầu GET đến /api/products/categories và trả về dữ liệu dưới dạng JSON.

        List<ProductCategoriesDto> categories = new ArrayList<>();
        // Tạo một danh sách (List) rỗng để lưu trữ thông tin sản phẩm và danh mục.

        if (searchProduct == null || searchProduct.isEmpty()) {
            // Kiểm tra xem có tham số tìm kiếm "search_product" hoặc không. Nếu không, thực hiện lấy tất cả danh mục với sản phẩm.

            categories = getAllCategoriesWithProducts();
            // Gọi phương thức getAllCategoriesWithProducts để lấy tất cả danh mục và sản phẩm tương ứng.

        } else {
            // Nếu có tham số tìm kiếm, thực hiện tìm kiếm sản phẩm và cập nhật danh mục tương ứng.

            List<Products> searchResults = productService.findByProductNameContaining(searchProduct);
            // Thực hiện tìm kiếm sản phẩm theo tên chứa trong tham số tìm kiếm "searchProduct".

            if (!searchResults.isEmpty()) {
                // Kiểm tra xem có sản phẩm được tìm thấy hay không.
                for (Products product : searchResults) {
                    ProductCategories category = categoryService.findById(product.getProductTypesByProductTypeId().getCategoryId());
                    if (!categories.contains(category)) {
                        ProductCategoriesDto categoriesDto = EntityDtoUtils.convertToDto(category, ProductCategoriesDto.class);
                        categories.add(categoriesDto);
                    }
                }
            }
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
        // Trả về danh sách danh mục và sản phẩm dưới dạng phản hồi JSON với mã trạng thái OK (200).
    }

    private List<ProductCategoriesDto> getAllCategoriesWithProducts() {
        List<ProductCategories> categories = categoryService.findAll();

        // Tạo danh sách DTO để lưu trữ danh mục và sản phẩm tương ứng.
        List<ProductCategoriesDto> categoriesDtoList = new ArrayList<>();

        for (ProductCategories category : categories) {
            // Duyệt qua danh sách danh mục.

            List<ProductTypes> productTypes = productTypeService.findByCategoryId(category.getId());
            List<ProductTypesDto> productTypesDtoList = new ArrayList<>();
            for (ProductTypes productType : productTypes) {
                // Duyệt qua danh sách loại sản phẩm.

                List<Products> products = productService.findByProductTypeId(productType.getId());
                List<ProductsDto> productsDtoList = new ArrayList<>();
                for (Products product : products) {
                    // Duyệt qua danh sách sản phẩm.
                    String brandName = product.getProductBrandsByProductBrandId().getBrandName();

                    // Tạo đối tượng ProductsDto và đặt giá trị cho các trường khác
                    ProductsDto productDto = EntityDtoUtils.convertToDto(product, ProductsDto.class);
                    productDto.setBrandName(brandName);
                    productsDtoList.add(productDto);
                }
                ProductTypesDto productTypeDtoList = EntityDtoUtils.convertToDto(productType, ProductTypesDto.class);
                // Set danh sách sản phẩm vào cấu trúc dữ liệu của productType
                productTypesDtoList.add(productTypeDtoList);
            }

            categoriesDtoList.add(EntityDtoUtils.convertToDto(category, ProductCategoriesDto.class));
        }

        return categoriesDtoList;
    }


    // tìm ra product bằng mã
    @GetMapping("product/find-by-id/{productId}")
    public ResponseEntity<Products> getAccountById(@PathVariable String productId) {
        Products product = productService.findProductByProductId(productId);
        return ResponseEntity.ok().body(product);
    }

    // tìm ra 4 danh mục đầu tiên
    @GetMapping("product/get-top4-category")
    public List<ProductCategories> showCategory() {
        Pageable pageable = PageRequest.of(0, 4);
        return categoryService.findAllTop4(pageable);
    }

    // tìm ra sản phẩm bằng mã danh mục
    @GetMapping("product/{categoryId}")
    public List<Object[]> productGetById(@PathVariable int categoryId) {
        return productService.findTopProductByCategoryId(categoryId);
    }

    @GetMapping("products/{categoryId}")
    public List<Products> productsList(@PathVariable int categoryId) {
        return productService.findByCategoryBProductTypeByProducts(categoryId);
    }

    @GetMapping("product/brandName/{productBrandId}")
    public ProductBrands brandNameByProduct(@PathVariable String productBrandId) {
        return productBrandService.findBandByProductId(productBrandId);
    }

}
