import { useEffect, useState } from 'react'

const initialDataForm = {
    id: 0,
    name: '',
    description: '',
    price: ''
}
export const ProductForm = ({ productSelected, handlerAdd }) => {
    const [form, setForm] = useState(initialDataForm)

    const { id, name, description, price } = form

    useEffect(() => {
        setForm(productSelected);
    }, [productSelected])

    return (
        <form onSubmit={(event) => {
            event.preventDefault();
            if (!name || !description || !price) {
                alert('Datos incompletos')
                return;
            }
            // console.log(form)
            handlerAdd(form)
            setForm(initialDataForm)

        }}>
            <div>
                <input className='form-control my-3 w-75' placeholder="name"
                    name="name"
                    value={name}
                    onChange={(event) => setForm({
                        ...form,
                        name: event.target.value
                    })}
                >
                </input>
            </div>
            <div>
                <input className='form-control my-3 w-75' placeholder="description"
                    name="description"
                    value={description}
                    onChange={(event) => setForm({
                        ...form,
                        description: event.target.value
                    })}
                >
                </input>
            </div>
            <div> <input className='form-control my-3 w-75' placeholder="price"
                name="price"
                value={price}
                onChange={(event) => setForm({
                    ...form,
                    price: event.target.value
                })}
            >
            </input></div>
            <div>
                <button className='btn btn-success' type="submit">{id > 0 ? "modificar" : "crear"}</button>
            </div>






        </form>
    )

}