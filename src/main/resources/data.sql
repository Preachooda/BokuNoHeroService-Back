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
    declare
        heroId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM hero where label = ''Герой 1'' and user_id = heroUserId)) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Герой 1'', ''Причуда'', ''REINFORCEMENT'', ''SS'', 1, 5, 5, 5, 5, 5, heroUserId) RETURNING id INTO heroId;
        end if;
    end;

    -- Hero Спец. сервисы 1
    declare
        heroSpecialServicesId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM hero where label = ''Спец. сервисы 1'' and user_id = heroUserSpecialServicesId)) THEN
        INSERT INTO hero(id, creation_date, label, quirk, quirk_type, skill_by_quirk, ranking_position, strength, speed, technique, intelligence, cooperation, user_id)
        VALUES (nextval(''hero_id_seq''), current_date, ''Спец. сервисы 1'', ''Причуда'', ''SUPPORT'', ''SS'', 1, 5, 5, 5, 5, 5, heroUserSpecialServicesId) RETURNING id INTO heroSpecialServicesId;
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
end;
'  LANGUAGE PLPGSQL;