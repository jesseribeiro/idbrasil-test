package com.teste.idbrasil.exception;

public class SemValorException extends RuntimeException {

    public SemValorException() {
        super("Insira um valor válido para a venda");
    }
}
