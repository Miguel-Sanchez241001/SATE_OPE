package pe.bn.com.sate.ope.transversal.dto.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Informacion_Tarjeta")
public class DTOInformacionTarjeta {

    @XmlElement(name = "CodEmisor")
    private String codEmisor;

    @XmlElement(name = "CodUsuario")
    private String codUsuario;

    @XmlElement(name = "NumTerminal")
    private String numTerminal;

    @XmlElement(name = "NumTarjeta")
    private String numTarjeta;

    @XmlElement(name = "NumReferencia")
    private String numReferencia;

    @XmlElement(name = "FechaExpiracion")
    private String fechaExpiracion;

    @XmlElement(name = "Comercio")
    private String comercio;

    @XmlElement(name = "FechaTxnTerminal")
    private String fechaTxnTerminal;

    @XmlElement(name = "HoraTxnTerminal")
    private String horaTxnTerminal;

    @XmlElement(name = "Reservado")
    private String reservado;

    @XmlElement(name = "IdTransaccion")
    private String idTransaccion;

    @XmlElement(name = "CodRespuesta")
    private String codRespuesta;

    @XmlElement(name = "DescRespuesta")
    private String descRespuesta;

    // Constructor sin argumentos
    public DTOInformacionTarjeta() {
    }

    // Getters y Setters
    
    @Override
    public String toString() {
        return "DTOInformacionTarjeta [codEmisor=" + codEmisor
                + ", codUsuario=" + codUsuario + ", numTerminal=" + numTerminal
                + ", numTarjeta=" + numTarjeta + ", numReferencia="
                + numReferencia + ", fechaExpiracion=" + fechaExpiracion
                + ", comercio=" + comercio + ", fechaTxnTerminal="
                + fechaTxnTerminal + ", horaTxnTerminal=" + horaTxnTerminal
                + ", reservado=" + reservado + ", idTransaccion="
                + idTransaccion + ", codRespuesta=" + codRespuesta
                + ", descRespuesta=" + descRespuesta + "]";
    }

    public String getCodEmisor() {
        return codEmisor;
    }

    public void setCodEmisor(String codEmisor) {
        this.codEmisor = codEmisor;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNumTerminal() {
        return numTerminal;
    }

    public void setNumTerminal(String numTerminal) {
        this.numTerminal = numTerminal;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getFechaTxnTerminal() {
        return fechaTxnTerminal;
    }

    public void setFechaTxnTerminal(String fechaTxnTerminal) {
        this.fechaTxnTerminal = fechaTxnTerminal;
    }

    public String getHoraTxnTerminal() {
        return horaTxnTerminal;
    }

    public void setHoraTxnTerminal(String horaTxnTerminal) {
        this.horaTxnTerminal = horaTxnTerminal;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }
}
