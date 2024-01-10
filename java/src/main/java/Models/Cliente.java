package Models;
import Exceptions.ClienteException;
import Exceptions.ContratoAluguelException;
import java.util.ArrayList;
import java.util.List;


public class Cliente implements Contabil {
    private String nome;
    private String telefone;
    private String email;
    private List<ContratoAluguel> listaContratos;

    public Cliente(String nome, String telefone, String email, List<ContratoAluguel> listaContratos) throws ClienteException {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        this.listaContratos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ClienteException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ClienteException("Nome do cliente não pode ser vazio.");
        }

        // Verifica se o nome contém apenas letras e espaços
        if (!nome.matches("^[a-zA-Z\\s]+$")) {
            throw new ClienteException("Nome do cliente deve conter apenas letras e espaços.");
        }

        this.nome = nome.trim(); // Remove espaços antes e depois do nome
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ClienteException {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new ClienteException("Telefone do cliente não pode ser vazio.");
        }

        // Remove espaços do telefone
        telefone = telefone.replaceAll("\\s", "");

        // Validação do telefone: pelo menos 10 dígitos numéricos
        if (!telefone.matches("^\\d{10,}$")) {
            throw new ClienteException("Telefone do cliente é inválido. Deve conter pelo menos 10 dígitos numéricos.");
        }

        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ClienteException {
        if (email == null || email.trim().isEmpty()) {
            throw new ClienteException("Email do cliente não pode ser vazio.");
        }

        // Remove espaços do email
        email = email.replaceAll("\\s", "");

        // Validação do email: deve conter "@" e um domínio com ponto
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ClienteException("Email do cliente é inválido.");
        }

        this.email = email;
    }

    public List<ContratoAluguel> getListaContratos() {
        return listaContratos;
    }

    public void adicionarContrato(ContratoAluguel contrato) throws ContratoAluguelException {
        for (ContratoAluguel contratoExistente : listaContratos) {
            if (!contratoExistente.equals(contrato) && contratoExistente.getCliente().equals(this)) {
                throw new ContratoAluguelException("Erro: O cliente já possui outro contrato vigente.");
            }
            
            // Verifica se o registro do imóvel já existe na lista de contratos do cliente
            if (contratoExistente.getImovel().getRegistro().equals(contrato.getImovel().getRegistro())) {
                throw new ContratoAluguelException("Erro: Este registro de imóvel já está associado a um contrato do cliente.");
            }
        }

        listaContratos.add(contrato);
    }

    public void removerContrato(ContratoAluguel contrato) {
        listaContratos.remove(contrato);
    }

    @Override
    public float calcularValorTotalContratos() {
        float totalContratos = 0;
        for (ContratoAluguel contrato : listaContratos) {
            if (!contrato.contratoVencido()) {
                totalContratos += contrato.calcularValorContrato();
            }
        }

        // Aplicar desconto de 5% se o cliente tiver mais de 3 contratos
        if (listaContratos.size() > 3) {
            totalContratos *= 0.95f;
        }

        // Aplicar desconto adicional de 5% se o cliente tiver mais de 5 contratos
        if (listaContratos.size() > 5) {
            totalContratos *= 0.95f;
        }

        return totalContratos;
    }
}
