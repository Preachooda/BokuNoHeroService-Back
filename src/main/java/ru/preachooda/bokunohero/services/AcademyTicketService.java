package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.AcademyTicket;
import ru.preachooda.bokunohero.repository.AcademyTicketRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;

@Service
public class AcademyTicketService extends BaseEntityService<AcademyTicket> {

    @Autowired
    private AcademyTicketRepository academyTicketRepository;

    @Override
    public BaseEntityRepository<AcademyTicket> getRepository() {
        return academyTicketRepository;
    }

    public List<AcademyTicket> findAllByAcademiesId(Long academyId) {
        return academyTicketRepository.findAllByAcademiesId(academyId);
    }

}
