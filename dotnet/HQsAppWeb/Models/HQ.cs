using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace HQsAppWeb.Models;

public class HQ
{
    public int HQId { get; set; }

    [Required(ErrorMessage = "Campo 'Titulo' obrigatório.")]
    [StringLength(50, MinimumLength = 5, ErrorMessage = "Campo 'Titulo' deve ter entre 5 e 50 caracteres.")]
    public string Titulo { get; set; }
    public string NomeSlug => Titulo?.ToLower().Replace(" ", "-");

    [Required(ErrorMessage = "Campo 'Descrição' obrigatório.")]
    [StringLength(300, MinimumLength = 20, ErrorMessage = "Campo 'Descrição' deve ter entre 20 e 200 caracteres.")]
    [Display(Name = "Descrição")]
    public string Descricao { get; set; }

    [Required(ErrorMessage = "Campo 'Caminho URL da imagem' obrigatório.")]
    [Display(Name = "Caminho URL da image")]
    public string ImagemUri { get; set; }

    [Required(ErrorMessage = "Campo 'Preço' obrigatório.")]
    [Display(Name = "Preço")]
    [DataType(DataType.Currency)]
    public double Preco { get; set; }

    [Display(Name = "Versão Digital")]
    public bool EntregaExpressa { get; set; }
    public string EntregaExpressaFormatada => EntregaExpressa ? "Sim" : "Não";

    [Required(ErrorMessage = "Campo 'Disponível em' obrigatório.")]
    [Display(Name = "Disponível em")]
    [DisplayFormat(DataFormatString = "{0:MMMM \\de yyyy}")]
    [DataType("month")]
    public DateTime DataCadastro { get; set; }

    [Display(Name = "Editora")]
    public int? EditoraId { get; set; }

    public ICollection<Categoria>? Categorias { get; set; }
}