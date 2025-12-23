package com.example.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Part;
import com.example.repo.PartRepository;
import com.example.service.IPartService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PartServiceImpl implements IPartService {

    @Autowired
    private PartRepository partRepo;

    @Override
    public Integer createPart(Part part) {
        if (isPartCodeExist(part.getPartCode())) {
            throw new RuntimeException("Part already exist with code: " + part.getPartCode());
        }
        return partRepo.save(part).getPartId();
    }

    @Override
    public Part getOnePart(Integer id) {
        if (id == null) {
            throw new RuntimeException("id cannot be null");
        }
        return partRepo.findById(id).orElseThrow(() -> new RuntimeException("Part not found with id: " + id));
    }

    @Override
    public List<Part> getAllPart() {
        return partRepo.findAll();
    }

    @Override
    public void updatePart(Part part) {
        getOnePart(part.getPartId());
        partRepo.save(part);
    }

    @Override
    public void deletePart(Integer id) {
        getOnePart(id);
        partRepo.deleteById(id);
    }

    @Override
    public byte[] excelExport() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("partId");
            row0.createCell(1).setCellValue("partCode");
            row0.createCell(2).setCellValue("partLength");
            row0.createCell(3).setCellValue("partWidth");
            row0.createCell(4).setCellValue("partHeight");
            row0.createCell(5).setCellValue("partBaseCost");
            row0.createCell(6).setCellValue("partBaseCurrency");

            List<Part> partList = partRepo.findAll();
            int rowIndex = 1;

            for (Part part : partList) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(part.getPartId());
                row.createCell(1).setCellValue(part.getPartCode());
                row.createCell(2).setCellValue(part.getPartLength());
                row.createCell(3).setCellValue(part.getPartWidth());
                row.createCell(4).setCellValue(part.getPartHeight());
                row.createCell(5).setCellValue(part.getPartBaseCost());
                row.createCell(6).setCellValue(part.getPartBaseCurrency());
            }

            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    @Override
    public byte[] pdfExport() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();

        PdfPTable table = new PdfPTable(7);

        table.addCell("partId");
        table.addCell("partCode");
        table.addCell("partLength");
        table.addCell("partWidth");
        table.addCell("partHeight");
        table.addCell("partBaseCost");
        table.addCell("partBaseCurrency");

        List<Part> partList = partRepo.findAll();
        for (Part part : partList) {
            table.addCell(part.getPartId().toString());
            table.addCell(part.getPartCode());
            table.addCell(part.getPartLength().toString());
            table.addCell(part.getPartWidth().toString());
            table.addCell(part.getPartHeight().toString());
            table.addCell(part.getPartBaseCost().toString());
            table.addCell(part.getPartBaseCurrency());
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    @Override
    public boolean isPartCodeExist(String partCode) {
        return partRepo.existsByPartCode(partCode);
    }

}
