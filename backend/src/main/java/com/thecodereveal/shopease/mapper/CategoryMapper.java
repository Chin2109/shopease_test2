package com.thecodereveal.shopease.mapper;

import com.thecodereveal.shopease.dto.CategoryTypeDto;
import com.thecodereveal.shopease.entities.CategoryType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    // Convert Entity → DTO
    public CategoryTypeDto toDto(CategoryType entity) {
        if (entity == null) return null;
        CategoryTypeDto dto = new CategoryTypeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    // Convert DTO → Entity
    public CategoryType toEntity(CategoryTypeDto dto) {
        if (dto == null) return null;
        CategoryType entity = new CategoryType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // Convert List<Entity> → List<DTO>
    public List<CategoryTypeDto> toDtoList(List<CategoryType> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Convert List<DTO> → List<Entity>
    public List<CategoryType> toEntityList(List<CategoryTypeDto> dtos) {
        if (dtos == null) return null;
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
