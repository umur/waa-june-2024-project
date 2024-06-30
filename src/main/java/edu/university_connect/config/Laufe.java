package edu.university_connect.config;

import edu.university_connect.model.entity.Action;
import edu.university_connect.model.entity.Role;
import edu.university_connect.model.entity.User;
import edu.university_connect.repository.ActionRepository;
import edu.university_connect.repository.RoleRepository;
import edu.university_connect.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//@Component
public class Laufe implements CommandLineRunner {
    ActionRepository actionRepository;
    RoleRepository roleRepository;
    UserRepository userRepository;

    public Laufe(ActionRepository actionRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.actionRepository = actionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Action action1=new Action("Create User","create_user","Create User");
        Action action2=new Action("Read User","view_user","Read User");
        Action action3=new Action("Update User","modify_user","Update User");
        Action action4=new Action("Delete User","delete_user","Delete User");
        Action action5=new Action("Read User List","view_user_list","Read User List");

        Action action11 = new Action("Create Role", "create_role", "Create new roles");
        Action action12 = new Action("Read Role", "view_role", "View existing roles");
        Action action13 = new Action("Update Role", "modify_role", "Modify role details");
        Action action14 = new Action("Delete Role", "delete_role", "Delete roles");
        Action action15=new Action("Read Action List","view_role_list","Read role List");

        Action action6 = new Action("Create Action", "create_action", "Create new actions");
        Action action7 = new Action("Read Action", "view_action", "View existing actions");
        Action action8 = new Action("Update Action", "modify_action", "Modify action details");
        Action action9 = new Action("Delete Action", "delete_action", "Delete actions");
        Action action10=new Action("Read Action List","view_action_list","Read action List");


        actionRepository.saveAll(List.of(action1,action2,action3,action4,action5 ,action6,action7,action8,action9,action10 ,action11,action12,action13,action14,action15));
        Set<Action> actionSet =new HashSet<Action>(actionRepository.findAll());
        Role role=new Role("SuperUser","superuser","SuperUser", actionSet.stream().map(Action::getCode).collect(Collectors.toSet()));
        roleRepository.save(role);
      Optional<Role> roleOpt= roleRepository.findByCodeIgnoreCase("superuser");
      if(roleOpt.isPresent()){
          Set<Role> roles=new HashSet<>();
          roles.add(roleOpt.get());
          // Create an encoder with strength 10
          BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
          String password = encoder.encode("kush");
          User user=new User("kush",password,"kushraj1204@gmail.com");
          user.setEnabled(true);
          user.setRoles(roles);
          userRepository.save(user);
      }
    }
}
