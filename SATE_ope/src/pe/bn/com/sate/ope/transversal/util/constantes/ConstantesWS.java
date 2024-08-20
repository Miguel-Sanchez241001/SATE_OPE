package pe.bn.com.sate.ope.transversal.util.constantes;

import java.util.HashMap;
import java.util.Map;

public class ConstantesWS {
		public static final String COD_EMISOR = "[COD_EMISOR]";				// Dinamico/Fijo => DESARROLLO
		public static final String COD_USUARIO = "[COD_USUARIO]";			// Dinamico/Fijo => DESARROLLO
		public static final String NUM_TERMINAL = "[NUM_TERMINAL]";			// Dinamico/Fijo => DESARROLLO
		public static final String NUM_REFERENCIA = "[NUM_REFERENCIA]";		// Dinamico => Autoincremental
		public static final String ORGANIZACION = "[ORGANIZACION]";			// Dinamico => Frontend/Sistema
		public static final String NUM_TARJETA = "[NUM_TARJETA]"; 			// Dinamico => Frontend/Sistema
		public static final String FECHA_EXPIRACION = "[FECHA_EXPIRACION]";	// Dinamico => Frontend/Sistema
		public static final String CODIGO_BLOQUEO = "[CODIGO_BLOQUEO]";		// Dinamico => Frontend/Sistema
		public static final String MOTIVO_BLOQUEO = "[MOTIVO_BLOQUEO]";		// Dinamico => Frontend/Sistema
		public static final String COMERCIO = "[COMERCIO]"; 				// Dinamico/Fijo => DESARROLLO
	public static final String FECHA_TXN_TERMINAL = "[FECHA_TXN_TERMINAL]"; // Dinamico => Frontend/Sistema YYYYMMDD                         
	public static final String HORA_TXN_TERMINAL = "[HORA_TXN_TERMINAL]";	// Dinamico => Frontend/Sistema  HHMMSS
	public static final String WS_USUARIO = "[WS_USUARIO]";					// Fijo => Parametros Comp
	public static final String WS_CLAVE = "[WS_CLAVE]";						// Fijo => Parametros Comp
	public static final String RESERVADO = "[RESERVADO]";					// Fijo => vacio
	public static final String MONEDA = "[MONEDA]";							// Fijo => 604:Soles
	public static final String NRO_DOCUMENTO = "[NRO_DOCUMENTO]";			// Dinamico => Frontend/Sistema
	public static final String CORREO_ELECTRONICO = "[CORREO_ELECTRONICO]";	// Dinamico => Frontend/Sistema
	public static final String NRO_CELULAR = "[NRO_CELULAR]";				// Dinamico => Frontend/Sistema
	
	public static final String COD_MONEDA = "604";				 

	// Constante para Bloqueo_Tarjeta
	public static final String BLOQUEO_TARJETA_XML = "<![CDATA[\n"
			+ "<Bloqueo_Tarjeta>\n" + "  <CodEmisor>[COD_EMISOR]</CodEmisor>\n"
			+ "  <CodUsuario>[COD_USUARIO]</CodUsuario>\n"
			+ "  <NumTerminal>[NUM_TERMINAL]</NumTerminal>\n"
			+ "  <NumReferencia>[NUM_REFERENCIA]</NumReferencia>\n"
			+ "  <Organizacion>[ORGANIZACION]</Organizacion>\n"
			+ "  <NumTarjeta>[NUM_TARJETA]</NumTarjeta>\n"
			+ "  <FechaExpiracion>[FECHA_EXPIRACION]</FechaExpiracion>\n"
			+ "  <CodigoBloqueo>[CODIGO_BLOQUEO]</CodigoBloqueo>\n"
			+ "  <MotivoBloqueo>[MOTIVO_BLOQUEO]</MotivoBloqueo>\n"
			+ "  <Comercio>[COMERCIO]</Comercio>\n"
			+ "  <FechaTxnTerminal>[FECHA_TXN_TERMINAL]</FechaTxnTerminal>\n"
			+ "  <HoraTxnTerminal>[HORA_TXN_TERMINAL]</HoraTxnTerminal>\n"
			+ "  <WSUsuario>[WS_USUARIO]</WSUsuario>\n"
			+ "  <WSClave>[WS_CLAVE]</WSClave>\n"
			+ "  <Reservado>[RESERVADO]</Reservado>\n"
			+ "</Bloqueo_Tarjeta>]]>";

	// Constante para Informacion_Tarjeta
	public static final String INFORMACION_TARJETA_XML = "<![CDATA[\n"
			+ "<Informacion_Tarjeta>\n"
			+ "  <CodEmisor>[COD_EMISOR]</CodEmisor>\n"
			+ "  <CodUsuario>[COD_USUARIO]</CodUsuario>\n"
			+ "  <NumTerminal>[NUM_TERMINAL]</NumTerminal>\n"
			+ "  <NumReferencia>[NUM_REFERENCIA]</NumReferencia>\n"
			+ "  <NumTarjeta>[NUM_TARJETA]</NumTarjeta>\n"
			+ "  <FechaExpiracion>[FECHA_EXPIRACION]</FechaExpiracion>\n"
			+ "  <Comercio>[COMERCIO]</Comercio>\n"
			+ "  <FechaTxnTerminal>[FECHA_TXN_TERMINAL]</FechaTxnTerminal>\n"
			+ "  <HoraTxnTerminal>[HORA_TXN_TERMINAL]</HoraTxnTerminal>\n"
			+ "  <WSUsuario>[WS_USUARIO]</WSUsuario>\n"
			+ "  <WSClave>[WS_CLAVE]</WSClave>\n"
			+ "  <Reservado>[RESERVADO]</Reservado>\n"
			+ "</Informacion_Tarjeta>]]>";

	// Constante para Consulta_Movimientos_Expediente
	public static final String CONSULTA_MOVIMIENTOS_EXPEDIENTE_XML = "<![CDATA[\n"
			+ "<Consulta_Movimientos_Expediente>\n"
			+ "  <CodEmisor>[COD_EMISOR]</CodEmisor>\n"
			+ "  <CodUsuario>[COD_USUARIO]</CodUsuario>\n"
			+ "  <NumTerminal>[NUM_TERMINAL]</NumTerminal>\n"
			+ "  <NumReferencia>[NUM_REFERENCIA]</NumReferencia>\n"
			+ "  <Organizacion>[ORGANIZACION]</Organizacion>\n"
			+ "  <NumTarjeta>[NUM_TARJETA]</NumTarjeta>\n"
			+ "  <FechaExpiracion>[FECHA_EXPIRACION]</FechaExpiracion>\n"
			+ "  <Comercio>[COMERCIO]</Comercio>\n"
			+ "  <Moneda>[MONEDA]</Moneda>\n"
			+ "  <FechaTxnTerminal>[FECHA_TXN_TERMINAL]</FechaTxnTerminal>\n"
			+ "  <HoraTxnTerminal>[HORA_TXN_TERMINAL]</HoraTxnTerminal>\n"
			+ "  <WSUsuario>[WS_USUARIO]</WSUsuario>\n"
			+ "  <WSClave>[WS_CLAVE]</WSClave>\n"
			+ "  <Reservado>[RESERVADO]</Reservado>\n"
			+ "</Consulta_Movimientos_Expediente>]]>";

	// Constante para Consulta_Datos_Expediente
	public static final String CONSULTA_DATOS_EXPEDIENTE_XML = "<![CDATA[\n"
			+ "<Consulta_Datos_Expediente>\n"
			+ "  <CodEmisor>[COD_EMISOR]</CodEmisor>\n"
			+ "  <CodUsuario>[COD_USUARIO]</CodUsuario>\n"
			+ "  <NumTerminal>[NUM_TERMINAL]</NumTerminal>\n"
			+ "  <NumReferencia>[NUM_REFERENCIA]</NumReferencia>\n"
			+ "  <Organizacion>[ORGANIZACION]</Organizacion>\n"
			+ "  <NumTarjeta>[NUM_TARJETA]</NumTarjeta>\n"
			+ "  <FechaExpiracion>[FECHA_EXPIRACION]</FechaExpiracion>\n"
			+ "  <Comercio>[COMERCIO]</Comercio>\n"
			+ "  <FechaTxnTerminal>[FECHA_TXN_TERMINAL]</FechaTxnTerminal>\n"
			+ "  <HoraTxnTerminal>[HORA_TXN_TERMINAL]</HoraTxnTerminal>\n"
			+ "  <WSUsuario>[WS_USUARIO]</WSUsuario>\n"
			+ "  <WSClave>[WS_CLAVE]</WSClave>\n"
			+ "  <Reservado>[RESERVADO]</Reservado>\n"
			+ "</Consulta_Datos_Expediente>]]>";

	// Constante para Actualizacion_Datos
	public static final String ACTUALIZACION_DATOS_XML = "<![CDATA[\n"
			+ "<Actualizacion_Datos>\n"
			+ "  <CodEmisor>[COD_EMISOR]</CodEmisor>\n"
			+ "  <CodUsuario>[COD_USUARIO]</CodUsuario>\n"
			+ "  <NumTerminal>[NUM_TERMINAL]</NumTerminal>\n"
			+ "  <NumReferencia>[NUM_REFERENCIA]</NumReferencia>\n"
			+ "  <Organizacion>[ORGANIZACION]</Organizacion>\n"
			+ "  <NroDocumento>[NRO_DOCUMENTO]</NroDocumento>\n"
			+ "  <CorreoElectronico>[CORREO_ELECTRONICO]</CorreoElectronico>\n"
			+ "  <NroCelular>[NRO_CELULAR]</NroCelular>\n"
			+ "  <FechaExpiracion>[FECHA_EXPIRACION]</FechaExpiracion>\n"
			+ "  <Comercio>[COMERCIO]</Comercio>\n"
			+ "  <FechaTxnTerminal>[FECHA_TXN_TERMINAL]</FechaTxnTerminal>\n"
			+ "  <HoraTxnTerminal>[HORA_TXN_TERMINAL]</HoraTxnTerminal>\n"
			+ "  <WSUsuario>[WS_USUARIO]</WSUsuario>\n"
			+ "  <WSClave>[WS_CLAVE]</WSClave>\n"
			+ "  <Reservado>[RESERVADO]</Reservado>\n"
			+ "</Actualizacion_Datos>]]>";

	public static String generarXml(String template, Map<String, String> valores) {
		String xml = template;
		for (Map.Entry<String, String> entry : valores.entrySet()) {
			xml = xml.replace(entry.getKey(), entry.getValue());
		}
		return xml;
	}
	 /**
     * Retorna un `Map` que contiene todos los `Map` específicos para cada operación.
     * La clave del `Map` externo es el nombre de la operación y el valor es el `Map`
     * que contiene las claves y valores asociados a esa operación.
     *
     * Las operaciones incluyen:
     * - Informacion_Tarjeta
     * - Bloqueo_Tarjeta
     * - Consulta_Movimientos_Expediente
     * - Consulta_Datos_Expediente
     * - Actualizacion_Datos
     *
     * @return un `Map` con todos los `Map` específicos para cada operación.
     */
	public static Map<String, Map<String, String>> getAllOperationsMaps() {
        Map<String, Map<String, String>> allOperations = new HashMap<String, Map<String, String>>();

        // Agregar cada operación con su correspondiente Map
        allOperations.put("Informacion_Tarjeta", getInformacionTarjetaMap());
        allOperations.put("Bloqueo_Tarjeta", getBloqueoTarjetaMap());
        allOperations.put("Consulta_Movimientos_Expediente", getConsultaMovimientosExpedienteMap());
        allOperations.put("Consulta_Datos_Expediente", getConsultaDatosExpedienteMap());
        allOperations.put("Actualizacion_Datos", getActualizacionDatosMap());

        return allOperations;
    }
	  /**
     * Retorna un `Map` con los valores predeterminados para la operación `Informacion_Tarjeta`.
     *
     * Claves incluidas:
     * - COD_EMISOR: "971"
     * - COD_USUARIO: "TW9999"
     * - NUM_TERMINAL: "11010101"
     * - NUM_REFERENCIA: "AC2020000322"
     * - NUM_TARJETA: "000000009"
     * - FECHA_EXPIRACION: "2701"
     * - COMERCIO: "9999999"
     * - FECHA_TXN_TERMINAL: "20160224"
     * - HORA_TXN_TERMINAL: "172020"
     * - WS_USUARIO: "prueba1234"
     * - WS_CLAVE: "prueba1234567890"
     * - RESERVADO: ""
     *
     * @return un `Map` con las claves y valores para `Informacion_Tarjeta`.
     */
    public static Map<String, String> getInformacionTarjetaMap() {
        Map<String, String> informacionTarjetaMap = new HashMap<String, String>();
        informacionTarjetaMap.put(COD_EMISOR, "971");
        informacionTarjetaMap.put(COD_USUARIO, "TW9999");
        informacionTarjetaMap.put(NUM_TERMINAL, "11010101");
        informacionTarjetaMap.put(NUM_REFERENCIA, "AC2020000322");
        informacionTarjetaMap.put(NUM_TARJETA, "000000009");
        informacionTarjetaMap.put(FECHA_EXPIRACION, "2701");
        informacionTarjetaMap.put(COMERCIO, "9999999");
        informacionTarjetaMap.put(FECHA_TXN_TERMINAL, "20160224");
        informacionTarjetaMap.put(HORA_TXN_TERMINAL, "172020");
        informacionTarjetaMap.put(WS_USUARIO, "prueba1234");
        informacionTarjetaMap.put(WS_CLAVE, "prueba1234567890");
        informacionTarjetaMap.put(RESERVADO, "");
        return informacionTarjetaMap;
    }
    /**
     * Retorna un `Map` con los valores predeterminados para la operación `Bloqueo_Tarjeta`.
     *
     * Claves incluidas:
     * - COD_EMISOR: "971"
     * - COD_USUARIO: "TW9999"
     * - NUM_TERMINAL: "11010101"
     * - NUM_REFERENCIA: "AC2020000322"
     * - ORGANIZACION: "941"
     * - NUM_TARJETA: "000000009"
     * - FECHA_EXPIRACION: "2701"
     * - CODIGO_BLOQUEO: "A3"
     * - MOTIVO_BLOQUEO: "Robo"
     * - COMERCIO: "9999999"
     * - FECHA_TXN_TERMINAL: "20160224"
     * - HORA_TXN_TERMINAL: "172020"
     * - WS_USUARIO: "prueba1234"
     * - WS_CLAVE: "prueba1234567890"
     * - RESERVADO: ""
     *
     * @return un `Map` con las claves y valores para `Bloqueo_Tarjeta`.
     */
    public static Map<String, String> getBloqueoTarjetaMap() {
        Map<String, String> bloqueoTarjetaMap = new HashMap<String, String>();
        bloqueoTarjetaMap.put(COD_EMISOR, "971");
        bloqueoTarjetaMap.put(COD_USUARIO, "TW9999");
        bloqueoTarjetaMap.put(NUM_TERMINAL, "11010101");
        bloqueoTarjetaMap.put(NUM_REFERENCIA, "AC2020000322");
        bloqueoTarjetaMap.put(ORGANIZACION, "941");
        bloqueoTarjetaMap.put(NUM_TARJETA, "000000009");
        bloqueoTarjetaMap.put(FECHA_EXPIRACION, "2701");
        bloqueoTarjetaMap.put(CODIGO_BLOQUEO, "A3");
        bloqueoTarjetaMap.put(MOTIVO_BLOQUEO, "Robo");
        bloqueoTarjetaMap.put(COMERCIO, "9999999");
        bloqueoTarjetaMap.put(FECHA_TXN_TERMINAL, "20160224");
        bloqueoTarjetaMap.put(HORA_TXN_TERMINAL, "172020");
        bloqueoTarjetaMap.put(WS_USUARIO, "prueba1234");
        bloqueoTarjetaMap.put(WS_CLAVE, "prueba1234567890");
        bloqueoTarjetaMap.put(RESERVADO, "");
        return bloqueoTarjetaMap;
    }
    
    /**
     * Retorna un `Map` con los valores predeterminados para la operación `Consulta_Movimientos_Expediente`.
     *
     * Claves incluidas:
     * - COD_EMISOR: "941"
     * - COD_USUARIO: "CS00000001"
     * - NUM_TERMINAL: "12345678"
     * - NUM_REFERENCIA: "ORD20160224"
     * - ORGANIZACION: "941"
     * - NUM_TARJETA: "526983659"
     * - FECHA_EXPIRACION: ""
     * - COMERCIO: "2999994"
     * - MONEDA: "604"
     * - FECHA_TXN_TERMINAL: "20160224"
     * - HORA_TXN_TERMINAL: "172020"
     * - WS_USUARIO: "0944006748"
     * - WS_CLAVE: "dRUch4hupAvuduBE"
     * - RESERVADO: ""
     *
     * @return un `Map` con las claves y valores para `Consulta_Movimientos_Expediente`.
     */
    public static Map<String, String> getConsultaMovimientosExpedienteMap() {
        Map<String, String> consultaMovimientosExpedienteMap = new HashMap<String, String>();
        consultaMovimientosExpedienteMap.put(COD_EMISOR, "941");
        consultaMovimientosExpedienteMap.put(COD_USUARIO, "CS00000001");
        consultaMovimientosExpedienteMap.put(NUM_TERMINAL, "12345678");
        consultaMovimientosExpedienteMap.put(NUM_REFERENCIA, "ORD20160224");
        consultaMovimientosExpedienteMap.put(ORGANIZACION, "941");
        consultaMovimientosExpedienteMap.put(NUM_TARJETA, "526983659");
        consultaMovimientosExpedienteMap.put(FECHA_EXPIRACION, "");
        consultaMovimientosExpedienteMap.put(COMERCIO, "2999994");
        consultaMovimientosExpedienteMap.put(MONEDA, "604");
        consultaMovimientosExpedienteMap.put(FECHA_TXN_TERMINAL, "20160224");
        consultaMovimientosExpedienteMap.put(HORA_TXN_TERMINAL, "172020");
        consultaMovimientosExpedienteMap.put(WS_USUARIO, "0944006748");
        consultaMovimientosExpedienteMap.put(WS_CLAVE, "dRUch4hupAvuduBE");
        consultaMovimientosExpedienteMap.put(RESERVADO, "");
        return consultaMovimientosExpedienteMap;
    }
   
    /**
     * Retorna un `Map` con los valores predeterminados para la operación `Consulta_Datos_Expediente`.
     *
     * Claves incluidas:
     * - COD_EMISOR: "971"
     * - COD_USUARIO: "TW9999"
     * - NUM_TERMINAL: "11010101"
     * - NUM_REFERENCIA: "AC2020000322"
     * - ORGANIZACION: "941"
     * - NUM_TARJETA: "000000009"
     * - FECHA_EXPIRACION: "2701"
     * - COMERCIO: "9999999"
     * - FECHA_TXN_TERMINAL: "20160224"
     * - HORA_TXN_TERMINAL: "172020"
     * - WS_USUARIO: "prueba1234"
     * - WS_CLAVE: "prueba1234567890"
     * - RESERVADO: ""
     *
     * @return un `Map` con las claves y valores para `Consulta_Datos_Expediente`.
     */
    public static Map<String, String> getConsultaDatosExpedienteMap() {
        Map<String, String> consultaDatosExpedienteMap = new HashMap<String, String>();
        consultaDatosExpedienteMap.put(COD_EMISOR, "971");
        consultaDatosExpedienteMap.put(COD_USUARIO, "TW9999");
        consultaDatosExpedienteMap.put(NUM_TERMINAL, "11010101");
        consultaDatosExpedienteMap.put(NUM_REFERENCIA, "AC2020000322");
        consultaDatosExpedienteMap.put(ORGANIZACION, "941");
        consultaDatosExpedienteMap.put(NUM_TARJETA, "000000009");
        consultaDatosExpedienteMap.put(FECHA_EXPIRACION, "2701");
        consultaDatosExpedienteMap.put(COMERCIO, "9999999");
        consultaDatosExpedienteMap.put(FECHA_TXN_TERMINAL, "20160224");
        consultaDatosExpedienteMap.put(HORA_TXN_TERMINAL, "172020");
        consultaDatosExpedienteMap.put(WS_USUARIO, "prueba1234");
        consultaDatosExpedienteMap.put(WS_CLAVE, "prueba1234567890");
        consultaDatosExpedienteMap.put(RESERVADO, "");
        return consultaDatosExpedienteMap;
    }
    
    /**
     * Retorna un `Map` con los valores predeterminados para la operación `Actualizacion_Datos`.
     *
     * Claves incluidas:
     * - COD_EMISOR: "971"
     * - COD_USUARIO: "TW9999"
     * - NUM_TERMINAL: "11010101"
     * - NUM_REFERENCIA: "AC2020000322"
     * - ORGANIZACION: "941"
     * - NRO_DOCUMENTO: "74851254"
     * - CORREO_ELECTRONICO: "prueba@hotmail.com"
     * - NRO_CELULAR: "965845214"
     * - FECHA_EXPIRACION: "2701"
     * - COMERCIO: "9999999"
     * - FECHA_TXN_TERMINAL: "20160224"
     * - HORA_TXN_TERMINAL: "172020"
     * - WS_USUARIO: "prueba1234"
     * - WS_CLAVE: "prueba1234567890"
     * - RESERVADO: ""
     *
     * @return un `Map` con las claves y valores para `Actualizacion_Datos`.
     */
    public static Map<String, String> getActualizacionDatosMap() {
        Map<String, String> actualizacionDatosMap = new HashMap<String, String>();
        actualizacionDatosMap.put(COD_EMISOR, "971");
        actualizacionDatosMap.put(COD_USUARIO, "TW9999");
        actualizacionDatosMap.put(NUM_TERMINAL, "11010101");
        actualizacionDatosMap.put(NUM_REFERENCIA, "AC2020000322");
        actualizacionDatosMap.put(ORGANIZACION, "941");
        actualizacionDatosMap.put(NRO_DOCUMENTO, "74851254");
        actualizacionDatosMap.put(CORREO_ELECTRONICO, "prueba@hotmail.com");
        actualizacionDatosMap.put(NRO_CELULAR, "965845214");
        actualizacionDatosMap.put(FECHA_EXPIRACION, "2701");
        actualizacionDatosMap.put(COMERCIO, "9999999");
        actualizacionDatosMap.put(FECHA_TXN_TERMINAL, "20160224");
        actualizacionDatosMap.put(HORA_TXN_TERMINAL, "172020");
        actualizacionDatosMap.put(WS_USUARIO, "prueba1234");
        actualizacionDatosMap.put(WS_CLAVE, "prueba1234567890");
        actualizacionDatosMap.put(RESERVADO, "");
        return actualizacionDatosMap;
    }
	public static void main(String[] args) {
		// Obtener el Map de todas las operaciones
        Map<String, Map<String, String>> allOperationsMaps =  getAllOperationsMaps();

        // Ejemplo de cómo obtener el Map de "Informacion_Tarjeta"
        Map<String, String> informacionTarjetaMap = allOperationsMaps.get("Informacion_Tarjeta");

        // Modificar un valor en el Map de "Informacion_Tarjeta"
        informacionTarjetaMap.put(NUM_REFERENCIA, "AC2021000456");

        // Generar el XML usando el Map actualizado
        String xml = generarXml(INFORMACION_TARJETA_XML, informacionTarjetaMap);
        System.out.println(xml);

        // Similar para otros métodos...
        Map<String, String> bloqueoTarjetaMap = allOperationsMaps.get("Bloqueo_Tarjeta");
        bloqueoTarjetaMap.put(CODIGO_BLOQUEO, "B5");
        String xmlBloqueo = generarXml(BLOQUEO_TARJETA_XML, bloqueoTarjetaMap);
        System.out.println(xmlBloqueo);
	}
}
