package pe.bn.com.sate.ope.transversal.util.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoTarjeta {

	BLACK_COLIBRI("MC BLACK COLIBRI", "531013", "1"), BLACK_MACHUPICCHU(
			"MC BLACK MACHUPICCHU", "531013", "2"), CORPORATE_SILUETA_PERU(
		
			"VIATICO", "530927", "3"), CORPORATE_COLIBRI(
			"ENCARGO", "530927", "1"), CORPORATE_MACHUPICCHU(
			"CAJA CHICA", "530927", "2");

	private String descripcion;
	private String codigo;
	private String diseno;

	private TipoTarjeta(String descripcion, String codigo, String diseno) {
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.diseno = diseno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDiseno() {
		return diseno;
	}

	public static List<TipoTarjeta> buscarTipoTarjetaUsoNacional() {
		List<TipoTarjeta> listaTipoTarjeta = new ArrayList<TipoTarjeta>();
		listaTipoTarjeta.add(TipoTarjeta.CORPORATE_SILUETA_PERU);
		listaTipoTarjeta.add(TipoTarjeta.CORPORATE_COLIBRI);
		listaTipoTarjeta.add(TipoTarjeta.CORPORATE_MACHUPICCHU);
		return listaTipoTarjeta;
	}

	public static List<TipoTarjeta> buscarTipoTarjetaUsoExtrajero() {
		return Arrays.asList(values());
	}

	public static String descripcionTipotarjeta(String codigo, String diseno) {
		if (codigo != null && diseno != null)
			for (TipoTarjeta tipoTarjeta : values()) {
				if (codigo.equals(tipoTarjeta.getCodigo())
						&& diseno.equals(tipoTarjeta.getDiseno())) {
					return tipoTarjeta.getDescripcion();
				}
			}
		return "Ninguno";
	}

}
