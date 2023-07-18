import { useState } from 'react';
import { TextInput, HelperText, Text } from 'react-native-paper';
import styles from './styles';

export function EntradaTexto({ 
  label, 
  value, 
  onChangeText, 
  secureTextEntry, 
  error, 
  messageError,
  pattern 
}) {
  const [secureMode, setSecureMode] = useState(secureTextEntry);

  function regexValidation() {
    if(!value) return false;
    if (pattern) {
      const condition = new RegExp(pattern);
      return !condition.test(value);
    }

    return false;
  }

  const showError = value == null || error || regexValidation();

  return (
    <>
      <TextInput
        label={label}
        value={value}
        error={showError}
        secureTextEntry={secureMode}
        onChangeText={onChangeText}
        style={styles.input}
        mode="outlined"
        activeOutlineColor='#8E7CC3'
        right={
          secureTextEntry ?
          <TextInput.Icon
            name={secureMode ? 'eye-off' : 'eye'}
            onPress={() => setSecureMode(!secureMode)}
          /> : null
        }
      />
      {showError && (
        <HelperText type="error" visible={showError}>
          <Text>{messageError}</Text>
        </HelperText>
      )}
    </>
  );
}
