do '
declare
    roleUser bigint;
    roleHero bigint;
    roleAcademy bigint;
    roleServices bigint;
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
    IF (NOT EXISTS(SELECT * FROM roles where code = ''HeroAcademy'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Геройская академия'', ''HeroAcademy'')  RETURNING id INTO roleAcademy;
    end if;

    --  Role SpecialServices
    IF (NOT EXISTS(SELECT * FROM roles where code = ''HeroCommittee'')) THEN
        INSERT INTO roles VALUES (nextval(''roles_id_seq''), current_date, ''Геройский комитет'', ''HeroCommittee'')  RETURNING id INTO roleServices;
    end if;


    -- User user1
    declare
        userId bigint;
    begin
        IF (NOT EXISTS(SELECT * FROM users where username = ''user1'')) THEN
            INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''Пользователь 1'', null, ''12345678'', ''user1'') RETURNING id INTO userId;
            INSERT INTO users_roles VALUES (userId, COALESCE(roleUser, (SELECT id FROM roles WHERE code = ''User'')));
        end if;
    end;

    -- User hero1
    declare
        heroId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM users where username = ''hero1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''Герой 1'', null, ''12345678'', ''hero1'') RETURNING id INTO heroId;
        INSERT INTO users_roles VALUES (heroId, COALESCE(roleHero, (SELECT id FROM roles WHERE code = ''Hero'')));
        end if;
    end;

    -- User academy1
    declare
        academyId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM users where username = ''academy1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''ГА 1'', null, ''12345678'', ''academy1'') RETURNING id INTO academyId;
        INSERT INTO users_roles VALUES (academyId, COALESCE(roleAcademy, (SELECT id FROM roles WHERE code = ''HeroAcademy'')));
        end if;
    end;

    -- User specialServices1
    declare
        servicesId bigint;
    begin
    IF (NOT EXISTS(SELECT * FROM users where username = ''committee1'')) THEN
        INSERT INTO users VALUES (nextval(''users_id_seq''), current_date, ''ГК 1'', null, ''12345678'', ''committee1'') RETURNING id INTO servicesId;
        INSERT INTO users_roles VALUES (servicesId, COALESCE(roleServices, (SELECT id FROM roles WHERE code = ''HeroCommittee'')));
        end if;
    end;
end;
'  LANGUAGE PLPGSQL;