using HQsAppWeb.Models;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace HQsAppWeb.Data.Migrations
{
    /// <inheritdoc />
    public partial class AdicionarTabelaCategoria : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<string>(
                name: "Descricao",
                table: "Categoria",
                type: "nvarchar(max)",
                nullable: false,
                oldClrType: typeof(string),
                oldType: "nvarchar(50)",
                oldMaxLength: 50);

        }
        private IList<Categoria> ObterCargaInicialEditora()
        {
            return new List<Categoria>()
            {
                new Categoria() { Descricao = "Ação" },
                new Categoria() { Descricao = "Fantasia" },
                new Categoria() { Descricao = "Romance" },
            };
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Categoria");
        }
    }
}
