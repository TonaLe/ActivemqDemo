package ActiveMQ.TopicConnection;

import ActiveMQ.Constraint.Constraint;

import javax.jms.*;

public class TopicConsumerFactory {

    public static TopicSubscriber createTopicSession(final TopicSession topicSession) throws JMSException {
        Topic topic = topicSession.createTopic(Constraint.DESTINATION);
        return topicSession.createSubscriber(topic);
    }
}
