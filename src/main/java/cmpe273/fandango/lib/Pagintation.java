package cmpe273.fandango.lib;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Pagintation {
  public static <T> Page<T> getPage(List<T> list, Pageable pageable){

    int start = pageable.getOffset();
    int end = start + pageable.getPageSize();
    if ( end > list.size()) end = list.size();

    return new PageImpl<>(list.subList(start, end), pageable, list.size());

  }
}
