import React from 'react';
import { View, Text, Image, StyleSheet } from 'react-native';

const Movie = ({ 
  titulo,
  sala,
  horarioInicio,
  diretor,
  imagem,
  ano,
  classificacao,
  genero }) => {
    return (
      <View style={styles.container}>
        <Image source={{ uri: imagem }} style={styles.image} />
        <Text style={styles.title}>{titulo}</Text>
        <Text style={styles.info}>
          Diretor: {diretor},
          Cinema: {sala},
          Horário: {horarioInicio},
          Ano: {ano},
          Genero: {genero},
          Classificação: {classificacao},
        </Text>
      </View>
    );
  };

const styles = StyleSheet.create({
  container: {
    marginBottom: 20,
    padding: 10,
    backgroundColor: '#f2f2f2',
    borderRadius: 5,
  },
  image: {
    width: 200,
    height: 300,
    resizeMode: 'cover',
    marginBottom: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 5,
  },
  info: {
    fontSize: 16,
  },
});

export default Movie;
