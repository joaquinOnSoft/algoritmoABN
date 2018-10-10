package com.joaquinonsoft.algoritmoabn.operations;

public enum Operarion {
    SUM('+'),
    SUBSTRACTION('-'),
    DIVISON('*'),
    MULTIPLICATION('*');

    private final char op;
    Operarion(char op){
        this.op = op;
    }


    @Override
    public String toString() {
        return Character.toString(op);
    }
}
