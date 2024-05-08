package com.example.data_jpa_vintage.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.data_jpa_vintage.model.Cliente;
import com.example.data_jpa_vintage.paginator.PageRender;
import com.example.data_jpa_vintage.service.IClienteService;
import com.example.data_jpa_vintage.service.IUploadFileService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash, Locale locale) {

        // Cliente cliente = clienteService.findOne(id);
        Cliente cliente = clienteService.fetchByIdWithFacturas(id);

        if (cliente == null) {
            flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", messageSource.getMessage("text.cliente.detalle.titulo", null, locale).concat(": ")
                .concat(cliente.getName()));
        return "ver";
    }

    @GetMapping("/listar")
    public String listar(Model model, @RequestParam(name = "page", defaultValue = "0") int page, Locale locale) {

        Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page, 5));
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";

    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model, Locale locale) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", messageSource.getMessage("text.cliente.listar.titulo", null, locale));

        return "form";
    }

    @RequestMapping("/form/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash, Locale locale) {
        Cliente cliente = null;

        if (id > 0) {

            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.id.error", null, locale));

            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", messageSource.getMessage("text.cliente.form.titulo.editar", null, locale));
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
            @RequestParam("file") MultipartFile foto, Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", messageSource.getMessage("text.cliente.form.titulo", null, locale));

            return "form";
        }

        if (!foto.isEmpty()) {

            if (cliente.getId() != null
                    && cliente.getId() > 0
                    && cliente.getPhoto() != null
                    && cliente.getPhoto().length() > 0) {

                uploadFileService.delete(cliente.getPhoto());

            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            flash.addFlashAttribute("info",
                    messageSource.getMessage("text.cliente.flash.foto.subir.success", null, locale) + "'"
                            + uniqueFilename + "'");
            cliente.setPhoto(uniqueFilename);
        }

        String mensajeFlash = (cliente.getId() != null)
                ? messageSource.getMessage("text.cliente.flash.editar.success", null, locale)
                : messageSource.getMessage("text.cliente.flash.crear.success", null, locale);

        clienteService.guardar(cliente);
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash, Locale locale) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success",
                    messageSource.getMessage("text.cliente.flash.eliminar.success", null, locale));

            if (uploadFileService.delete(cliente.getPhoto())) {
                String mensajeFotoEliminar = String.format(
                        messageSource.getMessage("text.cliente.flash.foto.eliminar.success", null, locale),
                        cliente.getPhoto());
                flash.addFlashAttribute("info", mensajeFotoEliminar);
            }

        }
        return "redirect:/listar";
    }

}
