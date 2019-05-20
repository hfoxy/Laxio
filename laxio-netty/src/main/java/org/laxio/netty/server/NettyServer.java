package org.laxio.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.laxio.api.client.Client;
import org.laxio.api.protocol.connection.server.NetworkServer;
import org.laxio.api.server.ManagedServer;
import org.laxio.netty.server.pipeline.NettyServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

public class NettyServer extends Thread implements NetworkServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private final ManagedServer managedServer;
    private final InetSocketAddress address;

    private final Set<Client> clients;

    private EventLoopGroup boss;
    private EventLoopGroup worker;

    public NettyServer(ManagedServer managedServer) {
        this.managedServer = managedServer;
        this.address = new InetSocketAddress(managedServer.getOptions().getHost(), managedServer.getOptions().getPort());
        this.clients = new HashSet<>();
    }

    @Override
    public ManagedServer getManagedServer() {
        return managedServer;
    }

    @Override
    public Set<Client> getClients() {
        return clients;
    }

    @Override
    public void run() {
        synchronized (address) {
            try {
                ThreadFactory bossFactory = new DefaultThreadFactory("BOSS");
                ThreadFactory workerFactory = new DefaultThreadFactory("WORKER");

                boss = new NioEventLoopGroup(0, bossFactory);
                worker = new NioEventLoopGroup(0, workerFactory);
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(boss, worker)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new NettyServerChannelInitializer(this));

                bootstrap.bind(address).sync();
            } catch (Exception ex) {
                LOGGER.error("Start Netty server", ex);
            }
        }
    }

}
