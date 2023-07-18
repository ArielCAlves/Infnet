import { View, TouchableOpacity, Alert } from "react-native"
import { EntradaTexto } from "../../componentes/EntradaTexto"
import Botao from "../../componentes/Botao"
import styles from "./styles"
import React, { useState } from "react"
import { salvarProduto, atualizarProduto, deletarProduto } from "../../servicos/firestore"
import { Alerta } from "../../componentes/Alerta"
import Icon from "react-native-vector-icons/Feather"

export default function DadosProduto({ navigation, route }){
  const [nome, setNome] = useState(route?.params?.nome || '')
  const [preco, setPreco] = useState(route?.params?.preco || '')
  const [qtde, setQtde] = useState(route?.params?.qtde || '')
  const [peso, setPeso] = useState(route?.params?.peso || '')
  const [mensagem, setMensagem] = useState('')
  const [mostrarMensagem, setMostrarMensagem] = useState(false)

  async function salvar(){
    if(nome == '' || preco == ''|| qtde == ''|| peso == ''){
      setMensagem("Por favor preencha todos os campos")
      setMostrarMensagem(true)
      return
    }

    let resultado = ''
    if(route?.params) {
      resultado = await atualizarProduto(route?.params?.id, {
        nome, preco, qtde, peso
      })
    }
    else{
      resultado = await salvarProduto({
        nome,
        preco,
        qtde,
        peso
      })
    }
    
    if(resultado == 'erro'){
      setMensagem("Erro ao salvar produto")
      setMostrarMensagem(true)
    }
    else {
      navigation.goBack();
    }
  }

  async function deletar(){
    Alert.alert(
      'Deletar produto',
      'Tem certeza que quer deletar?',
      [
        {
          text: 'Não',
          style:"cancel"
        },
        {
          text: 'Sim',
          onPress: () => {
            deletarProduto(route?.params?.id);
            navigation.goBack()
          },
          style: 'default'
        }
      ]
    )
  }

  return (
    <View style={styles.container}>

      { route?.params &&
      <TouchableOpacity onPress={() => deletar()}>
        <Icon
          name="trash-2"
          size={20}
          color="#000"
        />
      </TouchableOpacity>}

      <EntradaTexto
        label="Nome do produto"
        value={nome}
        onChangeText={texto => setNome(texto)}
      />
      <EntradaTexto
        label="Preço do produto"
        value={preco}
        onChangeText={texto => setPreco(texto)}
      />
      <EntradaTexto
        label="Quantidade do produto"
        value={qtde}
        onChangeText={texto => setQtde(texto)}
      />
      <EntradaTexto
        label="Peso do produto(kg)"
        value={peso}
        onChangeText={texto => setPeso(texto)}
      />

      <Botao onPress={() => salvar()} >Salvar</Botao>

      <Alerta
        mensagem={mensagem}
        error={mostrarMensagem}
        setError={setMostrarMensagem}
      />
    </View>
  )
}