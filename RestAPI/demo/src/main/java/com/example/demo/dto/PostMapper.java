package com.example.demo.dto;

import com.example.demo.entity.*;
import org.mapstruct.Mapper;
// import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE) 
// Sử dụng để map khi só lượng trường dữ liệu trong Dto ít hơn trong Entity,
// Và tên trong Dto khác vs tên trong entity 
@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDto mapPostDto(Post post);
    Post mapDtoToPost(PostDto dto);
}
