using HQsAppWeb.Models;
using System.Text.RegularExpressions;

namespace HQsAppWeb.Services.Memory;

public class HQService : IHQService
{
    public HQService()
        => CarregarListaInicial();

    private IList<HQ> _hqs;

    private void CarregarListaInicial()
    {
        _hqs = new List<HQ>()
        {
            new HQ
            {
                HQId = 1,
                Titulo = "Superman Terra Um",
                Descricao = "A versão moderna da história do único sobrevivente do planeta Krypton e sua luta para salvar a humanidade das forças alienígenas que destruíram seu antigo lar.",
                ImagemUri = "/images/superman-terra-um.jpg",
                Preco = 22.90,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 2,
                Titulo = "Constantine",
                Descricao = "O solitário Constantine tenta garantir seu lugar no paraíso enviando demônios de volta ao inferno, mas seu destino está ligado ao de Angela, uma policial que investiga o suposto suicídio de sua irmã gêmea.",
                ImagemUri = "/images/constantine.jpg",
                Preco = 59.90,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 3,
                Titulo = "Spawn",
                Descricao = "O assassino do governor Al Simmons é morto depois de ser traído por seu chefe, Jason Wynn. Ao chegar no inferno, Simmons recebe uma oferta para retornar à Terra se ele estiver disposto a liderar um exército do mal.",
                ImagemUri = "/images/spawn.jpg",
                Preco = 179.90,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 4,
                Titulo = "Mascara",
                Descricao = "A vida do tímido e socialmente desajeitado bancário Stanley Ipkiss muda completamente quando ele encontra no mar uma máscara que possui o espírito do deus escandinavo Loki.",
                ImagemUri = "/images/mascara.jpg",
                Preco = 32.00,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 5,
                Titulo = "Homem-Aranha Superior",
                Descricao = "Peter Parker lutou a vida inteira para tornar o mundo um lugar melhor, mas sua história terminou de maneira dramática, com um sacrifício final. Agora, seu arqui-inimigo, o Dr. Octopus, tomou seu corpo, suas memórias e sua vida.",
                ImagemUri = "/images/homem-aranha.jpg",
                Preco = 18.90,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 6,
                Titulo = "Django & Zorro",
                Descricao = "A obra é ambientada anos depois da trama de Django Livre e mostra como o caçador de recompensas conheceu Diego de la Vega, vulgo Zorro. No enredo, Django acaba se tornando o guarda-costas de Zorro em uma missão que busca salvar povos indígenas da escravidão nos Estados Unidos.",
                ImagemUri = "/images/django-zorro.jpg",
                Preco = 43.00,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 7,
                Titulo = "Homem Aranha Noir",
                Descricao = "Nova York, 1933. Quatro anos depois da quebra da Bolsa de Valores de Nova York. Os Estados Unidos vivem a Grande Depressão. O Duende é o chefe de uma quadrilha de criminosos que domina a cidade pela corrupção e violência. Peter Parker vive sua adolescência com os tios Ben e May, um casal de socialistas fervorosos que luta para melhorar a sociedade e impedir a exploração dos mais humildes.",
                ImagemUri = "/images/homem-aranha-noir.jpg",
                Preco = 250.00,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 8,
                Titulo = "Franklin Richards - Filho de um gênio",
                Descricao = "Nasceu como mutante devido aos genes alterados pela radiação cósmica que deram poderes aos seus pais. Ele se mostrou um mutante atípico pois, diferente dos outros mutantes que manifestam seus poderes na puberdade, manifestou seus poderes extremamente cedo.",
                ImagemUri = "/images/franklin-richards.jpg",
                Preco = 26.90,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 9,
                Titulo = "MIB Homens de Preto",
                Descricao = "Uma agência ultra-secreta que monitora esses alienígenas, protege a Terra contra ameaças intergalácticas e usa neuralyzers (dispositivos) que apagam a memória para manter a atividade alienígena em segredo.",
                ImagemUri = "/images/mib.jpg",
                Preco = 120.00,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 10,
                Titulo = "Dexter",
                Descricao = "Dexter, o psicopata dos psicopatas, depois de fazer grande sucesso nos livros e na TV, agora invade as páginas dos quadrinhos. Nessa história completa, o devotado personagem deve lidar com um fantasma de seu passado: Steve Gonzalez, um valentão que perseguiu Dexter na época da escola.",
                ImagemUri = "/images/dexter.jpg",
                Preco = 19.90,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 11,
                Titulo = "X-Men Dinastia X",
                Descricao = "Xavier oferece à raça humana novas e milagrosas drogas que aumentam a expectativa de vida, que curam praticamente todas as enfermidades do corpo e as doenças da mente. Mas ele espera um pagamento adequado por este verdadeiro milagre.",
                ImagemUri = "/images/dinastia-x.jpg",
                Preco = 24.90,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 12,
                Titulo = "Inumanos",
                Descricao = "Inumanidade. Depois dos eventos de Infinito, em que há terrígena na atmosfera transformando a população que possui o ADN inumano no organismo, Raio Negro desaparece e Medusa reina sozinha, na cidade agora chamada de Nova Attilan, assumindo todas as responsabilidades e consequências das ações do seu marido.",
                ImagemUri = "/images/inumanos.jpg",
                Preco = 67.90,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 13,
                Titulo = "The Boys",
                Descricao = "The Boys se passa em um universo onde indivíduos superpoderosos são reconhecidos como heróis pelo público em geral e pertencem à poderosa corporação Vought International, que os comercializa e monetiza. Fora de suas personas heroicas, a maioria é arrogante e corrupta.",
                ImagemUri = "/images/the-boys.jpg",
                Preco = 59.99,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 14,
                Titulo = "Invincible Compendium, Volume 1",
                Descricao = "A trama segue um adolescente que é filho do super-herói mais poderoso do planeta. Ao começar a desenvolver seus próprios poderes, no entanto, ele descobre que a história da família pode ser mais sombria do que esperava.",
                ImagemUri = "/images/invincible.jpg",
                Preco = 149.90,
                EntregaExpressa = true,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 15,
                Titulo = "Ciclope",
                Descricao = "Ciclope foi um dos primeiros alunos do Professor X (o cérebro da equipe), fazendo parte da primeira formação dos X-Men. Ele é filho do Pirata Espacial, Corsário, e irmão de Alex Summers, o também herói Destrutor, e de Gabriel Summers, o Vulcano.",
                ImagemUri = "/images/ciclope.jpg",
                Preco = 39.00,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
            new HQ
            {
                HQId = 16,
                Titulo = "X-Men Noir",
                Descricao = "Nova York, 1937. Os chefões do submundo, os tubarões, os criminosos controlam a polícia, a igreja... Só não controlam os X-Men, um bando de garotos de uma escola de Westchester, dirigida por um psicólogo louco chamado Charles Xavier.",
                ImagemUri = "/images/xmen-noir.jpg",
                Preco = 37.00,
                EntregaExpressa = false,
                DataCadastro = DateTime.Now
            },
        };
    }

    public IList<HQ> ObterTodos()
        => _hqs;

    public HQ Obter(int id)
        => ObterTodos().SingleOrDefault(item => item.HQId == id);

    public void Incluir(HQ hq)
    {
        var proximoId = _hqs.Max(item => item.HQId) + 1;
        hq.HQId = proximoId;
        _hqs.Add(hq);
    }

    public void Alterar(HQ hq)
    {
        var hqEncontrado = _hqs.SingleOrDefault(item => item.HQId == hq.HQId);
        hqEncontrado.Titulo = hq.Titulo;
        hqEncontrado.Descricao = hq.Descricao;
        hqEncontrado.Preco = hq.Preco;
        hqEncontrado.EntregaExpressa = hq.EntregaExpressa;
        hqEncontrado.DataCadastro = hq.DataCadastro;
        hqEncontrado.ImagemUri = hq.ImagemUri;
    }

    public void Excluir(int id)
    {
        var hqEncontrado = Obter(id);
        _hqs.Remove(hqEncontrado);
    }

    public IList<Editora> ObterTodasEditoras()
    {
        return new List<Editora>() {
        new Editora() { Descricao = "Marvel" },
        new Editora() { Descricao = "DC Comics" },
        new Editora() { Descricao = "Vertigo" },
        };
        
    }

    public Editora ObterEditora(int id)
    {
        throw new NotImplementedException();
    }

    public IList<Categoria> ObterTodasCategorias()
    {
        return new List<Categoria>()
        {
            new Categoria() { Descricao = "Ação"},
            new Categoria() { Descricao = "Fantasia"},
            new Categoria() { Descricao = "Romance"},
        };
    }
}
