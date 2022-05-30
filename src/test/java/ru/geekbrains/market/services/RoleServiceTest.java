package ru.geekbrains.market.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.model.Role;
import ru.geekbrains.market.repositories.RoleRepository;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void getUserRoleTest() {
        String name = "ROLE_USER";
        Role role = new Role();
        role.setName("ROLE_USER");

        Mockito.doReturn(role)
                .when(roleRepository)
                .findRoleByName(name);

        Role roleT = roleService.getUserRole();
        Assertions.assertNotNull(roleT);
        Assertions.assertEquals(role.getName(), roleT.getName());

    }
}
