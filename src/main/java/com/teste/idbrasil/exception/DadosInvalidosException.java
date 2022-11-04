package com.teste.idbrasil.exception;

public class DadosInvalidosException extends RuntimeException {

    public DadosInvalidosException() {
        super("Dados inválidos. Insira corretamente");
    }
}
