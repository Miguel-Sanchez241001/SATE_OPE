-- ============================================================
-- Detalles:
--   Este script utiliza la instrucción EXPLAIN PLAN para analizar
--   el plan de ejecución de una consulta sobre la tabla 
--   BNMODCf06_ESTADOS_CUENTA_PATH. La consulta busca registros
--   específicos filtrados por los campos f06_dni, f06_age y f06_mes,
--   y los ordena por f06_age y f06_mes en orden ascendente.
-- ============================================================
-- Generar el plan de ejecución para la consulta
EXPLAIN PLAN FOR 
SELECT * 
FROM BNMODCf06_ESTADOS_CUENTA_PATH 
WHERE f06_dni = '26612780' 
  AND f06_age = '2022' 
  AND f06_mes >= '01' 
  AND f06_mes <= '12' 
ORDER BY f06_age ASC, f06_mes ASC;

-- Mostrar el plan de ejecución generado
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

-- Resultados del Análisis Desarrollo Sin Indice:
-- ----------------------------------------------------------------------------------------------------
-- | Id  | Operation          | Name                          | Rows  | Bytes | Cost (%CPU)| Time     |
-- ----------------------------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT   |                               |    12 |  1932 |     3  (34)| 00:00:01 |
-- |   1 |  SORT ORDER BY     |                               |    12 |  1932 |     3  (34)| 00:00:01 |
-- |*  2 |   TABLE ACCESS FULL| BNMODCF06_ESTADOS_CUENTA_PATH |    12 |  1932 |     2   (0)| 00:00:01 |
-- ----------------------------------------------------------------------------------------------------
--
-- Información de los Campos:
-- - **Id:** Identificador del paso en el plan de ejecución. Cada operación en el plan de ejecución tiene un ID único.
-- - **Operation:** Tipo de operación que se realiza (e.g., `TABLE ACCESS FULL`, `SORT ORDER BY`).
-- - **Name:** Nombre del objeto sobre el que se realiza la operación (e.g., nombre de la tabla).
-- - **Rows:** Número estimado de filas que la operación devolverá.
-- - **Bytes:** Tamaño estimado de los datos que se procesarán (en bytes).
-- - **Cost:** Costo estimado de la operación; refleja la cantidad de recursos necesarios para realizarla. Un número más bajo indica una operación más eficiente.
-- - **(%CPU):** Porcentaje estimado del costo total que será consumido por la CPU.
-- - **Time:** Tiempo estimado que tomará ejecutar la operación (en formato HH:MM:SS).
--
-- Notas Adicionales:
-- - El plan muestra un escaneo completo de la tabla (TABLE ACCESS FULL), lo que puede ser ineficiente en una tabla con 17 millones de registros.
