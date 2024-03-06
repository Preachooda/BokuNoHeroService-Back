package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.preachooda.bokunohero.dto.enumeration.Rate;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.entity.Hero;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class ScheduledRatingService {

    @Autowired
    private HeroService heroService;

    @Autowired
    private EvaluationService evaluationService;

    private static final BigDecimal MULTIPLIER_1 = BigDecimal.valueOf(0.8);
    private static final BigDecimal MULTIPLIER_2 = BigDecimal.valueOf(0.2);
    private static final Integer MAX_RATE = Rate.FIVE.getRate();
    private static final Integer MIN_RATE = Rate.NOT_RATED.getRate();
    private static final BigDecimal AVG_RATE = BigDecimal.valueOf(3.5);

    @Scheduled(cron = "${interval-in-cron}")
//    @Transactional
    public void computeRating() {
        System.out.println("Начало выполнения расчета рейтинга героев");
        List<Hero> heroList = heroService.findAll();
        Map<Long, BigDecimal> scoreByHero = new HashMap<>();
        Map<Long, List<Evaluation>> evaluationMap =
                evaluationService.findAllByHeroesId(heroList
                        .stream()
                        .map(Hero::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
                );

        // Высчитываем скор по каждому герою
        for (Hero hero : heroList) {
            // Считаем по характеристикам
            Integer sumByCharacteristics = hero.getStrength() + hero.getSpeed() + hero.getIntelligence() + hero.getTechnique() + hero.getCooperation();
            BigDecimal multiplier = new BigDecimal(hero.getSkillByQuirk().getValue());
            BigDecimal score1 = multiplier.multiply(BigDecimal.valueOf(sumByCharacteristics));

            // Считаем по рейтингу
            BigDecimal score2 = BigDecimal.ZERO;
            List<Evaluation> evaluationList = evaluationMap.get(hero.getId());
            if (!CollectionUtils.isEmpty(evaluationList)) {
                BigDecimal avgRate = BigDecimal.valueOf(evaluationList
                        .stream()
                        .map(Evaluation::getRate)
                        .filter(r -> r != null && !r.equals(Rate.NOT_RATED))
                        .mapToDouble(Rate::getRate).average().orElse(0f));

                score2 = avgRate.compareTo(BigDecimal.ZERO) > 0 ? avgRate.subtract(AVG_RATE) : BigDecimal.ZERO;
            }
            // Результат
            scoreByHero.put(hero.getId(), score1.multiply(MULTIPLIER_1).add(score2.multiply(MULTIPLIER_2)));
        }

        // Сортируем и сохраняем рейтинг
        heroList = heroList.stream()
                .sorted((h1, h2) -> scoreByHero.get(h2.getId()).compareTo(scoreByHero.get(h1.getId())))
//                        Comparator.comparing(h -> scoreByHero.get(h.getId())))
                .collect(Collectors.toList());

        // Очищаем рейтинг
        for (int i = 0; i < heroList.size(); i++) {
            Hero hero = heroList.get(i);
            hero.setRankingPosition(null);
        }
        heroService.getRepository().saveAll(heroList);

        // Устанавливаем рейтинг
        for (int i = 0; i < heroList.size(); i++) {
            Hero hero = heroList.get(i);
            hero.setRankingPosition(i + 1);
        }
        heroService.getRepository().saveAll(heroList);

//        heroList.stream().gr

         System.out.println("Окончание выполнения расчета рейтинга героев");
    }

}
