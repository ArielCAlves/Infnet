import { auth, db } from "../config/firebase";

function errosFirebase(error){
  let mensagem = '';
  switch(error.code) {
    case AuthErrorCodes.EMAIL_EXISTS:
      mensagem = "Esse email já está em uso";
      break;
    case AuthErrorCodes.INVALID_EMAIL:
      mensagem = "Email inválido";
      break;
    case AuthErrorCodes.WEAK_PASSWORD:
      mensagem = "A senha precisa de no minimo 6 caracteres";
      break;
    default:
      mensagem="Erro desconhecido";
  }
  return mensagem;
}

export async function cadastrar(email, senha) {
  const resultado = await auth.createUserWithEmailAndPassword(email, senha)
  .then((dadosDoUsuario) => {
    console.log(dadosDoUsuario)
    return "sucesso"
  })
  .catch((error) => {
    console.log(error);
    return errosFirebase(error)
  });

  return resultado
}

export async function logar(email, senha) {
  const resultado = await auth.signInWithEmailAndPassword(email, senha)
  .then((dadosDoUsuario) => {
    console.log(dadosDoUsuario)
    return "sucesso"
  })
  .catch((error) => {
    console.log(error);
    return "erro"
  });
  return resultado
}