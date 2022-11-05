package com.teste.idbrasil.service;

import com.teste.idbrasil.bean.ProductBean;
import com.teste.idbrasil.bean.VendaBean;
import com.teste.idbrasil.dto.InstallmentsDefinitionDTO;
import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.dto.TaxaDTO;
import com.teste.idbrasil.exception.DadosInvalidosException;
import com.teste.idbrasil.exception.NumeroParcelasException;
import com.teste.idbrasil.exception.SemValorException;
import com.teste.idbrasil.repository.VendaRepository;
import com.teste.idbrasil.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VendaService extends GenericService<VendaBean, VendaRepository> {

    @Autowired
    ProductService productService;

    @Autowired
    TaxaService taxaService;

    @Transactional
    public List<InstallmentsDefinitionDTO> calcularVender(ProductDTO productDTO, PaymentDTO paymentDTO) {

        if (productDTO == null || paymentDTO == null) {
            throw new DadosInvalidosException();
        }

        if (productDTO.getPrice() == null || productDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new SemValorException();
        }

        if (paymentDTO.getInstallments() == null || paymentDTO.getInstallments() < 0) {
            throw new NumeroParcelasException();
        }

        ProductBean product = productService.returnProduct(productDTO);

        saveVenda(productDTO, product);

        BigDecimal tax;
        if (paymentDTO.getInstallments() > 6) {
            List<TaxaDTO> taxas = taxaService.getTaxaSelic();
            TaxaDTO taxaUtilizada = taxas.get(taxas.size() - 1);
            tax = taxaUtilizada.getValor();
        } else {
            tax = BigDecimal.ZERO;
        }

        BigDecimal valorFaltante = productDTO.getPrice();

        if (paymentDTO.getEntry() != null && paymentDTO.getEntry().compareTo(BigDecimal.ZERO) > 0) {
            valorFaltante = productDTO.getPrice().subtract(paymentDTO.getEntry());
        }

        BigDecimal valorParcela = valorFaltante;
        if (paymentDTO.getInstallments() > 0) {
            valorParcela = MathUtils.getValorParcela(valorFaltante, paymentDTO.getInstallments());
        }

        List<InstallmentsDefinitionDTO> listaParcelas = getInstallments(paymentDTO, tax, valorFaltante, valorParcela);

        return listaParcelas;
    }

    private static List<InstallmentsDefinitionDTO> getInstallments(PaymentDTO paymentDTO, BigDecimal tax, BigDecimal valorFaltante, BigDecimal valorParcela) {
        List<InstallmentsDefinitionDTO> listaParcelas = new ArrayList<>();

        // -- entrada é definida como parcela número 0 --
        if (paymentDTO.getEntry() != null && paymentDTO.getEntry().compareTo(BigDecimal.ZERO) > 0) {
            InstallmentsDefinitionDTO parcela = new InstallmentsDefinitionDTO();
            parcela.setPrice(paymentDTO.getEntry());
            parcela.setTax(BigDecimal.ZERO);
            parcela.setInstallmentsNum(0);
            listaParcelas.add(parcela);
        }

        // -- se número de parcelas for igual a 0 --
        // -- verifica se existe diferença entre o preço e o valor da entrada --

        if (paymentDTO.getInstallments() == 0) {
            if (valorFaltante.compareTo(BigDecimal.ZERO) > 0) {
                InstallmentsDefinitionDTO parcela = new InstallmentsDefinitionDTO();
                parcela.setPrice(valorFaltante);
                parcela.setTax(BigDecimal.ZERO);
                parcela.setInstallmentsNum(1);
                listaParcelas.add(parcela);
            }
        } else {
            for (int x = 1; x <= paymentDTO.getInstallments(); x++) {
                InstallmentsDefinitionDTO parcela = new InstallmentsDefinitionDTO();
                parcela.setPrice(valorParcela);

                // -- verifica se existe taxa para a parcela e converte a taxa em reais --
                // -- senão salva a taxa como 0 --
                if (tax.compareTo(BigDecimal.ZERO) > 0) {
                    parcela.setTax(MathUtils.getValorTaxa(valorParcela, tax));
                } else {
                    parcela.setTax(tax);
                }

                parcela.setInstallmentsNum(x);
                listaParcelas.add(parcela);
            }
        }
        return listaParcelas;
    }

    private void saveVenda(ProductDTO productDTO, ProductBean product) {
        VendaBean vendaBean = new VendaBean();
        vendaBean.setProduto(product);
        vendaBean.setPrice(productDTO.getPrice());
        save(vendaBean);
    }
}
