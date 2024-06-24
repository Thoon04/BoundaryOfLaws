package boundaryOfLaws.controllers;

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

@Controller
public class EndUserController {
		@Autowired
		AuthorReposistory authorRepo;
		
		@RequestMapping("authors")
		public String displayAll(ModelMap map) {
			
			List<EndUser> authors = authorRepo.getAll();
			map.addAttribute("authors" , authors);
			return "display_author";
		}
		@GetMapping("addauthor")
		public ModelAndView addauthor() {
			return new ModelAndView("add_author", "author" , new EndUser());
		}
		
		@PostMapping("addauthor")
		public String addAuthor(@ModelAttribute("author")@Validated EndUser author,BindingResult bResult,ModelMap map) {
			if(bResult.hasErrors()) {
				return "add_author";
			}
			
			int rs = authorRepo.add(author);
			if(rs==0) {
				map.addAttribute("author" , author);
				map.addAttribute("error_msg","Database Something wrong");
				return "add_author";
			}else {
				return "redirect:/authors";
			}
		
		}
		@GetMapping("editauthor/{id}")
		public ModelAndView editAuthor(@PathVariable int id) {
			EndUser author = authorRepo.getById(id);
			return new ModelAndView ("edit_author","author",author);
		}
		@PostMapping("editauthor")
		public String editAuthor(@ModelAttribute("author")@Validated EndUser author,BindingResult bResult,ModelMap map) {
			if(bResult.hasErrors()) {
				return "edit_author";
			}
			
			int rs = authorRepo.edit(author);
			if(rs==0) {
				map.addAttribute("error_msg","In Updating, Database Something wrong");
				return "edit_author";
			}else {
				return "redirect:/authors";
			}
		}
		@GetMapping("deleteauthor/{id}")
		public String deleteAuthor(@PathVariable int id) {
			authorRepo.delete(id);
			return "redirect:/authors";
		}
	}


