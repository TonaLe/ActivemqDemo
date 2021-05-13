package ActiveMQ.QueueConnection;

import ActiveMQ.Constraint.Constraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class ConsumerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerFactory.class);

    public static QueueReceiver getMessageConsumer(final QueueSession queueSession) throws JMSException {
        LOGGER.info("Get Message Consumer:    \n");
//        Destination destination = queueSession.createQueue(Constraint.DESTINATION);
        Queue queue = queueSession.createQueue(Constraint.DESTINATION);
        return queueSession.createReceiver(queue);
    }
}
