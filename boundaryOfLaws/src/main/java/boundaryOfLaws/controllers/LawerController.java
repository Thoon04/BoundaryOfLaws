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

import boundaryOfLaws.models.Lawer;
import boundaryOfLaws.persistance.LawerRepository;

@Controller
public class LawerController {
	@Autowired
	LawerRepository lawerRepo;

	@RequestMapping("lawers")
	public String displayAll(ModelMap map) {
		List<Lawer> lawers = lawerRepo.getAll();
		map.addAttribute("lawers", lawers);
		return "display_lawers";
	}

	@GetMapping("addlawer")
	public ModelAndView addLawer() {
		return new ModelAndView("add_lawer", "lawer", new Lawer());

	}

	@PostMapping("addlawer")
	public String addLawer(@ModelAttribute("lawer") @Validated Lawer lawer, BindingResult bResult, ModelMap map) {
		if (bResult.hasErrors()) {
			return "add_lawer";

		}
		int rs = lawerRepo.add(lawer);
		if (rs == 0) {
			map.addAttribute("error_msg", "Database something wrong");
			return "add_lawer";

		} else {
			return "redirect:/lawers";
		}
	}

	@GetMapping("editlawer/{id}")
	public ModelAndView editLawer(@PathVariable int id) {
		Lawer lawer = lawerRepo.getById(id);
		return new ModelAndView("edit_lawer", "lawer",lawer);

	}

	@PostMapping("editlawer")

	public String editLawer(@ModelAttribute("lawer") @Validated Lawer lawer, BindingResult bResult, ModelMap map) {
		int rs = lawerRepo.edit(lawer);
		if (rs == 0) {
			map.addAttribute("error_msg", "SQL Update Error");
			map.addAttribute("enduser", lawer);
			return "edit_lawer";
		} else {
			return "redirect:/lawers";
		}
	}

	@GetMapping("/deletelawer/{id}")
	public String deleteLawer(@PathVariable int id) {

		lawerRepo.delete(id);
		return "welcome";
	}

}

