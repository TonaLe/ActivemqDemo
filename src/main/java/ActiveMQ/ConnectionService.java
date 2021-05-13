package ActiveMQ;


import ActiveMQ.Constraint.Constraint;
import ActiveMQ.QueueConnection.ConsumerFactory;
import ActiveMQ.TopicConnection.TopicConsumerFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.Objects;

/**
 * The type Connection service.
 */
public class ConnectionService {

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class);



    public void sendTextMessageToQueue(final QueueSession queueSession) throws JMSException {
        Queue queue = queueSession.createQueue(Constraint.DESTINATION);
        for (int index = 1; index < 10; index++) {
            String message = String.join("My Man ", String.valueOf(index));
            TextMessage textMessage = queueSession.createTextMessage(message);
            QueueSender queueSender = queueSession.createSender(queue);
            try  {
                LOGGER.info("Message send: " + message);
                queueSender.send(textMessage);
            } catch (Exception e) {
                LOGGER.error("Cannot send message because of:   " + e);
                queueSender.close();
            }

        }
    }

    public void receiveMessage(final QueueSession queueSession) throws JMSException {
        QueueReceiver queueReceiver = ConsumerFactory.getMessageConsumer(queueSession);

        while(true) {
            try {
                Message message = queueReceiver.receive(Constraint.MESSAGE_TIMEOUT_MILLISECONDS);
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    LOGGER.info("Message:    " + textMessage.getText());
                }
            } catch (Exception e) {
                LOGGER.error("FAIL: " + e);
                break;
            }
        }
    }

    public void sendMessageToTopic(final TopicSession topicSession) throws JMSException {
        Topic topic = topicSession.createTopic(Constraint.DESTINATION);
        MessageProducer producer = topicSession.createProducer(topic);

        for (int index = 0; index < 10; index++) {
            try {
                String message = String.join(" ", "My topic message:", String.valueOf(index));
                LOGGER.info(message);

                TextMessage txt = topicSession.createTextMessage(message);
                producer.send(txt);
            } catch (Exception e) {
                LOGGER.error(String.join(" ","Write topic message No.",
                        String.valueOf(index), "fail", String.valueOf(e)));
                break;
            }
        }
        producer.close();
    }

    public void consumeTopicMessage(final TopicSession topicSession) throws JMSException {
        TopicSubscriber topicSubscriber = TopicConsumerFactory.createTopicSession(topicSession);

        while(true) {
            Message message = Objects.requireNonNull(topicSubscriber.receive());
            try {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    LOGGER.info(textMessage.getText());
                }
            } catch (Exception e) {
                LOGGER.error(String.join(" ","Consume topic message fail: ", String.valueOf(e)));
                break;
            }
        }
    }
}
