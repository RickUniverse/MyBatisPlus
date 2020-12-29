import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class TestPage {

    private static ApplicationContext applicationContext = null;


    private static EmployeeMapper employeeMapper = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeMapper = (EmployeeMapper) applicationContext.getBean("employeeMapper");
    }

    //条件构造器
    @Test
    public void testWrapperUpdateAndDelete() throws SQLException {
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<>(1, 1), null);
        employeePage.getRecords().forEach(System.out::println);
        employeePage.hasPrevious();//是否有上一页
        employeePage.getPages();
    }
}
