import { ProductDetail } from "./ProductDetail";
import PropTypes from "prop-types";

export const ProductGrid = ({ handlerProductSelected, handlerRemove, products }) => {

    return (

        <table class="table table-striped table-hover table-bordered">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Descripcion</th>
                    <th>modificar</th>
                    <th>eliminar</th>
                </tr>
            </thead>
            <tbody>
                {products.map(product => {
                    return <ProductDetail handlerProductSelected={handlerProductSelected} handlerRemove={handlerRemove} product={product} key={product.id} />
                }
                )}
            </tbody>
        </table>


    )
};

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired
}