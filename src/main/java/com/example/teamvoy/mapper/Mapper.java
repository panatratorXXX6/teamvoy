package com.example.teamvoy.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<ENTITY, DTO> {

    DTO toDto(ENTITY entity, List<String> args);

    ENTITY toEntity(DTO dto, List<String> args);

    default DTO toDto(ENTITY entity) {
        return toDto(entity, new ArrayList<>());
    }

    default ENTITY toEntity(DTO dto) {
        return toEntity(dto, new ArrayList<>());
    }

    default List<DTO> toDto(Collection<ENTITY> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }

        return toDto(entities, Collections.emptyList());
    }

    default List<ENTITY> toEntity(Collection<DTO> dtos) {
        return toEntity(dtos, Collections.emptyList());
    }

    default List<DTO> toDto(Collection<ENTITY> entities, List<String> args) {
        if (entities == null) {
            return new ArrayList<>();
        }

        return entities.stream().map(entity -> toDto(entity, args)).collect(Collectors.toList());
    }

    default List<ENTITY> toEntity(Collection<DTO> dtos, List<String> args) {
        return dtos.stream().map(dto -> toEntity(dto, args)).collect(Collectors.toList());
    }
}