package br.com.guilherme.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "fullName", "adress", "cpf"})
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("fullName")
    private String name;
    private String adress;
    private String cpf;

    public PersonVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id) && Objects.equals(name, personVO.name) && Objects.equals(adress, personVO.adress) && Objects.equals(cpf, personVO.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, adress, cpf);
    }
}
