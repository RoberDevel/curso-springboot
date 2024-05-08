package com.example.data_jpa_vintage.view.pdf;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.data_jpa_vintage.model.Factura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    // pdf seccion 39
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // key factura viene de model.addAttribute("factura", factura); en
        // FacturaController.java
        Factura factura = (Factura) model.get("factura");

        // Forma 1
        MessageSourceAccessor message = getMessageSourceAccessor();
        // Forma 2
        Locale locale = localeResolver.resolveLocale(request);
        // messageSource.getMessage("text.factura.ver.datos.factura", null, locale)

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = null;
        cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.ver.datos.cliente", null, locale)));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);
        tabla.addCell(factura.getCliente().getName() + " " + factura.getCliente().getSurname());
        tabla.addCell(factura.getCliente().getEmail());

        PdfPTable tabla2 = new PdfPTable(1);
        tabla2.setSpacingAfter(20);

        cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.ver.datos.factura", null, locale)));
        cell.setBackgroundColor(new Color(194, 230, 205));
        cell.setPadding(8f);
        tabla2.addCell(cell);
        tabla2.addCell(message.getMessage("text.cliente.factura.folio") + factura.getId());
        tabla2.addCell(message.getMessage("text.cliente.factura.descripcion") + factura.getDescripcion());
        tabla2.addCell(message.getMessage("text.cliente.factura.fecha") + factura.getCreateAt());

        PdfPTable tabla3 = new PdfPTable(4);
        tabla3.setWidths(new float[] { 3.5f, 1, 1, 1 });
        tabla3.addCell(message.getMessage("text.factura.form.item.nombre"));
        tabla3.addCell(message.getMessage("text.factura.form.item.precio"));
        tabla3.addCell(message.getMessage("text.factura.form.item.cantidad"));
        tabla3.addCell(message.getMessage("text.factura.form.item.total"));

        factura.getItems().forEach(item -> {
            tabla3.addCell(item.getProducto().getNombre());
            tabla3.addCell(item.getProducto().getPrecio().toString());
            PdfPCell cell1 = new PdfPCell(new Phrase(item.getCantidad().toString()));
            cell1.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            tabla3.addCell(cell1);
            tabla3.addCell(item.calcularImporte().toString());
        });

        cell = new PdfPCell(new Phrase(message.getMessage("text.factura.form.total")));
        cell.setColspan(3);
        cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
        tabla3.addCell(cell);
        tabla3.addCell(factura.getTotal().toString());

        document.add(tabla);
        document.add(tabla2);
        document.add(tabla3);
    }

}
