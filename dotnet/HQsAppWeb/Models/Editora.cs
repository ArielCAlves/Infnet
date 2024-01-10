using System.ComponentModel.DataAnnotations.Schema;

namespace HQsAppWeb.Models;

public class Editora
{
    public int EditoraId { get; set; }
    public string Descricao { get; set; }

    public ICollection<HQ>? HQs { get; set; }
}
