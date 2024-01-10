package Models;

import java.util.List;

import Exceptions.ClienteException;

public class Juridica extends Cliente {
    private String cnpj;

    public Juridica(String nome, String telefone, String email, List<ContratoAluguel> listaContratos, String cnpj) throws ClienteException {
        super(nome, telefone, email, listaContratos);
        setCnpj(cnpj); // Chama o método de validação do CNPJ
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws ClienteException {
        // Remove caracteres especiais e espaços, mantendo apenas os dígitos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            throw new ClienteException("CNPJ do cliente deve conter exatamente 14 dígitos numéricos.");
        }

        this.cnpj = cnpj;
    }
}
