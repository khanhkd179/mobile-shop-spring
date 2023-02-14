package com.ecommercetest.service.impl;

import com.ecommercetest.dto.ProductDto;
import com.ecommercetest.entity.Product;
import com.ecommercetest.repository.ProductRepository;
import com.ecommercetest.service.ProductService;
import com.ecommercetest.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<ProductDto> findAll() {
        List<Product> list= repo.findAll();
        List<ProductDto> dtoList=new ArrayList<>();
        for(Product p: list){
            ProductDto productDto=new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setDescription(p.getDescription());
            productDto.setPrice(p.getPrice());
            productDto.setBrand(p.getBrand());
            productDto.setImage(p.getImage());
            productDto.setActivated(p.is_activated());
            productDto.setAvailableQuantity(p.getAvailableQuantity());
            dtoList.add(productDto);
        }
        return dtoList;
    }
    @Override
    public Product findById(Long id){
        return repo.findById(id).get();
    }

    @Override
    public Product save(ProductDto productDto, MultipartFile imageProduct) {
        try{
            Product product=new Product();
            if(imageProduct==null){
                product.setImage(null);
            }else{
                if(imageUpload.uploadImage(imageProduct)){
                    System.out.println("Upload successfully");
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.set_activated(productDto.isActivated());
            product.setName(productDto.getName());
            product.setAvailableQuantity(productDto.getAvailableQuantity());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setBrand(productDto.getBrand());
            return repo.save(product);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<Product> findAllByBrandName(String name){

        return repo.findAllByBrandName(name);
    }

    @Override
    public ProductDto getById(Long id) {
        ProductDto productDto=new ProductDto();
        Product p=repo.findById(id).get();
        productDto.setId(p.getId());
        productDto.setName(p.getName());
        productDto.setDescription(p.getDescription());
        productDto.setPrice(p.getPrice());
        productDto.setBrand(p.getBrand());
        productDto.setImage(p.getImage());
        productDto.setActivated(p.is_activated());
        productDto.setAvailableQuantity(p.getAvailableQuantity());
        return productDto;
    }

    @Override
    public Product updateProduct(MultipartFile imageProduct,ProductDto productDto) {
        try{
            Product product=findById(productDto.getId());
            if(imageProduct == null){
                product.setImage(product.getImage());
            }else{
                if(imageUpload.checkExisted(imageProduct) == false){
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.set_activated(product.is_activated());
            product.setName(productDto.getName());
            product.setAvailableQuantity(productDto.getAvailableQuantity());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setBrand(productDto.getBrand());
            return repo.save(product);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Product changeStatus(Long id) {
        Product product=findById(id);
        if(product.is_activated()) product.set_activated(false);
        else product.set_activated(true);
        return repo.save(product);
    }
}
