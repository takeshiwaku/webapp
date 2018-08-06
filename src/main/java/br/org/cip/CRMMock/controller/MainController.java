package br.org.cip.CRMMock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.org.cip.CRMMock.config.StartH2Console;
import br.org.cip.CRMMock.dao.FeriadoDao;
import br.org.cip.CRMMock.dao.FeriadoDaoImpl;
import br.org.cip.CRMMock.model.Feriado;
import br.org.cip.CRMMock.model.UserVO;
import br.org.cip.CRMMock.model.form.FeriadoForm;
import br.org.cip.CRMMock.model.theme.Config;
import br.org.cip.CRMMock.model.theme.ThemeType;

@Controller
public class MainController {

	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session, Model model) throws IOException {
		System.out.println(Config.getInstance().getThemeType().getLabel());
		
		model.addAttribute("theme", Config.getInstance().getThemeType().getLabel());
		
		UserVO user = (UserVO) session.getAttribute("usuarioLogado");
		if(user==null) {
			user = new UserVO();
		}
		model.addAttribute("user", user);
		
		System.out.println(Config.getInstance().getThemeType().getLabel());
		
		FeriadoDao feriadosDao = new FeriadoDaoImpl();
		List<Feriado> feriados = feriadosDao.getAllFeriados();
		model.addAttribute("feriados", feriados);
		
		
//		FeriadoForm feriadoForm = new FeriadoForm();
//		model.addAttribute("feriadoform", feriadoForm );
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/home2")
	public ModelAndView home2(HttpSession session, Model model) throws IOException {
		System.out.println(Config.getInstance().getThemeType().getLabel());
		
		model.addAttribute("theme", Config.getInstance().getThemeType().getLabel());
		
		UserVO user = (UserVO) session.getAttribute("usuarioLogado");
		if(user==null) {
			user = new UserVO();
		}
		model.addAttribute("user", user);
		
		System.out.println(Config.getInstance().getThemeType().getLabel());
		
		FeriadoDao feriadosDao = new FeriadoDaoImpl();
		List<Feriado> feriados = feriadosDao.getAllFeriados();
		//model.addAttribute("feriados", feriados);
		
		
		FeriadoForm feriadoForm = new FeriadoForm();
		model.addAttribute("feriadoform", feriadoForm );
		
		return new ModelAndView("home2");
	}

	@RequestMapping(value = "/dbconsole")
	public String startConsole(Model model) throws IOException {

		try {
			StartH2Console console = new StartH2Console();
			Thread t = new Thread(console);
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
	
}
