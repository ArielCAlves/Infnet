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
    public class IndexModel : PageModel
    {
        private readonly HQsAppWeb.Data.ComicShopDbContext _context;

        public IndexModel(HQsAppWeb.Data.ComicShopDbContext context)
        {
            _context = context;
        }

        public IList<Editora> Editora { get;set; } = default!;

        public async Task OnGetAsync()
        {
            if (_context.Editora != null)
            {
                Editora = await _context.Editora.ToListAsync();
            }
        }
    }
}
