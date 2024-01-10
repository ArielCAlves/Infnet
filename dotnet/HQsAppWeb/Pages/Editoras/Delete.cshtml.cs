using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using HQsAppWeb.Data;
using HQsAppWeb.Models;

namespace HQsAppWeb.Pages.Editoras
{
    public class DeleteModel : PageModel
    {
        private readonly HQsAppWeb.Data.ComicShopDbContext _context;

        public DeleteModel(HQsAppWeb.Data.ComicShopDbContext context)
        {
            _context = context;
        }

        [BindProperty]
      public Editora Editora { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Editora == null)
            {
                return NotFound();
            }

            var editora = await _context.Editora.FirstOrDefaultAsync(m => m.EditoraId == id);

            if (editora == null)
            {
                return NotFound();
            }
            else 
            {
                Editora = editora;
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.Editora == null)
            {
                return NotFound();
            }
            var editora = await _context.Editora.FindAsync(id);

            if (editora != null)
            {
                Editora = editora;
                _context.Editora.Remove(Editora);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
