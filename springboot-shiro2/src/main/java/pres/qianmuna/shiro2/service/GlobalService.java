package pres.qianmuna.shiro2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  22:52
 * @description :
 */
public interface GlobalService<T> {

    /**
     * 插入
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 批量插入
     * @param entityList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveBatch(Collection<T> entityList){
        return saveBatch(entityList,100);
    }

    /**
     * 批量插入
     * @param entityList
     * @param size
     * @return
     */
    boolean saveBatch(Collection<T> entityList , int size);

    /**
     * 批量修改添加
     * @param entityList
     * @param size
     * @return
     */
    boolean saveOrUpdateBatch(Collection<T> entityList , int size);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    boolean removeById(Serializable id);

    /**
     * 根据map删除
     * @param columnMap
     * @return
     */
    boolean removeByMap(Map<String,Object> columnMap);

    /**
     * 根据实体条件删除
     * @param queryWrapper
     * @return
     */
    boolean remove(Wrapper<T> queryWrapper);

    /**
     *  根据id批量删除
     * @param idList
     * @return
     */
    boolean removeByIds(Collection<? extends Serializable> idList);

    /**
     * 根据id修改
     * @param entity
     * @return
     */
    T updateById(T entity);

    /**
     * 根据wrapper 修改
     * @param entity
     * @param updateWrapper
     * @return
     */
    boolean update (T entity , Wrapper<T> updateWrapper);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 根据map查询
     * @param columnMap
     * @return
     */
    Collection<T> listByMap(Map<String , Object> columnMap);

    /**
     * 根据wrapper 查询一条记录
     * @param queryWrapper
     * @return
     */
    Map<String , Object> getMap(Wrapper<T> queryWrapper);

    /**
     * 根据wrapper 查询总数
     * @param queryWrapper
     * @return
     */
    int count(Wrapper<T> queryWrapper);

    /**
     * 返回总数
     * @return
     */
    default int count(){
        return count(Wrappers.emptyWrapper());
    }

    List<T> list(Wrapper<T> queryWrapper);
}
