package Models;

import java.time.LocalDate;

import Exceptions.ContratoAluguelException;

public class ContratoAluguel {
    private Imovel imovel;
    private Cliente cliente;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    public ContratoAluguel(Imovel imovel, Cliente cliente, LocalDate dataInicio, LocalDate dataTermino) throws ContratoAluguelException {
        // Valida se os parâmetros não são nulos
        if (imovel == null || cliente == null || dataInicio == null || dataTermino == null) {
            throw new IllegalArgumentException("Nenhum dos parâmetros pode ser nulo.");
        }

        // Valida se a data de início não é posterior à data de término
        if (dataInicio.isAfter(dataTermino)) {
            throw new ContratoAluguelException("A data de início não pode ser posterior à data de término.");
        }

        // Valida se não há sobreposição de datas com outros contratos no mesmo imóvel
        for (ContratoAluguel contratoExistente : imovel.getListContratos()) {
            if (dataInicio.isBefore(contratoExistente.getDataTermino()) &&
                dataTermino.isAfter(contratoExistente.getDataInicio())) {
                throw new ContratoAluguelException("Erro: Sobreposição de datas com outro contrato no mesmo imóvel.");
            }
        }

        // Valida se o cliente já possui outro contrato vigente
        for (ContratoAluguel contratoExistente : cliente.getListaContratos()) {
            if (!contratoExistente.equals(this) && !contratoExistente.contratoVencido()) {
                throw new ContratoAluguelException("Erro: O cliente já possui outro contrato vigente.");
            }
        }

        this.imovel = imovel;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
    
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public float calcularValorContrato() {
        float valorAluguelMensal = imovel.getValorAluguel();
        long mesesContrato = dataInicio.until(dataTermino).toTotalMonths();
        return valorAluguelMensal * mesesContrato;
    }

    public boolean contratoVencido() {
        // O contrato está vencido se a data atual for após a data de término do contrato.
        return LocalDate.now().isAfter(dataTermino);
    }

    public String toCSV() {        
        String vencido = contratoVencido() ? "Sim" : "Não";
        return String.format(
            "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
            imovel.getRegistro(),
            imovel.getNome(),
            imovel.getEndereco().toString(),
            imovel.getTipo(),
            imovel.getValorAluguel(),
            cliente.getNome(),
            cliente.getTelefone(),
            dataInicio,
            dataTermino,
            vencido
        );
    }
}
