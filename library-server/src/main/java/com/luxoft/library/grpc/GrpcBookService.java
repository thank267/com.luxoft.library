package com.luxoft.library.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.BookProto;
import com.luxoft.library.BookServiceGrpc;
import com.luxoft.library.entities.Book;
import com.luxoft.library.services.BookService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Optional;


@Slf4j
@GRpcService
public class GrpcBookService extends BookServiceGrpc.BookServiceImplBase {

    private final BookService service;

    public GrpcBookService(BookService service) {
        this.service = service;
    }

    @Override
    public void findAll(Empty request, StreamObserver<BookProto> responseObserver) {
        service.findAll().forEach(e -> {
            responseObserver.onNext(e.toProto());
        });
        responseObserver.onCompleted();
    }

    @Override
    public void findById(Int64Value request, StreamObserver<BookProto> responseObserver) {
        Optional<Book> entry = service.findById(request.getValue());
        entry.map(e -> e.toProto())
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(BookProto request, StreamObserver<Int64Value> responseObserver) {
        Book entry = Book.fromProto(request);
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
    public void create(Empty request, StreamObserver<BookProto> responseObserver) {
        Optional<Book> entry = service.create();
        entry.map(e -> e.toProto())
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }


}
