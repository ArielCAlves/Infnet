using System.ComponentModel.DataAnnotations.Schema;

namespace HQsAppWeb.Models;

public class Categoria
{

    public int CategoriaId { get; set; }
    public string Descricao { get; set; }
    public ICollection<HQ>? HQs { get; set; }
}
