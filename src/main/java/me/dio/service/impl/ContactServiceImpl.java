package me.dio.service.impl;

import me.dio.domain.model.Contact;
import me.dio.domain.repository.ContactRepository;
import me.dio.service.ContactService;
import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Long UNCHANGEABLE_CONTACT_ID = 1L;

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return this.contactRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Contact create(Contact contactToCreate) {
        ofNullable(contactToCreate).orElseThrow(() -> new BusinessException("Contact to create must not be null."));
        ofNullable(contactToCreate.getName())
                .orElseThrow(() -> new BusinessException("Contact account must not be null."));

        this.validateChangeableId(contactToCreate.getId(), "created");

        return this.contactRepository.save(contactToCreate);
    }

    @Transactional
    public Contact update(Long id, Contact contactToUpdate) {
        this.validateChangeableId(id, "updated");
        Contact dbContact = this.findById(id);
        if (!dbContact.getId().equals(contactToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbContact.setName(contactToUpdate.getName());
        dbContact.setAddress(contactToUpdate.getAddress());
        dbContact.setSocialNetworks(contactToUpdate.getSocialNetworks());
        dbContact.setGroups(contactToUpdate.getGroups());

        return this.contactRepository.save(dbContact);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        Contact dbContact = this.findById(id);
        this.contactRepository.delete(dbContact);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_CONTACT_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_CONTACT_ID, operation));
        }
    }
}
