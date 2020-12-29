import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mchange.v1.util.MapUtils;
import org.junit.Test;
import org.mybatisplus.dao.EmployeeMapper;
import org.mybatisplus.pojo.Employee;
import org.mybatisplus.utlils.MapUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

import static org.mybatisplus.utlils.MapUtil.kv;

/**
 * @author lijichen
 * @date 2020/12/10 - 14:30
 */
public class TestMP {

    private static ApplicationContext applicationContext = null;


    private static EmployeeMapper employeeMapper = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeMapper = (EmployeeMapper) applicationContext.getBean("employeeMapper");
    }

    //条件构造器
    @Test
    public void testWrapperUpdateAndDelete() throws SQLException {
        employeeMapper.update( new Employee(1,"name",null,null),
                new UpdateWrapper<Employee>().
                        eq("id",1).eq("last_name","name"));
        int count = employeeMapper.delete(new UpdateWrapper<Employee>().
                eq("id", 10044));
        System.out.println(count);
    }
    //条件构造器
    @Test
    public void testWrapper() throws SQLException {
        IPage<Employee> employeeIPage = employeeMapper.selectPage(new Page<Employee>(1, 2), new QueryWrapper<Employee>().
                eq("last_name", "name").
                between("id", 1, 200));
        employeeIPage.getRecords().forEach(System.out::println);
        System.out.println(employeeIPage.getPages());

        employeeMapper.selectList(new QueryWrapper<Employee>().
                like("last_name",1).
                or().
                in("id",Arrays.asList(1,2,3,4,5)).
                orderByDesc("id","last_name").
                last("limit 1,5")).forEach(System.out::println);//last是在末尾拼接sql语句

    }
    //通用的删除
    @Test
    public void testDelete() throws SQLException {
        Employee employee = new Employee(1,"name",null,null);
//        employeeMapper.deleteById();
//        int i = employeeMapper.deleteByMap(MapUtil.hashMap(
//                kv("last_name","name"),
//                kv("gender","0")
//        ));
//        employeeMapper.deleteBatchIds(Arrays.asList(1,2,3,4));
    }
    @Test
    public void testSelect() throws SQLException {
        System.out.println(employeeMapper.selectById(1));
        Employee employee = new Employee(1,"name",null,null);
        //按照多个id查询
        employeeMapper.selectBatchIds(Arrays.asList(1,2,3,4)).
                forEach(System.out::println);

        //根据数据库字段名查询
        employeeMapper.selectByMap(MapUtil.hashMap(
                kv("last_name","name"),
                kv("gender","0")
        )).forEach(System.out::println);

        //分页
        /*IPage iPage = employeeMapper.selectPage(new Page(1, 2), null);
        iPage.orders().forEach(System.out::println);*/
    }
    @Test
    public void testUpdate() throws SQLException {
        Employee employee = new Employee(1,"name",null,null);
        System.out.println(employeeMapper.updateById(employee));
    }
    @Test
    public void testCRUD() throws SQLException {
        Employee employee = new Employee(null,"name",1,"email");
        int insert = employeeMapper.insert(employee);
        System.out.println(insert);
        System.out.println(employee.getId());
    }
    @Test
    public void testMP() throws SQLException {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }
}
