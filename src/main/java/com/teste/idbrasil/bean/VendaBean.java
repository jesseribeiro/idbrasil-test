package com.teste.idbrasil.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Venda")
public class VendaBean extends GenericBean {

    @NotNull
    private BigDecimal price;

    private Integer installments;

    @JoinColumn(name = "produto_id")
    @ManyToOne
    private ProductBean produto;
}
