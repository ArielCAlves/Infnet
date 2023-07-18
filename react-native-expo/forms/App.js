import React from 'react';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { NavigationContainer } from '@react-navigation/native';
import InfosPessoais from './pages/InfosPessoais';
import Voltar from './pages/Voltar';
import Formacoes from './pages/Formacoes';
import Publicacoes from './pages/Publicacoes';
import Cursos from './pages/Cursos';
import ExpProfissional from './pages/ExpProfissional';
import Formulario from './components/Formulario';
import { UserProvider } from './utils/UserContext';

const Drawer = createDrawerNavigator();

export default function App() {
  return (
    <UserProvider>
      <NavigationContainer>
        <Drawer.Navigator useLegacyImplementation initialRouteName="Formulário">
          <Drawer.Screen name="Menu Principal" component={Formulario} />
          <Drawer.Screen name="Informações Pessoais" component={InfosPessoais} />       
          <Drawer.Screen name="Formações" component={Formacoes} />
          <Drawer.Screen name="Publicações" component={Publicacoes} />
          <Drawer.Screen name="Cursos" component={Cursos} />
          <Drawer.Screen name="Experiência Profissional" component={ExpProfissional} />
          <Drawer.Screen name="Voltar ao Menu Principal" component={Voltar} />
        </Drawer.Navigator>
      </NavigationContainer>
    </UserProvider>
  );
}

