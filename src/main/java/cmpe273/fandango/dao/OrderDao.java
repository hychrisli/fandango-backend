package cmpe273.fandango.dao;

import cmpe273.fandango.entity.TicketOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDao extends
    PagingAndSortingRepository<TicketOrder, Long>,
    JpaSpecificationExecutor<TicketOrder> {

  Page<TicketOrder> findAll(Specification<TicketOrder> specs, Pageable pageable);

}
