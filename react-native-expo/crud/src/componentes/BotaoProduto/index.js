import { TouchableOpacity, Text } from "react-native";
import styles from './styles';

export function BotaoProduto({ onPress }){
  return (
    <TouchableOpacity style={styles.botao} onPress={onPress}>
      <Text style={styles.textoBotao}>+</Text>
    </TouchableOpacity>
  )
}