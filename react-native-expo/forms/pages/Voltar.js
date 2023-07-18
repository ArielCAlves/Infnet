import React, { useState, useEffect } from 'react';
import { Button, View, ActivityIndicator } from 'react-native';

export default function Voltar({ navigation }) {
  const [startTimer, setStartTimer] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    let timer;

    if (startTimer) {
      setIsLoading(true);

      timer = setTimeout(() => {
        navigation.goBack();
      }, 2000);
    }

    return () => clearTimeout(timer); 
  }, [navigation, startTimer]);

  const handleButtonClick = () => {
    setStartTimer(true);
  };

  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      {isLoading ? (
        <ActivityIndicator size="large" color="blue" />
      ) : (
        <Button onPress={handleButtonClick} title="Go back home" />
      )}
    </View>
  );
}


