package base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface basedao<T>{
    List<T> slectall();
    Integer add(T a);
    T slectOne(Serializable a);
    Integer updata(T a);

    void del(Serializable parseInt);

    List<T> findPage(Map<String, Object> filters);
}
