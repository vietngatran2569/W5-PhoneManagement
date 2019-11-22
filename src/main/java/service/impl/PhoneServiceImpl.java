package service.impl;

import model.Phone;
import model.Category;
import repository.PhoneRepository;
import service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PhoneServiceImpl implements PhoneService {
    @Autowired
    PhoneRepository phoneRepository;
    @Override
    public Page<Phone> findAll(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findOne(id);
    }

    @Override
    public void save(Phone book) {
        phoneRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        phoneRepository.delete(id);
    }

    @Override
    public Page<Phone> findAllByNameContaining(String name, Pageable pageable) {
        return phoneRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Iterable<Phone> findAllByCategory(Category category) {
        return phoneRepository.findAllByCategory(category);
    }

    @Override
    public Page<Phone> findAllByCategory(Pageable pageable, Category category) {
        return phoneRepository.findAllByCategory(pageable,category);
    }

    @Override
    public Page<Phone> findAllByOrderByPriceAsc(Pageable pageable) {
        return phoneRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Phone> findAllByOrderByPriceDesc(Pageable pageable) {
        return phoneRepository.findAllByOrderByPriceDesc(pageable);
    }
}
