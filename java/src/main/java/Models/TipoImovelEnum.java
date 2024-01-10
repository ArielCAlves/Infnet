package Models;

public enum TipoImovelEnum {
    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private String descricao;

    TipoImovelEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

