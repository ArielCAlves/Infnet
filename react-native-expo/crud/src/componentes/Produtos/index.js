import { View, Text } from 'react-native';
import styles from './styles';

export default function Produto({ nome, preco, qtde, peso }) {

  return (
    <View style={styles.container}>
      <Text style={styles.texto}>{nome}</Text>
      <Text style={styles.texto}>R$ {preco}</Text>
      <Text style={styles.texto}>{qtde} un</Text>
      <Text style={styles.texto}>{peso} kg</Text>
    </View>
  );
}
