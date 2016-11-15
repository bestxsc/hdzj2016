package wl.hdzj.converter;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;
import wl.hdzj.domain.MemberVO;
import wl.hdzj.domain.NewsVO;
import wl.hdzj.entity.Member;
import wl.hdzj.entity.News;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


/** 模型转换器
 * @author lipengbiao
 */
public class ModelConverter{
    public static Function convert(@NotNull Class r){
        return (s) ->{
            try {
                Object or = r.newInstance();
                BeanUtils.copyProperties(s, or);
                return or;
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
        };
    }
    /*
    该方法通过相关VO类中非NULL属性更改实体属性
    无类型状态
     */
    public static Object setUpdateEntity(@NotNull Object e, @NotNull Object vo){
        // 遍历模型成员
        Field[] fields = vo.getClass().getDeclaredFields();
        for (Field v : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(v.getName(), vo.getClass());
                PropertyDescriptor pd2 = new PropertyDescriptor(v.getName(), e.getClass());
                //获得get方法
                Method get = pd.getReadMethod();
                //获得set方法
                Method set = pd2.getWriteMethod();
                Object oin = get.invoke(vo);
                //不为null且set方法存在时复制
                if (oin != null && set != null){
                    set.invoke(e, oin);
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException me) {
                System.out.println(me.getMessage());
            }
        }
        return e;
    }

    /*
    该方法通过相关VO类中非NULL属性组合成查询条件
    无类型状态
     */
    @SuppressWarnings("unchecked")
    public static Specification setQueryStatement(@NotNull Object vo){
        return (root, query, cb) -> {
            // 遍历模型成员
            Field[] fields = vo.getClass().getDeclaredFields();
            List<Predicate> ap = Lists.newArrayList();
            for (Field v : fields) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(v.getName(), vo.getClass());
                    //获得get方法
                    Method get = pd.getReadMethod();
                    Object oin = get.invoke(vo);
                    //不为null时串联值
                    if (oin != null){
                        ap.add(cb.like(root.get(v.getName()), "%" + oin.toString() + "%"));
                    }
                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
            }
            //谓词连接
            Predicate p1 = cb.and(ap.toArray(new Predicate[ap.size()]));
            query.where(p1);
            return null;
        };
    }
}
