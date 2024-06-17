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
 * ReciverB class receives messages from a RabbitMQ queue
 * "SpaceGame" is name of the RabbitMQ queue from which messages will be received.
 * Create a connection, set the RabbitMQ server host
 * Create a channel
 * Declare a queue named
 * Callback for messages deilvery
 * print received then the message
 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReciverB {
        private final static String QUEUE_NAME = "game_queue";

        public static void main(String[] argv) throws Exception {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String encryptedObject = new String(delivery.getBody(), "UTF-8");
                    String decryptedObject = CaesarCipher.encrypt(encryptedObject, -5);

                    System.out.println("Received Encrypted Object: " + encryptedObject);
                    System.out.println("Decrypted Object: " + decryptedObject);

                    GameObject gameObject = deserializeGameObject(decryptedObject);
                    System.out.println("Game Object: " + gameObject);
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            }
        }

        private static GameObject deserializeGameObject(String data) {
            // Deserialize method to convert string back to game object
            String[] parts = data.split(",");
            String name = parts[0].split("=")[1];
            int score = Integer.parseInt(parts[1].split("=")[1]);
            int health = Integer.parseInt(parts[2].split("=")[1]);
            return new GameObject(name, score, health);
        }
    }