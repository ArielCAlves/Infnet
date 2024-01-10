using Microsoft.EntityFrameworkCore;
using HQsAppWeb.Models;
using HQsAppWeb.Data;


namespace HQsAppWeb.Services.Data;

public class HQService : IHQService
{
    private ComicShopDbContext _context;

    public HQService(ComicShopDbContext context)
    {
        _context = context;
    }

    public void Alterar(HQ hq)
    {
        var hqEncontrado = Obter(hq.HQId);
        hqEncontrado.Titulo = hq.Titulo;
        hqEncontrado.Descricao = hq.Descricao;
        hqEncontrado.Preco = hq.Preco;
        hqEncontrado.ImagemUri = hq.ImagemUri;
        hqEncontrado.EntregaExpressa = hq.EntregaExpressa;
        hqEncontrado.DataCadastro = hq.DataCadastro;
        hqEncontrado.EditoraId = hq.EditoraId;
        hqEncontrado.Categorias = hq.Categorias;

        _context.SaveChanges();
    }

    public void Excluir(int id)
    {
        var hqEncontrado = Obter(id);
        _context.HQ.Remove(hqEncontrado);
        _context.SaveChanges();
    }

    public void Incluir(HQ hq)
    {
        _context.HQ.Add(hq);
        _context.SaveChanges();
    }

    public HQ Obter(int id)
    {
        return _context.HQ
            .Include(item => item.Categorias)
            .SingleOrDefault(item => item.HQId == id);
    }

    public IList<HQ> ObterTodos()
    {
        return _context.HQ.ToList();
    }

    public IList<Editora> ObterTodasEditoras()
        => _context.Editora.ToList();

    public Editora ObterEditora(int id)
        => _context.Editora.SingleOrDefault(item => item.EditoraId == id);

    public IList<Categoria> ObterTodasCategorias()
        => _context.Categoria.ToList();
}