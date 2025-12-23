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

import com.example.model.WhUserType;
import com.example.repo.WhUserTypeRepository;
import com.example.service.IWhUserTypeService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {
	
	@Autowired
	private WhUserTypeRepository whRepo;;

	@Override
	public Integer createWhUserType(WhUserType whUserType) {
		if (isEmailExist(whUserType.getWhUserEmail())) {
			throw new RuntimeException("WhUserType already exist with email: " + whUserType.getWhUserEmail());
		}
		return whRepo.save(whUserType).getWhId();
	}

	@Override
	public WhUserType getOneWhUserType(Integer id) {
		if(id==null) {
			throw new RuntimeException("id cannot be null");
		}
		return whRepo.findById(id).orElseThrow(()-> new RuntimeException("WhUserType not found with id: "+id));
	}

	@Override
	public List<WhUserType> getAllWhUserType() {
		return whRepo.findAll();
	}

	@Override
	public void updateWhUserType(WhUserType whUserType) {
		getOneWhUserType(whUserType.getWhId());
		whRepo.save(whUserType);
	}

	@Override
	public void deleteWhUserType(Integer id) {
		getOneWhUserType(id);
		whRepo.deleteById(id);
	}

	@Override
	public byte[] excelExport() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("whId");
			row0.createCell(1).setCellValue("whUserType");
			row0.createCell(2).setCellValue("whUserCode");
			row0.createCell(3).setCellValue("whUserEmail");
			row0.createCell(4).setCellValue("whUserContact");
			row0.createCell(5).setCellValue("whUserIdType");
			row0.createCell(6).setCellValue("whIdNumber");
			
			List<WhUserType> whList = whRepo.findAll();
			int rowIndex = 1;
			
			for(WhUserType wh : whList) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(wh.getWhId());
				row.createCell(1).setCellValue(wh.getWhUserType());
				row.createCell(2).setCellValue(wh.getWhUserCode());
				row.createCell(3).setCellValue(wh.getWhUserEmail());
				row.createCell(4).setCellValue(wh.getWhUserContact());
				row.createCell(5).setCellValue(wh.getWhUserIdType());
				row.createCell(6).setCellValue(wh.getWhIdNumber());			
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
		
		table.addCell("whId");
		table.addCell("whUserType");
		table.addCell("whUserCode");
		table.addCell("whUserEmail");
		table.addCell("whUserContact");
		table.addCell("whUserIdType");
		table.addCell("whIdNumber");
		
		List<WhUserType> whList = whRepo.findAll();
		for(WhUserType wh : whList) {
			table.addCell(wh.getWhId().toString());
			table.addCell(wh.getWhUserType());
			table.addCell(wh.getWhUserCode());
			table.addCell(wh.getWhUserEmail());
			table.addCell(wh.getWhUserContact());
			table.addCell(wh.getWhUserIdType());
			table.addCell(wh.getWhIdNumber());
		}
		
		document.add(table);
		document.close();
		
		return out.toByteArray();
	}
	
	@Override
	public boolean isEmailExist(String whUserEmail) {
		return whRepo.existsByWhUserEmail(whUserEmail);
	}

}
