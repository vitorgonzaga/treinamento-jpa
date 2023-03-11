package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto {

    private String marca;
    private String modelo;

    public Informatica() {
    }

    public Informatica(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getAutor() {
        return marca;
    }

    public void setAutor(String marca) {
        this.marca = marca;
    }

    public String getNumeroDePaginas() {
        return modelo;
    }

    public void setNumeroDePaginas(String modelo) {
        this.modelo = modelo;
    }
}
