package com.zerostech.model;

import com.zerostech.annotation.ContainEnum;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by gjason on 2017/2/22.
 */
public class DemoBean {
    @NotBlank(message = "名字不能为空或者空串")
    @Length(min = 2, max = 10, message = "名字必须由2~10个字组成")
    private String name;

    @Past(message = "时间不能晚于当前时间")
    private Date date;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}", message = "密码必须是5~10位数字和字母的组合")
    private String password;

    @AssertTrue(message = "字段必须为真")
    private boolean valid;
    @ContainEnum(cls = TestEnum.class)
//    @NotNull(message = "sss")
    private String testEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(String testEnum) {
        this.testEnum = testEnum;
    }
}
