package base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import util.CastUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract  class baseServer<T>{
    public abstract basedao<T> getEntityDao();
    public List<T> selectall(){
        return getEntityDao().slectall();

    }
    public  Integer add(T a){
        Integer integer = getEntityDao().add(a);
        return integer;
    }
    public T slectOne(Integer a){
        T T = getEntityDao().slectOne(a);
        return T;
    }
    public Integer uppdata(T a ){
        Integer T = getEntityDao().updata(a);
        return T;
    }

    public void del(Serializable parseInt) {
        getEntityDao().del(parseInt);
    }


    public PageInfo<T> findPages(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<T>(getEntityDao().findPage(filters), 3);
    }

}
