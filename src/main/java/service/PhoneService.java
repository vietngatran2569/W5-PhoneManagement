package service;


import model.Category;
import model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {
    Page<Phone> findAll(Pageable pageable);
    Phone findById(Long id);

    void save(Phone book);

    void remove(Long id);
    Page<Phone> findAllByNameContaining(String name, Pageable pageable);
    Iterable<Phone> findAllByCategory(Category category);

    Page<Phone> findAllByCategory(Pageable pageable, Category category);
    Page<Phone> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Phone> findAllByOrderByPriceDesc(Pageable pageable);
}
