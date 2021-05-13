package ActiveMQ;

import javax.jms.JMSException;
import javax.jms.QueueSession;
import javax.jms.TopicSession;

public class ProducerApp {

    private static ConnectionService connectionService = new ConnectionService();

    public static void main(String[] args) throws JMSException {
//        Sending message to queue

/*
        QueueSession queueSession = ConnectionMQFactory.createQueueSession();
        connectionService.sendTextMessageToQueue(queueSession);
 */
//        Sending message to topic
        TopicSession topicSession = ConnectionMQFactory.createTopicSession();
        connectionService.sendMessageToTopic(topicSession);
    }
}
