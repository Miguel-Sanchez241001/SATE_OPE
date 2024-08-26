package pe.bn.com.sate.ope.infrastructure.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.bn.com.sate.ope.infrastructure.exception.ServiceException;
import pe.bn.com.sate.ope.infrastructure.service.internal.TarjetaService;
import pe.bn.com.sate.ope.transversal.dto.sate.SaldoTarjeta;
import pe.bn.com.sate.ope.transversal.dto.sate.Tarjeta;

@Component
public class BuscarTarjetaFacade {

	@Autowired
	private TarjetaService tarjetaService;

	@Autowired
	private FWMCProcesos fWMCProcesos;

	public void actualizarSaldoTarjeta(String numTarjeta)
			throws ServiceException {
		SaldoTarjeta saldoTarjeta = fWMCProcesos
				.consultarSaldosPorTarjeta(numTarjeta);
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setNumTarjeta(numTarjeta);
		tarjeta.setFechaInicioLinea(saldoTarjeta.getFechaApertura());
		tarjeta.setFechaTerminoLinea(saldoTarjeta.getFechaExpiracion());
		tarjeta.setMontoLineaAsignado(saldoTarjeta.getLineaCredito());
		tarjeta.setMontoLineaActual(saldoTarjeta.getDispActual());
		tarjeta.setMontoCompraUsado(saldoTarjeta.getPagoTotal());
		tarjeta.setMontoPorProcesar(saldoTarjeta.getImporteMora());
		tarjeta.setDispEfectivoUsado(saldoTarjeta.getEfectivo()
				- saldoTarjeta.getDispEfectivo());
		
		// tarjetaService.actualizarSaldos(tarjeta);

	}
}
