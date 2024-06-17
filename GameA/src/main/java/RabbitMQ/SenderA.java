/** Project: Systems Integration Space Game: File Files, RabbitMQ, and Web Service/JSON
 * Purpose Details: use RabbitMQ and Web Services for sending and receiving game object data between applications GameA and GameB
 * Course:IST242
 * Author:Christina Yang
 * Date Developed:Jun 8 2024
 * Last Date Changed: Jun 11 2024
 * Rev:4

 */
package RabbitMQ;


/**
 * RabbitMQ server will declares a queue, and sends a message to that queue.
 * The SenderA send a message to the RabbitMQ queue named "SpaceGame".
 * Create a connection and set server host
 * Finds the queue named "SpaceGame"
 * message is sent to the queue
 * Print the confirmation message
 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderA {
    private final static String QUEUE_NAME = "game_queue";

    public static void main(String[] argv) throws Exception {
        GameObject gameObject = new GameObject("SpaceGame", 5, 1000);
        String serializedObject = serializeGameObject(gameObject);
        String encryptedObject = CaesarCipher.encrypt(serializedObject, 5);

        System.out.println("Original Object: " + serializedObject);
        System.out.println("Encrypted Object: " + encryptedObject);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, encryptedObject.getBytes());
            System.out.println(" [x] Sent '" + encryptedObject + "'");
        }
    }

    private static String serializeGameObject(GameObject gameObject) {
        // Use any serialization method, e.g., JSON
        return "name=" + gameObject.getName() + ",score=" + gameObject.getScore() + ",health=" + gameObject.getHealth();
    }
}


