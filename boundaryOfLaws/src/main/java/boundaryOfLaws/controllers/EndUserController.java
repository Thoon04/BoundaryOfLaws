package boundaryOfLaws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import boundaryOfLaws.models.EndUser;
import boundaryOfLaws.persistance.EndUserRepository;

@Controller

public class EndUserController {
	@Autowired
		EndUserRepository enduserRepo;

		@RequestMapping("endusers")
		public String diaplayAll(ModelMap map) {
			List<EndUser> endusers = enduserRepo.getAll();
			//System.out.println("authors :" + authors.size());
			map.addAttribute("endusers", endusers);
			return "display_endusers";
		}

		@GetMapping("addauthor")
		public ModelAndView addAuthor() {
			return new ModelAndView("add_author", "author", new EndUser());

		}

		@PostMapping("addauthor")
		public String addAuthor(@ModelAttribute("author") @Validated EndUser enduser, BindingResult bResult, ModelMap map) {
			if (bResult.hasErrors()) {
				return "add_author";

			}
			int rs = enduserRepo.add(enduser);
			if (rs == 0) {
				map.addAttribute("error_msg", "Database something wrong");
				return "add_author";

			} else {
				return "redirect:/authors";
			}
		}

		@GetMapping("editenduser/{id}")
		public ModelAndView editAuthor(@PathVariable int id) {
			EndUser author = enduserRepo.getById(id);
			return new ModelAndView("edit_author", "author", author);

		}

		@PostMapping("editauthor")

		public String editAuthor(@ModelAttribute("author") @Validated EndUser enduser, BindingResult bResult, ModelMap map) {
			int rs = enduserRepo.edit(enduser);
			if (rs == 0) {
				map.addAttribute("error_msg", "SQL Update Error");
				map.addAttribute("enduser", enduser);
				return "edit_author";
			} else {
				return "redirect:/authors";
			}
		}

		@GetMapping("/deleteenduser/{id}")
		public String deleteEndUser(@PathVariable int id) {

			enduserRepo.delete(id);
			return "welcome";
		}

	}


