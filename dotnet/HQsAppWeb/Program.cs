using HQsAppWeb.Data;
using HQsAppWeb.Services;
using HQsAppWeb.Services.Memory;
using Microsoft.EntityFrameworkCore;
using NToastNotify;
using Microsoft.AspNetCore.Identity;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages(options =>
{    
    options.Conventions.AuthorizeFolder("/Editoras");
    options.Conventions.AuthorizeFolder("/Categorias");
}).AddNToastNotifyToastr(new ToastrOptions()
                {
                    TimeOut = 5000,
                    ProgressBar = true,
                    PositionClass = ToastPositions.BottomRight
                });

builder.Services.AddSingleton<IHQService, HQService>();
builder.Services.AddDbContext<ComicShopDbContext>();

builder.Services.AddDefaultIdentity<IdentityUser>(options => 
options.SignIn.RequireConfirmedAccount = false)
    .AddEntityFrameworkStores<ComicShopDbContext>();

builder.Services.Configure<IdentityOptions>(options =>
{
    options.Password.RequireDigit = false;
    options.Password.RequireLowercase = false;
    options.Password.RequireNonAlphanumeric = false;
    options.Password.RequireUppercase = false;
    options.Password.RequiredLength = 3;

    options.Lockout.MaxFailedAccessAttempts = 30;
    options.Lockout.AllowedForNewUsers = true;

    options.User.RequireUniqueEmail = true;
});


var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");  
    app.UseHsts();
}

// var context = new ComicShopDbContext();
// context.Database.Migrate();

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.UseAuthentication();

app.UseAuthorization();

app.UseNToastNotify();

app.MapRazorPages();

app.Run();
