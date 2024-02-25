package ru.preachooda.bokunohero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.MediaFile;

@Repository
public interface MediaFileRepository extends CrudRepository<MediaFile, Long> {

}
