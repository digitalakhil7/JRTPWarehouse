package com.example.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.OrderNotFoundException;
import com.example.model.OrderMethod;
import com.example.repo.OrderMethodRepository;
import com.example.service.IOrderMethodService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository orderRepo;

	@Override
	public Integer createOrderMethod(OrderMethod orderMethod) {
		if (isOrderCodeExist(orderMethod.getOrderCode())) {
			throw new RuntimeException("OrderMethod already exist with code: " + orderMethod.getOrderCode());
		}
		return orderRepo.save(orderMethod).getOrderId();
	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		if (id == null) {
			throw new RuntimeException("id cannot be null");
		}
		return orderRepo.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("OrderMethod not found with id: " + id));
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {
		return orderRepo.findAll();
	}
	
	@Override
	public Map<Integer, String> findAllOrderIdAndCodeByOrderMode(String orderMode) {
		return orderRepo.findAllOrderIdAndCodeByOrderMode(orderMode).stream().collect(Collectors.toMap(
				ob->Integer.valueOf(ob[0].toString()), ob->String.valueOf(ob[1])));
	}

	@Override
	public void updateOrderMethod(OrderMethod orderMethod) {
		getOneOrderMethod(orderMethod.getOrderId());
		orderRepo.save(orderMethod);
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		getOneOrderMethod(id);
		orderRepo.deleteById(id);
	}

	@Override
	public byte[] excelExport() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet();
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("orderId");
			row0.createCell(1).setCellValue("orderMode");
			row0.createCell(2).setCellValue("orderCode");
			row0.createCell(3).setCellValue("orderType");
			row0.createCell(4).setCellValue("orderAccept");
			row0.createCell(5).setCellValue("orderDesc");

			List<OrderMethod> orderMethodList = orderRepo.findAll();
			int rowIndex = 1;

			for (OrderMethod orderMethod : orderMethodList) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(orderMethod.getOrderId());
				row.createCell(1).setCellValue(orderMethod.getOrderMode());
				row.createCell(2).setCellValue(orderMethod.getOrderCode());
				row.createCell(3).setCellValue(orderMethod.getOrderType());
				row.createCell(4).setCellValue(
						orderMethod.getOrderAccept() != null ? String.join(", ", orderMethod.getOrderAccept()) : "");
				row.createCell(5).setCellValue(orderMethod.getOrderDesc());
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

		PdfPTable table = new PdfPTable(6);

		table.addCell("orderId");
		table.addCell("orderMode");
		table.addCell("orderCode");
		table.addCell("orderType");
		table.addCell("orderAccept");
		table.addCell("orderDesc");

		List<OrderMethod> orderMethodList = orderRepo.findAll();
		for (OrderMethod orderMethod : orderMethodList) {
			table.addCell(orderMethod.getOrderId().toString());
			table.addCell(orderMethod.getOrderMode());
			table.addCell(orderMethod.getOrderCode());
			table.addCell(orderMethod.getOrderType());
			table.addCell(orderMethod.getOrderAccept() != null ? String.join(", ", orderMethod.getOrderAccept()) : "");
			table.addCell(orderMethod.getOrderDesc());
		}

		document.add(table);
		document.close();

		return out.toByteArray();
	}

	@Override
	public boolean isOrderCodeExist(String orderCode) {
		return orderRepo.existsByOrderCode(orderCode);
	}

}
