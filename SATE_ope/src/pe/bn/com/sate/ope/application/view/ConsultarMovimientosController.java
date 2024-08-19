package pe.bn.com.sate.ope.application.view;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.bn.com.sate.ope.application.model.ConsultarMovimientosModel;
import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.facade.FWMCProcesos;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;
import pe.bn.com.sate.ope.transversal.util.constantes.ConstantesGenerales;

@Controller("consultarMovimientosController")
@Scope("view")
public class ConsultarMovimientosController {

	private final static Logger logger = Logger
			.getLogger(ConsultarMovimientosController.class);

	private ConsultarMovimientosModel consultarMovimientosModel;

	private @Autowired
	FWMCProcesos fwmcProcesos;

	@PostConstruct
	public void init() {
		consultarMovimientosModel = new ConsultarMovimientosModel();
	}

	public void buscarMovimientos() {
		consultarMovimientosModel.limpiarMovimientosTarjeta();
		try {
			consultarMovimientosModel.setMovimientosTarjeta(fwmcProcesos
					.consultarMovimientosPorTarjeta(consultarMovimientosModel
							.getNumeroTarjeta()));

		} catch (ServiceException se) {
			logger.error(se.getMessage());
			UsefulWebApplication.mostrarMensajeJSF(
					ConstantesGenerales.SEVERITY_ERROR, "", se.getMessage());
			UsefulWebApplication.actualizarComponente("msgs");
			UsefulWebApplication
					.actualizarComponente("formCambiarEstadoTarjeta:pgResultado");
		}
	}

	public ConsultarMovimientosModel getConsultarMovimientosModel() {
		return consultarMovimientosModel;
	}

	public void setConsultarMovimientosModel(
			ConsultarMovimientosModel consultarMovimientosModel) {
		this.consultarMovimientosModel = consultarMovimientosModel;
	}

}
