package com.example.lancamentosfinanceiros.controllers.utils;

import java.time.LocalDate;
import java.util.Optional;

public record FinancialReleaseFilter(int page, Optional<LocalDate> fromDate, Optional<LocalDate> toDate) {
}
