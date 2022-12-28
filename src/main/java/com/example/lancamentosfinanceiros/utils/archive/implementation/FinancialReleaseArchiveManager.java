package com.example.lancamentosfinanceiros.utils.archive.implementation;

import com.example.lancamentosfinanceiros.exceptions.ArchiveException;
import com.example.lancamentosfinanceiros.utils.archive.ArchiveManager;
import com.example.lancamentosfinanceiros.dtos.FinancialRealeaseArchiveDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FinancialReleaseArchiveManager implements ArchiveManager<FinancialRealeaseArchiveDto> {
    private File file;
    @Value("${file-storage-name}")
    private String filename;

    @PostConstruct
    public void initialize() {
        this.file = new File(this.filename);
    }

    @Override
    public void saveToFile(List<FinancialRealeaseArchiveDto> data) throws ArchiveException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(this.file, data);
            mapper.registerModule(new JavaTimeModule());
        } catch (Exception exception) {
            throw new ArchiveException(exception.getLocalizedMessage(), exception);
        }
    }

    @Override
    public List<FinancialRealeaseArchiveDto> getFromFile() throws ArchiveException {
        try {
            if (!this.file.exists())
                return new ArrayList<>();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(this.file, new TypeReference<List<FinancialRealeaseArchiveDto>>() {
            });
        } catch (Exception exception) {
            throw new ArchiveException(exception.getLocalizedMessage(), exception);
        }
    }
}
