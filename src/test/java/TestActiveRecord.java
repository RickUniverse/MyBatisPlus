import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import org.junit.Test;
import org.mybatisplus.dao.EmployeeMapper;
import org.mybatisplus.pojo.Employee;
import org.mybatisplus.utlils.MapUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lijichen
 * @date 2020/12/10 - 20:09
 */
public class TestActiveRecord {

    private static ApplicationContext applicationContext = null;


    private static EmployeeMapper employeeMapper = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeMapper = (EmployeeMapper) applicationContext.getBean("employeeMapper");
    }

    @Test
    public void testActiveRecord() {
        Employee employee = new Employee(null,"王文峰",1,"sdfasdf");
        boolean insert = employee.insert();
        System.out.println(employee.getId());
        System.out.println(employee.updateById());

        Employee employee1 = employee.selectById();
        employee.selectList(new QueryWrapper<Employee>().
                eq("id","1")).forEach(System.out::println);
        employee.selectAll();
        System.out.println("============--==============");
        //使用lambda表达式获取,不用手动设置 列名
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = Wrappers.<Employee>lambdaQuery();
        employeeLambdaQueryWrapper.
                likeLeft(Employee::getLastName,"name");
        employee.selectList(employeeLambdaQueryWrapper).
                forEach(System.out::println);
        System.out.println("============--==============");

        //查询条数
        Integer integer = employee.selectCount(new QueryWrapper<Employee>().
                allEq(
                        MapUtil.hashMap(
                                MapUtil.kv("last_name", "name"),
                                MapUtil.kv("gender", 1)
                        )
                 ));
        //分页
        employee.selectPage(new Page<>(1, 1), null).
                getRecords().
                forEach(System.out::println);
        System.out.println("==========================");
        System.out.println(integer);
        System.out.println("==========================");
    }
}
