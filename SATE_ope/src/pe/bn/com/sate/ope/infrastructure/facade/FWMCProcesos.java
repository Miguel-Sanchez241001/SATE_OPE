package pe.bn.com.sate.ope.infrastructure.facade;

import java.io.IOException;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import com.ibm.wsspi.webservices.Constants;  // Importa las constantes relevantes

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	private final Logger logger = Logger
			.getLogger(FWMCProcesos.class);

	public List<MovimientoTarjeta> consultarMovimientosPorTarjeta(
			String numTarjeta) throws ServiceException {
			// 	TODO CAMBIO de if para que envie para pruebas y no verifique 
		 if (tarjetaMapper.buscarTarjetaPorNumeroTarjeta(numTarjeta,	UsefulWebApplication.obtenerUsuario().getRuc()) != null) {

		//if (true) {
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

			request =
			"<![CDATA["+
			"<Consulta_Movimientos>" 
					+ "<CodEmisor>" + codigoEmisor+ "</CodEmisor>"
					+ "<CodUsuario>" + codigoUsuario + "</CodUsuario>" 
					+ "<NumTerminal>" + numTerminal + "</NumTerminal>" 
					+ "<NumReferencia>" + NumReferencia + "</NumReferencia>" 
					+ "<NumTarjetaMonedero>" + numTarjeta + "</NumTarjetaMonedero>"
					+ "<FechaExpiracion>0722</FechaExpiracion>"
					+ "<Comercio>4058950</Comercio>" + "<Moneda>604</Moneda>"
					+ "<FechaTxnTerminal>20160621</FechaTxnTerminal>"
					+ "<HoraTxnTerminal>111901</HoraTxnTerminal>"
					+ "<WSUsuario>" + wSUsuario + "</WSUsuario>" 
					+ "<WSClave>" + WSClave + "</WSClave>" 
					+ "<Reservado></Reservado>"
					+ "</Consulta_Movimientos>"
			  +"]]>";

			System.out.println(request);
			try {
				BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
				ServicioWebUtil.cambiarTiempoEspera(parametros
						.getConexionTiempo(), parametros.getRespuestaTiempo(),
						(BindingProvider) basicHttpBinding_IService1Proxy
								._getDescriptor().getProxy());

				response = basicHttpBinding_IService1Proxy
						.consultaMovimientos(request);

				System.out.println(response.indexOf("<Consulta_Movimientos>"));
				System.out.println(response.indexOf("</Consulta_Movimientos>"));
				System.out.println(response.substring(
						response.indexOf("<Consulta_Movimientos>"),
						response.indexOf("</soap:Body>")));
				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Movimientos>"),
						response.indexOf("</soap:Body>")));
				/*	JAXBContext jc = JAXBContext
						.newInstance(ConsultaMovimientos.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				
				ConsultaMovimientos consultaMovimientos = (ConsultaMovimientos) unmarshaller
						.unmarshal(reader);*/
				
				ConsultaMovimientos consultaMovimientos = convertirXMLAObjeto(reader,ConsultaMovimientos.class);
				System.out.println(consultaMovimientos.toString());

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
			System.out.println("request: " + request);
			String response = "";

			BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
			response = basicHttpBinding_IService1Proxy
					.modificacionTarjetas(request);

			System.out.println(response.indexOf("<Modificacion_Tarjeta>"));
			System.out.println(response.indexOf("</Modificacion_Tarjeta>"));
			System.out.println(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));


			StringReader reader = new StringReader(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));
			ModificacionTarjeta modificacionTarjeta = convertirXMLAObjeto(reader,ModificacionTarjeta.class);

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
					+ "<WSUsuario>" + wSUsuario + "</WSUsuario>" 
					+ "<WSClave>" + WSClave + "</WSClave>" 
					+ "<Reservado></Reservado>"
					+ "</Consulta_Saldos>" + "]]>";

			System.out.println(request);
			try {
				BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();
				response = basicHttpBinding_IService1Proxy
						.consultaSaldos(request);

				System.out.println(response);
				System.out.println(response.indexOf("<Consulta_Saldos>"));
				System.out.println(response.indexOf("</Consulta_Saldos>"));
				System.out.println(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));

				 

				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));
				ConsultaSaldos consultaSaldos = convertirXMLAObjeto(reader,ConsultaSaldos.class);

				System.out.println(consultaSaldos.toString());

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

	
	public DTOInformacionTarjeta informacionDeTarjeta(int idTarjeta) throws SocketTimeoutException, IOException {
	    logger.info("Iniciando consulta de información de tarjeta.");
	    DTOInformacionTarjeta response = null;
	    String responseData = null;

	    try {
	        // Preparación de la solicitud
	        Map<String, String> inputRequest =  ConstantesWS.getInformacionTarjetaMap();
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
	        String request =  ConstantesWS.generarXml(ConstantesWS.INFORMACION_TARJETA_XML, inputRequest);
	        logger.info("Request generado: " + request);

	        // Configuración del cliente SOAP
	        BasicHttpsBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpsBinding_IService1Proxy();

	        // Configurar tiempo de conexión y tiempo de lectura
	        BindingProvider bindingProvider = (BindingProvider) basicHttpBinding_IService1Proxy._getDescriptor().getProxy();
	        Map<String, Object> requestContext = bindingProvider.getRequestContext();
 
	     // Configuración del timeout
	   //  requestContext.put("com.ibm.websphere.webservices.connectionTimeout", 10000); // 10 segundos
	     //requestContext.put("com.ibm.websphere.webservices.responseTimeout", 20000);   // 20 segundos

	     // Desactivación de políticas de seguridad predeterminadas en IBM WebSphere
	     requestContext.put("com.ibm.ws.webservices.security.disableDefaultPolicyBinding", Boolean.TRUE);
	        logger.info("Realizando llamada al servicio SOAP...");

	        // Ejecución de la llamada al servicio
	        responseData = basicHttpBinding_IService1Proxy.informacionTarjeta(request);
	        logger.info("Response recibido: " + responseData);

	        // Procesamiento de la respuesta
	        StringReader reader = new StringReader(responseData);
	        response = convertirXMLAObjeto(reader, DTOInformacionTarjeta.class);
	        logger.info("Objeto de respuesta: " + response);

	    } catch (SOAPFaultException e) {
	        logger.error("Error SOAP: Ocurrió un error en el servicio web SOAP", e);
	    } catch (WebServiceException e) {
	        logger.error("Error de Servicio Web: Ocurrió un error en la comunicación con el servicio web", e);
	    } catch (Exception ex) {
	        logger.error("Error general al consultar información de tarjeta", ex);
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
            // Implementación de la lógica para consultar movimientos por expediente
            logger.info("Consultando movimientos para el expediente.");

            // Simulación de la lógica de negocio
            // Aquí iría el código que consulta y procesa los movimientos del expediente

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
            // Aquí iría el código que consulta y procesa los datos del expediente

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

	
	
	
    public <T> T convertirXMLAObjeto(StringReader reader, Class<T> class1) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(class1);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(reader);
    }
	
	
	
	
	
	
	
	
}