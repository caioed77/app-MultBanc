package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.models.dto.ListTransferQuantitiesDTO;
import com.multBancapp.apimultbanc.models.projections.ListTransferQuantitiesProjection;
import com.multBancapp.apimultbanc.repositories.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class GeneratedXLSService {

    @Autowired
    private AccountRepository accountRepository;

    public String gerarXls(HttpServletResponse response) throws IOException {
          var dataAccount = accountRepository.listTranferAccounts();

        List<ListTransferQuantitiesDTO> dataResult = new ArrayList<>();

        for (ListTransferQuantitiesProjection listTransferQuantitiesProjection : dataAccount) {
            dataResult.add(new ListTransferQuantitiesDTO(
                    listTransferQuantitiesProjection.getNumAccount(),
                    listTransferQuantitiesProjection.getEmail(),
                    listTransferQuantitiesProjection.getQte()
            ));
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados Financeiros");

        int rowNum = 0;

        for (ListTransferQuantitiesDTO data : dataResult) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            Integer numberAccount = data.numberAccount();
            row.createCell(cellNum++).setCellValue(Objects.requireNonNullElse(numberAccount, 0));

            row.createCell(cellNum++).setCellValue(data.nameHolder());
            row.createCell(cellNum).setCellValue(data.value().doubleValue());
        }

          try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\H1xz\\Documents\\finance.xlsx")) {
                workbook.write(fileOut);
                return "Arquivo Excel gerado com sucesso!";
          } catch (IOException e) {
                return "Erro ao gerar o arquivo Excel: " + e.getMessage();
          }
    }
}
