async function retornaResultado() {
    let formulario = document.getElementById('formulario');
  
    formulario.addEventListener('submit', async(e) => {
      e.preventDefault();
    
      const idadeDigitada = document.getElementById('idade');
      const idade = idadeDigitada.value;
      
      const pontuacaoDigitada = document.getElementById('pontuacao');
      const pontuacao = pontuacaoDigitada.value;

      const dptoDigitado = document.getElementById('dpto');
      const dpto = dptoDigitado.value;

      const distancia_do_trabalhoDigitada = document.getElementById('distancia_do_trabalho');
      const distancia_do_trabalho = distancia_do_trabalhoDigitada.value;

      const educacaoDigitada = document.getElementById('educacao');
      const educacao = educacaoDigitada.value;

      const satisfacaoDigitada = document.getElementById('satisfacao');
      const satisfacao = satisfacaoDigitada.value;

      const generoDigitado = document.getElementById('genero');
      const genero = generoDigitado.value;

      const estado_civilDigitado = document.getElementById('estado_civil');
      const estado_civil = estado_civilDigitado.value;

      const rendaDigitada = document.getElementById('renda');
      const renda = rendaDigitada.value;

      const anos_expDigitado = document.getElementById('anos_exp');
      const anos_exp = anos_expDigitado.value;

      const anos_ultima_empresaDigitado = document.getElementById('anos_ultima_empresa');
      const anos_ultima_empresa = anos_ultima_empresaDigitado.value;

      const resultado = await fetch(`http://127.0.0.1:5000/previsao?idade=${idade}&pontuacao=${pontuacao}&dpto=${dpto}&distancia_do_trabalho=${distancia_do_trabalho}&educacao=${educacao}&satisfacao=${satisfacao}&genero=${genero}&estado_civil=${estado_civil}&renda=${renda}&anos_exp=${anos_exp}&anos_ultima_empresa=${anos_ultima_empresa}/json`);
      const dados = await resultado.json();    
  });
  }