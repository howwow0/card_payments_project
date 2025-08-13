package com.howwow.cpploggingservice.rest.controller;

import com.howwow.cpploggingservice.business.service.LogService;
import com.howwow.cpploggingservice.rest.dto.LogDto;
import com.howwow.cpploggingservice.rest.exception.AbstractApiException;
import com.howwow.cpploggingservice.rest.exception.ValidationErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logging")
@AllArgsConstructor
@Tag(name = "Логирование", description = "Логирование разного уровня")
@Validated
public class LogController {

    private final LogService logService;

    @PostMapping("/log")
    @Operation(
            summary = "Создает лог",
            description = "Принимает данные лога, сохраняет их и возвращает"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Лог успешно сохранен",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LogDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации входных данных",
                    content = @Content(
                            schema = @Schema(implementation = ValidationErrorException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            schema = @Schema(implementation = AbstractApiException.class)
                    )
            )
    })
    public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogDto logDto) {
        return ResponseEntity.ok(logService.saveLog(logDto));
    }

    @GetMapping("/fetch-logs")
    @Operation(
            summary = "Возвращает логи",
            description = "Принимает данные фильтрации, возвращает логи согласно фильтру"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные лого возвращены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LogDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации входных данных",
                    content = @Content(
                            schema = @Schema(implementation = LogDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            schema = @Schema(implementation = LogDto.class)
                    )
            )
    })
    public ResponseEntity<List<LogDto>> fetchLogs(
            @RequestParam(name = "level", required = false) LogLevel level,
            @RequestParam(name = "service", required = false) String service,
            @RequestParam(name = "traceId", required = false) String traceId,
            @RequestParam(name = "from", required = false) Instant from,
            @RequestParam(name = "to", required = false) Instant to,
            @RequestParam(name = "limit")
            @Min(1) @Max(100) int limit) {
        return ResponseEntity.ok(logService.fetchLogs(level, service, traceId, from, to, limit));
    }

}
