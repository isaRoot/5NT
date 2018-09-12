import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//эмулятор 5NT
public class Main {

    public static void main(String[] args) throws JMSException {
        CommonData commonData = new CommonData();
        commonData.readConfig();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        //ActiveMQConnection.DEFAULT_BROKER_URL;
        //commonData.urlConnection
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destinationOut = session.createQueue(commonData.getOutQueue());
        MessageConsumer consumer = session.createConsumer(destinationOut);

        Destination destinationIn = session.createQueue(commonData.getInputQueue());
        MessageProducer producer = session.createProducer(destinationIn);

        Listener listener = new Listener(consumer, producer, session);
        consumer.setMessageListener(listener);
        connection.start();

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
