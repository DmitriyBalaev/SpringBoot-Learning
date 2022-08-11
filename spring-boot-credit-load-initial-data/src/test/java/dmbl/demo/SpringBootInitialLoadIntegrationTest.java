package dmbl.demo;

import dmbl.demo.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@PropertySource("application_test.properties")
@Sql(value = {"/employees_schema.sql", "/import_employees.sql"})
public class SpringBootInitialLoadIntegrationTest {
    @Autowired
    private EmployeesRepository employeesRepository;

    @Test
    public void testLoadDataForTestClass(){
        assertEquals(3, employeesRepository.findAll().size());
    }

    @Test
    @Sql("/import_2_emploees.sql")
    public void testLoadDataWithSqlAnnotated(){
        assertEquals(5, employeesRepository.findAll().size());
    }
}
