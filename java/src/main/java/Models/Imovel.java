package Models;

import Exceptions.ImovelException;
import java.util.ArrayList;
import java.util.List;

public class Imovel {
    private String registro;
    private String nome;
    private Endereco endereco;
    private TipoImovelEnum tipo;
    private float valorAluguel;
    private ArrayList<ContratoAluguel> listContratos;

    public Imovel(String registro, String nome, Endereco endereco, TipoImovelEnum tipo, float valorAluguel, ArrayList<ContratoAluguel> listContratos) throws ImovelException {
        setRegistro(registro);
        setNome(nome);
        setEndereco(endereco);
        setTipo(tipo);
        setValorAluguel(valorAluguel);
        this.listContratos = listContratos;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) throws ImovelException {
        // Remove espaços em branco antes e depois
        registro = registro.trim();

        // Remove caracteres não numéricos do registro
        registro = registro.replaceAll("[^0-9]", "");

        if (registro.isEmpty()) {
            throw new ImovelException("O registro não pode estar vazio após a validação.");
        }

        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoImovelEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovelEnum tipo) {
        this.tipo = tipo;
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) throws ImovelException {
        if (valorAluguel <= 0) {
            throw new ImovelException("O valor do aluguel deve ser maior que zero.");
        }
        this.valorAluguel = valorAluguel;
    }

    public List<ContratoAluguel> getListContratos() {
        return listContratos;
    }

    public void setListContratos(ArrayList<ContratoAluguel> listContratos) {
        this.listContratos = listContratos;
    }

    // Verifica a unicidade de imóveis
    public void validarImovelUnico(List<Imovel> listaImoveis) throws ImovelException {
        for (Imovel imovelExistente : listaImoveis) {
            if (imovelExistente != this) {
                Endereco enderecoExistente = imovelExistente.getEndereco();
                Endereco enderecoAtual = this.getEndereco();

                if (enderecoAtual.getLogradouro().equals(enderecoExistente.getLogradouro()) &&
                        enderecoAtual.getTipoLogradouro().equals(enderecoExistente.getTipoLogradouro()) &&
                        enderecoAtual.getNumero().equals(enderecoExistente.getNumero()) &&
                        enderecoAtual.getComplemento().equals(enderecoExistente.getComplemento()) &&
                        enderecoAtual.getCidade().equals(enderecoExistente.getCidade()) &&
                        enderecoAtual.getEstado().equals(enderecoExistente.getEstado()) &&
                        enderecoAtual.getCep().equals(enderecoExistente.getCep())) {
                    throw new ImovelException("Já existe um imóvel com o mesmo endereço.");
                }

                if (registro.equals(imovelExistente.getRegistro())) {
                    throw new ImovelException("Já existe um imóvel com o mesmo registro.");
                }
            }
        }
    }
}
