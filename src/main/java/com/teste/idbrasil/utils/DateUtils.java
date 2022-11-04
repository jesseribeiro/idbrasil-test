package com.teste.idbrasil.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Component("dateUtils")
public class DateUtils {
    public DateUtils() {
    }

    public static String getDiaMesAnoPortugues(Calendar date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date.getTime());
        } else {
            return null;
        }
    }
}
