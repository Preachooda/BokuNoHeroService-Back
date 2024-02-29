package ru.preachooda.bokunohero.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.preachooda.bokunohero.dto.enumeration.TicketMediaType;
import ru.preachooda.bokunohero.entity.MediaFile;
import ru.preachooda.bokunohero.entity.TicketMediaFile;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;
import ru.preachooda.bokunohero.services.MediaFileService;
import ru.preachooda.bokunohero.services.TicketMediaFileService;
import ru.preachooda.bokunoherocore.utils.BaseUtils;
import ru.preachooda.bokunoherocore.validator.exceptions.NotFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/upload-media")
public class UploadMediaController {

    @Autowired
    private MediaFileService mediaFileService;

    @Autowired
    private TicketMediaFileService ticketMediaFileService;

    @GetMapping("/all")
    public List<MediaFile> findAll() {
        return mediaFileService.findAll();
    }

    @PostMapping("/ticket/{id}")
    public void handleFilesUpload(@PathVariable("id") String id, @RequestParam("files") MultipartFile[] files) {
        Long idValue = BaseUtils.convertToLong(id);
        if (idValue == null) {
            throw new NotFoundException("Параметр '" + id + "' передан неверно");
        }

        for (MultipartFile file : Arrays.stream(files).toList()) {
            try {
                if (file.isEmpty()) {
                    throw new RuntimeException("Передан пустой файл для сохранения");
                }
                // Сохраняем файл в БД
                MediaFile mediaFile = new MediaFile();
                mediaFile.setType(file.getContentType());
                mediaFile.setName(file.getName());
                mediaFile.setData(Base64.encodeBase64String(file.getBytes()));
                mediaFile = mediaFileService.create(mediaFile);

                // Сохраняем привязку файла к тикету
                TicketMediaFile ticketMediaFile = new TicketMediaFile();
                TicketMediaKey ticketMediaKey = new TicketMediaKey();
                ticketMediaKey.setTicketId(idValue);
                ticketMediaKey.setMediaFile(mediaFile.getId());
                ticketMediaFile.setTicketMediaKey(ticketMediaKey);
                // TODO: 25.02.2024 Универсально сохранять
                if (mediaFile.getType().contains("video")) {
                    ticketMediaFile.setMediaType(TicketMediaType.VIDEO);
                } else if (mediaFile.getType().contains("audio")) {
                    ticketMediaFile.setMediaType(TicketMediaType.AUDIO);
                } else if (mediaFile.getType().contains("image")) {
                    ticketMediaFile.setMediaType(TicketMediaType.IMAGE);
                }
                ticketMediaFileService.create(ticketMediaFile);

//                Path destinationFile = Paths.get("upload-dir").resolve(
//                                Paths.get(file.getOriginalFilename()))
//                        .normalize().toAbsolutePath();
//            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//                // This is a security check
//                throw new StorageException(
//                        "Cannot store file outside current directory.");
//            }
/*
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);
                }
*/
            } catch (IOException e) {
                throw new RuntimeException("Ошибка сохранения файла - ", e);
            }
        }
    }


}
