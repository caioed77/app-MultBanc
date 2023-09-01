package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.entities.AccountEntity;
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

@Service
public class GeneratedXLSService {

    @Autowired
    private AccountRepository accountRepository;

    public void gerarXls(HttpServletResponse response) throws IOException {
        var dataAccount = accountRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados Financeiros");

        int rowNum = 0;
        for (AccountEntity dado : dataAccount) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            row.createCell(cellNum++).setCellValue(dado.getId());
            row.createCell(cellNum++).setCellValue(dado.getNumber());
            row.createCell(cellNum++).setCellValue(dado.getAgency());
            row.createCell(cellNum++).setCellValue(dado.getHolder().getDocument());
            row.createCell(cellNum).setCellValue(dado.getHolder().getId());
        }

        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\suporte\\Documents\\finance.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo Excel: " + e.getMessage());
        }
    }
}
