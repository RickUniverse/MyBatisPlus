package org.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @author lijichen
 * @date 2020/12/10 - 13:54
 */
//指定数据库
//@TableName(value = "tb_employee")
//Oracle
@TableName(value = "EMPLOYEE_TABLE")
//Orcale的主键策略,可以继承一个带该序列的类,共用同一个序列
@KeySequence(value = "employees_seq",clazz = Integer.class)
//extends Model<Employee> :实现ＡＲ操作　即ActionRecord
public class Employee extends Model<Employee> implements Serializable {

    /*
    * 使用AR需要制定当前实体类的主键属性
    * */

    @Override
    protected Serializable pkVal() {
        return id;
    }

    /*
    * TableId：指定主键id
    *   value：表中字段名
    *   type：id的生成策略，auto为数据库自增
    * */
    /*@TableId(value = "id", type = IdType.AUTO)*/
    @TableId(type = IdType.INPUT)//设置oracle使用到的主键策略
    private Integer id;

    //数据库中表对应的名字
    //@TableField(value = "last_name")//mysql
    @TableField(value = "NAME")//Oracle
    private String lastName;

    @TableField(exist = false)//Oracle 表中没有该字段
    private Integer gender;

    @TableField(fill = FieldFill.INSERT_UPDATE)//公共字段填充
    //INSERT_UPDATE:插入和更新时填充字段
    private String email;

    /*
    * 字段需要加上version表示实行乐观锁
    * */
    @Version
    private Integer version;

    //配置该xml后不用加注解:<property name="logicDeleteField" value="logicFlag"/>
    //@TableLogic//配置逻辑删除属性
    @TableField(exist = false)//Oracle中也没有
    private Integer logicFlag;



    public Integer getLogicFlag() {
        return logicFlag;
    }

    public void setLogicFlag(Integer logicFlag) {
        this.logicFlag = logicFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    //exist = false表示不是数据库中表的字段 ,true表示是
    @TableField(exist = false)
    private double salary;

    public Employee() {
    }

    public Employee(Integer id, String lastName, Integer gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
