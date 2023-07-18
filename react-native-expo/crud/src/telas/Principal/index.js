import React, { useEffect, useState } from 'react';
import { View, Text, RefreshControl, ScrollView, TouchableOpacity, FlatList, Button, ActivityIndicator, Image, StyleSheet } from 'react-native';
import Cabecalho from '../../componentes/Cabecalho';
import Produto from '../../componentes/Produtos';
import styles from './styles';
import { auth } from '../../config/firebase';
import { BotaoProduto } from '../../componentes/BotaoProduto';
import { pegarProdutos, pegarProdutosTempoReal } from '../../servicos/firestore';
import { FontAwesome } from '@expo/vector-icons';

export default function Principal({ navigation }) {
  const usuario = auth.currentUser;
  const [produtos, setProdutos] = useState([]);
  const [refreshing, setRefreshing] = useState(false);
  const [ordenacao, setOrdenacao] = useState(null);
  const [showSplash, setShowSplash] = useState(true);

  async function carregarDadosProdutos() {
    setRefreshing(true);
    const produtosFirestore = await pegarProdutos();
    setProdutos(produtosFirestore);
    setRefreshing(false);
  }

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowSplash(false);
    }, 2000);

    carregarDadosProdutos();
    pegarProdutosTempoReal(setProdutos);

    return () => clearTimeout(timer);
  }, []);

  function deslogar() {
    auth.signOut();
    navigation.replace('Login');
  }

  const converterVirgulaParaPonto = (valor) => {
    return valor.replace(',', '.');
  };

  const ordenarPorNome = () => {
    if (ordenacao === 'nome') {
      setOrdenacao(null);
    } else {
      setOrdenacao('nome');
      const produtosOrdenados = [...produtos].sort((a, b) => a.nome.localeCompare(b.nome));
      setProdutos(produtosOrdenados);
    }
  };

  const ordenarPorPreco = () => {
    if (ordenacao === 'preco') {
      setOrdenacao(null);
    } else {
      setOrdenacao('preco');
      const produtosOrdenados = [...produtos].sort((a, b) => {
        const precoA = parseFloat(converterVirgulaParaPonto(a.preco));
        const precoB = parseFloat(converterVirgulaParaPonto(b.preco));
        return precoA - precoB;
      });
      setProdutos(produtosOrdenados);
    }
  };

  const renderItem = ({ item }) => (
    <TouchableOpacity onPress={() => navigation.navigate('DadosProduto', item)}>
      <Produto nome={item.nome} preco={item.preco} qtde={item.qtde} peso={item.peso} />
    </TouchableOpacity>
  );

  if (showSplash) {
    return (
      <View style={splashStyles.container}>
        <Image source={require('../../../assets/splash.gif')} style={splashStyles.image} />
        <ActivityIndicator size="large" color="#2196F3" />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Cabecalho logout={deslogar} />
      <Text style={styles.texto}>Usuário: {usuario.email}</Text>

      <View style={styles.opcoesOrdenacao}>
        <Button
          title="Ordenar por Nome"
          onPress={ordenarPorNome}
          color={ordenacao === 'nome' ? '#2196F3' : '#e0e0e0'}
        />
        <Button
          title="Ordenar por Preço"
          onPress={ordenarPorPreco}
          color={ordenacao === 'preco' ? '#2196F3' : '#e0e0e0'}
        />
      </View>

      <FlatList
        style={{ width: '100%' }}
        data={produtos}
        renderItem={renderItem}
        keyExtractor={(item) => item.id.toString()}
        refreshControl={<RefreshControl refreshing={refreshing} onRefresh={carregarDadosProdutos} />}
      />

      <BotaoProduto onPress={() => navigation.navigate('DadosProduto')} />
    </View>
  );
}

const splashStyles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: 200,
    height: 200,
    marginBottom: 16,
  },
});


