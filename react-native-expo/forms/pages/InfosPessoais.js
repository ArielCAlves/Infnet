import React, { useContext } from 'react';
import { View, Text } from 'react-native';
import { UserContext } from '../utils/UserContext';
import { styles } from './styles';

export default function InfosPessoais() {
  const { userData } = useContext(UserContext);

  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Detalhes sobre informações pessoais:</Text>
        <Text style={styles.text}>Nome: {userData.nome}</Text>
        <Text style={styles.text}>Endereço: {userData.endereco}</Text>
        <Text style={styles.text}>Cidade: {userData.cidade}</Text>
        <Text style={styles.text}>Estado: {userData.estado}</Text>
        <Text style={styles.text}>Telefone: {userData.telefone}</Text>
        <Text style={styles.text}>Email: {userData.email}</Text>
      </View>
    </View>
  );
}


