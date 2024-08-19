package pe.bn.com.sate.ope.infrastructure.service.internal.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.bn.com.sate.ope.infrastructure.exception.InternalServiceException;
import pe.bn.com.sate.ope.infrastructure.service.internal.TarjetaService;
import pe.bn.com.sate.ope.persistence.mapper.internal.ClienteMapper;
import pe.bn.com.sate.ope.persistence.mapper.internal.EmpresaMapper;
import pe.bn.com.sate.ope.persistence.mapper.internal.TarjetaMapper;
import pe.bn.com.sate.ope.transversal.configuration.security.SecurityContextFacade;
import pe.bn.com.sate.ope.transversal.dto.sate.Cliente;
import pe.bn.com.sate.ope.transversal.dto.sate.DatosTarjetaCliente;
import pe.bn.com.sate.ope.transversal.dto.sate.EstadoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.SolicitudTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.Tarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.TarjetaResumen;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.enums.TipoBusqueda;
import pe.bn.com.sate.ope.transversal.util.enums.TipoEstadoTarjeta;

@Service
public class TarjetaServiceImpl implements TarjetaService {

	private final static String FLAG_CAMBIO_CLAVE = "1";

	private final static Logger logger = Logger
			.getLogger(TarjetaServiceImpl.class);

	private @Autowired
	ClienteMapper clienteMapper;

	private @Autowired
	TarjetaMapper tarjetaMapper;

	private @Autowired
	EmpresaMapper empresaMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	//@Transactional
	public void registrarSolicitudTarjeta(Tarjeta tarjeta, Cliente cliente) {
		try {
			logger.info("Inicio metodo registrarSolicitudTarjeta ");
			if (tarjetaMapper.cantidadTarjetasDisponiblesPorDocumento(
					cliente.getTipoDocumento(), cliente.getNroDocumento(),
					SecurityContextFacade.getAuthenticatedUser().getRuc()) == 0) {
				if (cliente.getId() == null) {

					clienteMapper.registrarCliente(cliente);
					logger.info("Exito registro Cliente");
				} else {
					clienteMapper.actualizarCliente(cliente);
					logger.info("Exito actualizacion Cliente");

				}

				tarjeta.setIdEmpresa(empresaMapper.buscarEmpresaPorRUC(
						SecurityContextFacade.getAuthenticatedUser().getRuc())
						.getId());

				tarjeta.setIdUsu(SecurityContextFacade.getAuthenticatedUser()
						.getId());
				cliente = clienteMapper.buscarCliente(cliente.getTipoDocumento(), cliente.getNroDocumento());
				logger.info("Exito trayendo cliente ");

				tarjeta.setIdCli(cliente.getId());
				tarjeta.setFechaCreacion(new Date());
				tarjeta.setFlagActualizarContacto("0");
				tarjetaMapper.registrarTarjeta(tarjeta);
				logger.info("Exito registro Tarjeta");
				tarjeta = tarjetaMapper.buscarTarjeta(tarjeta.getIdEmpresa(), tarjeta.getIdUsu(),cliente.getId());
				
				
				
				EstadoTarjeta estadoTarjeta = new EstadoTarjeta();
				estadoTarjeta.setIdTarjeta(tarjeta.getId());
				estadoTarjeta
						.setEstado(TipoEstadoTarjeta.SOLICITUD_TARJETA_REGISTRADA
								.getCod());
				estadoTarjeta.setFechaRegistro(new Date());
				estadoTarjeta.setUsuarioRegistro(UsefulWebApplication
						.obtenerUsuario().getUsername());

				tarjetaMapper.registrarEstadoTarjeta(estadoTarjeta);
				logger.info("Exito registro Estado Tarjeta");

			} else {
				throw new InternalServiceException(
						"Este cliente ya tiene una solicitud pendiente o una tarjeta activada");
			}
			logger.info("Fin metodo registrarSolicitudTarjeta ");

		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<SolicitudTarjeta> buscarTodosSolicitudTarjetaPendientes() {
		try {
			return tarjetaMapper.buscarTodosSolicitudesTarjetaPendientes(
					TipoEstadoTarjeta.SOLICITUD_TARJETA_REGISTRADA.getCod(),
					UsefulWebApplication.obtenerUsuario().getRuc());
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void aprobarSolicitudTarjeta(List<SolicitudTarjeta> solicitudTarjetas) {
		try {
			for (SolicitudTarjeta solicitudTarjeta : solicitudTarjetas) {
				EstadoTarjeta estadoTarjeta = new EstadoTarjeta();
				estadoTarjeta.setIdTarjeta(solicitudTarjeta.getId());
				estadoTarjeta
						.setEstado(TipoEstadoTarjeta.SOLICITUD_TARJETA_AUTORIZADA
								.getCod());
				estadoTarjeta.setFechaRegistro(new Date());
				estadoTarjeta.setUsuarioRegistro(UsefulWebApplication
						.obtenerUsuario().getUsername());
				tarjetaMapper.registrarEstadoTarjeta(estadoTarjeta);
			}
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void rechazarSolicitudTarjeta(
			List<SolicitudTarjeta> solicitudTarjetas) {
		try {
			for (SolicitudTarjeta solicitudTarjeta : solicitudTarjetas) {
				EstadoTarjeta estadoTarjeta = new EstadoTarjeta();
				estadoTarjeta.setIdTarjeta(solicitudTarjeta.getId());
				estadoTarjeta
						.setEstado(TipoEstadoTarjeta.SOLICITUD_TARJETA_CANCELADA
								.getCod());
				estadoTarjeta.setFechaRegistro(new Date());
				estadoTarjeta.setUsuarioRegistro(UsefulWebApplication
						.obtenerUsuario().getUsername());
				tarjetaMapper.registrarEstadoTarjeta(estadoTarjeta);
			}
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public Tarjeta buscarTarjetaPorNumeroTarjeta(String numTarjeta) {
		try {
			return tarjetaMapper.buscarTarjetaPorNumeroTarjeta(numTarjeta,
					UsefulWebApplication.obtenerUsuario().getRuc());
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<Tarjeta> buscarTarjetaPorTipoDocumento(String tipoDocumento,
			String numDocumento) {
		try {
			return tarjetaMapper.buscarTarjetaPorTipoDocumento(tipoDocumento,
					numDocumento, UsefulWebApplication.obtenerUsuario()
							.getRuc());
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public DatosTarjetaCliente buscarDatosTarjetasCliente(String tipoBusqueda,
			String numDocumento, String tipoOperacion) {
		try {
			DatosTarjetaCliente datosTarjetaCliente = new DatosTarjetaCliente();
			if (tipoBusqueda.equals(TipoBusqueda.NUM_TARJETA.getId())) {
				datosTarjetaCliente.setCliente(clienteMapper
						.buscarClientePorNumTajeta(numDocumento));
				datosTarjetaCliente
						.setTarjeta(tarjetaMapper
								.buscarTarjetaPorNumeroTarjeta(numDocumento,
										UsefulWebApplication.obtenerUsuario()
												.getRuc()));
			} else if (tipoBusqueda.equals(TipoBusqueda.DNI.getId())
					|| tipoBusqueda.equals(TipoBusqueda.CARNET_EXTRANJERIA
							.getId())) {
				datosTarjetaCliente.setCliente(clienteMapper.buscarCliente(
						tipoBusqueda, numDocumento));
				if (tipoOperacion.equals("B")) {// bloqueo
					datosTarjetaCliente.setTarjetas(tarjetaMapper
							.buscarTarjetaPorTipoDocumento(tipoBusqueda,
									numDocumento, UsefulWebApplication
											.obtenerUsuario().getRuc()));
				} else if (tipoOperacion.equals("C")) {// cancelacion
					datosTarjetaCliente.setTarjetas(tarjetaMapper
							.buscarTarjetaPorTipoDocumentoValidosParaBloqueo(
									tipoBusqueda, numDocumento,
									UsefulWebApplication.obtenerUsuario()
											.getRuc()));
				}
			}

			return datosTarjetaCliente;
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void actualizarEstadoTarjeta(EstadoTarjeta estadoTarjeta) {
		try {
			tarjetaMapper.registrarEstadoTarjeta(estadoTarjeta);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<TarjetaResumen> obtenerListaTarjetas(String cuentaCorriente,
			String fechaInicio, String fechaFin) {
		try {
			return tarjetaMapper.obtenerListaTarjetas(cuentaCorriente,
					fechaInicio, fechaFin);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}

	}

	@Override
	public void actualizarContacto(Tarjeta tarjeta) {
		try {
			tarjeta.setFlagActualizarContacto("1");
			tarjetaMapper.actualizarContacto(tarjeta);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void actualizarSaldos(Tarjeta tarjeta) {
		try {
			tarjetaMapper.actualizarSaldos(tarjeta);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void bloquearTarjetaPorRobo(EstadoTarjeta estadoTarjeta,
			Long idTarjeta, Long idCliente) {
		try {
			Tarjeta nuevaTarjeta = tarjetaMapper.buscarTarjetaPorId(idTarjeta);
			System.out.println("nuevaTarjeta:" + nuevaTarjeta.toString());
			tarjetaMapper.registrarEstadoTarjeta(estadoTarjeta);
			nuevaTarjeta.setIdEmpresa(empresaMapper.buscarEmpresaPorRUC(
					SecurityContextFacade.getAuthenticatedUser().getRuc())
					.getId());
			nuevaTarjeta.setIdUsu(SecurityContextFacade.getAuthenticatedUser()
					.getId());
			nuevaTarjeta.setFechaCreacion(new Date());
			tarjetaMapper.registrarTarjeta(nuevaTarjeta);
			EstadoTarjeta estadoTarjetaCreacion = new EstadoTarjeta();
			estadoTarjetaCreacion.setIdTarjeta(nuevaTarjeta.getId());
			estadoTarjetaCreacion
					.setEstado(TipoEstadoTarjeta.SOLICITUD_TARJETA_REGISTRADA
							.getCod());
			estadoTarjetaCreacion.setFechaRegistro(new Date());
			estadoTarjetaCreacion.setUsuarioRegistro(UsefulWebApplication
					.obtenerUsuario().getUsername());
			tarjetaMapper.registrarEstadoTarjeta(estadoTarjetaCreacion);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void actualizarEstadoCuenta(Tarjeta tarjeta) {
		try {
			tarjeta.setFlagActualizarEstadoCuenta(FLAG_CAMBIO_CLAVE);
			tarjetaMapper.actualizarestadoCuenta(tarjeta);
		} catch (Exception ex) {
			throw new InternalServiceException(ex.getMessage(), ex);
		}
	}

}
