package com.zerostech.validation;

import com.zerostech.model.DemoBean;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.ValidationException;

/**
 * Created by gjason on 2017/2/22.
 */
public class TestValidation {
    @Test
    public void singlePropertyValidationTest() {
        DemoBean demoBean = new DemoBean();
        demoBean.setName("");
        demoBean.setTestEnum("AP");
        ValidationResult result = ValidationUtils.validateProperty(demoBean, "testEnum");
        Assert.assertTrue(!result.isHasErrors());
    }

    @Test
    public void entityValidationTest() {
        DemoBean demoBean = new DemoBean();
        ValidationResult result = ValidationUtils.validateEntity(demoBean);
        System.out.println(result);
        Assert.assertTrue(result.isHasErrors());
    }

    @Test
    public void validateWithExceptionTest() {
        DemoBean demoBean = new DemoBean();
        ValidationResult result = null;
        try {
            ValidationUtils.validateWithException(demoBean);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        Assert.assertTrue(result.isHasErrors());
    }
}
