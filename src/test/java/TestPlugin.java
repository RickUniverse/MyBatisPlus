import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.mybatisplus.dao.EmployeeMapper;
import org.mybatisplus.pojo.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/11 - 19:17
 */
public class TestPlugin {
    private static ApplicationContext applicationContext = null;


    private static EmployeeMapper employeeMapper = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeMapper = (EmployeeMapper) applicationContext.getBean("employeeMapper");
    }

    //测试Oracle Sequence
    @Test
    public void testPluginSequence(){
        Employee employee = new Employee(null, "sadf", 1, null);
        employee.insert();
        employee.updateById();
    }
    //测试公共字段填充
    @Test
    public void testPluginFill(){
        Employee employee = new Employee(null, "sadf", 1, null);
        employee.insert();
        employee.updateById();
    }
    //测试逻辑删除
    @Test
    public void testPluginLogicDelete(){
        employeeMapper.deleteById(4);
        Employee employee = employeeMapper.selectById(4);
        System.out.println(employee);
    }
    //测试sql注入
    @Test
    public void testPluginInjector(){
        List<Employee> all = employeeMapper.findAll();
        System.out.println(all);

    }
    //测试乐观锁插件
    @Test
    public void testPluginOptimisticLock(){
        Employee employee = new Employee(null, "sadf", 1, "erere");
        employee = employeeMapper.selectById(1);
        employee.setLastName("ssd");
        employee.setVersion(1);//如果跟数据库中记录的数据一致,则就会更行成功
        //执行更新操作
        int update = employeeMapper.updateById(employee);
        System.out.println(update);//如果是0表示没有更行成功,乐观锁生效
        Employee employee1 = new Employee();
        employee1.setLogicFlag(1);
        employeeMapper.update(employee1,null);
    }

    //测试性能分析插件
    @Test
    public void testPlugin(){
        employeeMapper.insert(new Employee(null,"sadf",1,"erere"));
    }
}
