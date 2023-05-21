package com.example.sockstore.controller;

import com.example.sockstore.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Загрузка файлов JSON")
public class DUControl {
    private final IService iService;

    public DUControl(IService iService) {
        this.iService = iService;
    }


    @Operation(summary = "\uD83D\uDCBE СКАЧАТЬ ФАЙЛ .json"
            , description = "Введите имя требуемого файла и пароль для доступа к закачки")
    @Parameters(value = {
            @Parameter(name = "name", description = "Имя файла"),
            @Parameter(name = "password", description = "Пароль")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ФАЙЛ ДОСТУПЕН ДЛЯ СКАЧИВАНИЯ",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "403", description = "⚠️ НЕВЕРНЫЙ ВВОД ДАННЫХ",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            )
    })
    @GetMapping("/download/{name}/{password}")
    public ResponseEntity<?> downloadFile(@PathVariable String name, @PathVariable String password) throws FileNotFoundException {
        File file = new File(name + ".json");
        if (file.exists() && password.equals("123")) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + ".json")
                    .body(inputStreamResource);
        } else if (!password.equals("123") && file.exists()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\uD83D\uDEAB Неверный пароль! \uD83D\uDEAB");
        } else if (!file.exists() && password.equals("123")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\uD83D\uDEAB Неверное имя файла загрузки! \uD83D\uDEAB");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\uD83D\uDEAB Неверный пароль и имя файла загрузки! \uD83D\uDEAB");
        }
    }


    @Operation(summary = "\uD83C\uDF00 ЗАГРУЗИТЬ ФАЙЛ .json"
            , description = "Выберите файл для загрузки")
    @RequestBody(description = "Выберите файл формата .json")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ФАЙЛ УСПЕШНО ЗАГРУЖЕН",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "❌ ФАЙЛ ПОВРЕЖДЕН",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "500", description = "❗ НЕВЕРНЫЙ ФОРМАТ JSON",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            )
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile uploadFile) {
        try {
            return ResponseEntity.ok(iService.up(uploadFile));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("\uD83D\uDCBE❌ Файл поврежден!");
        }
    }
}
