package com.luxoft.library.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.AuthorProto;
import com.luxoft.library.AuthorServiceGrpc;
import com.luxoft.library.entities.Author;
import com.luxoft.library.services.AuthorService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Optional;


@Slf4j
@GRpcService
public class GrpcAuthorService extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorService service;

    public GrpcAuthorService(AuthorService service) {
        this.service = service;
    }

    @Override
    public void findAll(Empty request, StreamObserver<AuthorProto> responseObserver) {
        service.findAll().forEach(e -> {
            responseObserver.onNext(e.toProto());
        });
        responseObserver.onCompleted();
    }

    @Override
    public void findById(Int64Value request, StreamObserver<AuthorProto> responseObserver) {
        Optional<Author> entry = service.findById(request.getValue());
        entry.map(e -> e.toProto())
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(AuthorProto request, StreamObserver<Int64Value> responseObserver) {
        Author entry = Author.fromProto(request);
        long id = service.save(entry);
        responseObserver.onNext(Int64Value.newBuilder()
                .setValue(id)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Int64Value request, StreamObserver<Int64Value> responseObserver) {
        long id = service.delete(request.getValue());
        responseObserver.onNext(Int64Value.newBuilder()
                .setValue(id)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void create(Empty request, StreamObserver<AuthorProto> responseObserver) {
        Optional<Author> entry = service.create();
        entry.map(e -> e.toProto())
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }


}
