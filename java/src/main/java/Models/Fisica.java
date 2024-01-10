package Models;

import java.util.List;

import Exceptions.ClienteException;

public class Fisica extends Cliente {
    private String cpf;

    public Fisica(String nome, String telefone, String email, List<ContratoAluguel> listaContratos, String cpf) throws ClienteException {
        super(nome, telefone, email, listaContratos);
        setCpf(cpf); // Chama o método de validação do CPF
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ClienteException {
        // Remove caracteres especiais e espaços, mantendo apenas os dígitos
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new ClienteException("CPF do cliente deve conter exatamente 11 dígitos numéricos.");
        }

        this.cpf = cpf;
    }
}
