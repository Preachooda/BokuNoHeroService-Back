package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.MediaFile;
import ru.preachooda.bokunohero.repository.MediaFileRepository;

import java.util.List;

@Service
public class MediaFileService {

    @Autowired
    private MediaFileRepository mediaFileRepository;

    public List<MediaFile> findAll() {
        return (List<MediaFile>) mediaFileRepository.findAll();
    }

    public MediaFile create(MediaFile entity) {
        return mediaFileRepository.save(entity);
    }

    public List<MediaFile> findAllById(List<Long> ids) { return (List<MediaFile>) mediaFileRepository.findAllById(ids); }
    
}
