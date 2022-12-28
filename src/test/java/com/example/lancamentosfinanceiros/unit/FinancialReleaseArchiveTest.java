package com.example.lancamentosfinanceiros.unit;

import com.example.lancamentosfinanceiros.dtos.FinancialRealeaseArchiveDto;
import com.example.lancamentosfinanceiros.dtos.requests.RequestFinancialReleaseDto;
import com.example.lancamentosfinanceiros.dtos.requests.RequestUserDto;
import com.example.lancamentosfinanceiros.jobs.ArchiveOldFinacialReleases;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.User;
import com.example.lancamentosfinanceiros.repositories.FinancialReleaseRepository;
import com.example.lancamentosfinanceiros.services.UserService;
import com.example.lancamentosfinanceiros.utils.archive.ArchiveManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class FinancialReleaseArchiveTest {
    @Autowired
    private UserService userService;

    @Autowired
    private FinancialReleaseRepository financialReleaseRepository;

    @Autowired
    private ArchiveOldFinacialReleases archiveOldFinacialReleasesJob;

    @Autowired
    private ArchiveManager<FinancialRealeaseArchiveDto> archiveManager;

    @Value("${file-storage-name}")
    private String filename;

    private static User user;

    @Test
    public void shouldMoveRecordsOlderThan3MonthsToFile() throws Exception {
        this.deleteTestFile();

        FinancialReleaseArchiveTest.user = this.getTestUser();
        List<FinancialRelease> financialReleases = new ArrayList<>();
        financialReleases.add(this.getTestFinancialRelease());
        financialReleases.add(this.getTestFinancialRelease());

        financialReleases.forEach((financialRelease) -> {
            financialRelease.setCreatedAt(LocalDateTime.now().minusMonths(4));

            this.financialReleaseRepository.save(financialRelease);
        });

        this.financialReleaseRepository.flush();

        Assertions.assertThat(this.financialReleaseRepository.findAll().size()).isEqualTo(financialReleases.size());

        this.archiveOldFinacialReleasesJob.run();

        Assertions.assertThat(this.financialReleaseRepository.findAll().size()).isEqualTo(0);

        List<FinancialRealeaseArchiveDto> financialReleaseArchiveDtos = this.archiveManager.getFromFile();

        for (int i = 0; i < financialReleaseArchiveDtos.size(); i++) {
            FinancialRealeaseArchiveDto dto = financialReleaseArchiveDtos.get(i);
            FinancialRelease data = financialReleases.get(i);

            Assertions.assertThat(dto.userId).isEqualTo(FinancialReleaseArchiveTest.user.getId());
            Assertions.assertThat(dto.username).isEqualTo(FinancialReleaseArchiveTest.user.getUsername());
            Assertions.assertThat(dto.value).isEqualTo(data.getValue());
            Assertions.assertThat(dto.description).isEqualTo(data.getDescription());
            Assertions.assertThat(LocalDateTime.parse(dto.createdAt).toLocalDate())
                    .isEqualTo(data.getCreatedAt().toLocalDate());
        }
    }

    @Test
    public void shouldRemoveRecordsOlderThan3YearsFromFile() throws Exception {
        this.deleteTestFile();

        FinancialReleaseArchiveTest.user = this.getTestUser();
        List<FinancialRelease> financialReleases = new ArrayList<>();
        financialReleases.add(this.getTestFinancialRelease());
        financialReleases.add(this.getTestFinancialRelease());

        financialReleases.forEach((financialRelease) -> {
            financialRelease.setCreatedAt(LocalDateTime.now().minusMonths(4));
        });

        financialReleases.add(this.getTestFinancialRelease());
        financialReleases.add(this.getTestFinancialRelease());
        financialReleases.get(2).setCreatedAt(LocalDateTime.now().minusYears(4));
        financialReleases.get(3).setCreatedAt(LocalDateTime.now().minusYears(3).minusMonths(1));

        financialReleases.forEach((financialRelease) -> this.financialReleaseRepository.save(financialRelease));

        this.financialReleaseRepository.flush();

        Assertions.assertThat(this.financialReleaseRepository.findAll().size()).isEqualTo(financialReleases.size());

        this.archiveOldFinacialReleasesJob.run();

        Assertions.assertThat(this.financialReleaseRepository.findAll().size()).isEqualTo(0);

        List<FinancialRealeaseArchiveDto> financialReleaseArchiveDtos = this.archiveManager.getFromFile();

        Assertions.assertThat(financialReleaseArchiveDtos.size()).isEqualTo(financialReleases.size());

        this.archiveOldFinacialReleasesJob.run();

        financialReleaseArchiveDtos = this.archiveManager.getFromFile();

        Assertions.assertThat(financialReleaseArchiveDtos.size()).isEqualTo(2);
    }

    private User getTestUser() {

        if (FinancialReleaseArchiveTest.user != null)
            return FinancialReleaseArchiveTest.user;

        RequestUserDto userDto = new RequestUserDto();
        userDto.nome = "Teste";
        userDto.email = "teste@teste.com";
        userDto.senha = "123";

        return this.userService.create(userDto);
    }

    private FinancialRelease getTestFinancialRelease() {
        RequestFinancialReleaseDto financialReleaseDto = new RequestFinancialReleaseDto();
        financialReleaseDto.descricao = "Teste";
        financialReleaseDto.valor = new BigDecimal("12.00");

        return new FinancialRelease(financialReleaseDto, FinancialReleaseArchiveTest.user);
    }

    private void deleteTestFile() {
        File file = new File(this.filename);

        if (file.exists())
            file.delete();
    }
}
