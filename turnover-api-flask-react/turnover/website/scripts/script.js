// const button = document.getElementById('generate');

// button.addEventListener('click', function() {    
//     var aposta = document.getElementById("numeros").value;
//     var sorteados = [];

//     while (sorteados.length < aposta) {
//         var result = Math.floor(Math.random() * 60) + 1;

//         if (!sorteados.includes(result)) {
//             sorteados.push(result);
//         }
//     }

//     sorteados.sort(function(a, b) {
//         return a - b;
//     });

  
//     document.querySelector('#result > span').textContent = sorteados;
// });




// Funções para pegar elementos, ver como usar no Flask
// async function retornaEndereco() {
//     let formulario = document.getElementById('formulario-endereco');
  
//     formulario.addEventListener('submit', async(e) => {
//       e.preventDefault();
    
//       const cepDigitado = document.getElementById('cep');
//       const cep = cepDigitado.value;
      
//       const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json`);
//       const dados = await resposta.json();
    
//       const logradouro = document.getElementById('logradouro');
//       logradouro.value = dados.logradouro;
    
//       const bairro = document.getElementById('bairro');
//       bairro.value = dados.bairro;
    
//       const cidade = document.getElementById('cidade');
//       cidade.value = dados.localidade;
    
//       const estado = document.getElementById('estado');
//       estado.value = dados.uf;
//   });
//   }
  
