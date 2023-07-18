import React from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';

const Card = ({ titulo, imagem }) => {
  return (
    <View style={styles.container}>
      <Image source={getImageSource(imagem)} style={styles.image} />
      <Text style={styles.title}>{titulo}</Text>
    </View>
  );
};

const getImageSource = (imagem) => {
  return typeof imagem === 'string' ? { uri: imagem } : imagem;
};

const styles = StyleSheet.create({
  container: {
    marginBottom: 20,
    alignItems: 'center',
  },
  image: {
    width: 200,
    height: 300,
    marginBottom: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 5,
  },
});

export default Card;
