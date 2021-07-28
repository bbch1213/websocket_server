package com.example.websocket_server.chatRoom.mapper;

import com.example.websocket_server.chatRoom.entity.ChatRoom;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface ChatRoomMapper {

    ChatRoomMapper mapper = Mappers.getMapper(ChatRoomMapper.class);

    @Mappings({
            @Mapping(target="chatId", ignore = true),
            @Mapping(target = "title", ignore = true),
            @Mapping(target = "userId", ignore = true)
    })
    ChatRoom modify(ChatRoom source, @MappingTarget ChatRoom target);

}
