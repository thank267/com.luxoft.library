package com.luxoft.library.utils;

import org.modelmapper.ModelMapper;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ObjectMapperUtils {
    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
    }

    private ObjectMapperUtils() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Iterator<T> entityList, Class<D> outCLass) {
        return StreamSupport.stream(toIterable(entityList).spliterator(),false).map(entity ->
            map(entity, outCLass)).collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    private static <T> Iterable<T> toIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }
}
