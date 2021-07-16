package loan.application.SpringBootLoanApplication.repositories;

import loan.application.SpringBootLoanApplication.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
