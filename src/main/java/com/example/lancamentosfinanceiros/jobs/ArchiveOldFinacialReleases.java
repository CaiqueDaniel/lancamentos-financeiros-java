package com.example.lancamentosfinanceiros.jobs;

import com.example.lancamentosfinanceiros.services.FinancialReleaseService;
import com.example.lancamentosfinanceiros.exceptions.ArchiveException;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.utils.archive.ArchiveManager;
import com.example.lancamentosfinanceiros.dtos.FinancialRealeaseArchiveDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@Slf4j
public class ArchiveOldFinacialReleases {
    @Autowired
    private FinancialReleaseService financialReleaseService;

    @Autowired
    private ArchiveManager<FinancialRealeaseArchiveDto> archiveManager;

    private final Logger logger = LoggerFactory.getLogger(ArchiveOldFinacialReleases.class);

    @Scheduled(cron = "0 0 1 * * *")
    public void run() {
        this.logger.info("Iniciando migração de lançamentos financeiros antigos.");

        List<FinancialRelease> oldFinancialReleases = this.financialReleaseService.findAllOldData();

        this.updateArchive(oldFinancialReleases);
        this.financialReleaseService.delete(oldFinancialReleases);

        this.logger.info("Migração concluída.");
    }

    private void updateArchive(List<FinancialRelease> oldFinancialReleases) throws ArchiveException {
        List<FinancialRealeaseArchiveDto> fileData = this.archiveManager.getFromFile();
        List<FinancialRealeaseArchiveDto> data = oldFinancialReleases.stream()
                .map((financialRelease) -> new FinancialRealeaseArchiveDto(financialRelease))
                .collect(Collectors.toList());

        if (fileData.size() != 0) {
            fileData = this.removeOlderData(fileData);
            data.addAll(fileData);
        }

        this.archiveManager.saveToFile(data);
    }

    private List<FinancialRealeaseArchiveDto> removeOlderData(List<FinancialRealeaseArchiveDto> financialReleases) {

        long limitEpochDate = LocalDate.now()
                .minusYears(3)
                .withDayOfMonth(1)
                .atStartOfDay()
                .toEpochSecond(ZoneOffset.UTC);

        return financialReleases.stream().filter((financialRelease) -> {
            LocalDateTime createdAt = LocalDateTime.parse(financialRelease.createdAt);

            return createdAt.toEpochSecond(ZoneOffset.UTC) > limitEpochDate;
        }).collect(Collectors.toList());
    }
}
