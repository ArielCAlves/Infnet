using HQsAppWeb.Models;

namespace HQsAppWeb.Services;

public interface IHQService
{
    IList<HQ> ObterTodos();
    HQ Obter(int id);
    void Incluir(HQ hq);
    void Alterar(HQ hq);
    void Excluir(int id);
    IList<Editora> ObterTodasEditoras();
    Editora ObterEditora(int id);
    IList<Categoria> ObterTodasCategorias();
}
