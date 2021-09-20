package com.luxoft.library.configs;

import com.luxoft.library.AuthorServiceGrpc;
import com.luxoft.library.BookServiceGrpc;
import com.luxoft.library.GenreServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Value("${library.server.target}")
    private String libraryServerEndpoint;

    @Bean
    Channel channel() {
        return ManagedChannelBuilder.forTarget(libraryServerEndpoint)
                .usePlaintext()
                .build();
    }

    @Bean
    GenreServiceGrpc.GenreServiceBlockingStub genreServiceBlockingStub(Channel channel) {
        return GenreServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub(Channel channel) {
        return AuthorServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub(Channel channel) {
        return BookServiceGrpc.newBlockingStub(channel);
    }
}
