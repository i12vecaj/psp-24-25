from flask import Flask, jsonify, request

app = Flask(__name__)

# Datos de ejemplo
personas = [
    {"id": 1, "name": "Juan"},
    {"id": 2, "name": "Ana"},
    {"id": 3, "name": "Luis"}
]

# Endpoint para obtener todas las personas
@app.route('/people', methods=['GET'])
def get_people():
    return jsonify(personas)

# Endpoint para obtener una persona por ID
@app.route('/people/<int:id>', methods=['GET'])
def get_person(id):
    person = next((p for p in personas if p['id'] == id), None)
    if person:
        return jsonify(person)
    else:
        return jsonify({"message": "Persona no encontrada"}), 404

# Endpoint para eliminar una persona por ID
@app.route('/people/<int:id>', methods=['DELETE'])
def delete_person(id):
    global personas
    personas = [p for p in personas if p['id'] != id]
    return jsonify({"message": f"Persona {id} eliminada"})

if __name__ == '__main__':
    app.run(debug=True)