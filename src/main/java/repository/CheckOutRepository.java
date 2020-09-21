package repository;

import model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutRepository extends JpaRepository<CheckOut,Integer> {
}
