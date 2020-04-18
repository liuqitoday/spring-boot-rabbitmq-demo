### 基本概念
![RabbitMQ](https://ask.qcloudimg.com/http-save/yehe-2744758/w3to0u38xr.png)
#### Message
消息，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）、delivery-mode（指出该消息可能需要持久性存储）等。
#### Exchange
交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
#### Queue
消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。
#### Binding
绑定，用于消息队列和交换器之间的关联。一个绑定就是基于绑定键将交换器和消息队列绑定起来的关系。
#### Publisher
消息的生产者，也是一个向交换器发布消息的客户端应用程序。
### Consumer
消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。
#### Connection
网络连接，比如一个TCP连接。
#### Channel
信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内地虚拟连接，AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。
#### Virtual Host
虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是 / 。
#### Broker
表示消息队列服务器实体。

### 交换器
不同类型的交换器有着不同的消息分发策略，常用的Exchange类型有以下几种：direct（默认）、fanout、topic、headers
此处说明一下RoutingKey、BindingKey。
#### direct
direct是默认的交换器模式，也是最简单的。该模式下，路由键与绑定键一致，路由键与队列名完全匹配。
例如，消息路由键 "liuqitech" 只能匹配 "liuqitech" 绑定，不匹配 "liuqitech.info" 这类绑定。 
#### fanout
fanout不处理路由键，将消息广播给绑定到该交换机的所有队列。 
#### topic
topic处理路由键，按模式匹配路由键。模式符号 "#" 表示一个或多个单词，"*" 仅匹配一个单词。如 "liuqitech.#" 可匹配 "liuqitech.info.message"，但 "liuqitech.*" 只匹配 "liuqitech.info"
