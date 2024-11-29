To implement message broadcasting across servers using Redis Pub/Sub in a Star Topology, you will need to complete a few prerequisites. Here's a breakdown of the setup steps:

# RedisPublisher

<img width="741" alt="Screenshot 2024-11-29 at 6 38 21 PM" src="https://github.com/user-attachments/assets/26de3df9-916c-4942-8ca8-0ea8f8f88b34">

# RedisSubscriber

<img width="733" alt="Screenshot 2024-11-29 at 6 38 32 PM" src="https://github.com/user-attachments/assets/33b895e9-14d4-4e99-ac53-c08fca41243c">

### Prerequisites

#### 1. **Install Redis**
Redis will act as the central message broker in this scenario, handling message distribution between the publisher and subscribers.

- **Install Redis on macOS:**
  1. **Using Homebrew**: Run the following command to install Redis:
     ```bash
     brew install redis
     ```
  2. **Start Redis**:
     ```bash
     brew services start redis
     ```
  3. **Verify Redis is running**:
     You can check the Redis server status by running:
     ```bash
     redis-cli ping
     ```
     If it responds with `PONG`, then Redis is successfully running.

     To implement message broadcasting across servers using **Redis Pub/Sub** with a **Star Topology**, you can follow these steps. The **Star Topology** involves one central server (the "hub") that publishes messages, and several other servers (the "spokes") that subscribe to receive those messages.

In this setup:
- The **Publisher** will be a Redis client that sends messages to a specific Redis **channel**.
- The **Subscribers** will listen to this channel and handle the received messages.

### Prerequisites

1. Install **Redis** on the machines (servers) that will communicate.
2. Use **Jedis** or **Lettuce** for Redis integration in Java. In this case, I'll demonstrate using **Jedis**.

### Step 1: Set Up Your Project with Maven

You need to include the **Jedis** library in your `pom.xml` file for Redis integration.

### Step 2: Publish a Message (Central Server)

The **Publisher** will send messages to a Redis channel. This server acts as the **hub** in the Star Topology.

### Step 3: Subscribe to the Channel (Spokes)

The **Subscribers** will listen to the Redis channel for any messages. This server acts as a **spoke** in the Star Topology.

#### Example Subscriber:


### Step 4: Run the Application

1. **Start the Redis Server**: Ensure that Redis is running on the default port (`6379`). You can start Redis by running the following command in the terminal:
   ```bash
   redis-server
   ```

2. **Run the Subscriber(s)**: Start one or more instances of the **RedisSubscriber** class (spokes) on different machines or processes to simulate the servers that will receive messages.

   Each **RedisSubscriber** instance will connect to the Redis server and subscribe to the `message_channel` to receive messages.

3. **Run the Publisher**: Start the **RedisPublisher** class (hub) to send a message to the channel. The publisher can be run from any machine that can connect to Redis.

### Step 5: Testing

Once everything is set up:
1. When the **RedisPublisher** sends a message, the **RedisSubscriber(s)** (spokes) will automatically receive the message.
2. If you run multiple subscribers on different servers, all of them will receive the message simultaneously, effectively broadcasting it.

### Optional: Handle Multiple Publishers and Subscribers

If you have multiple publishers and subscribers, Redis Pub/Sub can handle it efficiently. You can run as many publishers and subscribers as you want, and Redis will ensure that messages are delivered to all subscribers connected to the relevant channels.

### Step 6: Scaling and Load Balancing

For scaling the system:
- **Multiple Redis instances** can be used in a distributed fashion (with Redis Sentinel or Redis Cluster) to handle higher loads.
- **Redis Channels** can be scaled horizontally by running multiple Redis servers and ensuring the channels are shared across all of them.

### Summary

This setup uses **Redis Pub/Sub** in a **Star Topology**, where:
- A **central server (hub)** publishes messages.
- Multiple **servers (spokes)** subscribe to the Redis channel and receive the messages.

Redis efficiently handles message broadcasting across multiple servers in real-time using the Pub/Sub mechanism.
