import React from 'react';
import { View, Text } from 'react-native';
import { styles } from './styles';

export default function Publicacoes() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Publicações:</Text>
        <Text style={styles.text}>Artigo 1: Novas tendências em inteligência artificial</Text>
        <Text style={styles.text}>Artigo 2: O impacto das redes sociais na sociedade</Text>
        <Text style={styles.text}>Artigo 3: Desenvolvimento sustentável e tecnologia</Text>
      </View>
    </View>
  );
}
