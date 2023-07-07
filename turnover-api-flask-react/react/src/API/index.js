export const getReceita = () => {
return fetch("https://dummyjson.com/carts").then((res) => res.json());
};

export const getTurnover = () => {
return fetch("https://dummyjson.com/products").then((res) => res.json());
};

export const getUsuarios = () => {
return fetch("https://dummyjson.com/users").then((res) => res.json());
};
export const getComments = () => {
return fetch("https://dummyjson.com/comments").then((res) => res.json());
};