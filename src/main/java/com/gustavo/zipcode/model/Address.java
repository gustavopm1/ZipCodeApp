package com.gustavo.zipcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String cep;
    private String tipoCep;
    private String subTipoCep;
    private String uf;
    private String cidade;
    private String bairro;
    private String endereco;
    private String complemento;
    private String codigoIBGE;

}
