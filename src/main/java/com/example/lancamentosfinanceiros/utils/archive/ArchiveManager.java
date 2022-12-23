package com.example.lancamentosfinanceiros.utils.archive;

import com.example.lancamentosfinanceiros.exceptions.ArchiveException;

import java.util.List;

public interface ArchiveManager<Dto> {
    void saveToFile(List<Dto> data) throws ArchiveException;

    List<Dto> getFromFile() throws ArchiveException;
}
