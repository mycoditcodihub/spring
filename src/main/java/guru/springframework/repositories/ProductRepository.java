package guru.springframework.repositories;

import com.codihub.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 12/18/15.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
