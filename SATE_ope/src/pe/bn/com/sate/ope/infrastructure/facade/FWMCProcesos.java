package pe.bn.com.sate.ope.infrastructure.facade;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.bn.com.sate.ope.application.view.AutorizarSolicitudesController;
import pe.bn.com.sate.ope.infrastructure.exception.ExternalServiceMCProcesosException;
import pe.bn.com.sate.ope.infrastructure.exception.InternalServiceException;
import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.service.external.domain.mc.BasicHttpBinding_IService1Proxy;
import pe.bn.com.sate.ope.persistence.mapper.internal.ParametroMapper;
import pe.bn.com.sate.ope.persistence.mapper.internal.TarjetaMapper;
import pe.bn.com.sate.ope.transversal.dto.sate.ModificacionTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.MovimientoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.SaldoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.ws.ConsultaMovimientos;
import pe.bn.com.sate.ope.transversal.dto.ws.ConsultaSaldos;
import pe.bn.com.sate.ope.transversal.util.ServicioWebUtil;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.componentes.Parametros;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesGenerales;

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
		// if (tarjetaMapper.buscarTarjetaPorNumeroTarjeta(numTarjeta,	UsefulWebApplication.obtenerUsuario().getRuc()) != null) {

		if (true) {
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
				BasicHttpBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpBinding_IService1Proxy();
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

				JAXBContext jc = JAXBContext
						.newInstance(ConsultaMovimientos.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Movimientos>"),
						response.indexOf("</soap:Body>")));
				ConsultaMovimientos consultaMovimientos = (ConsultaMovimientos) unmarshaller
						.unmarshal(reader);
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
					"N�mero de tarjeta no encontrada");
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

			BasicHttpBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpBinding_IService1Proxy();
			response = basicHttpBinding_IService1Proxy
					.modificacionTarjetas(request);

			System.out.println(response.indexOf("<Modificacion_Tarjeta>"));
			System.out.println(response.indexOf("</Modificacion_Tarjeta>"));
			System.out.println(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));

			JAXBContext jc = JAXBContext.newInstance(ModificacionTarjeta.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			StringReader reader = new StringReader(response.substring(
					response.indexOf("<Modificacion_Tarjeta>"),
					response.indexOf("</soap:Body>")));
			ModificacionTarjeta modificacionTarjeta = (ModificacionTarjeta) unmarshaller
					.unmarshal(reader);

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
				BasicHttpBinding_IService1Proxy basicHttpBinding_IService1Proxy = new BasicHttpBinding_IService1Proxy();
				response = basicHttpBinding_IService1Proxy
						.consultaSaldos(request);

				System.out.println(response);
				System.out.println(response.indexOf("<Consulta_Saldos>"));
				System.out.println(response.indexOf("</Consulta_Saldos>"));
				System.out.println(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));

				JAXBContext jc = JAXBContext.newInstance(ConsultaSaldos.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();

				StringReader reader = new StringReader(response.substring(
						response.indexOf("<Consulta_Saldos>"),
						response.indexOf("</soap:Body>")));
				ConsultaSaldos consultaSaldos = (ConsultaSaldos) unmarshaller
						.unmarshal(reader);

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
					"N�mero de tarjeta no encontrada");
		}
	}

	
	public void informacionDeTarjeta(int idTarjeta) {
        logger.info("Iniciando consulta de informaci�n de tarjeta.");
        
        try {
            // Implementaci�n de la l�gica para consultar la informaci�n de la tarjeta
            logger.info("Consultando informaci�n de la tarjeta.");

            // Simulaci�n de la l�gica de negocio
            // Aqu� ir�a el c�digo que consulta y procesa la informaci�n de la tarjeta

            logger.info("Consulta de informaci�n de tarjeta finalizada exitosamente.");

        } catch (Exception ex) {
            logger.error("Error al consultar informaci�n de tarjeta", ex);
            // Manejo de excepciones espec�fico si es necesario
        } finally {
            logger.info("Finalizando consulta de informaci�n de tarjeta");
        }
    }

    public void bloqueoDeTarjeta(int idTarjeta, String motivoBloqueo) {
        logger.info("Iniciando bloqueo de tarjeta.");
        
        try {
            // Implementaci�n de la l�gica para bloquear la tarjeta
            logger.info("Bloqueando tarjeta.");

            // Simulaci�n de la l�gica de negocio
            // Aqu� ir�a el c�digo que realiza el bloqueo de la tarjeta

            logger.info("Bloqueo de tarjeta finalizado exitosamente.");

        } catch (Exception ex) {
            logger.error("Error al bloquear la tarjeta", ex);
            // Manejo de excepciones espec�fico si es necesario
        } finally {
            logger.info("Finalizando bloqueo de tarjeta");
        }
    }

    public void consultaDeMovimientoPorExpediente(int expedienteId) {
        logger.info("Iniciando consulta de movimientos por expediente.");
        
        try {
            // Implementaci�n de la l�gica para consultar movimientos por expediente
            logger.info("Consultando movimientos para el expediente.");

            // Simulaci�n de la l�gica de negocio
            // Aqu� ir�a el c�digo que consulta y procesa los movimientos del expediente

            logger.info("Consulta de movimientos por expediente finalizada exitosamente.");

        } catch (Exception ex) {
            logger.error("Error al consultar movimientos por expediente", ex);
            // Manejo de excepciones espec�fico si es necesario
        } finally {
            logger.info("Finalizando consulta de movimientos por expediente");
        }
    }

    public void consultaDeDatosPorExpediente(int expedienteId) {
        logger.info("Iniciando consulta de datos por expediente.");
        
        try {
            // Implementaci�n de la l�gica para consultar datos por expediente
            logger.info("Consultando datos para el expediente.");

            // Simulaci�n de la l�gica de negocio
            // Aqu� ir�a el c�digo que consulta y procesa los datos del expediente

            logger.info("Consulta de datos por expediente finalizada exitosamente.");

        } catch (Exception ex) {
            logger.error("Error al consultar datos por expediente", ex);
            // Manejo de excepciones espec�fico si es necesario
        } finally {
            logger.info("Finalizando consulta de datos por expediente");
        }
    }

    public void actualizacionDeDatos(int idTarjeta, String nuevosDatos) {
        logger.info("Iniciando actualizaci�n de datos para la tarjeta.");
        
        try {
            // Implementaci�n de la l�gica para actualizar datos de la tarjeta
            logger.info("Actualizando datos para la tarjeta.");

            // Simulaci�n de la l�gica de negocio
            // Aqu� ir�a el c�digo que actualiza los datos de la tarjeta

            logger.info("Actualizaci�n de datos finalizada exitosamente.");

        } catch (Exception ex) {
            logger.error("Error al actualizar datos de la tarjeta", ex);
            // Manejo de excepciones espec�fico si es necesario
        } finally {
            logger.info("Finalizando actualizaci�n de datos para la tarjeta");
        }
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
