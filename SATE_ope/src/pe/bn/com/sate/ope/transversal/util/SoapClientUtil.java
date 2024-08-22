package pe.bn.com.sate.ope.transversal.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pe.bn.com.sate.ope.transversal.util.excepciones.InternalExcepcion;

public class SoapClientUtil {
	 /**
     * Parsea una cadena XML y la convierte en un objeto Document.
     *
     * Este método toma una cadena XML como entrada, la convierte en un flujo de 
     * bytes utilizando codificación UTF-8, y luego la parsea en un documento DOM. 
     * El documento se normaliza para asegurar un formato consistente.
     *
     * @param xml la cadena XML a parsear
     * @return un objeto Document que representa el XML parseado
     * @throws InternalExcepcion si ocurre un error al intentar parsear el XML
     */
    public static Document parseXmlResponse(String xml) throws InternalExcepcion {
        try {
            // Convierte la cadena XML a un flujo de bytes usando UTF-8
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));

            // Configura y crea un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsea el XML y normaliza el documento
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            return document;

        } catch (SAXException | IOException | ParserConfigurationException e) {
            // Lanza una excepción personalizada en caso de error
            throw new InternalExcepcion("Error al parsear el XML: " + e.getMessage(), e);
        }
    }


    /**
     * Obtiene el contenido de texto de un elemento XML específico.
     *
     * Este método busca un elemento en el documento XML por su nombre de etiqueta 
     * y retorna el contenido de texto del primer elemento encontrado.
     *
     * @param documentoXML el documento XML en el que se buscará el elemento
     * @param tagName el nombre de la etiqueta a buscar
     * @return el contenido de texto del elemento si se encuentra
     * @throws InternalException si no se encuentra el elemento o si no contiene texto
     */
    public static String getTextFromElement(Document documentoXML, String tagName) throws InternalExcepcion {
        // Obtener la lista de elementos con el nombre de la etiqueta
        NodeList nodeList = documentoXML.getElementsByTagName(tagName);

        if (nodeList != null && nodeList.getLength() > 0) {
            // Obtener el primer elemento de la lista (suponiendo que solo hay uno)
            Node node = nodeList.item(0);

            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                // Retornar el contenido de texto si se encuentra
                return node.getTextContent();
            }
        }
        // Si no se encuentra la etiqueta, lanzar una excepción personalizada
        throw new InternalExcepcion("No se encontró el elemento con la etiqueta: " + tagName);
    }


}
