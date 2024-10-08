//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package pe.bn.com.sate.ope.infrastructure.service.external.domain.reniec;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ServiceReniec2Service", targetNamespace = "http://interfaz.mq2.bn", wsdlLocation = "WEB-INF/wsdl/ServiceReniec2.wsdl")
public class ServiceReniec2Service
    extends Service
{

    private final static URL SERVICERENIEC2SERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICERENIEC2SERVICE_EXCEPTION;
    private final static QName SERVICERENIEC2SERVICE_QNAME = new QName("http://interfaz.mq2.bn", "ServiceReniec2Service");

    static {
            SERVICERENIEC2SERVICE_WSDL_LOCATION = pe.bn.com.sate.ope.infrastructure.service.external.domain.reniec.ServiceReniec2Service.class.getResource("/WEB-INF/wsdl/ServiceReniec2.wsdl");
        WebServiceException e = null;
        if (SERVICERENIEC2SERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/ServiceReniec2.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        SERVICERENIEC2SERVICE_EXCEPTION = e;
    }

    public ServiceReniec2Service() {
        super(__getWsdlLocation(), SERVICERENIEC2SERVICE_QNAME);
    }

    public ServiceReniec2Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICERENIEC2SERVICE_QNAME, features);
    }

    public ServiceReniec2Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICERENIEC2SERVICE_QNAME);
    }

    public ServiceReniec2Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICERENIEC2SERVICE_QNAME, features);
    }

    public ServiceReniec2Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceReniec2Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServiceReniec2
     */
    @WebEndpoint(name = "ServiceReniec2")
    public ServiceReniec2 getServiceReniec2() {
        return super.getPort(new QName("http://interfaz.mq2.bn", "ServiceReniec2"), ServiceReniec2.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceReniec2
     */
    @WebEndpoint(name = "ServiceReniec2")
    public ServiceReniec2 getServiceReniec2(WebServiceFeature... features) {
        return super.getPort(new QName("http://interfaz.mq2.bn", "ServiceReniec2"), ServiceReniec2.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICERENIEC2SERVICE_EXCEPTION!= null) {
            throw SERVICERENIEC2SERVICE_EXCEPTION;
        }
        return SERVICERENIEC2SERVICE_WSDL_LOCATION;
    }

}
