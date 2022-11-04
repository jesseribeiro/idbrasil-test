package com.teste.idbrasil.service;

import com.teste.idbrasil.dto.TaxaDTO;
import com.teste.idbrasil.feign.SelicClient;
import com.teste.idbrasil.feign.json.TaxaJson;
import com.teste.idbrasil.utils.DateUtils;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class TaxaService {

    @Transactional
    public List<TaxaDTO> getTaxaSelic() {

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.add(Calendar.DATE, -30);
        Calendar dataFinal = Calendar.getInstance();

        List<TaxaJson> response = Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(SelicClient.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(600000, 500000))
                .target(SelicClient.class, "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/")
                .busca(DateUtils.getDiaMesAnoPortugues(dataInicial), DateUtils.getDiaMesAnoPortugues(dataFinal));

        List<TaxaDTO> listaTaxa = new ArrayList<>();
        for (TaxaJson json : response) {
            TaxaDTO dto = new TaxaDTO(json);
            listaTaxa.add(dto);
        }

        return listaTaxa;
    }
}
