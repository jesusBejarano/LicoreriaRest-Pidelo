package upc.edu.pe.tools;

import java.io.Serializable;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Miguel Cardoso
 */
public class PadreType implements Serializable {
    
    public static String cambiarFormatoFecha(String fecha) {
        if (fecha != null && !fecha.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(fecha);
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return sdf.format(date);
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public static String convertirDateToStringLong(Date datoDate) {
        if (datoDate == null) {
            return "";
        } else {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaejecucion = formatter.format(datoDate);

            return fechaejecucion;
        }
    }
    
    public void enviarPedidoaCola(String json) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            connectionFactory.setObjectMessageSerializationDefered(true);
            //Creacion de Conexion
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Creacion de Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Creacion de Cola
            Destination destination = session.createQueue("LICORERIA");
            // Creacion de mesaje para la session de la cola y de tipo persistente
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            // Creacion de mensaje
            TextMessage message = session.createTextMessage(json);
            producer.send(message);
            session.close();
            connection.close();
            System.out.println("MENSAJE EN COLA ENTREGADO");
        } catch (JMSException ex) {
            System.out.println("Error Enviar a Cola : " + ex.getMessage());
        }
    }

}
