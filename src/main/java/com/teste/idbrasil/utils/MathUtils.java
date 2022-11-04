package com.teste.idbrasil.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class MathUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));

    public static BigDecimal convertStringToBigDecimal(String value) {
        try {
            return new BigDecimal(decimalFormat.parseObject(value).toString()).setScale(2);
        } catch (ParseException e) {
            throw new RuntimeException("Não foi possível converter o valor informado para um BigDecimal");
        }
    }

    public static BigDecimal getValorParcela(BigDecimal price, Integer installments) {
        BigDecimal valorParcela = price.divide(new BigDecimal(installments), 2, BigDecimal.ROUND_HALF_EVEN);
        return valorParcela;
    }

    public static BigDecimal getValorTaxa(BigDecimal parcela, BigDecimal taxa) {
        BigDecimal valorTaxa = parcela.multiply(taxa).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return valorTaxa;
    }
}
