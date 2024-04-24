import PropTypes from "prop-types";

export const ProductDetail = ({ handlerProductSelected, handlerRemove, product }) => {
    return (
        <tr>
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>{product.price}</td>
            <td>{product.description}</td>
            <td>
                <button className="btn btn-warning btn-sm" onClick={() => handlerProductSelected(product)}>modificar</button>
            </td>
            <td>
                <button className="btn btn-danger btn-sm" onClick={() => handlerRemove(product.id)}>eliminar</button>
            </td>

        </tr>
    )
}

ProductDetail.propTypes = {
    product: PropTypes.object.isRequired
}