using HQsAppWeb.Models;
using Microsoft.EntityFrameworkCore.Migrations;
using System.Text.RegularExpressions;

#nullable disable

namespace HQsAppWeb.Data.Migrations
{
    /// <inheritdoc />
    public partial class AdicionarTabelaEditora : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            var context = new ComicShopDbContext();
            context.Editora.AddRange(ObterCargaInicialEditora());
            context.SaveChanges();
        }

        private IList<Editora> ObterCargaInicialEditora()
        {
            return new List<Editora>()
            {
                new Editora() { Descricao = "Marvel" },
                new Editora() { Descricao = "DC Comics" },
                new Editora() { Descricao = "Vertigo" },
            };
        }        
    }
}
