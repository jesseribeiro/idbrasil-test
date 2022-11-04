package com.teste.idbrasil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentsDefinitionDTO {

    private Integer installmentsNum;
    private BigDecimal price;
    private BigDecimal tax;
}
