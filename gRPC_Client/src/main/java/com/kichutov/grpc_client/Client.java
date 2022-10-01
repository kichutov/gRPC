package com.kichutov.grpc_client;

import com.kichutov.grpc_server.GreetingServiceGrpc;
import com.kichutov.grpc_server.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Vlad")
                .build();

        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request);

        System.out.println(response);

        channel.shutdownNow();
    }
}
