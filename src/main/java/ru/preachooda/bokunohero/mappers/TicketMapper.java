package ru.preachooda.bokunohero.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.dto.enumeration.Rate;
import ru.preachooda.bokunohero.dto.enumeration.TicketMediaType;
import ru.preachooda.bokunohero.dto.enumeration.TicketPriority;
import ru.preachooda.bokunohero.entity.*;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;
import ru.preachooda.bokunohero.services.EvaluationService;
import ru.preachooda.bokunohero.services.HeroService;
import ru.preachooda.bokunohero.services.MediaFileService;
import ru.preachooda.bokunohero.services.TicketMediaFileService;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

import java.util.HashMap;
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

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroMapper heroMapper;

    TicketPriority mapPriority(Integer value) {
        return TicketPriority.getPriorityByValue(value);
    }

    Integer mapPriority(TicketPriority value) {
        return value.priority;
    }

    Rate mapRate(Integer value) {
        return Rate.getRateByValue(value);
    }

    Integer mapPriority(Rate value) {
        return value.getRate();
    }

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

        // Устанавливаем героев и оценки
        List<Evaluation> evaluationList = evaluationService.findByTicketId(ticket.getId());
        if (!CollectionUtils.isEmpty(evaluationList)) {
            List<Long> heroIdList = evaluationList.stream().map(Evaluation::getTicketHeroKey).map(TicketHeroKey::getHeroId).collect(Collectors.toList());
            // Оценки
            Map<Long, Integer> heroRateMap = new HashMap<>();
            evaluationList.stream()
                    .filter(ev -> ev.getTicketHeroKey() != null && ev.getTicketHeroKey().getHeroId() != null)
                    .forEach(evaluation -> {
                        heroRateMap.put(evaluation.getTicketHeroKey().getHeroId(),
                                        evaluation.getRate().getRate());
                    });
            // Герои
            List<Hero> heroList = heroService.findAllByIds(heroIdList);
            if (!CollectionUtils.isEmpty(heroList)) {
                ticketDto.setHeroes(heroMapper.entityListToDtoList(heroList));
            }
        }
    }

    // TODO: 06.03.2024 Сохранение героев при отправке заявки
    @AfterMapping
    public void ticketDtoToTicket(TicketDto ticketDto, @MappingTarget Ticket ticket) {
        // Устанавливаем юзера
        User user = new User();
        user.setId(ticketDto.getUserId());
        ticket.setUser(user);
    }

    @AfterMapping
    public void ticketListToTicketDtoList(@MappingTarget List<TicketDto> ticketDtoList) {
        ticketDtoList.forEach(t -> {
            t.setVideoCode(null);
            t.setVideoPath(null);
            t.setAudioCode(null);
            t.setAudioPath(null);
            t.setPhotosCodes(null);
            t.setPhotosPaths(null);
        });
    }

}
