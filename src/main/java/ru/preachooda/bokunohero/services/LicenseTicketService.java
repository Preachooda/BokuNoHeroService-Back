package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.LicenseTicket;
import ru.preachooda.bokunohero.repository.LicenseTicketRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class LicenseTicketService extends BaseEntityService<LicenseTicket> {

    @Autowired
    private LicenseTicketRepository licenseTicketRepository;

    @Override
    public BaseEntityRepository<LicenseTicket> getRepository() {
        return licenseTicketRepository;
    }

}
