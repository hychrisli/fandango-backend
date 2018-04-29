package cmpe273.fandango.dao.filter;

import cmpe273.fandango.dto.ParamFilterSchedule;
import cmpe273.fandango.entity.Schedule;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSpecs implements Specification<Schedule> {

  private List<Predicate> predicates = new ArrayList<>();

  private ParamFilterSchedule filter;

  private String zipcode;

  private Integer cityId;

  private Integer movieId;

  private Integer theaterId;

  private Boolean isOrdered = false;

  public ScheduleSpecs(ParamFilterSchedule filter){this.filter = filter;}

  @Override
  public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    if (zipcode != null)
      predicates.add(cb.equal(root.get("theater").get("zipcode"),zipcode));
    if (cityId != null)
      predicates.add(cb.equal(root.get("theater").get("city").get("cityId"), cityId));
    if (movieId != null)
      predicates.add(cb.equal(root.get("movie").get("movieId"), movieId));
    if (theaterId != null)
      predicates.add(cb.equal(root.get("theater").get("theaterId"), theaterId));
    if ( filter.getScheduleDate() != null)
      predicates.add(cb.equal(root.get("scheduleDate"), filter.getScheduleDate()));
    if ( filter.getMaxPrice() != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
    if ( filter.getMinPrice() != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
    if ( filter.getMaxStars() != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("movie").get("stars"), filter.getMaxStars()));
    if ( filter.getMinStars() != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("movie").get("stars"), filter.getMinStars()));
    if (isOrdered ) {
      List<Order> orders = new ArrayList<>();
      orders.add(cb.asc(root.get("theater").get("theaterId")));
      orders.add(cb.desc(root.get("movie").get("releaseDate")));
      orders.add(cb.asc(root.get("movie").get("movieId")));
      orders.add(cb.asc(root.get("showtime")));

      query.orderBy(orders);
    }

    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public void setTheaterId(Integer theaterId) {
    this.theaterId = theaterId;
  }

  public void setOrdered(Boolean ordered) {
    isOrdered = ordered;
  }
}
