import { useEffect, useState } from "react"
import { listProducts } from "../services/ProductService"
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

    useEffect(() => {
        const result = listProducts();
        setProducts(result);

    }, [])

    const handlerAddProduct = (product) => {
        console.log(product)

        if (product.id > 0) {
            setProducts(products.map(p => {
                if (p.id == product.id) {
                    return {
                        ...product
                    }

                } return p;
            }));
        } else {
            setProducts([...products, { ...product, id: products.length + 1 }]);
        }

    }

    const handlerRemoveProduct = (id) => {
        console.log(name);
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