package org.mybatisplus.metaobjecthandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author lijichen
 * @date 2020/12/11 - 21:35
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler  {

    //插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object email = getFieldValByName("email", metaObject);
        if (email == null) {
            System.out.println("=========insertFill===========");
            setFieldValByName("email","insert@fill.com",metaObject);
        }
    }

    //修改时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        Object email = getFieldValByName("email", metaObject);
        if (email == null) {
            System.out.println("=========updateFill===========");
            setFieldValByName("email","update@fill.com",metaObject);
        }
    }
}
