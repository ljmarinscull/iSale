package com.webproject.isale.controllers;

import com.webproject.isale.business.product.IProductBusiness;
import com.webproject.isale.business.users.IUserBusiness;
import com.webproject.isale.exceptions.ApiError;
import com.webproject.isale.exceptions.BusinessException;
import com.webproject.isale.exceptions.NotFoundException;
import com.webproject.isale.model.ProductEntity;
import com.webproject.isale.model.UserEntity;
import com.webproject.isale.model.dto.ProductUpdateRequest;
import com.webproject.isale.business.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static com.webproject.isale.utils.Constants.*;

@RestController
public class ProductController {

    @Autowired
    IProductBusiness productBusiness;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    IUserBusiness userBusiness;

    @GetMapping(URL_BASE_POPULARS_PRODUCTS)
    ResponseEntity<List<ProductEntity>> getPopularsProducts() throws BusinessException {
        try {
            return new ResponseEntity(productBusiness.popularsList(), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(URL_BASE_PRODUCTS)
    ResponseEntity<List<ProductEntity>> getProducts() {
        try {
            return new ResponseEntity(productBusiness.list(), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(URL_BASE_USER_PRODUCTS)
    ResponseEntity<List<ProductEntity>> getUserProducts(Principal principal) {
        try {
            UserEntity user = userBusiness.loadByUserName(principal.getName());
            List<ProductEntity> list = productBusiness.userProductList(user.getId());
            return new ResponseEntity(list, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(URL_BASE_PRODUCT)
    ResponseEntity<ProductEntity> getProduct(@PathVariable Long id, Principal principal) {
        try {
            ProductEntity pe = productBusiness.load(id);
            if(principal.getName().equals(pe.getUser().getUsername())){
                return new ResponseEntity(pe, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(URL_BASE_PUBLIC_PRODUCT)
    ResponseEntity<ProductEntity> getPublicProduct(@PathVariable Long id){
        try {
            ProductEntity pe = productBusiness.load(id);
            return new ResponseEntity(pe, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"Resource not found","NOT_FOUND"),HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = URL_BASE_PRODUCTS)
    ResponseEntity<UserEntity> addProduct(@RequestPart("title") String title,
                                          @RequestPart("description") String description,
                                          @RequestPart(name = "file", required = false) MultipartFile file , Principal principal) {
        try {
            String username = principal.getName();
            UserEntity userEntity = userBusiness.loadByUserName(username);
            ProductEntity entity = new ProductEntity();

            if(file != null) {
                String fileName = fileStorageService.storeFile(file);

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/v1/products/image/")
                        .path(fileName)
                        .toUriString();
                entity.setImageUrl(fileDownloadUri);
            }

            entity.setTitle(title);
            entity.setDescription(description);
            entity.setUser(userEntity);

            productBusiness.save(entity);
            return new ResponseEntity(entity,HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"The title "+ title +" already exists","ConstraintViolationException"),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = URL_BASE_PRODUCTS)
    ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductUpdateRequest request, Principal principal) {

        try {
            ProductEntity pe = productBusiness.load(request.getId());
            if(principal.getName().equals(pe.getUser().getUsername())){
                if(request.getTitle() != null )
                    pe.setTitle(request.getTitle());

                if(request.getDescription() != null)
                    pe.setDescription(request.getDescription());

                productBusiness.save(pe);
            } else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(URL_BASE_VIEW)
    ResponseEntity<Object> addProductView(@PathVariable Long id) {
        try {
            productBusiness.addView(id);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(URL_BASE_PRODUCT_IMAGE)
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName, HttpServletRequest request)  {
        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);

            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return new ResponseEntity("File no found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(URL_BASE_DELETE)
    ResponseEntity<Object> removeProduct(@PathVariable Long id, Principal principal) {
        try {
            ProductEntity pe = productBusiness.load(id);
            if(principal.getName().equals(pe.getUser().getUsername())){
                productBusiness.remove(id);
            } else{
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
