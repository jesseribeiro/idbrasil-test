package com.teste.idbrasil.service;

import com.teste.idbrasil.bean.ProductBean;
import com.teste.idbrasil.dto.InstallmentsDefinitionDTO;
import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.feign.SelicClient;
import com.teste.idbrasil.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService extends GenericService<ProductBean, ProductRepository> {

    public List<ProductBean> findAll() {
        return convertIterableToList(getRepository().findAll());
    }

    public ProductBean returnProduct (ProductDTO dto) {
        ProductBean product;
        if (!getRepository().ifExistsProduct(dto.getId())) {
            product = new ProductBean();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            save(product);
        } else {
            product = getById(dto.getId());
        }
        return  product;
    }
}


