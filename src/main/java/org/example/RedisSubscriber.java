package org.example;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber {

    public static void main(String[] args) {
        // Redis server connection details
        String redisHost = "localhost";
        int redisPort = 6379;

        // Create Jedis client for Redis
        try (Jedis jedis = new Jedis(redisHost, redisPort)) {
            String channel = "message_channel"; // Redis channel name

            // Create a subscriber to listen to messages on the channel
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    // This method is called whenever a message is received
                    System.out.println("Received message: " + message);
                }
            };

            // Subscribe to the channel
            System.out.println("Subscribing to channel: " + channel);
            jedis.subscribe(jedisPubSub, channel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
