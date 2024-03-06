package ru.preachooda.bokunohero.services;

import jakarta.annotation.PostConstruct;
import org.pmml4s.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import ru.preachooda.bokunohero.constants.MatchingAlgorithmMLConstants;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.dto.enumeration.PatrolStatus;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.repository.HeroRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeroService extends BaseEntityService<Hero> {

    @Autowired
    private HeroRepository heroRepository;

    private Model model;

    @Override
    public BaseEntityRepository<Hero> getRepository() {
        return heroRepository;
    }

    @PostConstruct
    public void init() throws FileNotFoundException {
        model = Model.fromFile(ResourceUtils.getFile("classpath:hero_model.pmml"));
    }

    public Hero findByUser(User user) {return heroRepository.findByUser(user);}

    public List<Hero> findAllByIds(List<Long> idList) {
        return heroRepository.findByIdIn(idList);
    }

    public List<Hero> findFreeHeroes() {
        List<Hero> heroList = heroRepository.findAvailableHeroes(List.of(ActivityStatus.IN_WORK, ActivityStatus.ASSIGNED, ActivityStatus.EVALUATION));
        return heroList.stream().filter(Objects::nonNull).filter(hero -> hero.getDistrictPatrollingList() == null
                || hero.getDistrictPatrollingList().isEmpty()
                || !List.of(PatrolStatus.STARTED, PatrolStatus.PENDING).containsAll(
                        hero.getDistrictPatrollingList()
                                .stream()
                                .map(DistrictPatrolling::getStatus)
                                .distinct()
                                .toList()))
                .collect(Collectors.toList());
    }

    /**
     * Поиск подходящих для заявки героев. Ипользует предобученную модель SVM
     * @param ticketDto DTO заявки
     * @return          список подходящих героев
     */
    public List<Hero> findSuitableHeroes(TicketDto ticketDto) {
        // Проверка на обязательные поля
        Objects.requireNonNull(ticketDto.getTicketComplexity());
        Objects.requireNonNull(ticketDto.getPriority());

        List<Hero> availableHeroList = this.findFreeHeroes();
        List<Hero> suitableHeroList = new ArrayList<>();

        for (Hero availableHero : availableHeroList) {
            // Подготавливаем DTO для поиска
            // Парсим категории
            List<String> categories = ticketDto.getCategories();
            Double category1 = !categories.isEmpty() ? MatchingAlgorithmMLConstants.categoryMap.get(categories.get(0)) : -1d;
            Double category2 = categories.size() > 1 ? MatchingAlgorithmMLConstants.categoryMap.get(categories.get(1)) : -1d;
            Double category3 = categories.size() > 2 ? MatchingAlgorithmMLConstants.categoryMap.get(categories.get(2)) : -1d;
            Map<String, Double> values = new HashMap<>();
            values.put("Quirk_Type", MatchingAlgorithmMLConstants.quirkTypesMap.get(availableHero.getQuirkType().name()));
            values.put("Quirk_Level", MatchingAlgorithmMLConstants.quirkLevelsMap.get(availableHero.getSkillByQuirk().name()));
            values.put("Rank", Double.valueOf(availableHero.getRankingPosition()));
            values.put("Strength", Double.valueOf(availableHero.getStrength()));
            values.put("Speed", Double.valueOf(availableHero.getSpeed()));
            values.put("Intelligence", Double.valueOf(availableHero.getIntelligence()));
            values.put("Technique", Double.valueOf(availableHero.getTechnique()));
            values.put("Cooperativeness", Double.valueOf(availableHero.getCooperation()));
            values.put("Category_1", category1);
            values.put("Category_2", category2);
            values.put("Category_3", category3);
            values.put("Urgency", Double.valueOf(ticketDto.getPriority()));
            values.put("Complexity", MatchingAlgorithmMLConstants.complexityMap.get(ticketDto.getTicketComplexity()));

            Object[] valuesMap = Arrays.stream(model.inputNames())
                    .map(values::get)
                    .toArray();

            Object[] result = model.predict(valuesMap);

            // Если результат предсказания - 1
            if ((Long) result[0] == 1) {
                suitableHeroList.add(availableHero);
            }
        }

        return suitableHeroList;
    }

}
