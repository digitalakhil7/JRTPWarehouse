package com.example.service;

import java.util.List;

import com.example.model.Part;

public interface IPartService {
    // create
    public Integer createPart(Part part);

    // read
    public Part getOnePart(Integer id);

    public List<Part> getAllPart();

    // update
    public void updatePart(Part part);

    // delete
    public void deletePart(Integer id);

    // exports
    public byte[] excelExport();

    public byte[] pdfExport();

    // validation
    public boolean isPartCodeExist(String partCode);
}
