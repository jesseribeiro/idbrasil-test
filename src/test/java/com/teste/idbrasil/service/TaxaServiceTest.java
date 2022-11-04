package com.teste.idbrasil.service;

import com.teste.idbrasil.feign.SelicClient;
import com.teste.idbrasil.utils.DateUtils;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class TaxaServiceTest {

    @Autowired
    private TaxaService taxaService;

    private SelicClient selicClient;

    @Test
    public void getTestTaxa() {
        Calendar dataInicial = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();

       Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(SelicClient.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(600000, 500000))
                .target(SelicClient.class, "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/")
                .busca(DateUtils.getDiaMesAnoPortugues(dataInicial), DateUtils.getDiaMesAnoPortugues(dataFinal));

        Assert.assertTrue(true);
    }
}
