using HQsAppWeb.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace HQsAppWeb.Data;

public class ComicShopDbContext : IdentityDbContext
{
    public DbSet<HQ> HQ { get; set; }
    public DbSet<Editora> Editora { get; set; }
    public DbSet<Categoria> Categoria { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        var config = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();

        var stringConn = config.GetConnectionString("StringConn");

        optionsBuilder.UseSqlServer(stringConn);
    }

    /*protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        // chaves primárias e relacionamentos para mexer depois.        
        modelBuilder.Entity<HQ>()
            .HasOne(hq => hq.Editora)
            .WithMany(editora => editora.HQs)
            .HasForeignKey(hq => hq.EditoraId);
    }*/
}
