package br.com.guilherme.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "fullName", "adress", "cpf"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonProperty("fullName")
    private String name;
    private String adress;
    private String cpf;

    public PersonVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
        if (!super.equals(o)) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(key, personVO.key) && Objects.equals(name, personVO.name) && Objects.equals(adress, personVO.adress) && Objects.equals(cpf, personVO.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, name, adress, cpf);
    }
}
