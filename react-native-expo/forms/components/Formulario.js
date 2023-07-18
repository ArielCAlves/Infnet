import React, { useContext } from 'react';
import { View, TextInput, Button } from 'react-native';
import { UserContext } from '../utils/UserContext';
import { styles } from './styles';

export default function Formulario() {
  const { updateUserData } = useContext(UserContext);

  const [nome, setNome] = React.useState('');
  const [endereco, setEndereco] = React.useState('');
  const [cidade, setCidade] = React.useState('');
  const [estado, setEstado] = React.useState('');
  const [telefone, setTelefone] = React.useState('');
  const [email, setEmail] = React.useState('');

  const handleEnviar = () => {
    updateUserData({ nome, endereco, cidade, telefone, email, estado });
    alert('Dados enviados com sucesso para Informações Pessoais!');
  };

  const handleLimpar = () => {
    setNome('');
    setEndereco('');
    setCidade('');
    setEstado('');
    setTelefone('');
    setEmail('');
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder="Nome"
        value={nome}
        onChangeText={setNome}
      />
      <TextInput
        style={styles.input}
        placeholder="Endereço"
        value={endereco}
        onChangeText={setEndereco}
      />
      <TextInput
        style={styles.input}
        placeholder="Cidade"
        value={cidade}
        onChangeText={setCidade}
      />
      <TextInput
        style={styles.input}
        placeholder="Estado"
        value={estado}
        onChangeText={setEstado}
      />
      <TextInput
        style={styles.input}
        placeholder="Telefone"
        value={telefone}
        onChangeText={setTelefone}
      />
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />

      <View style={styles.buttonContainer}>
        <Button title="Enviar" onPress={handleEnviar}/>
        <Button title="Limpar" onPress={handleLimpar}/>
      </View>
    </View>
  );
}
