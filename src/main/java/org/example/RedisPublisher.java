package org.example;
import redis.clients.jedis.Jedis;

public class RedisPublisher {

    public static void main(String[] args) {
        // Redis server connection details
        String redisHost = "localhost";
        int redisPort = 6379;

        // Create Jedis client for Redis
        try (Jedis jedis = new Jedis(redisHost, redisPort)) {
            String channel = "message_channel"; // Redis channel name

            // Publish a message
            jedis.publish(channel, "Hello, Subscribers! This is a broadcast message.");

            System.out.println("Message published to channel: " + channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
