package com.example.data_jpa_vintage.view.xlsx;

import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.data_jpa_vintage.model.Factura;
import com.example.data_jpa_vintage.model.ItemFactura;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//https://poi.apache.org/components/spreadsheet/examples.html

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String fileName = "Factura_Spring.xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        MessageSourceAccessor mensajes = getMessageSourceAccessor();

        Factura factura = (Factura) model.get("factura");

        Sheet sheet = workbook.createSheet("Factura Spring");

        sheet.setColumnWidth(0, 20 * 256);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(mensajes.getMessage("text.factura.ver.datos.cliente"));

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getName() + " " + factura.getCliente().getSurname());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getEmail());

        sheet.createRow(4).createCell(0).setCellValue(mensajes.getMessage("text.factura.ver.datos.factura"));
        sheet.createRow(5).createCell(0)
                .setCellValue(mensajes.getMessage("text.cliente.factura.folio") + factura.getId());
        sheet.createRow(6).createCell(0)
                .setCellValue(mensajes.getMessage("text.cliente.factura.descripcion") + factura.getDescripcion());
        sheet.createRow(7).createCell(0)
                .setCellValue(mensajes.getMessage("text.cliente.factura.fecha") + factura.getCreateAt());

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row header = sheet.createRow(9);

        header.createCell(0).setCellValue(mensajes.getMessage("text.factura.form.item.nombre"));
        header.createCell(1).setCellValue(mensajes.getMessage("text.factura.form.item.precio"));
        header.createCell(2).setCellValue(mensajes.getMessage("text.factura.form.item.cantidad"));
        header.createCell(3).setCellValue(mensajes.getMessage("text.factura.form.item.total"));

        header.getCell(0).setCellStyle(theaderStyle);
        header.getCell(1).setCellStyle(theaderStyle);
        header.getCell(2).setCellStyle(theaderStyle);
        header.getCell(3).setCellStyle(theaderStyle);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        int rowNum = 10;
        for (ItemFactura item : factura.getItems()) {
            Row fila = sheet.createRow(rowNum++);
            cell = fila.createCell(0);
            cell.setCellValue(item.getProducto().getNombre());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(item.getProducto().getPrecio());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(item.getCantidad());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(item.calcularImporte());
            cell.setCellStyle(tbodyStyle);

        }
        Row filaTotal = sheet.createRow(rowNum);
        cell = filaTotal.createCell(2);
        cell.setCellValue(mensajes.getMessage("text.factura.form.total"));
        // theaderStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.index);
        cell.setCellStyle(theaderStyle);

        cell = filaTotal.createCell(3);
        cell.setCellStyle(tbodyStyle);
        cell.setCellValue(factura.getTotal());
    }

}
