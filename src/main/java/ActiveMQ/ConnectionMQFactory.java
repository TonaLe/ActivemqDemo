package ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConnectionMQFactory {

    private ConnectionFactory getConnectionFactory() {
        return new ActiveMQConnectionFactory( "tcp://localhost:61616");
    }

    private static QueueConnectionFactory getQueueConnectionFactory() {
        return new ActiveMQConnectionFactory( "tcp://localhost:61616");
    }

    private static TopicConnectionFactory getTopicConnectionFactory() {
        return new ActiveMQConnectionFactory( "tcp://localhost:61616");
    }

    /**
     * Create connection connection.
     *
     * @param connectionFactory the connection factory
     * @return the connection
     * @throws JMSException the jms exception
     */
    public Connection createConnection(final ConnectionFactory connectionFactory) throws JMSException {
        return connectionFactory.createConnection();
    }

    /**
     * Create queue connection queue connection.
     *
     * @param queueConnectionFactory the queue connection factory
     * @return the queue connection
     * @throws JMSException the jms exception
     */
    public static QueueConnection createQueueConnection(final QueueConnectionFactory queueConnectionFactory) throws JMSException {
        return queueConnectionFactory.createQueueConnection();
    }

    /**
     * Create topic connection topic connection.
     *
     * @param topicConnectionFactory the topic connection factory
     * @return the topic connection
     * @throws JMSException the jms exception
     */
    private static TopicConnection createTopicConnection(final TopicConnectionFactory topicConnectionFactory) throws JMSException {
        return topicConnectionFactory.createTopicConnection();
    }

    /**
     * Create session session.
     *
     * @param connection the connection
     * @return the session
     * @throws JMSException the jms exception
     */
    public Session createSession(final Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static QueueSession createQueueSession() throws JMSException {
        QueueConnection queueConnection = createQueueConnection(getQueueConnectionFactory());
        queueConnection.start();
        return queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static TopicSession createTopicSession() throws JMSException {
        TopicConnection topicConnection = createTopicConnection(getTopicConnectionFactory());
        topicConnection.start();
        return topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}
