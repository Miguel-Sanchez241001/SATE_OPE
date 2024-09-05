create or replace PROCEDURE "BNPD_00_SOLICITUD_GEN_1"
IS
		  -- Declaración de variables
CURSOR c_solicitudes IS

		SELECT
	-- CABECERA DE CADA TRAMA   -- 74
		LPAD(
			LPAD(UPPER('B'), 1, '0') || -- Tipo de Registro
			LPAD(UPPER('0302'), 4, '0') || -- solicitud
			LPAD(UPPER('0'), 16, '0') || -- Bit map de campos
			LPAD(UPPER('19'), 2, '0') || -- long DE02
			LPAD(UPPER('0'), 19, '0') || -- DE02 - Numero de Cuenta
		   TO_CHAR(SYSDATE, 'MMDDHHMISS')   -- DE07 - Fecha y Hora:
			-- TO_CHAR(TO_DATE('2024-02-23', 'YYYY-MM-DD'), 'MMDDHHMISS')
			, 52, '0') AS TRAMA1,
		LPAD(UPPER(GENERAR_TRACE()), 6, '0')  -- DE11 - Trace
		AS TRAMA2,
	   RPAD(
			LPAD(UPPER('11'), 2, '0') || -- long DE33 valor = "11"
			LPAD(UPPER('00000000000'), 11, '0') || -- DE33 valor = "00000000000"
		   -- LPAD(UPPER('926'), 3, '0') || -- long DE48 valor = "926"
			LPAD(UPPER('947'), 3, '0') || -- long DE48 valor = "947"
			-- ESTRUCTURA   TRAMA
			LPAD(UPPER('0101'), 4, '0') ||                             -- Transaccion-type = "0101"
			LPAD(UPPER('0'), 1, '0') ||                                -- Data compres = "0"
			LPAD(UPPER('0000'), 4, '0') ||                             -- Data sequent = "0000"
			LPAD(UPPER('N'), 1, '0') ||                                -- more data = "N"
			LPAD(UPPER('0'), 15, '0') ||                               -- Código BT = 000000000000000
			   -- CLIENTE
			LPAD(UPPER(NVL(cli.B06_TIPO_DOCUMENTO, 0)), 1, '0') ||     -- Tipo de documento
			LPAD(UPPER(NVL(cli.B06_NUM_DOCUMENTO, 0)), 12, '0') ||     -- Numero de documento
			RPAD(UPPER(NVL(TRIM(cli.B06_APPATERNO) || ' ' ||
			TRIM(cli.B06_APMATERNO), '0')), 30, ' ') ||          -- Apellidos funcionario
			RPAD(UPPER(NVL(cli.B06_NOMBRES, '0')), 30, ' ') ||         -- Nombre funcionario
			UPPER(NVL(cli.B06_EST_CIVIL, ' ')) ||                      -- Estado Civil
			UPPER(NVL(cli.B06_SEXO, ' ')) ||                           -- Sexo
			RPAD(UPPER(NVL(tar.B05_EMAIL, '0')), 57, ' ') ||           -- E-Mail
			-- CLIENTE DOMICILIO
			LPAD(UPPER(' '), 5, ' ') ||  -- Dirección domicilio: Tipo de Via
			LPAD(UPPER(NVL(cli.B06_DIRECCION,' ')), 30, ' ') ||  -- Dirección domicilio: Nombre de Via
			LPAD(UPPER(' '), 6, ' ') ||  -- Dirección domicilio: Número de Via/Calle
			LPAD(UPPER(' '), 6, ' ') ||  -- Dirección domicilio: Departamento
			LPAD(UPPER('0'), 5, '0') ||  -- Dirección domicilio: Oficina
			LPAD(UPPER('0'), 3, '0') ||  -- Dirección domicilio: Piso
			LPAD(UPPER('0'), 3, '0') ||  -- Dirección domicilio: Manzana
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección domicilio: Lote
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección domicilio: Interior
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección domicilio: Sector
			LPAD(UPPER(' '), 5, ' ') ||  -- Dirección domicilio: Kilometro
			LPAD(UPPER(' '), 5, ' ') ||  -- Dirección domicilio: Código de zona
			LPAD(UPPER(NVL(cli.B06_DIRECCION_MC,' ')), 30, ' ') ||  -- Dirección domicilio: Nombre de zona
			LPAD(UPPER(NVL(cli.B06_UBIGEO, 0)), 9, '0') ||  -- UBIGEO
			RPAD(UPPER(NVL(cli.B06_REFERENCIA, ' ')), 55, ' ') ||  -- REFERENCIA
			LPAD(REPLACE(NVL(cli.B06_TELEF_CASA, 0),'-', ''  ), 10, '0') ||  -- TELEFONO
			LPAD(UPPER(NVL(tar.B05_NUM_CELULAR, 0)), 10, '0') ||  -- NUMERO
			-- EMPRESA
			RPAD(UPPER(NVL(emp.B00_RAZON_SOCIAL, ' ')), 30, ' ') ||  -- Nombre empresa
			RPAD(UPPER(NVL(codeje.B09_NOMBRE, ' ')), 26, ' ') ||  -- Nombre empresa CORTO
			-- EMPRESA DIRECCION
			LPAD(UPPER('0'), 5, '0') ||                              -- Dirección trabajo: Tipo de Via
			RPAD(UPPER(NVL(emp.B00_DIRECCION, ' ')), 30, ' ') ||   -- Dirección trabajo: Nombre de Via
			LPAD(UPPER(' '), 6, ' ') ||  -- Dirección trabajo: Número de Via/Calle
			LPAD(UPPER(' '), 6, ' ') ||  -- Dirección trabajo: Departamento
			LPAD(UPPER('0'), 5, '0') ||  -- Dirección trabajo: Oficina
			LPAD(UPPER('0'), 3, '0') ||  -- Dirección trabajo: Piso
			LPAD(UPPER('0'), 3, '0') ||  -- Dirección trabajo: Manzana
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección trabajo: Lote
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección trabajo: Interior
			LPAD(UPPER(' '), 3, ' ') ||  -- Dirección trabajo: Sector
			LPAD(UPPER(' '), 5, ' ') ||  -- Dirección trabajo: Kilometro
			LPAD(UPPER(' '), 5, ' ') ||  -- Dirección trabajo: Código de zona
			LPAD(UPPER(NVL(emp.B00_DIRECCION_MC, ' ')), 30, ' ') ||  -- Dirección trabajo: Nombre de zona
			 LPAD(NVL(emp.B00_UBIGEO, 0), 9, '0') ||  -- Dirección trabajo: Ubigeo
		  RPAD(UPPER(NVL(emp.B00_REFERENCIA, ' ')), 55, ' ') || -- Dirección trabajo: referencia
		  LPAD(REPLACE(NVL(emp.B00_TELEFONO, 0) , '-', ''  ), 10, '0') || -- Dirección trabajo: TELEFONO
		  LPAD(UPPER(' '), 4, ' ') || -- Dirección trabajo: Telefono Anexo
		  LPAD(UPPER(' '), 30, ' ') || -- Nombre conyuge
		  LPAD(UPPER(' '), 30, ' ') || -- Nombre trabajo de conyugue
		  LPAD(UPPER('0'), 10, '0') || -- Nombre telefono de conyugue
		  LPAD(UPPER(' '), 4, ' ') || -- Nombre anexo de telefono de conyugue
		  LPAD(UPPER(' '), 55, ' ') || -- Nombre referencia personal
		  LPAD(UPPER('3'), 1, ' ') || -- Indicador de envio de correo (Carrier Route)
		  LPAD(UPPER('001'), 3, ' ') || -- Logo
		  LPAD(UPPER('000000000'), 9, '0') || -- Línea de crédito
		  LPAD(UPPER('604'), 3, '0') || -- Moneda de cuenta
		  LPAD(UPPER('00'), 2, '0') || -- Ciclo de cuenta
		  RPAD(UPPER(NVL(TO_CHAR(cli.B06_FEC_NACIMIENTO, 'YYYYMMDD'),' ')), 8, ' ') || -- Fecha de nacimiento
		  LPAD(UPPER('0'), 9, '0') || -- Sueldo
		  LPAD(UPPER('STD'), 3, '0') || -- PCT
		  LPAD(UPPER('0000'), 4, ' ') || -- CDR origen
		  LPAD(UPPER('00000'), 5, ' ') || -- Código de funcionario
		  LPAD(UPPER('Y'), 1, ' ') || -- Limite de disposición de efectivo
		  LPAD(UPPER('3'), 1, ' ') || -- Indicador de envio de EECC
		  LPAD(NVL(codeje.B09_COD_INTERNO,'0'), 4, '0') || -- Código de unidad ejecutora
		  LPAD(UPPER(' '), 5, ' ') || -- Dirección envío de EECC: Tipo de Via
		  LPAD(UPPER(' '), 30, ' ') || -- Dirección envío de EECC: Nombre de Via
		  LPAD(UPPER(' '), 6, ' ') || -- Dirección envío de EECC: Número de Via/Calle
		  LPAD(UPPER(' '), 6, ' ') || -- Dirección envío de EECC: Departamento
		  LPAD(UPPER(' '), 5, ' ') || -- Dirección envío de EECC: Oficina
		  LPAD(UPPER(' '), 3, ' ') || -- Dirección envío de EECC: Piso
		  LPAD(UPPER(' '), 3, ' ') || -- Dirección envío de EECC: Manzana
		  LPAD(UPPER(' '), 3, ' ') ||   -- Dirección envío de EECC: Lote
		  LPAD(UPPER(' '), 3, ' ') ||    -- Dirección envío de EECC: Interior
		  LPAD(UPPER(' '), 3, ' ') ||   -- Dirección envío de EECC: Sector
		  LPAD(UPPER(' '), 5, ' ') ||   -- Dirección envío de EECC: Kilometro
		  LPAD(UPPER(' '), 5, ' ') ||   -- Dirección envío de EECC: Código de zona
		  LPAD(UPPER(' '), 30, ' ') ||  -- Dirección envío de EECC: Nombre de zona
		  LPAD(UPPER('0') ,9, '0') ||   -- Dirección envío de EECC: Ubigeo
		  LPAD(UPPER(' '), 55, ' ') ||  -- Dirección envío de EECC: Referencia
		  LPAD(UPPER('N'), 1, '0') ||   -- Indicador de Cargo
		  LPAD(UPPER('0'), 1, '0') ||   -- Indicador de disposición de efectivo (botones)
		  LPAD(UPPER('0'), 1, '0') ||   -- Indicador de sobregiro (botones)
		  LPAD(UPPER('0'), 1, '0') ||   -- Indicador de compras en el exterior (botones)
		  LPAD(UPPER('0'), 1, '0')      -- Indicador de compras web (botones)
		  , 963, '0') AS TRAMA3,
	tar.B05_ID_TAR as ID_TARJETA
	FROM BN_SATE.BNSATE05_TARJETA tar
	JOIN BN_SATE.BNSATE06_CLIENTE cli
	  ON tar.B06_ID_CLI = cli.B06_ID_CLI
	JOIN BN_SATE.BNSATE00_EMPRESA emp
	  ON tar.B00_ID_EMP=emp.B00_ID_EMP
	  
	JOIN BN_SATE.BNSATE09_NOMBRE_CORTO codeje
	  ON emp.B00_NUM_RUC=codeje.B09_NUM_RUC
	  
	JOIN BN_SATE.BNSATE07_EST_TARJETA eta
	  ON eta.B05_ID_TAR = tar.B05_ID_TAR
	INNER JOIN (SELECT tar1.B05_ID_TAR, MAX(eta1.B07_FEC_REGISTRO) B07_FEC_REGISTRO FROM BN_SATE.BNSATE05_TARJETA tar1
	JOIN BNSATE07_EST_TARJETA eta1 ON eta1.B05_ID_TAR = tar1.B05_ID_TAR
	 GROUP BY tar1.B05_ID_TAR) q ON tar.B05_ID_TAR = q.B05_ID_TAR AND eta.B07_FEC_REGISTRO = q.B07_FEC_REGISTRO
	 WHERE eta.B07_ESTADO = 2 ;

		v_total_registros NUMBER := 0; -- Inicializamos la variable a cero

	BEGIN
	  -- Sumar uno a la variable en cada iteración del bucle
	   FOR FILA IN c_solicitudes LOOP
		  v_total_registros := v_total_registros + 1;
	   END LOOP;

	 -- PRIMERA LINEA HEADER A19
	   dbms_output.put_line(
	   'A' ||                               --Tipo de Registro "A"
	   '019' ||                             -- Código de emisor: "019"
	   TO_CHAR(SYSDATE, 'DDMMYYYY')    ||   -- FECHA ACTUAL
	  -- TO_CHAR(TO_DATE('2024-02-23', 'YYYY-MM-DD'), 'DDMMYYYY') ||
	   LPAD(v_total_registros, 6, '0') ||   -- Número Total de Registros de Datos
	   LPAD(' ',982 , ' '));

	   -- REGISTRANDO TRAMAS TXT
	   FOR FILA IN c_solicitudes LOOP
		  INSERT INTO BNSATE18_TRACE_TRAMA (B18_TRACE_CODE, B18_FECHA, B18_DATOS)
			VALUES (FILA.TRAMA2, SYSDATE, FILA.TRAMA1||FILA.TRAMA2||FILA.TRAMA3);
	   dbms_output.put_line(FILA.TRAMA1||FILA.TRAMA2||FILA.TRAMA3);
	   END LOOP;
END BNPD_00_SOLICITUD_GEN_1;
/


CREATE OR REPLACE PROCEDURE BNPD_13_ASIGNACION_GEN_1 IS

    CURSOR c_one_asignation IS
        WITH MaxDateRecords AS (
            SELECT
                COUNT(*) AS estCount,
                B04_ID_CAS,
                MAX(B019_FEC_REGISTRO) AS MAX_FECHA
            FROM
                BNSATE19_EST_ASIGNACION
            GROUP BY
                B04_ID_CAS
        )
        SELECT
            LPAD(
                LPAD(UPPER('B'), 1, '0') ||
                LPAD(UPPER('0302'), 4, '0') ||
                LPAD(UPPER('4220000080010000'), 16, '0') ||
                LPAD(UPPER('19'), 2, '0') ||
                LPAD(NVL(tar.b05_num_cuenta, '9'), 19, '0') ||
                TO_CHAR(SYSDATE, 'MMDDHHMISS') || 
                LPAD(
                    (SELECT COALESCE(b18_trace_code, '0') FROM bnsate18_trace_trama WHERE b05_num_tarjeta = tar.b05_num_tarjeta), 6, '0') ||
                LPAD(UPPER('11'), 2, '0') ||
                LPAD(UPPER('00000000000'), 11, '0') ||
                LPAD(UPPER('081'), 3, '0')
             , 74, '0') AS PART1,
            RPAD(LPAD('0117', 4, '0') || '0' || '0000' || 'N' || asi.b04_linea || TO_CHAR(asi.b04_fecha_inicio_linea, 'DDMMYYYY') || TO_CHAR(asi.b04_fecha_fin_linea, 'DDMMYYYY') || 'N' || 'N' || '0050000', 926, ' ') AS linea
        FROM
            BNSATE04_ASIGNACION asi
        JOIN
            bnsate05_tarjeta tar
            ON tar.b05_id_tar = asi.b05_id_tar
        JOIN
            BNSATE19_EST_ASIGNACION etasi
            ON etasi.B04_ID_CAS = asi.B04_ID_CAS
            AND etasi.B019_FEC_REGISTRO = (
                SELECT MAX_FECHA
                FROM MaxDateRecords
                WHERE MaxDateRecords.B04_ID_CAS = asi.B04_ID_CAS
                  AND MaxDateRecords.estCount = 1
            )
        JOIN
            (SELECT COUNT(*) AS AsigCount, B05_ID_TAR, MAX(B04_FECHA_REGISTRO) AS B04_FECHA_REGISTRO
             FROM BNSATE04_ASIGNACION
             GROUP BY B05_ID_TAR) q
            ON q.B05_ID_TAR = asi.B05_ID_TAR
         WHERE
            etasi.B019_ESTADO = '1'
            AND q.AsigCount = 1
        ORDER BY tar.b05_num_cuenta;

    CURSOR c_two_asignation IS
        -- Similar structure as c_one_asignation
        WITH MaxDateRecords AS (
            SELECT
                COUNT(*) AS estCount,
                B04_ID_CAS,
                MAX(B019_FEC_REGISTRO) AS MAX_FECHA
            FROM
                BNSATE19_EST_ASIGNACION
            GROUP BY
                B04_ID_CAS
        ),
        NumberSeries AS (
            SELECT LEVEL AS repetition
            FROM DUAL
            CONNECT BY LEVEL <= 4
        )
        SELECT
            LPAD(
                LPAD(UPPER('B'), 1, '0') ||
                LPAD(UPPER('0302'), 4, '0') ||
                LPAD(UPPER('4220000080010000'), 16, '0') ||
                LPAD(UPPER('19'), 2, '0') ||
                LPAD(NVL(tar.b05_num_cuenta, '9'), 19, '0') ||
                TO_CHAR(SYSDATE, 'MMDDHHMISS') ||
                LPAD(
                    (SELECT COALESCE(b18_trace_code, '0') FROM bnsate18_trace_trama WHERE b05_num_tarjeta = tar.b05_num_tarjeta), 6, '0') ||
                LPAD(UPPER('11'), 2, '0') ||
                LPAD(UPPER('00000000000'), 11, '0') ||
                LPAD(UPPER('081'), 3, '0')
             , 74, '0') AS PART1,
             CASE
                WHEN n.repetition = 1 THEN RPAD(LPAD('0119', 4, '0') || '0' || '0000' || 'N' || '1017' || '009'  || asi.b04_linea , 926, ' ')
                WHEN n.repetition = 2 THEN RPAD(LPAD('0119', 4, '0') || '0' || '0000' || 'N' || '1119' || '008'  || TO_CHAR(asi.b04_fecha_inicio_linea, 'DDMMYYYY') , 926, ' ')
                WHEN n.repetition = 3 THEN RPAD(LPAD('0119', 4, '0') || '0' || '0000' || 'N' || '1120' || '008'  || TO_CHAR(asi.b04_fecha_fin_linea, 'DDMMYYYY') , 926, ' ')
                WHEN n.repetition = 4 THEN RPAD(LPAD('0119', 4, '0') || '0' || '0000' || 'N' || '1123' || '007'  || '0050000', 926, ' ')
            END AS linea
        FROM
            BNSATE04_ASIGNACION asi
        JOIN
            bnsate05_tarjeta tar
            ON tar.b05_id_tar = asi.b05_id_tar
        JOIN
            BNSATE19_EST_ASIGNACION etasi
            ON etasi.B04_ID_CAS = asi.B04_ID_CAS
            AND etasi.B019_FEC_REGISTRO = (
                SELECT MAX_FECHA
                FROM MaxDateRecords
                WHERE MaxDateRecords.B04_ID_CAS = asi.B04_ID_CAS
                  AND MaxDateRecords.estCount = 1
            )
        JOIN
            (SELECT COUNT(*) AS AsigCount, B05_ID_TAR, MAX(B04_FECHA_REGISTRO) AS B04_FECHA_REGISTRO
             FROM BNSATE04_ASIGNACION
             GROUP BY B05_ID_TAR) q
            ON q.B05_ID_TAR = asi.B05_ID_TAR
        CROSS JOIN
            NumberSeries n
         WHERE
            etasi.B019_ESTADO = '1'
            AND q.AsigCount >= 2
        ORDER BY tar.b05_num_cuenta , n.repetition;

    -- Variables para almacenar las líneas obtenidas del cursor
    v_linea1 VARCHAR2(2000);
    v_linea2 VARCHAR2(2000);

    -- Variable para contar el número de registros
    v_numero_registros NUMBER := 0;

BEGIN
    DBMS_OUTPUT.ENABLE(1000000);
    
    -- Contar los registros antes de la apertura del cursor
    OPEN c_one_asignation;
    LOOP
        FETCH c_one_asignation INTO v_linea1, v_linea2;
        EXIT WHEN c_one_asignation%NOTFOUND;
        v_numero_registros := v_numero_registros + 1;
    END LOOP;
    CLOSE c_one_asignation;

    OPEN c_two_asignation;
    LOOP
        FETCH c_two_asignation INTO v_linea1, v_linea2;
        EXIT WHEN c_two_asignation%NOTFOUND;
        v_numero_registros := v_numero_registros + 1;
    END LOOP;
    CLOSE c_two_asignation;

    -- Primera línea de encabezado (header) con formato específico
    DBMS_OUTPUT.PUT_LINE('A' || '019' ||
        TO_CHAR(SYSDATE, 'DDMMYYYY') ||
        LPAD(v_numero_registros, 6, '0') || LPAD(' ', 982, ' '));

    OPEN c_one_asignation;
    LOOP
        FETCH c_one_asignation INTO v_linea1, v_linea2;
        EXIT WHEN c_one_asignation%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_linea1 || v_linea2);  -- Imprimir la línea generada
    END LOOP;
    CLOSE c_one_asignation;

    OPEN c_two_asignation;
    LOOP
        FETCH c_two_asignation INTO v_linea1, v_linea2;
        EXIT WHEN c_two_asignation%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_linea1 || v_linea2);  -- Imprimir la línea generada
    END LOOP;
    CLOSE c_two_asignation;

END BNPD_13_ASIGNACION_GEN_1;
/


create or replace PROCEDURE "BNPD_14_ASIGNACION_GEN_2"
IS
BEGIN
    
	
	
END BNPD_14_ASIGNACION_GEN_2;