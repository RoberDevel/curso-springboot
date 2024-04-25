import { useEffect, useState } from "react"
import { create, findAll, listProducts, remove, update } from "../services/ProductService"
import { ProductGrid } from "./ProductGrid"
import PropTypes from "prop-types"
import { ProductForm } from "./ProductForm"



export const ProductApp = ({ title }) => {

    const [products, setProducts] = useState([]);

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    const getProducts = async () => {
        const result = await findAll();
        setProducts(result.data._embedded.products);
    }

    useEffect(() => {

        getProducts();

    }, [])

    const handlerAddProduct = async (product) => {
        console.log(product)

        if (product.id > 0) {
            const response = await update(product);
            setProducts(products.map(p => {
                if (p.id == product.id) {
                    return {
                        ...response.data
                    }

                } return p;
            }));
        } else {
            const response = await create(product);
            setProducts([...products, { ...response.data }]);
        }

    }

    const handlerRemoveProduct = (id) => {
        console.log(id);
        remove(id);
        setProducts(products.filter(product => product.id != id));
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product });
    }

    return (
        <div className="container my-4">
            <h2>{title}</h2>
            <div className="row">
                <div className="col"><ProductForm handlerAdd={handlerAddProduct} productSelected={productSelected}></ProductForm></div>
                <div className="col">

                    {products.length > 0 ? <ProductGrid products={products} handlerRemove={handlerRemoveProduct} handlerProductSelected={handlerProductSelected}></ProductGrid> :
                        <div>No hay productos</div>
                    }

                </div>
            </div>


        </div>
    )
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}