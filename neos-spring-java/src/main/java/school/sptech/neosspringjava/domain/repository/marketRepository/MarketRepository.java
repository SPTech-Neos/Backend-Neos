package school.sptech.neosspringjava.domain.repository.marketRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.market.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
