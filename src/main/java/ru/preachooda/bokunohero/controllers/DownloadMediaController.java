package ru.preachooda.bokunohero.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.entity.MediaFile;
import ru.preachooda.bokunohero.services.MediaFileService;
import ru.preachooda.bokunohero.services.TicketMediaFileService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/download-media")
public class DownloadMediaController {

    @Autowired
    private MediaFileService mediaFileService;

    @Autowired
    private TicketMediaFileService ticketMediaFileService;

    @GetMapping(value = "/ticket/{id}")
    public ResponseEntity<List<String>> getMediaByTicket(@PathParam("id") String id) {
        List<MediaFile> mediaFileList = mediaFileService.findAll();

        List<String> resourceList = new ArrayList<>();

        for (MediaFile mediaFile : mediaFileList) {
            resourceList.add(mediaFile.getData());
        }

        return ResponseEntity.ok().body(resourceList);
    }

}