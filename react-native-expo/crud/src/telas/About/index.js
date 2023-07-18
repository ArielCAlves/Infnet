import React from 'react';
import { View, Text, Image } from 'react-native';
import styles from './styles';


import logo from '../../../assets/logo.png';

export default function About() {
  return (
    <View style={styles.container}>
      <Text style={styles.headerText}>ASSESSMENT Parte III</Text>
      <Image source={logo} style={styles.logo} />
      <View style={styles.textContainer}>        
        <Text>Aluno: Ariel Carvalho Alves</Text>
        <Text>Prof: John Edson Ribeiro de Carvalho</Text>
      </View>
    </View>
  );
}
