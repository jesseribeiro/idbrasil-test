package com.teste.idbrasil.feign.json;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaxaJson {

    private String data;
    private String valor;
}
