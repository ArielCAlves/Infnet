import React from 'react';
import { View, Text } from 'react-native';
import { styles } from './styles';

export default function Formacoes() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Formação:</Text>
        <Text style={styles.text}>Graduação: Bacharel em Ciência da Computação</Text>
        <Text style={styles.text}>Universidade: Universidade</Text>
        <Text style={styles.text}>Ano de formatura: 2022</Text>
      </View>
    </View>
  );
}
