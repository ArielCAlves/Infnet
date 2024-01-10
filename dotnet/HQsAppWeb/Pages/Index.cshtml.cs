using Microsoft.AspNetCore.Mvc.RazorPages;
using HQsAppWeb.Models;
using HQsAppWeb.Services;

namespace HQsAppWeb.Pages;

public class IndexModel : PageModel
{
    private IHQService _service;

    public string StringConexao { get; set; }

    public IndexModel(IHQService service, IConfiguration configuration)
    {
        _service = service;
        StringConexao = configuration.GetConnectionString("StringConn");
    }

    public IList<HQ> ListaHQ { get; private set; }

    public void OnGet()
    {
        ViewData["Title"] = "Home page";

        ListaHQ = _service.ObterTodos();
    }
}
