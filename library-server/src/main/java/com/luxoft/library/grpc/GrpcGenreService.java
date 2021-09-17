package com.luxoft.library.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.GenreProto;
import com.luxoft.library.GenreServiceGrpc;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.services.GenreService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Optional;


@Slf4j
@GRpcService
public class GrpcGenreService extends GenreServiceGrpc.GenreServiceImplBase {

    private final GenreService service;

    public GrpcGenreService(GenreService service) {
        this.service = service;
    }

    @Override
    public void findAll(Empty request, StreamObserver<GenreProto> responseObserver) {
        service.findAll().forEach(e -> {
            responseObserver.onNext(e.toProto());
        });
        responseObserver.onCompleted();
    }

    @Override
    public void findById(Int64Value request, StreamObserver<GenreProto> responseObserver) {
        Optional<Genre> entry = service.findById(request.getValue());
        entry.map(e -> e.toProto())
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(GenreProto request, StreamObserver<Int64Value> responseObserver) {
        Genre entry = Genre.fromProto(request);
        long id = service.save(entry);
        responseObserver.onNext(Int64Value.newBuilder()
                .setValue(id)
                .build());
        responseObserver.onCompleted();
    }


}
