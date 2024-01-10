using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using HQsAppWeb.Services;
using HQsAppWeb.Models;

namespace HQsAppWeb.Pages
{
    public class DetailsModel : PageModel
    {
        private IHQService _service;
        public string DescricaoEditora { get; set; }

        public DetailsModel(IHQService service)
        {
            _service = service;
        }

        public HQ HQ { get; private set; }

        public IActionResult OnGet(int id)
        {
            HQ = _service.Obter(id);
            if (HQ.EditoraId is not null)
            {
                DescricaoEditora = _service.ObterEditora(HQ.EditoraId.Value).Descricao;
            }

            if (HQ == null)
            {
                return NotFound();
            }

            return Page();
        }
    }
}
