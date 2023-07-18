import React from 'react';
import { View, Text } from 'react-native';
import { styles } from './styles';

export default function Cursos() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Cursos:</Text>
        <Text style={styles.text}>Curso 1: Desenvolvimento de Aplicativos Móveis</Text>
        <Text style={styles.text}>Curso 2: Introdução à Inteligência Artificial</Text>
        <Text style={styles.text}>Curso 3: Web Design Avançado</Text>
      </View>
    </View>
  );
}
