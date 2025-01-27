// Mostrar la lista de personas
function obtenerPersonas() {
    axios.get('/personas/listar')
        .then(function(response) {
            const personas = response.data;
            const tbody = document.querySelector('#personasTable tbody');
            tbody.innerHTML = '';
            personas.forEach(persona => {
                const row = document.createElement('tr');
                row.innerHTML = `
				<td>${persona.id}</td>
				      <td>${persona.nombre}</td>
				      <td>${persona.telefono}</td>
				      <td>
				    <button onclick="editarPersona(${persona.id})" class="btn btn-warning btn-sm">Editar</button>
				    <button onclick="eliminarPersona(${persona.id})" class="btn btn-danger btn-sm">Eliminar</button>
				 </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(function(error) {
            console.error("Error al obtener las personas:", error);
        });
}

// Agregar o actualizar una persona
document.getElementById('personaForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const id = document.getElementById('personaId').value;
    const nombre = document.getElementById('nombre').value;
    const telefono = document.getElementById('telefono').value;

    const persona = { nombre, telefono };

    if (id) {
        // Si hay ID, hacer PUT para actualizar
        axios.put(`/personas/actualizar/${id}`, persona)
            .then(function(response) {
                alert(response.data);
                obtenerPersonas();
                limpiarFormulario();
            })
            .catch(function(error) {
                console.error("Error al actualizar la persona:", error);
            });
    } else {
        // Si no hay ID, hacer POST para crear
        axios.post('/personas/guardar', persona)
            .then(function(response) {
                alert(response.data);
                obtenerPersonas();
                limpiarFormulario();
            })
            .catch(function(error) {
                console.error("Error al guardar la persona:", error);
            });
    }
});

// Editar una persona
// Editar una persona
function editarPersona(id) {
    axios.get(`/personas/${id}`)
        .then(function(response) {
            const persona = response.data;
            document.getElementById('personaId').value = persona.id;
            document.getElementById('nombre').value = persona.nombre;
            document.getElementById('telefono').value = persona.telefono;
        })
        .catch(function(error) {
            console.error("Error al obtener la persona:", error);
        });
}


// Eliminar una persona
function eliminarPersona(id) {
    if (confirm("¿Estás seguro de eliminar esta persona?")) {
        axios.delete(`/personas/eliminar/${id}`)
            .then(function(response) {
                alert(response.data);
                obtenerPersonas();
            })
            .catch(function(error) {
                console.error("Error al eliminar la persona:", error);
            });
    }
}

// Limpiar el formulario
function limpiarFormulario() {
    document.getElementById('personaId').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('telefono').value = '';
}

// Inicializar la lista de personas al cargar la página
window.onload = function() {
    obtenerPersonas();
};
