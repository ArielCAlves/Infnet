using HQsAppWeb.Models;
using HQsAppWeb.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace HQsAppWeb.Pages
{
    [Authorize]
    public class CreateModel : PageModel
    {
        public SelectList EditoraOptionItems { get; set; }
        public SelectList CategoriaOptionItems { get; set; }
        private IHQService _service;

        public CreateModel(IHQService hqservice)
        {
            _service = hqservice;
        }

        public void OnGet()
        {
            EditoraOptionItems = new SelectList(_service.ObterTodasEditoras(),
                                    nameof(Editora.EditoraId),
                                    nameof(Editora.Descricao));

            CategoriaOptionItems = new SelectList(_service.ObterTodasCategorias(),
                                    nameof(Categoria.CategoriaId),
                                    nameof(Categoria.Descricao));

        }

        [BindProperty]
        public HQ HQ { get; set; }

        [BindProperty]
        public IList<int> CategoriaIds { get; set; }

        public IActionResult OnPost()
        {
            HQ.Categorias = _service.ObterTodasCategorias()
                                    .Where(item => CategoriaIds.Contains(item.CategoriaId))
                                    .ToList();

            if (!ModelState.IsValid)
            {
                return Page();
            }

            _service.Incluir(HQ);

            TempData["TempMensagemSucesso"] = true;

            return RedirectToPage("/Index");
        }
    }
}