package pe.bn.com.sate.ope.infrastructure.facade;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import com.ibm.wsspi.webservices.Constants; // Importa las constantes relevantes

import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pe.bn.com.sate.ope.application.view.AutorizarSolicitudesController;
import pe.bn.com.sate.ope.infrastructure.exception.ExternalServiceMCProcesosException;
import pe.bn.com.sate.ope.infrastructure.exception.InternalServiceException;
import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.service.external.domain.mc.BasicHttpsBinding_IService1Proxy;
import pe.bn.com.sate.ope.persistence.mapper.internal.ParametroMapper;
import pe.bn.com.sate.ope.persistence.mapper.internal.TarjetaMapper;
import pe.bn.com.sate.ope.transversal.dto.sate.ModificacionTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.MovimientoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.SaldoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.ws.ConsultaMovimientos;
import pe.bn.com.sate.ope.transversal.dto.ws.ConsultaSaldos;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOInformacionTarjeta;
import pe.bn.com.sate.ope.transversal.util.ServicioWebUtil;
import pe.bn.com.sate.ope.transversal.util.SoapClientUtil;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.componentes.Parametros;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesGenerales;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesWS;

@Component
public class FWMCProcesos {

	private @Autowired
	ParametroMapper parametroMapper;

	private @Autowired
	TarjetaMapper tarjetaMapper;

	private @Autowired
	Parametros parametros;
	private final Logger logger = Logger.getLogger(FWMCProcesos.class);

	// ANTIGUO
	public List<MovimientoTarjeta> consultarMovimientosPorTarjeta(
			String numTarjeta) throws ServiceException {
		// TODO CAMBIO de if para que envie para pruebas y no verifique
		if (tarjetaMapper.buscarTarjetaPorNumeroTarjeta(numTarjeta,
				UsefulWebApplication.obtenerUsuario().getRuc()) != null) {

			// if (true) {
			// TODO DATOS PARA SERVICIO WS IZIPAY
			String codigoEmisor = parametros.getCodigoEmisorMc();
			String codigoUsuario = parametros.getCodigoUsuarioMc();
			String numTerminal = parametros.getNumTerminalMc();
			String NumReferencia = parametros.getPrefijoNumReferenciaMc()
					+ parametroMapper.obtenerNumeroReferenciaMovimientos();

			String wSUsuario = parametros.getWsUsuarioMc();
			String WSClave = parametros.getWsClaveMc();

			String request = "";
			String response = "";

			List<MovimientoTarjeta> movimientosTarjeta;

			request = "<![CDATA[" + "<Consulta_Movimientos>" + "<CodEmisor>"
					+ codigoEmisor + "</CodEmisor>" + "<CodUsuario>"
					+ codigoUsuario + "</CodUsuario>" + "<NumTerminal>"
					+ numTerminal + "</NumTerminal>" + "<NumReferencia>"
					+ NumReferencia + "</NumReferencia>"
					+ "<NumTarjetaMonedero>" + numTarjeta
					+ "</NumTarjetaMonedero>"
					+ "<FechaExpiracion>0722</FechaExpiracion>"
					+ "<Comercio>4058950</Comercio>" + "<Moneda>604</Moneda>"
					+ "<FechaTxnTerminal>20160621</FechaTxnTerminal>"
					+ "<HoraTxnTerminal>111901</HoraTxnTerminal>"
					+ "<WSUsuario>" + wSUsuario + "</WSUsuario>" + "<WSClave>"
					+ WSClave + "</WSClave>" + "<Reservado></Reservado>"
					+ "</Consulta_Movimientos>" + "]]>";

			logger.info(request);
			try {
				BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
				ServicioWebUtil.cambiarTiempoEspera(parametros
						.getConexionTiempo(), parametros.getRespuestaTiempo(),
						(BindingProvider) basicHttpBinding_IService1Proxy
								._getDescriptor().getProxy());

				response = basicHttpBinding_IService1Proxy
						.consultaMovimientos(request);

				logger.info(response.indexOf("<Consulta_Movimientos>"));
				logger.info(response.indexOf("</Consulta_Movimientos>"));
				logger.info(response.substring(
						response.indexOf("<Consulta_Movimientos>"),
						response.indexOf("</soap:Body>")));
				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Movimientos>"),
						response.indexOf("</soap:Body>")));
				/*
				 * JAXBContext jc = JAXBContext
				 * .newInstance(ConsultaMovimientos.class); Unmarshaller
				 * unmarshaller = jc.createUnmarshaller();
				 * 
				 * ConsultaMovimientos consultaMovimientos =
				 * (ConsultaMovimientos) unmarshaller .unmarshal(reader);
				 */

				ConsultaMovimientos consultaMovimientos = convertirXMLAObjeto(
						reader, ConsultaMovimientos.class);
				logger.info(consultaMovimientos.toString());

				if (consultaMovimientos.getCodRespuesta().equals("0000")) {
					movimientosTarjeta = consultaMovimientos
							.obtenerMovimientosTarjeta();
					return movimientosTarjeta;
				} else {
					throw new ExternalServiceMCProcesosException(
							consultaMovimientos.getDescRespuesta());
				}
			} catch (JAXBException je) {
				throw new ExternalServiceMCProcesosException(
						ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_MC,
						je);
			} catch (Exception ex) {
				throw new ExternalServiceMCProcesosException(
						ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_MC,
						ex);
			}
		} else {
			throw new InternalServiceException(
					"Número de tarjeta no encontrada");
		}

	}

	public ModificacionTarjeta modificarTarjeta(String numTarjetaMonedero,
			String codigoBloqueo, String motivoBloqueo) throws JAXBException {

		try {
			String monedaProducto = "1";

			numTarjetaMonedero = "52634178"; // mockeado
			// codigoBloqueo = "Z"; //mockeado
			// motivoBloqueo = "Bloqueo Z"; //mockeado

			String fechaTxnTerminal = "20160301";
			String horaTxnTerminal = "142021";

			String numTerminal = "12345678";// colocar el parametros comp
			String NumReferencia = "ORD000123456789";// colocar el parametros
														// comp
			String wSUsuario = "0944006748";// colocar el parametros comp
			String WSClave = "dRUch4hupAvuduBE";// colocar el parametros comp

			String request = "<![CDATA[" + "<Modificacion_Tarjeta>"
					+ "<CodEmisor>941</CodEmisor>" + // Obligatorio
					"<CodUsuario>CS00000001</CodUsuario>" + // Obligatorio
					"<NumTerminal>"
					+ numTerminal
					+ "</NumTerminal>"
					+ // Obligatorio
					"<NumReferencia>"
					+ NumReferencia
					+ "</NumReferencia>"
					+ // Obligatorio
					"<MonedaProducto>"
					+ monedaProducto
					+ "</MonedaProducto>"
					+ // Obligatorio
					"<CodigoProducto></CodigoProducto>"
					+ "<NumCuentaAsociada></NumCuentaAsociada>"
					+ "<IndAutGenCodCliente></IndAutGenCodCliente>"
					+ "<NumTarjetaMonedero>"
					+ numTarjetaMonedero
					+ "</NumTarjetaMonedero>"
					+ "<TipoTarjeta></TipoTarjeta>"
					+ "<SecuenciaTarjeta></SecuenciaTarjeta>"
					+ "<TipoEmisionTarjeta></TipoEmisionTarjeta>"
					+ "<NombrePlasticoLinea1></NombrePlasticoLinea1>"
					+ "<NombrePlasticoLinea2></NombrePlasticoLinea2>"
					+ "<CodigoBloqueo>"
					+ codigoBloqueo
					+ "</CodigoBloqueo>"
					+ "<MotivoBloqueo>"
					+ motivoBloqueo
					+ "</MotivoBloqueo>"
					+ "<DireccionEnvioTipoVia></DireccionEnvioTipoVia>"
					+ "<DireccionEnvioNombreVia></DireccionEnvioNombreVia>"
					+ "<DireccionEnvioNum></DireccionEnvioNum>"
					+ "<DireccionEnvioNumDpto></DireccionEnvioNumDpto>"
					+ "<DireccionEnvioOficina></DireccionEnvioOficina>"
					+ "<DireccionEnvioPiso></DireccionEnvioPiso>"
					+ "<DireccionEnvioManzana></DireccionEnvioManzana>"
					+ "<DireccionEnvioLote></DireccionEnvioLote>"
					+ "<DireccionEnvioNumInterior></DireccionEnvioNumInterior>"
					+ "<DireccionEnvioSector></DireccionEnvioSector>"
					+ "<DireccionEnvioKilometro></DireccionEnvioKilometro>"
					+ "<DireccionEnvioTipoZona></DireccionEnvioTipoZona>"
					+ "<DireccionEnvioNombreZona></DireccionEnvioNombreZona>"
					+ "<DireccionEnvioReferencia></DireccionEnvioReferencia>"
					+ "<DireccionEnvioUbigeo></DireccionEnvioUbigeo>"
					+ "<IndTipoDireccion></IndTipoDireccion>"
					+ "<SucursalOriginal></SucursalOriginal>"
					+ "<Mandatorio1></Mandatorio1>"
					+ "<Mandatorio2></Mandatorio2>"
					+ "<SucursalActual></SucursalActual>"
					+ "<CodigoPromocion></CodigoPromocion>"
					+ "<FechaTxnTerminal>"
					+ fechaTxnTerminal
					+ "</FechaTxnTerminal>"
					+ "<HoraTxnTerminal>"
					+ horaTxnTerminal
					+ "</HoraTxnTerminal>"
					+ "<WSUsuario>"
					+ wSUsuario
					+ "</WSUsuario>"
					+ "<WSClave>"
					+ WSClave
					+ "</WSClave>"
					+ "<Reservado></Reservado>"
					+ "</Modificacion_Tarjeta>]]>";
			logger.info("request: " + request);
			String response = "";

			BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
			response = basicHttpBinding_IService1Proxy
					.modificacionTarjetas(request);

			logger.info(response.indexOf("<Modificacion_Tarjeta>"));
			logger.info(response.indexOf("</Modificacion_Tarjeta>"));
			logger.info(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));

			StringReader reader = new StringReader(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));
			ModificacionTarjeta modificacionTarjeta = convertirXMLAObjeto(
					reader, ModificacionTarjeta.class);

			return modificacionTarjeta;
		} catch (Exception ex) {
			throw new ExternalServiceMCProcesosException(ex.getMessage(), ex);
		}
	}

	public SaldoTarjeta consultarSaldosPorTarjeta(String numTarjeta) {

		if (tarjetaMapper.buscarTarjetaPorNumeroTarjeta(numTarjeta,
				UsefulWebApplication.obtenerUsuario().getRuc()) != null) {

			String codigoEmisor = parametros.getCodigoEmisorMc();
			String codigoUsuario = parametros.getCodigoUsuarioMc();
			String numTerminal = parametros.getNumTerminalMc();
			String NumReferencia = parametros.getPrefijoNumReferenciaMc()
					+ parametroMapper.obtenerNumeroReferenciaSaldos();
			String wSUsuario = parametros.getWsUsuarioMc();
			String WSClave = parametros.getWsClaveMc();

			String request = "";
			String response = "";

			request = "<![CDATA[" + "<Consulta_Saldos>" + "<CodEmisor>"
					+ codigoEmisor + "</CodEmisor>" + "<CodUsuario>"
					+ codigoUsuario + "</CodUsuario>" + "<NumTerminal>"
					+ numTerminal + "</NumTerminal>" + "<NumReferencia>"
					+ NumReferencia + "</NumReferencia>"
					+ "<NumTarjetaMonedero>" + numTarjeta
					+ "</NumTarjetaMonedero>"
					+ "<FechaExpiracion>0921</FechaExpiracion>"
					+ "<Comercio>4058950</Comercio>" + "<Moneda>604</Moneda>"
					+ "<FechaTxnTerminal>20181106</FechaTxnTerminal>"
					+ "<HoraTxnTerminal>120000</HoraTxnTerminal>"
					+ "<WSUsuario>" + wSUsuario + "</WSUsuario>" + "<WSClave>"
					+ WSClave + "</WSClave>" + "<Reservado></Reservado>"
					+ "</Consulta_Saldos>" + "]]>";

			logger.info(request);
			try {
				BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
				response = basicHttpBinding_IService1Proxy
						.consultaSaldos(request);

				logger.info(response);
				logger.info(response.indexOf("<Consulta_Saldos>"));
				logger.info(response.indexOf("</Consulta_Saldos>"));
				logger.info(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));

				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));
				ConsultaSaldos consultaSaldos = convertirXMLAObjeto(reader,
						ConsultaSaldos.class);

				logger.info(consultaSaldos.toString());

				if (consultaSaldos.getCodRespuesta().equals("0000")) {
					return new SaldoTarjeta(consultaSaldos);
				} else {
					throw new ExternalServiceMCProcesosException(
							consultaSaldos.getDescRespuesta());
				}

			} catch (JAXBException je) {
				throw new ExternalServiceMCProcesosException(
						"Error en el negocio, consulte con el administrador");
			} catch (Exception ex) {
				throw new ExternalServiceMCProcesosException(ex.getMessage(),
						ex);
			}
		} else {
			throw new ExternalServiceMCProcesosException(
					"Número de tarjeta no encontrada");
		}
	}

	// NUEVO
	public DTOInformacionTarjeta informacionDeTarjeta(int idTarjeta)
			throws SocketTimeoutException, IOException {
		logger.info("Iniciando consulta de información de tarjeta.");
		DTOInformacionTarjeta response = null;
		String responseData = null;
		conexionTest();
		int maxRetries = 10;
		int attempt = 0;
		boolean success = false;
		// Preparación de la solicitud
		Map<String, String> inputRequest = ConstantesWS
				.getInformacionTarjetaMap();
		inputRequest.put(ConstantesWS.COD_EMISOR, "971");
		inputRequest.put(ConstantesWS.COD_USUARIO, "TW9999");
		inputRequest.put(ConstantesWS.NUM_TERMINAL, "11010101");
		inputRequest.put(ConstantesWS.NUM_REFERENCIA, "AC2020000322");
		inputRequest.put(ConstantesWS.NUM_TARJETA, "000000009");
		inputRequest.put(ConstantesWS.FECHA_EXPIRACION, "2701");
		inputRequest.put(ConstantesWS.COMERCIO, "9999999");
		inputRequest.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		inputRequest.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		inputRequest.put(ConstantesWS.WS_USUARIO, "prueba1234");
		inputRequest.put(ConstantesWS.WS_CLAVE, "prueba1234567890");
		inputRequest.put(ConstantesWS.RESERVADO, "");
		String request = ConstantesWS.generarXml(
				ConstantesWS.INFORMACION_TARJETA_XML, inputRequest);
		logger.info("Request generado: " + request);
		while (attempt < maxRetries && !success) {
			attempt++;
			try {
				try {

					// Configuración del cliente SOAP
					BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();

					ServicioWebUtil.cambiarTiempoEspera("10000", "3000",
							(BindingProvider) basicHttpBinding_IService1Proxy
									._getDescriptor().getProxy());

					logger.info("Realizando llamada al servicio SOAP..."
							.concat(" Intento: " + attempt));

					// Ejecución de la llamada al servicio
					responseData = basicHttpBinding_IService1Proxy
							.informacionTarjeta(request);
					logger.info("Response recibido: " + responseData);
					success = true;
					logger.info("Conexión exitosa en el intento " + attempt);
					// Procesamiento de la respuesta
					StringReader reader = new StringReader(responseData);
					response = convertirXMLAObjeto(reader,
							DTOInformacionTarjeta.class);
					logger.info("Objeto de respuesta: " + response);

				} catch (SOAPFaultException e) {
					logger.error("Error SOAP: Ocurrió un error en el servicio web SOAP "
							+ e.getMessage());
					;
				} catch (WebServiceException e) {
					logger.error("Error de Servicio Web: Ocurrió un error en la comunicación con el servicio web "
							+ e.getMessage());
				}
			} catch (Exception e) {
				logger.error("Fallo en el intento " + attempt + ": "
						+ e.getMessage());
				if (attempt == maxRetries) {
					logger.error("Maximos intentos alcanzados. Abortando.");
				}
			}
		}
		return response;
	}

	public void bloqueoDeTarjeta(int idTarjeta, String motivoBloqueo) {
		logger.info("Iniciando bloqueo de tarjeta.");

		try {
			// Implementación de la lógica para bloquear la tarjeta
			logger.info("Bloqueando tarjeta.");

			// Simulación de la lógica de negocio
			// Aquí iría el código que realiza el bloqueo de la tarjeta

			logger.info("Bloqueo de tarjeta finalizado exitosamente.");

		} catch (Exception ex) {
			logger.error("Error al bloquear la tarjeta", ex);
			// Manejo de excepciones específico si es necesario
		} finally {
			logger.info("Finalizando bloqueo de tarjeta");
		}
	}

	public void consultaDeMovimientoPorExpediente(int expedienteId) {
		logger.info("Iniciando consulta de movimientos por expediente.");

		try {
			// Implementación de la lógica para consultar movimientos por
			// expediente
			logger.info("Consultando movimientos para el expediente.");

			// Simulación de la lógica de negocio
			// Aquí iría el código que consulta y procesa los movimientos del
			// expediente

			logger.info("Consulta de movimientos por expediente finalizada exitosamente.");

		} catch (Exception ex) {
			logger.error("Error al consultar movimientos por expediente", ex);
			// Manejo de excepciones específico si es necesario
		} finally {
			logger.info("Finalizando consulta de movimientos por expediente");
		}
	}

	public void consultaDeDatosPorExpediente(int expedienteId) {
		logger.info("Iniciando consulta de datos por expediente.");

		try {
			// Implementación de la lógica para consultar datos por expediente
			logger.info("Consultando datos para el expediente.");

			// Simulación de la lógica de negocio
			// Aquí iría el código que consulta y procesa los datos del
			// expediente

			logger.info("Consulta de datos por expediente finalizada exitosamente.");

		} catch (Exception ex) {
			logger.error("Error al consultar datos por expediente", ex);
			// Manejo de excepciones específico si es necesario
		} finally {
			logger.info("Finalizando consulta de datos por expediente");
		}
	}

	public void actualizacionDeDatos(int idTarjeta, String nuevosDatos) {
		logger.info("Iniciando actualización de datos para la tarjeta.");

		try {
			// Implementación de la lógica para actualizar datos de la tarjeta
			logger.info("Actualizando datos para la tarjeta.");

			// Simulación de la lógica de negocio
			// Aquí iría el código que actualiza los datos de la tarjeta

			logger.info("Actualización de datos finalizada exitosamente.");

		} catch (Exception ex) {
			logger.error("Error al actualizar datos de la tarjeta", ex);
			// Manejo de excepciones específico si es necesario
		} finally {
			logger.info("Finalizando actualización de datos para la tarjeta");
		}
	}

	public <T> T convertirXMLAObjeto(StringReader reader, Class<T> class1)
			throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(class1);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			// Lanza una nueva excepción con un mensaje detallado, preservando
			// el stack trace original
			logger.error(e.getMessage());
			throw new JAXBException(
					"Error al convertir el XML a un objeto de tipo "
							+ class1.getName(), e);
		} catch (Exception e) {
			// Captura cualquier otra excepción inesperada
			throw new JAXBException(
					"Ocurrió un error inesperado al intentar convertir el XML a un objeto.",
					e);
		}
	}

	public void conexionTest() {
	/*	System.setProperty("javax.net.ssl.trustStore",
				"C:\\RAD9\\IBM\\WebSphere\\AppServer\\java\\jre\\lib\\security\\cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		System.setProperty("javax.net.debug", "ssl,handshake");*/

		String wsdlUrl = "https://testwsgestiontarjetas.izipay.pe/WCFGestionTarjetas/Service1.svc";
		String soapTemplate = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<Body>"
				+ "<Informacion_Tarjeta xmlns=\"http://tempuri.org/\">"
				+ "<XML>WER</XML>"
				+ "</Informacion_Tarjeta>"
				+ "</Body>"
				+ "</Envelope>";

		Map<String, String> inputRequest = ConstantesWS
				.getInformacionTarjetaMap();
		inputRequest.put(ConstantesWS.COD_EMISOR, "971");
		inputRequest.put(ConstantesWS.COD_USUARIO, "TW9999");
		inputRequest.put(ConstantesWS.NUM_TERMINAL, "11010101");
		inputRequest.put(ConstantesWS.NUM_REFERENCIA, "AC2020000322");
		inputRequest.put(ConstantesWS.NUM_TARJETA, "000000009");
		inputRequest.put(ConstantesWS.FECHA_EXPIRACION, "2701");
		inputRequest.put(ConstantesWS.COMERCIO, "9999999");
		inputRequest.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		inputRequest.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		inputRequest.put(ConstantesWS.WS_USUARIO, "prueba1234");
		inputRequest.put(ConstantesWS.WS_CLAVE, "prueba1234567890");
		inputRequest.put(ConstantesWS.RESERVADO, "");
		String soapRequestPrevie = ConstantesWS.generarXml(
				ConstantesWS.INFORMACION_TARJETA_XML, inputRequest);
		String soapRequest = soapTemplate.replace("WER", soapRequestPrevie);

		logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;
		DTOInformacionTarjeta responseDTO = null;

		while (attempt < maxRetries && !success) {
			attempt++;
			try {

				try {
					// Ignorar SSL si es necesario (no recomendado para
					// producción)
					ignoreSSL();

					URL url = new URL(wsdlUrl);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type",
							"text/xml;charset=UTF-8");
					connection.setRequestProperty("SOAPAction",
							"http://tempuri.org/IService1/Informacion_Tarjeta");
					connection.setRequestProperty("Accept-Encoding",
							"gzip,deflate");
					connection.setRequestProperty("User-Agent",
							"Apache-HttpClient/4.5.5 (Java/16.0.2)");
					connection.setRequestProperty("Connection", "Keep-Alive");
					connection.setDoOutput(true);
					connection.setConnectTimeout(15000); // 15 segundos
					connection.setReadTimeout(15000); // 15 segundos

					logger.info("Enviando solicitud SOAP:");

					// Enviar la solicitud SOAP
					OutputStream os = connection.getOutputStream();
					os.write(soapRequest.getBytes("UTF-8"));
					os.flush();
					os.close();

					// Capturar y registrar la respuesta
					int statusCode = connection.getResponseCode();
					logger.info("Código de estado de la respuesta: "
							+ statusCode);

					if (statusCode == 200) {
						logger.info("Conexión exitosa al servicio SOAP.");
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										connection.getInputStream(), "UTF-8"));
						String responseLine;
						StringBuilder response = new StringBuilder();
						while ((responseLine = br.readLine()) != null) {
							response.append(responseLine);
						}
						br.close();

						logger.info("Respuesta del servidor:");
						logger.info(response.toString());

						Document  documentoXML = SoapClientUtil.parseXmlResponse(response
								.toString());
						String resultado = SoapClientUtil.getTextFromElement(documentoXML, "Informacion_TarjetaResult");
						Document  documentoXMLDTO = SoapClientUtil.parseXmlResponse(new String(resultado.getBytes("UTF-8")));
						logger.info("Respuesta del servidor DATA:");
						logger.info(documentoXMLDTO.toString());
						String contenidoXML = resultado.substring(
						        resultado.indexOf("<Informacion_Tarjeta>"),
						        resultado.indexOf("</Informacion_Tarjeta>") + "</Informacion_Tarjeta>".length());

						StringReader reader = new StringReader(contenidoXML);
						
						   try (BufferedReader bufferedReader = new BufferedReader(reader)) {
					            String line;
					            while ((line = bufferedReader.readLine()) != null) {
					                System.out.println(line);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
						responseDTO = convertirXMLAObjeto(new StringReader(contenidoXML),
								DTOInformacionTarjeta.class);
						logger.info("Objeto de respuesta: " + responseDTO);
						

					} else {
						logger.info("No se pudo conectar al servicio SOAP. Código de estado: "
								+ statusCode);
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										connection.getErrorStream()));
						String responseLine;
						StringBuilder response = new StringBuilder();
						while ((responseLine = br.readLine()) != null) {
							response.append(responseLine);
						}
						br.close();

						logger.info("Respuesta de error del servidor:");
						logger.info(response.toString());
					}

					connection.disconnect();
					success = true;
					logger.info("Conexión exitosa en el intento " + attempt);
				} catch (Exception e) {
					logger.info("Error testeando servicio Izipay: "
							+ e.getMessage());
					// e.printStackTrace();
				}

			} catch (Exception e) {
				logger.error("Fallo en el intento " + attempt + ": "
						+ e.getMessage());
				if (attempt == maxRetries) {
					logger.error("Maximos intentos alcanzados. Abortando.");
				}
			}
		}
	}





	
	

	
	
	
	
	
	
	
	
	
	
	private void ignoreSSL() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() { // TODO
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException { // TODO Auto-generated method
													// stub

			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException { // TODO Auto-generated method
													// stub

			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Deshabilitar verificación de nombre de host
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) { // TODO
				return true;
			}

		});
	}

}