package com.teste.idbrasil.feign;

import com.teste.idbrasil.feign.json.TaxaJson;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "selicClient")
public interface SelicClient {

    @RequestLine("GET dados?formato=json&dataInicial={dataInicial}&dataFinal={dataFinal}")
    @Headers({"Content-Type: multipart/form-data"})
    List<TaxaJson> busca(@Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal);
}

