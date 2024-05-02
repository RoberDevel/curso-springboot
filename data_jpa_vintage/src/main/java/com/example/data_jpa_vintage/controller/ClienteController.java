package com.example.data_jpa_vintage.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.data_jpa_vintage.model.Cliente;
import com.example.data_jpa_vintage.paginator.PageRender;
import com.example.data_jpa_vintage.service.IClienteService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(id);

        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        System.out.println(cliente.toString());
        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getName());
        return "ver";
    }

    @GetMapping("/listar")
    public String listar(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page, 5));
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";

    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");

        return "form";
    }

    @RequestMapping("/form/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente cliente = null;

        if (id > 0) {

            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero");

            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
            @RequestParam("file") MultipartFile foto) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");

            return "form";
        }

        if (!foto.isEmpty()) {

            String roothString = "C://Users//roberto.bravo//Desktop//proy//curso-springboot//almacen";
            try {
                byte[] bytes = foto.getBytes();
                Path rutaCompleta = Paths.get(roothString + "//" + foto.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
                cliente.setPhoto(foto.getOriginalFilename());
            } catch (IOException e) {
                // TODO Auto-generated catch block

                flash.addFlashAttribute("error", "Hubo un problema subiendo '" + foto.getOriginalFilename() + "'");
                e.printStackTrace();
            }
        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";

        System.out.println(cliente.toString());

        clienteService.guardar(cliente);
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {

            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return "redirect:/listar";
    }

}
