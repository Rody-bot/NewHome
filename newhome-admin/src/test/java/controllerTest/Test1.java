package controllerTest;

import com.roy.NewhomeAdminApplication;
import com.roy.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;



@SpringBootTest(classes = NewhomeAdminApplication.class)
public class Test1 {

    @Autowired
    private EmployeeService employeeService;
//    @Test
//    public void test1(){
//        System.out.println(employeeService.selectTest("admin"));
//    }
}
