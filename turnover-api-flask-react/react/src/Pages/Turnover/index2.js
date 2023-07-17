import { Avatar, Rate, Space, Table, Typography } from "antd";
import { useEffect, useState } from "react";
// import { getTurnover } from "../../API";
import API from "../../API/modelo-machine-learning";

function Turnover(props) {  
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.target);

    API(data).then((response) => {
      <p>foi</p>
      console.log(response)
    }).catch((error) => {
      console.error(error);
    });
    
    fetch('http://localhost:5000/previsao', {
      method: 'POST',
      body: data
    })
      .then(response => response.json())
      .then(data => {
        alert(data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }


  const [formValues, setFormValues] = useState({
    idade: "",
    pontuacao: "",
    dpto: "",
    distancia_do_trabalho: "",
    educacao: "",
    satisfacao: "",
    genero: "",
    estado_civil: "",
    renda: "",
    anos_exp: "",
    anos_ultima_empresa: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormValues({ ...formValues, [name]: value });
  };

  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        <label htmlFor="idade">Idade:</label>
        <input type="number" id="idade" name="idade" min="18" max="100" value={formValues.idade} onChange={handleChange}/>
        <br/>

        <label htmlFor="pontuacao">Pontuação do Teste:</label>
        <input type="number" id="pontuacao" name="pontuacao" min="0" max="2500" value={formValues.pontuacao} onChange={handleChange}/>
        <br/>

        <label htmlFor="dpto">Departamento:</label>
        <select type="number" id="dpto" name="dpto" value={formValues.dpto} onChange={handleChange}>
            <option value="">Selecione o Departamento</option>
            <option value="0">Engenharia</option>
            <option value="1">Vendas</option>
            <option value="2">RH</option>
        </select>
        <br/>

        <label htmlFor="distancia_do_trabalho">Distância de Casa para o Trabalho:</label>
        <input type="number" id="distancia_do_trabalho" name="distancia_do_trabalho" min="1" max="29" value={formValues.distancia} onChange={handleChange}/>
        <br/>

        <label htmlFor="educacao">Educação:</label>
        <select type="number" id="educacao" name="educacao" value={formValues.educacao} onChange={handleChange}>
            <option value="">Selecione a Escolaridade</option>
            <option value="0">Médio Completo</option>
            <option value="1">Superior Incompleto - Cursando</option>
            <option value="2">Superior Incompleto</option>
            <option value="3">Superior Completo</option>
            <option value="4">Pós-gradução</option>
        </select>
        <br/>

        <label htmlFor="satisfacao">Grau de Satisfação:</label>
        <input type="number" id="satisfacao" name="satisfacao" min="1" max="4" value={formValues.satisfacao} onChange={handleChange}/>
        <br/>

        <label htmlFor="genero">Sexo:</label>
        <select type="text" id="genero" name="genero" value={formValues.sexo} onChange={handleChange}>         
            <option value="">Selecione o Sexo</option>
            <option value="0">Masculino</option>
            <option value="1">Feminino</option>	
        </select>
        <br/>

        <label htmlFor="estado_civil">Estado Civil:</label>
        <select type="text" id="estado_civil" name="estado_civil" value={formValues.estado_civil} onChange={handleChange}>            
            <option value="">Selecione o Estado Civil</option>
            <option value="0">Casado</option>
            <option value="1">Solteiro</option>	
            <option value="2">Divorciado</option>	
        </select>
        <br/>

        <label htmlFor="renda">Renda:</label>
        <input type="number" id="renda" name="renda" min="0" max="100000" value={formValues.renda} onChange={handleChange}/>
        <br/>

        <label htmlFor="anos_exp">Anos de Experiência:</label>
        <input type="number" id="anos_exp" name="anos_exp" min="0" max="50" value={formValues.anos_exp} onChange={handleChange}/>
        <br/>

        <label htmlFor="anos_ultima_empresa">Anos na Última Empresa:</label>
        <input type="number" id="anos_ultima_empresa" name="anos_ultima_empresa" min="0" max="50" value={formValues.anos_ultima_empresa} onChange={handleChange}/>
        <br/>

        <button className="resultado" type="submit">Rodar Modelo</button>
        
      </form>
      
    </div>
  );
}
export default Turnover;
