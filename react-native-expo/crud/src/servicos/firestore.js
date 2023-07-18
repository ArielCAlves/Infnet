import { db, auth } from "../config/firebase";
function getProdutosCollection() {
  const currentUser = auth.currentUser;
  if (currentUser) {
    const userID = currentUser.uid;
    return db.collection('usuarios').doc(userID).collection('produtos');
  }
  return null;
}

export async function salvarProduto(data) {
  try {
    const produtosCollection = getProdutosCollection();
    if (produtosCollection) {
      await produtosCollection.add(data);
      return 'ok';
    }
    return 'erro';
  } catch (error) {
    console.log('Erro ao adicionar produto:', error);
    return 'erro';
  }
}

export async function pegarProdutos() {
  try {
    const produtosCollection = getProdutosCollection();
    if (produtosCollection) {
      const querySnapshot = await produtosCollection.get();
      let produtos = [];
      querySnapshot.forEach((doc) => {
        let produto = { id: doc.id, ...doc.data() };
        produtos.push(produto);
      });
      return produtos;
    }
    return [];
  } catch (error) {
    console.log(error);
    return [];
  }
}

export async function pegarProdutosTempoReal(setProdutos) {
  const produtosCollection = getProdutosCollection();
  if (produtosCollection) {
    const unsubscribe = produtosCollection.onSnapshot((querySnapshot) => {
      const produtos = [];
      querySnapshot.forEach((doc) => {
        produtos.push({ id: doc.id, ...doc.data() });
      });
      setProdutos(produtos);
    });

    return unsubscribe;
  }
  return null;
}

export async function atualizarProduto(produtoID, data) {
  try {
    const produtosCollection = getProdutosCollection();
    if (produtosCollection) {
      await produtosCollection.doc(produtoID).update(data);
      return 'ok';
    }
    return 'erro';
  } catch (error) {
    console.log(error);
    return 'erro';
  }
}

export async function deletarProduto(produtoID) {
  try {
    const produtosCollection = getProdutosCollection();
    if (produtosCollection) {
      await produtosCollection.doc(produtoID).delete();
      return 'ok';
    }
    return 'erro';
  } catch (error) {
    console.log(error);
    return 'erro';
  }
}
