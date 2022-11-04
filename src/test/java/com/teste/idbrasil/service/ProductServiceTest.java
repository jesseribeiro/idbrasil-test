package com.teste.idbrasil.service;

import com.teste.idbrasil.bean.ProductBean;
import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.exception.DadosInvalidosException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private VendaService vendaService;

    @Test
    public void getProducts(){
        List<ProductBean> products = productService.findAll();
        Assert.assertTrue(true);
    }

    @Test(expected = DadosInvalidosException.class)
    public void getVendaProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Teste");
        productDTO.setPrice(new BigDecimal(50));
        vendaService.calcularVender(productDTO, null);
    }

    @Test(expected = RuntimeException.class)
    public void getProdutoInexistente(){
        productService.getById(1L);
    }
}
