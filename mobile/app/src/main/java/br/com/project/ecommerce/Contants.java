package br.com.project.ecommerce;

public enum Contants {
    URL_BASE("http://localhost:80");

    private final String value;

    Contants(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
