package com.teste.idbrasil.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.teste.idbrasil.utils.MathUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyDeserializerJson extends StdConverter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String value) {
        if(value == null || value.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal bd = MathUtils.convertStringToBigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}
