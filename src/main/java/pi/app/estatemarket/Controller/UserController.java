package pi.app.estatemarket.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pi.app.estatemarket.Entities.User;
import pi.app.estatemarket.Services.IUserService;
import pi.app.estatemarket.dto.UserDTO;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Transactional
public class UserController {
    @Autowired
    private final IUserService userService;
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){ userService.createUser(user);}
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("update")
    public void updateUser( @RequestBody User user){
        userService.updateUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
    @GetMapping("getbyid/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}