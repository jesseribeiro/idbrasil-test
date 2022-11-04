package com.teste.idbrasil.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.teste.idbrasil.json.MoneyDeserializerJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    @NotNull
    @JsonDeserialize(converter = MoneyDeserializerJson.class)
    private BigDecimal entry;

    @NotBlank
    private Integer installments;
}
