// import Turnover from "../Pages/Turnover";
// import React, { useState, useEffect } from 'react';

// export const getReceita = () => {
// return fetch("https://dummyjson.com/carts").then((res) => res.json());
// };

// export const getTurnover = () => {
// return fetch("https://dummyjson.com/products").then((res) => res.json());
// };

// export const getUsuarios = () => {
// return fetch("https://dummyjson.com/users").then((res) => res.json());
// };
// export const getComments = () => {
// return fetch("https://dummyjson.com/comments").then((res) => res.json());
// };



// function Formulario() {
//   const [formValues, setFormValues] = useState({
//     idade: '',
//     pontuacao: '',
//     dpto: '',
//     distancia_do_trabalho: '',
//     educacao: '',
//     satisfacao: '',
//     genero: '',
//     estado_civil: '',
//     renda: '',
//     anos_exp: '',
//     anos_ultima_empresa: ''
//   });

//   const [resultado, setResultado] = useState('');

//   const handleInputChange = (event) => {
//     const { name, value } = event.target;

//     setFormValues({
//       ...formValues,
//       [name]: value
//     });
//   }

//   const handleSubmit = (event) => {
//     event.preventDefault();
//     const requestOptions = {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify(formValues)
//     };
      
//     fetch('http://localhost:5000/turnover', requestOptions)
//       .then(response => response.json())
//       .then(data => {
//         setResultado(data.resultado);
//       })
//       .catch(error => console.log(error));

//       useEffect(() => {
//         handleSubmit();
//       }, []);
    
//   }

//   return (
//     <form onSubmit={handleSubmit}>
//         <label htmlFor="idade">Idade:</label>
//         <input type="number" id="idade" name="idade" min="18" max="100" value={formValues.idade} onChange={handleInputChange} />
//         <br/>

//         <label htmlFor="pontuacao">Pontuação do Teste:</label>
//         <input type="number" id="pontuacao" name="pontuacao" min="0" max="2500" value={formValues.pontuacao} onChange={handleInputChange} />
//         <br/>

//         <label htmlFor="dpto">Departamento:</label>
//         <select type="number" id="dpto" name="dpto" value={formValues.dpto} onChange={handleInputChange}>
//             <option value="">Selecione o Departamento</option>
//             <option value="0">Engenharia</option>
//             <option value="1">Vendas</option>
//             <option value="2">RH</option>
//         </select>
//         <br/>

//         <label htmlFor="distancia_do_trabalho">Distância de Casa para o Trabalho:</label>
//         <input type="number" id="distancia_do_trabalho" name="distancia_do_trabalho" min="1" max="29" value={formValues.distancia_do_trabalho} onChange={handleInputChange} />
//         <br/>

//         <label htmlFor="educacao">Educação:</label>
//         <select type="number" id="educacao" name="educacao" value={formValues.educacao} onChange={handleInputChange}>
//             <option value="">Selecione a Escolaridade</option>
//             <option value="0">Médio Completo</option>
//             <option value="1">Superior Incompleto - Cursando</option>
//             <option value="2">Superior Incompleto</option>
//             <option value="3">Superior Completo</option>
//             <option value="4">Pós-gradução</option>
//         </select>
//         <br/>

//         <label htmlFor="satisfacao">Grau de Satisfação:</label>
//         <input type="number" id="satisfacao" name="satisfacao" min="1" max="4" value={formValues.satisfacao} onChange={handleInputChange}/>
//         <br/>

//         <label htmlFor="genero">Sexo:</label>
//         <select type="text" id="genero" name="genero" value={formValues.genero} onChange={handleInputChange}>         
//             <option value="">Selecione o Sexo</option>
//             <option value="0">Masculino</option>
//             <option value="1">Feminino</option>	
//         </select>
//         <br/>

//         <label htmlFor="estado_civil">Estado Civil:</label>
//         <select type="text" id="estado_civil" name="estado_civil" value={formValues.estado_civil} onChange={handleInputChange}>            
//             <option value="">Selecione o Estado Civil</option>
//             <option value="0">Casado</option>
//             <option value="1">Solteiro</option>	
//             <option value="2">Divorciado</option>	
//         </select>
//         <br/>

//         <label for="renda">Renda:</label>
//         <input type="number" id="renda" name="renda" min="0" max="100000" value={formValues.renda} onChange={handleInputChange}/>
//         <br/>

//         <label for="anos_exp">Anos de Experiência:</label>
//         <input type="number" id="anos_exp" name="anos_exp" min="0" max="50" value={formValues.anos_exp} onChange={handleInputChange}/>
//         <br/>

//         <label for="anos_ultima_empresa">Anos na Última Empresa:</label>
//         <input type="number" id="anos_ultima_empresa" name="anos_ultima_empresa" min="0" max="50" value={formValues.anos_ultima_empresa} onChange={handleInputChange}/>
//         <br/>

//         <button className="resultado" type="submit">Rodar Modelo</button>
        
//         <div>Resultado: {resultado}</div> 

//     </form>
//   );
// }

// export default Formulario;