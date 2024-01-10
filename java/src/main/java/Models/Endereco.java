package Models;

import Exceptions.EnderecoException;

public class Endereco {
    private String logradouro;
    private String tipoLogradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, String tipoLogradouro, String numero, String complemento, String cidade, String estado, String cep) throws EnderecoException {
        setLogradouro(logradouro);
        setTipoLogradouro(tipoLogradouro);
        setNumero(numero);
        setComplemento(complemento);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public void setLogradouro(String logradouro) throws EnderecoException {
        // Remove espaços em branco antes e depois do logradouro
        logradouro = logradouro.trim();

        if (logradouro.isEmpty()) {
            throw new EnderecoException("Logradouro não pode ser vazio.");
        }

        this.logradouro = logradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) throws EnderecoException {
        // Remove espaços em branco antes e depois do tipo de logradouro
        tipoLogradouro = tipoLogradouro.trim();

        if (tipoLogradouro.isEmpty()) {
            throw new EnderecoException("Tipo de logradouro não pode ser vazio.");
        }

        this.tipoLogradouro = tipoLogradouro;
    }

    public void setNumero(String numero) throws EnderecoException {
        // Remove espaços em branco antes e depois do número
        numero = numero.trim();

        if (numero.isEmpty()) {
            throw new EnderecoException("Número não pode ser vazio.");
        }

        this.numero = numero;
    }

    public void setComplemento(String complemento) throws EnderecoException {
        // Remove espaços em branco antes e depois do complemento
        complemento = complemento.trim();

        this.complemento = complemento;
    }

    public void setCidade(String cidade) throws EnderecoException {
        // Remove espaços em branco antes e depois da cidade
        cidade = cidade.trim();

        if (cidade.isEmpty()) {
            throw new EnderecoException("Cidade não pode ser vazia.");
        }

        this.cidade = cidade;
    }

    public void setEstado(String estado) throws EnderecoException {
        // Remove espaços em branco antes e depois do estado
        estado = estado.trim();

        if (estado.isEmpty()) {
            throw new EnderecoException("Estado não pode ser vazio.");
        }

        this.estado = estado;
    }

    public void setCep(String cep) throws EnderecoException {
        // Remove espaços em branco antes e depois do CEP
        cep = cep.trim();

        // Remove caracteres não numéricos do CEP
        cep = cep.replaceAll("[^0-9]", "");

        // Garante que o CEP tenha 8 dígitos numéricos
        if (cep.length() != 8) {
            throw new EnderecoException("CEP deve conter exatamente 8 dígitos numéricos.");
        }

        this.cep = cep;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %s, %s %s", tipoLogradouro, logradouro, numero, complemento, cidade, estado, cep);
    }
}
