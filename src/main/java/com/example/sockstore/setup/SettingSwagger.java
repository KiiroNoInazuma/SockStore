package com.example.sockstore.setup;

import com.example.sockstore.model.Socks;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Tag(name = "Операции на складе")
@OpenAPIDefinition(info = @Info(title = "Склад носков \uD83E\uDDE6", version = "Версия 1.0"),
        servers = @Server(description = "Сервер склада носков", url = "http://127.0.0.1:8080"))
public interface SettingSwagger {


    @Operation(summary = "\uD83D\uDD0E КОЛИЧЕСТВО НОСКОВ НА СКЛАДЕ ПО ВВЕДЕННЫМ ПАРАМЕТРАМ"
            , description = "Введите параметры носков")
    @Parameters(value = {
            @Parameter(name = "color", description = "Цвет носков", example = "Красные"),
            @Parameter(name = "size", description = "Размер носков", example = "41"),
            @Parameter(name = "cottonPath", description = "Содержание хлопка", example = "90")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ЗАПРОС ВЫПОЛНЕН",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "404", description = "⚠️ ДАННЫЕ ОТСУТСТВУЮТ",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            )
    })
    @GetMapping
    ResponseEntity<String> getCountSocks(String color, int size, int cottonPath);


    @Operation(summary = "\uD83D\uDCE6 ПРИХОД ТОВАРА НА СКЛАД"
            , description = "Введите количество носков и добавьте корректный JSON объект")
    @Parameters(value = {
            @Parameter(name = "quantity", description = "Количество носков", example = "20"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ТОВАР ДОБАВЛЕН",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "404", description = "❌ ОШИБКА ВВЕДЕННЫХ ДАННЫХ!",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            ),
            @ApiResponse(
                    responseCode = "400", description = "❗ НЕКОРРЕКТНЫЙ ФОРМАТ JSON ОБЪЕКТА",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            )
    })
    @PostMapping
    ResponseEntity<String> add(Socks socks, int quantity) throws IOException;


    @Operation(summary = "\uD83D\uDE9B ОТПУСК ТОВАРА СО СКЛАДА"
            , description = "Введите количество носков и добавьте корректный json объект")
    @Parameters(value = {
            @Parameter(name = "quantity", description = "Количество носков", example = "20"),
    })

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ УСПЕШНЫЙ ОТПУСК ТОВАРА",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "404", description = "⚠️ ОШИБКА ЧИСЛА ИЛИ ЗНАЧЕНИЯ!",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            ),
            @ApiResponse(
                    responseCode = "400", description = "❗ НЕКОРРЕКТНЫЙ ФОРМАТ JSON ОБЪЕКТА",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            )
    })
    @PutMapping
    ResponseEntity<String> del(Socks socks, int quantity);


    @Operation(summary = "\uD83D\uDDD1️ СПИСАНИЕ БРАКОВАННЫХ НОСКОВ "
            , description = "Добавьте корректный json объект")

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ТОВАР СПИСАН СО СКЛАДА",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "404", description = "❌ ОШИБКА ВВЕДЕННЫХ ДАННЫХ!",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            ),
            @ApiResponse(
                    responseCode = "400", description = "❗ НЕКОРРЕКТНЫЙ ФОРМАТ JSON ОБЪЕКТА",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            )
    })
    @DeleteMapping
    ResponseEntity<String> delAll(Socks socks);


    @Operation(summary = "\uD83D\uDCCB ПОЛНЫЙ ОТЧЕТ ПО СКЛАДУ"
            , description = "Показывает все носки, которые есть на складе(количество и тип)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "✅ ОТЧЕТ ВЫГРУЖЕН",
                    content = {@Content(
                            schema = @Schema(hidden = true))}
            ),
            @ApiResponse(
                    responseCode = "404", description = "⚠️ ДАННЫЕ ОТСУТСТВУЮТ ",
                    content = {@Content(
                            schema = @Schema(hidden = true))}

            )
    })
    @GetMapping("/all")
    ResponseEntity<StringBuilder> all();
}
