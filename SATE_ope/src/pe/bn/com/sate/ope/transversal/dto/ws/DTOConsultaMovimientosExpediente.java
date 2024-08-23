package pe.bn.com.sate.ope.transversal.dto.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Consulta_Movimientos_Expediente")
public class DTOConsultaMovimientosExpediente {
	private String codEmisor;
    private String codUsuario;
    private String numTerminal;
    private String numCuentaMonedero;
    private String numReferencia;
    private String fechaExpiracion;
    private String comercio;
    private String moneda;
    private String fechaTxnTerminal;
    private String horaTxnTerminal;
    private String reservado;
    private String idTransaccion;
    private String codRespuesta;
    private String descRespuesta;
    private String codAutorizacion;
    private String moviMoneda;
    private String moviCodBloqueo;
    private String moviDescBloqueo;
    private String moviNombreTarjeta;
    private String moviFechaExpiracion;
    private String moviUltMovimientos;

    // Mov1 - Mov20 fields
    private String mov1FechaOpe;
    private String mov1FechaPosteo;
    private String mov1Operacion;
    private String mov1NumCuenta;
    private String mov1NomComercio;
    private String mov1Monto;
    private String mov1Autorizacion;
    private String mov1Estado;

    private String mov2FechaOpe;
    private String mov2FechaPosteo;
    private String mov2Operacion;
    private String mov2NumCuenta;
    private String mov2NomComercio;
    private String mov2Monto;
    private String mov2Autorizacion;
    private String mov2Estado;

    private String mov3FechaOpe;
    private String mov3FechaPosteo;
    private String mov3Operacion;
    private String mov3NumCuenta;
    private String mov3NomComercio;
    private String mov3Monto;
    private String mov3Autorizacion;
    private String mov3Estado;

    private String mov4FechaOpe;
    private String mov4FechaPosteo;
    private String mov4Operacion;
    private String mov4NumCuenta;
    private String mov4NomComercio;
    private String mov4Monto;
    private String mov4Autorizacion;
    private String mov4Estado;

    private String mov5FechaOpe;
    private String mov5FechaPosteo;
    private String mov5Operacion;
    private String mov5NumCuenta;
    private String mov5NomComercio;
    private String mov5Monto;
    private String mov5Autorizacion;
    private String mov5Estado;

    private String mov6FechaOpe;
    private String mov6FechaPosteo;
    private String mov6Operacion;
    private String mov6NumCuenta;
    private String mov6NomComercio;
    private String mov6Monto;
    private String mov6Autorizacion;
    private String mov6Estado;

    private String mov7FechaOpe;
    private String mov7FechaPosteo;
    private String mov7Operacion;
    private String mov7NumCuenta;
    private String mov7NomComercio;
    private String mov7Monto;
    private String mov7Autorizacion;
    private String mov7Estado;

    private String mov8FechaOpe;
    private String mov8FechaPosteo;
    private String mov8Operacion;
    private String mov8NumCuenta;
    private String mov8NomComercio;
    private String mov8Monto;
    private String mov8Autorizacion;
    private String mov8Estado;

    private String mov9FechaOpe;
    private String mov9FechaPosteo;
    private String mov9Operacion;
    private String mov9NumCuenta;
    private String mov9NomComercio;
    private String mov9Monto;
    private String mov9Autorizacion;
    private String mov9Estado;

    private String mov10FechaOpe;
    private String mov10FechaPosteo;
    private String mov10Operacion;
    private String mov10NumCuenta;
    private String mov10NomComercio;
    private String mov10Monto;
    private String mov10Autorizacion;
    private String mov10Estado;

    private String mov11FechaOpe;
    private String mov11FechaPosteo;
    private String mov11Operacion;
    private String mov11NumCuenta;
    private String mov11NomComercio;
    private String mov11Monto;
    private String mov11Autorizacion;
    private String mov11Estado;

    private String mov12FechaOpe;
    private String mov12FechaPosteo;
    private String mov12Operacion;
    private String mov12NumCuenta;
    private String mov12NomComercio;
    private String mov12Monto;
    private String mov12Autorizacion;
    private String mov12Estado;

    private String mov13FechaOpe;
    private String mov13FechaPosteo;
    private String mov13Operacion;
    private String mov13NumCuenta;
    private String mov13NomComercio;
    private String mov13Monto;
    private String mov13Autorizacion;
    private String mov13Estado;

    private String mov14FechaOpe;
    private String mov14FechaPosteo;
    private String mov14Operacion;
    private String mov14NumCuenta;
    private String mov14NomComercio;
    private String mov14Monto;
    private String mov14Autorizacion;
    private String mov14Estado;

    private String mov15FechaOpe;
    private String mov15FechaPosteo;
    private String mov15Operacion;
    private String mov15NumCuenta;
    private String mov15NomComercio;
    private String mov15Monto;
    private String mov15Autorizacion;
    private String mov15Estado;

    private String mov16FechaOpe;
    private String mov16FechaPosteo;
    private String mov16Operacion;
    private String mov16NumCuenta;
    private String mov16NomComercio;
    private String mov16Monto;
    private String mov16Autorizacion;
    private String mov16Estado;

    private String mov17FechaOpe;
    private String mov17FechaPosteo;
    private String mov17Operacion;
    private String mov17NumCuenta;
    private String mov17NomComercio;
    private String mov17Monto;
    private String mov17Autorizacion;
    private String mov17Estado;

    private String mov18FechaOpe;
    private String mov18FechaPosteo;
    private String mov18Operacion;
    private String mov18NumCuenta;
    private String mov18NomComercio;
    private String mov18Monto;
    private String mov18Autorizacion;
    private String mov18Estado;

    private String mov19FechaOpe;
    private String mov19FechaPosteo;
    private String mov19Operacion;
    private String mov19NumCuenta;
    private String mov19NomComercio;
    private String mov19Monto;
    private String mov19Autorizacion;
    private String mov19Estado;

    private String mov20FechaOpe;
    private String mov20FechaPosteo;
    private String mov20Operacion;
    private String mov20NumCuenta;
    private String mov20NomComercio;
    private String mov20Monto;
    private String mov20Autorizacion;
    private String mov20Estado;
    
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

    @XmlElement(name = "NumCuenta")
    public String getNumTarjetaMonedero() {
        return numCuentaMonedero;
    }

    public void setNumTarjetaMonedero(String numTarjetaMonedero) {
        this.numCuentaMonedero = numTarjetaMonedero;
    }

    @XmlElement(name = "NumReferencia")
    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    @XmlElement(name = "FechaExpiracion")
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @XmlElement(name = "Comercio")
    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    @XmlElement(name = "Moneda")
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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

    @XmlElement(name = "CodAutorizacion")
    public String getCodAutorizacion() {
        return codAutorizacion;
    }

    public void setCodAutorizacion(String codAutorizacion) {
        this.codAutorizacion = codAutorizacion;
    }

    @XmlElement(name = "MoviMoneda")
    public String getMoviMoneda() {
        return moviMoneda;
    }

    public void setMoviMoneda(String moviMoneda) {
        this.moviMoneda = moviMoneda;
    }

    @XmlElement(name = "MoviCodBloqueo")
    public String getMoviCodBloqueo() {
        return moviCodBloqueo;
    }

    public void setMoviCodBloqueo(String moviCodBloqueo) {
        this.moviCodBloqueo = moviCodBloqueo;
    }

    @XmlElement(name = "MoviDescBloqueo")
    public String getMoviDescBloqueo() {
        return moviDescBloqueo;
    }

    public void setMoviDescBloqueo(String moviDescBloqueo) {
        this.moviDescBloqueo = moviDescBloqueo;
    }

    @XmlElement(name = "MoviNombreTarjeta")
    public String getMoviNombreTarjeta() {
        return moviNombreTarjeta;
    }

    public void setMoviNombreTarjeta(String moviNombreTarjeta) {
        this.moviNombreTarjeta = moviNombreTarjeta;
    }

    @XmlElement(name = "MoviFechaExpiracion")
    public String getMoviFechaExpiracion() {
        return moviFechaExpiracion;
    }

    public void setMoviFechaExpiracion(String moviFechaExpiracion) {
        this.moviFechaExpiracion = moviFechaExpiracion;
    }

    @XmlElement(name = "MoviUltMovimientos")
    public String getMoviUltMovimientos() {
        return moviUltMovimientos;
    }

    public void setMoviUltMovimientos(String moviUltMovimientos) {
        this.moviUltMovimientos = moviUltMovimientos;
    }

    @XmlElement(name = "Mov1FechaOpe")
    public String getMov1FechaOpe() {
        return mov1FechaOpe;
    }

    public void setMov1FechaOpe(String mov1FechaOpe) {
        this.mov1FechaOpe = mov1FechaOpe;
    }

    @XmlElement(name = "Mov1FechaPosteo")
    public String getMov1FechaPosteo() {
        return mov1FechaPosteo;
    }

    public void setMov1FechaPosteo(String mov1FechaPosteo) {
        this.mov1FechaPosteo = mov1FechaPosteo;
    }

    @XmlElement(name = "Mov1Operacion")
    public String getMov1Operacion() {
        return mov1Operacion;
    }

    public void setMov1Operacion(String mov1Operacion) {
        this.mov1Operacion = mov1Operacion;
    }

    @XmlElement(name = "Mov1NumCuenta")
    public String getMov1NumCuenta() {
        return mov1NumCuenta;
    }

    public void setMov1NumCuenta(String mov1NumCuenta) {
        this.mov1NumCuenta = mov1NumCuenta;
    }

    @XmlElement(name = "Mov1NomComercio")
    public String getMov1NomComercio() {
        return mov1NomComercio;
    }

    public void setMov1NomComercio(String mov1NomComercio) {
        this.mov1NomComercio = mov1NomComercio;
    }

    @XmlElement(name = "Mov1Monto")
    public String getMov1Monto() {
        return mov1Monto;
    }

    public void setMov1Monto(String mov1Monto) {
        this.mov1Monto = mov1Monto;
    }

    @XmlElement(name = "Mov1Autorizacion")
    public String getMov1Autorizacion() {
        return mov1Autorizacion;
    }

    public void setMov1Autorizacion(String mov1Autorizacion) {
        this.mov1Autorizacion = mov1Autorizacion;
    }

    @XmlElement(name = "Mov1Estado")
    public String getMov1Estado() {
        return mov1Estado;
    }

    public void setMov1Estado(String mov1Estado) {
        this.mov1Estado = mov1Estado;
    }
    
 // Movimiento 2  
    @XmlElement(name = "Mov2FechaOpe")
    public String getov2FechaOpe() {
        return mov2FechaOpe;
    }

    public void setov2FechaOpe(String ov2FechaOpe) {
        this.mov2FechaOpe = ov2FechaOpe;
    }

    @XmlElement(name = "Mov2FechaPosteo")
    public String getov2FechaPosteo() {
        return mov2FechaPosteo;
    }

    public void setov2FechaPosteo(String ov2FechaPosteo) {
        this.mov2FechaPosteo = ov2FechaPosteo;
    }

    @XmlElement(name = "Mov2Operacion")
    public String getov2Operacion() {
        return mov2Operacion;
    }

    public void setov2Operacion(String ov2Operacion) {
        this.mov2Operacion = ov2Operacion;
    }

    @XmlElement(name = "Mov2NumCuenta")
    public String getov2NumCuenta() {
        return mov2NumCuenta;
    }

    public void setov2NumCuenta(String ov2NumCuenta) {
        this.mov2NumCuenta = ov2NumCuenta;
    }

    @XmlElement(name = "Mov2NomComercio")
    public String getov2NomComercio() {
        return mov2NomComercio;
    }

    public void setov2NomComercio(String ov2NomComercio) {
        this.mov2NomComercio = ov2NomComercio;
    }

    @XmlElement(name = "Mov2Monto")
    public String getov2Monto() {
        return mov2Monto;
    }

    public void setov2Monto(String ov2Monto) {
        this.mov2Monto = ov2Monto;
    }

    @XmlElement(name = "Mov2Autorizacion")
    public String getov2Autorizacion() {
        return mov2Autorizacion;
    }

    public void setov2Autorizacion(String ov2Autorizacion) {
        this.mov2Autorizacion = ov2Autorizacion;
    }

    @XmlElement(name = "Mov2Estado")
    public String getov2Estado() {
        return mov2Estado;
    }

    public void setov2Estado(String ov2Estado) {
        this.mov2Estado = ov2Estado;
    }

    // Movimiento 3  
    @XmlElement(name = "Mov3FechaOpe")
    public String getov3FechaOpe() {
        return mov3FechaOpe;
    }

    public void setov3FechaOpe(String ov3FechaOpe) {
        this.mov3FechaOpe = ov3FechaOpe;
    }

    @XmlElement(name = "Mov3FechaPosteo")
    public String getov3FechaPosteo() {
        return mov3FechaPosteo;
    }

    public void setov3FechaPosteo(String ov3FechaPosteo) {
        this.mov3FechaPosteo = ov3FechaPosteo;
    }

    @XmlElement(name = "Mov3Operacion")
    public String getov3Operacion() {
        return mov3Operacion;
    }

    public void setov3Operacion(String ov3Operacion) {
        this.mov3Operacion = ov3Operacion;
    }

    @XmlElement(name = "Mov3NumCuenta")
    public String getov3NumCuenta() {
        return mov3NumCuenta;
    }

    public void setov3NumCuenta(String ov3NumCuenta) {
        this.mov3NumCuenta = ov3NumCuenta;
    }

    @XmlElement(name = "Mov3NomComercio")
    public String getov3NomComercio() {
        return mov3NomComercio;
    }

    public void setov3NomComercio(String ov3NomComercio) {
        this.mov3NomComercio = ov3NomComercio;
    }

    @XmlElement(name = "Mov3Monto")
    public String getov3Monto() {
        return mov3Monto;
    }

    public void setov3Monto(String ov3Monto) {
        this.mov3Monto = ov3Monto;
    }

    @XmlElement(name = "Mov3Autorizacion")
    public String getov3Autorizacion() {
        return mov3Autorizacion;
    }

    public void setov3Autorizacion(String ov3Autorizacion) {
        this.mov3Autorizacion = ov3Autorizacion;
    }

    @XmlElement(name = "Mov3Estado")
    public String getov3Estado() {
        return mov3Estado;
    }

    public void setov3Estado(String ov3Estado) {
        this.mov3Estado = ov3Estado;
    }

    // Movimiento 4  
    @XmlElement(name = "Mov4FechaOpe")
    public String getov4FechaOpe() {
        return mov4FechaOpe;
    }

    public void setov4FechaOpe(String ov4FechaOpe) {
        this.mov4FechaOpe = ov4FechaOpe;
    }

    @XmlElement(name = "Mov4FechaPosteo")
    public String getov4FechaPosteo() {
        return mov4FechaPosteo;
    }

    public void setov4FechaPosteo(String ov4FechaPosteo) {
        this.mov4FechaPosteo = ov4FechaPosteo;
    }

    @XmlElement(name = "Mov4Operacion")
    public String getov4Operacion() {
        return mov4Operacion;
    }

    public void setov4Operacion(String ov4Operacion) {
        this.mov4Operacion = ov4Operacion;
    }

    @XmlElement(name = "Mov4NumCuenta")
    public String getov4NumCuenta() {
        return mov4NumCuenta;
    }

    public void setov4NumCuenta(String ov4NumCuenta) {
        this.mov4NumCuenta = ov4NumCuenta;
    }

    @XmlElement(name = "Mov4NomComercio")
    public String getov4NomComercio() {
        return mov4NomComercio;
    }

    public void setov4NomComercio(String ov4NomComercio) {
        this.mov4NomComercio = ov4NomComercio;
    }

    @XmlElement(name = "Mov4Monto")
    public String getov4Monto() {
        return mov4Monto;
    }

    public void setov4Monto(String ov4Monto) {
        this.mov4Monto = ov4Monto;
    }

    @XmlElement(name = "Mov4Autorizacion")
    public String getov4Autorizacion() {
        return mov4Autorizacion;
    }

    public void setov4Autorizacion(String ov4Autorizacion) {
        this.mov4Autorizacion = ov4Autorizacion;
    }

    @XmlElement(name = "Mov4Estado")
    public String getov4Estado() {
        return mov4Estado;
    }

    public void setov4Estado(String ov4Estado) {
        this.mov4Estado = ov4Estado;
    }

    // Movimiento 5  
    @XmlElement(name = "Mov5FechaOpe")
    public String getov5FechaOpe() {
        return mov5FechaOpe;
    }

    public void setov5FechaOpe(String ov5FechaOpe) {
        this.mov5FechaOpe = ov5FechaOpe;
    }

    @XmlElement(name = "Mov5FechaPosteo")
    public String getov5FechaPosteo() {
        return mov5FechaPosteo;
    }

    public void setov5FechaPosteo(String ov5FechaPosteo) {
        this.mov5FechaPosteo = ov5FechaPosteo;
    }

    @XmlElement(name = "Mov5Operacion")
    public String getov5Operacion() {
        return mov5Operacion;
    }

    public void setov5Operacion(String ov5Operacion) {
        this.mov5Operacion = ov5Operacion;
    }

    @XmlElement(name = "Mov5NumCuenta")
    public String getov5NumCuenta() {
        return mov5NumCuenta;
    }

    public void setov5NumCuenta(String ov5NumCuenta) {
        this.mov5NumCuenta = ov5NumCuenta;
    }

    @XmlElement(name = "Mov5NomComercio")
    public String getov5NomComercio() {
        return mov5NomComercio;
    }

    public void setov5NomComercio(String ov5NomComercio) {
        this.mov5NomComercio = ov5NomComercio;
    }

    @XmlElement(name = "Mov5Monto")
    public String getov5Monto() {
        return mov5Monto;
    }

    public void setov5Monto(String ov5Monto) {
        this.mov5Monto = ov5Monto;
    }

    @XmlElement(name = "Mov5Autorizacion")
    public String getov5Autorizacion() {
        return mov5Autorizacion;
    }

    public void setov5Autorizacion(String ov5Autorizacion) {
        this.mov5Autorizacion = ov5Autorizacion;
    }

    @XmlElement(name = "Mov5Estado")
    public String getov5Estado() {
        return mov5Estado;
    }

    public void setov5Estado(String ov5Estado) {
        this.mov5Estado = ov5Estado;
    }

    // Movimiento 6  
    @XmlElement(name = "Mov6FechaOpe")
    public String getov6FechaOpe() {
        return mov6FechaOpe;
    }

    public void setov6FechaOpe(String ov6FechaOpe) {
        this.mov6FechaOpe = ov6FechaOpe;
    }

    @XmlElement(name = "Mov6FechaPosteo")
    public String getov6FechaPosteo() {
        return mov6FechaPosteo;
    }

    public void setov6FechaPosteo(String ov6FechaPosteo) {
        this.mov6FechaPosteo = ov6FechaPosteo;
    }

    @XmlElement(name = "Mov6Operacion")
    public String getov6Operacion() {
        return mov6Operacion;
    }

    public void setov6Operacion(String ov6Operacion) {
        this.mov6Operacion = ov6Operacion;
    }

    @XmlElement(name = "Mov6NumCuenta")
    public String getov6NumCuenta() {
        return mov6NumCuenta;
    }

    public void setov6NumCuenta(String ov6NumCuenta) {
        this.mov6NumCuenta = ov6NumCuenta;
    }

    @XmlElement(name = "Mov6NomComercio")
    public String getov6NomComercio() {
        return mov6NomComercio;
    }

    public void setov6NomComercio(String ov6NomComercio) {
        this.mov6NomComercio = ov6NomComercio;
    }

    @XmlElement(name = "Mov6Monto")
    public String getov6Monto() {
        return mov6Monto;
    }

    public void setov6Monto(String ov6Monto) {
        this.mov6Monto = ov6Monto;
    }

    @XmlElement(name = "Mov6Autorizacion")
    public String getov6Autorizacion() {
        return mov6Autorizacion;
    }

    public void setov6Autorizacion(String ov6Autorizacion) {
        this.mov6Autorizacion = ov6Autorizacion;
    }

    @XmlElement(name = "Mov6Estado")
    public String getov6Estado() {
        return mov6Estado;
    }

    public void setov6Estado(String ov6Estado) {
        this.mov6Estado = ov6Estado;
    }

    // Movimiento 7  
    @XmlElement(name = "Mov7FechaOpe")
    public String getov7FechaOpe() {
        return mov7FechaOpe;
    }

    public void setov7FechaOpe(String ov7FechaOpe) {
        this.mov7FechaOpe = ov7FechaOpe;
    }

    @XmlElement(name = "Mov7FechaPosteo")
    public String getov7FechaPosteo() {
        return mov7FechaPosteo;
    }

    public void setov7FechaPosteo(String ov7FechaPosteo) {
        this.mov7FechaPosteo = ov7FechaPosteo;
    }

    @XmlElement(name = "Mov7Operacion")
    public String getov7Operacion() {
        return mov7Operacion;
    }

    public void setov7Operacion(String ov7Operacion) {
        this.mov7Operacion = ov7Operacion;
    }

    @XmlElement(name = "Mov7NumCuenta")
    public String getov7NumCuenta() {
        return mov7NumCuenta;
    }

    public void setov7NumCuenta(String ov7NumCuenta) {
        this.mov7NumCuenta = ov7NumCuenta;
    }

    @XmlElement(name = "Mov7NomComercio")
    public String getov7NomComercio() {
        return mov7NomComercio;
    }

    public void setov7NomComercio(String ov7NomComercio) {
        this.mov7NomComercio = ov7NomComercio;
    }

    @XmlElement(name = "Mov7Monto")
    public String getov7Monto() {
        return mov7Monto;
    }

    public void setov7Monto(String ov7Monto) {
        this.mov7Monto = ov7Monto;
    }

    @XmlElement(name = "Mov7Autorizacion")
    public String getov7Autorizacion() {
        return mov7Autorizacion;
    }

    public void setov7Autorizacion(String ov7Autorizacion) {
        this.mov7Autorizacion = ov7Autorizacion;
    }

    @XmlElement(name = "Mov7Estado")
    public String getov7Estado() {
        return mov7Estado;
    }

    public void setov7Estado(String ov7Estado) {
        this.mov7Estado = ov7Estado;
    }

    // Movimiento 8  
    @XmlElement(name = "Mov8FechaOpe")
    public String getov8FechaOpe() {
        return mov8FechaOpe;
    }

    public void setov8FechaOpe(String ov8FechaOpe) {
        this.mov8FechaOpe = ov8FechaOpe;
    }

    @XmlElement(name = "Mov8FechaPosteo")
    public String getov8FechaPosteo() {
        return mov8FechaPosteo;
    }

    public void setov8FechaPosteo(String ov8FechaPosteo) {
        this.mov8FechaPosteo = ov8FechaPosteo;
    }

    @XmlElement(name = "Mov8Operacion")
    public String getov8Operacion() {
        return mov8Operacion;
    }

    public void setov8Operacion(String ov8Operacion) {
        this.mov8Operacion = ov8Operacion;
    }

    @XmlElement(name = "Mov8NumCuenta")
    public String getov8NumCuenta() {
        return mov8NumCuenta;
    }

    public void setov8NumCuenta(String ov8NumCuenta) {
        this.mov8NumCuenta = ov8NumCuenta;
    }

    @XmlElement(name = "Mov8NomComercio")
    public String getov8NomComercio() {
        return mov8NomComercio;
    }

    public void setov8NomComercio(String ov8NomComercio) {
        this.mov8NomComercio = ov8NomComercio;
    }

    @XmlElement(name = "Mov8Monto")
    public String getov8Monto() {
        return mov8Monto;
    }

    public void setov8Monto(String ov8Monto) {
        this.mov8Monto = ov8Monto;
    }

    @XmlElement(name = "Mov8Autorizacion")
    public String getov8Autorizacion() {
        return mov8Autorizacion;
    }

    public void setov8Autorizacion(String ov8Autorizacion) {
        this.mov8Autorizacion = ov8Autorizacion;
    }

    @XmlElement(name = "Mov8Estado")
    public String getov8Estado() {
        return mov8Estado;
    }

    public void setov8Estado(String ov8Estado) {
        this.mov8Estado = ov8Estado;
    }

    // Movimiento 9  
    @XmlElement(name = "Mov9FechaOpe")
    public String getov9FechaOpe() {
        return mov9FechaOpe;
    }

    public void setov9FechaOpe(String ov9FechaOpe) {
        this.mov9FechaOpe = ov9FechaOpe;
    }

    @XmlElement(name = "Mov9FechaPosteo")
    public String getov9FechaPosteo() {
        return mov9FechaPosteo;
    }

    public void setov9FechaPosteo(String ov9FechaPosteo) {
        this.mov9FechaPosteo = ov9FechaPosteo;
    }

    @XmlElement(name = "Mov9Operacion")
    public String getov9Operacion() {
        return mov9Operacion;
    }

    public void setov9Operacion(String ov9Operacion) {
        this.mov9Operacion = ov9Operacion;
    }

    @XmlElement(name = "Mov9NumCuenta")
    public String getov9NumCuenta() {
        return mov9NumCuenta;
    }

    public void setov9NumCuenta(String ov9NumCuenta) {
        this.mov9NumCuenta = ov9NumCuenta;
    }

    @XmlElement(name = "Mov9NomComercio")
    public String getov9NomComercio() {
        return mov9NomComercio;
    }

    public void setov9NomComercio(String ov9NomComercio) {
        this.mov9NomComercio = ov9NomComercio;
    }

    @XmlElement(name = "Mov9Monto")
    public String getov9Monto() {
        return mov9Monto;
    }

    public void setov9Monto(String ov9Monto) {
        this.mov9Monto = ov9Monto;
    }

    @XmlElement(name = "Mov9Autorizacion")
    public String getov9Autorizacion() {
        return mov9Autorizacion;
    }

    public void setov9Autorizacion(String ov9Autorizacion) {
        this.mov9Autorizacion = ov9Autorizacion;
    }

    @XmlElement(name = "Mov9Estado")
    public String getov9Estado() {
        return mov9Estado;
    }

    public void setov9Estado(String ov9Estado) {
        this.mov9Estado = ov9Estado;
    }

    // Movimiento 10  
    @XmlElement(name = "Mov10FechaOpe")
    public String getov10FechaOpe() {
        return mov10FechaOpe;
    }

    public void setov10FechaOpe(String ov10FechaOpe) {
        this.mov10FechaOpe = ov10FechaOpe;
    }

    @XmlElement(name = "Mov10FechaPosteo")
    public String getov10FechaPosteo() {
        return mov10FechaPosteo;
    }

    public void setov10FechaPosteo(String ov10FechaPosteo) {
        this.mov10FechaPosteo = ov10FechaPosteo;
    }

    @XmlElement(name = "Mov10Operacion")
    public String getov10Operacion() {
        return mov10Operacion;
    }

    public void setov10Operacion(String ov10Operacion) {
        this.mov10Operacion = ov10Operacion;
    }

    @XmlElement(name = "Mov10NumCuenta")
    public String getov10NumCuenta() {
        return mov10NumCuenta;
    }

    public void setov10NumCuenta(String ov10NumCuenta) {
        this.mov10NumCuenta = ov10NumCuenta;
    }

    @XmlElement(name = "Mov10NomComercio")
    public String getov10NomComercio() {
        return mov10NomComercio;
    }

    public void setov10NomComercio(String ov10NomComercio) {
        this.mov10NomComercio = ov10NomComercio;
    }

    @XmlElement(name = "Mov10Monto")
    public String getov10Monto() {
        return mov10Monto;
    }

    public void setov10Monto(String ov10Monto) {
        this.mov10Monto = ov10Monto;
    }

    @XmlElement(name = "Mov10Autorizacion")
    public String getov10Autorizacion() {
        return mov10Autorizacion;
    }

    public void setov10Autorizacion(String ov10Autorizacion) {
        this.mov10Autorizacion = ov10Autorizacion;
    }

    @XmlElement(name = "Mov10Estado")
    public String getov10Estado() {
        return mov10Estado;
    }

    public void setov10Estado(String ov10Estado) {
        this.mov10Estado = ov10Estado;
    }

    // Movimiento 11  
    @XmlElement(name = "Mov11FechaOpe")
    public String getov11FechaOpe() {
        return mov11FechaOpe;
    }

    public void setov11FechaOpe(String ov11FechaOpe) {
        this.mov11FechaOpe = ov11FechaOpe;
    }

    @XmlElement(name = "Mov11FechaPosteo")
    public String getov11FechaPosteo() {
        return mov11FechaPosteo;
    }

    public void setov11FechaPosteo(String ov11FechaPosteo) {
        this.mov11FechaPosteo = ov11FechaPosteo;
    }

    @XmlElement(name = "Mov11Operacion")
    public String getov11Operacion() {
        return mov11Operacion;
    }

    public void setov11Operacion(String ov11Operacion) {
        this.mov11Operacion = ov11Operacion;
    }

    @XmlElement(name = "Mov11NumCuenta")
    public String getov11NumCuenta() {
        return mov11NumCuenta;
    }

    public void setov11NumCuenta(String ov11NumCuenta) {
        this.mov11NumCuenta = ov11NumCuenta;
    }

    @XmlElement(name = "Mov11NomComercio")
    public String getov11NomComercio() {
        return mov11NomComercio;
    }

    public void setov11NomComercio(String ov11NomComercio) {
        this.mov11NomComercio = ov11NomComercio;
    }

    @XmlElement(name = "Mov11Monto")
    public String getov11Monto() {
        return mov11Monto;
    }

    public void setov11Monto(String ov11Monto) {
        this.mov11Monto = ov11Monto;
    }

    @XmlElement(name = "Mov11Autorizacion")
    public String getov11Autorizacion() {
        return mov11Autorizacion;
    }

    public void setov11Autorizacion(String ov11Autorizacion) {
        this.mov11Autorizacion = ov11Autorizacion;
    }

    @XmlElement(name = "Mov11Estado")
    public String getov11Estado() {
        return mov11Estado;
    }

    public void setov11Estado(String ov11Estado) {
        this.mov11Estado = ov11Estado;
    }

    // Movimiento 12  
    @XmlElement(name = "Mov12FechaOpe")
    public String getov12FechaOpe() {
        return mov12FechaOpe;
    }

    public void setov12FechaOpe(String ov12FechaOpe) {
        this.mov12FechaOpe = ov12FechaOpe;
    }

    @XmlElement(name = "Mov12FechaPosteo")
    public String getov12FechaPosteo() {
        return mov12FechaPosteo;
    }

    public void setov12FechaPosteo(String ov12FechaPosteo) {
        this.mov12FechaPosteo = ov12FechaPosteo;
    }

    @XmlElement(name = "Mov12Operacion")
    public String getov12Operacion() {
        return mov12Operacion;
    }

    public void setov12Operacion(String ov12Operacion) {
        this.mov12Operacion = ov12Operacion;
    }

    @XmlElement(name = "Mov12NumCuenta")
    public String getov12NumCuenta() {
        return mov12NumCuenta;
    }

    public void setov12NumCuenta(String ov12NumCuenta) {
        this.mov12NumCuenta = ov12NumCuenta;
    }

    @XmlElement(name = "Mov12NomComercio")
    public String getov12NomComercio() {
        return mov12NomComercio;
    }

    public void setov12NomComercio(String ov12NomComercio) {
        this.mov12NomComercio = ov12NomComercio;
    }

    @XmlElement(name = "Mov12Monto")
    public String getov12Monto() {
        return mov12Monto;
    }

    public void setov12Monto(String ov12Monto) {
        this.mov12Monto = ov12Monto;
    }

    @XmlElement(name = "Mov12Autorizacion")
    public String getov12Autorizacion() {
        return mov12Autorizacion;
    }

    public void setov12Autorizacion(String ov12Autorizacion) {
        this.mov12Autorizacion = ov12Autorizacion;
    }

    @XmlElement(name = "Mov12Estado")
    public String getov12Estado() {
        return mov12Estado;
    }

    public void setov12Estado(String ov12Estado) {
        this.mov12Estado = ov12Estado;
    }

    // Movimiento 13  
    @XmlElement(name = "Mov13FechaOpe")
    public String getov13FechaOpe() {
        return mov13FechaOpe;
    }

    public void setov13FechaOpe(String ov13FechaOpe) {
        this.mov13FechaOpe = ov13FechaOpe;
    }

    @XmlElement(name = "Mov13FechaPosteo")
    public String getov13FechaPosteo() {
        return mov13FechaPosteo;
    }

    public void setov13FechaPosteo(String ov13FechaPosteo) {
        this.mov13FechaPosteo = ov13FechaPosteo;
    }

    @XmlElement(name = "Mov13Operacion")
    public String getov13Operacion() {
        return mov13Operacion;
    }

    public void setov13Operacion(String ov13Operacion) {
        this.mov13Operacion = ov13Operacion;
    }

    @XmlElement(name = "Mov13NumCuenta")
    public String getov13NumCuenta() {
        return mov13NumCuenta;
    }

    public void setov13NumCuenta(String ov13NumCuenta) {
        this.mov13NumCuenta = ov13NumCuenta;
    }

    @XmlElement(name = "Mov13NomComercio")
    public String getov13NomComercio() {
        return mov13NomComercio;
    }

    public void setov13NomComercio(String ov13NomComercio) {
        this.mov13NomComercio = ov13NomComercio;
    }

    @XmlElement(name = "Mov13Monto")
    public String getov13Monto() {
        return mov13Monto;
    }

    public void setov13Monto(String ov13Monto) {
        this.mov13Monto = ov13Monto;
    }

    @XmlElement(name = "Mov13Autorizacion")
    public String getov13Autorizacion() {
        return mov13Autorizacion;
    }

    public void setov13Autorizacion(String ov13Autorizacion) {
        this.mov13Autorizacion = ov13Autorizacion;
    }

    @XmlElement(name = "Mov13Estado")
    public String getov13Estado() {
        return mov13Estado;
    }

    public void setov13Estado(String ov13Estado) {
        this.mov13Estado = ov13Estado;
    }

    // Movimiento 14  
    @XmlElement(name = "Mov14FechaOpe")
    public String getov14FechaOpe() {
        return mov14FechaOpe;
    }

    public void setov14FechaOpe(String ov14FechaOpe) {
        this.mov14FechaOpe = ov14FechaOpe;
    }

    @XmlElement(name = "Mov14FechaPosteo")
    public String getov14FechaPosteo() {
        return mov14FechaPosteo;
    }

    public void setov14FechaPosteo(String ov14FechaPosteo) {
        this.mov14FechaPosteo = ov14FechaPosteo;
    }

    @XmlElement(name = "Mov14Operacion")
    public String getov14Operacion() {
        return mov14Operacion;
    }

    public void setov14Operacion(String ov14Operacion) {
        this.mov14Operacion = ov14Operacion;
    }

    @XmlElement(name = "Mov14NumCuenta")
    public String getov14NumCuenta() {
        return mov14NumCuenta;
    }

    public void setov14NumCuenta(String ov14NumCuenta) {
        this.mov14NumCuenta = ov14NumCuenta;
    }

    @XmlElement(name = "Mov14NomComercio")
    public String getov14NomComercio() {
        return mov14NomComercio;
    }

    public void setov14NomComercio(String ov14NomComercio) {
        this.mov14NomComercio = ov14NomComercio;
    }

    @XmlElement(name = "Mov14Monto")
    public String getov14Monto() {
        return mov14Monto;
    }

    public void setov14Monto(String ov14Monto) {
        this.mov14Monto = ov14Monto;
    }

    @XmlElement(name = "Mov14Autorizacion")
    public String getov14Autorizacion() {
        return mov14Autorizacion;
    }

    public void setov14Autorizacion(String ov14Autorizacion) {
        this.mov14Autorizacion = ov14Autorizacion;
    }

    @XmlElement(name = "Mov14Estado")
    public String getov14Estado() {
        return mov14Estado;
    }

    public void setov14Estado(String ov14Estado) {
        this.mov14Estado = ov14Estado;
    }

    // Movimiento 15  
    @XmlElement(name = "Mov15FechaOpe")
    public String getov15FechaOpe() {
        return mov15FechaOpe;
    }

    public void setov15FechaOpe(String ov15FechaOpe) {
        this.mov15FechaOpe = ov15FechaOpe;
    }

    @XmlElement(name = "Mov15FechaPosteo")
    public String getov15FechaPosteo() {
        return mov15FechaPosteo;
    }

    public void setov15FechaPosteo(String ov15FechaPosteo) {
        this.mov15FechaPosteo = ov15FechaPosteo;
    }

    @XmlElement(name = "Mov15Operacion")
    public String getov15Operacion() {
        return mov15Operacion;
    }

    public void setov15Operacion(String ov15Operacion) {
        this.mov15Operacion = ov15Operacion;
    }

    @XmlElement(name = "Mov15NumCuenta")
    public String getov15NumCuenta() {
        return mov15NumCuenta;
    }

    public void setov15NumCuenta(String ov15NumCuenta) {
        this.mov15NumCuenta = ov15NumCuenta;
    }

    @XmlElement(name = "Mov15NomComercio")
    public String getov15NomComercio() {
        return mov15NomComercio;
    }

    public void setov15NomComercio(String ov15NomComercio) {
        this.mov15NomComercio = ov15NomComercio;
    }

    @XmlElement(name = "Mov15Monto")
    public String getov15Monto() {
        return mov15Monto;
    }

    public void setov15Monto(String ov15Monto) {
        this.mov15Monto = ov15Monto;
    }

    @XmlElement(name = "Mov15Autorizacion")
    public String getov15Autorizacion() {
        return mov15Autorizacion;
    }

    public void setov15Autorizacion(String ov15Autorizacion) {
        this.mov15Autorizacion = ov15Autorizacion;
    }

    @XmlElement(name = "Mov15Estado")
    public String getov15Estado() {
        return mov15Estado;
    }

    public void setov15Estado(String ov15Estado) {
        this.mov15Estado = ov15Estado;
    }

    // Movimiento 16  
    @XmlElement(name = "Mov16FechaOpe")
    public String getov16FechaOpe() {
        return mov16FechaOpe;
    }

    public void setov16FechaOpe(String ov16FechaOpe) {
        this.mov16FechaOpe = ov16FechaOpe;
    }

    @XmlElement(name = "Mov16FechaPosteo")
    public String getov16FechaPosteo() {
        return mov16FechaPosteo;
    }

    public void setov16FechaPosteo(String ov16FechaPosteo) {
        this.mov16FechaPosteo = ov16FechaPosteo;
    }

    @XmlElement(name = "Mov16Operacion")
    public String getov16Operacion() {
        return mov16Operacion;
    }

    public void setov16Operacion(String ov16Operacion) {
        this.mov16Operacion = ov16Operacion;
    }

    @XmlElement(name = "Mov16NumCuenta")
    public String getov16NumCuenta() {
        return mov16NumCuenta;
    }

    public void setov16NumCuenta(String ov16NumCuenta) {
        this.mov16NumCuenta = ov16NumCuenta;
    }

    @XmlElement(name = "Mov16NomComercio")
    public String getov16NomComercio() {
        return mov16NomComercio;
    }

    public void setov16NomComercio(String ov16NomComercio) {
        this.mov16NomComercio = ov16NomComercio;
    }

    @XmlElement(name = "Mov16Monto")
    public String getov16Monto() {
        return mov16Monto;
    }

    public void setov16Monto(String ov16Monto) {
        this.mov16Monto = ov16Monto;
    }

    @XmlElement(name = "Mov16Autorizacion")
    public String getov16Autorizacion() {
        return mov16Autorizacion;
    }

    public void setov16Autorizacion(String ov16Autorizacion) {
        this.mov16Autorizacion = ov16Autorizacion;
    }

    @XmlElement(name = "Mov16Estado")
    public String getov16Estado() {
        return mov16Estado;
    }

    public void setov16Estado(String ov16Estado) {
        this.mov16Estado = ov16Estado;
    }

    // Movimiento 17  
    @XmlElement(name = "Mov17FechaOpe")
    public String getov17FechaOpe() {
        return mov17FechaOpe;
    }

    public void setov17FechaOpe(String ov17FechaOpe) {
        this.mov17FechaOpe = ov17FechaOpe;
    }

    @XmlElement(name = "Mov17FechaPosteo")
    public String getov17FechaPosteo() {
        return mov17FechaPosteo;
    }

    public void setov17FechaPosteo(String ov17FechaPosteo) {
        this.mov17FechaPosteo = ov17FechaPosteo;
    }

    @XmlElement(name = "Mov17Operacion")
    public String getov17Operacion() {
        return mov17Operacion;
    }

    public void setov17Operacion(String ov17Operacion) {
        this.mov17Operacion = ov17Operacion;
    }

    @XmlElement(name = "Mov17NumCuenta")
    public String getov17NumCuenta() {
        return mov17NumCuenta;
    }

    public void setov17NumCuenta(String ov17NumCuenta) {
        this.mov17NumCuenta = ov17NumCuenta;
    }

    @XmlElement(name = "Mov17NomComercio")
    public String getov17NomComercio() {
        return mov17NomComercio;
    }

    public void setov17NomComercio(String ov17NomComercio) {
        this.mov17NomComercio = ov17NomComercio;
    }

    @XmlElement(name = "Mov17Monto")
    public String getov17Monto() {
        return mov17Monto;
    }

    public void setov17Monto(String ov17Monto) {
        this.mov17Monto = ov17Monto;
    }

    @XmlElement(name = "Mov17Autorizacion")
    public String getov17Autorizacion() {
        return mov17Autorizacion;
    }

    public void setov17Autorizacion(String ov17Autorizacion) {
        this.mov17Autorizacion = ov17Autorizacion;
    }

    @XmlElement(name = "Mov17Estado")
    public String getov17Estado() {
        return mov17Estado;
    }

    public void setov17Estado(String ov17Estado) {
        this.mov17Estado = ov17Estado;
    }

    // Movimiento 18  
    @XmlElement(name = "Mov18FechaOpe")
    public String getov18FechaOpe() {
        return mov18FechaOpe;
    }

    public void setov18FechaOpe(String ov18FechaOpe) {
        this.mov18FechaOpe = ov18FechaOpe;
    }

    @XmlElement(name = "Mov18FechaPosteo")
    public String getov18FechaPosteo() {
        return mov18FechaPosteo;
    }

    public void setov18FechaPosteo(String ov18FechaPosteo) {
        this.mov18FechaPosteo = ov18FechaPosteo;
    }

    @XmlElement(name = "Mov18Operacion")
    public String getov18Operacion() {
        return mov18Operacion;
    }

    public void setov18Operacion(String ov18Operacion) {
        this.mov18Operacion = ov18Operacion;
    }

    @XmlElement(name = "Mov18NumCuenta")
    public String getov18NumCuenta() {
        return mov18NumCuenta;
    }

    public void setov18NumCuenta(String ov18NumCuenta) {
        this.mov18NumCuenta = ov18NumCuenta;
    }

    @XmlElement(name = "Mov18NomComercio")
    public String getov18NomComercio() {
        return mov18NomComercio;
    }

    public void setov18NomComercio(String ov18NomComercio) {
        this.mov18NomComercio = ov18NomComercio;
    }

    @XmlElement(name = "Mov18Monto")
    public String getov18Monto() {
        return mov18Monto;
    }

    public void setov18Monto(String ov18Monto) {
        this.mov18Monto = ov18Monto;
    }

    @XmlElement(name = "Mov18Autorizacion")
    public String getov18Autorizacion() {
        return mov18Autorizacion;
    }

    public void setov18Autorizacion(String ov18Autorizacion) {
        this.mov18Autorizacion = ov18Autorizacion;
    }

    @XmlElement(name = "Mov18Estado")
    public String getov18Estado() {
        return mov18Estado;
    }

    public void setov18Estado(String ov18Estado) {
        this.mov18Estado = ov18Estado;
    }

    // Movimiento 19  
    @XmlElement(name = "Mov19FechaOpe")
    public String getov19FechaOpe() {
        return mov19FechaOpe;
    }

    public void setov19FechaOpe(String ov19FechaOpe) {
        this.mov19FechaOpe = ov19FechaOpe;
    }

    @XmlElement(name = "Mov19FechaPosteo")
    public String getov19FechaPosteo() {
        return mov19FechaPosteo;
    }

    public void setov19FechaPosteo(String ov19FechaPosteo) {
        this.mov19FechaPosteo = ov19FechaPosteo;
    }

    @XmlElement(name = "Mov19Operacion")
    public String getov19Operacion() {
        return mov19Operacion;
    }

    public void setov19Operacion(String ov19Operacion) {
        this.mov19Operacion = ov19Operacion;
    }

    @XmlElement(name = "Mov19NumCuenta")
    public String getov19NumCuenta() {
        return mov19NumCuenta;
    }

    public void setov19NumCuenta(String ov19NumCuenta) {
        this.mov19NumCuenta = ov19NumCuenta;
    }

    @XmlElement(name = "Mov19NomComercio")
    public String getov19NomComercio() {
        return mov19NomComercio;
    }

    public void setov19NomComercio(String ov19NomComercio) {
        this.mov19NomComercio = ov19NomComercio;
    }

    @XmlElement(name = "Mov19Monto")
    public String getov19Monto() {
        return mov19Monto;
    }

    public void setov19Monto(String ov19Monto) {
        this.mov19Monto = ov19Monto;
    }

    @XmlElement(name = "Mov19Autorizacion")
    public String getov19Autorizacion() {
        return mov19Autorizacion;
    }

    public void setov19Autorizacion(String ov19Autorizacion) {
        this.mov19Autorizacion = ov19Autorizacion;
    }

    @XmlElement(name = "Mov19Estado")
    public String getov19Estado() {
        return mov19Estado;
    }

    public void setov19Estado(String ov19Estado) {
        this.mov19Estado = ov19Estado;
    }

    // Movimiento 20  
    @XmlElement(name = "Mov20FechaOpe")
    public String getov20FechaOpe() {
        return mov20FechaOpe;
    }

    public void setov20FechaOpe(String ov20FechaOpe) {
        this.mov20FechaOpe = ov20FechaOpe;
    }

    @XmlElement(name = "Mov20FechaPosteo")
    public String getov20FechaPosteo() {
        return mov20FechaPosteo;
    }

    public void setov20FechaPosteo(String ov20FechaPosteo) {
        this.mov20FechaPosteo = ov20FechaPosteo;
    }

    @XmlElement(name = "Mov20Operacion")
    public String getov20Operacion() {
        return mov20Operacion;
    }

    public void setov20Operacion(String ov20Operacion) {
        this.mov20Operacion = ov20Operacion;
    }

    @XmlElement(name = "Mov20NumCuenta")
    public String getov20NumCuenta() {
        return mov20NumCuenta;
    }

    public void setov20NumCuenta(String ov20NumCuenta) {
        this.mov20NumCuenta = ov20NumCuenta;
    }

    @XmlElement(name = "Mov20NomComercio")
    public String getov20NomComercio() {
        return mov20NomComercio;
    }

    public void setov20NomComercio(String ov20NomComercio) {
        this.mov20NomComercio = ov20NomComercio;
    }

    @XmlElement(name = "Mov20Monto")
    public String getov20Monto() {
        return mov20Monto;
    }

    public void setov20Monto(String ov20Monto) {
        this.mov20Monto = ov20Monto;
    }

    @XmlElement(name = "Mov20Autorizacion")
    public String getov20Autorizacion() {
        return mov20Autorizacion;
    }

    public void setov20Autorizacion(String ov20Autorizacion) {
        this.mov20Autorizacion = ov20Autorizacion;
    }

    @XmlElement(name = "Mov20Estado")
    public String getov20Estado() {
        return mov20Estado;
    }

    public void setov20Estado(String ov20Estado) {
        this.mov20Estado = ov20Estado;
    }
    
    /*public static void main(String[] args) {
    	for (int i = 2; i <= 20; i++) {
            String movNum = "ov" + i;
            String code = String.format(
                    "// Movimiento %2$d  \n" +
                "@XmlElement(name = \"M%1$sFechaOpe\")\n" +
                "public String get%1$sFechaOpe() {\n" +
                "    return m%1$sFechaOpe;\n" +
                "}\n\n" +
                "public void set%1$sFechaOpe(String %1$sFechaOpe) {\n" +
                "    this.m%1$sFechaOpe = %1$sFechaOpe;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sFechaPosteo\")\n" +
                "public String get%1$sFechaPosteo() {\n" +
                "    return m%1$sFechaPosteo;\n" +
                "}\n\n" +
                "public void set%1$sFechaPosteo(String %1$sFechaPosteo) {\n" +
                "    this.m%1$sFechaPosteo = %1$sFechaPosteo;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sOperacion\")\n" +
                "public String get%1$sOperacion() {\n" +
                "    return m%1$sOperacion;\n" +
                "}\n\n" +
                "public void set%1$sOperacion(String %1$sOperacion) {\n" +
                "    this.m%1$sOperacion = %1$sOperacion;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sNumCuenta\")\n" +
                "public String get%1$sNumCuenta() {\n" +
                "    return m%1$sNumCuenta;\n" +
                "}\n\n" +
                "public void set%1$sNumCuenta(String %1$sNumCuenta) {\n" +
                "    this.m%1$sNumCuenta = %1$sNumCuenta;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sNomComercio\")\n" +
                "public String get%1$sNomComercio() {\n" +
                "    return m%1$sNomComercio;\n" +
                "}\n\n" +
                "public void set%1$sNomComercio(String %1$sNomComercio) {\n" +
                "    this.m%1$sNomComercio = %1$sNomComercio;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sMonto\")\n" +
                "public String get%1$sMonto() {\n" +
                "    return m%1$sMonto;\n" +
                "}\n\n" +
                "public void set%1$sMonto(String %1$sMonto) {\n" +
                "    this.m%1$sMonto = %1$sMonto;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sAutorizacion\")\n" +
                "public String get%1$sAutorizacion() {\n" +
                "    return m%1$sAutorizacion;\n" +
                "}\n\n" +
                "public void set%1$sAutorizacion(String %1$sAutorizacion) {\n" +
                "    this.m%1$sAutorizacion = %1$sAutorizacion;\n" +
                "}\n\n" +
                "@XmlElement(name = \"M%1$sEstado\")\n" +
                "public String get%1$sEstado() {\n" +
                "    return m%1$sEstado;\n" +
                "}\n\n" +
                "public void set%1$sEstado(String %1$sEstado) {\n" +
                "    this.m%1$sEstado = %1$sEstado;\n" +
                "}\n",movNum,i );

            System.out.println(code);
        }	
	}*/
    
    
    
}
