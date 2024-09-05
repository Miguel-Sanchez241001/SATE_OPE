
CREATE TABLE BNSATE19_EST_ASIGNACION (
    B019_EASI NUMBER(13) PRIMARY KEY,            -- ID generado por la secuencia
    B04_ID_CAS NUMBER(6),         -- Columna de clave foránea de 6 dígitos
    B019_ESTADO NUMBER(1),        -- Columna de 1 dígito
    B019_FEC_REGISTRO DATE                            -- Columna de fecha en formato DD/MM/YYYY
);