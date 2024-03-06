package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.MediaTicketDto;
import ru.preachooda.bokunohero.dto.enumeration.TicketMediaType;
import ru.preachooda.bokunohero.entity.MediaFile;
import ru.preachooda.bokunohero.entity.TicketMediaFile;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;
import ru.preachooda.bokunohero.services.MediaFileService;
import ru.preachooda.bokunohero.services.TicketMediaFileService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/download-media")
public class DownloadMediaController {

    @Autowired
    private MediaFileService mediaFileService;

    @Autowired
    private TicketMediaFileService ticketMediaFileService;

    @GetMapping(value = "/ticket/{id}")
    public ResponseEntity<MediaTicketDto> getMediaByTicket(@PathVariable("id") String id) {
        MediaTicketDto mediaTicketDto = new MediaTicketDto();
        // Ищем и устанавливаем медиа-файлы
        List<TicketMediaFile> ticketMediaFileList = ticketMediaFileService.findByTicketId(Long.valueOf(id));
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
                    mediaTicketDto.getPhotosCodes().add(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }

                if (ticketMediaFile.getMediaType().equals(TicketMediaType.VIDEO)) {
                    mediaTicketDto.setVideoCode(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }

                if (ticketMediaFile.getMediaType().equals(TicketMediaType.AUDIO)) {
                    mediaTicketDto.setAudioCode(mediaFileIdToValueMap.get(ticketMediaFile.getTicketMediaKey().getMediaFile()));
                }
            }
        }

        return ResponseEntity.ok().body(mediaTicketDto);
    }

}