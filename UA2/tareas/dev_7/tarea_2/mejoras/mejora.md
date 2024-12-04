# Revisión de la Tarea: Clase CuentaCorriente

## Reflexión

- **¿Qué errores encontré en mi código?**  
  No se manejaba adecuadamente la concurrencia en las operaciones que involucraban la actualización del saldo.
- **¿Qué aprendí al corregirlo?**  
  Aprendí la importancia del uso de la palabra clave `synchronized` para proteger las operaciones críticas en clases compartidas por múltiples hilos.

- **¿Cómo me ayudaron los tests unitarios?**  
  Los tests permitieron verificar que las operaciones con múltiples hilos produjeran un saldo consistente. También ayudaron a identificar casos donde los métodos `setSaldo` y `getSaldo` no sincronizados podían provocar condiciones de carrera.
---
## Tests Implementados

1. **Caso de prueba 1: Verificar saldo inicial**  
   - **Descripción:** Comprueba que el saldo inicial sea 1000.  
   - **Resultado:** Test exitoso; el saldo inicial es correcto.

2. **Caso de prueba 2: Depósito simple con un hilo**  
   - **Descripción:** Realiza depósitos individuales desde un solo hilo y verifica el saldo final.  
   - **Resultado:** Test exitoso; los depósitos se reflejan correctamente en el saldo.

3. **Caso de prueba 3: Depósitos concurrentes**  
   - **Descripción:** Varios hilos realizan depósitos simultáneamente, y se verifica el saldo final.  
   - **Resultado:** Test exitoso; el saldo final refleja correctamente la suma de todas las transacciones.
---
## Observaciones

- El uso de `synchronized` en el método `depositar` garantiza la integridad de los datos.
- La generación de tiempos de espera aleatorios en los métodos permitió simular condiciones más realistas de concurrencia.
