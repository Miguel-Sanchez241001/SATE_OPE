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
import pe.bn.com.sate.ope.transversal.dto.ws.DTOModificacionClientes;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOModificacionTarjeta;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOConsultaDatosExpediente;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOConsultaMovimientosExpediente;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOConsultaDatosTarjeta;
import pe.bn.com.sate.ope.transversal.dto.ws.DTOwservice;
import pe.bn.com.sate.ope.transversal.util.ServicioWebUtil;
import pe.bn.com.sate.ope.transversal.util.SoapClientUtil;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.componentes.Parametros;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesGenerales;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesWS;
import pe.bn.com.sate.ope.transversal.util.excepciones.InternalExcepcion;

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

				ConsultaMovimientos consultaMovimientos = SoapClientUtil
						.convertirXMLAObjeto(reader, ConsultaMovimientos.class);
				logger.info(consultaMovimientos.toString());

				if (consultaMovimientos.getCodRespuesta().equals("0000")) {
					movimientosTarjeta = consultaMovimientos
							.obtenerMovimientosTarjeta();
					return movimientosTarjeta;
				} else {
					throw new ExternalServiceMCProcesosException(
							consultaMovimientos.getDescRespuesta());
				}
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
			ModificacionTarjeta modificacionTarjeta = SoapClientUtil
					.convertirXMLAObjeto(reader, ModificacionTarjeta.class);

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
				ConsultaSaldos consultaSaldos = SoapClientUtil
						.convertirXMLAObjeto(reader, ConsultaSaldos.class);

				logger.info(consultaSaldos.toString());

				if (consultaSaldos.getCodRespuesta().equals("0000")) {
					return new SaldoTarjeta(consultaSaldos);
				} else {
					throw new ExternalServiceMCProcesosException(
							consultaSaldos.getDescRespuesta());
				}

			} catch (Exception ex) {
				throw new ExternalServiceMCProcesosException(ex.getMessage(),
						ex);
			}
		} else {
			throw new ExternalServiceMCProcesosException(
					"Número de tarjeta no encontrada");
		}
	}

	// WS IZIPAY METODOS 2024
	public DTOConsultaDatosTarjeta informacionDeTarjeta() throws InternalExcepcion,ExternalServiceMCProcesosException {

		String wsdlUrl = parametros.getWsSoapMc();
	 

		Map<String, String> inputRequest = ConstantesWS
				.getConsultaDatosTarjetaMap();
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
				ConstantesWS.Consulta_Datos_Tarjeta_XML, inputRequest);
		DTOwservice dto = new DTOwservice(ConstantesWS.SOACTION_Consulta_Datos_Tarjeta);

		String soapRequest = dto.getSoapTemplate().replace("SOAP_CONTENT", soapRequestPrevie);

		//logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;
		DTOConsultaDatosTarjeta responseDTO = null;

		while (attempt < maxRetries && !success) {
			attempt++;
			String dataWS = "";
			try {
				dataWS = SoapClientUtil.sendSoapRequest(wsdlUrl,
						dto.getSoapAction(),
						soapRequest);
			} catch (ExternalServiceMCProcesosException e) {
				throw e;
			}

			try {

				logger.info("Respuesta del servidor:");
				logger.info(dataWS);

				Document documentoXML = SoapClientUtil.parseXmlResponse(dataWS
						.toString());
				String resultado = SoapClientUtil.getTextFromElement(
						documentoXML, dto.getResultTag());

				String contenidoXML = resultado.substring(
						resultado.indexOf(dto.getStartTag()),
						resultado.indexOf(dto.getEndTag())
								+ dto.getEndTag().length());

				responseDTO = SoapClientUtil.convertirXMLAObjeto(
						new StringReader(contenidoXML),
						DTOConsultaDatosTarjeta.class);

				logger.info("Objeto de respuesta: " + responseDTO);
				success = true;
				logger.info("Conexión exitosa en el intento " + attempt);
			} catch (InternalExcepcion e) {
				throw e;
			}

		}
		return responseDTO;

	}

	public DTOModificacionTarjeta bloqueoDeTarjeta(int idTarjeta, String motivoBloqueo) throws InternalExcepcion {
		String wsdlUrl = parametros.getWsSoapMc();
		DTOwservice dto = new DTOwservice(ConstantesWS.SOACTION_BLOQUEO_TARJETA);
		Class<DTOModificacionTarjeta> dtoClass = DTOModificacionTarjeta.class;
		DTOModificacionTarjeta responseDTO = null;


		Map<String, String> inputRequest = ConstantesWS
				.getModificacionTarjetaMap();
		
		
		inputRequest.put(ConstantesWS.COD_EMISOR, "971");
		inputRequest.put(ConstantesWS.COD_USUARIO, "TW9999");
		inputRequest.put(ConstantesWS.NUM_TERMINAL, "11010101");
		inputRequest.put(ConstantesWS.NUM_REFERENCIA, "AC2020000322");
		inputRequest.put(ConstantesWS.ORGANIZACION, "941");
		inputRequest.put(ConstantesWS.NUM_TARJETA, "000000009");
		inputRequest.put(ConstantesWS.FECHA_EXPIRACION, "2701");
		inputRequest.put(ConstantesWS.CODIGO_BLOQUEO, "A3");
		inputRequest.put(ConstantesWS.MOTIVO_BLOQUEO, "Robo");
		inputRequest.put(ConstantesWS.COMERCIO, "9999999");
		inputRequest.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		inputRequest.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		inputRequest.put(ConstantesWS.WS_USUARIO, "prueba1234");
		inputRequest.put(ConstantesWS.WS_CLAVE, "prueba1234567890");
		inputRequest.put(ConstantesWS.RESERVADO, "");
		
		String soapRequestPrevie = ConstantesWS.generarXml(
				ConstantesWS.MODIFICACION_TARJETA_XML, inputRequest);
		

		String soapRequest = dto.getSoapTemplate().replace("SOAP_CONTENT", soapRequestPrevie);

		//logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;

		while (attempt < maxRetries && !success) {
			attempt++;
			String dataWS = "";
			try {
				dataWS = SoapClientUtil.sendSoapRequest(wsdlUrl,
						dto.getSoapAction(),
						soapRequest);
			} catch (ExternalServiceMCProcesosException e) {
				throw e;
			}

			try {

				logger.info("Respuesta del servidor:");
				logger.info(dataWS);

				Document documentoXML = SoapClientUtil.parseXmlResponse(dataWS
						.toString());
				String resultado = SoapClientUtil.getTextFromElement(
						documentoXML, dto.getResultTag());

				String contenidoXML = resultado.substring(
						resultado.indexOf(dto.getStartTag()),
						resultado.indexOf(dto.getEndTag())
								+ dto.getEndTag().length());

				responseDTO = SoapClientUtil.convertirXMLAObjeto(
						new StringReader(contenidoXML),
						dtoClass);

				logger.info("Objeto de respuesta: " + responseDTO);
				success = true;
				logger.info("Conexión exitosa en el intento " + attempt);
			} catch (InternalExcepcion e) {
				throw e;
			}

		}
		return responseDTO;
	}

	public DTOConsultaMovimientosExpediente consultaDeMovimientoPorExpediente(int expedienteId) throws InternalExcepcion {
		String wsdlUrl = parametros.getWsSoapMc();
		DTOwservice dto = new DTOwservice(ConstantesWS.SOACTION_CONSULTA_MOVIMIENTOS_EXPEDIENTE);
		Class<DTOConsultaMovimientosExpediente> dtoClass = DTOConsultaMovimientosExpediente.class;
		DTOConsultaMovimientosExpediente responseDTO = null;


		Map<String, String> consultaMovimientosExpedienteMap = ConstantesWS
				.getConsultaMovimientosExpedienteMap();
		
		
		consultaMovimientosExpedienteMap.put(ConstantesWS.COD_EMISOR, "941");
		consultaMovimientosExpedienteMap.put(ConstantesWS.COD_USUARIO, "CS00000001");
		consultaMovimientosExpedienteMap.put(ConstantesWS.NUM_TERMINAL, "12345678");
		consultaMovimientosExpedienteMap.put(ConstantesWS.NUM_REFERENCIA, "ORD20160224");
		consultaMovimientosExpedienteMap.put(ConstantesWS.ORGANIZACION, "941");
		consultaMovimientosExpedienteMap.put(ConstantesWS.NUM_TARJETA, "526983659");
		consultaMovimientosExpedienteMap.put(ConstantesWS.FECHA_EXPIRACION, "");
		consultaMovimientosExpedienteMap.put(ConstantesWS.COMERCIO, "2999994");
		consultaMovimientosExpedienteMap.put(ConstantesWS.MONEDA, "604");
		consultaMovimientosExpedienteMap.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		consultaMovimientosExpedienteMap.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		consultaMovimientosExpedienteMap.put(ConstantesWS.WS_USUARIO, "0944006748");
		consultaMovimientosExpedienteMap.put(ConstantesWS.WS_CLAVE, "dRUch4hupAvuduBE");
		consultaMovimientosExpedienteMap.put(ConstantesWS.RESERVADO, "");
		
		String soapRequestPrevie = ConstantesWS.generarXml(
				ConstantesWS.CONSULTA_MOVIMIENTOS_EXPEDIENTE_XML, consultaMovimientosExpedienteMap);
		

		String soapRequest = dto.getSoapTemplate().replace("SOAP_CONTENT", soapRequestPrevie);

		//logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;

		while (attempt < maxRetries && !success) {
			attempt++;
			String dataWS = "";
			try {
				dataWS = SoapClientUtil.sendSoapRequest(wsdlUrl,
						dto.getSoapAction(),
						soapRequest);
			} catch (ExternalServiceMCProcesosException e) {
				throw e;
			}

			try {

				logger.info("Respuesta del servidor:");
				logger.info(dataWS);

				Document documentoXML = SoapClientUtil.parseXmlResponse(dataWS
						.toString());
				String resultado = SoapClientUtil.getTextFromElement(
						documentoXML, dto.getResultTag());

				String contenidoXML = resultado.substring(
						resultado.indexOf(dto.getStartTag()),
						resultado.indexOf(dto.getEndTag())
								+ dto.getEndTag().length());

				responseDTO = SoapClientUtil.convertirXMLAObjeto(
						new StringReader(contenidoXML),
						dtoClass);

				logger.info("Objeto de respuesta: " + responseDTO);
				success = true;
				logger.info("Conexión exitosa en el intento " + attempt);
			} catch (InternalExcepcion e) {
				throw e;
			}

		}
		return responseDTO;
	}

	public DTOConsultaDatosExpediente consultaDeDatosPorExpediente(int expedienteId) throws InternalExcepcion {
		String wsdlUrl = parametros.getWsSoapMc();
		DTOwservice dto = new DTOwservice(ConstantesWS.SOACTION_CONSULTA_DATOS_EXPEDIENTE);
		Class<DTOConsultaDatosExpediente> dtoClass = DTOConsultaDatosExpediente.class;
		DTOConsultaDatosExpediente responseDTO = null;


		Map<String, String> inputRequest = ConstantesWS
				.getConsultaDatosExpedienteMap();
		
		
		inputRequest.put(ConstantesWS.COD_EMISOR, "971");
		inputRequest.put(ConstantesWS.COD_USUARIO, "TW9999");
		inputRequest.put(ConstantesWS.NUM_TERMINAL, "11010101");
		inputRequest.put(ConstantesWS.NUM_REFERENCIA, "AC2020000322");
		inputRequest.put(ConstantesWS.ORGANIZACION, "941");
		inputRequest.put(ConstantesWS.NRO_DOCUMENTO, "74851254");
		inputRequest.put(ConstantesWS.CORREO_ELECTRONICO, "prueba@hotmail.com");
		inputRequest.put(ConstantesWS.NRO_CELULAR, "965845214");
		inputRequest.put(ConstantesWS.FECHA_EXPIRACION, "2701");
		inputRequest.put(ConstantesWS.COMERCIO, "9999999");
		inputRequest.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		inputRequest.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		inputRequest.put(ConstantesWS.WS_USUARIO, "prueba1234");
		inputRequest.put(ConstantesWS.WS_CLAVE, "prueba1234567890");
		inputRequest.put(ConstantesWS.RESERVADO, "");
		
		String soapRequestPrevie = ConstantesWS.generarXml(
				ConstantesWS.CONSULTA_DATOS_EXPEDIENTE_XML, inputRequest);
		

		String soapRequest = dto.getSoapTemplate().replace("SOAP_CONTENT", soapRequestPrevie);

		//logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;

		while (attempt < maxRetries && !success) {
			attempt++;
			String dataWS = "";
			try {
				dataWS = SoapClientUtil.sendSoapRequest(wsdlUrl,
						dto.getSoapAction(),
						soapRequest);
			} catch (ExternalServiceMCProcesosException e) {
				throw e;
			}

			try {

				logger.info("Respuesta del servidor:");
				logger.info(dataWS);

				Document documentoXML = SoapClientUtil.parseXmlResponse(dataWS
						.toString());
				String resultado = SoapClientUtil.getTextFromElement(
						documentoXML, dto.getResultTag());

				String contenidoXML = resultado.substring(
						resultado.indexOf(dto.getStartTag()),
						resultado.indexOf(dto.getEndTag())
								+ dto.getEndTag().length());

				responseDTO = SoapClientUtil.convertirXMLAObjeto(
						new StringReader(contenidoXML),
						dtoClass);

				logger.info("Objeto de respuesta: " + responseDTO);
				success = true;
				logger.info("Conexión exitosa en el intento " + attempt);
			} catch (InternalExcepcion e) {
				throw e;
			}

		}
		return responseDTO;
	}

	public DTOModificacionClientes actualizacionDeDatos(int idTarjeta, String nuevosDatos) throws InternalExcepcion {
		String wsdlUrl = parametros.getWsSoapMc();
		DTOwservice dto = new DTOwservice(ConstantesWS.SOACTION_MODIFICACION_CLIENTE);
		Class<DTOModificacionClientes> dtoClass = DTOModificacionClientes.class;


		Map<String, String> inputRequest = ConstantesWS
				.getModificacionClienteMap();
		
		
		inputRequest.put(ConstantesWS.COD_EMISOR, "971");
		inputRequest.put(ConstantesWS.COD_USUARIO, "TW9999");
		inputRequest.put(ConstantesWS.NUM_TERMINAL, "11010101");
		inputRequest.put(ConstantesWS.NUM_REFERENCIA, "AC2020000322");
		inputRequest.put(ConstantesWS.ORGANIZACION, "941");
		inputRequest.put(ConstantesWS.NRO_DOCUMENTO, "74851254");
		inputRequest.put(ConstantesWS.CORREO_ELECTRONICO, "prueba@hotmail.com");
		inputRequest.put(ConstantesWS.NRO_CELULAR, "965845214");
		inputRequest.put(ConstantesWS.FECHA_EXPIRACION, "2701");
		inputRequest.put(ConstantesWS.COMERCIO, "9999999");
		inputRequest.put(ConstantesWS.FECHA_TXN_TERMINAL, "20160224");
		inputRequest.put(ConstantesWS.HORA_TXN_TERMINAL, "172020");
		inputRequest.put(ConstantesWS.WS_USUARIO, "prueba1234");
		inputRequest.put(ConstantesWS.WS_CLAVE, "prueba1234567890");
		inputRequest.put(ConstantesWS.RESERVADO, "");
		
		String soapRequestPrevie = ConstantesWS.generarXml(
				ConstantesWS.MODIFICACION_CLIENTE_XML, inputRequest);
		

		String soapRequest = dto.getSoapTemplate().replace("SOAP_CONTENT", soapRequestPrevie);

		//logger.info("Request generado: " + soapRequest);

		int maxRetries = 5;
		int attempt = 0;
		boolean success = false;
		DTOModificacionClientes responseDTO = null;

		while (attempt < maxRetries && !success) {
			attempt++;
			String dataWS = "";
			try {
				dataWS = SoapClientUtil.sendSoapRequest(wsdlUrl,
						dto.getSoapAction(),
						soapRequest);
			} catch (ExternalServiceMCProcesosException e) {
				throw e;
			}

			try {

				logger.info("Respuesta del servidor:");
				logger.info(dataWS);

				Document documentoXML = SoapClientUtil.parseXmlResponse(dataWS
						.toString());
				String resultado = SoapClientUtil.getTextFromElement(
						documentoXML, dto.getResultTag());

				String contenidoXML = resultado.substring(
						resultado.indexOf(dto.getStartTag()),
						resultado.indexOf(dto.getEndTag())
								+ dto.getEndTag().length());

				responseDTO = SoapClientUtil.convertirXMLAObjeto(
						new StringReader(contenidoXML),
						dtoClass);

				logger.info("Objeto de respuesta: " + responseDTO);
				success = true;
				logger.info("Conexión exitosa en el intento " + attempt);
			} catch (InternalExcepcion e) {
				throw e;
			}

		}
		return responseDTO;
	}

	public void conexionTest() throws InternalExcepcion, ExternalServiceMCProcesosException {
	    int maxRetries = 5;
	    boolean success = false;
	    StringBuilder resultadoFallos = new StringBuilder();

	    // Probar cada método secuencialmente 5 veces
	    for (int i = 0; i < 5; i++) {
	        int fallidos = 0;

	        // Probar tryInformacionDeTarjeta
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {
	            try {
	                success = tryInformacionDeTarjeta();
	                if (success) break;
	            } catch (ExternalServiceMCProcesosException e) {
	                fallidos++;
	                resultadoFallos.append("tryInformacionDeTarjeta falló en el intento ")
	                        .append(attempt).append(": ").append(e.getMessage()).append("\n");
	                logger.error("Error en intento " + attempt + " de tryInformacionDeTarjeta: " + e.getMessage(), e);
	            }
	        }
	        resultadoFallos.append("tryInformacionDeTarjeta intentado ").append(fallidos).append(" veces.\n");

	        //if (success) break; // Salir si se ha tenido éxito en el método

	        // Probar tryBloqueoDeTarjeta
	        fallidos = 0;
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {
	            try {
	                success = tryBloqueoDeTarjeta();
	                if (success) break;
	            } catch (ExternalServiceMCProcesosException e) {
	                fallidos++;
	                resultadoFallos.append("tryBloqueoDeTarjeta falló en el intento ")
	                        .append(attempt).append(": ").append(e.getMessage()).append("\n");
	                logger.error("Error en intento " + attempt + " de tryBloqueoDeTarjeta: " + e.getMessage(), e);
	            }
	        }
	        resultadoFallos.append("tryBloqueoDeTarjeta intentado ").append(fallidos).append(" veces.\n");

	        //if (success) break;

	        // Probar tryConsultaDeMovimientoPorExpediente
	        fallidos = 0;
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {
	            try {
	                success = tryConsultaDeMovimientoPorExpediente();
	                if (success) break;
	            } catch (ExternalServiceMCProcesosException e) {
	                fallidos++;
	                resultadoFallos.append("tryConsultaDeMovimientoPorExpediente falló en el intento ")
	                        .append(attempt).append(": ").append(e.getMessage()).append("\n");
	                logger.error("Error en intento " + attempt + " de tryConsultaDeMovimientoPorExpediente: " + e.getMessage(), e);
	            }
	        }
	        resultadoFallos.append("tryConsultaDeMovimientoPorExpediente intentado ").append(fallidos).append(" veces.\n");

	        //if (success) break;

	        // Probar tryConsultaDeDatosPorExpediente
	        fallidos = 0;
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {
	            try {
	                success = tryConsultaDeDatosPorExpediente();
	               if (success) break;
	            } catch (ExternalServiceMCProcesosException e) {
	                fallidos++;
	                resultadoFallos.append("tryConsultaDeDatosPorExpediente falló en el intento ")
	                        .append(attempt).append(": ").append(e.getMessage()).append("\n");
	                logger.error("Error en intento " + attempt + " de tryConsultaDeDatosPorExpediente: " + e.getMessage(), e);
	            }
	        }
	        resultadoFallos.append("tryConsultaDeDatosPorExpediente intentado ").append(fallidos).append(" veces.\n");

	        //if (success) break;

	        // Probar tryActualizacionDeDatos
	        fallidos = 0;
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {
	            try {
	                success = tryActualizacionDeDatos();
	                if (success) break;
	            } catch (ExternalServiceMCProcesosException e) {
	                fallidos++;
	                resultadoFallos.append("tryActualizacionDeDatos falló en el intento ")
	                        .append(attempt).append(": ").append(e.getMessage()).append("\n");
	                logger.error("Error en intento " + attempt + " de tryActualizacionDeDatos: " + e.getMessage(), e);
	            }
	        }
	        resultadoFallos.append("tryActualizacionDeDatos intentado ").append(fallidos).append(" veces.\n");

	        //if (success) break;
	    }

	    if (!success) {
	        logger.error("Todos los métodos fallaron después de múltiples intentos.\n" + resultadoFallos.toString());
	        throw new ExternalServiceMCProcesosException("Todos los intentos fallaron para obtener el DTO:\n" + resultadoFallos.toString());
	    } else {
	        logger.info("Método ejecutado con éxito después de múltiples intentos.");
	    }
	}



	private boolean tryInformacionDeTarjeta() {
	    try {
	        DTOConsultaDatosTarjeta responseDTO = informacionDeTarjeta();
	        logger.info("informacionDeTarjeta ejecutado con éxito: " + responseDTO);
	        return true;
	    } catch (Exception e) {
	        logger.warn("Falló el método informacionDeTarjeta: " + e.getMessage());
	        return false;
	    }
	}

	private boolean tryBloqueoDeTarjeta() {
	    try {
	        DTOModificacionTarjeta responseDTO = bloqueoDeTarjeta(1, "Robo");
	        logger.info("bloqueoDeTarjeta ejecutado con éxito: " + responseDTO);
	        return true;
	    } catch (Exception e) {
	        logger.warn("Falló el método bloqueoDeTarjeta: " + e.getMessage());
	        return false;
	    }
	}

	private boolean tryConsultaDeMovimientoPorExpediente() {
	    try {
	        DTOConsultaMovimientosExpediente responseDTO = consultaDeMovimientoPorExpediente(1);
	        logger.info("consultaDeMovimientoPorExpediente ejecutado con éxito: " + responseDTO);
	        return true;
	    } catch (Exception e) {
	        logger.warn("Falló el método consultaDeMovimientoPorExpediente: " + e.getMessage());
	        return false;
	    }
	}

	private boolean tryConsultaDeDatosPorExpediente() {
	    try {
	        DTOConsultaDatosExpediente responseDTO = consultaDeDatosPorExpediente(1);
	        logger.info("consultaDeDatosPorExpediente ejecutado con éxito: " + responseDTO);
	        return true;
	    } catch (Exception e) {
	        logger.warn("Falló el método consultaDeDatosPorExpediente: " + e.getMessage());
	        return false;
	    }
	}

	private boolean tryActualizacionDeDatos() {
	    try {
	        DTOModificacionClientes responseDTO = actualizacionDeDatos(1, "nuevosDatos");
	        logger.info("actualizacionDeDatos ejecutado con éxito: " + responseDTO);
	        return true;
	    } catch (Exception e) {
	        logger.warn("Falló el método actualizacionDeDatos: " + e.getMessage());
	        return false;
	    }
	}


}