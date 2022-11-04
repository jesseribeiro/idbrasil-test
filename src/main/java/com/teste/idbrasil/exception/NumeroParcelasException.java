package com.teste.idbrasil.exception;

public class NumeroParcelasException extends RuntimeException {

    public NumeroParcelasException() {
        super("Número de parcelas inválido. Insira outro valor!");
    }
}
