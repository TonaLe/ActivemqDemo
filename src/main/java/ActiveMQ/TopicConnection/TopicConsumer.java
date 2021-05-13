package ActiveMQ.TopicConnection;

import ActiveMQ.ConnectionMQFactory;
import ActiveMQ.ConnectionService;

import javax.jms.JMSException;
import javax.jms.TopicSession;

public class TopicConsumer {

    private static ConnectionService connectionService = new ConnectionService();

    public static void main(String[] args) throws JMSException {
        TopicSession topicSession = ConnectionMQFactory.createTopicSession();
        connectionService.consumeTopicMessage(topicSession);
    }
}
