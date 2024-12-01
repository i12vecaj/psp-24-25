# 📝 Revisión de la Tarea 2

## 📌 Reflexión
- **¿Qué errores encontré en mi código?**  
  _Al principio, no se estaba verificando si los hilos estaban funcionando correctamente ni se realizaban pruebas de sincronización adecuadas. También, la lógica de la ejecución de hilos podría no ser clara sin el uso de mocks en los tests unitarios._

- **¿Qué aprendí al corregirlo?**  
  _Aprendí a utilizar JUnit junto con Mockito para realizar pruebas unitarias más efectivas, especialmente cuando se interactúa con métodos sincronizados y múltiples hilos. Además, comprendí la importancia de la sincronización de hilos para evitar condiciones de carrera._

- **¿Cómo me ayudaron los tests unitarios?**  
  _Los tests unitarios fueron cruciales para verificar el comportamiento esperado de las funciones `agregarSaldo`, `restarSaldo` y `setSaldo` bajo condiciones controladas. También facilitaron la comprobación de que los hilos se comportaban como se esperaba al realizar las acciones correctamente._

---

## ✅ Tests Implementados
1. **Test de agregarSaldo:**  
   _Verifica que el saldo se incremente correctamente cuando se agrega un monto._
    - Resultado esperado: _El saldo inicial + 50._
    - Resultado obtenido: _Saldo correcto con diferencia de 50._

2. **Test de restarSaldo:**  
   _Verifica que el saldo se reste correctamente cuando se resta un monto._
    - Resultado esperado: _El saldo inicial - 20._
    - Resultado obtenido: _Saldo correcto con diferencia de 20._

3. **Test de setSaldo:**  
   _Verifica que se actualiza correctamente el saldo mediante el método `setSaldo`._
    - Resultado esperado: _Saldo actualizado a 200._
    - Resultado obtenido: _Saldo correcto con valor 200._

4. **Test de HiloConsumidor:**  
   _Verifica que el hilo consumidor ejecuta correctamente el método `restarSaldo`._
    - Resultado esperado: _Se llama al método `restarSaldo` con el saldo correcto._
    - Resultado obtenido: _Método llamado correctamente._

5. **Test de HiloProveedor:**  
   _Verifica que el hilo proveedor ejecuta correctamente el método `agregarSaldo`._
    - Resultado esperado: _Se llama al método `agregarSaldo` con el saldo correcto._
    - Resultado obtenido: _Método llamado correctamente._

---

## 💡 Observaciones
- **Observaciones generales`_

5. **Test de HiloProveedor:**  
   _Verifica que el hilo proveedor ejecuta correctamente el método `agregarSaldo...`_
