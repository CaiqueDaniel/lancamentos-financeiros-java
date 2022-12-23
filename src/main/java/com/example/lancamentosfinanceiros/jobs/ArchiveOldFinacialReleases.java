package com.example.lancamentosfinanceiros.jobs;

import com.example.lancamentosfinanceiros.controllers.services.FinancialReleaseService;
import com.example.lancamentosfinanceiros.exceptions.ArchiveException;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.utils.archive.ArchiveManager;
import com.example.lancamentosfinanceiros.utils.archive.dtos.FinancialRealeaseArchiveDto;
import com.example.lancamentosfinanceiros.utils.archive.implementation.FinancialReleaseArchiveManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@Slf4j
public class ArchiveOldFinacialReleases {
    @Autowired
    private FinancialReleaseService financialReleaseService;

    private final Logger logger = LoggerFactory.getLogger(ArchiveOldFinacialReleases.class);

    @Scheduled(cron = "0 0 1 * * *")
    public void run() {
        this.logger.info("Iniciando migração de lançamentos financeiros antigos.");

        List<FinancialRelease> oldFinancialReleases = this.financialReleaseService.findAllOldData();

        if (oldFinancialReleases.size() == 0) {
            this.logger.info("Nenhum lançamento financeiro antigo.");
            return;
        }

        this.updateArchive(oldFinancialReleases);
        this.financialReleaseService.delete(oldFinancialReleases);

        this.logger.info("Migração concluída.");
    }

    private void updateArchive(List<FinancialRelease> oldFinancialReleases) throws ArchiveException {
        ArchiveManager<FinancialRealeaseArchiveDto> manager = new FinancialReleaseArchiveManager();

        List<FinancialRealeaseArchiveDto> data = oldFinancialReleases.stream()
                .map((financialRelease) -> new FinancialRealeaseArchiveDto(financialRelease))
                .collect(Collectors.toList());

        manager.saveToFile(data);
    }
}
