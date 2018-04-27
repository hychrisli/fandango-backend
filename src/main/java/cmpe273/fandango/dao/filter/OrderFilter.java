package cmpe273.fandango.dao.filter;

import cmpe273.fandango.dto.ParamFilterOrder;
import cmpe273.fandango.entity.TicketOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFilter implements Specification<TicketOrder> {

  private List<Predicate> predicates = new ArrayList<>();
  private ParamFilterOrder filter;

  public OrderFilter(ParamFilterOrder filter){
    this.filter = filter;
  }

  @Override
  public Predicate toPredicate(Root<TicketOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    if ( filter.getMovieId() != null)
      predicates.add(cb.equal(root.get("schedule").get("movie").get("movieId"), filter.getMovieId()));
    if ( filter.getUserId() != null)
      predicates.add(cb.equal(root.get("user").get("userId"), filter.getUserId()));
    if ( filter.getOrderDate() != null )
      predicates.add(cb.equal(root.get("orderDate"), filter.getOrderDate()));
    if (filter.getOrderMonth() != null )
      predicates.add(cb.equal(root.get("orderMonth"), filter.getOrderMonth()));

    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
