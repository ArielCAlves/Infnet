import React from 'react';
import { View, Text } from 'react-native';
import { styles } from './styles';

export default function ExpProfissional() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Experiência Profissional:</Text>
        <Text style={styles.text}>Empresa: ABC Technologies</Text>
        <Text style={styles.text}>Cargo: Desenvolvedor de Software</Text>
        <Text style={styles.text}>Período: Janeiro 2019 - Presente</Text>
      </View>
    </View>
  );
}
