package com.example.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Uom;
import com.example.repo.UomRepository;
import com.example.service.IUomService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@RestController
@RequestMapping("/uom")
public class UomRestController {
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private UomRepository uomRepo;
	
	@PostMapping("/create")
	public ResponseEntity<String> createUom(@RequestBody Uom uom){
		return new ResponseEntity<>("Uom created with id: "+uomService.createUom(uom), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Uom>> getAllUom(){
		return new ResponseEntity<>(uomService.getAllUom(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUom(@RequestBody Uom uom){
		uomService.updateUom(uom);
		return new ResponseEntity<>("Uom updated with id: "+uom.getUomId(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUom(@PathVariable Integer id){
		uomService.deleteUom(id);
		return new ResponseEntity<>("Uom deleted for id: "+id, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
    public ResponseEntity<byte[]> downloadExcel() {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("UOM Sheet");

            // Header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("UOM ID a big column name");
            header.createCell(1).setCellValue("Type");
            header.createCell(2).setCellValue("Model");
            header.createCell(3).setCellValue("Description");

            List<Uom> uomList = uomRepo.findAll();
            int rowIndex = 1;
            
            for(Uom uom : uomList) {
            	Row row = sheet.createRow(rowIndex++);
            	row.createCell(0).setCellValue(uom.getUomId());
            	row.createCell(1).setCellValue(uom.getUomType());
            	row.createCell(2).setCellValue(uom.getUomModel());
            	row.createCell(3).setCellValue(uom.getUomDescription());
            }

			for (int i = 0; i < 4; i++) {
				sheet.autoSizeColumn(i);
			}

            workbook.write(out);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(
                MediaType.parseMediaType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                )
            );
            
            // setting filename (Content-Disposition: attachment; filename="uom.xlsx")
            headers.setContentDisposition(
                ContentDisposition.attachment()
                    .filename("uom.xlsx")
                    .build()
            );

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(out.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/excelbasic")
    public ResponseEntity<byte[]> downloadBasicExcel() throws IOException {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	Workbook workbook = new XSSFWorkbook();
    	Sheet sheet = workbook.createSheet("sample1");
    	Row row0 = sheet.createRow(0);
    	row0.createCell(0).setCellValue("Name");
    	row0.createCell(1).setCellValue("Age");
    	
    	Row row1 = sheet.createRow(1);
    	row1.createCell(0).setCellValue("Akhil");
    	row1.createCell(1).setCellValue("18");
    	
    	//write workbook to output stream
    	workbook.write(out);
    	
    	//set workbook name
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentDisposition(ContentDisposition.attachment().filename("akhil.xlsx").build());
    	
		return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
    
	@GetMapping("/pdfbasic")
    public ResponseEntity<byte[]> downloadPdf() {

    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	Document document = new Document(PageSize.A4);
    	PdfWriter.getInstance(document, out);
    	
    	document.open();
    	
    	// add element to document
    	Paragraph p = new Paragraph("first para");
    	document.add(p);
    	
    	PdfPTable table = new PdfPTable(2);
    	table.addCell("Name");
    	table.addCell("Age");
    	table.addCell("Akhil");
    	table.addCell("18");
    	document.add(table);
    	
    	document.close();
    	
    	//set pdf name
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentDisposition(ContentDisposition.attachment().filename("akhil.pdf").build());
    	
    	return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

}

