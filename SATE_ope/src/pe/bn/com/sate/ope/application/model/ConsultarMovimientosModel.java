package pe.bn.com.sate.ope.application.model;

import java.util.Date;
import java.util.List;

import pe.bn.com.sate.ope.transversal.dto.sate.MovimientoTarjeta;
import pe.bn.com.sate.ope.transversal.util.enums.TipoMoneda;
import pe.bn.com.sate.ope.transversal.util.enums.TipoMontoMC;
import pe.bn.com.sate.ope.transversal.util.enums.TipoTarjetaMC;

public class ConsultarMovimientosModel {

	private Date fechaInicio;
	private Date fechaFin;
	private String numeroTarjeta;

	private List<MovimientoTarjeta> movimientosTarjeta;

	public ConsultarMovimientosModel() {

	}

	public void limpiarMovimientosTarjeta() {
		if (movimientosTarjeta != null)
			movimientosTarjeta.clear();
	}

	public List<MovimientoTarjeta> getMovimientosTarjeta() {
		return movimientosTarjeta;
	}

	public void setMovimientosTarjeta(List<MovimientoTarjeta> movimientosTarjeta) {
		this.movimientosTarjeta = movimientosTarjeta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String tipoMontoLetras(String tipoMonto) {
		return TipoMontoMC.enLetras(tipoMonto);
	}

	public String tipoTarjetaLetras(String tipoTarjeta) {
		return TipoTarjetaMC.enLetras(tipoTarjeta);
	}

	public String tipoMonedaLetras(String tipoMoneda) {
		return TipoMoneda.decripcionTipoMoneda(tipoMoneda);
	}

	public boolean existenMovimientosTarjeta() {
		return (movimientosTarjeta != null && movimientosTarjeta.size() > 0) ? true
				: false;
	}

}
