package org.mybatisplus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/11 - 20:21
 */
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //将FindAll添加到集合中
        methodList.add(new FindAll());
        //注入逻辑删除
        //<!--逻辑删除-->
        methodList.add(new LogicDeleteByIdWithFill());
        return methodList;
    }

}
