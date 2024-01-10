using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace HQsAppWeb.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Categoria",
                columns: table => new
                {
                    CategoriaId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Descricao = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Categoria", x => x.CategoriaId);
                });

            migrationBuilder.CreateTable(
                name: "Editora",
                columns: table => new
                {
                    EditoraId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Descricao = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Editora", x => x.EditoraId);
                });

            migrationBuilder.CreateTable(
                name: "HQ",
                columns: table => new
                {
                    HQId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Titulo = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    Descricao = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false),
                    ImagemUri = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Preco = table.Column<double>(type: "float", nullable: false),
                    EntregaExpressa = table.Column<bool>(type: "bit", nullable: false),
                    DataCadastro = table.Column<DateTime>(type: "datetime2", nullable: false),
                    EditoraId = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_HQ", x => x.HQId);
                    table.ForeignKey(
                        name: "FK_HQ_Editora_EditoraId",
                        column: x => x.EditoraId,
                        principalTable: "Editora",
                        principalColumn: "EditoraId");
                });

            migrationBuilder.CreateTable(
                name: "CategoriaHQ",
                columns: table => new
                {
                    CategoriasCategoriaId = table.Column<int>(type: "int", nullable: false),
                    HQsHQId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CategoriaHQ", x => new { x.CategoriasCategoriaId, x.HQsHQId });
                    table.ForeignKey(
                        name: "FK_CategoriaHQ_Categoria_CategoriasCategoriaId",
                        column: x => x.CategoriasCategoriaId,
                        principalTable: "Categoria",
                        principalColumn: "CategoriaId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_CategoriaHQ_HQ_HQsHQId",
                        column: x => x.HQsHQId,
                        principalTable: "HQ",
                        principalColumn: "HQId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CategoriaHQ_HQsHQId",
                table: "CategoriaHQ",
                column: "HQsHQId");

            migrationBuilder.CreateIndex(
                name: "IX_HQ_EditoraId",
                table: "HQ",
                column: "EditoraId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CategoriaHQ");

            migrationBuilder.DropTable(
                name: "Categoria");

            migrationBuilder.DropTable(
                name: "HQ");

            migrationBuilder.DropTable(
                name: "Editora");
        }
    }
}
