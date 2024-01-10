using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using HQsAppWeb.Models;
using HQsAppWeb.Services;
using NToastNotify;
using Microsoft.AspNetCore.Authorization;

namespace HQsAppWeb.Pages
{
    [Authorize]
    public class EditModel : PageModel
    {
        public SelectList EditoraOptionItems { get; set; }
        public SelectList CategoriaOptionItems { get; set; }
        private IHQService _service;
        private IToastNotification _toastNotification;

        public EditModel(IHQService service,
                         IToastNotification toastNotification)
        {
            _service = service;
            _toastNotification = toastNotification;
        }

        [BindProperty]
        public HQ HQ { get; set; }

        [BindProperty]
        public IList<int> CategoriaIds { get; set; }

        public IActionResult OnGet(int id)
        {
            HQ = _service.Obter(id);

            CategoriaIds = HQ.Categorias.Select(item => item.CategoriaId).ToList();

            EditoraOptionItems = new SelectList(_service.ObterTodasEditoras(),
            nameof(Editora.EditoraId),
                                                nameof(Editora.Descricao));

            CategoriaOptionItems = new SelectList(_service.ObterTodasCategorias(),
                                    nameof(Categoria.CategoriaId),
                                    nameof(Categoria.Descricao));

            if (HQ == null)
            {
                return NotFound();
            }

            return Page();
        }

        public IActionResult OnPost()
        {
            HQ.Categorias = _service.ObterTodasCategorias()
                                            .Where(item => CategoriaIds.Contains(item.CategoriaId))
                                            .ToList();

            if (!ModelState.IsValid)
            {
                return Page();
            }

            //alteração
            _service.Alterar(HQ);

            _toastNotification.AddSuccessToastMessage("Operação realizada com sucesso!");

            return RedirectToPage("/Index");
        }

        public IActionResult OnPostExclusao()
        {
            //exclusão
            _service.Excluir(HQ.HQId);

            _toastNotification.AddSuccessToastMessage("Operação realizada com sucesso!");

            return RedirectToPage("/Index");
        }
    }
}
