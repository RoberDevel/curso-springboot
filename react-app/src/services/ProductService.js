import axios from "axios";

const initProducts = [{
    id: 1,
    name: 'Monitor Asus',
    price: 200,
    description: 'Monitor de 24 pulgadas',
},
{
    id: 2,
    name: 'laptop hp',
    price: 1000,
    description: 'laptop de 15 pulgadas',
}
];

const baseUrl = 'http://localhost:8080/products';

export const listProducts = () => {
    return initProducts;
}

export const findAll = async () => {

    try {
        const response = await axios.get(baseUrl);
        return response;
    } catch (error) { console.error(error) }
    return null;
}

export const create = async ({ name, description, price }) => {

    try {
        const response = await axios.post(baseUrl, {
            name,
            description,
            price
        });
        return response;
    } catch (error) { console.error(error) }

    return undefined;
}

export const update = async ({ id, name, description, price }) => {

    try {

        const response = await axios.put(`${baseUrl}/${id}`, {
            name,
            description,
            price
        });
        return response;
    } catch (error) { console.error(error) }

    return undefined;
}

export const remove = async (id) => {

    try {
        await axios.delete(`${baseUrl}/${id}`);

    } catch (error) { console.error(error) }


}


