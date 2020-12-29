import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author lijichen
 * @date 2020/12/11 - 16:42
 */
public class TestGenerator {
    @Test
    public void testGenerator() {
        //System.out.println(System.getProperty("user.dir"));//E:\yangyangli\Desktop\IDEA-workspace\MyBatisPlus
        //1,全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java")//生成路径
                .setAuthor("Rick")
                .setActiveRecord(true) //是否支持AR模式
                .setFileOverride(false) //文件覆盖
                .setIdType(IdType.AUTO) //生成主键策略
                .setServiceName("%sService") //设置生成的Service首字母是否带I
                .setBaseResultMap(true) //生成基本的 resultMap映射,通用查询映射
                .setBaseColumnList(true); //通用查询结果列

        //2,配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//设置数据库类型
                .setUrl("jdbc:mysql://localhost:3306/mybatis")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("root");

        //3,策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                .setTablePrefix("tb_") //表名前缀
                .setInclude("tb_employee"); //生成的表,这里指定数据库中的所有需要指定的表
        //4,包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.generator")
                .setController("controller")
                .setEntity("beans")
                .setMapper("mapper")
                .setService("service")
                .setXml("mapper");
        //5,整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6,执行
        autoGenerator.execute();//开启



    }
}
