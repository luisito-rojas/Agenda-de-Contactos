package agenda_contactos.controller;

import agenda_contactos.entity.Contacto;
import agenda_contactos.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoController {

    //inyectamos la clase repositorio
    @Autowired
    private ContactoRepository contactoRepository;

    //este metodo nos va a mostrar todos los registros en la pagina index
    /*
    La clase Model de org.springframework.ui.Model en Spring Framework se utiliza para pasar datos desde un controlador
    a una vista (por ejemplo, una plantilla HTML con Thymeleaf, JSP, etc.).
    * */
    @GetMapping({"/", "" })
    public String verPaginaInicio(Model modelo){
        List<Contacto> contactos = contactoRepository.findAll();
        modelo.addAttribute("contactos", contactos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrarContacto(Model modelo){
        modelo.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String guardarContacto(@Validated  Contacto contacto, BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "nuevo";
        }
        contactoRepository.save(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con exito");
        return "redirect:/";
    }


//getById: todavía existe, pero está en desuso a partir de Spring Data JPA 2.5+, ya que
//getById() usa lazy loading (una referencia proxy), lo que puede causar errores si se accede a propiedades del objeto
// fuera de una transacción.
    @GetMapping("/{id}/editar")
    public String mostrarFormularioDeEditarContacto(@PathVariable Integer id, Model modelo) {
        Contacto contacto = contactoRepository.getById(id);
        //Contacto contacto = contactoRepository.findById(id).get();
        modelo.addAttribute("contacto", contacto);
        return "nuevo";
    }


    @PostMapping("/{id}/editar")
    public String actualizarContacto(@PathVariable Integer id,@Validated Contacto contacto,BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {
        Contacto contactoDB = contactoRepository.getById(id);
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "nuevo";
        }


        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());


        contactoRepository.save(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido actualizado correctamente");
        return "redirect:/";
    }


    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id,RedirectAttributes redirect) {
        Contacto contacto = contactoRepository.getById(id);
        contactoRepository.delete(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
        return "redirect:/";
    }


}
