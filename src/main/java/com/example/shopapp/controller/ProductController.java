package com.example.shopapp.controller;

import com.example.shopapp.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProduct(
            @Valid @RequestBody
            ProductDTO productDto,
            //@RequestParam("file")MultipartFile file,
            BindingResult result){
        try{
            if (result.hasErrors()){
                List<String> errorMessages= result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages.toString());
            }
            List<MultipartFile> files=productDto.getFiles();
            files= files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files){
                if (file.getSize()==0){
                    continue;
                }
                if (file != null){
                    //Kiem tra kich thuoc va dinh dang
                    if (file.getSize() > 10*1024 *1024) { // Kich thuoc lon hon 10MB
                        //( HttpStatus.PAYLOAD_TOO_LARGE,"fILE is too large! Maximum size is 10MB");
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                                .body("File is too lare! Maximum size is 10MB");
                    }
                    String contenType= file.getContentType();
                    if (contenType== null || !contenType.startsWith("image/")){
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                .body("File must be an image");
                    }
                    //Luu file va cap nhat thumbnail trong DTO
                    String filename= storeFile(file);//Thay doi ham nay voi code cua ban da luu file
                    //Luu vao doi tuong product vao database
                }
            }



            return ResponseEntity.ok("Products creatr succsessfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException{
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        //Them UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename= UUID.randomUUID().toString()+'_'+ filename;
        //Đường dãn den thu mu chua file
        Path uploadDir= Paths.get("uploads");
        //Kiem tra thu muc upload ton tai hay chua
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }

        //Đuong dan day du den file
        Path destinatio = Paths.get(uploadDir.toString(), uniqueFilename);

        // Sao chep file vao thu muc
        Files.copy(file.getInputStream(), destinatio, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page ,
            @RequestParam("limmit") int limit
    ){

        return  ResponseEntity.ok("getproducts here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductId(@PathVariable("id") String productId){

        return ResponseEntity.ok("Products with Id"+ productId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id){
        return ResponseEntity.ok("update products"+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        return  ResponseEntity.status(HttpStatus.OK).body("Product delete successfuly");
    }
}
