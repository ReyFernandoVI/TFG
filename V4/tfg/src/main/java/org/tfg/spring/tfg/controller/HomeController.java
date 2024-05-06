package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfg.spring.tfg.domain.Usuario;
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
	private UsuarioService usuarioService;

    @GetMapping("/")
    public String home(
        ModelMap m
    ) {
        m.put("view","home/home");
        return "_t/frame";
    }

	@GetMapping("/admin")
    public String admin(
        ModelMap m
    ) {
        m.put("view","home/admin");
        return "_t/frame";
    }

	@GetMapping("/pricing")
    public String pricing(
        ModelMap m
    ) {
        m.put("view","home/pricing");
        return "_t/frame";
    }

	@GetMapping("/features")
    public String features(
        ModelMap m
    ) {
        m.put("view","home/features");
        return "_t/frame";
    }

	@GetMapping("/faqs")
    public String faqs(
        ModelMap m
    ) {
        m.put("view","home/faqs");
        return "_t/frame";
    }

	@GetMapping("/about")
    public String about(
        ModelMap m
    ) {
        m.put("view","home/about");
        return "_t/frame";
    }

	@GetMapping("/news")
    public String news(
        ModelMap m
    ) {
        m.put("view","home/news");
        return "_t/frame";
    }

	@GetMapping("/catalogue")
    public String catalogue(
        ModelMap m
    ) {
        m.put("view","home/catalogue");
        return "_t/frame";
    }

	@GetMapping("/vipzone")
    public String vipzone(
        ModelMap m
    ) {
        m.put("view","home/vipzone");
        return "_t/frame";
    }

    @GetMapping("/info")
	public String info(HttpSession s, ModelMap m) {

		String mensaje = s.getAttribute("_mensaje") != null ? (String) s.getAttribute("_mensaje")
				: "Pulsa para volver a home";
		String severity = s.getAttribute("_severity") != null ? (String) s.getAttribute("_severity") : "info";
		String link = s.getAttribute("_link") != null ? (String) s.getAttribute("_link") : "/";

		s.removeAttribute("_mensaje");
		s.removeAttribute("_severity");
		s.removeAttribute("_link");

		m.put("mensaje", mensaje);
		m.put("severity", severity);
		m.put("link", link);

		m.put("view", "/_t/info");
		return "/_t/frame";
	}

	@GetMapping("/signup")
	public String signup(
		ModelMap m
	) {
		//m.put("view","home/signup");
		return "redirect:/usuario/c";
	}

	/*@PostMapping("/signup")
	public String signupPost(
		@RequestParam("nombre") String nombre,
		@RequestParam("dni") String dni,
		@RequestParam("mail") String mail,
		@RequestParam("contraseña") String contraseña,
		HttpSession s,
		ModelMap m
	) throws DangerException {
		try {
			Usuario usuario = usuarioService.save(nombre, dni, mail, contraseña);
			s.setAttribute("usuario", usuario);
		}
		catch (Exception e) {
			e.printStackTrace();
			PRG.error("Usuario o contraseña incorrectos");
		}
		return "_t/frame";
	}
*/
	@GetMapping("/init")
    public String crearAdmin() {
		usuarioService.save(null, "-1", null, "admin", null);
		usuarioService.setAdmin("-1");
		return "redirect:/";
    }

    @GetMapping("/login")
	public String login(
		ModelMap m
	) {
		m.put("view","home/login");
		return "_t/frame";
	}
	@PostMapping("/login")
	public String loginPost(
		@RequestParam("nombre") String nombre,
		@RequestParam("contraseña") String contraseña,
		HttpSession s,
		ModelMap m
	) throws DangerException {
		try {
			Usuario usuario = usuarioService.login(nombre,contraseña);
			s.setAttribute("usuario", usuario);
		}
		catch (Exception e) {
			e.printStackTrace();
			PRG.error("Usuario o contraseña incorrectos");
		}
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(
		HttpSession s
	) {
		//s.setAttribute("usuario", null);
		s.invalidate();
		return "redirect:/";
	}
	
}