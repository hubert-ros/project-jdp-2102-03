package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductName(String productName);

    Product findByProductId(Long productId);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    @Override
    void deleteById(Long productId);
}
