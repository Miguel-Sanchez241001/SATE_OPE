-- ============================================================
-- Script de Creación de Índice
-- Autor: Miguel Sanchez
-- Fecha: 05/09/2024
-- Hora: 12:54
-- Detalles:
--   Este script crea un índice llamado IDX_EC_PATH_DNI_AGE_MES
--   sobre la tabla BNMODCf06_ESTADOS_CUENTA_PATH para mejorar
--   el rendimiento de las consultas que involucren los campos 
--   f06_dni, f06_age y f06_mes. 
--   Se recomienda ejecutarlo durante un período de baja actividad.
-- ============================================================

CREATE INDEX IDX_EC_PATH_DNI_AGE_MES 
ON BNMODCf06_ESTADOS_CUENTA_PATH (f06_dni, f06_age, f06_mes) ;  
 

-- ============================================================
-- Notas:
-- 1. Verificar que el índice se haya creado correctamente.
-- 2. Monitorear el rendimiento del servidor durante la ejecución.
-- 3. Este script debe ejecutarse en un entorno de producción fuera de horas pico.
-- ============================================================
