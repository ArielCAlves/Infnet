import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View, ScrollView, TouchableOpacity, Text, Image } from 'react-native';
import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{ title: 'Filmes' }}
        />
        <Stack.Screen
          name="MovieDetails"
          component={MovieDetailsScreen}
          options={{ title: 'Detalhes do Filme' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

const HomeScreen = ({ navigation }) => {
  const [movies, setMovies] = useState([]);
  const [sortedMovies, setSortedMovies] = useState([]);

  useEffect(() => {
    fetch('https://api.npoint.io/ad11562db87bd36563e3')
      .then(response => response.json())
      .then(data => {
        setMovies(data.filmes);
        setSortedMovies(data.filmes);
      })
      .catch(error => console.error(error));
  }, []);

  const sortByYear = () => {
    const sorted = [...movies].sort((a, b) => a.ano - b.ano);
    setSortedMovies(sorted);
  };

  return (
    <View style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContainer}>
        <View style={styles.gridContainer}>
          <TouchableOpacity onPress={sortByYear} style={styles.sortButton}>
            <Text style={styles.sortButtonText}>Ordenar por Ano</Text>
          </TouchableOpacity>
          {sortedMovies.map(item => (
            <TouchableOpacity
              key={item.id}
              style={styles.cardContainer}
              onPress={() => navigation.navigate('MovieDetails', { movie: item })}
            >
              <Image style={styles.cardImage} source={{ uri: item.imagem }} />
              <Text style={styles.cardTitle}>{item.titulo}</Text>
            </TouchableOpacity>
          ))}
        </View>
      </ScrollView>
      <StatusBar style="auto" />
    </View>
  );
};

const MovieDetailsScreen = ({ route }) => {
  const { movie } = route.params;

  return (
    <View style={styles.container}>
      <Image style={styles.movieImage} source={{ uri: movie.imagem }} />
      <Text style={styles.movieTitle}>{movie.titulo}</Text>
      <Text>Sala: {movie.sala}</Text>
      <Text>Horário de Início: {movie.horarioInicio}</Text>
      <Text>Diretor: {movie.diretor}</Text>
      <Text>Ano: {movie.ano}</Text>
      <Text>Gênero: {movie.genero}</Text>
      <Text>Classificação: {movie.classificacao}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  scrollContainer: {
    flexGrow: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  gridContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
    paddingHorizontal: 10,
  },
  sortButton: {
    width: '100%',
    alignItems: 'center',
    marginBottom: 10,
  },
  sortButtonText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: 'red',
  },
  cardContainer: {
    width: '48%',
    marginBottom: 20,
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
  },
  cardImage: {
    width: '100%',
    height: 200,
    marginBottom: 10,
  },
  cardTitle: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  movieImage: {
    width: '100%',
    height: 300,
    marginBottom: 10,
  },
  movieTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },
});

export default App;
