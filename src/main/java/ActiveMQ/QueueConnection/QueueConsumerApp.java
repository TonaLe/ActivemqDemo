package ActiveMQ.QueueConnection;

import ActiveMQ.ConnectionMQFactory;
import ActiveMQ.ConnectionService;

import javax.jms.JMSException;
import javax.jms.QueueSession;

public class QueueConsumerApp {

    private static ConnectionService connectionService = new ConnectionService();

    public static void main(String[] args) throws JMSException {
        QueueSession queueSession = ConnectionMQFactory.createQueueSession();
        connectionService.receiveMessage(queueSession);
    }
}
