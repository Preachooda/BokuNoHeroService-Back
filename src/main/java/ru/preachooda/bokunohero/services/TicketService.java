package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.dto.enumeration.Rate;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;
import ru.preachooda.bokunohero.repository.TicketRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;
import ru.preachooda.bokunoherocore.validator.exceptions.IncorrectDataException;

import java.util.List;
import java.util.Map;

@Service
public class TicketService extends BaseEntityService<Ticket> {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HeroService heroService;

    @Autowired
    private EvaluationService evaluationService;

    @Override
    public BaseEntityRepository<Ticket> getRepository() {
        return ticketRepository;
    }

    @Override
    public Ticket afterSave(Ticket object) {
        // Сохраняем героев
        Map<Long, Integer> heroRate = object.getHeroRate();
        List<HeroDto> heroDtoList = object.getHeroes();
        if (!CollectionUtils.isEmpty(heroDtoList)) {
            for (HeroDto heroDto : heroDtoList) {
                // Ищем существующую запись
                TicketHeroKey ticketHeroKey = new TicketHeroKey();
                ticketHeroKey.setTicketId(object.getId());
                ticketHeroKey.setHeroId(heroDto.getId());
                Evaluation evaluation = evaluationService.getRepository().findByTicketHeroKey(ticketHeroKey);
                if (evaluation == null) {
                    evaluation = new Evaluation();
                }
                evaluation.setTicketHeroKey(ticketHeroKey);
                // Привязываем оценку
                Integer rate = !CollectionUtils.isEmpty(heroRate) ? heroRate.get(heroDto.getId()) : null;
                if (rate != null) {
                    evaluation.setRate(Rate.getRateByValue(rate));
                }
                evaluationService.getRepository().save(evaluation);
            }
        }
        return super.afterSave(object);
    }

    public List<Ticket> findAllByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    public List<Ticket> findAllByUserId(Long userId) {
        User user = new User();
        user.setId(userId);
        return ticketRepository.findAllByUser(user);
    }

    public Ticket findActiveTicketByHeroId(Long heroId) {
        if (heroId == null) {
            throw new IncorrectDataException("Для поиска активной заявки по герою не передан его идентификатор");
        }
        List<Ticket> ticketList = ticketRepository.findAllByHeroIdAndStatus(heroId, ActivityStatus.IN_WORK);

        if (ticketList.size() > 1) {
            Hero hero = heroService.find(heroId);
            throw new IncorrectDataException("По герою " + hero.getLabel() + " найдено несколько активных заявок");
        }

        return !ticketList.isEmpty() ? ticketList.get(0) : null;
    }

}
