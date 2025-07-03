const BASE_API_URL = "http://localhost:3000/products";
const existingProducts = async () => {
    try{
        const response = await fetch(`${BASE_API_URL}`);
        const data = await response.json()
        console.log(data);
    }
    catch (error) {
        console.error("Error fetching products:", error);
    }
}

// Create a new product (POST)
const newProductFunction = async () => {

  const newProduct = { id: "5", name: "Monitor", price: 233 };

  try {
    await fetch("http://localhost:3000/products", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newProduct),
    })
  } catch {
    console.log("error");
  }
//   console.log(newProduct)
};


const updateProductFunction = async () => {

    const updateProduct = { "id":"2","name": "Mouse", "price": 140 };

    try {
      const response = await fetch(`${BASE_API_URL}/${updateProduct.id}`, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json' },
      body: JSON.stringify(updateProduct),
      })
      const data =  await response.json()
      console.log("Updated product:", data)
    } catch (error) {
     
      error => console.error("Error gupdating product", error);
    }
};  

const deleteProduct = async () => {
    const deleteId = 2;
    try{
        const response = await fetch(`${BASE_API_URL}/${deleteId}`,{
        method:'DELETE'
        })
        const data = await response.json();
        console.log("Producto eliminado correctamente", data)
    }
    catch (error){
        console.error('Error al eliminar tarea:', error)
    }
}



// const UpdateProduct = { "name": "Laptop", "price": 1400 };

// fetch('http://localhost:3000/products', {
//     method: 'PUT',
//     headers: {'Content-Type': 'application/json' },
//     body: JSON.stringify(UpdateProduct)
// })
//     .then(response => response.json())
//     .then(data => console.log("Updated product:", data))
//     .catch(error => console.error("Error gupdating product", error));

 // console.log(UpdateProduct)
