package repository;

import model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter,Integer> {
}
