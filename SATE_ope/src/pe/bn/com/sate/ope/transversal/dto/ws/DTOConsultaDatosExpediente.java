package pe.bn.com.sate.ope.transversal.dto.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Consulta_Datos_Expediente")
public class DTOConsultaDatosExpediente {

    private String codEmisor;
    private String codUsuario;
    private String numTerminal;
    private String numReferencia;
    private String organizacion;
    private String numCuenta;
    private String fechaExpiracion;
    private String codigoBloqueo;
    private String comercio;
    private String fechaTxnTerminal;
    private String horaTxnTerminal;
    private String reservado;
    private String idTransaccion;
    private String codRespuesta;
    private String descRespuesta;
    private String tipoTarjeta;
    private String bloqueoTarjeta;
    private String fechaCreacion;
    private String disposicionEfectivo;
    private String usoExtranjero;
    private String usoInternet;
    private String fechaInicioLinea;
    private String fechaTerminoLinea;
    private String lineaDisponible;
    private String disposicionEfectivoUsado;
    private String montoProcesar;
    private String porcentajeDE;
    private String estadoCuenta;
    private String nroCelular;
    private String correoElectronico;

    @XmlElement(name = "CodEmisor")
    public String getCodEmisor() {
        return codEmisor;
    }

    public void setCodEmisor(String codEmisor) {
        this.codEmisor = codEmisor;
    }

    @XmlElement(name = "CodUsuario")
    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    @XmlElement(name = "NumTerminal")
    public String getNumTerminal() {
        return numTerminal;
    }

    public void setNumTerminal(String numTerminal) {
        this.numTerminal = numTerminal;
    }

    @XmlElement(name = "NumReferencia")
    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    @XmlElement(name = "Organizacion")
    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    @XmlElement(name = "NumCuenta")
    public String getnumCuenta() {
        return numCuenta;
    }

    public void setnumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @XmlElement(name = "FechaExpiracion")
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @XmlElement(name = "CodigoBloqueo")
    public String getCodigoBloqueo() {
        return codigoBloqueo;
    }

    public void setCodigoBloqueo(String codigoBloqueo) {
        this.codigoBloqueo = codigoBloqueo;
    }

    @XmlElement(name = "Comercio")
    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    @XmlElement(name = "FechaTxnTerminal")
    public String getFechaTxnTerminal() {
        return fechaTxnTerminal;
    }

    public void setFechaTxnTerminal(String fechaTxnTerminal) {
        this.fechaTxnTerminal = fechaTxnTerminal;
    }

    @XmlElement(name = "HoraTxnTerminal")
    public String getHoraTxnTerminal() {
        return horaTxnTerminal;
    }

    public void setHoraTxnTerminal(String horaTxnTerminal) {
        this.horaTxnTerminal = horaTxnTerminal;
    }

    @XmlElement(name = "Reservado")
    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    @XmlElement(name = "IdTransaccion")
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @XmlElement(name = "CodRespuesta")
    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    @XmlElement(name = "DescRespuesta")
    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    @XmlElement(name = "TipoTarjeta")
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    @XmlElement(name = "BloqueoTarjeta")
    public String getBloqueoTarjeta() {
        return bloqueoTarjeta;
    }

    public void setBloqueoTarjeta(String bloqueoTarjeta) {
        this.bloqueoTarjeta = bloqueoTarjeta;
    }

    @XmlElement(name = "FechaCreacion")
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlElement(name = "DisposicionEfectivo")
    public String getDisposicionEfectivo() {
        return disposicionEfectivo;
    }

    public void setDisposicionEfectivo(String disposicionEfectivo) {
        this.disposicionEfectivo = disposicionEfectivo;
    }

    @XmlElement(name = "UsoExtranjero")
    public String getUsoExtranjero() {
        return usoExtranjero;
    }

    public void setUsoExtranjero(String usoExtranjero) {
        this.usoExtranjero = usoExtranjero;
    }

    @XmlElement(name = "UsoInternet")
    public String getUsoInternet() {
        return usoInternet;
    }

    public void setUsoInternet(String usoInternet) {
        this.usoInternet = usoInternet;
    }

    @XmlElement(name = "FechaInicioLinea")
    public String getFechaInicioLinea() {
        return fechaInicioLinea;
    }

    public void setFechaInicioLinea(String fechaInicioLinea) {
        this.fechaInicioLinea = fechaInicioLinea;
    }

    @XmlElement(name = "FechaTerminoLinea")
    public String getFechaTerminoLinea() {
        return fechaTerminoLinea;
    }

    public void setFechaTerminoLinea(String fechaTerminoLinea) {
        this.fechaTerminoLinea = fechaTerminoLinea;
    }

    @XmlElement(name = "LineaDisponible")
    public String getLineaDisponible() {
        return lineaDisponible;
    }

    public void setLineaDisponible(String lineaDisponible) {
        this.lineaDisponible = lineaDisponible;
    }

    @XmlElement(name = "DisposicionEfectivoUsado")
    public String getDisposicionEfectivoUsado() {
        return disposicionEfectivoUsado;
    }

    public void setDisposicionEfectivoUsado(String disposicionEfectivoUsado) {
        this.disposicionEfectivoUsado = disposicionEfectivoUsado;
    }

    @XmlElement(name = "MontoProcesar")
    public String getMontoProcesar() {
        return montoProcesar;
    }

    public void setMontoProcesar(String montoProcesar) {
        this.montoProcesar = montoProcesar;
    }

    @XmlElement(name = "PorcentajeDE")
    public String getPorcentajeDE() {
        return porcentajeDE;
    }

    public void setPorcentajeDE(String porcentajeDE) {
        this.porcentajeDE = porcentajeDE;
    }

    @XmlElement(name = "EstadoCuenta")
    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    @XmlElement(name = "NroCelular")
    public String getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(String nroCelular) {
        this.nroCelular = nroCelular;
    }

    @XmlElement(name = "CorreoElectronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "DTOConsultaDatosExpediente [codEmisor=" + codEmisor + ", codUsuario=" + codUsuario 
                + ", numTerminal=" + numTerminal + ", numReferencia=" + numReferencia 
                + ", organizacion=" + organizacion + ", numCuenta=" + numCuenta 
                + ", fechaExpiracion=" + fechaExpiracion + ", codigoBloqueo=" + codigoBloqueo 
                + ", comercio=" + comercio + ", fechaTxnTerminal=" + fechaTxnTerminal 
                + ", horaTxnTerminal=" + horaTxnTerminal + ", reservado=" + reservado 
                + ", idTransaccion=" + idTransaccion + ", codRespuesta=" + codRespuesta 
                + ", descRespuesta=" + descRespuesta + ", tipoTarjeta=" + tipoTarjeta 
                + ", bloqueoTarjeta=" + bloqueoTarjeta + ", fechaCreacion=" + fechaCreacion 
                + ", disposicionEfectivo=" + disposicionEfectivo + ", usoExtranjero=" + usoExtranjero 
                + ", usoInternet=" + usoInternet + ", fechaInicioLinea=" + fechaInicioLinea 
                + ", fechaTerminoLinea=" + fechaTerminoLinea + ", lineaDisponible=" + lineaDisponible 
                + ", disposicionEfectivoUsado=" + disposicionEfectivoUsado + ", montoProcesar=" + montoProcesar 
                + ", porcentajeDE=" + porcentajeDE + ", estadoCuenta=" + estadoCuenta 
                + ", nroCelular=" + nroCelular + ", correoElectronico=" + correoElectronico + "]";
    }
}
