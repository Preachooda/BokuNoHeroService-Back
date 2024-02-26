package ru.preachooda.bokunohero.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.dto.enumeration.TicketMediaType;
import ru.preachooda.bokunohero.entity.MediaFile;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.TicketMediaFile;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;
import ru.preachooda.bokunohero.services.MediaFileService;
import ru.preachooda.bokunohero.services.TicketMediaFileService;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class TicketMapper extends BaseEntityMapper<Ticket, TicketDto> {

    @Autowired
    private MediaFileService mediaFileService;

    @Autowired
    private TicketMediaFileService ticketMediaFileService;

    @AfterMapping
    public void ticketToTicketDto(Ticket ticket, @MappingTarget TicketDto ticketDto) {
        ticketDto.setUserId(ticket.getUser().getId());

        // Устанвливаем медиа-файлы
        List<TicketMediaFile> ticketMediaFileList = ticketMediaFileService.findByTicketId(ticket.getId());
        if (!CollectionUtils.isEmpty(ticketMediaFileList)) {
            Map<Long, String> mediaFileIdToValueMap =
                    mediaFileService.findAllById(
                            ticketMediaFileList
                                    .stream()
                                    .map(TicketMediaFile::getTicketMediaKey)
                                    .map(TicketMediaKey::getMediaFile)
                                    .collect(Collectors.toList())
                    ).stream().collect(Collectors.toMap(MediaFile::getId, MediaFile::getData));

            for (TicketMediaFile ticketMediaFile : ticketMediaFileList) {
                if (ticketMediaFile.getMediaType().equals(TicketMediaType.IMAGE)) {
                    ticketDto.getPhotosCodes().add(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }

                if (ticketMediaFile.getMediaType().equals(TicketMediaType.VIDEO)) {
                    ticketDto.setVideoCode(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }

                if (ticketMediaFile.getMediaType().equals(TicketMediaType.AUDIO)) {
                    ticketDto.setAudioCode(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }
            }
        }
    }

    @AfterMapping
    public void ticketDtoToTicket(TicketDto ticketDto, @MappingTarget Ticket ticket) {
        // Устанавливаем юзера
        User user = new User();
        user.setId(ticketDto.getUserId());
        ticket.setUser(user);
    }

}
