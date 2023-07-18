import { StyleSheet, StatusBar } from 'react-native';

export default StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
    paddingTop: StatusBar.currentHeight,
  },
  texto: {
    fontSize: 20,
    fontWeight: 'bold',
    marginLeft: 10,
    marginBottom: 20,
  },
  opcoesOrdenacao: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginBottom: 10,
  },
  botaoOrdenacao: {
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 5,
    backgroundColor: '#e0e0e0',
    color: '#000',
  },
  botaoOrdenacaoAtivo: {
    backgroundColor: '#2196F3',
    color: '#fff',
  },
  textoBotaoOrdenacao: {
    fontWeight: 'bold',
  },
});
