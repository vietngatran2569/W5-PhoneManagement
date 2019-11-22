package repository;

import model.Phone;
import model.Category;
import model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone,Long> {
    Iterable<Phone> findAllByCategory(Category category);
    Page<Phone> findAllByNameContaining(String name, Pageable pageable);

    Page<Phone> findAllByCategory(Pageable pageable, Category category);
    Page<Phone> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Phone> findAllByOrderByPriceDesc(Pageable pageable);
}
