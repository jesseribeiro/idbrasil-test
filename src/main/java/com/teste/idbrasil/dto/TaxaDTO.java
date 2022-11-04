package com.teste.idbrasil.dto;

import com.teste.idbrasil.feign.json.TaxaJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxaDTO {

    private String data;
    private BigDecimal valor;

    public TaxaDTO(TaxaJson json) {
        data = json.getData();
        valor = new BigDecimal(json.getValor());
    }
}
