package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserDAO userDao;

	@Autowired
	public UserController(UserDAO userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping
	public String addUser(@ModelAttribute("user") User user) {
		userDao.addUser(user);
		return "redirect:/users";
	}

	@GetMapping
	public String getAllUsers(Model model) {
		model.addAttribute("users", userDao.getAllUsers());
		return "getAllUsers";
	}

	@PostMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") int id) {
		userDao.deleteUser(id);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDao.getUser(id));
		return "edit";
	}

	@PostMapping("/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userDao.updateUser(id, user);
		return "redirect:/users";
	}

	@GetMapping("/{id}")
	public String getUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDao.getUser(id));
		return "getUser";
	}

//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
//	public String printWelcome(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("I'm Spring MVC-SECURITY application");
//		messages.add("5.2.0 version by sep'19 ");
//		model.addAttribute("messages", messages);
//		return "hello";
//	}
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }

}