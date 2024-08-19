package pe.bn.com.sate.ope.application.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.bn.com.sate.ope.application.model.SolicitarTarjetaModel;
import pe.bn.com.sate.ope.infrastructure.exception.ExternalServiceBnTablasException;
import pe.bn.com.sate.ope.infrastructure.exception.ExternalServiceWsReniecException;
import pe.bn.com.sate.ope.infrastructure.exception.InternalServiceException;
import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.facade.FWPersonaNatural;
import pe.bn.com.sate.ope.infrastructure.service.external.AgenciaService;
import pe.bn.com.sate.ope.infrastructure.service.external.UbigeoService;
import pe.bn.com.sate.ope.infrastructure.service.internal.ClienteService;
import pe.bn.com.sate.ope.infrastructure.service.internal.EmpresaService;
import pe.bn.com.sate.ope.infrastructure.service.internal.TarjetaService;
import pe.bn.com.sate.ope.transversal.configuration.security.SecurityContextFacade;
import pe.bn.com.sate.ope.transversal.dto.sate.Cliente;
import pe.bn.com.sate.ope.transversal.dto.sate.Empresa;
import pe.bn.com.sate.ope.transversal.dto.tablas.Agencia;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesGenerales;
import pe.bn.com.sate.ope.transversal.util.enums.TipoTarjeta;

@Controller("solicitarTarjertaController")
@Scope("view")
public class SolicitarTarjertaController implements Serializable {

	private final static Logger logger = Logger
			.getLogger(SolicitarTarjertaController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SolicitarTarjetaModel solicitarTarjetaModel;

	private @Autowired
	FWPersonaNatural fwPersonaNatural;

	private @Autowired
	ClienteService clienteService;

	private @Autowired
	TarjetaService tarjetaService;

	private @Autowired
	UbigeoService ubigeoService;

	private @Autowired
	AgenciaService agenciaService;

	private @Autowired
	EmpresaService empresaService;

	@PostConstruct
	public void init() {
		solicitarTarjetaModel = new SolicitarTarjetaModel();
		try {
			solicitarTarjetaModel.setDepartamentos(ubigeoService
					.buscarDepartamentos());
		} catch (InternalServiceException ise) {
			logger.error(ise.getMessage());
		} catch (ServiceException se) {
			logger.error(se.getMessage());
		}
		mostrarOpcionPorTipoUbicacion();
	}

	public void buscarCliente() {
		try {
			Cliente clienteBusqueda = clienteService.buscarCliente(
					solicitarTarjetaModel.getTipoDocumentoSeleccionado(),
					solicitarTarjetaModel.getNumDocumentoSeleccionado());

			// Se realiza la busqueda en la base de datos de SATE_ope
			if (clienteBusqueda == null) {
				clienteBusqueda = fwPersonaNatural.buscarCliente(
						solicitarTarjetaModel.getTipoDocumentoSeleccionado(),
						solicitarTarjetaModel.getNumDocumentoSeleccionado());
			}

			// Se realiza la busqueda en el servicio de la RENIEC
			if (clienteBusqueda == null) {
				solicitarTarjetaModel.setClienteSeleccionado(new Cliente());
				solicitarTarjetaModel.setPersonaExiste(false);
				// Mensaje de validacion
				UsefulWebApplication.mostrarMensajeJSF(
						ConstantesGenerales.SEVERITY_ERROR,
						ConstantesGenerales.TITULO_ERROR_AGREGAR_PARAMETRO,
						"No existe persona con el tipo y número de documento.");
			} else {
				solicitarTarjetaModel.setPersonaExiste(true);
				solicitarTarjetaModel.setClienteSeleccionado(clienteBusqueda);
			}
		} catch (InternalServiceException ise) {
			logger.error(ise.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_INTERNAL,
					ConstantesGenerales.ERROR_PERSISTENCE_INTERNAL);
		} catch (ExternalServiceWsReniecException ese) {
			logger.error(ese.getMessage());
			UsefulWebApplication
					.mostrarMensajeJSF(
							ConstantesGenerales.SEVERITY_ERROR,
							ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_RENIEC,
							ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_RENIEC);
		} catch (ServiceException se) {
			logger.error(se.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
		}
	}

	public void registarSolicitudTarjeta() {
		try {
			solicitarTarjetaModel.generarUbigeoPorUbicacion();
			solicitarTarjetaModel.getClienteSeleccionado().setTipoDocumento(
					solicitarTarjetaModel.getTipoDocumentoSeleccionado());
			solicitarTarjetaModel.getClienteSeleccionado().setNroDocumento(
					solicitarTarjetaModel.getNumDocumentoSeleccionado());
			tarjetaService.registrarSolicitudTarjeta(
					solicitarTarjetaModel.getTarjeta(),
					solicitarTarjetaModel.getClienteSeleccionado());
			reiniciarPasos();
			solicitarTarjetaModel.inicializarFormulario();
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_INFO,
					ConstantesGenerales.TITULO_MENSAJE,
					"Se registró la solicitud exitosamente");
			UsefulWebApplication.actualizarComponente("msgs");
		} catch (InternalServiceException ise) {
			logger.error(ise.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_INTERNAL,
					ConstantesGenerales.ERROR_PERSISTENCE_INTERNAL);
		} catch (ExternalServiceWsReniecException ese) {
			logger.error(ese.getMessage());
			UsefulWebApplication
					.mostrarMensajeJSF(
							ConstantesGenerales.SEVERITY_ERROR,
							ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_RENIEC,
							ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_WEB_SERVICE_RENIEC);
		} catch (ServiceException se) {
			logger.error(se.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
		}
	}

	public void buscarTipoTarjeta() {
		if (solicitarTarjetaModel.getTarjeta().getUsoExtranjero()
				.equals(ConstantesGenerales.USO_EXTRANJERO)) {
			/*solicitarTarjetaModel.setListaTipoTarjeta(TipoTarjeta
					.buscarTipoTarjetaUsoExtrajero());*/
			solicitarTarjetaModel.setListaTipoTarjeta(TipoTarjeta
					.buscarTipoTarjetaUsoNacional());
		} else if (solicitarTarjetaModel.getTarjeta().getUsoExtranjero()
				.equals(ConstantesGenerales.USO_NACIONAL)) {
			solicitarTarjetaModel.setListaTipoTarjeta(TipoTarjeta
					.buscarTipoTarjetaUsoNacional());
		} else {
			solicitarTarjetaModel.setListaTipoTarjeta(null);
		}
	}

	public void fijarTipoTarjetaYDiseno() {
		solicitarTarjetaModel.getTarjeta().setTipoTarjeta(
				solicitarTarjetaModel.getTipoTarjetaSeleccionada().getCodigo());
		solicitarTarjetaModel.getTarjeta().setDiseno(
				solicitarTarjetaModel.getTipoTarjetaSeleccionada().getDiseno());
	}

	public void mostrarOpcionPorTipoUbicacion() {
		try {
			if (solicitarTarjetaModel.getTarjeta().getEntregaUbicacion()
					.equals(ConstantesGenerales.ENTREGA_AGENCIA_BN)) {
				solicitarTarjetaModel.getTarjeta().setEntregaDireccion(null);
				solicitarTarjetaModel.setEsEntregaBN(true);
				solicitarTarjetaModel.setEsEntregaUE(false);
				solicitarTarjetaModel.setEsEntregaReferencia(true);
				solicitarTarjetaModel.getTarjeta().setEntregaDepartamento(null);
				solicitarTarjetaModel.getTarjeta().setEntregaProvincia(null);
				solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
				solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);
			} else if (solicitarTarjetaModel.getTarjeta().getEntregaUbicacion()
					.equals(ConstantesGenerales.ENTREGA_UNIDAD_EJECUTORA)) {

				// realiza consulta a la base de datos entrega
				Empresa empresa = empresaService
						.buscarEmpresaPorRUC(SecurityContextFacade
								.getAuthenticatedUser().getRuc());
				solicitarTarjetaModel.getTarjeta().setEntregaDireccion(
						empresa.getDireccion());
				solicitarTarjetaModel.getTarjeta().setEntregaUbigeo(
						empresa.getUbigeo());
				solicitarTarjetaModel.getTarjeta().setEntregaAgenciaBN("0000");
				System.out.println("Referencia: " + empresa.getReferencia());
				solicitarTarjetaModel.getTarjeta().setEntregaReferencia(
						empresa.getReferencia());
				solicitarTarjetaModel.setAgenciasBN(null);

				solicitarTarjetaModel.setEsEntregaBN(false);
				solicitarTarjetaModel.setEsEntregaUE(true);
				solicitarTarjetaModel.setEsEntregaReferencia(true);
				solicitarTarjetaModel.getTarjeta().setEntregaDepartamento(null);
				solicitarTarjetaModel.getTarjeta().setEntregaProvincia(null);
				solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
				solicitarTarjetaModel.setAgenciaSeleccionada(null);
			} else {
				solicitarTarjetaModel.getTarjeta().setEntregaDireccion(null);
				solicitarTarjetaModel.setAgenciasBN(null);

				solicitarTarjetaModel.setEsEntregaBN(false);
				solicitarTarjetaModel.setEsEntregaUE(false);
				solicitarTarjetaModel.setEsEntregaReferencia(false);
				solicitarTarjetaModel.getTarjeta().setEntregaDepartamento(null);
				solicitarTarjetaModel.getTarjeta().setEntregaProvincia(null);
				solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
				solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);
				solicitarTarjetaModel.setAgenciaSeleccionada(null);
			}
		} catch (ExternalServiceBnTablasException se) {
			logger.error(se.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS,
					ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS);
		}
	}

	public void buscarAgenciasPorUbigeo() {
		
		logger.info("[SolicitarTarjertaController] Inicio metodo buscarAgenciasPorUbigeo");
		String provincia = solicitarTarjetaModel.getTarjeta().getEntregaProvincia();
		String departamento = solicitarTarjetaModel.getTarjeta().getEntregaDepartamento();
		String distrito = solicitarTarjetaModel.getTarjeta().getEntregaDistrito();
		logger.info("[SolicitarTarjertaController] valor departamento: "+ departamento );
		logger.info("[SolicitarTarjertaController] valor Provincia: "+ provincia );
		logger.info("[SolicitarTarjertaController] valor distrito: "+ distrito );

		
		
		
		if (distrito == null) {
			solicitarTarjetaModel.getTarjeta().setEntregaAgenciaBN(null);
			solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);
		} else {
			try {
			/*	solicitarTarjetaModel.setAgenciasBN(agenciaService
						.buscarAgenciasPorUbigeo(solicitarTarjetaModel
								.getTarjeta().getEntregaDepartamento(),
								solicitarTarjetaModel.getTarjeta()
										.getEntregaProvincia(),
								solicitarTarjetaModel.getTarjeta()
										.getEntregaDistrito()));*/
				solicitarTarjetaModel.setAgenciasBN(agenciaService
						.buscarAgenciasPorUbigeo(departamento,provincia,distrito));
				solicitarTarjetaModel.getTarjeta().setEntregaAgenciaBN(null);
				solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);
			} catch (ExternalServiceBnTablasException este) {
				UsefulWebApplication
						.mostrarMensajeJSF(
								ConstantesGenerales.SEVERITY_ERROR,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS);
				logger.error(este.getMessage());
			} catch (ServiceException es) {
				UsefulWebApplication.mostrarMensajeJSF(
						ConstantesGenerales.SEVERITY_ERROR,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
				logger.error(es.getMessage());
			}
		}
		logger.info("[SolicitarTarjertaController] Fin metodo buscarAgenciasPorUbigeo");

	}

	public void buscarDatosAgencia() {
		try {
			logger.info("[SolicitarTarjertaController] Inicio metodo buscarDatosAgencia");
			Agencia agencia = agenciaService
					.buscarAgenciaPorCodAgencia(solicitarTarjetaModel
							.getAgenciaSeleccionada().getCodAgencia());
			solicitarTarjetaModel.getTarjeta().setEntregaDireccion(
					agencia == null ? "No hay direccion registrada" : agencia
							.getDireccion());
			logger.info("[SolicitarTarjertaController] fin metodo buscarDatosAgencia");

		} catch (ExternalServiceBnTablasException este) {
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS,
					ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS);
			logger.error(este.getMessage());
		} catch (ServiceException es) {
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
					ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
			logger.error(es.getMessage());
		}
	}

	public void buscarProvincias() {
		logger.info("[SolicitarTarjertaController] Inicio metodo buscarProvincias");
		String departamento = solicitarTarjetaModel.getTarjeta().getEntregaDepartamento();
		logger.info("[SolicitarTarjertaController] valor departamento: "+ departamento );

		if (departamento == null) {
			solicitarTarjetaModel.setProvincias(null);
			solicitarTarjetaModel.setDistritos(null);
			solicitarTarjetaModel.getTarjeta().setEntregaProvincia(null);
			solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
			solicitarTarjetaModel.setAgenciaSeleccionada(null);
			solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);

		} else {
			try {
				solicitarTarjetaModel.setProvincias(ubigeoService
						.buscarProvinciasPorDepartamento(departamento));
				solicitarTarjetaModel.setDistritos(null);
				solicitarTarjetaModel.getTarjeta().setEntregaProvincia(null);
				solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
				solicitarTarjetaModel.setAgenciaSeleccionada(null);
			} catch (ExternalServiceBnTablasException este) {
				UsefulWebApplication
						.mostrarMensajeJSF(
								ConstantesGenerales.SEVERITY_ERROR,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS);
				logger.error(este.getMessage());
			} catch (ServiceException es) {
				UsefulWebApplication.mostrarMensajeJSF(
						ConstantesGenerales.SEVERITY_ERROR,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
				logger.error(es.getMessage());
			}
		}
		logger.info("[SolicitarTarjertaController] Fin metodo buscarProvincias");

	}

	public void buscarDistritos() {
		logger.info("[SolicitarTarjertaController] Inicio metodo buscarDistritos");
		String provincia = solicitarTarjetaModel.getTarjeta().getEntregaProvincia();
		String departamento = solicitarTarjetaModel.getTarjeta().getEntregaDepartamento();
		logger.info("[SolicitarTarjertaController] valor Provincia: "+ provincia );
		logger.info("[SolicitarTarjertaController] valor departamento: "+ departamento );

		if (provincia == null) {
			logger.info("[SolicitarTarjertaController] Provincia nulo");
			solicitarTarjetaModel.setDistritos(null);
			solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
			solicitarTarjetaModel.setAgenciaSeleccionada(null);
			solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);

		} else {
			try {
				solicitarTarjetaModel.setDistritos(ubigeoService
						.buscarDistritosPorProvincia(departamento,provincia));
				solicitarTarjetaModel.getTarjeta().setEntregaDistrito(null);
				solicitarTarjetaModel.setAgenciaSeleccionada(null);
				solicitarTarjetaModel.getTarjeta().setEntregaReferencia(null);
			} catch (ExternalServiceBnTablasException este) {
				UsefulWebApplication
						.mostrarMensajeJSF(
								ConstantesGenerales.SEVERITY_ERROR,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS,
								ConstantesGenerales.ERROR_PERSISTENCE_EXTERNAL_BN_TABLAS);
				logger.error(este.getMessage());
			} catch (ServiceException es) {
				UsefulWebApplication.mostrarMensajeJSF(
						ConstantesGenerales.SEVERITY_ERROR,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL,
						ConstantesGenerales.ERROR_PERSISTENCE_GENERAL);
				logger.error(es.getMessage());
			}
		}
		logger.info("[SolicitarTarjertaController] fin metodo buscarDistritos");

	}

	public SolicitarTarjetaModel getSolicitarTarjetaModel() {
		return solicitarTarjetaModel;
	}

	public void setSolicitarTarjetaModel(
			SolicitarTarjetaModel solicitarTarjetaModel) {
		this.solicitarTarjetaModel = solicitarTarjetaModel;
	}

	public void avanzarPaso() {
		if (solicitarTarjetaModel.getPasoActual() != 0
				|| (!solicitarTarjetaModel.esTipoDocumentoDNI() || solicitarTarjetaModel
						.validarDNI())) {
			if (solicitarTarjetaModel.getPasoActual() < 3)
				solicitarTarjetaModel.setPasoActual(solicitarTarjetaModel
						.getPasoActual() + 1);

			UsefulWebApplication.ejecutar("activar("
					+ solicitarTarjetaModel.getPasoActual() + ")");
		} else {
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR,
					ConstantesGenerales.TITULO_ERROR_AGREGAR_PARAMETRO,
					"La persona no ha sido buscada");
		}
	}

	public void retrocederPaso() {
		UsefulWebApplication.ejecutar("desactivar("
				+ solicitarTarjetaModel.getPasoActual() + ")");

		if (solicitarTarjetaModel.getPasoActual() > 0)
			solicitarTarjetaModel.setPasoActual(solicitarTarjetaModel
					.getPasoActual() - 1);
	}

	public void reiniciarPasos() {
		UsefulWebApplication.ejecutar("reiniciar("
				+ solicitarTarjetaModel.getPasoActual() + ")");
	}
public void reiniciarFormularioCliente() {
	    logger.info("Reiniciando el formulario debido al cambio en el tipo de documento.");
	    solicitarTarjetaModel.reiniciarDatosCliente();
	    logger.info("Formulario reiniciado.");
	}



	
}
