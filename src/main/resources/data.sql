do '
declare
    roleUser bigint;
    roleHero bigint;
    roleAcademy bigint;
    roleServices bigint;

    heroUserId bigint;
    heroUserSpecialServicesId bigint;
    heroAcademyHeadId bigint;

    password varchar := ''$2a$12$8JQu3kZwMXbcO/TWaLAGx.gwJjuSBIJ0D8R38akaxnJWw2ObcQkEK'';
begin
    --  Role User
    IF (NOT EXISTS(SELECT * FROM roles where code = ''User'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Пользователь'', ''User'') RETURNING id INTO roleUser;
    end if;
    --  Role Hero
    IF (NOT EXISTS(SELECT * FROM roles where code = ''Hero'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Герой'', ''Hero'')  RETURNING id INTO roleHero;
    end if;

    --  Role Academy
    IF (NOT EXISTS(SELECT * FROM roles where code = ''HeroAcademyHead'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Геройская академия'', ''HeroAcademyHead'')  RETURNING id INTO roleAcademy;
    end if;

    --  Role SpecialServices
    IF (NOT EXISTS(SELECT * FROM roles where code = ''HeroCommitteeHead'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Геройский комитет'', ''HeroCommitteeHead'')  RETURNING id INTO roleServices;
    end if;


    -- User user1
    declare
        userId bigint;
    begin
        IF (NOT EXISTS(SELECT * FROM users where username = ''user1'')) THEN
            INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''Пользователь 1'', null, password, ''user1'') RETURNING id INTO userId;
            INSERT INTO users_roles VALUES (userId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''User'')));
        end if;
    end;

    -- User hero1
    IF (NOT EXISTS(SELECT * FROM users where username = ''hero1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''Герой 1'', null, password, ''hero1'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleHero, (SELECT id FROM roles WHERE code = ''Hero'')));
    else
        SELECT u.id into heroUserId FROM users u where u.username = ''hero1'';
    end if;

    -- User specialServices1
    IF (NOT EXISTS(SELECT * FROM users where username = ''heroSpecialServicesId1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''Спец. сервисы 1'', null, password, ''heroSpecialServicesId1'') RETURNING id INTO heroUserSpecialServicesId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleHero, (SELECT id FROM roles WHERE code = ''Hero'')));
    else
        SELECT u.id into heroUserSpecialServicesId FROM users u where u.username = ''heroSpecialServicesId1'';
    end if;

    -- User academyHead1
    IF (NOT EXISTS(SELECT * FROM users where username = ''academyHead1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''ГА 1'', null, password, ''academyHead1'') RETURNING id INTO heroAcademyHeadId;
        INSERT INTO users_roles VALUES (heroAcademyHeadId, COALESCE(roleAcademy, (SELECT id FROM roles WHERE code = ''HeroAcademyHead'')));
    end if;

    -- User committeeHeadId1
    declare
        committeeHeadId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM users where username = ''committeeHead1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''ГК 1'', null, password, ''committeeHead1'') RETURNING id INTO committeeHeadId;
        INSERT INTO users_roles VALUES (committeeHeadId, COALESCE(roleServices, (SELECT id FROM roles WHERE code = ''HeroCommitteeHead'')));
        end if;
    end;

    -- Hero Герой1
--     declare
--         heroId bigint;
--     begin
--     IF (NOT EXISTS(SELECT * FROM hero where label = ''Герой 1'' and user_id = heroUserId)) THEN
--         INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
--         VALUES (nextval(''hero_id_seq''), current_date, ''Герой 1'', ''Причуда'', ''REINFORCEMENT'', ''SS'', 1, 5, 5, 5, 5, 5, heroUserId) RETURNING id INTO heroId;
--         end if;
--     end;

    -- Hero Спец. сервисы 1
    declare
        heroSpecialServicesId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM hero where label = ''Спец. сервисы 1'' and user_id = heroUserSpecialServicesId)) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Спец. сервисы 1'', ''Причуда'', ''SUPPORT'', ''S'', 13, 5, 5, 5, 5, 5, heroUserSpecialServicesId) RETURNING id INTO heroSpecialServicesId;
        end if;
    end;

    -- Academy Академия1
    declare
        academyId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM academy where label = ''Академия 1'')) THEN
        INSERT INTO academy(id, creation_date, label, address, motto, head_id)
        VALUES (nextval(''academy_id_seq''), current_date, ''Академия 1'', ''г. Гифу'', ''Быстрее, выше, сильнее'', heroAcademyHeadId) RETURNING id INTO academyId;
        end if;
    end;

-- Вставляем героев

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1000'')) THEN
        INSERT INTO users VALUES (1000, current_date, ''Пользователь-герой 1000'', null, password, ''userHero1000'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Хатсуме'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Хатсуме'', ''Водяное восприятие'', ''REINFORCEMENT'', ''SS'', 1, 6, 6, 5, 6, 6, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1001'')) THEN
        INSERT INTO users VALUES (1001, current_date, ''Пользователь-герой 1001'', null, password, ''userHero1001'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рикидо Сато'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рикидо Сато'', ''Сладкий молот'', ''REINFORCEMENT'', ''SS'', 2, 5, 6, 6, 6, 6, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1002'')) THEN
        INSERT INTO users VALUES (1002, current_date, ''Пользователь-герой 1002'', null, password, ''userHero1002'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Шинко Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Шинко Тодороки'', ''Ледяной огонь'', ''DESTRUCTION'', ''SS'', 3, 6, 6, 5, 6, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1003'')) THEN
        INSERT INTO users VALUES (1003, current_date, ''Пользователь-герой 1003'', null, password, ''userHero1003'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рёй Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рёй Мидория'', ''Зеленая вспышка'', ''REINFORCEMENT'', ''SS'', 4, 6, 5, 5, 6, 6, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1004'')) THEN
        INSERT INTO users VALUES (1004, current_date, ''Пользователь-герой 1004'', null, password, ''userHero1004'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хагаши Кирисаки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хагаши Кирисаки'', ''Острая игла'', ''REINFORCEMENT'', ''SS'', 5, 5, 6, 6, 6, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1005'')) THEN
        INSERT INTO users VALUES (1005, current_date, ''Пользователь-герой 1005'', null, password, ''userHero1005'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мидзуки Имай'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мидзуки Имай'', ''Вспышка света'', ''REINFORCEMENT'', ''SS'', 6, 6, 5, 5, 6, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1006'')) THEN
        INSERT INTO users VALUES (1006, current_date, ''Пользователь-герой 1006'', null, password, ''userHero1006'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кёсукэ Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кёсукэ Тодороки'', ''Ледяное пламя'', ''DESTRUCTION'', ''SS'', 7, 5, 5, 5, 6, 6, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1007'')) THEN
        INSERT INTO users VALUES (1007, current_date, ''Пользователь-герой 1007'', null, password, ''userHero1007'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юто Хирока'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юто Хирока'', ''Металлическое сердце'', ''REINFORCEMENT'', ''SS'', 8, 6, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1008'')) THEN
        INSERT INTO users VALUES (1008, current_date, ''Пользователь-герой 1008'', null, password, ''userHero1008'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сакура Хатсумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сакура Хатсумэ'', ''Весенний ветер'', ''SUPPORT'', ''SS'', 9, 5, 5, 5, 6, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1009'')) THEN
        INSERT INTO users VALUES (1009, current_date, ''Пользователь-герой 1009'', null, password, ''userHero1009'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сатору Итидзука'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сатору Итидзука'', ''Железная воля'', ''REINFORCEMENT'', ''SS'', 10, 5, 5, 6, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1010'')) THEN
        INSERT INTO users VALUES (1010, current_date, ''Пользователь-герой 1010'', null, password, ''userHero1010'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Эйда Тэнсё'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Эйда Тэнсё'', ''Шквал железа'', ''DESTRUCTION'', ''S'', 11, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1011'')) THEN
        INSERT INTO users VALUES (1011, current_date, ''Пользователь-герой 1011'', null, password, ''userHero1011'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юга Аояма'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юга Аояма'', ''Блеск космоса'', ''DESTRUCTION'', ''S'', 12, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1013'')) THEN
        INSERT INTO users VALUES (1013, current_date, ''Пользователь-герой 1013'', null, password, ''userHero1013'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кайо Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кайо Мидория'', ''Зеленый бриз'', ''REINFORCEMENT'', ''S'', 14, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1014'')) THEN
        INSERT INTO users VALUES (1014, current_date, ''Пользователь-герой 1014'', null, password, ''userHero1014'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мэй Хатсумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мэй Хатсумэ'', ''Технологическая фантазия'', ''CONTROL'', ''S'', 15, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1015'')) THEN
        INSERT INTO users VALUES (1015, current_date, ''Пользователь-герой 1015'', null, password, ''userHero1015'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Иппо Масука'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Иппо Масука'', ''Тяжелое кулак'', ''REINFORCEMENT'', ''S'', 16, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1016'')) THEN
        INSERT INTO users VALUES (1016, current_date, ''Пользователь-герой 1016'', null, password, ''userHero1016'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Санэй Ёрозуя'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Санэй Ёрозуя'', ''Изогнутая мечта'', ''CONTROL'', ''S'', 17, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1017'')) THEN
        INSERT INTO users VALUES (1017, current_date, ''Пользователь-герой 1017'', null, password, ''userHero1017'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Судзи Кирию'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Судзи Кирию'', ''Магнитная аура'', ''REINFORCEMENT'', ''S'', 18, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1018'')) THEN
        INSERT INTO users VALUES (1018, current_date, ''Пользователь-герой 1018'', null, password, ''userHero1018'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Моё Кугимия'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Моё Кугимия'', ''Металлическая леди'', ''REINFORCEMENT'', ''S'', 19, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1019'')) THEN
        INSERT INTO users VALUES (1019, current_date, ''Пользователь-герой 1019'', null, password, ''userHero1019'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Шинку Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Шинку Тодороки'', ''Ледяная молния'', ''DESTRUCTION'', ''S'', 20, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1020'')) THEN
        INSERT INTO users VALUES (1020, current_date, ''Пользователь-герой 1020'', null, password, ''userHero1020'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сора Хагакура'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сора Хагакура'', ''Прозрачная вспышка'', ''SUPPORT'', ''S'', 21, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1021'')) THEN
        INSERT INTO users VALUES (1021, current_date, ''Пользователь-герой 1021'', null, password, ''userHero1021'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хэйдзи Хатсумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хэйдзи Хатсумэ'', ''Технологический гений'', ''CONTROL'', ''S'', 22, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1022'')) THEN
        INSERT INTO users VALUES (1022, current_date, ''Пользователь-герой 1022'', null, password, ''userHero1022'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Шинку Тодорока'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Шинку Тодорока'', ''Ледяная молния'', ''DESTRUCTION'', ''S'', 23, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1023'')) THEN
        INSERT INTO users VALUES (1023, current_date, ''Пользователь-герой 1023'', null, password, ''userHero1023'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ёро Зиро'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ёро Зиро'', ''Взрывной каток'', ''DESTRUCTION'', ''SS'', 24, 5, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1024'')) THEN
        INSERT INTO users VALUES (1024, current_date, ''Пользователь-герой 1024'', null, password, ''userHero1024'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сэки Дзиро'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сэки Дзиро'', ''Чудовищный хват'', ''REINFORCEMENT'', ''B'', 25, 5, 4, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1025'')) THEN
        INSERT INTO users VALUES (1025, current_date, ''Пользователь-герой 1025'', null, password, ''userHero1025'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ринко Судзуки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ринко Судзуки'', ''Голубая мечта'', ''CONTROL'', ''A'', 26, 5, 5, 5, 4, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1026'')) THEN
        INSERT INTO users VALUES (1026, current_date, ''Пользователь-герой 1026'', null, password, ''userHero1026'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Очако Урарака'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Очако Урарака'', ''Невесомость'', ''SUPPORT'', ''A'', 27, 5, 4, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1027'')) THEN
        INSERT INTO users VALUES (1027, current_date, ''Пользователь-герой 1027'', null, password, ''userHero1027'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Минасси Хитошэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Минасси Хитошэ'', ''Отраженная сила'', ''REINFORCEMENT'', ''A'', 28, 5, 4, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1028'')) THEN
        INSERT INTO users VALUES (1028, current_date, ''Пользователь-герой 1028'', null, password, ''userHero1028'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хирако Хосики'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хирако Хосики'', ''Звездная мечта'', ''CONTROL'', ''A'', 29, 5, 4, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1029'')) THEN
        INSERT INTO users VALUES (1029, current_date, ''Пользователь-герой 1029'', null, password, ''userHero1029'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Омура'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Омура'', ''Серебряный меч'', ''REINFORCEMENT'', ''A'', 30, 4, 5, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1030'')) THEN
        INSERT INTO users VALUES (1030, current_date, ''Пользователь-герой 1030'', null, password, ''userHero1030'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Тору Хагакурэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Тору Хагакурэ'', ''Прозрачная девушка'', ''SUPPORT'', ''A'', 31, 5, 5, 5, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1031'')) THEN
        INSERT INTO users VALUES (1031, current_date, ''Пользователь-герой 1031'', null, password, ''userHero1031'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Такуми Хатсумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Такуми Хатсумэ'', ''Ледяной кулак'', ''DESTRUCTION'', ''A'', 32, 5, 5, 5, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1032'')) THEN
        INSERT INTO users VALUES (1032, current_date, ''Пользователь-герой 1032'', null, password, ''userHero1032'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Хатсумю'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Хатсумю'', ''Танцующая леди'', ''CONTROL'', ''A'', 33, 5, 5, 4, 4, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1033'')) THEN
        INSERT INTO users VALUES (1033, current_date, ''Пользователь-герой 1033'', null, password, ''userHero1033'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кэнко Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кэнко Тодороки'', ''Ледяная сталь'', ''DESTRUCTION'', ''A'', 34, 5, 4, 5, 5, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1034'')) THEN
        INSERT INTO users VALUES (1034, current_date, ''Пользователь-герой 1034'', null, password, ''userHero1034'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Масару Бакуго'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Масару Бакуго'', ''Взрывная молния'', ''DESTRUCTION'', ''A'', 35, 5, 4, 4, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1035'')) THEN
        INSERT INTO users VALUES (1035, current_date, ''Пользователь-герой 1035'', null, password, ''userHero1035'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мицуки Идзуми'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мицуки Идзуми'', ''Вечерняя звезда'', ''SUPPORT'', ''A'', 36, 4, 5, 5, 5, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1036'')) THEN
        INSERT INTO users VALUES (1036, current_date, ''Пользователь-герой 1036'', null, password, ''userHero1036'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ёро Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ёро Мидория'', ''Зеленый щит'', ''REINFORCEMENT'', ''A'', 37, 4, 4, 5, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1037'')) THEN
        INSERT INTO users VALUES (1037, current_date, ''Пользователь-герой 1037'', null, password, ''userHero1037'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мисато Ида'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мисато Ида'', ''Стальная скорость'', ''REINFORCEMENT'', ''B'', 38, 5, 3, 5, 5, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1038'')) THEN
        INSERT INTO users VALUES (1038, current_date, ''Пользователь-герой 1038'', null, password, ''userHero1038'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рё Сано'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рё Сано'', ''Легкий туман'', ''SUPPORT'', ''A'', 39, 5, 5, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1039'')) THEN
        INSERT INTO users VALUES (1039, current_date, ''Пользователь-герой 1039'', null, password, ''userHero1039'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Найто Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Найто Мидория'', ''Зеленый взгляд'', ''REINFORCEMENT'', ''A'', 40, 5, 5, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1040'')) THEN
        INSERT INTO users VALUES (1040, current_date, ''Пользователь-герой 1040'', null, password, ''userHero1040'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ёки Тодороке'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ёки Тодороке'', ''Ледяное пламя'', ''DESTRUCTION'', ''A'', 41, 5, 5, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1041'')) THEN
        INSERT INTO users VALUES (1041, current_date, ''Пользователь-герой 1041'', null, password, ''userHero1041'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Дзиро Масаши'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Дзиро Масаши'', ''Громовая сила'', ''REINFORCEMENT'', ''A'', 42, 4, 5, 4, 5, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1042'')) THEN
        INSERT INTO users VALUES (1042, current_date, ''Пользователь-герой 1042'', null, password, ''userHero1042'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кацуки Бакуго'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кацуки Бакуго'', ''Взрывная длань'', ''DESTRUCTION'', ''A'', 43, 4, 5, 5, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1043'')) THEN
        INSERT INTO users VALUES (1043, current_date, ''Пользователь-герой 1043'', null, password, ''userHero1043'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сакура Хагакурэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сакура Хагакурэ'', ''Прозрачная мечта'', ''SUPPORT'', ''B'', 44, 5, 4, 4, 3, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1044'')) THEN
        INSERT INTO users VALUES (1044, current_date, ''Пользователь-герой 1044'', null, password, ''userHero1044'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Кагэяма'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Кагэяма'', ''Танцующий огонь'', ''DESTRUCTION'', ''B'', 45, 3, 4, 4, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1045'')) THEN
        INSERT INTO users VALUES (1045, current_date, ''Пользователь-герой 1045'', null, password, ''userHero1045'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хаято Асуко'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хаято Асуко'', ''Огненная душа'', ''DESTRUCTION'', ''A'', 46, 4, 4, 4, 5, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1046'')) THEN
        INSERT INTO users VALUES (1046, current_date, ''Пользователь-герой 1046'', null, password, ''userHero1046'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хагакурэ Тору'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хагакурэ Тору'', ''Невидимое присутствие'', ''SUPPORT'', ''A'', 47, 4, 4, 4, 4, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1047'')) THEN
        INSERT INTO users VALUES (1047, current_date, ''Пользователь-герой 1047'', null, password, ''userHero1047'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хана Сирумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хана Сирумэ'', ''Цветочная благодать'', ''SUPPORT'', ''C'', 48, 4, 4, 4, 4, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1048'')) THEN
        INSERT INTO users VALUES (1048, current_date, ''Пользователь-герой 1048'', null, password, ''userHero1048'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Шинсукэ Хагакурэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Шинсукэ Хагакурэ'', ''Прозрачное сердце'', ''SUPPORT'', ''C'', 49, 4, 4, 4, 4, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1049'')) THEN
        INSERT INTO users VALUES (1049, current_date, ''Пользователь-герой 1049'', null, password, ''userHero1049'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ёко Асуко'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ёко Асуко'', ''Огненный шторм'', ''DESTRUCTION'', ''C'', 50, 4, 4, 4, 4, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1050'')) THEN
        INSERT INTO users VALUES (1050, current_date, ''Пользователь-герой 1050'', null, password, ''userHero1050'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сен Кайбара'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сен Кайбара'', ''Вихревой ветер'', ''REINFORCEMENT'', ''C'', 51, 3, 4, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1051'')) THEN
        INSERT INTO users VALUES (1051, current_date, ''Пользователь-герой 1051'', null, password, ''userHero1051'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ринко Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ринко Мидория'', ''Зеленый лед'', ''REINFORCEMENT'', ''C'', 52, 3, 4, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1052'')) THEN
        INSERT INTO users VALUES (1052, current_date, ''Пользователь-герой 1052'', null, password, ''userHero1052'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сора Хагакуре'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сора Хагакуре'', ''Прозрачная вспышка'', ''SUPPORT'', ''C'', 53, 3, 4, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1053'')) THEN
        INSERT INTO users VALUES (1053, current_date, ''Пользователь-герой 1053'', null, password, ''userHero1053'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Акито Масаи'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Акито Масаи'', ''Огненная кара'', ''DESTRUCTION'', ''B'', 54, 3, 3, 3, 5, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1054'')) THEN
        INSERT INTO users VALUES (1054, current_date, ''Пользователь-герой 1054'', null, password, ''userHero1054'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рин Сато'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рин Сато'', ''Сладкий бунт'', ''REINFORCEMENT'', ''C'', 55, 3, 4, 3, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1055'')) THEN
        INSERT INTO users VALUES (1055, current_date, ''Пользователь-герой 1055'', null, password, ''userHero1055'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Момо Яойорозу'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Момо Яойорозу'', ''Творческий разум'', ''CONTROL'', ''C'', 56, 3, 3, 4, 4, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1056'')) THEN
        INSERT INTO users VALUES (1056, current_date, ''Пользователь-герой 1056'', null, password, ''userHero1056'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Акира Асуко'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Акира Асуко'', ''Огненный ветер'', ''DESTRUCTION'', ''B'', 57, 3, 4, 3, 3, 5, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1057'')) THEN
        INSERT INTO users VALUES (1057, current_date, ''Пользователь-герой 1057'', null, password, ''userHero1057'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Тору Монома'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Тору Монома'', ''Капризный крик'', ''MUTATION'', ''C'', 58, 3, 4, 4, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1058'')) THEN
        INSERT INTO users VALUES (1058, current_date, ''Пользователь-герой 1058'', null, password, ''userHero1058'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Киёми Хатсумэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Киёми Хатсумэ'', ''Ледяной бриз'', ''DESTRUCTION'', ''C'', 59, 3, 4, 4, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1059'')) THEN
        INSERT INTO users VALUES (1059, current_date, ''Пользователь-герой 1059'', null, password, ''userHero1059'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мина Ашидо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мина Ашидо'', ''Кислотный свет'', ''DESTRUCTION'', ''C'', 60, 3, 4, 3, 3, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1060'')) THEN
        INSERT INTO users VALUES (1060, current_date, ''Пользователь-герой 1060'', null, password, ''userHero1060'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Шото Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Шото Тодороки'', ''Ледяной огонь'', ''DESTRUCTION'', ''C'', 61, 3, 3, 4, 4, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1061'')) THEN
        INSERT INTO users VALUES (1061, current_date, ''Пользователь-герой 1061'', null, password, ''userHero1061'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Хатсума'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Хатсума'', ''Танцующая леди'', ''CONTROL'', ''C'', 62, 3, 3, 4, 3, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1062'')) THEN
        INSERT INTO users VALUES (1062, current_date, ''Пользователь-герой 1062'', null, password, ''userHero1062'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Макото Тодорока'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Макото Тодорока'', ''Ледяное сердце'', ''DESTRUCTION'', ''B'', 63, 4, 3, 3, 3, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1063'')) THEN
        INSERT INTO users VALUES (1063, current_date, ''Пользователь-герой 1063'', null, password, ''userHero1063'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Изуку Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Изуку Мидория'', ''Возрожденный герой'', ''REINFORCEMENT'', ''B'', 64, 3, 4, 3, 4, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1064'')) THEN
        INSERT INTO users VALUES (1064, current_date, ''Пользователь-герой 1064'', null, password, ''userHero1064'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сора Ида'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сора Ида'', ''Бешеный пламень'', ''DESTRUCTION'', ''D'', 65, 4, 4, 4, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1065'')) THEN
        INSERT INTO users VALUES (1065, current_date, ''Пользователь-герой 1065'', null, password, ''userHero1065'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Томоё Хатсуме'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Томоё Хатсуме'', ''Ледяной шторм'', ''DESTRUCTION'', ''D'', 66, 4, 4, 3, 2, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1066'')) THEN
        INSERT INTO users VALUES (1066, current_date, ''Пользователь-герой 1066'', null, password, ''userHero1066'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кейен Асуки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кейен Асуки'', ''Огненный порыв'', ''DESTRUCTION'', ''C'', 67, 4, 3, 3, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1067'')) THEN
        INSERT INTO users VALUES (1067, current_date, ''Пользователь-герой 1067'', null, password, ''userHero1067'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хиро Ямада'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хиро Ямада'', ''Звездный мечник'', ''DESTRUCTION'', ''C'', 68, 3, 4, 3, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1068'')) THEN
        INSERT INTO users VALUES (1068, current_date, ''Пользователь-герой 1068'', null, password, ''userHero1068'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Синсей Номура'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Синсей Номура'', ''Мощный удар'', ''DESTRUCTION'', ''D'', 69, 4, 2, 2, 3, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1069'')) THEN
        INSERT INTO users VALUES (1069, current_date, ''Пользователь-герой 1069'', null, password, ''userHero1069'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хаято Асука'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хаято Асука'', ''Огненный взгляд'', ''DESTRUCTION'', ''D'', 70, 3, 3, 3, 4, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1070'')) THEN
        INSERT INTO users VALUES (1070, current_date, ''Пользователь-герой 1070'', null, password, ''userHero1070'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Дени Хаидо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Дени Хаидо'', ''Электрическая душа'', ''DESTRUCTION'', ''D'', 71, 2, 4, 3, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1071'')) THEN
        INSERT INTO users VALUES (1071, current_date, ''Пользователь-герой 1071'', null, password, ''userHero1071'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рейджи Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рейджи Мидория'', ''Зеленый ветер'', ''REINFORCEMENT'', ''D'', 72, 2, 3, 4, 2, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1072'')) THEN
        INSERT INTO users VALUES (1072, current_date, ''Пользователь-герой 1072'', null, password, ''userHero1072'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Токоями Фумикаге'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Токоями Фумикаге'', ''Темное восприятие'', ''CONTROL'', ''D'', 73, 2, 3, 3, 2, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1073'')) THEN
        INSERT INTO users VALUES (1073, current_date, ''Пользователь-герой 1073'', null, password, ''userHero1073'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кендзи Танеба'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кендзи Танеба'', ''Каменное сердце'', ''REINFORCEMENT'', ''D'', 74, 4, 2, 2, 2, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1074'')) THEN
        INSERT INTO users VALUES (1074, current_date, ''Пользователь-герой 1074'', null, password, ''userHero1074'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рикуо Мидзукэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рикуо Мидзукэ'', ''Тяжелая душа'', ''REINFORCEMENT'', ''D'', 75, 2, 4, 3, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1075'')) THEN
        INSERT INTO users VALUES (1075, current_date, ''Пользователь-герой 1075'', null, password, ''userHero1075'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ицуки Кото'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ицуки Кото'', ''Пробивающий взгляд'', ''CONTROL'', ''D'', 76, 2, 3, 2, 2, 4, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1076'')) THEN
        INSERT INTO users VALUES (1076, current_date, ''Пользователь-герой 1076'', null, password, ''userHero1076'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Теня Ида'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Теня Ида'', ''Скоростное пламя'', ''REINFORCEMENT'', ''E'', 77, 3, 3, 1, 3, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1077'')) THEN
        INSERT INTO users VALUES (1077, current_date, ''Пользователь-герой 1077'', null, password, ''userHero1077'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Изуми Тору'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Изуми Тору'', ''Горячая лава'', ''DESTRUCTION'', ''E'', 78, 3, 1, 1, 3, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1078'')) THEN
        INSERT INTO users VALUES (1078, current_date, ''Пользователь-герой 1078'', null, password, ''userHero1078'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сакура Андо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сакура Андо'', ''Цветущий лепесток'', ''SUPPORT'', ''E'', 79, 2, 2, 2, 2, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1079'')) THEN
        INSERT INTO users VALUES (1079, current_date, ''Пользователь-герой 1079'', null, password, ''userHero1079'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Тсуя Асуи'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Тсуя Асуи'', ''Лягушачье квакание'', ''MUTATION'', ''E'', 80, 1, 3, 2, 2, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1080'')) THEN
        INSERT INTO users VALUES (1080, current_date, ''Пользователь-герой 1080'', null, password, ''userHero1080'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Химико Сёто'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Химико Сёто'', ''Ядовитый поцелуй'', ''MUTATION'', ''D'', 81, 2, 2, 2, 3, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1081'')) THEN
        INSERT INTO users VALUES (1081, current_date, ''Пользователь-герой 1081'', null, password, ''userHero1081'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Макото Тодороке'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Макото Тодороке'', ''Ледяной камень'', ''DESTRUCTION'', ''F'', 82, 2, 2, 2, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1082'')) THEN
        INSERT INTO users VALUES (1082, current_date, ''Пользователь-герой 1082'', null, password, ''userHero1082'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кимико Кагэяма'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кимико Кагэяма'', ''Танцующий ветер'', ''REINFORCEMENT'', ''E'', 83, 2, 3, 1, 1, 3, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1083'')) THEN
        INSERT INTO users VALUES (1083, current_date, ''Пользователь-герой 1083'', null, password, ''userHero1083'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сюкэй Мурока'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сюкэй Мурока'', ''Мозаика звуков'', ''CONTROL'', ''F'', 84, 2, 2, 2, 2, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1084'')) THEN
        INSERT INTO users VALUES (1084, current_date, ''Пользователь-герой 1084'', null, password, ''userHero1084'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ёсукэ Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ёсукэ Мидория'', ''Зеленая искра'', ''REINFORCEMENT'', ''F'', 85, 2, 2, 1, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1085'')) THEN
        INSERT INTO users VALUES (1085, current_date, ''Пользователь-герой 1085'', null, password, ''userHero1085'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мичиро Тодо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мичиро Тодо'', ''Горящая решимость'', ''DESTRUCTION'', ''F'', 86, 2, 1, 2, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1086'')) THEN
        INSERT INTO users VALUES (1086, current_date, ''Пользователь-герой 1086'', null, password, ''userHero1086'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сайто Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сайто Тодороки'', ''Ледяной туман'', ''DESTRUCTION'', ''F'', 87, 2, 1, 2, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1087'')) THEN
        INSERT INTO users VALUES (1087, current_date, ''Пользователь-герой 1087'', null, password, ''userHero1087'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Хитоси Шинсо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Хитоси Шинсо'', ''Оплетающий шепот'', ''CONTROL'', ''E'', 88, 2, 2, 1, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1088'')) THEN
        INSERT INTO users VALUES (1088, current_date, ''Пользователь-герой 1088'', null, password, ''userHero1088'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Рэй Хагакурэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Рэй Хагакурэ'', ''Скрытая аура'', ''CONTROL'', ''E'', 89, 2, 1, 2, 2, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1089'')) THEN
        INSERT INTO users VALUES (1089, current_date, ''Пользователь-герой 1089'', null, password, ''userHero1089'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Сайто Хагакурэ'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Сайто Хагакурэ'', ''Прозрачное присутствие'', ''SUPPORT'', ''E'', 90, 1, 2, 1, 3, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1090'')) THEN
        INSERT INTO users VALUES (1090, current_date, ''Пользователь-герой 1090'', null, password, ''userHero1090'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Нито Монома'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Нито Монома'', ''Копирующий дар'', ''MUTATION'', ''F'', 91, 2, 2, 1, 2, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1091'')) THEN
        INSERT INTO users VALUES (1091, current_date, ''Пользователь-герой 1091'', null, password, ''userHero1091'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Кецубура Тэнко'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Кецубура Тэнко'', ''Леденящая душа'', ''MUTATION'', ''F'', 92, 2, 2, 2, 1, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1092'')) THEN
        INSERT INTO users VALUES (1092, current_date, ''Пользователь-герой 1092'', null, password, ''userHero1092'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Року Мина'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Року Мина'', ''Кислотная искра'', ''DESTRUCTION'', ''E'', 93, 2, 2, 1, 2, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1093'')) THEN
        INSERT INTO users VALUES (1093, current_date, ''Пользователь-герой 1093'', null, password, ''userHero1093'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Юки Ягами'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Юки Ягами'', ''Сияющий сумрак'', ''CONTROL'', ''E'', 94, 2, 2, 2, 1, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1094'')) THEN
        INSERT INTO users VALUES (1094, current_date, ''Пользователь-герой 1094'', null, password, ''userHero1094'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мицуки Мидория'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мицуки Мидория'', ''Зеленая сила'', ''REINFORCEMENT'', ''F'', 95, 2, 1, 2, 1, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1095'')) THEN
        INSERT INTO users VALUES (1095, current_date, ''Пользователь-герой 1095'', null, password, ''userHero1095'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Каминари Дэнки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Каминари Дэнки'', ''Электрический щит'', ''DESTRUCTION'', ''F'', 96, 1, 2, 1, 1, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1096'')) THEN
        INSERT INTO users VALUES (1096, current_date, ''Пользователь-герой 1096'', null, password, ''userHero1096'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ичиро Имай'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ичиро Имай'', ''Ответственный голос'', ''SUPPORT'', ''F'', 97, 1, 2, 1, 1, 2, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1097'')) THEN
        INSERT INTO users VALUES (1097, current_date, ''Пользователь-герой 1097'', null, password, ''userHero1097'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Ацуко Тодороки'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Ацуко Тодороки'', ''Ледяное пламя'', ''DESTRUCTION'', ''F'', 98, 1, 1, 2, 2, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1098'')) THEN
        INSERT INTO users VALUES (1098, current_date, ''Пользователь-герой 1098'', null, password, ''userHero1098'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Томоё Хатсуми'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Томоё Хатсуми'', ''Ледяной покров'', ''DESTRUCTION'', ''E'', 99, 1, 2, 1, 2, 1, heroUserId);
        end if;

    IF (NOT EXISTS(SELECT * FROM users where username = ''userHero1099'')) THEN
        INSERT INTO users VALUES (1099, current_date, ''Пользователь-герой 1099'', null, password, ''userHero1099'') RETURNING id INTO heroUserId;
        INSERT INTO users_roles VALUES (heroUserId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;

    IF (NOT EXISTS(SELECT * FROM hero where label = ''Мицуки Серо'')) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Мицуки Серо'', ''Конструктивная устойчивость'', ''REINFORCEMENT'', ''F'', 100, 1, 1, 1, 1, 1, heroUserId);
        end if;
end;
'  LANGUAGE PLPGSQL;