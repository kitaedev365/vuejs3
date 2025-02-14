package com.company.app1.ctrl;

import com.company.app1.biz.ProductService;
import com.company.app1.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/detail/{pno}")
    public ResponseEntity<Product> getProductById(@PathVariable(name ="pno") int pno) {
        Optional<Product> product = productService.getProductById(pno);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Product createProduct(@RequestParam(name = "cate") String cate,
                                 @RequestParam(name = "pname") String pname,
                                 @RequestParam(name = "pcontent") String pcontent,
                                 @RequestParam(name = "img1", required = false) MultipartFile img1,
                                 @RequestParam(name = "img2", required = false) MultipartFile img2,
                                 @RequestParam(name = "img3", required = false) MultipartFile img3) throws IOException {
        String img1Path = img1 != null ? saveFile(img1) : null;
        String img2Path = img2 != null ? saveFile(img2) : null;
        String img3Path = img3 != null ? saveFile(img3) : null;

        Product product = new Product();
        product.setCate(cate);
        product.setPname(pname);
        product.setPcontent(pcontent);
        product.setImg1(img1Path);
        product.setImg2(img2Path);
        product.setImg3(img3Path);
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{pno}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "pno") int pno,
            @RequestParam(name = "cate") String cate,
            @RequestParam(name = "pname") String pname,
            @RequestParam(name = "pcontent") String pcontent,
            @RequestParam(name = "img1", required = false) MultipartFile img1,
            @RequestParam(name = "img2", required = false) MultipartFile img2,
            @RequestParam(name = "img3", required = false) MultipartFile img3) throws IOException {
        deleteFiles(productService.getProductById(pno).orElseThrow().getImg1(), productService.getProductById(pno).orElseThrow().getImg2(), productService.getProductById(pno).orElseThrow().getImg3());

        String img1Path = saveFile(img1);
        String img2Path = saveFile(img2);
        String img3Path = saveFile(img3);

        Product updatedProduct = new Product();
        updatedProduct.setCate(cate);
        updatedProduct.setPname(pname);
        updatedProduct.setPcontent(pcontent);
        updatedProduct.setImg1(img1Path);
        updatedProduct.setImg2(img2Path);
        updatedProduct.setImg3(img3Path);

        return ResponseEntity.ok(productService.updateProduct(pno, updatedProduct));
    }

    @DeleteMapping("/delete/{pno}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "pno") int pno) {
        deleteFiles(productService.getProductById(pno).orElseThrow().getImg1(), productService.getProductById(pno).orElseThrow().getImg2(), productService.getProductById(pno).orElseThrow().getImg3());
        productService.deleteProduct(pno);
        return ResponseEntity.noContent().build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {  // 파일이 null 또는 비어 있는지 체크
            return null;
        }
        Files.createDirectories(Paths.get(UPLOAD_DIR)); // 경로가 없으면 생성
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;
    }

    private void deleteFiles(String... filePaths) {
        for (String filePath : filePaths) {
            try {
                Files.deleteIfExists(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}