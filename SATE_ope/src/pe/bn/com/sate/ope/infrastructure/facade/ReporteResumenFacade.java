package pe.bn.com.sate.ope.infrastructure.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.service.internal.EmpresaService;
import pe.bn.com.sate.ope.infrastructure.service.internal.TarjetaService;
import pe.bn.com.sate.ope.persistence.mapper.internal.CargoMapper;
import pe.bn.com.sate.ope.persistence.mapper.internal.TransaccionMapper;
import pe.bn.com.sate.ope.transversal.dto.sate.Cargo;
import pe.bn.com.sate.ope.transversal.dto.sate.TarjetaResumen;
import pe.bn.com.sate.ope.transversal.dto.sate.Transaccion;
import pe.bn.com.sate.ope.transversal.util.UsefulWebApplication;

@Component
public class ReporteResumenFacade {

	private @Autowired
	TarjetaService tarjetaService;

	private @Autowired
	TransaccionMapper transaccionMapper;

	private @Autowired
	CargoMapper cargoMapper;

	private @Autowired
	EmpresaService empresaService;

	public List<TarjetaResumen> obtenerListaTarjetas(String fechaInicio,
			String fechaFin) throws ServiceException {
		return tarjetaService.obtenerListaTarjetas(
				empresaService.buscarEmpresaPorRUC(
						UsefulWebApplication.obtenerUsuario().getRuc())
						.getCuentaCorriente(), fechaInicio, fechaFin);

	}

	public List<Transaccion> obtenerListaTransacciones(String fechaInicio,
			String fechaFin) throws ServiceException {
		return transaccionMapper.obtenerlistaTransacciones(
				empresaService.buscarEmpresaPorRUC(
						UsefulWebApplication.obtenerUsuario().getRuc())
						.getCuentaCorriente(), fechaInicio, fechaFin);
	}

	public List<Cargo> obtenerListaCargos(String fechaInicio, String fechaFin)
			throws ServiceException {
		return cargoMapper.obtenerlistaCargos(
				empresaService.buscarEmpresaPorRUC(
						UsefulWebApplication.obtenerUsuario().getRuc())
						.getCuentaCorriente(), fechaInicio, fechaFin);
	}
}
